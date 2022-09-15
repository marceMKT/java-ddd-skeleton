package tv.codely.mooc.courses.application.search;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.Optional;

@Service
public class CourseSearch {

    private CourseRepository repository;

    public CourseSearch(CourseRepository repository) {
        this.repository = repository;
    }

    public Optional<Course> last() {
        return this.repository.searchLast();
    }
}