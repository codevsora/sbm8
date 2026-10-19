package ee.tak24.kursusepunkt.course;

import jakarta.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @ManyToOne: many lessons belong to one course. Owning side, holds course_id.
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "course_id")
  private Course course;

  @Column(nullable = false)
  private String title;

  protected Lesson() {}

  public Lesson(Course course, String title) {
    this.course = course;
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public Course getCourse() {
    return course;
  }

  public String getTitle() {
    return title;
  }
}
