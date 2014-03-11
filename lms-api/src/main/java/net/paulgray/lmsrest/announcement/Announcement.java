/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.announcement;

import java.util.Date;

/**
 *
 * @author pfgray
 */
public class Announcement {
    
    protected String id;
    protected String text;
    protected String title;
    protected Date created;

    public Announcement() {
    }

    public Announcement(String id, String text, String title, Date created) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.created = created;
    }
    
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
