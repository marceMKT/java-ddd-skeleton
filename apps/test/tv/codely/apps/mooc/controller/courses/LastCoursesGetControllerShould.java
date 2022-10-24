package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tv.codely.apps.mooc.MoocApplicationTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;

public final class LastCoursesGetControllerShould extends MoocApplicationTestCase {

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
        Course course = new Course(
            new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5"),
            new CourseName("name"),
            new CourseDuration("duration"));
        repository.save(course);

        this.assertResponse(
            "/courses/last",
            200,
            "{\"name\": \"name\", \"duration\": \"duration\"}"
        );
    }
}
