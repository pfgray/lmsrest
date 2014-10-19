package net.paulgray.mockrest;

import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.enrollment.Enrollment;
import net.paulgray.lmsrest.enrollment.EnrollmentService;
import net.paulgray.lmsrest.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pgray on 10/19/14.
 */
@Component
public class MockEnrollmentService implements EnrollmentService {

    public Enrollment getEnrollmentForId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Enrollment> getEnrollmentsForCourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Enrollment> getEnrollmentsForUser(User userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
