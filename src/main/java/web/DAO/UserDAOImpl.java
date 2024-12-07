package web.DAO;

import org.springframework.stereotype.Repository;
import web.models.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(Users user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Users user = entityManager.find(Users.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    public Users edit(Users user) {
        return entityManager.merge(user);
    }
}