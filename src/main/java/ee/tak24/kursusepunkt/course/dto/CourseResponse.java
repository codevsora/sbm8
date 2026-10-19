package ee.tak24.kursusepunkt.course.dto;

/** Representation of a course returned to clients. */
public record CourseResponse(Long id, String name, String gradingKey, int credits) {}
