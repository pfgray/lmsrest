/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.bbrest.user;

import gray.paul.lmsdata.user.User;

/**
 *
 * @author pfgray
 */
public class BbUser extends User {
    
    public BbUser(blackboard.data.user.User user){
        this.id = user.getId().getExternalString();
        this.username = user.getUserName();
        this.lastName = user.getFamilyName();
        this.firstName = user.getGivenName();
        if (user.getMiddleName() != null && user.getMiddleName().length() > 0) {
            this.mi = user.getMiddleName().substring(0, 1);
        }
        if (user.getLastLoginDate() != null) {
            this.lastLogin = user.getLastLoginDate().getTime();
        }
        if (user.getBirthDate() != null) {
            this.birthDate = user.getBirthDate().getTime();
        }
        if(user.getSystemRole() != null){
            this.systemRole = user.getSystemRole().getDisplayName();
        }

        this.email = user.getEmailAddress();
        this.locale = user.getLocale();
        
    }
    
}
