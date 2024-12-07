package web.service;

import web.models.Users;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers();

    void add(Users user);

    void deleteById(int id);

    void edit(Users user);
}