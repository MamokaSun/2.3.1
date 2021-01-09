package hiber.service;

import hiber.dao.Dao;
import hiber.dao.DaoImp;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final Dao userDao;

    public UserServiceImp(Dao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Transactional
    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User update(int id, User user) {
        return userDao.update(id,user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
