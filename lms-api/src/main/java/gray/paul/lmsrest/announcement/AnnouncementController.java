/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.announcement;

import gray.paul.lmsdata.announcement.AnnouncementService;
import gray.paul.lmsdata.announcement.Announcement;
import gray.paul.lmsdata.user.User;
import gray.paul.lmsdata.user.UserService;
import gray.paul.lmsrest.web.ContextUser;
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
@RequestMapping(value = "/announcements")
public class AnnouncementController {

    @Autowired
    UserService userService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    AnnouncementResourceAssembler announcementResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAnnouncements(@ContextUser User user) {
        List<Announcement> announcements = announcementService.getAnnouncementsForUser(user);
        return new ResponseEntity<List<AnnouncementResource>>(announcementResourceAssembler.toResources(announcements), HttpStatus.OK);
    }
}
