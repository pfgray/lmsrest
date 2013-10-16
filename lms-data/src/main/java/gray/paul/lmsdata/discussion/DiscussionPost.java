/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsdata.discussion;

import java.util.Date;
import java.util.List;

/**
 *
 * @author pfgray
 */
public class DiscussionPost {
    
    protected String id;
    protected String body;
    protected Date lastEdited;
    protected Date postedDate;
    protected String user;
    protected List<DiscussionPost> replies;
    
    protected String userId;
    protected String threadId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public List<DiscussionPost> getReplies() {
        return replies;
    }

    public void setReplies(List<DiscussionPost> replies) {
        this.replies = replies;
    }
    
}
