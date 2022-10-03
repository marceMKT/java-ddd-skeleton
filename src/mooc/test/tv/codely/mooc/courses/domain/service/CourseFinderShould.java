package tv.codely.mooc.courses.domain.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.*;

import java.util.Optional;

import static org.mockito.Mockito.*;

final class CourseFinderShould {
    @Test
    void get_a_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseFinder finder = new CourseFinder(repository);

        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        when(repository.searchLast()).thenReturn(Optional.of(course));
        finder.last();

        verify(repository, atLeastOnce()).searchLast();
    }

    @Test
    void get_last_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseFinder finder = new CourseFinder(repository);
        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        when(repository.searchLast()).thenReturn(Optional.of(course));

        FindCourseResponse courseFinded = finder.last();

        Assert.assertEquals(courseFinded.id(), course.id().value());
        Assert.assertEquals(courseFinded.name(), course.name().value());
        Assert.assertEquals(courseFinded.duration(), course.duration().value());
    }
}
