/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.grades;

import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseController;
import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUser;
import net.paulgray.lmsrest.web.LmsRestConstants;
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
 * @author paul
 */
@Controller
@RequestMapping("/" + LmsRestConstants.API_PREFIX)
public class GradesController {
        
    public static final String PATH = "grades";

    @Autowired
    GradesService gradesService;

    @RequestMapping(value = GradesController.PATH, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAnnouncements(@ContextUser User user, @RequestParam(value = "courseFilter", defaultValue = "") String courseFilter) {
        return new ResponseEntity(gradesService.getGradesForUser(user, courseFilter),HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CourseController.PATH + "/{course}/" + GradesController.PATH)
    public ResponseEntity getAnnouncements(@ContextUser User user, @PathVariable Course course) {
        return new ResponseEntity(gradesService.getGradesForUserAndCourse(user, course), HttpStatus.OK);
    }
    
    
}
