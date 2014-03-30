/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.announcement;

import net.paulgray.lmsrest.announcement.Announcement;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author pfgray
 */
public class BbAnnouncement extends Announcement {


    public BbAnnouncement(blackboard.data.announcement.Announcement a, Course course) {
        this.id = a.getId().getExternalString();
        this.text = a.getBody().getText();
        this.title = a.getTitle();
        this.updated = a.getModifiedDate() != null ? a.getModifiedDate().getTime() : null;
        this.course = course;
    }

}
