package ee.tak24.kursusepunkt.course;

/**
 * Thrown when a course cannot be found by id. Handled by {@link
 * ee.tak24.kursusepunkt.common.GlobalExceptionHandler}.
 */
public class CourseNotFoundException extends RuntimeException {

  public CourseNotFoundException(Long id) {
    super("Course not found: " + id);
  }
}
