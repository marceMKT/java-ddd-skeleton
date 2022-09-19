package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.*;

import static org.mockito.Mockito.*;

final class CourseCreatorShould {
    @Test
    void create_a_valid_course() {
        CourseRepository repository = mock(CourseRepository.class);
        CourseNotification notification = mock(CourseNotification.class);
        CourseCreator creator = new CourseCreator(repository, notification);

        CourseId id = new CourseId("some-id");
        CourseName name = new CourseName("name");
        CourseDuration duration = new CourseDuration("duration");

        Course course = new Course(id, name, duration);

        creator.create(course);

        verify(repository, atLeastOnce()).save(course);
        verify(notification, atLeastOnce()).sendNotification(course);

    }
}
