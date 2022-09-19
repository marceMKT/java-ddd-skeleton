package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.domain.service.CourseFinder;
import tv.codely.shared.domain.Service;

import java.util.Optional;

@Service
public class CourseUpdater {
    private CourseRepository repository;
    private CourseFinder finder;

    public CourseUpdater (CourseRepository repository, CourseFinder finder){
        this.repository = repository;
        this.finder = finder;
    }

    public void update(String id, String name) throws Exception{
        Optional<Course> course = finder.findOneById(id);
        if (course.isPresent()){
            repository.updateName(id, name);
        }
        else{
            throw new Exception("Course not exists");
        }

    }
}
