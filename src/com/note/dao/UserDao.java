package com.note.dao;

import java.util.List;
import java.util.Map;

import com.note.entity.User;
import com.note.entity.UserTest;


public interface UserDao {
	User findByName(String name);
	void updateToken(Map<String, Object> params);
	int insert(User user);
	User findById(String id);
	List<User> findByIf(UserTest user);
}
