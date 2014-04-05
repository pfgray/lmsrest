/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.assignment;

import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.impl.OutcomeDefinitionCategory;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.gradebook.LineitemDbLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.assignment.builder.BbAssignmentBuilder;
import net.paulgray.bbrest.assignment.builder.BbAssignmentFactory;
import net.paulgray.bbrest.assignment.builder.DefaultAssignmentBuilder;
import net.paulgray.bbrest.assignment.builder.DiscussionAssignmentBuilder;
import net.paulgray.bbrest.course.BbCourseService;
import net.paulgray.bbrest.discussion.BbDiscussionService;
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
public class BbAssignmentService implements AssignmentService<BbAssignment> {

    @Autowired
    BbCourseService bbCourseService;

    @Autowired
    BbDiscussionService bbDiscussionService;

    public static final List<String> acceptedAssignmentTypes
            = Arrays.asList(OutcomeDefinitionCategory.ASSIGNMENT,
                    OutcomeDefinitionCategory.BLOG,
                    OutcomeDefinitionCategory.DISCUSSION,
                    OutcomeDefinitionCategory.ESSAY,
                    OutcomeDefinitionCategory.EXAM,
                    OutcomeDefinitionCategory.EXTRA_CREDIT,
                    OutcomeDefinitionCategory.FINAL_EXAM,
                    OutcomeDefinitionCategory.GROUP_PROJECT,
                    OutcomeDefinitionCategory.HOMEWORK,
                    OutcomeDefinitionCategory.JOURNAL,
                    OutcomeDefinitionCategory.LAB,
                    OutcomeDefinitionCategory.MIDTERM_EXAM,
                    OutcomeDefinitionCategory.OTHER,
                    OutcomeDefinitionCategory.PAPER,
                    OutcomeDefinitionCategory.PRESENTATION,
                    OutcomeDefinitionCategory.PROBLEM_SET,
                    OutcomeDefinitionCategory.WIKI,
                    OutcomeDefinitionCategory.TEST,
                    OutcomeDefinitionCategory.SURVEY,
                    OutcomeDefinitionCategory.SCORM,
                    OutcomeDefinitionCategory.RESOURCE_BUNDLE,
                    OutcomeDefinitionCategory.QUIZ);

    {
        for (int i = 0; i < acceptedAssignmentTypes.size(); i++) {
            acceptedAssignmentTypes.set(i, acceptedAssignmentTypes.get(i).replaceAll("\\.name", ""));
        }
    }

    public List<BbAssignment> getAssignments(User user, String courseFilter) {
        List<BbAssignment> toReturn = new LinkedList<BbAssignment>();
        List<Course> courses = bbCourseService.getCoursesForUser(user, courseFilter);
        for (Course course : courses) {
            toReturn.addAll(this.getAssignments(user, course));
        }
        return toReturn;
    }

    public List<BbAssignment> getAssignments(User user, Course course) {
        try {
//            LineitemDbLoader lineItemDbLoader = LineitemDbLoader.Default.getInstance();
//            List<Lineitem> lineitems = lineItemDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
//            ContentDbLoader contentDbLoader = ContentDbLoader.Default.getInstance();
//            ScoreDbLoader scoreDbLoader = ScoreDbLoader.Default.getInstance();
            LineitemDbLoader lineitemDbLoader = LineitemDbLoader.Default.getInstance();
//            OutcomeDefinitionDbLoader outcomeDefinitionDbLoader = OutcomeDefinitionDbLoader.Default.getInstance();
//            List<OutcomeDefinition> outcomeDefinitions = outcomeDefinitionDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class));
//            List<OutcomeDefinition> ods = new LinkedList<OutcomeDefinition>();
            Id courseId = BlackboardUtilities.getIdFromPk(course.getId(), blackboard.data.course.Course.class);
            List<Lineitem> lineitems = lineitemDbLoader.loadByCourseId(courseId);
            List<BbAssignment> toReturn = new LinkedList<BbAssignment>();

            Map<String, BbAssignmentFactory> assignmentBuilders = new HashMap<String, BbAssignmentFactory>();
            assignmentBuilders.put(OutcomeDefinitionCategory.ASSIGNMENT.replaceAll("\\.name", ""), new BbAssignmentBuilder());
            assignmentBuilders.put(OutcomeDefinitionCategory.DISCUSSION.replaceAll("\\.name", ""), new DiscussionAssignmentBuilder(courseId));

            for (Lineitem li : lineitems) {
                if (li != null && li.getIsAvailable()) {
                    //&& li.getAssessmentLocation().equals(Lineitem.AssessmentLocation.INTERNAL)){
                    //&& acceptedAssignmentTypes.contains(li.getOutcomeDefinition().getCategory().getTitle())){
                    System.out.println("****checking factories for: " + li.getOutcomeDefinition().getCategory().getTitle());
                    BbAssignmentFactory factory = assignmentBuilders.get(li.getOutcomeDefinition().getCategory().getTitle());
                    if (factory == null) {
                        System.out.println("****factory not found");
                        factory = new DefaultAssignmentBuilder();
                    }

                    toReturn.add(factory.getBbAssignment(li, course));
                }
            }
            return toReturn;
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public BbAssignment getAssignment(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
