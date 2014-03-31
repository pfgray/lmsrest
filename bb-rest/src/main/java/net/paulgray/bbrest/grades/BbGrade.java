/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.grades;

import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.Score;
import blackboard.persist.PersistenceException;
import blackboard.persist.gradebook.LineitemDbLoader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.grades.Grade;

/**
 *
 * @author paul
 */
public class BbGrade extends Grade {

    public String assessmentType;
    
    public Date created;
    public Date updated;

    public BbGrade(Score bbScore, Course course) {
        this.id = bbScore.getId().getExternalString();
        this.course = course;
        this.grade = bbScore.getGrade();
        if (bbScore.getOutcome() != null) {
            if (bbScore.getOutcome().getBbAttributes() != null) {
                try {
                    this.score = bbScore.getOutcome().getManualScore();
                } catch (NullPointerException e) {
                    System.out.println("****BbAttributes was null, but amazingly, getManualScore still NPE'd");
                }
            } else {
                System.out.println("****BbAttributes was null");
            }
        } else {
            System.out.println("****Outcome was null");
        }
        try {
            LineitemDbLoader lineItemDbLoader = LineitemDbLoader.Default.getInstance();
            Lineitem lineitem = lineItemDbLoader.loadById(bbScore.getLineitemId());
            this.max = lineitem.getPointsPossible();
            this.title = lineitem.getName();
            if (lineitem.getOutcomeDefinition() != null && lineitem.getOutcomeDefinition().getCategory() != null) {
                this.assessmentType = lineitem.getOutcomeDefinition().getCategory().getTitle();
            }
            
        } catch (PersistenceException ex) {
            Logger.getLogger(BbGrade.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.created = bbScore.getDateAdded() != null ? bbScore.getDateAdded().getTime() : null;
        this.updated =  bbScore.getDateChanged() != null ? bbScore.getDateChanged().getTime() : null;
    }

}
