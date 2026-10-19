package ee.tak24.kursusepunkt.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/** Payload for replacing an existing course's data. */
public record UpdateCourseRequest(
    @NotBlank String name, @NotBlank String gradingKey, @Positive int credits) {}
