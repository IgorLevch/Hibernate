package one_to_many_and_back;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OtMApp {

    public static void main(String[] args) {



        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try{
            session= factory.getCurrentSession();
            session.beginTransaction();
            University university = session.get(University.class, 1L);
            System.out.println(university.getStudents().get(0).getName());// берем первого попавшегося студента и справшиваем его имя

            System.out.println(university);
            session.getTransaction().commit();


        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

}
