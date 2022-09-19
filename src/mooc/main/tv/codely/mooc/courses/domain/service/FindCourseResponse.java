package tv.codely.mooc.courses.domain.service;

import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;

public class FindCourseResponse {

    private final CourseId id;
    private final CourseName name;
    private final CourseDuration duration;

    public FindCourseResponse(CourseId id, CourseName name, CourseDuration duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String id() {
        return id.value();
    }

    public String name() {
        return name.value();
    }

    public String duration() {
        return duration.value();
    }
}
