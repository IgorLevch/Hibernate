package many_to_many;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import validation.PrepareDataApp;

import java.util.List;

public class ManyToManyApp {

    public static void main(String[] args) {

        PrepareDataApp.forcePrepareData();



        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try{
            session=factory.getCurrentSession();
            session.beginTransaction();
           Book book = session.get(Book.class, 1L);
            System.out.println(book);
            System.out.println(book.getReaders());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }


    }

}
