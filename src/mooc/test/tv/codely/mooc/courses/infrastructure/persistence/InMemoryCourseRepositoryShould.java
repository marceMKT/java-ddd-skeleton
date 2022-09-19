package tv.codely.mooc.courses.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class InMemoryCourseRepositoryShould {
    @Test
    void save_a_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        CourseId id = new CourseId("some-id");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        repository.save(course);
    }

    @Test
    void return_an_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        CourseId id = new CourseId("some-id");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        repository.save(course);

        assertEquals(Optional.of(course), repository.search(course.id()));
    }

    @Test
    void not_return_a_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.search(new CourseId("randomId")).isPresent());
    }

    @Test
    void not_return_last_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.searchLast().isPresent());
    }

    @Test
    void return_last_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        CourseId id = new CourseId("some-id1");
        CourseName name = new CourseName("name1");
        CourseDuration duration = new CourseDuration("duration1");
        Course courseFirst = new Course(id, name, duration);

        Course courseLast = new Course(new CourseId("some-id2"), new CourseName("name2"), new CourseDuration("duration2"));

        repository.save(courseFirst);
        repository.save(courseLast);

        assertEquals(Optional.of(courseLast), repository.searchLast());
    }
}
