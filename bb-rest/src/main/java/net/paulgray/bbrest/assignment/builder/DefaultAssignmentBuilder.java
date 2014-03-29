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
public class DefaultAssignmentBuilder implements BbAssignmentFactory {

    public BbAssignment getBbAssignment(Lineitem lineitem, Course course) {
        System.out.println("****Calling DefaultBuilder ");
    
        //if it's a discussion Assignment, get the link for the outcome definition's link id, and somehow attach the description to that!
        if(lineitem.getOutcomeDefinition() != null){
            System.out.println("****OutcomeDef was not null...");
        }
        return new BbAssignment(lineitem, course, "Default");
    }

}
