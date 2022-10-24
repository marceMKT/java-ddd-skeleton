package tv.codely.mooc.courses.domain.service;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;

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
    
    @Test
    void get_all_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseFinder finder = new CourseFinder(repository);
        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

		when(repository.searchAll()).thenReturn(Arrays.asList(course));

        CoursesResponse coursesResponse = finder.findAll();
        
        Assert.assertEquals(coursesResponse.courses().get(0).getId(), course.id().value());
        Assert.assertEquals(coursesResponse.courses().get(0).getName(), course.name().value());
        Assert.assertEquals(coursesResponse.courses().get(0).getDuration(), course.duration().value());
    }
}
