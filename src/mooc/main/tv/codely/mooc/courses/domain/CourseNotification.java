package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.bus.event.DomainEvent;

import java.util.List;

public interface CourseNotification {

    void sendNotification(List<DomainEvent> domainEvents);
}
