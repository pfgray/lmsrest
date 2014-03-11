/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.announcement;

import net.paulgray.lmsrest.announcement.Announcement;



/**
 *
 * @author pfgray
 */
public class BbAnnouncement extends Announcement{
    
    public BbAnnouncement(blackboard.data.announcement.Announcement a){
        super(a.getId().getExternalString(), a.getBody().getText(), a.getTitle(), null);
    }
    
}
