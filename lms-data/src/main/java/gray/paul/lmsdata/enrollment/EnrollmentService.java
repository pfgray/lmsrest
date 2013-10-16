/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsdata.enrollment;

import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.user.User;
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
