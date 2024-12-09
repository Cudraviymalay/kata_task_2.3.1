package web.DAO;

import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private static int USERS_COUNT;
    private List<User> userList;

    public UserDAOImpl() {
        this.userList = new ArrayList<>();

        userList.add(new User(++USERS_COUNT, "Vova", "Pavlov", 33));
        userList.add(new User(++USERS_COUNT, "Petya", "Sidorod", 21));
        userList.add(new User(++USERS_COUNT, "Kolya", "Vasin", 18));
        userList.add(new User(++USERS_COUNT, "Sasha", "Morozova", 19));
        userList.add(new User(++USERS_COUNT, "Masha", "Makarova", 22));

    }

    public UserDAOImpl(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    public void save(User user) {
        user.setId(++USERS_COUNT);
        userList.add(user);
    }

    public void update(int id, User updatedUser) {
        User userToUpdated = userById(id);
        userToUpdated.setName(updatedUser.getName());
        userToUpdated.setSurname(updatedUser.getSurname());
        userToUpdated.setAge(updatedUser.getAge());
    }

    public User userById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void delete(int id) {
        userList.removeIf(user -> user.getId() == id);
    }
}