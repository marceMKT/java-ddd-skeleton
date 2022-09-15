package tv.codely.apps.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.controller.RequestTestCase;

public final class CoursesGetControllerShould extends RequestTestCase {
    @Test
    void get_a_valid_non_existing_course() throws Exception {
        this.assertResponse(
            "/courses/last",
            204,
            ""
        );
    }
}
