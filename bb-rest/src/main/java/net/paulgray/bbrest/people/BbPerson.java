/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.people;

import blackboard.data.user.User;
import net.paulgray.lmsrest.people.Person;

/**
 *
 * @author paul
 */
public class BbPerson extends Person {

    public String address;
    public String phone;
    public String department;
    public String uniqueId;

    public BbPerson(User user) {
        //standard info
        this.id = user.getId().getExternalString();
        this.given_name = user.getGivenName();
        this.family_name = user.getFamilyName();
        this.username = user.getUserName();
        this.email = user.getEmailAddress();
        this.role = user.getPortalRole() != null ? user.getPortalRole().getRoleName() : null;

        //bb info
        this.department = user.getDepartment();
        this.address = user.getStreet1() + " " + user.getStreet2() + ", " + user.getCity() + ", " + user.getState() + " " + user.getZipCode();
        this.phone = user.getMobilePhone();
        this.uniqueId = user.getStudentId();

    }

}
