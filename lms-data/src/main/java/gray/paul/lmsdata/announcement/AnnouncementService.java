/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsdata.announcement;

import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.user.User;
import java.util.List;

/**
 *
 * @author pfgray
 */
public interface AnnouncementService {
    
    
    public List<Announcement> getAnnouncementsForUser(User user);
    
    public List<Announcement> getAnnouncementsForCourse(Course course);
    
    public List<Announcement> getAnnouncementsForCourseAndUser(Course course, User user);
    
    public Announcement getAnnouncementForId(String Id);
    
}
