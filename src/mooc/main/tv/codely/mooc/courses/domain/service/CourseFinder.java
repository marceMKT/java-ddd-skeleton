package tv.codely.mooc.courses.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.domain.CoursesNotFound;
import tv.codely.shared.domain.Service;

@Service
public class CourseFinder {

    private CourseRepository repository;

    public CourseFinder(CourseRepository repository){
        this.repository = repository;
    }

    public FindCourseResponse findOneById(String id) throws CourseNotExist {
        Optional<Course> courseFinded = repository.search(new CourseId(id));

        if (courseFinded.isPresent()){
            return new FindCourseResponse(courseFinded.get().id(), 
            		courseFinded.get().name(), 
            		courseFinded.get().duration());
        } else {
            throw new CourseNotExist(new CourseId(id));
        }
    }

    public FindCourseResponse last() throws CoursesNotFound {
        Optional<Course> courseFinded = this.repository.searchLast();

        if (courseFinded.isPresent()){
            return new FindCourseResponse(courseFinded.get().id(), 
            		courseFinded.get().name(), 
            		courseFinded.get().duration());
        } else {
            throw new CoursesNotFound();
        }

    }

	public CoursesResponse findAll() throws CoursesNotFound {
		List<Course> allCourses = this.repository.searchAll();
				
        if (!allCourses.isEmpty()){
        	List<CourseResponse> coursesResponse = new ArrayList<CourseResponse>();
        	allCourses.forEach(course -> coursesResponse.add(CourseResponse.fromAggregate(course)));
            return new CoursesResponse(coursesResponse);
        } else {
            throw new CoursesNotFound();
        }
	}
}
