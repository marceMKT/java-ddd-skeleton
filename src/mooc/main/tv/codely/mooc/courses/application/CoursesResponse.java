package tv.codely.mooc.courses.application;

import java.util.List;

import tv.codely.shared.domain.bus.query.Response;

public final class CoursesResponse implements Response {
    private final List<CourseResponse> courses;
    
    public CoursesResponse(List<CourseResponse> courses) {
        this.courses = courses;
    }

    public List<CourseResponse> courses() {
        return courses;
    }
    
}
