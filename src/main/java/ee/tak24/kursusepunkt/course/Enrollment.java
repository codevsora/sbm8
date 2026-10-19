package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.student.Student;
import jakarta.persistence.*;
import java.time.LocalDate;

// Many-to-many, modelled as a JOIN ENTITY instead of @ManyToMany: the link carries
// its own data (enrolledAt, grade), and a plain @ManyToMany cannot hold extra columns.
@Entity
@Table(name = "enrollment")
public class Enrollment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "student_id")
  private Student student;

  @Column(name = "enrolled_at", nullable = false)
  private LocalDate enrolledAt;

  private Integer grade;

  protected Enrollment() {}

  public Enrollment(Course course, Student student, LocalDate enrolledAt) {
    this.course = course;
    this.student = student;
    this.enrolledAt = enrolledAt;
  }

  public Long getId() {
    return id;
  }

  public Course getCourse() {
    return course;
  }

  public Student getStudent() {
    return student;
  }

  public LocalDate getEnrolledAt() {
    return enrolledAt;
  }

  public Integer getGrade() {
    return grade;
  }
}
