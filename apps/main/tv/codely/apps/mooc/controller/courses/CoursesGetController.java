package tv.codely.apps.mooc.controller.courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tv.codely.mooc.courses.application.search.CourseSearch;
import tv.codely.mooc.courses.domain.Course;

import java.util.Optional;

@RestController
public class CoursesGetController {
    private final CourseSearch search;

    public CoursesGetController(CourseSearch search) {
        this.search = search;
    }

    @GetMapping(value = "/courses/last")
    public ResponseEntity<Response> lastCourse() {
        Optional<Course> lastCourse = this.search.last();
        if (lastCourse.isPresent()){
            Course course = lastCourse.get();
            return ResponseEntity.ok(new Response(course.id(), course.name(), course.duration()));
        }
        return ResponseEntity.noContent().build();
    }
}

final class Response {
    private String id;
    private String name;
    private String duration;

    Response (String id, String name, String duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDuration() {
        return duration;
    }
}
