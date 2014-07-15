/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.enrollment.Enrollment;
import net.paulgray.lmsrest.enrollment.EnrollmentService;
import net.paulgray.lmsrest.user.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author paul
 */
@Component
public class MockEnrollmentService implements EnrollmentService {

    public Enrollment getEnrollmentForId(String id) {
        return new Enrollment(new Date(), "id", "available", "course", "user", "role");
    }

    public List<Enrollment> getEnrollmentsForCourse(Course course) {
        return Arrays.asList( new Enrollment(new Date(), "id", "available", "course", "user", "role"),  new Enrollment(new Date(), "id", "available", "course", "user", "role"));
    }

    public List<Enrollment> getEnrollmentsForUser(User userId) {
        return Arrays.asList( new Enrollment(new Date(), "id", "available", "course", "user", "role"),  new Enrollment(new Date(), "id", "available", "course", "user", "role"));
    }
    
}
