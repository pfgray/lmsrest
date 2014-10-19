/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.mockrest.course;

import java.util.List;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.lmsrest.course.CourseService;
import net.paulgray.lmsrest.user.User;
import net.paulgray.mockrest.user.MockUser;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author paul
 */
@Component
public class MockCourseService implements CourseService {

    @Autowired
    SessionFactory sessionFactory;

    public Course getCourseForId(String id) {
        Integer courseId = Integer.valueOf(id);
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(MockCourse.class);
        crit.add(Restrictions.eq("id", id));
        return (MockCourse) crit.uniqueResult();
    }

    public List getCoursesForUser(User user, String courseFilter) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(MockUser.class);
        crit.add(Restrictions.eq("id", user.getId()));
        crit.setFetchMode("courses", FetchMode.JOIN);
        MockUser mockUser = (MockUser) crit.uniqueResult();
        return mockUser.getCourses();
    }

}
