/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.assignment;

import blackboard.data.content.Content;
import blackboard.data.gradebook.impl.OutcomeDefinition;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.PkId;
import blackboard.persist.content.ContentDbLoader;
import blackboard.persist.gradebook.impl.OutcomeDefinitionDbLoader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.course.BbCourseService;
import net.paulgray.lmsrest.assignment.Assignment;
import net.paulgray.lmsrest.assignment.AssignmentService;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author paul
 */
@Service
public class BbAssignmentService implements AssignmentService<OutcomeDefinition> {
    
    @Autowired
    BbCourseService bbCourseService;

    public List<OutcomeDefinition> getAssignments(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<OutcomeDefinition> getAssignments(User user, Course course) {
        try {
            List<Assignment> assignments = new LinkedList<Assignment>();
//            LineitemDbLoader lineItemDbLoader = LineitemDbLoader.Default.getInstance();
//            List<Lineitem> lineitems = lineItemDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
//            ContentDbLoader contentDbLoader = ContentDbLoader.Default.getInstance();
//            ScoreDbLoader scoreDbLoader = ScoreDbLoader.Default.getInstance();
            ContentDbLoader contentDbLoader = ContentDbLoader.Default.getInstance();
            OutcomeDefinitionDbLoader outcomeDefinitionDbLoader = OutcomeDefinitionDbLoader.Default.getInstance();
            List<OutcomeDefinition> outcomeDefinitions = outcomeDefinitionDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
            
            
            
            List<Content> contents = contentDbLoader.loadByCourseIdAndTitle(Id.UNSET_ID, null);
            
            for(Content c : contents){
                assignments.add(new BbAssignment(c, course));
            }
            
            for(OutcomeDefinition od : outcomeDefinitions){
                if(od != null && !od.isTotal() && !od.isWeightedTotal() && od.isVisible()){
                    assignments.add(new BbAssignment(od, course));
                }
            }
            List<OutcomeDefinition> ods = new ArrayList<OutcomeDefinition>(outcomeDefinitions);
            return outcomeDefinitions;
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public OutcomeDefinition getAssignment(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
