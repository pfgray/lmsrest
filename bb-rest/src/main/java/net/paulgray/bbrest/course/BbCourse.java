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
    
    public Integer unread_discussion_count;
    
    public BbCourse(blackboard.data.course.Course course) {
        super(course.getId().getExternalString(), course.getCourseId(), course.getTitle(), course.getDescription());
    }
    
    public BbCourse(blackboard.data.course.Course course, blackboard.persist.Id contextUser) {
        super(course.getId().getExternalString(), course.getCourseId(), course.getTitle(), course.getDescription());
        this.unread_discussion_count = 0;
    }
    
}
