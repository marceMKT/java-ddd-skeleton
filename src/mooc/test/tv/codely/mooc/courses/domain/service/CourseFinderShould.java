package tv.codely.mooc.courses.domain.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.*;

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
        CourseId id = new CourseId("some-id");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        when(repository.searchLast()).thenReturn(Optional.of(course));

        Optional<Course> courseFinded = finder.last();

        Assert.assertEquals(courseFinded.get().id().value(), course.id().value());
        Assert.assertEquals(courseFinded.get().name().value(), course.name().value());
        Assert.assertEquals(courseFinded.get().duration().value(), course.duration().value());
    }
}
