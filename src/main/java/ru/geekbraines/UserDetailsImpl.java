package ru.geekbraines;

import org.hibernate.Session;

public class UserDetailsImpl {

    private SessionFactoryUtils sessionFactoryUtils;

    public UserDetailsImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    public  UserDetails findById(Long id) {
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            UserDetails userd = session.get(UserDetails.class, id); // находим пользователя по айди

            session.getTransaction().commit();// коммитим транзакцию после работы

            return userd; // получаем пользователя
        }}


}
