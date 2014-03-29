/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.people;

import java.util.List;

/**
 *
 * @author paul
 */
public interface PeopleService {
    
    
    public List<Person> getPeopleForUserAndCourse(String userId, String courseId);
    
}
