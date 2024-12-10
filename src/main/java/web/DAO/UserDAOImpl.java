package web.DAO;

import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser) {
        User userToUpdate = entityManager.find(User.class, id);
        if (userToUpdate != null) {
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setSurname(updatedUser.getSurname());
            userToUpdate.setAge(updatedUser.getAge());
            entityManager.merge(userToUpdate);
        }
    }

    @Override
    @Transactional
    public User userById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User userToDelete = entityManager.find(User.class, id);
        if (userToDelete != null) {
            entityManager.remove(userToDelete);
        }
    }
}