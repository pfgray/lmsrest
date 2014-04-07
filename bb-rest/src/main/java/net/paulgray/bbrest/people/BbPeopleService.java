/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.people;

import blackboard.data.user.User;
import blackboard.persist.PersistenceException;
import blackboard.persist.user.UserDbLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.paulgray.bbrest.BlackboardUtilities;
import net.paulgray.bbrest.course.BbCourseService;
import net.paulgray.lmsrest.people.PeopleService;
import net.paulgray.lmsrest.people.Person;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author paul
 */
public class BbPeopleService implements PeopleService {

    public List<Person> getPeopleForUserAndCourse(String userId, String courseId) {
        try {
            if (!BbCourseService.currentUserCanViewCourse(courseId)) {
                throw new AccessDeniedException("User cannot view course: " +courseId);
            }

            List<Person> people = new LinkedList<Person>();
            UserDbLoader userDbLoader = UserDbLoader.Default.getInstance();
            List<User> users = userDbLoader.loadByCourseId(BlackboardUtilities.getIdFromPk(courseId, blackboard.data.course.Course.class));
            for (User user : users) {
                people.add(new BbPerson(user));
            }
            return people;
        } catch (PersistenceException ex) {
            Logger.getLogger(BbPeopleService.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<Person>();
        }

    }

}
