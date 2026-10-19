package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.course.dto.CourseResponse;
import ee.tak24.kursusepunkt.course.dto.CreateCourseRequest;
import ee.tak24.kursusepunkt.course.dto.UpdateCourseRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST endpoints for managing courses. */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  /** 200 OK — list all courses. */
  @GetMapping
  public List<CourseResponse> findAll() {
    return courseService.findAll();
  }

  /** 200 OK, or 404 if the course does not exist. */
  @GetMapping("/{id}")
  public CourseResponse findById(@PathVariable Long id) {
    return courseService.findById(id);
  }

  /** 201 Created, with a Location header pointing at the new resource. */
  @PostMapping
  public ResponseEntity<CourseResponse> create(@Valid @RequestBody CreateCourseRequest request) {
    CourseResponse created = courseService.create(request);
    URI location = URI.create("/api/courses/" + created.id());
    return ResponseEntity.created(location).body(created);
  }

  /** 200 OK, or 404 if the course does not exist. */
  @PutMapping("/{id}")
  public CourseResponse update(
      @PathVariable Long id, @Valid @RequestBody UpdateCourseRequest request) {
    return courseService.update(id, request);
  }

  /** 204 No Content, or 404 if the course does not exist. */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    courseService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
