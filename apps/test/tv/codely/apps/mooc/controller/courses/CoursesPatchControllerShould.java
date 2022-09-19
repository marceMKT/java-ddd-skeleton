package tv.codely.apps.mooc.controller.courses;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.apps.mooc.controller.RequestTestCase;
import tv.codely.mooc.courses.domain.*;

public final class CoursesPatchControllerShould extends RequestTestCase {

    @Autowired
    CourseRepository repository;

    @Test
    void update_name_of_existing_course() throws Exception {

        Course course = new Course(new CourseId("some-id"), new CourseName("name"), new CourseDuration("duration"));
        repository.save(course);

        this.assertRequestWithBody(
            "PATCH",
            "/courses/some-id",
            "{\"name\": \"nameChange\"}",
            202
        );

        Assert.assertEquals(repository.search(new CourseId("some-id")).get().name().value(), "nameChange");
    }

    @Test
    void update_name_of_non_existing_course() throws Exception {
        this.assertRequestWithBody(
            "PATCH",
            "/courses/id2",
            "{\"name\": \"nameChange\"}",
            404
        );
    }
}
