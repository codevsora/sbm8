package ee.tak24.kursusepunkt.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/** Payload for creating a new course. */
public record CreateCourseRequest(
    @NotBlank String name, @NotBlank String gradingKey, @Positive int credits) {}
