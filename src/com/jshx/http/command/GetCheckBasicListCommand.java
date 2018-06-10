package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkContent.service.CheckContentService;
import com.jshx.checkResult.service.CheckResultService;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.CommonTroubleBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.UserService;

/**
 * 企业安全检查列表 
 * GY-UPDATE 2015-01-19
 *
 */

public class GetCheckBasicListCommand implements Command
{
	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String companyid = json.getString("companyid");//企业id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("companyid", companyid);
		JSONArray ja = new JSONArray();
		try
		{
			Pagination pagination = new Pagination(s, l);
			pagination = checkBasicService.findByPage(pagination, paraMap);
			List list = pagination.getList();
			if(null != list || list.size() != 0){
				for (Object object : list) {
					Object[] objArr = (Object[])object;
				    JSONObject jo = new JSONObject();
				    jo.put("checkbasicid", objArr[0] != null ? objArr[0].toString() : "");//主键
				    jo.put("companyname", objArr[1] != null ? objArr[1].toString() : "");//检查企业名称
				    jo.put("checktime", objArr[2] != null ? objArr[2].toString() : "");//检查时间
				    jo.put("dwdz", objArr[3] != null ? objArr[3].toString() : "");//地址
				    jo.put("fddbr", objArr[4] != null ? objArr[4].toString() : "");//主要负责人
				    jo.put("fddbrlxhm", objArr[5] != null ? objArr[5].toString() : "");//联系方式
				    jo.put("gddh", objArr[6] != null ? objArr[6].toString() : "");//固定电话
				    ja.add(jo);
				}
				bd.setCode("0");
				bd.setMessage("查询成功");
				bd.setTotal(pagination.getTotalCount()+"");
				bd.setPage(pagination.getPageNumber()+"");
				bd.setContent(ja.toString());
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
}
