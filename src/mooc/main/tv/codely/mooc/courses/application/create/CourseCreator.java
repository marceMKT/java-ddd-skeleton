package tv.codely.mooc.courses.application.create;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseNotification;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class CourseCreator {
    private CourseRepository repository;
    private CourseNotification notification;

    public CourseCreator(CourseRepository repository, CourseNotification notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void create(String id, String name, String duration) {
        Course course = new Course(id, name, duration);

        this.repository.save(course);
        this.notification.sendNotification(course);
    }
}
