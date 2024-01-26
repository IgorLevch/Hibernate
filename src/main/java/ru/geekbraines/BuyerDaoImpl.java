package ru.geekbraines;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BuyerDaoImpl implements BuyerDao{
    @Autowired
    private SessionFactoryUtils sessionFactoryUtils;

    public BuyerDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Buyer findById(Long id) {
        try
                ( Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);

            session.getTransaction().commit();
            return buyer;

        }}

    @Override
    public List<Buyer> findAll() {
        try(Session session =sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List <Buyer> buyers = session.createQuery("SELECT u FROM Buyer u").getResultList();
            session.getTransaction().commit();

            return buyers;

        }

    }

    @Override
    public Buyer findByName(String name) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Buyer buyer = session
                    .createQuery("select buyer from Buyer buyer where buyer.name = :name", Buyer.class)
                    .setParameter("name", name)
                    .getSingleResult();

            session.getTransaction().commit();

            return buyer;

        }
    }

    @Override
    public void save(Buyer buyer) {

        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            session.saveOrUpdate(buyer);

            session.getTransaction().commit();// коммитим транзакцию после работы




        }


    }

    @Override
    public void updateNameById(Long id, String newName) {

        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();



            Buyer buyer = session.get(Buyer.class, id);
            buyer.setName(newName);

            session.getTransaction().commit();

        }


    }

}
