/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.course;

import net.paulgray.lmsrest.user.User;
import java.util.List;

/**
 *
 * @author pfgray
 */
public interface CourseService {
    
    public Course getCourseForId(String Id);
    
    public List<Course> getCoursesForUser(User user, String courseFilter);
    
}
