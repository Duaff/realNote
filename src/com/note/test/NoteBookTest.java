package com.note.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.note.dao.NoteBookDao;
import com.note.entity.NoteBook;

public class NoteBookTest {
	NoteBookDao bookDao;
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookDao = ac.getBean("noteBookDao", NoteBookDao.class);
	}
	@Test
	public void test1(){
		List<NoteBook> list = bookDao.findByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(NoteBook book : list){
			System.out.println(book.getCn_notebook_name()+","+book.getCn_notebook_id());
		}
	}
}
