/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.discussion;

import blackboard.data.discussionboard.Conference;
import blackboard.data.discussionboard.Forum;
import net.paulgray.bbrest.course.BbCourse;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.discussion.DiscussionBoard;

/**
 *
 * @author pfgray
 */
public class BbDiscussionBoard extends DiscussionBoard {

    BbDiscussionBoard(Forum forum, String courseId) {
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
        this.available = forum.getIsAvailable();
        if(forum.getEndDate() != null){
            this.endDate = forum.getEndDate().getTime();
        }
        this.courseId = courseId;
    }
}
