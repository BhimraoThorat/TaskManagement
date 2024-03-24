package com.task.DAO;

import java.util.List;

import com.task.Entity.User;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
	List<User> getAllUsers();
	
}
