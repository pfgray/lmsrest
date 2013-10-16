/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.web;

import gray.paul.lmsdata.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 * @author pfgray
 */
public class ContextUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter mp) {
        if (!mp.hasParameterAnnotation(ContextUser.class)) {
            return false;
        } else {

            return true;
        }
    }

    @Override
    public Object resolveArgument(MethodParameter mp, ModelAndViewContainer mavc, NativeWebRequest nwr, WebDataBinderFactory wdbf) throws Exception {
        Boolean required = mp.getParameterAnnotation(ContextUser.class).required();
        try {
            SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (o.getClass().equals(java.lang.String.class) && ((String) o).equals("anonymmousUser")) {
                if (required) {
                    throw new NoContextUserException("Context User not found but required.");
                } else {
                    return null;
                }
            } else {
                return o;
            }
        } catch (Exception e) {
            if (required) {
                throw new NoContextUserException("Context User not found but required.");
            } else {
                return null;
            }
        }
    }
}
