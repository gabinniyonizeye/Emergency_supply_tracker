package dao;

import model.User;

public interface UserDAO {
    User authenticateUser(String username, String password);
    boolean createUser(User user);
}