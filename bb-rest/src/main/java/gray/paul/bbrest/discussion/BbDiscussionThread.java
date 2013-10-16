/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.discussion;

import blackboard.base.FormattedText;
import blackboard.data.discussionboard.Forum;
import blackboard.data.discussionboard.Message;
import gray.paul.lmsdata.discussion.DiscussionThread;

/**
 *
 * @author pfgray
 */
public class BbDiscussionThread extends DiscussionThread {

    BbDiscussionThread(Message message) {
        
        message.getComparableName();
        message.getEditDate();
        message.getForumId();
        message.getIsThreadLocked();
        message.getPostDate();
        message.getUserFamilyName();
        message.getUserGivenName();
        
        
        if (message.getId() != null) {
            this.id = message.getId().getExternalString();
        }
        if (message.getBody() != null) {
            this.text = message.getBody().getText();
        }
        this.subject = message.getSubject();
    }
    
    public static Message toMessage(DiscussionThread thread, Forum forum){
        Message message = new Message();
        message.setBody(new FormattedText(thread.getText(), FormattedText.Type.PLAIN_TEXT));
        message.setSubject(thread.getSubject());
        message.setForumId(forum.getId());
        return message;
    }
}
