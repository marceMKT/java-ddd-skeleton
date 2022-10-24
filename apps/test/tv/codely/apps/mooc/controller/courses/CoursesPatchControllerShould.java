package tv.codely.apps.mooc.controller.courses;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tv.codely.apps.mooc.MoocApplicationTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;

public final class CoursesPatchControllerShould extends MoocApplicationTestCase {

    @Autowired
    CourseRepository repository;

    @Test
    void update_name_of_existing_course() throws Exception {

        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        Course course = new Course(
            id,
            new CourseName("name"),
            new CourseDuration("duration"));
        repository.save(course);

        this.assertRequestWithBody(
            "PATCH",
            "/courses/" + id.value(),
            "{\"name\": \"nameChange\"}",
            202
        );

        Assert.assertEquals(repository.search(id).get().name().value(), "nameChange");
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
