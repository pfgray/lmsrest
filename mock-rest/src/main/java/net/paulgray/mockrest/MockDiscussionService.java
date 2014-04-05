/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionPost;
import net.paulgray.lmsrest.discussion.DiscussionService;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
public class MockDiscussionService implements DiscussionService {

    public List<DiscussionBoard> getDiscussionBoardsForCourseAndUser(Course course, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DiscussionThread> getDiscussionThreadsForBoard(DiscussionBoard board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DiscussionPost> getDiscussionPostsForThread(DiscussionThread thread) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DiscussionBoard getDiscussionBoardForId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DiscussionThread getDiscussionThreadForId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DiscussionPost getDiscussionPostForId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DiscussionThread insertDiscussionThreadForDiscussionBoardAndUser(DiscussionBoard discussionBoard, DiscussionThread discussionThread, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DiscussionPost insertDiscussionPostForDiscussionThreadAndUser(DiscussionThread discussionThread, DiscussionPost discussionPost, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DiscussionThread> getDiscussionThreadsForBoard(DiscussionBoard board, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DiscussionPost> getDiscussionPostsForThread(DiscussionThread thread, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
