package com.note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class NoteUtil {
	/**
	 * 生产一个身份令牌
	 */
	public static String crateToken(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	/**
	 * 将生产的令牌进行md5算法加密
	 */
	public static String md5(String msg){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] output = md.digest(msg.getBytes());
			//System.out.println(output.length);
			//采用Base64算法将加密后的字节信息转成字符串
			return Base64.encodeBase64String(output);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 生成一个主键ID
	 * @param args
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(md5("123"));
//		System.out.println(md5("123e3243545455"));
//		System.out.println(md5(crateToken()));
	}
}
