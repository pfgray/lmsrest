/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.assignment;

import blackboard.data.content.Content;
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
    
    public BbAssignment(OutcomeDefinition od, Course course, Content content){
        this.id = od.getId() != null ? od.getId().getExternalString() : null;
        this.title = od.getTitle();
        this.course = course;
        this.created =  od.getDateAdded() != null ? od.getDateAdded().getTime() : null;
        this.due = od.getDueDate() != null ? od.getDueDate().getTime() : null;
        if(content != null && content.getBody() != null){
            this.description = Jsoup.parse(content.getBody().getText()).text();
        } else {
            this.description = od.getDescription();
        }
        this.weight = od.getWeight();
        this.category = "external: " + od.getCategory().getExternalId() + ", title: " + od.getCategory().getTitle();
        this.updated = od.getDateModified() != null ? od.getDateModified().getTime() : null;
    }
    
}
