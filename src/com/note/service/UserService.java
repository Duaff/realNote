package com.note.service;

import com.note.entity.NoteResult;
import com.note.entity.User;

public interface UserService {
	NoteResult checkLogin(String name,String password);
	NoteResult checkLogin(String author);
	NoteResult regist(User user);
}
