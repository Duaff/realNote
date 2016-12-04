package com.note.dao;

import java.util.List;
import java.util.Map;

import com.note.entity.NoteBook;

public interface NoteBookDao {
	List<NoteBook> findByUserId(String userId);
	NoteBook findByNameAndUserId(Map<String,Object> params);
	int save(NoteBook book);
}
