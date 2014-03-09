/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.thread;

import gray.paul.lmsdata.discussion.DiscussionThread;
import gray.paul.lmsrest.discussion.board.DiscussionBoardController;
import gray.paul.lmsrest.discussion.post.DiscussionPostController;
import gray.paul.lmsrest.discussion.post.DiscussionPostResource;
import gray.paul.lmsrest.user.UserController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 * @author pfgray
 */
@Component
public class DiscussionThreadResourceAssembler extends ResourceAssemblerSupport<DiscussionThread, DiscussionThreadResource> {

    public DiscussionThreadResourceAssembler() {
        super(DiscussionThreadController.class, DiscussionThreadResource.class);
    }

    @Override
    public DiscussionThreadResource toResource(DiscussionThread entity) {
        DiscussionThreadResource discussionThreadResource = new DiscussionThreadResource();
        if (entity != null) {
            discussionThreadResource.subject = entity.getSubject();
            discussionThreadResource.text = entity.getText();
            discussionThreadResource.last_edited = entity.getLastEdited();
            discussionThreadResource.locked = entity.getLocked();
            discussionThreadResource.posted_date = entity.getPostedDate();
            discussionThreadResource.created_by = entity.getCreatedBy();
            addLinks(entity, discussionThreadResource);
        }
        return discussionThreadResource;
    }

    public DiscussionThread toDiscussionThread(DiscussionThreadResource discussionThreadResource) {
        DiscussionThread dt = new DiscussionThread();
        dt.setText(discussionThreadResource.text);
        dt.setSubject(discussionThreadResource.subject);
        dt.setLocked(discussionThreadResource.locked);
        return dt;
    }

    private void addLinks(DiscussionThread discussionThread, DiscussionThreadResource discussionThreadResource) {
        //links:
        //self: /discussion_thread/{id}
        discussionThreadResource.add(linkTo(DiscussionPostController.class).slash(discussionThread.getId()).withRel("self"));
        
        //user: /users/{id}
        discussionThreadResource.add(linkTo(UserController.class).slash(discussionThread.getUserId()).withRel("user"));
        
        //posts: /discussion_thread/{id}/discussion_posts
        discussionThreadResource.add(linkTo(DiscussionPostController.class).slash(discussionThread.getId()).slash(DiscussionPostController.PATH).withRel(DiscussionPostController.PATH));
    }
}
