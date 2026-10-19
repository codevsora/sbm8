package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.course.dto.CourseResponse;
import ee.tak24.kursusepunkt.course.dto.CreateCourseRequest;
import ee.tak24.kursusepunkt.course.dto.UpdateCourseRequest;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

  public Course toEntity(CreateCourseRequest request) {
    return new Course(request.name(), request.gradingKey(), request.credits());
  }

  public void applyUpdate(Course course, UpdateCourseRequest request) {
    course.setName(request.name());
    course.setGradingKey(request.gradingKey());
    course.setCredits(request.credits());
  }

  public CourseResponse toResponse(Course course) {
    return new CourseResponse(
        course.getId(), course.getName(), course.getGradingKey(), course.getCredits());
  }
}
