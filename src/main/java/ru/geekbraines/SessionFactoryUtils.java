package ru.geekbraines;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class SessionFactoryUtils {

        // этот класс отвечает за взаимодействие с фабрикой. Это утилита для управления фабрикой и для запроса сессии.

    private SessionFactory factory;
    public void init(){
        factory =    new Configuration()       // фабрика создается следующим образом -- говорим , что есть Configuration
                .configure("hibernate.cfg.xml")    //  из файла hibernate.cfg.xml
                .buildSessionFactory();                      //  и мы хотим создать buildSessionFactory();
        // SessionFactory -- тяжеловесный объект, его не надо создавать на каждый случай. Закрываем его, только когда работа приложения
        // завершается
        // session -- это единица работы с БД

    }

    public Session getSession(){
        return factory.getCurrentSession();
    }
    public void shutdown(){
        // если фабрика не пустая - то мы ее закрываем
        if (factory !=null){  // это чтобы не словить NullPOinterExceprion
            factory.close();
        }
    }


}
