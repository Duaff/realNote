package com.note.entity;

import java.io.Serializable;

public class UserTest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4624223355042721797L;
	private String cn_user_id;
	private String cn_user_desc;
	
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_desc() {
		return cn_user_desc;
	}
	public void setCn_user_desc(String cn_user_desc) {
		this.cn_user_desc = cn_user_desc;
	}
	
	
}
