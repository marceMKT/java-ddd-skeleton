package tv.codely.mooc.courses.domain;

import java.util.Optional;

public interface CourseRepository {
    void save(Course course);

    void updateName(String id, String name);

    Optional<Course> search(String id);

    Optional<Course> searchLast();
}
