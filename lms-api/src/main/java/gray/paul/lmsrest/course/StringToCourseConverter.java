/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.course;

import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.course.CourseService;
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
