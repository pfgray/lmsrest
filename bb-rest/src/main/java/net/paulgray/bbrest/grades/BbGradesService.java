/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.grades;

import blackboard.data.course.CourseMembership;
import blackboard.data.gradebook.Score;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.gradebook.ScoreDbLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.course.BbCourseService;
import net.paulgray.bbrest.course.LocalCachedBbCourseService;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.grades.Grade;
import net.paulgray.lmsrest.grades.GradesService;
import net.paulgray.lmsrest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author paul
 */
public class BbGradesService implements GradesService {
    
    @Autowired
    BbCourseService bbCourseService;

    public List<Grade> getGradesForUser(User user, String courseFilter) {
        try {
            CourseMembershipDbLoader courseMembershipDbLoader = CourseMembershipDbLoader.Default.getInstance();

            List<CourseMembership> courseMemberships = courseMembershipDbLoader
                    .loadByUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class), null, true, true);
            List<Grade> grades = new LinkedList<Grade>();
            
            LocalCachedBbCourseService courseService = new LocalCachedBbCourseService(bbCourseService);
            for(CourseMembership courseMembership : courseMemberships){
                if (courseMembership != null && courseMembership.getCourseId().getExternalString().contains(courseFilter)) {
                    ScoreDbLoader scoreDbLoader = ScoreDbLoader.Default.getInstance();
                    List<Score> scores = scoreDbLoader.loadByCourseMembershipId(courseMembership.getId());
                    for (Score score : scores) {
                        grades.add(new BbGrade(score, courseService.getCourseForId(courseMembership.getCourseId().getExternalString())));
                    }
                }
            }
            return grades;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbGradesService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<Grade>();
        }
    }

    public List<Grade> getGradesForUserAndCourse(User user, Course course) {
        try {
            if (!BbCourseService.currentUserCanViewCourse(course.getId())) {
                throw new AccessDeniedException("User cannot view course: " + course.getId());
            }

            CourseMembershipDbLoader courseMembershipDbLoader = CourseMembershipDbLoader.Default.getInstance();

            CourseMembership courseMembership = courseMembershipDbLoader.loadByCourseAndUserId(
                    BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class),
                    BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            List<Grade> grades = new LinkedList<Grade>();
            if (courseMembership != null) {
                ScoreDbLoader scoreDbLoader = ScoreDbLoader.Default.getInstance();
                List<Score> scores = scoreDbLoader.loadByCourseMembershipId(courseMembership.getId());
                for (Score score : scores) {
                    grades.add(new BbGrade(score, course));
                }
            }
            return grades;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbGradesService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<Grade>();
        }
    }

}
