/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.discussion;

import blackboard.base.FormattedText;
import blackboard.data.discussionboard.Message;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.lmsrest.discussion.DiscussionPost;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author pfgray
 */
public class BbDiscussionPost extends DiscussionPost {

    public String parentId;
    
    public BbDiscussionPost(Message message) {
        
        
        
        this.replies = new LinkedList<DiscussionPost>();
        this.id = message.getId().getExternalString();
        if (message.getBody() != null) {
            this.body = message.getBody().getText();
        }
        if (message.getEditDate() != null) {
            this.lastEdited = message.getEditDate().getTime();
        }
        if (message.getPostDate() != null) {
            this.postedDate = message.getPostDate().getTime();
        }
        if (message.getUserId() != null) {
            this.userId = message.getUserId().getExternalString();
        }
        if (message.getParentId() != null) {
            this.parentId = message.getParentId().getExternalString();
        }
        if(message.getResponses() != null){
            System.out.println("****responses was not null, length:" + message.getResponses().size());
            for (Message response : message.getResponses()) {
                this.replies.add(new BbDiscussionPost(response));
            }
        } else {
            System.out.println("****responses was null");
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
