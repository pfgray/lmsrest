package net.paulgray.mockrest.course;

import net.paulgray.lmsrest.enrollment.Enrollment;
import net.paulgray.mockrest.user.MockUser;

import javax.persistence.*;

/**
 * Created by pgray on 10/18/14.
 */
@Entity
@Table(name = "enrollments")
public class MockEnrollment extends Enrollment {

    @Id
    @Column(name = "id")
    protected Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    protected MockUser user;
    @ManyToOne(fetch = FetchType.EAGER)
    protected MockCourse course;

    @Override
    public String getId() {
        return id == null ? null : id.toString();
    }

    @Override
    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public MockUser getUser() {
        return user;
    }

    public void setUser(MockUser user) {
        this.user = user;
    }

    public MockCourse getCourse() {
        return course;
    }

    public void setCourse(MockCourse course) {
        this.course = course;
    }
}
