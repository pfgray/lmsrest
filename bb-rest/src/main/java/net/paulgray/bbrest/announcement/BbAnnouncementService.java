/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.announcement;

import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.announcement.AnnouncementDbLoader;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.lmsrest.announcement.Announcement;
import net.paulgray.lmsrest.announcement.AnnouncementService;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.course.BbCourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    BbCourseService bbCourseService;

    public List<Announcement> getAnnouncementsForUser(User user) {
        try {
            AnnouncementDbLoader announcementDbLoader = AnnouncementDbLoader.Default.getInstance();
            List<blackboard.data.announcement.Announcement> announcements = announcementDbLoader.loadAvailableByUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            List<Announcement> toReturn = new LinkedList<Announcement>();
            LocalCachedBbCourseService courseService = new LocalCachedBbCourseService();
            for (blackboard.data.announcement.Announcement a : announcements) {
                toReturn.add(new BbAnnouncement(a, courseService.getCourseForId(a.getId().getExternalString())));
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
                toReturn.add(new BbAnnouncement(a, course));
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
            blackboard.data.announcement.Announcement a = announcementDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.announcement.Announcement.class));
            return new BbAnnouncement(a, bbCourseService.getCourseForId(id));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbAnnouncementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private class LocalCachedBbCourseService {
        
        public Map<String, Course> courses;
        
        public Course getCourseForId(String courseId){
            if(!courses.containsKey(courseId)){
                courses.put(courseId, bbCourseService.getCourseForId(courseId));
            }
            return courses.get(courseId);
        }
        
    }

}
