package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.teacher.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120)
  private String name;

  @Column(name = "grading_key", nullable = false, length = 40)
  private String gradingKey;

  @Column(nullable = false)
  private int credits;

  @Version private long version;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @OneToMany(mappedBy = "course")
  private List<Lesson> lessons = new ArrayList<>();

  @OneToMany(mappedBy = "course")
  private List<Enrollment> enrollments = new ArrayList<>();

  protected Course() {}

  public Course(String name, String gradingKey, int credits) {
    this.name = name;
    this.gradingKey = gradingKey;
    this.credits = credits;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGradingKey() {
    return gradingKey;
  }

  public void setGradingKey(String gradingKey) {
    this.gradingKey = gradingKey;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public long getVersion() {
    return version;
  }

  // NEW getters/setters
  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public List<Lesson> getLessons() {
    return lessons;
  }

  public List<Enrollment> getEnrollments() {
    return enrollments;
  }
}
