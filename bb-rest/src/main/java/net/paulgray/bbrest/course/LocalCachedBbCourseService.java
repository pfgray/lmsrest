/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.course;

import java.util.HashMap;
import java.util.Map;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author paul
 */
public class LocalCachedBbCourseService {
    
    private BbCourseService bbCourseService; 
    private Map<String, Course> courses;

    public LocalCachedBbCourseService(BbCourseService bbCourseService) {
        this.bbCourseService = bbCourseService;
        this.courses =  new HashMap<String, Course>();
    }

    public Course getCourseForId(String courseId) {
        if (!courses.containsKey(courseId)) {
            courses.put(courseId, bbCourseService.getCourseForId(courseId));
        }
        return courses.get(courseId);
    }

}
