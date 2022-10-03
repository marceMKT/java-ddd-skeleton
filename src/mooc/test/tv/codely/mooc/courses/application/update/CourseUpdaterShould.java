package tv.codely.mooc.courses.application.update;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import tv.codely.mooc.courses.domain.*;
import tv.codely.mooc.courses.domain.service.CourseFinder;
import tv.codely.mooc.courses.domain.service.FindCourseResponse;

import static org.mockito.Mockito.*;

final class CourseUpdaterShould {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    void update_a_valid_course() throws Exception {
        CourseFinder finder = mock(CourseFinder.class);
        CourseRepository repository = mock(CourseRepository.class);
        CourseUpdater updater = new CourseUpdater(repository, finder);

        CourseId id = new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        FindCourseResponse course = new FindCourseResponse(id, name, duration);

        when(finder.findOneById(anyString())).thenReturn(course);
        doNothing().when(repository).updateName(any(), any());

        String nameChange = "nameChange";
        updater.update(course.id(), nameChange);
        verify(repository, atLeastOnce()).updateName(id, new CourseName(nameChange));

    }

    @Test
    void update_not_existing_course() throws Exception {
        CourseFinder finder = mock(CourseFinder.class);
        CourseRepository repository = mock(CourseRepository.class);
        CourseUpdater updater = new CourseUpdater(repository, finder);
        String id = "decf33ca-81a7-419f-a07a-74f214e928e5";
        when(finder.findOneById(id)).thenThrow(new Exception("Course not exists"));

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            updater.update(id, "nameChange");
        });

        Assertions.assertEquals("Course not exists", thrown.getMessage());

    }
}
