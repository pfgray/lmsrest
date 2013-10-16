/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.discussion;

import blackboard.base.FormattedText;
import blackboard.data.discussionboard.Message;
import gray.paul.bbrest.BlackboardUtilities;
import gray.paul.lmsdata.discussion.DiscussionPost;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author pfgray
 */
public class BbDiscussionPost extends DiscussionPost {

    public BbDiscussionPost(Message message) {
        this.replies = new LinkedList<DiscussionPost>();
        this.id = message.getId().getExternalString();
        if (message.getBody() != null) {
            this.body = message.getBody().getText();
        }
        this.user = message.getUserGivenName() + " " + message.getUserFamilyName();
        if (message.getEditDate() != null) {
            this.lastEdited = message.getEditDate().getTime();
        }
        if (message.getPostDate() != null) {
            this.postedDate = message.getPostDate().getTime();
        }
        if (message.getUserId() != null) {
            this.userId = message.getUserId().getExternalString();
        }
        if (message.getThreadId() != null) {
            this.threadId = message.getThreadId().getExternalString();
        }
        for (Message response : message.getResponses()) {
            this.replies.add(new BbDiscussionPost(response));
        }
    }

    public static Message toMessage(DiscussionPost post, String userId) {
        Message message = new Message();
        message.setBody(new FormattedText(post.getBody(), FormattedText.Type.PLAIN_TEXT));
        message.setUserId(BlackboardUtilities.getIdFromPk(userId, blackboard.data.user.User.class));
        
        message.setLifecycle(Message.MessageLifecycle.DEFAULT);
        message.setEditDate(Calendar.getInstance());
        
        return message;
    }
}
