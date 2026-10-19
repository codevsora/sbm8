package ee.tak24.kursusepunkt.student;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;

    protected Student() {}
    public Student(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}