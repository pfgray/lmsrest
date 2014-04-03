/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.discussion;

import blackboard.base.BbList;
import blackboard.data.discussionboard.Conference;
import blackboard.data.discussionboard.Forum;
import blackboard.data.discussionboard.Message;
import blackboard.data.discussionboard.MessageCounts;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.discussionboard.ConferenceDbLoader;
import blackboard.persist.discussionboard.ForumDbLoader;
import blackboard.persist.discussionboard.MessageDbLoader;
import blackboard.persist.discussionboard.UserMsgStateDbPersister;
import java.util.ArrayList;
import java.util.HashMap;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionPost;
import net.paulgray.lmsrest.discussion.DiscussionService;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.user.BbUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author pfgray
 */
public class BbDiscussionService implements DiscussionService {

    @Autowired
    BbUserService bbUserService;

    public List<DiscussionBoard> getDiscussionBoardsForCourseAndUser(Course course, User user) {
        try {
            ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
            ConferenceDbLoader conferenceDbLoader = ConferenceDbLoader.Default.getInstance();
            List<Conference> conferences = conferenceDbLoader.loadAllByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
            List<DiscussionBoard> discussionBoards = new LinkedList<DiscussionBoard>();
            for (Conference conference : conferences) {
                List<Forum> forums = forumDbLoader.loadByConferenceId(conference.getId());
                for (Forum forum : forums) {
                    discussionBoards.add(getBbDiscussionBoardForForum(course, forum, BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class)));
                }
            }
            return discussionBoards;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<DiscussionBoard>();
        }
    }

    public List<DiscussionThread> getDiscussionThreadsForBoard(DiscussionBoard board, User user) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();

            Id forumId = BlackboardUtilities.getIdFromPk(board.getId(), blackboard.data.discussionboard.Forum.class);
            Id userId = BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class);
            //List<Message> messages = messageDbLoader.loadMessagesByForumIdWithStatus(forumId, userId, false, false, false, false);
            List<Message> messages = messageDbLoader.loadTopThreadsWithStatusAndCountsByForumId(forumId, userId, false, false, false);

            List<DiscussionThread> discussionThreads = new LinkedList<DiscussionThread>();
            LocalCachedBbUserService cachedUsers = new LocalCachedBbUserService();
            for (Message message : messages) {
                String msgUserId = message.getUserId() != null ? message.getUserId().getExternalString() : null;
                discussionThreads.add(new BbDiscussionThread(message, cachedUsers.getUserForId(msgUserId)));
            }
            return discussionThreads;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<DiscussionThread>();
        }
    }

    public List<DiscussionPost> getDiscussionPostsForThread(DiscussionThread discussionThread, User user) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            //Message thread = messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(discussionThread.getId(), blackboard.data.discussionboard.Message.class), null, false, true);
            Id threadId = BlackboardUtilities.getIdFromPk(discussionThread.getId(), blackboard.data.discussionboard.Message.class);
            Id userId = BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class);
            List<Message> replies = messageDbLoader.loadMessageThreadWithStatus(threadId, userId, false, true, true);
            List<DiscussionPost> discussionPosts = new LinkedList<DiscussionPost>();

            discussionPosts.add(new BbDiscussionPost(buildMessageTreeFromList(getTopLevelMessage(replies), replies)));
            
            
            List<Id> messageIds = new ArrayList<Id>(replies.size());
            UserMsgStateDbPersister userMsgStateDbPersister = UserMsgStateDbPersister.Default.getInstance();
            for (Message msg : replies) {
                messageIds.add(msg.getId());
            }
            userMsgStateDbPersister.incrementMessagesReadCount(messageIds, userId);

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
            return getBbDiscussionBoardForForum(null, forum, null);
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DiscussionThread getDiscussionThreadForId(String id) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            return new BbDiscussionThread(messageDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.discussionboard.Message.class), null), null);
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

    private void setDiscussionThreadReadStatus(Message thread, blackboard.data.user.User user, Boolean read) {
        try {
            UserMsgStateDbPersister userMsgStateDbPersister = UserMsgStateDbPersister.Default.getInstance();
            userMsgStateDbPersister.updateThreadReadStatusByTopMsgId(read, thread.getId(), user.getId());
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDiscussionPostReadStatus(Message message, blackboard.data.user.User user, Boolean read) {
        try {
            UserMsgStateDbPersister userMsgStateDbPersister = UserMsgStateDbPersister.Default.getInstance();
            userMsgStateDbPersister.updateReadStatusByMsgId(read, message.getId(), user.getId());
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Message buildMessageTreeFromList(Message parent, List<Message> messages) {
        BbList<Message> children = new BbList<Message>();
        for (Message post : messages) {
            if (post.getParentId().equals(parent.getId())) {
                children.add(buildMessageTreeFromList(post, messages));
            }
        }
        parent.setResponses(children);
        return parent;
    }

    private Message getTopLevelMessage(List<Message> messages) {
        for (Message msg : messages) {
            if (msg.isTopLevelMessage()) {
                return msg;
            }
        }
        return null;
    }

    private static BbDiscussionBoard getBbDiscussionBoardForForum(Course course, Forum forum, Id contextUserId) {
        try {
            MessageDbLoader messageDbLoader = MessageDbLoader.Default.getInstance();
            MessageCounts mc = messageDbLoader.loadMessageCountsByForumId(forum.getId(), contextUserId);
            return new BbDiscussionBoard(forum, course, mc);
        } catch (PersistenceException ex) {
            Logger.getLogger(BbDiscussionBoard.class.getName()).log(Level.WARNING, "Could not get Message count for db forum: " + forum.getId().getExternalString(), ex);
            return new BbDiscussionBoard(forum, course, null);
        }
    }

    private class LocalCachedBbUserService {

        public Map<String, User> users = new HashMap<String, User>();

        public User getUserForId(String userId) {
            if (userId != null) {
                if (!users.containsKey(userId)) {
                    users.put(userId, bbUserService.getUserForId(userId));
                }
                return users.get(userId);
            } else {
                return null;
            }
        }
    }

    /*
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
     msg.setParentId(thread.getId());
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
     if (replies == null) {
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
     */
}
