/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.post;

import gray.paul.lmsdata.discussion.DiscussionPost;
import gray.paul.lmsdata.discussion.DiscussionService;
import gray.paul.lmsdata.discussion.DiscussionThread;
import gray.paul.lmsdata.user.User;
import static gray.paul.lmsrest.discussion.post.DiscussionPostController.PATH;
import gray.paul.lmsrest.discussion.thread.DiscussionThreadController;
import gray.paul.lmsrest.web.ContextUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    DiscussionPostResourceAssembler discussionPostResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, value = "/{discussionThread}/" + PATH)
    public ResponseEntity getPostsForDiscussionThread(@ContextUser User user, @PathVariable DiscussionThread discussionThread) {
        List<DiscussionPost> posts = discussionService.getDiscussionPostsForThread(discussionThread);
        return new ResponseEntity<List<DiscussionPostResource>>(discussionPostResourceAssembler.toResources(posts), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{discussionThread}/" + PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postPostForDiscussionThread(@ContextUser User user, @PathVariable DiscussionThread discussionThread, @RequestBody DiscussionPostResource postResource) {
        DiscussionPost post = discussionService.insertDiscussionPostForDiscussionThreadAndUser(discussionThread, postResource.asPost(), user);
        return new ResponseEntity<DiscussionPostResource>(discussionPostResourceAssembler.toResource(post), HttpStatus.CREATED);
    }
}
