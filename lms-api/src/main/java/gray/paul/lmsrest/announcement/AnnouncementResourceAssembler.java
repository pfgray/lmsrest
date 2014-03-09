/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.announcement;

import gray.paul.lmsdata.announcement.Announcement;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.stereotype.Component;

/**
 *
 * @author pfgray
 */
@Component
public class AnnouncementResourceAssembler extends ResourceAssemblerSupport<Announcement, AnnouncementResource>{

    public AnnouncementResourceAssembler() {
        super(AnnouncementController.class, AnnouncementResource.class);
    }
    
    @Override
    public AnnouncementResource toResource(Announcement announcement) {
        AnnouncementResource announcementResource = new AnnouncementResource();
        announcementResource.add(linkTo(AnnouncementController.class).slash(announcement.getId()).withRel("self"));
        populateResource(announcementResource, announcement);
        addLinks(announcementResource, announcement);
        return announcementResource;
    }

    private void populateResource(AnnouncementResource announcementResource, Announcement announcement) {
        announcementResource.id = announcement.getId();
        announcementResource.text = announcement.getText();
        announcementResource.title = announcement.getTitle();
        announcementResource.created = announcement.getCreated();
    }

    private void addLinks(AnnouncementResource announcementResource, Announcement announcement) {
        //announcementResource.add(linkTo(AnnouncementController.class).slash(announcement.getId()).withRel("course"));
    }
}
