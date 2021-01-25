package hiber.service;

import hiber.dao.Dao;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

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

        User saveParam = userDao.show(id);
        user.setRole(saveParam.getRole());
        user.setPassword(saveParam.getPassword());
        return userDao.update(id,user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public User getFromEmail(String email) {
        return userDao.getFromEmail(email);
    }

    @Transactional
    @Override
    public User getFromName(String name) {
        return userDao.getFromName(name);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getFromName(s);
        return user;
    }
}
