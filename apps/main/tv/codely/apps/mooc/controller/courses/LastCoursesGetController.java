package tv.codely.apps.mooc.controller.courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.courses.domain.service.CourseFinder;
import tv.codely.mooc.courses.domain.service.FindCourseResponse;


@RestController
public class LastCoursesGetController {
    private final CourseFinder finder;
    
    public LastCoursesGetController(CourseFinder finder) {
        this.finder = finder;
    }

    @GetMapping(value = "/courses/last")
    public ResponseEntity<Response> lastCourse() {
        try {
            FindCourseResponse lastCourse = this.finder.last();
            Response response = new Response(lastCourse.id(), lastCourse.name(), lastCourse.duration());
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.noContent().build();
        }

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
