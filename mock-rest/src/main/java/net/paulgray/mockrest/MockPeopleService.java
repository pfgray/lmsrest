/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest;

import java.util.List;
import net.paulgray.lmsrest.people.PeopleService;
import net.paulgray.lmsrest.people.Person;
import org.springframework.stereotype.Component;

/**
 *
 * @author pgray
 */
@Component
public class MockPeopleService implements PeopleService{

    public List<Person> getPeopleForUserAndCourse(String userId, String courseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
