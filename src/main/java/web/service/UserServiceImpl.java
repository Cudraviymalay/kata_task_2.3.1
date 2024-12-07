package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.DAO.UserDAO;
import web.models.Users;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static int USERS_COUNT;
    private List<Users> usersList;

    public UserServiceImpl() {
        this.usersList = new ArrayList<>();

        usersList.add(new Users(++USERS_COUNT, "Vova", "Pavlov", 33));
        usersList.add(new Users(++USERS_COUNT, "Petya", "Sidorod", 21));
        usersList.add(new Users(++USERS_COUNT, "Kolya", "Vasin", 18));
        usersList.add(new Users(++USERS_COUNT, "Sasha", "Morozova", 19));
        usersList.add(new Users(++USERS_COUNT, "Masha", "Makarova", 22));

    }

    public UserServiceImpl(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersList;
    }

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public void add(Users user) {
        userDAO.add(user);
    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public void edit(Users user) {
        userDAO.edit(user);
    }
}