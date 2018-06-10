package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkCategory.entity.CheckCategory;
import com.jshx.checkCategory.service.CheckCategoryService;
import com.jshx.checkContent.entity.CheckContent;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;

/**
 * 初始化安全检查检查内容接口
 *	GY-UPDATE 2015-01-19
 */

public class InitCheckCommand implements Command
{
	private CheckCategoryService checkCategoryService = (CheckCategoryService) SpringContextHolder.getBean("checkCategoryService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		//查找启用的数据
 		Map map = new HashMap();
 		map.put("isusing", "0");
 		List<CheckCategory> categoryList = checkCategoryService.findCheckCategory(map);
 		JSONArray categoryArr = new JSONArray();
 		for (CheckCategory category : categoryList)
 		{
 			JSONObject categoryObj = new JSONObject();
 			categoryObj.put("categoryid", category.getId());
 			categoryObj.put("categorycontent", category.getContent());
 			categoryObj.put("categoryindexnum", category.getIndexNum());
 			JSONArray contentArr = new JSONArray();
 			List<CheckContent> contentList = category.getContents();
 			for (CheckContent content : contentList)
 			{
 				JSONObject contentObj = new JSONObject();
 				//去除撤销数据
 				if (content.getIsusing().equals("0"))
 				{
	 				contentObj.put("contentid", content.getId());
	 				contentObj.put("content", content.getContent());
	 				contentObj.put("contentindexnum", content.getIndexNum());
	 				contentArr.add(contentObj);
 				}
 			}
 			categoryObj.put("contentlist", contentArr);
 			categoryArr.add(categoryObj);
 		}
 		bd.setContent(categoryArr.toString());
 		bd.setCode("0");
		bd.setMessage("查询成功");
 		return bd;
	}
}
