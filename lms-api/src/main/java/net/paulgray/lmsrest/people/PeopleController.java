/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.people;

import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseController;
import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author paul
 */
@Controller
public class PeopleController {
    
    public static final String PATH = "people";
    
    @Autowired
    PeopleService peopleService;
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CourseController.PATH + "/{course}/" + PeopleController.PATH)
    public ResponseEntity getAssignmentsForCourse(@ContextUser User user, @PathVariable Course course) {
        return new ResponseEntity(peopleService.getPeopleForUserAndCourse(user.getId(), course.getId()), HttpStatus.OK);
    }
}
