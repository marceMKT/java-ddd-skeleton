package tv.codely.mooc.courses.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class InMemoryCourseRepositoryShould {
    @Test
    void save_a_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        Course                   course     = new Course("id", "name", "duration");

        repository.save(course);
    }

    @Test
    void return_an_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        Course                   course     = new Course("id", "name", "duration");

        repository.save(course);

        assertEquals(Optional.of(course), repository.search(course.id()));
    }

    @Test
    void not_return_a_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.search("randomId").isPresent());
    }

    @Test
    void not_return_last_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.searchLast().isPresent());
    }

    @Test
    void return_last_of_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        Course courseFirst = new Course("id1", "name1", "duration1");
        Course courseLast = new Course("id2", "name2", "duration2");
        repository.save(courseFirst);
        repository.save(courseLast);

        assertEquals(Optional.of(courseLast), repository.searchLast());
    }
}
