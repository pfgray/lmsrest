/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.course;

import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author pfgray
 */
public class BbCourse extends Course {
    
    public BbCourse(blackboard.data.course.Course course) {
        super(course.getId().getExternalString(), course.getCourseId(), course.getTitle(), course.getDescription());
    }
    
}
