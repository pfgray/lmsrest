/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.enrollment;

import blackboard.admin.persist.course.EnrollmentLoader;
import blackboard.admin.persist.course.impl.EnrollmentDbLoader;
import blackboard.persist.DbLoaderFactory;
import blackboard.persist.PersistenceException;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.course.BbCourseService;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.enrollment.Enrollment;
import net.paulgray.lmsrest.enrollment.EnrollmentService;
import net.paulgray.lmsrest.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author pfgray
 */
public class BbEnrollmentService implements EnrollmentService {

    @Override
    public Enrollment getEnrollmentForId(String id) {
        try {
            EnrollmentLoader enrollmentDbLoader = EnrollmentDbLoader.Default.getInstance();
            blackboard.admin.data.course.Enrollment template = new blackboard.admin.data.course.Enrollment();
            template.setId(BlackboardUtilities.getIdFromPk(id, blackboard.admin.data.course.Enrollment.class));
            return new BbEnrollment(enrollmentDbLoader.load(template).get(0));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsForCourse(Course course) {
        try {
            if (!BbCourseService.currentUserCanViewCourse(course.getId())) {
                throw new AccessDeniedException("User cannot view course: " + course.getId());
            }
            EnrollmentLoader enrollmentDbLoader = EnrollmentDbLoader.Default.getInstance();
            blackboard.admin.data.course.Enrollment template = new blackboard.admin.data.course.Enrollment();
            template.setCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
            List<blackboard.admin.data.course.Enrollment> enrollments = enrollmentDbLoader.load(template);
            List<Enrollment> toReturn = new LinkedList<Enrollment>();
            for (blackboard.admin.data.course.Enrollment enrollment : enrollments) {
                toReturn.add(new BbEnrollment(enrollment));
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsForUser(User user) {
        try {
            EnrollmentLoader enrollmentDbLoader = EnrollmentDbLoader.Default.getInstance();
            blackboard.admin.data.course.Enrollment template = new blackboard.admin.data.course.Enrollment();
            template.setUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            List<blackboard.admin.data.course.Enrollment> enrollments = enrollmentDbLoader.load(template);
            List<Enrollment> toReturn = new LinkedList<Enrollment>();
            for (blackboard.admin.data.course.Enrollment enrollment : enrollments) {
                toReturn.add(new BbEnrollment(enrollment));
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
