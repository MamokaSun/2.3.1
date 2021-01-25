package hiber.dao;

import hiber.model.Role;
import hiber.model.User;

import java.util.List;
import java.util.Set;

public interface Dao {
    public List<User> index();
    public User show(int id);
    public User save(User user);
    public User update(int id, User user);
    public void delete(int id);
    public User getFromEmail(String email);
    public User getFromName(String name);
    public Role getRoleFromId(int id);
}
