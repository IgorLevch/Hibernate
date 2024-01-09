package ru.geekbraines;

import org.hibernate.Session;

import java.util.List;

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
            Product oldUser = session.get(Product.class, 1L);// пытаемся достать из БД объект из клсса Product, с айди =1
            oldUser.print(); // потом печатаем
            session.getTransaction().commit(); // потом коммитим нашу транзакцию
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
           shutdown();
        }


    }*/

    public static void main(String[] args) {

        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();

        try {

            ProductDao productDao = new ProductDaoImpl(sessionFactoryUtils);

          //  productDao.save(new Product("hhh",56));
         //   productDao.save(new Product("hhuuh",89));
            System.out.println(productDao.findById(2L));
           productDao.updateNameById(2L,"uhahah",7689);
            System.out.println(productDao.findById(1L));
            System.out.println(productDao.findById(2L));

          //  System.out.println(productDao.findAll());


          /*  Product user = productDao.findById(4L);
            user.print(); // это пример того, как мы будем делать запрос: я хочу найти пользователя, я хочу его тпечатать.*/


           // System.out.println(productDao.findAll());

           // System.out.println(productDao.findByName("Jack"));

           // productDao.save(new Product("Maxx"));
         //   System.out.println(productDao.findAll());

        //    productDao.updateNameById(1L,"Zeratul");

         //   System.out.println(productDao.findAll());



/*
            Session session = factory.getCurrentSession();  // из фабрики запрашиваем сессию
            session.beginTransaction();  // открываем транзакцию (любое взаимодействие с БД пороизсодит в рамках траназкции, даже с jdbc)
            // сессия создается под каждое действие с БД
            Product oldUser = session.get(Product.class, 1L);// пытаемся достать из БД объект из клсса Product, с айди =1
            oldUser.print(); // потом печатаем
            session.getTransaction().commit(); // потом коммитим нашу транзакцию*/






        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            sessionFactoryUtils.shutdown();
        }


    }


}