/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.assignment;

import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.impl.Outcome;
import blackboard.data.gradebook.impl.OutcomeDefinition;
import net.paulgray.lmsrest.assignment.Assignment;
import net.paulgray.lmsrest.course.Course;
import org.jsoup.Jsoup;

/**
 *
 * @author paul
 */
public class BbAssignment extends Assignment {

    public float weight;
    public String category;
    public Boolean attempted = false;
    //public OutcomeDefinition outcomeDefinition;

    public BbAssignment(Lineitem li, Course course, String description) {
        this.id = li.getId() != null ? li.getId().getExternalString() : null;
        this.title = li.getName();
        this.course = course;
        if(li.getOutcomeDefinition() != null){
            OutcomeDefinition od = li.getOutcomeDefinition();
            //this.outcomeDefinition = outcomeDefinition;
            if(od.getDateCreated() != null){
                this.created = od.getDateCreated().getTime();
            }
            if(od.getDueDate() != null){
                this.due = od.getDueDate().getTime();
            }
            if(od.getCategory() != null){
                this.category = od.getCategory().getTitle();
            }
            if(od.getDateModified() != null){
                this.updated = od.getDateModified().getTime();
            }
            for (Outcome o : od.getOutcomes(true)) {
                if (o.getAttemptCount() > 0) {
                    this.attempted = true;
                    break;
                }
            }
        }
        
        if(description != null){
            this.description = Jsoup.parse(description).text();
        }
        this.weight = li.getWeight();

        
    }


}
