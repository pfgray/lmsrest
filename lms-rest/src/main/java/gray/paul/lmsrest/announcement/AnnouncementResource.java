/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.announcement;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class AnnouncementResource extends ResourceSupport {

    public String id;
    public String text;
    public String title;
    public Date created;
    
}
