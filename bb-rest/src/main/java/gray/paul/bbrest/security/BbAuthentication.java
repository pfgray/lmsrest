/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.security;

import gray.paul.lmsdata.user.User;
import java.util.Collection;
import java.util.LinkedList;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author pfgray
 */
public class BbAuthentication implements Authentication{
    
    private User user;
    private boolean authenticated;
    
    public BbAuthentication(User user){
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new LinkedList<GrantedAuthority>();
    }

    public Object getCredentials() {
        return this.user;
    }

    public Object getDetails() {
        return this.user;
    }

    public Object getPrincipal() {
        return user;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public void setAuthenticated(boolean bln) throws IllegalArgumentException {
        this.authenticated = bln;
    }

    public String getName() {
        return user.getUsername();
    }
    
}
