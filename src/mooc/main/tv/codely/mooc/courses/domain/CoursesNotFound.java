package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.DomainError;

public final class CoursesNotFound extends DomainError {

	private static final long serialVersionUID = 1L;

	public CoursesNotFound() {
        super("course_not_found", "Not exists any course in database");
    }
}