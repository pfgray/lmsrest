/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.people;

import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.user.UserDbLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.course.BbCourseService;
import net.paulgray.lmsrest.people.PeopleService;
import net.paulgray.lmsrest.people.Person;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author paul
 */
public class BbPeopleService implements PeopleService {

    public List<Person> getPeopleForUserAndCourse(String userId, String courseId) {
        try {
            if (!BbCourseService.currentUserCanViewCourse(courseId)) {
                throw new AccessDeniedException("User cannot view course: " +courseId);
            }
            
            Set<Person> people = new HashSet<Person>();
            UserDbLoader userDbLoader = UserDbLoader.Default.getInstance();
            
            List<User> users = userDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(courseId, blackboard.data.course.Course.class));
            List<CourseMembership> courseMemberships = CourseMembershipDbLoader.Default.getInstance().loadByCourseIdWithUserInfo(BlackboardUtilities.getIdFromPk(courseId, blackboard.data.course.Course.class));
            
            for (CourseMembership enrollment : courseMemberships) {
                if(enrollment.getRole() != null){
                    for(User user : users){
                        if(user.getId().equals(enrollment.getUserId())){
                            people.add(new BbPerson(user, enrollment.getRole().getFieldName()));
                        }
                    }
                }
            }
            return new ArrayList(people);
        } catch (PersistenceException ex) {
            Logger.getLogger(BbPeopleService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<Person>();
        }

    }

}
