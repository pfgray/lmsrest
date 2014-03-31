/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.assignment.builder;

import blackboard.data.gradebook.Lineitem;
import net.paulgray.bbrest.assignment.BbAssignment;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author paul
 */
public interface BbAssignmentFactory {
    
    public BbAssignment getBbAssignment(Lineitem lineitem, Course course);
    
}
