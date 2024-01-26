package ru.geekbraines;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {

            Session session = sessionFactoryUtils.getSession();  // из фабрики запрашиваем сессию
            session.beginTransaction();
            BuyerDao bd = new BuyerDaoImpl(sessionFactoryUtils);
            Buyer buyer = new Buyer("John");
            bd.save(new Buyer("Goga"));
            bd.save(new Buyer("Vaso"));
            bd.save(new Buyer("Vityai"));

            System.out.println(bd.findAll());

           buyer.getProducts().forEach(System.out::println);


          //  ProductDao pd = new ProductDaoImpl(sessionFactoryUtils);
      //  pd.save(new Product("ffdgf", 456546));
      //  pd.save(new Product("iyuyu", 35344));
         //   System.out.println(pd.findAll()+"  all items");

          //  System.out.println(pd.findById(1L));
            //   pd.save(new Product("jhkhkh", 345));

            session.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        try{

            Session session = sessionFactoryUtils.getSession();  // из фабрики запрашиваем сессию
            session.beginTransaction();
            ProductDao pd = new ProductDaoImpl(sessionFactoryUtils);
        pd.save(new Product("cacao",87,new Buyer("Oleg")));
        pd.save(new Product("sugar",8897,new Buyer("Vlad")));

            System.out.println(pd.findAll());

            session.getTransaction().commit();

    } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Session session = null;
        try{
            session= sessionFactoryUtils.getSession();
            session.beginTransaction();


            Buyer b1 = session.get(Buyer.class, 1L);
            System.out.println(b1.getProducts().get(0).getTitle());

            System.out.println(b1);
            session.getTransaction().commit();


        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        try{
            session= sessionFactoryUtils.getSession();
            session.beginTransaction();


            Product p1 = session.get(Product.class, 1L);
            System.out.println(p1.getBuyer().getName());

            System.out.println(p1);
            session.getTransaction().commit();


        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } sessionFactoryUtils.shutdown();



    }}