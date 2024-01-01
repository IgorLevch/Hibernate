package ru.geekbraines;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

// это то, что мы ранее называли репозиториями (слой, который предоставляет доступ в БД)
public class UserDaoImpl implements UserDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public UserDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    // расшифровывается как Data Access Object
    // это специальный класс, через который мы взаимодействуем с БД и выполняем какие-то операции над сущностями

    @Override
    public  User findById(Long id) {
        try


            ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
        session.beginTransaction(); //  начинаем транзакцию у сессии
        User user = session.get(User.class, id); // находим пользователя по айди

        session.getTransaction().commit();// коммитим транзакцию после работы

        return user; // получаем пользователя
    }}

    @Override
    public User findByName(String name) {
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            User user = session
                    .createQuery("select user from User user where user.name = :name", User.class)
                            .setParameter("name",name)
                                    .getSingleResult();

            session.getTransaction().commit();// коммитим транзакцию после работы

            return user; // получаем пользователя
        }}




    @Override
    public List<User> findAll() {
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            List<User> users = session.createQuery("select u from User u").getResultList();
            // createQuery - метод, который позволяет сформировать запрос и отправить его
            // "select u from User u" -- это не СКЛ-ный запрос. Это HQL запрос. В этих запросах работаем на уровне объектов.
            // указываем класс User, а не таблицу users
            // можно просто написать from User


            // List<User> users = session.createQuery("select u from User u where u.id<3").getResultList();

            session.getTransaction().commit();// коммитим транзакцию после работы

            return users; // получаем спсок пользователей


    }
}


    @Override
    public void save(User user) {
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
           session.saveOrUpdate(user);

            session.getTransaction().commit();// коммитим транзакцию после работы




        }
    }
    @Override
    public void updateNameById (Long id, String newName){
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии

            // HQL Example:
            // это совсем правильный вариант (формируем запрос на апдейт и его выполняем):
          /*  session.createQuery("update User u set u.name = :name where u.id=:id")
                    .setParameter("name",newName)
                    .setParameter("id", id)
                    .executeUpdate();*/


            User user = session.get(User.class, id);
            user.setName(newName);

            session.getTransaction().commit();// коммитим транзакцию после работы


        }




}}
