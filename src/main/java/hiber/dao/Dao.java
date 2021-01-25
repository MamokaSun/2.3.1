package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface Dao {
    public List<User> index();
    public User show(int id);
    public User save(User user);
    public User update(int id, User user);
    public void delete(int id);
    public User getFromEmail(String email);
    public User getFromName(String name);
}
