package hiber.dao;
import hiber.model.User;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DaoImp implements Dao{

    private User users;


    @PersistenceContext(unitName = "getEntityManager")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> index() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User save(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public User update(int id, User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
       entityManager.remove(show(id));
    }
}
