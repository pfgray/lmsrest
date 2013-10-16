/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.board;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class DiscussionBoardResource extends ResourceSupport {
        
    public String id;
    public String title;
    public String description;
    public Date last_edited;
    public Date end_date;
    public Boolean available;
    
    public DiscussionBoardResource() {
    }
    
}
