package tv.codely.mooc.courses.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;

final class InMemoryCourseRepositoryShould {
    @Test
    void save_a_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        repository.save(course);
    }

    @Test
    void return_an_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        repository.save(course);

        assertEquals(Optional.of(course), repository.search(course.id()));
    }

    @Test
    void not_return_a_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.search(new CourseId("decf33ca-81a7-419f-a07a-74f214e92899")).isPresent());
    }

    @Test
    void not_return_last_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.searchLast().isPresent());
    }

    @Test
    void return_last_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name1");
        CourseDuration duration = new CourseDuration("duration1");
        Course courseFirst = new Course(id, name, duration);

        Course courseLast = new Course(new CourseId("decf33ca-81a7-419f-a07a-74f214e92123"), new CourseName("name2"), new CourseDuration("duration2"));

        repository.save(courseFirst);
        repository.save(courseLast);

        assertEquals(Optional.of(courseLast), repository.searchLast());
    }
    
    @Test
    void not_return_all_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertTrue(repository.searchAll().isEmpty());
    }

    @Test
    void return_all_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name1");
        CourseDuration duration = new CourseDuration("duration1");
        Course courseFirst = new Course(id, name, duration);

        Course courseLast = new Course(new CourseId("decf33ca-81a7-419f-a07a-74f214e92123"), new CourseName("name2"), new CourseDuration("duration2"));

        repository.save(courseFirst);
        repository.save(courseLast);

        List<Course> searchAll = repository.searchAll();
		assertEquals(2, searchAll.size());
        assertEquals(courseFirst.id().value(), searchAll.get(0).id().value());
        assertEquals(courseFirst.name().value(), searchAll.get(0).name().value());
        assertEquals(courseFirst.duration().value(), searchAll.get(0).duration().value());
    }
    
}
