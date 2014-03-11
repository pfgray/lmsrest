/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.enrollment;

import net.paulgray.lmsrest.enrollment.Enrollment;






/**
 *
 * @author pfgray
 */
public class BbEnrollment extends Enrollment {
    
    public BbEnrollment(blackboard.admin.data.course.Enrollment enrollment){
        super();
        if (enrollment.getCourseId() != null) {
            this.courseId = enrollment.getCourseId().toExternalString();
        }
        if (enrollment.getEnrollmentDate() != null) {
            this.enrollmentDate = enrollment.getEnrollmentDate().getTime();
        }
        if (enrollment.getId() != null) {
            this.id = enrollment.getId().getExternalString();
        }
        if (enrollment.getRowStatus() != null) {
            this.status = enrollment.getRowStatus().toFieldName();
        }
        if (enrollment.getUserId() != null) {
            this.userId = enrollment.getUserId().getExternalString();
        }
        if(enrollment.getRole() != null){
            this.role = enrollment.getRole().getIdentifier();
        }
    }
}
