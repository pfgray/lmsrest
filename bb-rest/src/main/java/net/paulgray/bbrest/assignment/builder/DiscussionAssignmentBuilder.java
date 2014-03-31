/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.bbrest.assignment.builder;

import blackboard.data.discussionboard.Conference;
import blackboard.data.discussionboard.Forum;
import blackboard.data.gradebook.Lineitem;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.discussionboard.ConferenceDbLoader;
import blackboard.persist.discussionboard.ForumDbLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.assignment.BbAssignment;
import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author paul
 */
public class DiscussionAssignmentBuilder implements BbAssignmentFactory {
    
    private List<Forum> forums;
    
    public DiscussionAssignmentBuilder(Id courseId){
        try {
            this.forums = new LinkedList<Forum>();
            ForumDbLoader forumDbLoader = ForumDbLoader.Default.getInstance();
            ConferenceDbLoader conferenceDbLoader = ConferenceDbLoader.Default.getInstance();
            List<Conference> conferences = conferenceDbLoader.loadAllByCourseId(courseId);

            for (Conference conference : conferences) {
                List<Forum> forums = forumDbLoader.loadByConferenceId(conference.getId());
                for (Forum forum : forums) {
                    this.forums.add(forum);
                }
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(DiscussionAssignmentBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BbAssignment getBbAssignment(Lineitem lineitem, Course course) {
        System.out.println("****Calling BbAssignmentBuilder ");
        //if it's a discussion Assignment, get the link for the outcome definition's link id, and somehow attach the description to that!
        if(lineitem.getAssessmentId() != null){
            System.out.println("****AssessmentId was not null...");
            
            for(Forum forum : forums){
                if( forum.getProperties() != null 
                    && forum.getDescription() != null
                    && forum.getProperties().getForumGradeLineitemPk() != null
                    && forum.getProperties().getForumGradeLineitemPk().equals(lineitem.getAssessmentId())){
                    //this is it, the the description!
                    String description = forum.getDescription().getText() != null ? forum.getDescription().getText() : forum.getDescription().getFormattedText();
                    System.out.println("****Description found: " + description);
                    return new BbAssignment(lineitem, course, description);
                }
            }
            return new BbAssignment(lineitem, course, null);
        } else {
            return new BbAssignment(lineitem, course, null);
        }
       
    }
    
}
