package ru.geekbraines;

import org.hibernate.Session;

import java.util.List;

// это то, что мы ранее называли репозиториями (слой, который предоставляет доступ в БД)
public class ProductDaoImpl implements ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    // расшифровывается как Data Access Object
    // это специальный класс, через который мы взаимодействуем с БД и выполняем какие-то операции над сущностями

    @Override
    public Product findById(Long id) {
        try


            ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
        session.beginTransaction(); //  начинаем транзакцию у сессии
        Product product = session.get(Product.class, id); // находим пользователя по айди

        session.getTransaction().commit();// коммитим транзакцию после работы

        return product; // получаем пользователя
    }}

    @Override
    public Product findByName(String title) {
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            Product product = session
                    .createQuery("select product from Product product where product.title = :title", Product.class)
                            .setParameter("title", title)
                                    .getSingleResult();

            session.getTransaction().commit();// коммитим транзакцию после работы

            return product; // получаем пользователя
        }}




    @Override
    public List<Product> findAll() {
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии
            List<Product> products = session.createQuery("SELECT u FROM Product u").getResultList();
            // createQuery - метод, который позволяет сформировать запрос и отправить его
            // "select u from Product u" -- это не СКЛ-ный запрос. Это HQL запрос. В этих запросах работаем на уровне объектов.
            // указываем класс Product, а не таблицу products
            // можно просто написать from Product


            // List<Product> products = session.createQuery("select u from Product u where u.id<3").getResultList();

            session.getTransaction().commit();// коммитим транзакцию после работы

            return products; // получаем спсок пользователей


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
    public void updateNameById (Long id, String newTitle, Integer newPrice){
        try


                ( Session session = sessionFactoryUtils.getSession()) {//получаем сессию
            session.beginTransaction(); //  начинаем транзакцию у сессии

            // HQL Example:
            // это совсем правильный вариант (формируем запрос на апдейт и его выполняем):
          /*  session.createQuery("update Product u set u.name = :name where u.id=:id")
                    .setParameter("name",newName)
                    .setParameter("id", id)
                    .executeUpdate();*/


            Product product = session.get(Product.class, id);
            product.setTitle(newTitle);
            product.setPrice(newPrice);

            session.getTransaction().commit();// коммитим транзакцию после работы


        }




}





}
