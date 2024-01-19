package one_to_many_and_back;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name="id")
private Long id;
@Column(name="name")
private String name;
@ManyToOne  // читаем слева направо: первое слово отностся к классу, в котором мы стоим (Student)
// т.е. студентов может быть много , но ссылаться могут только на 1 поле Университета
@JoinColumn(name="university_id")  // внешний ключ на Университет
private University university;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university=" + university +
                '}';
    }
}
