/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.discussion;

import blackboard.data.discussionboard.Message;
import blackboard.data.discussionboard.MessageCounts;
import net.paulgray.lmsrest.discussion.DiscussionThread;

/**
 *
 * @author pfgray
 */
public class BbDiscussionThread extends DiscussionThread {
    
    public Integer unread_messages;
    public Integer total_messages;

    BbDiscussionThread(Message message, MessageCounts counts) {
        if(counts != null){
            this.unread_messages = counts.getUnreadCount();
            this.total_messages = counts.getTotalCount();
        }
        
        if (message.getId() != null) {
            this.id = message.getId().getExternalString();
        }
        if (message.getBody() != null) {
            this.text = message.getBody().getText();
        }
        this.subject = message.getSubject();
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
