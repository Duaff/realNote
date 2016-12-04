package com.note.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.note.entity.NoteResult;
import com.note.service.NoteBookService;

public class NoteBookServiceTest {
	@Resource
	private NoteBookService bookService;
	
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookService = ac.getBean("bookService", NoteBookService.class);
	}
	
	@Test
	public void test1(){
		NoteResult result = bookService.loadByUserId("111");
		System.out.println(result.getStatus()+","+result.getStatus());
	}
}
