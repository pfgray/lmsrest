/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.security;

import net.paulgray.lmsrest.user.User;
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
        if(user != null){
            System.out.println("Got user: " + user.getUsername());
            this.user = user;
            this.authenticated = true;
        }
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new LinkedList<GrantedAuthority>();
    }

    @Override
    public Object getCredentials() {
        return this.user;
    }

    @Override
    public Object getDetails() {
        return this.user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean bln) throws IllegalArgumentException {
        this.authenticated = bln;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
    
}
