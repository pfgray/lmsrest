/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.mockrest;

import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseService;
import net.paulgray.lmsrest.user.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author paul
 */
@Component
public class MockCourseService implements CourseService {

    public Course getCourseForId(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Course> getCoursesForUser(User user, String courseFilter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
