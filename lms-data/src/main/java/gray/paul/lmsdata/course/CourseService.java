/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsdata.course;

import gray.paul.lmsdata.user.User;
import java.util.List;

/**
 *
 * @author pfgray
 */
public interface CourseService {
    
    public Course getCourseForId(String Id);
    
    public List<Course> getCoursesForUser(User user);
    
}
