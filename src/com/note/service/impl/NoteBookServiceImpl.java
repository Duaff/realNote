package com.note.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.note.dao.NoteBookDao;
import com.note.dao.UserDao;
import com.note.entity.NoteBook;
import com.note.entity.NoteResult;
import com.note.entity.User;
import com.note.service.NoteBookService;
import com.note.util.NoteUtil;

@Service("bookService")
public class NoteBookServiceImpl implements NoteBookService{
	
	@Resource
	private NoteBookDao noteBookDao;
	@Resource
	private UserDao userDao;
	
	
	
	public NoteBookDao getNoteBookDao() {
		return noteBookDao;
	}

	public void setNoteBookDao(NoteBookDao noteBookDao) {
		this.noteBookDao = noteBookDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public NoteResult loadByUserId(String userId) {
		NoteResult result = new NoteResult();
		if(userId==null || "".equals(userId)){
			result.setStatus(2);
			result.setMsg("需要指定用户Id");
			return result;
		}
		User user = userDao.findById(userId);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		List<NoteBook> books = noteBookDao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(books);
		return result;
	}

	public NoteResult add(String userId, String bookName) {
		NoteResult result = new NoteResult();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("bookName", bookName);
		NoteBook book = noteBookDao.findByNameAndUserId(params);
		if(book!=null){
			result.setStatus(1);
			result.setMsg("改笔记本已存在");
			return result;
		}
		NoteBook book2 = new NoteBook();
		String NoteBookId = NoteUtil.createId();
		book2.setCn_notebook_id(NoteBookId);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		book2.setCn_notebook_createtime(time);
		book2.setCn_user_id(userId);
		book2.setCn_notebook_name(bookName);
		noteBookDao.save(book2);
		result.setStatus(0);
		result.setMsg("添加笔记成功");
		result.setData(book2);
		return result;
	}
	
}
