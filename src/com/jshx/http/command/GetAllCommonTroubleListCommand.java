package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.CommonTroubleBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;

/**
 * 获取执法文书的列表
 * @author 李军 2013-07-23
 *
 */

public class GetAllCommonTroubleListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map paraMap = new HashMap();
		paraMap.put("start", s);
		paraMap.put("limit", l);
		
		User user = userService.findUserById(userId);
		String dpcode = user.getDeptCode();//判断是否是企业部门
		Department dept = deptService.findDeptByDeptCode(dpcode);
		
		//FIXME
		//hanxc 修改部门的时候注意sql语句比较复杂
		
		if(dpcode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
			CompanyBackUp company = companyService.getCompanyByLoginId(user.getLoginId());
			paraMap.put("qymcId", company.getId());
		}
		else if(user.getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))){
			//乡镇安监中队能查看本部门登记的安全隐患和重大安全隐患，也能查看安监局各部门登记的安全隐患和重大安全隐患 
			paraMap.put("szz", dpcode);
			paraMap.put("deptCode", dept.getChildDeptIds());
		}
		else if(dpcode.startsWith("002001")) //危化品管理科
		{
			paraMap.put("ifwhpqylx", "1");
			paraMap.put("deptCode", dept.getChildDeptIds());
		}
		else if(dpcode.startsWith("002002")) //职业健康管理科
		{
			paraMap.put("ifzywhqylx", "1");
			paraMap.put("deptCode", dept.getChildDeptIds());
		}
		else if(dpcode.startsWith("002004")) //综合科
		{
			paraMap.put("ifyhbzjyqy", "1");
			paraMap.put("deptCode", dept.getChildDeptIds());
		}
		else if(dpcode.startsWith("002003")) //监察大队
		{
			paraMap.put("deptCode", dept.getChildDeptIds());
		}
		else if(dpcode.startsWith("002005")) //办公室
		{
			paraMap.put("deptCode", dept.getChildDeptIds());
		}
		List<CommonTroubleBean> commons = httpService.getAllCommonTrouble(paraMap);//获取一般安全隐患列表信息

		
		int total = httpService.getAllCommonTroubleCount(paraMap);//获取一般安全隐患总条数
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray ja = new JSONArray();
		try {
				if(commons!=null&&!commons.isEmpty()){
					for(CommonTroubleBean bean:commons){
						   JSONObject jo = new JSONObject();
						   jo.put("rid", StringTools.NullToStr(bean.getRid(),""));//主键
						   jo.put("qymc", StringTools.NullToStr(bean.getQymc(),""));//检查企业名称
						   jo.put("jcry", StringTools.NullToStr(bean.getJcry(),""));//检查人员
						   jo.put("jcsj", StringTools.NullToStr(bean.getJcsj(),""));//检查时间
						   jo.put("linkId", StringTools.NullToStr(bean.getLinkId(),""));//关联id
						   ja.add(jo);
					}
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(ja.toString());
				}else{
					bd.setCode("1");
					bd.setMessage("无查询结果");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
