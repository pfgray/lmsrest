/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.security;

import java.util.logging.Logger;
import net.paulgray.lmsrest.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

/**
 *
 * @author pfgray
 */
public class BlackboardPreAuthenticatedAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {
    
    @Override
    public Authentication authenticate(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return new BbAuthentication(user);
    }
    
}
