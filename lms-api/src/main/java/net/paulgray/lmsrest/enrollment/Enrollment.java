/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.enrollment;

import java.util.Date;

/**
 *
 * @author pfgray
 */
public class Enrollment {
    
    protected Date enrollmentDate;
    protected String id;
    protected String status;
    protected String courseId;
    protected String userId;
    protected String role;

    public Enrollment() {
    }

    public Enrollment(Date enrollmentDate, String id, String status, String courseId, String userId, String role) {
        this.enrollmentDate = enrollmentDate;
        this.id = id;
        this.status = status;
        this.courseId = courseId;
        this.userId = userId;
        this.role = role;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
