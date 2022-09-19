package tv.codely.apps.mooc.controller.courses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tv.codely.mooc.courses.application.update.CourseUpdater;

@RestController
public final class CoursesPatchController {
    private final CourseUpdater updater;

    public CoursesPatchController(CourseUpdater updater) {
        this.updater = updater;
    }

    @PatchMapping(value = "/courses/{id}")
    public ResponseEntity index(@PathVariable String id, @RequestBody RequestPatch requestPatch) {

        try {
            this.updater.update(id, requestPatch.getName());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

final class RequestPatch {
    private String name;

    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
}
