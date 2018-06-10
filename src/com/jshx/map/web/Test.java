package com.jshx.map.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* [{
		        "id":obj,
		        "name":"测试点",
		        "attribute":{
		        	"NAMETIP":"气泡",
		        	"moreInfo":"",
		            "companyId":obj,
		            "companyName":"园区电信",
		            "address":"园区南施街电信营业厅",
		            "legalName":"李四",
		            "legalPhone":"18900000000",
		            "foundDate":"2015-10-20",
		            "managerTeam":"测试中队",
		            "manager":"张三"
		        }}];*/
		JSONObject retuJson = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("id", "001");
		json.put("name", "测试点");
		JSONObject j = new JSONObject();
		j.put("NAMETIP", "气泡");
		j.put("moreInfo", "气泡");
		j.put("companyId", "001");
		j.put("companyName", "园区电信");
		j.put("address", "园区南施街电信营业厅");
		j.put("legalName", "李四");
		j.put("legalPhone", "18900000000");
		j.put("foundDate", "2015-10-20");
		j.put("managerTeam", "测试中队");
		j.put("manager", "张三");
		json.put("attribute", j);
		ja.add(json);
		
		
		retuJson.put("result", true);
		retuJson.put("detail", ja);
		System.out.println(retuJson.toString());
	}

}
