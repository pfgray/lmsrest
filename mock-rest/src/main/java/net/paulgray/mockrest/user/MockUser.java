/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.mockrest.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import net.paulgray.lmsrest.user.User;

/**
 *
 * @author paul
 */
@Entity
@Table(name = "user")
public class MockUser extends User {

    @Id
    @Column(name = "id")
    protected String id;
    @Column(name = "username")
    protected String username;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    @Column(name = "mi")
    protected String mi;

}
