/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.board;

import gray.paul.lmsdata.course.Course;
import gray.paul.lmsrest.discussion.post.DiscussionPostResourceAssembler;
import gray.paul.lmsdata.discussion.DiscussionService;
import gray.paul.lmsdata.discussion.DiscussionBoard;
import gray.paul.lmsdata.course.CourseService;
import gray.paul.lmsdata.user.User;
import gray.paul.lmsdata.user.UserService;
import gray.paul.lmsrest.course.CourseController;
import gray.paul.lmsrest.web.ContextUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = CourseController.PATH)
public class DiscussionBoardController {

    public static final String PATH = "discussion_boards";
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    DiscussionService discussionBoardService;
    @Autowired
    DiscussionBoardResourceAssembler discussionBoardResourceAssembler;
    @Autowired
    DiscussionPostResourceAssembler discussionBoardPostResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{course}/" + DiscussionBoardController.PATH)
    public ResponseEntity getDiscussionBoardsForCourse(@ContextUser User user, @PathVariable Course course) {
        List<DiscussionBoard> discussionBoards = discussionBoardService.getDiscussionBoardsForCourseAndUser(course, user);
        return new ResponseEntity<List<DiscussionBoardResource>>(discussionBoardResourceAssembler.toResources(discussionBoards), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/{course}/" + DiscussionBoardController.PATH)
    public ResponseEntity postDiscussionBoardForCourse(@ContextUser User user, @PathVariable Course course, @RequestBody DiscussionBoardResource discussionBoardResource) {
        List<DiscussionBoard> discussionBoards = discussionBoardService.getDiscussionBoardsForCourseAndUser(course, user);
        return new ResponseEntity<List<DiscussionBoardResource>>(discussionBoardResourceAssembler.toResources(discussionBoards), HttpStatus.OK);
    }

}
