package many_to_many;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name="title")
    private String title;

    @ManyToMany
    @JoinTable(
            name="books_readers",   // имя связующей таблицы
            joinColumns = @JoinColumn(name="book_id"), // это столбец , который ссылается на нас (Book)
            inverseJoinColumns = @JoinColumn(name="reader_id") // столбец, который ссылается на связаную с нам сущность (Reader)
    )
    private List<Reader> readers;


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

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", readers=" + readers +
                '}';
    }


    public Book() {
    }
}
