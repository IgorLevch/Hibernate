package ru.geekbraines;

import jakarta.persistence.*;

@Entity    // одно из требований - чтобы объекты класса могли мапиться в БД и обратно -- они должны быть сущностями
@Table(name="users")  //  с пом-ю этой аннотации помечаю, что я хочу, чтобы объекты этого класса жили в таблице users
// (лучше указывать в явном виде)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //стратегия Identity означает, что уникальные значения праймери кей будет генерить БД
    @Column(name="id")  // какому столбцу соответствует этот первичный ключ
    private Long id;  // это первичный ключ . Он обязательно должен быть. И Хибернейт на него ориентируется.

    @Column(name="name", length = 128, unique = true, nullable = false)
    private String name;
    @Column(name="score")
    private Integer score;

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


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User(String name) {
        this.name = name;
    }

    public User() {
    }  // использует дефолтный конструктор, чтобы создать базовый объект

   /* public void print(){
        System.out.println("User id = "+id+"; name="+name);
    }*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
