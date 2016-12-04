package com.note.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.note.dao.NoteDao;
import com.note.entity.Note;
import com.note.entity.NoteResult;
import com.note.service.NoteService;
import com.note.util.Constant;
import com.note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDao noteDao;
	
	public NoteResult loadNotesByBookId(String bookId) {
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询成功");
		if(bookId == null ||"".equals(bookId)){
			result.setData(null);
			return result;
		}
		List<Note> list = noteDao.findByBookId(bookId);
		result.setData(list);
		return result;
	}

	public NoteResult add(Note note) {
		NoteResult result = new NoteResult();
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		//设置笔记状态
		note.setCn_note_status_id(Constant.NOTE_STATUS_NORMAL);
		//设置笔记类型
		note.setCn_note_type_id(Constant.NOTE_TYPE_NORMAL); 
		//设置创建时间
		note.setCn_note_create_time(System.currentTimeMillis());
		noteDao.save(note);
		result.setStatus(0);
		result.setData(note);
		result.setMsg("创建成功");
		return result;
	}

	public NoteResult loadNotesById(String id) {
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询成功");
		if(id == null || "".equals(id)){
			result.setData(null);
			return result;
		}
		Note note = noteDao.findById(id);
		result.setData(note);
		return result;
	}

	public NoteResult update(Note note) {
		NoteResult result = new NoteResult();
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.update(note);
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}

}
