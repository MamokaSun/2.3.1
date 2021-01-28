package hiber.service;

import hiber.dao.Dao;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Dao userDao;

    public UserServiceImp(BCryptPasswordEncoder bCryptPasswordEncoder, Dao userDao) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> arr = new HashSet<>();
        arr.add(getRoleFromId(2));
        user.setRole(arr);
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User update(int id, User user, String role) {
        Set<Role> arr = getSetUser(id);
        if(role.contains("ROLE_ADMIN")){
           arr.add(getRoleFromId(1));
        }
        if (!user.getPassword().equals(show(id).getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        arr.add(getRoleFromId(2));
//        getSetUser(id).add(getRoleFromId(2));
        user.setRole(arr);

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

    @Override
    public Role getRoleFromId(int id) {
        return userDao.getRoleFromId(id);
    }

    public Set<Role> getSetUser( int id) {
        User user = userDao.show(id);
        Set<Role> setRole = user.getRole();

        return setRole;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getFromName(s);
        return user;
    }
}
