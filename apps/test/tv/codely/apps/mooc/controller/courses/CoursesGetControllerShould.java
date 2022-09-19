package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.apps.mooc.controller.RequestTestCase;
import tv.codely.mooc.courses.domain.*;
import tv.codely.mooc.courses.infrastructure.persistence.InMemoryCourseRepository;

public final class CoursesGetControllerShould extends RequestTestCase {

    @Autowired
    CourseRepository repository;

    @Test
    void get_a_valid_non_existing_course() throws Exception {
        this.assertResponse(
            "/courses/last",
            204,
            ""
        );
    }

    @Test
    void get_a_valid_last_course() throws Exception {
        Course course = new Course(new CourseId("some-id"), new CourseName("name"), new CourseDuration("duration"));
        repository.save(course);

        this.assertResponse(
            "/courses/last",
            200,
            "{\"name\": \"name\", \"duration\": \"duration\"}"
        );
    }
}
