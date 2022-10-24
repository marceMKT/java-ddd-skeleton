package tv.codely.mooc.courses.infrastructure.persistence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class InMemoryCourseRepository implements CourseRepository {
    private Map<String, Course> courses = new LinkedHashMap<>();

    @Override
    public void save(Course course) {
        courses.put(course.id().value(), course);
    }

    @Override
    public void updateName(CourseId id, CourseName name) {
        Course course = courses.get(id.value());
        courses.put(id.value(), new Course(id, name, course.duration()));
    }

    public Optional<Course> search(CourseId id) {
        return Optional.ofNullable(courses.get(id.value()));
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

	@Override
	public List<Course> searchAll() {
		
        if (!courses.isEmpty()) {
            return new ArrayList<Course>(courses.values());
        }
        else {
            return new ArrayList<>();
        }
	}
}
