package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.DomainError;

public final class CourseNotExist extends DomainError {

	private static final long serialVersionUID = 1L;

	public CourseNotExist(CourseId id) {
        super("course_not_exist", String.format("The course <%s> doesn't exist", id.value()));
    }
}