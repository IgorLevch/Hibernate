package ru.geekbraines;

public class Main {


    public static void main(String[] args) {

        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            ProductDao pd = new ProductDaoImpl(sessionFactoryUtils);
      //  pd.save(new Product("ffdgf", 456546));
      //  pd.save(new Product("iyuyu", 35344));
            System.out.println(pd.findAll()+"  all items");

            System.out.println(pd.findById(1L));
            //   pd.save(new Product("jhkhkh", 345));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}