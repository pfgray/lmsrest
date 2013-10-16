/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.announcement;

import gray.paul.lmsdata.announcement.Announcement;



/**
 *
 * @author pfgray
 */
public class BbAnnouncement extends Announcement{
    
    public BbAnnouncement(blackboard.data.announcement.Announcement a){
        this.id = a.getId().getExternalString();
        this.title = a.getTitle();
        this.text = a.getBody().getText();
    }
    
}
