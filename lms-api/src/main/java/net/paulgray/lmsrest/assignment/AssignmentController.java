/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.assignment;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUser;
import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pfgray
 */
@Controller
public class AssignmentController<T> {

    public static final String PATH = "assignments";
    @Autowired
    AssignmentService assignmentService;

    @RequestMapping(produces = "application/json", value=AssignmentController.PATH, method = RequestMethod.GET)
    public ResponseEntity getAssignments(@ContextUser User user, @RequestParam(value = "courseFilter", defaultValue = "") String courseFilter) {
        List<T> assignments = assignmentService.getAssignments(user, courseFilter);
        return new ResponseEntity(assignments, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CourseController.PATH + "/{course}/" + AssignmentController.PATH)
    public ResponseEntity getAssignmentsForCourse(@ContextUser User user, @PathVariable Course course) {
        List<T> assignments = assignmentService.getAssignments(user, course);
        return new ResponseEntity(assignments, HttpStatus.OK);
    }

}