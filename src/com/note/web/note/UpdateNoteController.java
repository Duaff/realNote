package com.note.web.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.entity.Note;
import com.note.entity.NoteResult;
import com.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/update.form")
	@ResponseBody
	public NoteResult execute(Note note){
		NoteResult result = noteService.update(note);
		return result;
	}
}
