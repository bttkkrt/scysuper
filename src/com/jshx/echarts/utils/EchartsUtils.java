package com.jshx.echarts.utils;

import java.util.List;

import net.sf.json.JSONObject;

import com.jshx.yhb.entity.TjYhBean;

public class EchartsUtils {
	
	 /*
     * 内部设计的方法，用于封装查询数据
     */
	public static JSONObject createData(List<TjYhBean> list)throws Exception{
        JSONObject jo = new JSONObject();
        //x轴现实的信息
        String[] xAxisArr = new String[list.size()];
        //总量信息
        Integer[] restArr = new Integer[list.size()];
        //已使用信息
        Integer[] usedArr = new Integer[list.size()];
        for(int i=0;i<list.size();i++){
        	xAxisArr[i] =list.get(i).getDwdz();
        	usedArr[i] = list.get(i).getQyTotal();
        	restArr[i] = list.get(i).getSbqy();
        }
        jo.put("xAxisArr", xAxisArr);
        jo.put("restArr", restArr);
        jo.put("usedArr", usedArr);
        return jo;
    }


}
