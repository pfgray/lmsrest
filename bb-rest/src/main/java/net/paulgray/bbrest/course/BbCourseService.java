/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.course;

import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseService;
import net.paulgray.lmsrest.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.discussion.BbDiscussionBoard;
import net.paulgray.bbrest.discussion.BbDiscussionService;
import net.paulgray.lmsrest.discussion.DiscussionBoard;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author pfgray
 */
public class BbCourseService implements CourseService {
    
    @Autowired
    BbDiscussionService bbDiscussionService;

    @Override
    public Course getCourseForId(String id) {
        try {
            CourseDbLoader courseDbLoader = CourseDbLoader.Default.getInstance();
            return new BbCourse(courseDbLoader.loadById(BlackboardUtilities.getIdFromPk(id, blackboard.data.course.Course.class)));
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Course> getCoursesForUser(User user, String courseFilter) {
        try {
            CourseDbLoader courseDbLoader = CourseDbLoader.Default.getInstance();
            List<blackboard.data.course.Course> courses = courseDbLoader.loadByUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            List<Course> toReturn = new LinkedList<Course>();
            for(blackboard.data.course.Course course : courses){
                if(course.getId() != null && course.getCourseId().contains(courseFilter)){
                    BbCourse bbCourse = new BbCourse(course, BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
                    List<DiscussionBoard> dbs = bbDiscussionService.getDiscussionBoardsForCourseAndUser(bbCourse, user);
                    for(DiscussionBoard db : dbs){
                        bbCourse.unread_discussion_count = bbCourse.unread_discussion_count + ((BbDiscussionBoard)db).getUnread_messages();
                    }
                    toReturn.add(bbCourse);
                }
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /*
    public List<Course> getCoursesByCourseSearch(User user, String courseFilter, int page, int pageSize){
        try {
            CourseDbLoader courseDbLoader = CourseDbLoader.Default.getInstance();
            List<blackboard.data.course.Course> courses = courseDbLoader.loadByUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            
            CourseSearch cs = new CourseSearch(page, pageSize, null, null);
            cs.addSearchParameter(new CourseSearch.SearchParameter(CourseSearch.SearchKey.CourseId, courseFilter, SearchOperator.In));
            cs.addSearchParameter(new CourseSearch.SearchParameter(CourseSearch.SearchKey.CourseId, courseFilter, SearchOperator.In));
            
            List<Course> toReturn = new LinkedList<Course>();
            for(blackboard.data.course.Course course : courses){
                toReturn.add(new BbCourse(course, BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class)));
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
*/
}
