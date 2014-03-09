/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.board;

import gray.paul.lmsdata.discussion.DiscussionBoard;
import gray.paul.lmsrest.course.CourseController;
import gray.paul.lmsrest.discussion.thread.DiscussionThreadController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author pfgray
 */
@Component
public class DiscussionBoardResourceAssembler extends ResourceAssemblerSupport<DiscussionBoard, DiscussionBoardResource> {

    public DiscussionBoardResourceAssembler() {
        super(DiscussionBoardController.class, DiscussionBoardResource.class);
    }

    @Override
    public DiscussionBoardResource toResource(DiscussionBoard discussionBoard) {
        DiscussionBoardResource discussionBoardResource = new DiscussionBoardResource();
        discussionBoardResource.id = discussionBoard.getId();
        discussionBoardResource.title = discussionBoard.getTitle();
        discussionBoardResource.description = discussionBoard.getDescription();
        discussionBoardResource.last_edited = discussionBoard.getLastEdited();
        discussionBoardResource.end_date = discussionBoard.getEndDate();
        discussionBoardResource.available = discussionBoard.getAvailable();
        addLinks(discussionBoard, discussionBoardResource);

        return discussionBoardResource;
    }

    private void addLinks(DiscussionBoard discussionBoard, DiscussionBoardResource discussionBoardResource) {
        if(discussionBoard.getCourseId() != null){
            discussionBoardResource.add(linkTo(CourseController.class).slash(discussionBoard.getCourseId()).withRel("course"));
        }
        discussionBoardResource.add(linkTo(DiscussionThreadController.class).slash(discussionBoard.getId()).slash(DiscussionThreadController.PATH).withRel(DiscussionThreadController.PATH));
    }
}
