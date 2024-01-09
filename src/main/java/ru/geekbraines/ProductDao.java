package ru.geekbraines;

import java.util.List;

public interface ProductDao {

    Product findById(Long id);
    List<Product> findAll();
    Product findByName(String title);
    void save(Product product);
    void updateTitleById(Long id, String newTitle, Integer newPrice);

}
