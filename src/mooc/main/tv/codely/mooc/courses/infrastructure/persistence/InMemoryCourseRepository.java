package tv.codely.mooc.courses.infrastructure.persistence;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.*;

@Service
public final class InMemoryCourseRepository implements CourseRepository {
    private Map<String, Course> courses = new LinkedHashMap<>();

    @Override
    public void save(Course course) {
        courses.put(course.id(), course);
    }

    public Optional<Course> search(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public Optional<Course> searchLast() {
        List<Map.Entry<String, Course>> entryList =
            new ArrayList<Map.Entry<String, Course>>(courses.entrySet());
        if (entryList.size() > 0) {
            return Optional.of(entryList.get(entryList.size() - 1).getValue());
        }
        else {
            return Optional.empty();
        }
    }
}
