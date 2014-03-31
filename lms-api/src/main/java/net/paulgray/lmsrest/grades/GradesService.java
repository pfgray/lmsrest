/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.grades;

import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
public interface GradesService {
    
    public List<Grade> getGradesForUser(User user);
    
    public List<Grade> getGradesForUserAndCourse(User user, Course course);
    
}
