package tv.codely.mooc.courses.domain.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

final class CourseFinderShould {
    @Test
    void get_a_valid_course() {
        CourseRepository repository = mock(CourseRepository.class);
        CourseFinder finder = new CourseFinder(repository);

        finder.last();

        verify(repository, atLeastOnce()).searchLast();
    }

    @Test
    void get_last_valid_course() {
        CourseRepository repository = mock(CourseRepository.class);
        CourseFinder finder = new CourseFinder(repository);
        Course course = new Course("id", "name", "duration");
        when(repository.searchLast()).thenReturn(Optional.of(course));

        Optional<Course> courseFinded = finder.last();

        Assert.assertEquals(courseFinded.get().id(), course.id());
        Assert.assertEquals(courseFinded.get().name(), course.name());
        Assert.assertEquals(courseFinded.get().duration(), course.duration());
    }
}
