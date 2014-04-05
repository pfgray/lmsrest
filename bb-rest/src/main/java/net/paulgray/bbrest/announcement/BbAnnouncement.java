/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.announcement;

import net.paulgray.lmsrest.announcement.Announcement;
import net.paulgray.lmsrest.course.Course;
import org.jsoup.Jsoup;

/**
 *
 * @author pfgray
 */
public class BbAnnouncement extends Announcement {

    public BbAnnouncement(blackboard.data.announcement.Announcement a, Course course) {
        this.id = a.getId().getExternalString();
        if(a.getBody() != null &&  a.getBody().getText() != null){
            this.text = Jsoup.parse(a.getBody().getText()).text();
        }
        if(a.getTitle() != null){
            this.title = Jsoup.parse(a.getTitle()).text();
        }
        this.updated = a.getModifiedDate() != null ? a.getModifiedDate().getTime() : null;
        this.course = course;
    }

}
