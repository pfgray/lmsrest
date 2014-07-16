/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.web.ContextUserArgumentResolver;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 * @author paul
 */
public class MockUserArgumentResolver extends ContextUserArgumentResolver {
    @Override
    public Object resolveArgument(MethodParameter mp, ModelAndViewContainer mavc, NativeWebRequest nwr, WebDataBinderFactory wdbf) throws Exception {
        //get the authentication from spring's context
        return new User("id", "pfgray", "Paul", "Gray", "F", null, null, null, null, null);
    }
}
