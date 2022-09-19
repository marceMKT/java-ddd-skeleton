package tv.codely.mooc.courses.application.update;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.domain.service.CourseFinder;

import java.util.Optional;

import static org.mockito.Mockito.*;

final class CourseUpdaterShould {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    void update_a_valid_course() throws Exception {
        CourseFinder finder = mock(CourseFinder.class);
        CourseRepository repository = mock(CourseRepository.class);
        CourseUpdater updater = new CourseUpdater(repository, finder);

        String id = "some-id";
        String name = "name";
        String duration = "duration";
        Course course = new Course(id, name, duration);

        when(finder.findOneById(anyString())).thenReturn(Optional.of(course));
        doNothing().when(repository).updateName(anyString(), anyString());

        String nameChange = "nameChange";
        updater.update(course.id(), nameChange);
        verify(repository, atLeastOnce()).updateName(id, nameChange);

    }

    @Test
    void update_not_existing_course() throws Exception {
        CourseFinder finder = mock(CourseFinder.class);
        CourseRepository repository = mock(CourseRepository.class);
        CourseUpdater updater = new CourseUpdater(repository, finder);
        String id = "some-id";
        when(finder.findOneById(id)).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            updater.update("some-id", "nameChange");
        });

        Assertions.assertEquals("Course not exists", thrown.getMessage());

    }
}
