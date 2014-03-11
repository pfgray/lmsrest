/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.Arrays;
import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseService;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
public class MockCourseService implements CourseService {

    public Course getCourseForId(String Id) {
        return new Course("id", null, "Intro to English", null);
    }

    public List<Course> getCoursesForUser(User user) {
        return Arrays.asList(new Course("id", null, "Intro to English", null)
                ,new Course("id", null, "Intro to English", null)
                ,new Course("id", null, "Intro to English", null));
    }
    
    
    
}
