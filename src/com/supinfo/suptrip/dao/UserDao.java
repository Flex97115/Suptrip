package com.supinfo.suptrip.dao;
import java.util.List;

import com.supinfo.suptrip.entity.User;

public interface UserDao {	
	public void addUser( User user);
	public void updateUser( User user);
	public void deleteUser( User user);
	public User findByAuth(int idBooster, String password);
	public User findById(int id);
	public List<User> findAll();
}
