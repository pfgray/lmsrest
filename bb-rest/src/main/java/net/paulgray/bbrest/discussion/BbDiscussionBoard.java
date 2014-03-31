/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.discussion;

import blackboard.data.discussionboard.Forum;
import blackboard.data.discussionboard.MessageCounts;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.discussion.DiscussionBoard;

/**
 *
 * @author pfgray
 */
public class BbDiscussionBoard extends DiscussionBoard {
    
    public String lineitem_id;
    public Integer unread_messages;
    public Integer total_messages;

    BbDiscussionBoard(Forum forum, Course course, MessageCounts mc) {
        if (forum.getId() != null) {
            this.id = forum.getId().getExternalString();
        }
        this.title = forum.getTitle();
        if (forum.getDescription() != null) {
            this.description = forum.getDescription().getText();
        }
        if(forum.getModifiedDate() != null){
            this.lastEdited = forum.getModifiedDate().getTime();
        }
        if(forum.getProperties() != null){
            this.lineitem_id = forum.getProperties().getForumGradeLineitemPk();
        }
        this.available = forum.getIsAvailable();
        if(forum.getEndDate() != null){
            this.endDate = forum.getEndDate().getTime();
        }
        this.course = course;
        if(mc != null){
            this.unread_messages = mc.getUnreadCount();
            this.total_messages = mc.getTotalCount();
        }
    }
}
