/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.course;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUser;
import java.util.List;
import net.paulgray.lmsrest.assignment.Assignment;
import net.paulgray.lmsrest.assignment.AssignmentService;
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
@RequestMapping(value = AssignmentController.PATH)
public class AssignmentController {

    public static final String PATH = "assignments";
    @Autowired
    AssignmentService assignmentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAssignments(@ContextUser User user) {
        List<Assignment> assignments = assignmentService.getAssignments(user);
        return new ResponseEntity(assignments, HttpStatus.OK);
    }

}