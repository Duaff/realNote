package com.note.web.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.note.entity.NoteResult;
import com.note.entity.User;
import com.note.service.UserService;

@Controller
@RequestMapping("/user")
public class RegistController {
	@Resource
	UserService userService;
	
	@RequestMapping("/regist.form")
	@ResponseBody
	public NoteResult execute(User user){
		NoteResult result = userService.regist(user);
		return result;
	}
}
