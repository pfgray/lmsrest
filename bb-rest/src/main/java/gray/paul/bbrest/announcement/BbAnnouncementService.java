/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.announcement;

import blackboard.persist.DbLoaderFactory;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.announcement.AnnouncementDbLoader;
import gray.paul.bbrest.BlackboardUtilities;
import gray.paul.lmsdata.announcement.Announcement;
import gray.paul.lmsdata.announcement.AnnouncementService;
import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author pfgray
 */
@Service
public class BbAnnouncementService implements AnnouncementService {

    /**
     * Controls the amount of days to look back for announcements
     */
    public static int DAYS_BACK = 31;

    public List<Announcement> getAnnouncementsForUser(User user) {
        try {
            AnnouncementDbLoader announcementDbLoader = AnnouncementDbLoader.Default.getInstance();
            List<blackboard.data.announcement.Announcement> announcements = announcementDbLoader.loadAvailableByUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            List<Announcement> toReturn = new LinkedList<Announcement>();
            for (blackboard.data.announcement.Announcement a : announcements) {
                toReturn.add(new BbAnnouncement(a));
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbAnnouncementService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<Announcement>();
        }
    }

    public List<Announcement> getAnnouncementsForCourseAndUser(Course course, User user) {
        try {
            AnnouncementDbLoader announcementDbLoader = AnnouncementDbLoader.Default.getInstance();
            Id courseId = BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class);
            Id userId = BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class);

            List<blackboard.data.announcement.Announcement> announcements = announcementDbLoader.loadAvailableByCourseIdAndUserId(courseId, userId, DAYS_BACK, true);
            List<Announcement> toReturn = new LinkedList<Announcement>();
            for (blackboard.data.announcement.Announcement a : announcements) {
                toReturn.add(new BbAnnouncement(a));
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbAnnouncementService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<Announcement>();
        }
    }

    public List<Announcement> getAnnouncementsForCourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Announcement getAnnouncementForId(String id) {
        try {
            AnnouncementDbLoader announcementDbLoader = AnnouncementDbLoader.Default.getInstance();
            return new BbAnnouncement(announcementDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.announcement.Announcement.class)));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbAnnouncementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
