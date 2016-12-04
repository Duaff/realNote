package com.note.web.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.entity.NoteResult;
import com.note.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	UserService userService;
	
	@RequestMapping("/login.form")
	@ResponseBody
	public NoteResult execute(HttpServletRequest request){
		String author = request.getHeader("Authorization");
		NoteResult result = userService.checkLogin(author);
		return result;
	}
}
