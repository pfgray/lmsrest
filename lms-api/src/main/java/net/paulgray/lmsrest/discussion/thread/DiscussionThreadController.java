/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.discussion.thread;

import net.paulgray.lmsrest.discussion.DiscussionBoard;
import net.paulgray.lmsrest.discussion.DiscussionService;
import net.paulgray.lmsrest.discussion.DiscussionThread;
import net.paulgray.lmsrest.discussion.board.DiscussionBoardController;
import java.util.List;
import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUser;
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
@RequestMapping(value = DiscussionBoardController.PATH)
public class DiscussionThreadController {
    
    public static final String PATH = "discussion_threads";
    @Autowired
    DiscussionService discussionService;

    @RequestMapping(method = RequestMethod.GET, value = "/{discussionBoard}/" + DiscussionThreadController.PATH)
    public ResponseEntity getThreadsForDiscussionBoard(@ContextUser User user, @PathVariable DiscussionBoard discussionBoard) {
        List<DiscussionThread> threads = discussionService.getDiscussionThreadsForBoard(discussionBoard, user);
        return new ResponseEntity(threads, HttpStatus.OK);
    }
/*
    @RequestMapping(method = RequestMethod.POST, value = "/{discussionBoard}/" + DiscussionThreadController.PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postPostForDiscussionThread(@ContextUser User user, @PathVariable DiscussionBoard discussionBoard, @RequestBody DiscussionThread discussionThread) {
        DiscussionThread dt = discussionService.insertDiscussionThreadForDiscussionBoardAndUser(discussionBoard, discussionThread, user);
        return new ResponseEntity(dt, HttpStatus.CREATED);
    }
  */  
}
