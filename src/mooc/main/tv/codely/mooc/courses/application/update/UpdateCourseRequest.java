package tv.codely.mooc.courses.application.update;

public class UpdateCourseRequest {

    private final String id;
    private final String name;

    public UpdateCourseRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

}
