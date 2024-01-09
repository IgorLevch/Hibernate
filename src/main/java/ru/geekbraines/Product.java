package ru.geekbraines;

import jakarta.persistence.*;

@Entity    // одно из требований - чтобы объекты класса могли мапиться в БД и обратно -- они должны быть сущностями
@Table(name="products")  //  с пом-ю этой аннотации помечаю, что я хочу, чтобы объекты этого класса жили в таблице users
// (лучше указывать в явном виде)
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //стратегия Identity означает, что уникальные значения праймери кей будет генерить БД
    @Column(name="id")  // какому столбцу соответствует этот первичный ключ
    private Long id;  // это первичный ключ . Он обязательно должен быть. И Хибернейт на него ориентируется.

    @Column(name="title", length = 128, unique = true, nullable = false)
    private String title;

    @Column(name="price")
    private Integer Price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }  // c пом-ю геттеров отправляет объект в базу

    public void setTitle(String title) {
        this.title = title;
    }   // с пом-ю сеттеров достает объект из базы


    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Product() {
    }

    public Product(String title, Integer price) {

        this.title = title;
        Price = price;
    }

    // использует дефолтный конструктор, чтобы создать базовый объект


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Price=" + Price +
                '}';
    }
}
