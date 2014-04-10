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

    public BbPerson(User user, String role) {
        //standard info
        if(user != null){
            this.id = user.getId().getExternalString();
            this.given_name = user.getGivenName();
            this.family_name = user.getFamilyName();
            this.username = user.getUserName();
            this.email = user.getEmailAddress();
            this.role = role;

            //bb info
            this.department = user.getDepartment();
            this.address = user.getStreet1() + " " + user.getStreet2() + ", " + user.getCity() + ", " + user.getState() + " " + user.getZipCode();
            this.phone = user.getMobilePhone();
            this.uniqueId = user.getStudentId();
        }

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BbPerson other = (BbPerson) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

    
    
    

}
