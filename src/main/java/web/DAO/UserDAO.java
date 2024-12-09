package web.DAO;

import web.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void add(User user);

    void deleteById(int id);

    User edit(User user);

    public void save(User user);
}