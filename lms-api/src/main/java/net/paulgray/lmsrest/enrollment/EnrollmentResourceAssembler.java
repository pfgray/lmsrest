/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.lmsrest.enrollment;

import net.paulgray.lmsrest.course.CourseController;
import net.paulgray.lmsrest.user.UserController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.stereotype.Component;

/**
 *
 * @author pfgray
 */
@Component
public class EnrollmentResourceAssembler extends ResourceAssemblerSupport<Enrollment, EnrollmentResource> {

    public EnrollmentResourceAssembler() {
        super(EnrollmentController.class, EnrollmentResource.class);
    }

    /**
     * Takes the given person object and assembles a PersonResource with
     * relevant links
     *
     * @param enrollment
     * @return EnrollmentResource
     */
    @Override
    public EnrollmentResource toResource(Enrollment enrollment) {
        EnrollmentResource enrollmentResource = new EnrollmentResource();
        enrollmentResource.add(linkTo(EnrollmentController.class).slash(enrollment.getId()).withRel("self"));
        populateResource(enrollmentResource, enrollment);
        addLinks(enrollmentResource, enrollment);
        return enrollmentResource;
    }

    /**
     * Populate PersonResource fields with corresponding values from the person
     *
     * @param enrollment
     * @return EnrollmentResource
     */
    private void populateResource(EnrollmentResource enrollmentResource, Enrollment enrollment) {
        enrollmentResource.courseId = enrollment.getCourseId();
        enrollmentResource.enrollmentDate = enrollment.getEnrollmentDate();
        enrollmentResource.id = enrollment.getId();
        enrollmentResource.status = enrollment.getStatus();
        enrollmentResource.userId = enrollment.getUserId();
        enrollmentResource.role = enrollment.getRole();
    }

    /**
     * Add the appropriate links for a EnrollmentResource
     *
     * @param enrollmentResource to add the links to
     * @param enrollment containing information needed for linking
     */
    private void addLinks(EnrollmentResource enrollmentResource, Enrollment enrollment) {
        // Links to other personController mappings
        enrollmentResource.add(linkTo(UserController.class).slash(enrollment.getUserId()).withRel("user"));
        enrollmentResource.add(linkTo(CourseController.class).slash(enrollment.getCourseId()).withRel("course"));

//        userResource.add(linkTo(PersonController.class).slash(person).slash("fci-status").withRel("fci-status"));
//        userResource.add(linkTo(PersonController.class).slash(person).slash("addresses").withRel("addresses"));
//        userResource.add(linkTo(PersonController.class).slash(person).slash("phoneNumbers").withRel("phoneNumbers"));
    }
}
