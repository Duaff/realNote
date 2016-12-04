package com.note.dao;

import java.util.List;

import com.note.entity.Note;

public interface NoteDao {
	List<Note> findByBookId(String bookId);
	int save(Note note);
	Note findById(String id);
	int update(Note note);
}
