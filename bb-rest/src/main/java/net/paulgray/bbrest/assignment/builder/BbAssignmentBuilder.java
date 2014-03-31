/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.assignment.builder;

import blackboard.data.content.Content;
import blackboard.data.gradebook.Lineitem;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.assignment.BbAssignment;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author paul
 */
public class BbAssignmentBuilder implements BbAssignmentFactory {

    public BbAssignment getBbAssignment(Lineitem lineitem, Course course) {
        System.out.println("****Calling BbAssignmentBuilder ");
        //if it's a discussion Assignment, get the link for the outcome definition's link id, and somehow attach the description to that!
        if(lineitem.getOutcomeDefinition() != null){
            System.out.println("****OutcomeDef was not null...");
            Id contentId = lineitem.getOutcomeDefinition().getContentId();
            try {
                ContentDbLoader contentDbLoader = ContentDbLoader.Default.getInstance();
                Content content = contentDbLoader.loadById(contentId);
                
                if(content != null && content.getBody() != null){
                    return new BbAssignment(lineitem, course, content.getBody().getText());
                } else {
                    return new BbAssignment(lineitem, course, "Assignment");
                }
            } catch (PersistenceException ex) {
                Logger.getLogger(DiscussionAssignmentBuilder.class.getName()).log(Level.SEVERE, null, ex);
                return new BbAssignment(lineitem, course, null);
            }
        } else {
            return new BbAssignment(lineitem, course, null);
        }
        
    }
    
    
}
