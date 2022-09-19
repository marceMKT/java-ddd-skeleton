package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.domain.service.CourseSearch;
import tv.codely.shared.domain.Service;

import java.util.Optional;

@Service
public class CourseUpdater {
    private CourseRepository repository;
    private CourseSearch search;

    public CourseUpdater (CourseRepository repository, CourseSearch search){
        this.repository = repository;
        this.search = search;
    }

    public void update(String id, String name) throws Exception{
        Optional<Course> course = search.findOneById(id);
        if (course.isPresent()){
            repository.updateName(id, name);
        }
        else{
            throw new Exception("Course not exists");
        }

    }
}
