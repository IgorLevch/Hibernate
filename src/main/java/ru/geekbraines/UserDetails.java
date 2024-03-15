package ru.geekbraines;

import jakarta.persistence.*;

@Entity
@Table(name="users_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email")
    private String email;
    @Column(name="city")
    private String city;

    @OneToOne(mappedBy = "details")
    // @PrimaryKeyJoinColumn - см. описание в классе Юзер
    private User user; // обратная связь к классу Юзер
    // это для двунаправленной связи(из деталей попадаем в Юзеры)
    // если мы это закомментируем, то будет однонаправленная связь: только из Юзера в детали сможем попасть

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserDetails(SessionFactoryUtils sessionFactoryUtils) {
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", user=" + user +
                '}';
    }
}
