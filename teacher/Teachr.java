package ee.tak24.kursusepunkt.teacher;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;

    protected Teacher() {}
    public Teacher(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}