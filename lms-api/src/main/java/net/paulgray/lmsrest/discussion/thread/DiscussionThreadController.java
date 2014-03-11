/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion.thread;

import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionService;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.discussion.board.DiscussionBoardController;
import net.paulgray.lmsrest.web.ContextUser;
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
@RequestMapping(value = DiscussionBoardController.PATH)
public class DiscussionThreadController {
    
    public static final String PATH = "discussion_threads";
    @Autowired
    DiscussionService discussionService;
    @Autowired
    DiscussionThreadResourceAssembler discussionThreadResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, value = "/{discussionBoard}/" + DiscussionThreadController.PATH)
    public ResponseEntity getThreadsForDiscussionBoard(@PathVariable DiscussionBoard discussionBoard) {
        List<DiscussionThread> threads = discussionService.getDiscussionThreadsForBoard(discussionBoard);
        return new ResponseEntity<List<DiscussionThreadResource>>(discussionThreadResourceAssembler.toResources(threads), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{discussionBoard}/" + DiscussionThreadController.PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postPostForDiscussionThread(@ContextUser User user, @PathVariable DiscussionBoard discussionBoard, @RequestBody DiscussionThreadResource discussionThreadResource) {
        DiscussionThread dt = discussionService.insertDiscussionThreadForDiscussionBoardAndUser(discussionBoard, discussionThreadResourceAssembler.toDiscussionThread(discussionThreadResource), user);
        return new ResponseEntity<DiscussionThreadResource>(discussionThreadResourceAssembler.toResource(dt), HttpStatus.CREATED);
    }
    
}
