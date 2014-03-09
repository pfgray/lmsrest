/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.user;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author pfgray
 */
public class UserResource extends ResourceSupport {
    
    public String id;
    public String username;
    public String firstName;
    public String lastName;
    public String mi;
    public Date lastLogin;
    public Date birthDate;
    public String systemRole;
    public String email;
    public String locale;
    
}
