/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gray.paul.lmsrest.course;

import gray.paul.lmsdata.course.Course;
import gray.paul.lmsdata.course.CourseService;
import gray.paul.lmsrest.announcement.AnnouncementResourceAssembler;
import gray.paul.lmsdata.announcement.AnnouncementService;
import gray.paul.lmsrest.discussion.board.DiscussionBoardResourceAssembler;
import gray.paul.lmsdata.discussion.DiscussionService;
import gray.paul.lmsrest.enrollment.EnrollmentResourceAssembler;
import gray.paul.lmsdata.enrollment.EnrollmentService;
import gray.paul.lmsdata.user.User;
import gray.paul.lmsdata.user.UserService;
import gray.paul.lmsrest.web.ContextUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pfgray
 */
@Controller
@RequestMapping(value = CourseController.PATH)
public class CourseController {

    public static final String PATH = "courses";
    @Autowired
    CourseService courseService;
    @Autowired
    EnrollmentService enrollmentService;
    @Autowired
    EnrollmentResourceAssembler enrollmentResourceAssembler;
    @Autowired
    CourseResourceAssembler courseResourceAssembler;
    @Autowired
    UserService userService;
    @Autowired
    DiscussionService discussionBoardService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    AnnouncementResourceAssembler announcementResourceAssembler;
    @Autowired
    DiscussionBoardResourceAssembler discussionBoardResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCourses(@ContextUser User user) {
        List<Course> courses = courseService.getCoursesForUser(user);
        return new ResponseEntity<List<CourseResource>>(courseResourceAssembler.toResources(courses), HttpStatus.OK);
    }
/*
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{course}/announcements")
    public ResponseEntity getAnnouncementsForCourse(@ContextUser User user, @PathVariable Course course) {
        List<Announcement> announcements = announcementService.getAnnouncementsForCourseAndUser(course, user);
        return new ResponseEntity<List<AnnouncementResource>>(announcementResourceAssembler.toResources(announcements), HttpStatus.OK);
    }
*/
    
    
    //we should move these controller methods to an admin section:
    /*
     @RequestMapping(method = RequestMethod.GET, value = "/{courseId}")
     public ResponseEntity getCourse(ModelMap model, @PathVariable String courseId) {
     Course course = courseService.getCourseForId(courseId);
     if (course == null) {
     return new ResponseEntity(HttpStatus.NOT_FOUND);
     } else {
     return new ResponseEntity<CourseResource>(courseResourceAssembler.toResource(course), HttpStatus.OK);
     }
     }

     @RequestMapping(method = RequestMethod.GET, value = "/{courseId}/enrollments")
     public ResponseEntity getCourseEnrollments(ModelMap model, @PathVariable String courseId) {
     Course course = courseService.getCourseForId(courseId);
     if (course == null) {
     return new ResponseEntity(HttpStatus.NOT_FOUND);
     } else {
     List<Enrollment> enrollments = enrollmentService.getEnrollmentsForCourse(course);
     return new ResponseEntity<List<EnrollmentResource>>(enrollmentResourceAssembler.toResources(enrollments), HttpStatus.OK);
     }
     }
     * */
}