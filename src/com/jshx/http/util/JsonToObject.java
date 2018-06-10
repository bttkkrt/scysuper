package com.jshx.http.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;

import com.jshx.http.bean.DataBean;

public class JsonToObject {
	
	@SuppressWarnings("unchecked")
	public static Object convert(Object o,JSONObject json){
		try {
			Class c = o.getClass();
			Field[] field = c.getDeclaredFields();
			for(Field f:field){//获取对象属性名
				//根据set方法将数值赋给对应的属性
					if(!"delFlag".equals(f.getName())){
						Method methodsn = c.getMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1), String.class);
						methodsn.invoke(o, json.get(f.getName()));
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("rid", "001");
		json.put("rname", "哇哈哈");
		System.out.print(json.toString());
		DataBean bean = new DataBean();
		bean.setRid("002");
		bean.setRname("大豆粉");
		json = JSONObject.fromObject(bean);

		System.out.print(json.toString());
		try {
			//Object o = convert(bean,json);
			//DataBean oo = (DataBean)o;
			DataBean oo = (DataBean)JSONObject.toBean(json);
			System.out.print(oo.getRid()+":"+oo.getRname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
