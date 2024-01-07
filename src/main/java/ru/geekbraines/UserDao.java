package ru.geekbraines;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User findById(Long id);
    List<User> findAll();
    User findByName(String name);
    void save(User user);
    void updateNameById (Long id, String newName);
    void testCaching();


}
