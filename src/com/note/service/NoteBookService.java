package com.note.service;

import com.note.entity.NoteResult;

public interface NoteBookService {
	NoteResult loadByUserId(String userId);
	NoteResult add(String userId,String bookName);
}
