/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.enrollment;

import net.paulgray.lmsrest.course.CourseResource;
import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.user.UserService;
import net.paulgray.lmsrest.web.ContextUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pfgray
 */
@Controller
@RequestMapping(value = "/enrollments")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;
    @Autowired
    EnrollmentResourceAssembler enrollmentResourceAssembler;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEnrollments(@ContextUser User user) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForUser(user);
        if (enrollments == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<EnrollmentResource>>(enrollmentResourceAssembler.toResources(enrollments), HttpStatus.OK);
        }
    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{enrollmentId}", produces = "application/json")
//    public ResponseEntity getEnrollment(ModelMap model, @PathVariable String enrollmentId) {
//        Enrollment enrollment = enrollmentService.getEnrollmentForId(enrollmentId);
//        if(enrollment == null){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }else{
//            return new ResponseEntity<EnrollmentResource>(enrollmentResourceAssembler.toResource(enrollment), HttpStatus.OK);
//        }
//    }
}