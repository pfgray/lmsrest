/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest.user;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.user.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author paul
 */
public class MockUserService implements UserService {
    
    @Autowired
    SessionFactory sessionFactory;

    public User getUserForId(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
