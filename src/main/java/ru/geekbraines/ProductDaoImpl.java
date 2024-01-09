package ru.geekbraines;

import org.hibernate.Session;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Product findById(Long id) {
        try
                ( Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);

            session.getTransaction().commit();
            return product;

        }}

    @Override
    public List<Product> findAll() {
       try(Session session =sessionFactoryUtils.getSession()){
           session.beginTransaction();
           List <Product> products = session.createQuery("SELECT u FROM Product u").getResultList();
           session.getTransaction().commit();

           return products;

       }

    }

    @Override
    public Product findByName(String title) {
       try (Session session = sessionFactoryUtils.getSession()){
        session.beginTransaction();
        Product product = session
                .createQuery("select product from Product product where product.title = :title", Product.class)
                .setParameter("title", title)
                .getSingleResult();

        session.getTransaction().commit();

        return product;

       }
    }

    @Override
    public void save(Product product) {

        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            session.saveOrUpdate(product);

            session.getTransaction().commit();// коммитим транзакцию после работы




        }


    }

    @Override
    public void updateTitleById(Long id, String newTitle, Integer newPrice) {

        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();



            Product product = session.get(Product.class, id);
            product.setTitle(newTitle);
            product.setPrice(newPrice);

            session.getTransaction().commit();

        }


    }
}
