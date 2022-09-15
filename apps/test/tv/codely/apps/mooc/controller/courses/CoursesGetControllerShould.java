package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.apps.mooc.controller.RequestTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
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
        Course course = new Course("id1", "name1", "duration1");
        repository.save(course);

        this.assertResponse(
            "/courses/last",
            200,
            "{\"name\": \"name1\", \"duration\": \"duration1\"}"
        );
    }
}
