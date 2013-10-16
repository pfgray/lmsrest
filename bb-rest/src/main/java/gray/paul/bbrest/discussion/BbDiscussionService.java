/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.discussion;

import blackboard.base.BbList;
import blackboard.data.ValidationException;
import blackboard.data.discussionboard.Conference;
import blackboard.data.discussionboard.Forum;
import blackboard.data.discussionboard.Message;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.discussionboard.ConferenceDbLoader;
import blackboard.persist.discussionboard.ForumDbLoader;
import blackboard.persist.discussionboard.MessageDbLoader;
import blackboard.persist.discussionboard.MessageDbPersister;
import gray.paul.bbrest.BlackboardUtilities;
import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.discussion.DiscussionBoard;
import gray.paul.lmsdata.discussion.DiscussionPost;
import gray.paul.lmsdata.discussion.DiscussionService;
import gray.paul.lmsdata.discussion.DiscussionThread;
import gray.paul.lmsdata.user.User;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfgray
 */
public class BbDiscussionService implements DiscussionService {

    public List<DiscussionBoard> getDiscussionBoardsForCourseAndUser(Course course, User user) {
        try {
            ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
//            List<Forum> forums = forumDbLoader.loadEnabledByCourseIdAndUserId(
//                    BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class),
//                    BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));

            ConferenceDbLoader conferenceDbLoader = ConferenceDbLoader.Default.getInstance();
            List<Conference> conferences = conferenceDbLoader.loadAllByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));

            List<DiscussionBoard> discussionBoards = new LinkedList<DiscussionBoard>();
            for (Conference conference : conferences) {
                List<Forum> forums = forumDbLoader.loadByConferenceId(conference.getId());
                for (Forum forum : forums) {
                    discussionBoards.add(new BbDiscussionBoard(forum, conference.getCourseId().getExternalString()));
                }
            }
            return discussionBoards;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<DiscussionBoard>();
        }
//        try {
//            ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
//            ConferenceDbLoader conferenceDbLoader = ConferenceDbLoader.Default.getInstance();
//            List<Conference> conferences = conferenceDbLoader.loadAllByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
//            List<DiscussionBoard> discussionBoards = new LinkedList<DiscussionBoard>();
//            for (Conference conference : conferences) {
//                discussionBoards.add(new BbDiscussionBoard(conference));
//            }
//            return discussionBoards;
//        } catch (PersistenceException ex) {
//            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
//            return new LinkedList<DiscussionBoard>();
//        }
    }

    public List<DiscussionThread> getDiscussionThreadsForBoard(DiscussionBoard board) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            List<Message> messages = messageDbLoader.loadByForumId(BlackboardUtilities.getIdFromPk(board.getId(), blackboard.data.discussionboard.Forum.class));
            List<DiscussionThread> discussionThreads = new LinkedList<DiscussionThread>();
            for (Message message : messages) {
                discussionThreads.add(new BbDiscussionThread(message));
            }
            return discussionThreads;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<DiscussionThread>();
        }
    }

    public List<DiscussionPost> getDiscussionPostsForThread(DiscussionThread discussionThread) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            Message thread = messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(discussionThread.getId(), blackboard.data.discussionboard.Message.class));
            List<DiscussionPost> discussionPosts = new LinkedList<DiscussionPost>();
            if (thread.getResponses() != null) {
                for (Message post : thread.getResponses()) {
                    discussionPosts.add(new BbDiscussionPost(post));
                }
            }
            return discussionPosts;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<DiscussionPost>();
        }
    }

    public DiscussionBoard getDiscussionBoardForId(String id) {
        try {
            ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
            ConferenceDbLoader conferenceDbLoader = ConferenceDbLoader.Default.getInstance();
            Forum forum = forumDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.discussionboard.Forum.class));
            Id courseId = conferenceDbLoader.loadById(forum.getConferenceId()).getCourseId();
            if (courseId != null) {
                return new BbDiscussionBoard(forum, courseId.getExternalString());
            } else {
                return new BbDiscussionBoard(forum, null);
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DiscussionThread getDiscussionThreadForId(String id) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            return new BbDiscussionThread(messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.discussionboard.Message.class)));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DiscussionPost getDiscussionPostForId(String id) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            return new BbDiscussionPost(messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.discussionboard.Message.class)));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DiscussionThread insertDiscussionThreadForDiscussionBoardAndUser(DiscussionBoard discussionBoard, DiscussionThread discussionThread, User user) {
        try {
            ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
            Forum forum = forumDbLoader.loadById(BlackboardUtilities.getIdFromPk(discussionBoard.getId(), blackboard.data.discussionboard.Forum.class));
            MessageDbPersister messageDbPersister = MessageDbPersister.Default.getInstance();
            Message msg = BbDiscussionThread.toMessage(discussionThread, forum);
            msg.setLifecycle(Message.MessageLifecycle.DEFAULT);
            msg.setUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            messageDbPersister.persist(msg);
            BbDiscussionThread thread = new BbDiscussionThread(msg);
            return thread;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ValidationException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DiscussionPost insertDiscussionPostForDiscussionThreadAndUser(DiscussionThread discussionThread, DiscussionPost discussionPost, User user) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            Message thread = messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(discussionThread.getId(), blackboard.data.discussionboard.Message.class));
            MessageDbPersister messageDbPersister = MessageDbPersister.Default.getInstance();
            Message msg = BbDiscussionPost.toMessage(discussionPost, user.getId());
            msg.setThreadId(thread.getId());
            msg.setForumId(BlackboardUtilities.getIdFromPk(discussionThread.getForumId(), blackboard.data.discussionboard.Forum.class));
            msg.setUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            msg.setPostDate(Calendar.getInstance());
            messageDbPersister.persist(msg);
            BbDiscussionPost post = new BbDiscussionPost(msg);
            return post;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ValidationException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DiscussionPost insertReplyForDiscussionPostForUser(DiscussionPost discussionPost, DiscussionPost discussionReply, User user) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            Message post = messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(discussionPost.getId(), blackboard.data.discussionboard.Message.class));
            MessageDbPersister messageDbPersister = MessageDbPersister.Default.getInstance();
            Message reply = BbDiscussionPost.toMessage(discussionReply, user.getId());
            
            BbList<Message> replies = post.getResponses();
            if(replies == null){
                replies = new BbList<Message>();
            }
            reply.setUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            reply.setPostDate(Calendar.getInstance());
            replies.add(reply);
            post.setResponses(replies);
            
            messageDbPersister.persist(post);
            return new BbDiscussionPost(reply);
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ValidationException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /*
     public List<DiscussionThread> getDiscussionBoardsForThread(DiscussionBoard board) {
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

     public List<DiscussionPost> getDiscussionPostsForThread(DiscussionThread thread) {
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
    

     public List<DiscussionPost> getPostsForDiscussionBoard(DiscussionBoard discussionBoard) {
     List<DiscussionPost> toReturn = new LinkedList<DiscussionPost>();
     try {
            
     MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
     List<Message> messages = messageDbLoader.loadAllByForumId(BlackboardUtilities.getIdFromPk(discussionBoard.getId(), Forum.class));
     for(Message message : messages){
     toReturn.add(new BbDiscussionBoardPost(message));
     }
     } catch (PersistenceException ex) {
     Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return toReturn;
     }
    
     @Override
     public List<DiscussionBoard> getDiscussionBoardsForCourse(Course course) {
     //  \/ leaving this in here to see if we can get the conferences without the "real" id (from the Bb database)
     //blackboard.data.course.Course bbCourse = bbCourseService.getCourseById(BbUtil.getIdFromPk1(course.getId(), blackboard.data.course.Course.class));

     List<Forum> forums = new LinkedList<Forum>();
     try {
     ConferenceDbLoader conferenceDbLoader = ConferenceDbLoader.Default.getInstance();
     List<Conference> conferences = conferenceDbLoader.loadAllByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
            
     ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
     for(Conference conference : conferences){
     forums.addAll(forumDbLoader.loadByConferenceId(conference.getId()));
     }
     } catch (PersistenceException ex) {
     Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
     }
     List<DiscussionBoard> toReturn = new LinkedList<DiscussionBoard>();
     for(Forum forum : forums){
     toReturn.add(new BbDiscussionBoard(forum));
     }
     return toReturn;
     }

     @Override
     public List<DiscussionBoard> getDiscussionBoardsForCourseAndUser(Course course, User user) {
     throw new UnsupportedOperationException("not supported yet!");
     //        blackboard.data.course.Course bbCourse = bbCourseService.getCourseById(BbUtil.getIdFromPk1(course.getId(), blackboard.data.course.Course.class));
     //        blackboard.data.user.User bbUser = bbUserService.getUserById(BbUtil.getIdFromPk1(user.getId(), blackboard.data.user.User.class));
     //
     //        List<Forum> forums = getForumsForCourseAndUser(bbCourse, bbUser);
     //        List<DiscussionBoard> toReturn = new LinkedList<DiscussionBoard>();
     //        for(Forum forum : forums){
     //            toReturn.add(new BbDiscussionBoard(forum));
     //        }
     //        return toReturn;
     }

     private List<Forum> getForumsForCourseAndUser(blackboard.data.course.Course bbCourse, blackboard.data.user.User bbUser) {
     throw new UnsupportedOperationException("not supported yet.");
     //        try {
     //            ForumDbLoader forumDbLoader = (ForumDbLoader) blackboardDbLoaderFactory.getDbLoader(ForumDbLoader.TYPE);
     //            return forumDbLoader.loadEnabledByCourseIdAndUserId(bbCourse.getId(), bbUser.getId());
     //        } catch (PersistenceException ex) {
     //            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
     //            return new LinkedList<Forum>();
     //        }
     }
     */
}
