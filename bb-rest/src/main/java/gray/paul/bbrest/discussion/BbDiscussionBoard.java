/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.discussion;

import blackboard.data.discussionboard.Conference;
import blackboard.data.discussionboard.Forum;
import gray.paul.bbrest.course.BbCourse;
import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.discussion.DiscussionBoard;

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
