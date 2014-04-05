/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.List;
import net.paulgray.lmsrest.assignment.AssignmentService;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
public class MockAssignmentService implements AssignmentService{

    public List getAssignments(User user, String courseFilter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List getAssignments(User user, Course course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getAssignment(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
