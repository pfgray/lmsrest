/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.discussion;

import blackboard.data.discussionboard.Message;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author pfgray
 */
public class BbDiscussionThread extends DiscussionThread {
    
    public Integer unread_messages;
    public Integer total_messages;
    public Boolean read;
    public String subject;

    BbDiscussionThread(Message message, User user) {
        if(message.getMessageStatus() != null){
            this.unread_messages = message.getMessageStatus().getUnreadCountOfSelfAndChildren();
            this.total_messages = message.getMessageStatus().getTotalCount();
            this.read = message.getMessageStatus().getIsRead();
        }
        this.user = user;
        if (message.getId() != null) {
            this.id = message.getId().getExternalString();
        }
        if (message.getBody() != null) {
            this.text = message.getBody().getText();
        }
        this.subject = message.getSubject();
        if(message.getModifiedDate() != null){
            this.postedDate = message.getModifiedDate().getTime();
        }
    }
    /*
    public static Message toMessage(DiscussionThread thread, Forum forum){
        Message message = new Message();
        message.setBody(new FormattedText(thread.getText(), FormattedText.Type.PLAIN_TEXT));
        message.setSubject(thread.getSubject());
        message.setForumId(forum.getId());
        return message;
    }
    */
}
