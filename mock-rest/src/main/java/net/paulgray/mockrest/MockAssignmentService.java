/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.Arrays;
import java.util.List;
import net.paulgray.lmsrest.assignment.Assignment;
import net.paulgray.lmsrest.assignment.AssignmentService;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
public class MockAssignmentService implements AssignmentService{

    public List<Assignment> getAssignments(User user) {
        return Arrays.asList(new Assignment("1", "Assignment #1", "Write this paper, yo", null, null, null),
                             new Assignment("2", "Assignment #2", "Write this paper, yo", null, null, null),
                             new Assignment("3", "Assignment #3", "Write this paper, yo", null, null, null));
    }

    public List<Assignment> getAssignments(User user, Course course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Assignment getAssignment(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
