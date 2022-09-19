package tv.codely.mooc.courses.domain.service;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.Optional;

@Service
public class CourseFinder {

    private CourseRepository repository;

    public CourseFinder(CourseRepository repository){
        this.repository = repository;
    }

    public FindCourseResponse findOneById(String id) throws Exception {
        Optional<Course> courseFinded = repository.search(new CourseId(id));

        if (courseFinded.isPresent()){
            return new FindCourseResponse(courseFinded.get().id(), courseFinded.get().name(), courseFinded.get().duration());
        }
        else{
            throw new Exception("Course not found");
        }
    }

    public FindCourseResponse last() throws Exception {
        Optional<Course> courseFinded = this.repository.searchLast();

        if (courseFinded.isPresent()){
            return new FindCourseResponse(courseFinded.get().id(), courseFinded.get().name(), courseFinded.get().duration());
        }
        else{
            throw new Exception("Courses not found");
        }

    }
}
