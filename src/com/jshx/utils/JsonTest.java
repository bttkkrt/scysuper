package com.jshx.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {
	public static void main(String[] args){
		JSONObject json = new JSONObject();
		TestBean bean1 = new TestBean("aa","12");
		TestBean bean2 = new TestBean("bb","24");
		json.put("key1", "keyValue1");
		json.put("key2", bean1);
		json.put("key3", "keyValue3");
		json.put("key4",  bean2);
		System.out.println(json.toString());
		JSONArray ja = new JSONArray();
		ja.add(json);
		ja.add(1, "ASD");
		System.out.print(ja.toString());
	}
}
