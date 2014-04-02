/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion.post;

import net.paulgray.lmsrest.discussion.DiscussionPost;
import net.paulgray.lmsrest.discussion.DiscussionService;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.user.User;
import static net.paulgray.lmsrest.discussion.post.DiscussionPostController.PATH;
import net.paulgray.lmsrest.discussion.thread.DiscussionThreadController;
import net.paulgray.lmsrest.web.ContextUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pfgray
 */
@Controller
@RequestMapping(value = DiscussionThreadController.PATH )
public class DiscussionPostController {

    public static final String PATH = "discussion_posts";
    @Autowired
    DiscussionService discussionService;

    @RequestMapping(method = RequestMethod.GET, value = "/{discussionThread}/" + PATH)
    public ResponseEntity getPostsForDiscussionThread(@ContextUser User user, @PathVariable DiscussionThread discussionThread) {
        List<DiscussionPost> posts = discussionService.getDiscussionPostsForThread(discussionThread, user);
        return new ResponseEntity(posts, HttpStatus.OK);
    }
/*
    @RequestMapping(method = RequestMethod.POST, value = "/{discussionThread}/" + PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postPostForDiscussionThread(@ContextUser User user, @PathVariable DiscussionThread discussionThread, @RequestBody DiscussionPostResource postResource) {
        DiscussionPost post = discussionService.insertDiscussionPostForDiscussionThreadAndUser(discussionThread, postResource.asPost(), user);
        return new ResponseEntity<DiscussionPostResource>(discussionPostResourceAssembler.toResource(post), HttpStatus.CREATED);
    }
    */
}
