/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.course;

import blackboard.data.discussionboard.datamanager.impl.DiscussionBoardUnreadCountsManagerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author pfgray
 */
public class BbCourse extends Course {
    
    public Integer unread_discussion_count;
    
    public BbCourse(blackboard.data.course.Course course) {
        super(course.getId().getExternalString(), course.getCourseId(), course.getTitle(), course.getDescription());
    }
    
    public BbCourse(blackboard.data.course.Course course, blackboard.persist.Id contextUser) {
        super(course.getId().getExternalString(), course.getCourseId(), course.getTitle(), course.getDescription());
        try {
            this.unread_discussion_count = DiscussionBoardUnreadCountsManagerImpl.getDBTotalUnreadCountsForUserAndCourse(contextUser, course.getId(), "EM");
        } catch (ServletException ex) {
            Logger.getLogger(BbCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
