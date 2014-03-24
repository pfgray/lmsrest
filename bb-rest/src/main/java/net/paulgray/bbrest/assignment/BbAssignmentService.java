/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.assignment;

import blackboard.data.gradebook.impl.OutcomeDefinition;
import blackboard.persist.PersistenceException;
import blackboard.persist.gradebook.impl.OutcomeDefinitionDbLoader;
import java.util.LinkedList;
import java.util.List;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.course.BbCourseService;
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
//            LineitemDbLoader lineItemDbLoader = LineitemDbLoader.Default.getInstance();
//            List<Lineitem> lineitems = lineItemDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
//            ContentDbLoader contentDbLoader = ContentDbLoader.Default.getInstance();
//            ScoreDbLoader scoreDbLoader = ScoreDbLoader.Default.getInstance();
            OutcomeDefinitionDbLoader outcomeDefinitionDbLoader = OutcomeDefinitionDbLoader.Default.getInstance();
            List<OutcomeDefinition> outcomeDefinitions = outcomeDefinitionDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
            List<OutcomeDefinition> ods = new LinkedList<OutcomeDefinition>();
            
            for(OutcomeDefinition od : outcomeDefinitions){
                if(od != null && !od.isTotal() && !od.isWeightedTotal() && od.isVisible()){
                    
                    //if the od is a discussion post, consider getting it's link for the description???
                    ods.add(od);
                }
            }
            return ods;
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public OutcomeDefinition getAssignment(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
