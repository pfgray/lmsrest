/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.enrollment;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class EnrollmentResource extends ResourceSupport{

    public Date enrollmentDate;
    public String id;
    public String status;
    public String courseId;
    public String userId;
    public String role;
    
}
