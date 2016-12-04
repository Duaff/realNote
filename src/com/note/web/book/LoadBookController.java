package com.note.web.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.entity.NoteResult;
import com.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class LoadBookController {
	@Resource
	private NoteBookService bookService;
	
	@RequestMapping("/loadbooks.form")
	@ResponseBody
	public NoteResult execute(String userId){
		NoteResult result = 
			bookService.loadByUserId(userId);
		return result;
	}
}
