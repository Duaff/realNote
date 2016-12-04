package com.note.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.note.dao.NoteDao;
import com.note.dao.UserDao;
import com.note.entity.Note;
import com.note.entity.User;

public class NoteDaoTest {
	public NoteDao noteDao;
	
	@Before
	public void init(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		noteDao = ac.getBean("noteDao", NoteDao.class);
		
	}
	
	@Test
	public void test(){
		List<Note> list = noteDao.findByBookId("4b86d1f9-6345-4532-bc50-ee86442f004b");
		System.out.println(list.get(0).getCn_note_title()+","+list.get(0).getCn_note_create_time());
	}
}
