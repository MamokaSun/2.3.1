package hiber.service;

import hiber.model.Role;
import hiber.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> index();
    public User show(int id);
    public User save(User user);
    public User update(int id, User user, String role);
    public void delete(int id);
    public User getFromEmail(String email);
    public User getFromName(String name);
    public Role getRoleFromId(int id);
}
