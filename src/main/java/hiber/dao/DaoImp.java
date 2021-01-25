package hiber.dao;
import hiber.model.Role;
import hiber.model.User;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DaoImp implements Dao{

//    private User users;

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

    @Override
    public User getFromEmail(String email) {
        User user = entityManager.createQuery("FROM User where email=:email", User.class).
                setParameter("email", email).getSingleResult();
        return user;
    }


    @Override
    public User getFromName(String name) {
//
//        Role admin = (Role) entityManager.createQuery("FROM Role where id ='1'").getSingleResult();
//        Role userRole = (Role) entityManager.createQuery("FROM Role where id ='2'").getSingleResult();
//        Set<Role> arr = new HashSet<>();
//        arr.add(admin);
//        arr.add(userRole);
//        User user = new User(3,"au", "au", "test3@mail.ru");
//        entityManager.merge(role);
//        user.setRole(arr);
//        entityManager.merge(user);
        User user1 = entityManager.createQuery("FROM User where name=:name", User.class).
                setParameter("name", name).getSingleResult();
        return user1;
    }

    @Override
    public Role getRoleFromId(int id) {
        return entityManager.find(Role.class, id);
    }


}
