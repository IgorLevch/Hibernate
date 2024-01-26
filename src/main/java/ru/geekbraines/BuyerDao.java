package ru.geekbraines;

import java.util.List;

public interface BuyerDao {

    Buyer findById(Long id);
    List<Buyer> findAll();
    Buyer findByName(String name);
    void save(Buyer buyer);
    void updateNameById(Long id, String newName);


}
