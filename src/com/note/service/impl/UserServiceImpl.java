package com.note.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.note.dao.UserDao;
import com.note.entity.NoteResult;
import com.note.entity.User;
import com.note.service.UserService;
import com.note.util.NoteUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public NoteResult checkLogin(String name, String password) {
		String md5_password = NoteUtil.md5(password);
		//System.out.println(md5_password);
		NoteResult noteResult = new NoteResult();
		User user = userDao.findByName(name);
		if (user == null) {
			noteResult.setStatus(1);// 用户名为空
			noteResult.setMsg("用户不存在");
		} else if (user.getCn_user_password().equals(md5_password)) {
				noteResult.setStatus(0);
				noteResult.setMsg("登录成功");
				System.out.println("11");
				// 生产身份令牌，传给客户端，写入cookie
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("userId", user.getCn_user_id());
				String token = NoteUtil.crateToken();// 生成令牌
				data.put("token", token);
				noteResult.setData(data);// 用户ID和身份令牌传出
				userDao.updateToken(data);// 将token写入cn_user
			} else {
				noteResult.setStatus(2);
				noteResult.setMsg("密码错误");
			}
		return noteResult;
	}

	public NoteResult checkLogin(String author) {
		String name = "";
		String password = "";
		try {
			String base_msg = author.split(" ")[1];
			byte[] bbs = Base64.decode(base_msg);
			String msg = new String(bbs, "utf-8");
			//System.out.println(msg);
			name = msg.split(":")[0];
			password = msg.split(":")[1];
		} catch (Exception e) {
			NoteResult result = new NoteResult();
			result.setStatus(-1);
			result.setMsg("身份验证信息错误");
			return result;
		}
		return checkLogin(name, password);
	}

	public NoteResult regist(User user) {
		NoteResult result = new NoteResult();
		User u = userDao.findByName(user.getCn_user_name());
		if(u!=null){
			result.setStatus(1);
			result.setMsg("该账号已存在");
		}else {
			String cnUserId = NoteUtil.createId();
			user.setCn_user_id(cnUserId);
			String md5_password = NoteUtil.md5(user.getCn_user_password());
			user.setCn_user_password(md5_password);
			userDao.insert(user);
			result.setStatus(0);
			result.setMsg("注册成功");	
		}
		return result;
	}

}
