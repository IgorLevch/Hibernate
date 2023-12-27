package ru.geekbraines;

import jakarta.persistence.*;

@Entity    // одно из требований - чтобы объекты класса могли мапиться в БД и обратно -- они должны быть сущностями
@Table(name="users")  //  с пом-ю этой аннотации помечаю, что я хочу, чтобы объекты этого класса жили в таблице users
// (лучше указывать в явном виде)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")  // какому столбцу соответствует этот первичный ключ
    private Long id;  // это первичный ключ . Он обязательно должен быть. И Хибернейт на него ориентируется.

    @Column(name="name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }  // c пом-ю геттеров отправляет объект в базу

    public void setName(String name) {
        this.name = name;
    }   // с пом-ю сеттеров достает объект из базы

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public void print(){
        System.out.println("User id = "+id+"; name="+name);
    }

}
