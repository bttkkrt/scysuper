package com.jshx.http.command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;

/**
 * 获取所在镇的列表
 * @author 李军 2013-07-23
 *
 */

public class GetTownListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String deptCode = json.getString("deptCode");//获取县区部门编码
		if(null != deptCode && 0<deptCode.trim().length()){
			List<Department> parlist = new ArrayList<Department>();
			parlist = deptService.findDeptByParentDeptCode(deptCode);
			String tempCode = "";
			for (Department department : parlist) {
				if ("乡镇安监中队".equals(department.getDeptName())) {
					tempCode = department.getDeptCode();
				}
			}
			List<Department> deptlist  = deptService.findDeptByParentDeptCode(tempCode);
			/**
			 * hanxc 2014/11/8 获取所在镇信息
			 */
			JSONArray ja = new JSONArray();
			try {
				for (Department dept : deptlist) {
					DataBean bean = new DataBean();
					bean.setRid(dept.getDeptCode());
					
					String tempStr = dept.getDeptName();
					tempStr = tempStr.substring(0, tempStr.lastIndexOf("安监中队"));
					bean.setRname(tempStr);
					
					JSONObject jo = JSONObject.fromObject(bean);
					ja.add(jo);
				}
				bd.setCode("0");
				bd.setMessage("查询成功");
				bd.setContent(ja.toString());
				
			} catch (RuntimeException e) {
				e.printStackTrace();
				bd.setCode("1");
				bd.setMessage("查询失败");
			}
		}else{
			bd.setCode("1");
			bd.setMessage("县区部门编码不能为空");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
