/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.post;

import gray.paul.lmsdata.discussion.DiscussionPost;
import gray.paul.lmsrest.discussion.board.DiscussionBoardController;
import gray.paul.lmsrest.user.UserController;
import java.util.LinkedList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author pfgray
 */
@Component
public class DiscussionPostResourceAssembler extends ResourceAssemblerSupport<DiscussionPost, DiscussionPostResource> {

    public DiscussionPostResourceAssembler() {
        super(DiscussionBoardController.class, DiscussionPostResource.class);
    }

    @Override
    public DiscussionPostResource toResource(DiscussionPost post) {
        DiscussionPostResource discussionPostResource = new DiscussionPostResource();
        discussionPostResource.body = post.getBody();
        discussionPostResource.user = post.getUser();
        discussionPostResource.last_edited = post.getLastEdited();
        discussionPostResource.posted_date = post.getPostedDate();
        
        discussionPostResource.replies = new LinkedList<DiscussionPostResource>();
        for(DiscussionPost reply : post.getReplies()){
            discussionPostResource.replies.add(this.toResource(reply));
        }

        addLinks(discussionPostResource, post);
        return discussionPostResource;
    }

    private void addLinks(DiscussionPostResource discussionBoardPostResource, DiscussionPost t) {
        discussionBoardPostResource.add(linkTo(DiscussionPostController.class).slash(t.getId()).withRel("self_fake"));
        discussionBoardPostResource.add(linkTo(UserController.class).slash(t.getUserId()).withRel("user"));
        discussionBoardPostResource.add(linkTo(DiscussionPostController.class).slash(t.getThreadId()).withRel("thread"));        

        //discussionBoardPostResource.add(linkTo(DiscussionBoardController.class).slash(discussionBoard.id).slash("posts").slash(t.id).withRel("self"));)
        //discussionBoardPostResource.add(linkTo(DiscussionBoardController.class).slash(discussionBoard.id).slash("posts").withRel("posts"));
    }
}
