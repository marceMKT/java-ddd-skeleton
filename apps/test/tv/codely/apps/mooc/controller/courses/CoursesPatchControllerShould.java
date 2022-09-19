package tv.codely.apps.mooc.controller.courses;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.apps.mooc.controller.RequestTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;

public final class CoursesPatchControllerShould extends RequestTestCase {

    @Autowired
    CourseRepository repository;

    @Test
    void update_name_of_existing_course() throws Exception {

        Course course = new Course("id1", "name1", "duration1");
        repository.save(course);

        this.assertRequestWithBody(
            "PATCH",
            "/courses/id1",
            "{\"name\": \"nameChange\"}",
            202
        );

        Assert.assertEquals(repository.search("id1").get().name(), "nameChange");
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
