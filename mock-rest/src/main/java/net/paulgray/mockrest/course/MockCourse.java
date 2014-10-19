package net.paulgray.mockrest.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.paulgray.lmsrest.course.Course;
import net.paulgray.mockrest.user.MockUser;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pgray on 10/18/14.
 */
@Entity
@Table(name = "courses")
public class MockCourse extends Course {

    @Id
    @Column(name = "id")
    protected Integer id;
    @Column(name = "guid")
    protected String guid;
    @Column(name = "name")
    protected String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    protected List<MockUser> members;

    @Override
    public String getId() {
        return id == null ? null : id.toString();
    }

    @Override
    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
