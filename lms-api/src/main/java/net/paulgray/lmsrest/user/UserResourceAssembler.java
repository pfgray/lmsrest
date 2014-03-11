/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author pfgray
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    /**
     * Takes the given person object and assembles a PersonResource with
     * relevant links
     *
     * @param person
     * @return PersonResource
     */
    @Override
    public UserResource toResource(User user) {
        UserResource userResource = new UserResource();
        userResource.add(linkTo(UserController.class).slash(user.getId()).withRel("self"));
        populateResource(userResource, user);
        addLinks(userResource, user);
//      return personResource;
        return userResource;
    }

    /**
     * Populate PersonResource fields with corresponding values from the person
     *
     * @param person
     * @return PersonResource
     */
    private void populateResource(UserResource userResource, User user) {
        userResource.id = user.getId();
        userResource.username = user.getUsername();
        userResource.lastName = user.getLastName();
        userResource.firstName = user.getFirstName();
        userResource.mi = user.getMi();
        userResource.lastLogin = user.getLastLogin();
        userResource.birthDate = user.getBirthDate();
        userResource.systemRole = user.getSystemRole();
        userResource.email = user.getEmail();
        userResource.locale = user.getLocale();
    }

    /**
     * Add the appropriate links for a PersonResource
     *
     * @param personResource to add the links to
     * @param person containing information needed for linking
     */
    private void addLinks(UserResource userResource, User user) {
        // Links to other personController mappings
        
        //userResource.add(linkTo(UserController.class).slash(user.getId()).slash("enrollments").withRel("enrollments"));
        //userResource.add(linkTo(UserController.class).slash(user.getId()).slash("grades").withRel("grades"));
        
//        userResource.add(linkTo(PersonController.class).slash(person).slash("fci-status").withRel("fci-status"));
//        userResource.add(linkTo(PersonController.class).slash(person).slash("addresses").withRel("addresses"));
//        userResource.add(linkTo(PersonController.class).slash(person).slash("phoneNumbers").withRel("phoneNumbers"));
    }
}