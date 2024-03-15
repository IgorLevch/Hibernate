package one_to_many_and_back;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="universities")
public class University {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY ) // когда строится связь со многими объектами , то по умолчанию идет
    // такой параметр - fetch. Это способ загрузки. 2 вараинта: Eager и Lazy. Когда стоит коллекция, то по умолчанию идет LAZY.
    // Это значит, что мы достанем сам объект (университет), а коллекцию доставать не будем, пока о ней никто не попросит.
    // как только о ней попросят, тогда мы ее вторым запросом дозагрузимэ
    // EAGER делать не рекомендуется, т.к. при вытаскивании маленького объекта, будет вытягиваться пол.базы.
    // всегда , когда есть связь Many (ManytoOne иил OnetoMany) - по умолчанию стоит LAZY
    private List<Student> students;

    public University() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", students=" + students +
                '}';
    }
}
