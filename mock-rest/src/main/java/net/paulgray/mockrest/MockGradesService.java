/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.grades.Grade;
import net.paulgray.lmsrest.grades.GradesService;
import net.paulgray.lmsrest.user.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author paul
 */
@Component
public class MockGradesService implements GradesService{

    public List<Grade> getGradesForUser(User user, String courseFilter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Grade> getGradesForUserAndCourse(User user, Course course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
