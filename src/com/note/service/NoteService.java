package com.note.service;

import com.note.entity.Note;
import com.note.entity.NoteResult;

public interface NoteService {
	NoteResult loadNotesByBookId(String bookId);
	NoteResult loadNotesById(String id);
	NoteResult add(Note note);
	NoteResult update(Note note);
}
