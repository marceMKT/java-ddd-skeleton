package tv.codely.apps.mooc.controller.courses;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.find.FindAllCourseQuery;
import tv.codely.mooc.courses.domain.CoursesNotFound;
import tv.codely.shared.domain.DomainError;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.infrastructure.spring.ApiController;


@RestController
public class AllCoursesGetController extends ApiController{
    
    public AllCoursesGetController(QueryBus queryBus) {
        super(queryBus);
    }
    
    @GetMapping(value = "/courses/all")
    public ResponseEntity<HashMap<String, Serializable>> allCourses() throws QueryHandlerExecutionError{
        	
    	CoursesResponse response = ask(new FindAllCourseQuery());
    	
    	HashMap<String, Serializable> body = new HashMap<String, Serializable>();
        
        response.courses().forEach(course -> {
        	body.put(course.getId(), course);
        });
        
		return ResponseEntity.ok().body(body);

    }
    
    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends DomainError>, HttpStatus>() {
		{
            put(CoursesNotFound.class, HttpStatus.NOT_FOUND);
        }};
    }
}

