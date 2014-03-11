/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.course;

/**
 *
 * @author pfgray
 */
public class Course {
    
    protected String id;
    protected String courseId;
    protected String name;
    protected String description;

    public Course() {
    }

    public Course(String id, String courseId, String name, String description) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
