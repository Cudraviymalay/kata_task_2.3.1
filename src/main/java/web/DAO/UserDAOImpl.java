package web.DAO;

import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    public User edit(User user) {
        return entityManager.merge(user);
    }

    public void save(User user) {
        user.setId(++USERS_COUNT);
        userList.add(user);
    }
}