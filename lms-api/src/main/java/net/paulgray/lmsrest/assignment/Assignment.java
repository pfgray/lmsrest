/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.assignment;

import java.util.Date;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author paul
 */
public class Assignment {
    
    protected String id;
    protected String title;
    protected String description;
    protected Course course;
    
    protected Date due;
    protected Date created;
    protected Date updated;

    public Assignment() {
    }

    public Assignment(String id, String title, String description, Course course, Date due, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.course = course;
        this.due = due;
        this.created = created;
        this.updated = updated;
    }

    public Assignment(String id, String title, String description, Date due, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.due = due;
        this.created = created;
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
}
