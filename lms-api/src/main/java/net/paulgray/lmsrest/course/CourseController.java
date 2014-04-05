/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.course;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pfgray
 */
@Controller
@RequestMapping(value = CourseController.PATH)
public class CourseController {

    public static final String PATH = "courses";
    @Autowired
    CourseService courseService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCourses(@ContextUser User user) {
        List<Course> courses = courseService.getCoursesForUser(user);
        return new ResponseEntity(courses, HttpStatus.OK);
    }

}