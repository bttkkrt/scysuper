package com.jshx.http.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkCategory.entity.CheckCategory;
import com.jshx.checkCategory.service.CheckCategoryService;
import com.jshx.checkContent.entity.CheckContent;
import com.jshx.checkResult.entity.CategoryResult;
import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkResult.entity.ContentResult;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.utils.NumberFormat;

/**
 * 查看安全检查详情接口 
 * GY-UPDATE 2015-01-28
 */
public class GetCheckBasicDetailCommand implements Command {

	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	private CheckCategoryService checkCategoryService = (CheckCategoryService) SpringContextHolder.getBean("checkCategoryService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new  SummaryBean();
		String checkbasicid = json.getString("checkbasicid");//检查人id
		
		List<CategoryResult> crList = new ArrayList<CategoryResult>();
		CheckBasic checkBasic = checkBasicService.getById(checkbasicid);
		//封装实体类
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List categoryList = checkCategoryService.findCheckCategory(paraMap);
		List<CheckResult> resultList = checkBasic.getResultList();
		for (int i = 0; i < categoryList.size(); i++)
		{
			CheckCategory checkCategory = (CheckCategory) categoryList.get(i);
			CategoryResult categoryResult = new CategoryResult();
			categoryResult.setCategoryContent(checkCategory.getContent());//栏目内容
			categoryResult.setIndexNum(NumberFormat.foematInteger(i+1));//序号
			List<ContentResult> contentList = new ArrayList<ContentResult>();
			for (int m = 0; m < resultList.size(); m++) 
			{
				CheckResult checkResult = resultList.get(m);
				if (checkCategory.getId().equals(checkResult.getContent().getCategory().getId()))
				{
					ContentResult contentResult = new ContentResult();
					contentResult.setContent(checkResult.getContent().getContent());
					contentResult.setCheckResult(checkResult.getCheckResult());
					contentResult.setRemark(checkResult.getRemark());
					contentResult.setIndexNum(String.valueOf(m+1));
					contentList.add(contentResult);
				}
			}
			categoryResult.setContentList(contentList);//内容列表
			crList.add(categoryResult);
		}
		JSONArray resultArr = JSONArray.fromObject(crList);
		
 		bd.setContent(resultArr.toString());
 		bd.setCode("0");
		bd.setMessage("查询成功");
		return bd;
	}

}
