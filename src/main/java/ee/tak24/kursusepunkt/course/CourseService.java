package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.course.dto.CourseResponse;
import ee.tak24.kursusepunkt.course.dto.CreateCourseRequest;
import ee.tak24.kursusepunkt.course.dto.UpdateCourseRequest;
import java.util.List;
import org.springframework.stereotype.Service;

/** Business logic for courses. */
@Service
public class CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

  public List<CourseResponse> findAll() {
    return courseRepository.findAll().stream().map(courseMapper::toResponse).toList();
  }

  public CourseResponse findById(Long id) {
    return courseMapper.toResponse(getOrThrow(id));
  }

  public CourseResponse create(CreateCourseRequest request) {
    if (courseRepository.existsByNameIgnoreCase(request.name())) {
      throw new DuplicateCourseException(request.name());
    }
    Course course = courseMapper.toEntity(request);
    return courseMapper.toResponse(courseRepository.save(course));
  }

  public CourseResponse update(Long id, UpdateCourseRequest request) {
    Course course = getOrThrow(id);
    boolean nameChanged = !course.getName().equalsIgnoreCase(request.name());
    if (nameChanged && courseRepository.existsByNameIgnoreCase(request.name())) {
      throw new DuplicateCourseException(request.name());
    }
    courseMapper.applyUpdate(course, request);
    return courseMapper.toResponse(courseRepository.save(course));
  }

  public void delete(Long id) {
    if (!courseRepository.existsById(id)) {
      throw new CourseNotFoundException(id);
    }
    courseRepository.deleteById(id);
  }

  private Course getOrThrow(Long id) {
    return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
  }
}
