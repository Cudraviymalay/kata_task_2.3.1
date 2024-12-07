package web.DAO;

import web.models.Users;

public interface UserDAO {

    void add(Users user);

    void deleteById(int id);

    Users edit(Users user);
}