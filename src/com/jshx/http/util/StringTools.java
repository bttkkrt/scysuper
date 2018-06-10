package com.jshx.http.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jshx.shjl.entity.CheckRecord;

public class StringTools {
	/**
	 * 转换处理审核列表结果
	 * lj
	 * 2015-11-19
	 */
	public static String checkRecordToStr(List<CheckRecord> records){
		String checkrecord="";
		if(records!=null&&!records.isEmpty()){
			for(CheckRecord c:records)
			{
				checkrecord += new SimpleDateFormat("yyyy-MM-dd").format(c.getCreateTime())+","+c.getCheckUsername()+c.getCheckResult()+"[备注："+NullToStr(c.getCheckRemark(),"")+"]" + "\r\n";
			}
			if(checkrecord.length() != 0)
			{
				checkrecord = checkrecord.substring(0,checkrecord.length()-1);
			}
		}
		return checkrecord;
	}
	
	/**
	 * null 转换为 指定字符串
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public static String NullToStr(String oldStr,String newStr){
		if(oldStr==null){
			return newStr;
		}
		return oldStr;
	}
	/**
	 * 将xx,xxx,xx,类型的字符串按给定的下标索引给出对应的数值
	 */
	public static String getStrByIndex(String s,int index){
		String backStr;
		backStr = "";
		try {
			if(s!=null){
				String []  strs = s.split(",");
				if(strs.length>=index){
					backStr= strs[index].trim();
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return backStr;
	}
	/**
	 * 将xx,xxx,xx,类型的字符串按给定的下标索引给出对应的数值
	 */
	public static Date getDateByIndex(String s,int index){
		Date backStr;
		backStr = null;
		try {
			if(s!=null){
				String []  strs = s.split(",");
				if(strs.length>=index){
					backStr= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strs[index].trim());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return backStr;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(getStrByIndex("xx,cc,bb",3));
	}

}
