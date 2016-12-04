package com.note.web.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.entity.NoteResult;
import com.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class LoadNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadnotes.form")
	@ResponseBody
	public NoteResult execute(String bookId){
		NoteResult result = noteService.loadNotesByBookId(bookId);
		return result;
	}
	@RequestMapping("/loadnote.form")
	@ResponseBody
	public NoteResult loadByNoteId(String noteId){
		NoteResult result = noteService.loadNotesById(noteId);
		return result;
	}
}
