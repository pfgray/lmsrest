/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.assignment;

import blackboard.data.content.Content;
import blackboard.data.gradebook.impl.Outcome;
import blackboard.data.gradebook.impl.OutcomeDefinition;
import blackboard.data.gradebook.impl.OutcomeDefinitionCategory;
import java.util.Arrays;
import java.util.List;
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
    public Boolean attempted;
    public List<String> available_categories;

    public BbAssignment(OutcomeDefinition od, Course course) {
        this.id = od.getId() != null ? od.getId().getExternalString() : null;
        this.title = od.getTitle();
        this.course = course;
        this.created = od.getDateAdded() != null ? od.getDateAdded().getTime() : null;
        this.due = od.getDueDate() != null ? od.getDueDate().getTime() : null;
        
        if(od.getDescription() != null){
            this.description = Jsoup.parse(od.getDescription()).text();
        }
        this.weight = od.getWeight();
        this.category = ", title: " + od.getCategory().getTitle();
        this.updated = od.getDateModified() != null ? od.getDateModified().getTime() : null;

        this.available_categories = categories;

        this.attempted = false;
        for (Outcome o : od.getOutcomes(true)) {
            if (o.getAttemptCount() > 0) {
                this.attempted = true;
                break;
            }
        }
    }
    
    public BbAssignment(Content c, Course course) {
        this.id = c.getId() != null ? c.getId().getExternalString() : null;
        this.title = c.getTitle();
        this.course = course;
        this.category = "CONTENT_ITEM";
        
        if(c.getBody() != null && c.getBody().getText() != null){
            this.description = Jsoup.parse(c.getBody().getText()).text();
        }
        
        this.available_categories = categories;
    }

    public static List<String> categories = Arrays.asList(
            OutcomeDefinitionCategory.ASSIGNMENT,
            OutcomeDefinitionCategory.DISCUSSION,
            OutcomeDefinitionCategory.ESSAY,
            OutcomeDefinitionCategory.EXAM,
            OutcomeDefinitionCategory.EXTRA_CREDIT,
            OutcomeDefinitionCategory.GROUP_PROJECT,
            OutcomeDefinitionCategory.HOMEWORK,
            OutcomeDefinitionCategory.JOURNAL,
            OutcomeDefinitionCategory.LAB,
            OutcomeDefinitionCategory.MIDTERM_EXAM,
            OutcomeDefinitionCategory.OTHER,
            OutcomeDefinitionCategory.PAPER,
            OutcomeDefinitionCategory.PRESENTATION,
            OutcomeDefinitionCategory.PROBLEM_SET,
            OutcomeDefinitionCategory.QUIZ,
            OutcomeDefinitionCategory.RESOURCE_BUNDLE,
            OutcomeDefinitionCategory.SCORM,
            OutcomeDefinitionCategory.SURVEY,
            OutcomeDefinitionCategory.TEST,
            OutcomeDefinitionCategory.WIKI
    );

    {
        for (int i = 0; i < categories.size(); i++) {
            categories.set(i, categories.get(i).replaceAll("\\.name", ""));
        }
    }

}
