/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.security;

import blackboard.platform.context.ContextManagerFactory;
import blackboard.platform.session.BbSession;
import net.paulgray.bbrest.user.BbUserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 *
 * @author pfgray
 */
public class BlackboardPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    
    @Autowired
    BbUserService bbRestUserService;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        //returns the User object, populated by Blackboard's SecurityContext tools
        BbSession sess = ContextManagerFactory.getInstance().getContext().getSession();
        if(!sess.isAuthenticated()){
            return null;
        }else{
            return bbRestUserService.getUserForId(sess.getUserId().getExternalString());
        }
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest hsr) {
        //returns the User object, populated by Blackboard's SecurityContext tools
        BbSession sess = ContextManagerFactory.getInstance().getContext().getSession();
        if(!sess.isAuthenticated()){
            return null;
        }else{
            return bbRestUserService.getUserForId(sess.getUserId().getExternalString());
        }
    }
    
}
