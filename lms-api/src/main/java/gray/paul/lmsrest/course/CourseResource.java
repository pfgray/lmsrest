/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.course;

import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class CourseResource extends ResourceSupport {
        
    public String id;
    public String courseId;
    public String name;
    public String description;
    
}
