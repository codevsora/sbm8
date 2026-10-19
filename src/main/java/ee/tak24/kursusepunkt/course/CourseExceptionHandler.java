package ee.tak24.kursusepunkt.course;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Translates course-related exceptions into RFC 7807 problem responses. */
@RestControllerAdvice
public class CourseExceptionHandler {

  /** 404 — the requested course does not exist. */
  @ExceptionHandler(CourseNotFoundException.class)
  public ProblemDetail handleNotFound(CourseNotFoundException ex) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problem.setTitle("Course not found");
    problem.setType(URI.create("https://kursusepunkt.tak24.ee/errors/course-not-found"));
    return problem;
  }

  /** 409 — someone else updated this course first; the version is stale. */
  @ExceptionHandler(StaleCourseVersionException.class)
  public ProblemDetail handleConflict(StaleCourseVersionException ex) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    problem.setTitle("Stale version");
    problem.setType(URI.create("https://kursusepunkt.tak24.ee/errors/stale-version"));
    return problem;
  }

  /** 400 — the request body failed validation. */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
    ProblemDetail problem =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "One or more fields are invalid.");
    problem.setTitle("Validation failed");
    problem.setType(URI.create("https://kursusepunkt.tak24.ee/errors/validation-failed"));
    problem.setProperty(
        "errors",
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList());
    return problem;
  }
}
