package tv.codely.mooc.courses.infrastructure.notification;

import tv.codely.mooc.courses.domain.CourseNotification;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;

import java.util.List;

@Service
public class LogCourseNotification implements CourseNotification {

    @Override
    public void sendNotification(List<DomainEvent> domainEvents) {
        domainEvents.forEach(domainEvent -> System.out.println(domainEvent.toPrimitives()));
    }
}
