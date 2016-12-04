package com.note.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.note.entity.NoteResult;
import com.note.service.UserService;
import com.note.service.impl.UserServiceImpl;

public class UserServiceTest {
	@Resource
	UserService userService;
	
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = ac.getBean("userService", UserService.class);
	}
	@Test
	public void test1(){
		NoteResult result = userService.checkLogin("zhouj", "55587a910882016321201e6ebbc9f595");
		System.out.println(result.getMsg());
	}
}
