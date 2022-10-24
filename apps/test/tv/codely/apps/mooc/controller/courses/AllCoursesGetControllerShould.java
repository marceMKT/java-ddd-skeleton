package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tv.codely.apps.mooc.MoocApplicationTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;

public final class AllCoursesGetControllerShould extends MoocApplicationTestCase {

    @Autowired
    CourseRepository repository;

    @Test
    void get_a_valid_non_existing_courses() throws Exception {
        this.assertResponse(
            "/courses/all",
            404,
            "{"
            + "    \"error_code\": \"course_not_found\","
            + "    \"message\": \"Not exists any course in database\""
            + "}"
        );
    }

    @Test
    void get_a_valid_all_course() throws Exception {
        repository.save(new Course(
                new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5"),
                new CourseName("name1"),
                new CourseDuration("duration1")));

        repository.save(new Course(
                new CourseId("dac0764a-640d-4991-8eb8-baaa89e06a73"),
                new CourseName("name2"),
                new CourseDuration("duration2")));
        
        this.assertResponse(
            "/courses/all",
            200,
            "{"
            + "    \"decf33ca-81a7-419f-a07a-74f214e928e5\": {"
            + "        \"id\": \"decf33ca-81a7-419f-a07a-74f214e928e5\","
            + "        \"name\": \"name1\","
            + "        \"duration\": \"duration1\""
            + "    },"
            + "    \"dac0764a-640d-4991-8eb8-baaa89e06a73\": {"
            + "        \"id\": \"dac0764a-640d-4991-8eb8-baaa89e06a73\","
            + "        \"name\": \"name2\","
            + "        \"duration\": \"duration2\""
            + "    }"
            + "}"
        );
    }
}
