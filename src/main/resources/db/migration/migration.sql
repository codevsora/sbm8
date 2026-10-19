CREATE TABLE teacher (id BIGSERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL);
ALTER TABLE course ADD COLUMN teacher_id BIGINT REFERENCES teacher (id);

CREATE TABLE lesson (
    id BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL REFERENCES course (id),
    title VARCHAR(255) NOT NULL
);
CREATE INDEX idx_lesson_course_id ON lesson (course_id);

CREATE TABLE student (id BIGSERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL);

CREATE TABLE enrollment (
    id BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL REFERENCES course (id),
    student_id BIGINT NOT NULL REFERENCES student (id),
    enrolled_at DATE NOT NULL,
    grade INTEGER,
    UNIQUE (course_id, student_id)
);
CREATE INDEX idx_enrollment_course_id ON enrollment (course_id);