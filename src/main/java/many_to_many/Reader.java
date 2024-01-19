package many_to_many;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="readers")
public class Reader {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany  //слева направо -- много читателей (нас) ссылается на много книг (них)
    @JoinTable(
            name="books_readers",   // имя связующей таблицы
            joinColumns = @JoinColumn(name="reader_id"), // это столбец , который ссылается на нас (Reader)
            inverseJoinColumns = @JoinColumn(name="book_id") // столбец, который ссылается на связаную с нам сущность (Book)
    )
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
