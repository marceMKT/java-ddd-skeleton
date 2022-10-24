package tv.codely.mooc.courses.domain;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    void save(Course course);

    void updateName(CourseId id, CourseName name);

    Optional<Course> search(CourseId id);

    Optional<Course> searchLast();
    
    List<Course> searchAll();
}
