/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.enrollment;

import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;
import java.util.List;

/**
 *
 * @author pfgray
 */
public interface EnrollmentService {
    
    public Enrollment getEnrollmentForId(String id);
    
    public List<Enrollment> getEnrollmentsForCourse(Course course);
    
    public List<Enrollment> getEnrollmentsForUser(User userId);
    
}
