package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.service.CourseFinder;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
public final class FindAllCourseQueryHandler implements QueryHandler<FindAllCourseQuery, CoursesResponse> {
    private final CourseFinder finder;

    public FindAllCourseQueryHandler(CourseFinder finder) {
        this.finder = finder;
    }

    @Override
    public CoursesResponse handle(FindAllCourseQuery query) throws CourseNotExist {
        return finder.findAll();
    }
}