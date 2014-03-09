/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.discussion.post;

import gray.paul.lmsdata.discussion.DiscussionPost;
import java.util.Date;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class DiscussionPostResource extends ResourceSupport {
    
    public String body;
    public Date last_edited;
    public Date posted_date;
    public String user;
    public List<DiscussionPostResource> replies;
    
    public DiscussionPost asPost(){
        DiscussionPost post = new DiscussionPost();
        post.setBody(body);
        post.setLastEdited(last_edited);
        post.setPostedDate(posted_date);
        post.setUser(user);
        return post;        
    }
    
    
    
//    Links:
//    protected String userId;
//    protected String threadId;

    public DiscussionPostResource() {
    }
}
