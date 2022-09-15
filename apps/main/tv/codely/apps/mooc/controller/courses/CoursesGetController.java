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
            Response response = new Response(course.id(), course.name(), course.duration());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }
}

class Response {
    private String id;
    private String name;
    private String duration;

    Response (String id, String name, String duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
