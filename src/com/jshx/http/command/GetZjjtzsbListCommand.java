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
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.zjjtzsb.entity.Zjjtzsb;

/**
 * 获取质监局特种设备列表
 * @author 陆婷 2014-1-2
 *
 */

public class GetZjjtzsbListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
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
		
		Map map = new HashMap();
		map.put("start", s);
		map.put("limit", l);
		User user = userService.findUserById(userId);
		String deptCode = user.getDeptCode();
		List<String> state = new ArrayList<String>();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))) //企业
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(user.getLoginId());
			map.put("qyid",  company.getId());
			state.add("0");
			state.add("4");
			map.put("states", state);
		}
		else if(deptCode.startsWith("003")) //质监局
		{
			map.put("shbs", "2");
			state.add("2");
			map.put("states", state);
		}
		else  //安监中队
		{
			map.put("szzid", deptCode);
			map.put("shbs", "1");
			state.add("1");
			state.add("4");
			map.put("states", state);
		}
		List<Zjjtzsb> list = httpService.getZjjTzsbListByMap(map); //获取质监局特种设备列表
		
		int total = httpService.getZjjTzsbListSizeByMap(map);//获取
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(Zjjtzsb bean:list){
						   JSONObject jo = new JSONObject();
						   jo.put("id", bean.getId()); //编号
						   jo.put("szzname", bean.getSzzname()); //所属乡镇名称
						   jo.put("qymc", bean.getQymc()); // 单位名称
						   jo.put("sblb", bean.getSblb()); //设备类别
						   jo.put("sbdah", bean.getSbdah()); //设备档案号
						   jo.put("zcdm", bean.getZcdm()); //注册代码
						   jo.put("jyrq", bean.getJyrq()); //检验日期
						   jo.put("jyjl", bean.getJyjl()); //检验结论
						   jo.put("jcrq", bean.getJcrq()); //检查日期
						   jo.put("jcry", bean.getJcry()); //检查人员
						   jo.put("zywt", bean.getZywt()); //主要问题
						   jo.put("dwlxr", bean.getDwlxr()); //单位联系人
						   jo.put("dh", bean.getDh()); //电话
						   jo.put("bz", bean.getBz()); //备注
						   jo.put("zgqk", bean.getZgqk()); //整改情况
						   jo.put("zgwcsj", bean.getZgwcsj()); //整改完成时间
						   
						   Map m = new HashMap();
						   m.put("linkId", bean.getLinkId());
						   m.put("type", "zjjtzsb");
						   DataBean bean01 = httpService.getPhotoListByType(m);
						   jo.put("zghtp", StringTools.NullToStr(bean01.getRname(),""));//此处获取整改完成后图片
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
