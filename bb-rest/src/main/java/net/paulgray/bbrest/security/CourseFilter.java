/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.security;

import blackboard.persist.Id;
import blackboard.platform.security.Entitlement;
import blackboard.platform.security.SecurityUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.lmsrest.user.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author paul
 */
public class CourseFilter extends GenericFilterBean {
    
    private static final String COURSE_BEGIN_IDENTIFIER = "courses/";
    private static final String COURSE_END_IDENTIFIER = "/";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        
        Logger.getLogger(CourseFilter.class.getName()).log(Level.INFO, "Calling Course Filter...");

        String url = ((HttpServletRequest) request).getRequestURL().toString();
        String course = getCourseIdFromUrl(url);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getDetails();
        
        Id userId = BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class);
        Id courseId = BlackboardUtilities.getIdFromPk(course, blackboard.data.course.Course.class);
        
        if(!SecurityUtil.userHasEntitlement(userId, courseId, new Entitlement("course.VIEW"))){
            throw new AccessDeniedException("Context user: '" + user.getUsername() + "' does not have access to view course: " + courseId);
        }
        
    }
    
    private static String getCourseIdFromUrl(String url){
        Integer beginCourseIndex = url.indexOf(COURSE_BEGIN_IDENTIFIER) + COURSE_BEGIN_IDENTIFIER.length();
        Integer endCourseIndex = url.indexOf(COURSE_END_IDENTIFIER, beginCourseIndex);
        String id = url.substring(beginCourseIndex, endCourseIndex);
        Logger.getLogger(CourseFilter.class.getName()).log(Level.INFO, "got id: " + id + " from url: " + url);
        return id;
    }

}
