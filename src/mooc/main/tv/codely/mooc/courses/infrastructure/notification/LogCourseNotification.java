package tv.codely.mooc.courses.infrastructure.notification;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseNotification;
import tv.codely.shared.domain.Service;

@Service
public class LogCourseNotification implements CourseNotification {

    @Override
    public void sendNotification(Course course) {
        System.out.println("New course create --> id: " + course.id() + "; name: " + course.name() + "; duration: " + course.duration());
    }
}
