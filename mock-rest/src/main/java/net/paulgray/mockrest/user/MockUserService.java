/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest.user;

import net.paulgray.lmsrest.user.User;
import net.paulgray.lmsrest.user.UserService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author paul
 */
@Component
public class MockUserService implements UserService {
    
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public User getUserForId(String id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(MockUser.class);
        crit.add(Restrictions.eq("id", id));
        return (MockUser) crit.uniqueResult();
    }

    @Transactional
    public User getUserForUsername(String username) {
        System.out.println("getting user with username: " + username);
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(MockUser.class);
        crit.add(Restrictions.eq("username", username));
        return (MockUser) crit.uniqueResult();
    }
    
}
