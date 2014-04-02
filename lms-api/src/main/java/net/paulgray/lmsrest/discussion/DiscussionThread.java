/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion;

import java.util.Date;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author pfgray
 */
public class DiscussionThread {
    
    protected String id;
    protected String text;
    protected Date lastEdited;
    protected Date postedDate;
    
    protected User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
