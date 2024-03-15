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




}

    @Override
    public void testCaching() {   // это бесполезный метод, для обучения

        try ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.getTransaction().commit();// коммитим транзакцию после работы
        }
        try ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.getTransaction().commit();// коммитим транзакцию после работы

            // по резульаттам в логах будет только 2 селекта. Потому что создается контекст (коробка)
            // и после того , как мы 1й раз туда положили объект из БД. Когда мы запросим второй раз объект с тем же айдишником
            //  с тем же праймери кей , то Хибернет посмотри в кеш и увидит, что объект с таким праймери кей уже есть и отдаст ссылку на этот
            // же самый объект.
            //т.е это кеш на уровне сессии, Хибернет не будет напрягать БД лишний раз
            // есть еще кеш фабрики (factory)(это кеш второго уровня) -- но его надо очень осторожно исп-ть , т.к. он живет все время работы фабрики ( в отличие от
            // кеша сесии
        }


    }

    @Override
    public void trick() {    // метод 6 го урока
        System.out.println("==========================");
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            User user = session.get(User.class, 1L);
            user.setScore(90);

            session.getTransaction().commit();   // отдельно save or Update делать не надо. Commit это сделает сам

        }
        System.out.println("==========================");
    }


}
