/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion;

import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;
import java.util.List;

/**
 *
 * @author pfgray
 */
public interface DiscussionService {

    /**
     * Returns a list of DiscussionBoards for the specified Course and User
     *
     * @param course the course to grab DiscussionBoards for
     * @param user the user to get DiscussionBoards for
     * @return the list of DiscussionBoards for this course and user
     */
    public List<DiscussionBoard> getDiscussionBoardsForCourseAndUser(Course course, User user);

    /**
     * Returns a list of DiscussionThreads for a DiscussionBoard
     *
     * @param thread the thread to grab DiscussionBoards for
     * @return the list of DiscussionBoards for this thread
     */
    public List<DiscussionThread> getDiscussionThreadsForBoard(DiscussionBoard board);

    /**
     * Returns a list of DiscussionThreads for a DiscussionBoard
     *
     * @param thread the thread to grab DiscussionBoards for
     * @return the list of DiscussionBoards for this thread
     */
    public List<DiscussionPost> getDiscussionPostsForThread(DiscussionThread thread);
    
    /**
     * Returns a DiscussionBoard for the id
     *
     * @param id the of the resource
     * @return the DiscussionBoard 
     */
    public DiscussionBoard getDiscussionBoardForId(String id);

    /**
     * Returns a DiscussionThread for the id
     *
     * @param id the of the resource
     * @return the DiscussionThread 
     */
    public DiscussionThread getDiscussionThreadForId(String id);

    /**
     * Returns a DiscussionPost for the id
     *
     * @param id the of the resource
     * @return the DiscussionPost 
     */
    public DiscussionPost getDiscussionPostForId(String id);
    
    
    /**
     * Inserts a DiscussionThread for the DiscussionBoard
     *
     * @param id the of the resource
     * @return the DiscussionBoard 
     */
    public DiscussionThread insertDiscussionThreadForDiscussionBoardAndUser(DiscussionBoard discussionBoard, DiscussionThread discussionThread, User user);
    
    /**
     * Inserts a DiscussionThread for the DiscussionBoard
     *
     * @param id the of the resource
     * @return the DiscussionBoard 
     */
    public DiscussionPost insertDiscussionPostForDiscussionThreadAndUser(DiscussionThread discussionThread, DiscussionPost discussionPost, User user);
    
/*
    public List<DiscussionPost> getPostsForDiscussionBoard(DiscussionBoard discussionBoard);

    public List<DiscussionBoard> getDiscussionBoardsForCourse(Course course);
    * */
}
