/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.announcement;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.user.UserService;
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

/**
 *
 * @author pfgray
 */
@Controller
public class AnnouncementController {
    
    public static final String PATH = "announcements";

    @Autowired
    UserService userService;
    @Autowired
    AnnouncementService announcementService;

    @RequestMapping(value = AnnouncementController.PATH, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAnnouncements(@ContextUser User user) {
        List<Announcement> announcements = announcementService.getAnnouncementsForUser(user);
        return new ResponseEntity(announcements, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = CourseController.PATH + "/{course}/" + AnnouncementController.PATH)
    public ResponseEntity getAnnouncements(@ContextUser User user, @PathVariable Course course) {
        List<Announcement> announcements = announcementService.getAnnouncementsForCourseAndUser(course, user);
        return new ResponseEntity(announcements, HttpStatus.OK);
    }
}
