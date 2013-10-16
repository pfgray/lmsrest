/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.course;

import blackboard.persist.DbLoaderFactory;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import gray.paul.bbrest.BlackboardUtilities;
import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.course.CourseService;
import gray.paul.lmsdata.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfgray
 */
public class BbCourseService implements CourseService {

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
    public List<Course> getCoursesForUser(User user) {
        try {
            CourseDbLoader courseDbLoader = CourseDbLoader.Default.getInstance();
            List<blackboard.data.course.Course> courses = courseDbLoader.loadByUserId(BlackboardUtilities.getIdFromPk(user.getId(), blackboard.data.user.User.class));
            List<Course> toReturn = new LinkedList<Course>();
            for(blackboard.data.course.Course course : courses){
                toReturn.add(new BbCourse(course));
            }
            return toReturn;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbCourseService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
