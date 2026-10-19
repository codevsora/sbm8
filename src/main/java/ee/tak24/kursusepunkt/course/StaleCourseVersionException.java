package ee.tak24.kursusepunkt.course;

/**
 * Thrown when an update's {@code version} does not match the course's current version, meaning
 * someone else updated it first. Handled by {@link CourseExceptionHandler}.
 */
public class StaleCourseVersionException extends RuntimeException {

  public StaleCourseVersionException(Long id) {
    super("Course " + id + " was modified by someone else — refresh and try again.");
  }
}
