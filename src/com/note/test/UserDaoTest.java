package com.note.test;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.note.dao.UserDao;
import com.note.entity.User;
import com.note.entity.UserTest;

public class UserDaoTest {
	UserDao userDao;
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = ac.getBean("userDao", UserDao.class);
	}
	@Test
	public void test1() throws IOException{
//		User user = userDao.findByName("Tom");
//		Assert.assertNull(user);
		UserTest user = new UserTest();
		user.setCn_user_id("12accd77-5892-4f6b-b285-959e898988ff");
		List<User> list = userDao.findByIf(user);
		System.out.println(list.get(0).toString());
	}
}
