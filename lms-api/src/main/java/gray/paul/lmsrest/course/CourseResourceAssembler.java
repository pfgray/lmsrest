/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.course;

import gray.paul.lmsdata.course.Course;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.stereotype.Component;

/**
 *
 * @author pfgray
 */
@Component
public class CourseResourceAssembler extends ResourceAssemblerSupport<Course, CourseResource> {

    public CourseResourceAssembler() {
        super(CourseController.class, CourseResource.class);
    }

    /**
     * Takes the given person object and assembles a PersonResource with
     * relevant links
     *
     * @param person
     * @return PersonResource
     */
    @Override
    public CourseResource toResource(Course course) {
        CourseResource courseResource = new CourseResource();
        courseResource.add(linkTo(CourseController.class).slash(course.getId()).withRel("self"));
        populateResource(courseResource, course);
        addLinks(courseResource, course);
//      return personResource;
        return courseResource;
    }

    /**
     * Populate PersonResource fields with corresponding values from the person
     *
     * @param person
     * @return PersonResource
     */
    private void populateResource(CourseResource courseResource, Course course) {
        courseResource.id = course.getId();
        courseResource.courseId = course.getCourseId();
        courseResource.description = course.getDescription();
        courseResource.name = course.getName();
    }

    /**
     * Add the appropriate links for a PersonResource
     *
     * @param personResource to add the links to
     * @param person containing information needed for linking
     */
    private void addLinks(CourseResource courseResource, Course course) {
        // Links to other personController mappings
        //courseResource.add(linkTo(CourseController.class).slash(course.getId()).slash("enrollments").withRel("enrollments"));
        
        courseResource.add(linkTo(CourseController.class).slash(course.getId()).slash("announcements").withRel("announcements"));
        courseResource.add(linkTo(CourseController.class).slash(course.getId()).slash("discussion_boards").withRel("discussion_boards"));
        
//        userResource.add(linkTo(PersonController.class).slash(person).slash("fci-status").withRel("fci-status"));
//        userResource.add(linkTo(PersonController.class).slash(person).slash("addresses").withRel("addresses"));
//        userResource.add(linkTo(PersonController.class).slash(person).slash("phoneNumbers").withRel("phoneNumbers"));
    }
}
