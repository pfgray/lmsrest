/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.thread;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class DiscussionThreadResource extends ResourceSupport{
    
    public String subject;
    public String text;
    public Date last_edited;
    public Boolean locked;
    public Date posted_date;
    public String created_by;
    
    //links:
    //self: /discussion_thread/{id}
    //self: /users/{id}
    //posts: /discussion_thread/{id}/discussion_posts

    public DiscussionThreadResource() {
    }
    
}
