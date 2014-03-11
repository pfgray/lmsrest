/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.assignment;

import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
public interface AssignmentService {
    
    public List<Assignment> getAssignments(User user);
    
    public List<Assignment> getAssignments(User user, Course course);
    
    public Assignment getAssignment(String id);
    
}
