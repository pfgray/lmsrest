/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.user;

import net.paulgray.lmsrest.web.ContextUser;
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
@RequestMapping(value = "/" + UserController.PATH)
public class UserController {
    
    public static final String PATH = "user";

    @Autowired
    UserService userService;

    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json") 
    public ResponseEntity getUser(@ContextUser User user) {
        return new ResponseEntity(user, HttpStatus.OK);
    }
    
    
    //we should move these controller methods to a different java package and secure them differently, as they are essentially 'admin' functions 
    /*
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}", produces = "application/json")
    public ResponseEntity getUser(ModelMap model, @PathVariable String userId) {
        User user = userService.getUserForId(userId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<UserResource>(userResourceAssembler.toResource(user), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/enrollments", produces = "application/json")
    public ResponseEntity getEnrollments(ModelMap model, @PathVariable String userId) {
        User user = userService.getUserForId(userId);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForUser(user);
        return new ResponseEntity<List<EnrollmentResource>>(enrollmentResourceAssembler.toResources(enrollments), HttpStatus.OK);
    }
    * */

}
