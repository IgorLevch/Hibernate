package ru.geekbraines;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import validation.PrepareDataApp;

public class Main {

    //при работе с хибернет первым делом нужно создать фабрику сессий.
    // private static SessionFactory factory;
    /*public static void init(){
        factory =    new Configuration()       // фабрика создается следующим образом -- говорим , что есть Configuration
                .configure("hibernate.cfg.xml")    //  из файла hibernate.cfg.xml
                .buildSessionFactory();                      //  и мы хотим создать buildSessionFactory();
        // SessionFactory -- тяжеловесный объект, его не надо создавать на каждый случай. Закрываем его, только когда работа приложения
        // завершается
        // session -- это единица работы с БД

    }*/
/*    public static void shutdown(){
        // если фабрика не пустая - то мы ее закрываем
        if (factory !=null){  // это чтобы не словить NullPOinterExceprion
            factory.close();
        }
    }*/

   /* public static void main(String[] args) {

        try {  // далее идет простейая КРУД операция:
            init();
            Session session = factory.getCurrentSession();  // из фабрики запрашиваем сессию
            session.beginTransaction();  // открываем транзакцию (любое взаимодействие с БД пороизсодит в рамках траназкции, даже с jdbc)
                                              // сессия создается под каждое действие с БД
            User oldUser = session.get(User.class, 1L);// пытаемся достать из БД объект из клсса User, с айди =1
            oldUser.print(); // потом печатаем
            session.getTransaction().commit(); // потом коммитим нашу транзакцию
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
           shutdown();
        }


    }*/

    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();



        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();

        try {  // далее идет простейая КРУД операция:
            UserDao userDao = new UserDaoImpl(sessionFactoryUtils);
            UserDetailsImpl userDet = new UserDetailsImpl(sessionFactoryUtils);


          /*  User user = userDao.findById(4L);
            user.print(); // это пример того, как мы будем делать запрос: я хочу найти пользователя, я хочу его тпечатать.*/


           // System.out.println(userDao.findAll());

           // System.out.println(userDao.findByName("Jack"));

           // userDao.save(new User("Maxx"));
         //   System.out.println(userDao.findAll());

        //    userDao.updateNameById(1L,"Zeratul");

         //   System.out.println(userDao.findAll());

          //  userDao.testCaching();

            /*userDao.trick();
            System.out.println(userDao.findAll());*/

            userDao.findById(1L);
            System.out.println(userDao);
            userDet.findById(1L);
            System.out.println(userDet);


/*
            Session session = factory.getCurrentSession();  // из фабрики запрашиваем сессию
            session.beginTransaction();  // открываем транзакцию (любое взаимодействие с БД пороизсодит в рамках траназкции, даже с jdbc)
            // сессия создается под каждое действие с БД
            User oldUser = session.get(User.class, 1L);// пытаемся достать из БД объект из клсса User, с айди =1
            oldUser.print(); // потом печатаем
            session.getTransaction().commit(); // потом коммитим нашу транзакцию*/
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            sessionFactoryUtils.shutdown();
        }


    }


}