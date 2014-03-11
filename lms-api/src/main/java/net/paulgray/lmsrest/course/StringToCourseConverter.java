/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author pfgray
 */
public class StringToCourseConverter implements Converter<String, Course>{

    @Autowired
    CourseService courseService;
    
    public Course convert(String s) {
        return courseService.getCourseForId(s);
    }
    
}
