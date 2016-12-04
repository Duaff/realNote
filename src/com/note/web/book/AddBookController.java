package com.note.web.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.entity.NoteResult;
import com.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class AddBookController {
	@Resource
	private NoteBookService bookService;
	
	@RequestMapping("/add.form")
	@ResponseBody
	public NoteResult execute(String userId,String bookName ){
		NoteResult result = bookService.add(userId, bookName);
		return result;
	}
}
