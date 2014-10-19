/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.mockrest.user;

import javax.persistence.*;

import net.paulgray.lmsrest.user.User;
import net.paulgray.mockrest.course.MockCourse;
import net.paulgray.mockrest.course.MockEnrollment;

import java.util.List;

/**
 *
 * @author paul
 */
@Entity
@Table(name = "users")
public class MockUser extends User {

    @Id
    @Column(name = "id")
    protected Integer id;
    @Column(name = "username")
    protected String username;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    @Column(name = "mi")
    protected String mi;
    @Column(name = "encryptedPassword")
    protected String encryptedPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrollments", joinColumns = {
        @JoinColumn(name = "user_id", nullable = false, updatable = false)
    })
    protected List<MockCourse> courses;
    
    @Override
    public String getId() {
        return id == null ? null : id.toString();
    }

    @Override
    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMi() {
        return mi;
    }

    @Override
    public void setMi(String mi) {
        this.mi = mi;
    }

    public List<MockCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<MockCourse> courses) {
        this.courses = courses;
    }
}
