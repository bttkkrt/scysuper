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
import com.jshx.carDoneInfo.entity.CarEquipment;
import com.jshx.carDoneInfo.entity.CarEquipmentState;
import com.jshx.carDoneInfo.service.CardoneinfoService;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;

/**
 * 获取企业获取远程视频列表
 * @author 陆婷 2013-12-18
 *
 */

public class GetVideoListCommand implements Command
{
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	private CardoneinfoService cardoneinfoService = (CardoneinfoService) SpringContextHolder.getBean("cardoneinfoService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		User user = userService.findUserById(userId);
		
		
		Object s = json.get("limit");
		
		try
		{
			CarEquipmentState a = cardoneinfoService.getEquipmentState(null);
			if(a != null && a.getPassword() != null && !"".equals(a.getPassword()))
			{
				String password  = a.getPassword();
				String loginname = "xcqajj";
				Map m = new HashMap();
				List<CarEquipment> equipmentList = new ArrayList<CarEquipment>();
				if(s != null)
				{
					/**
					 * 每页限定数据条数
					 */
					int limit = Integer.parseInt(json.getString("limit"));
					/**
					 *每次查询的开始位置
					 */
					int start = (Integer.parseInt(json.getString("start")) - 1) * limit;
					m.put("start", start);
					m.put("limit", limit);
					
					if(user.getDeptCode().startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
					{
						CompanyBackUp comb = companyService.getCompanyByLoginId(user.getLoginId());
						if(comb != null && comb.getCompanyname() != null && !"".equals(comb.getCompanyname()))
						{
							m.put("detailName", "%" + comb.getCompanyname() + "%");
							equipmentList = cardoneinfoService.getEquipmentLists(m);
						}
					}
					else
					{
						equipmentList = cardoneinfoService.getEquipmentLists(m);
					}
					int total = cardoneinfoService.getEquipmentListSize(m);
					
					int page= total/(limit);
					page = total%limit==0?page:(page+1);
					
					bd.setTotal(total+"");
					bd.setPage(page+"");
					
				}
				else
				{
					if(user.getDeptCode().startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
					{
						CompanyBackUp comb = companyService.getCompanyByLoginId(user.getLoginId());
						if(comb != null && comb.getCompanyname() != null && !"".equals(comb.getCompanyname()))
						{
							m.put("detailName", "%" + comb.getCompanyname() + "%");
							equipmentList = cardoneinfoService.getEquipmentList(m);
						}
					}
					else
					{
						equipmentList = cardoneinfoService.getEquipmentList(null);
					}
				}
				if(equipmentList.size() != 0)
				{
					JSONObject j = new JSONObject();
					JSONArray ja = new JSONArray();
					for(CarEquipment c:equipmentList)
					{
						JSONObject o = new JSONObject();
						o.put("detailName", c.getDetailName());
						o.put("puid", c.getPuid());
						ja.add(o);
					}
					j.put("loginname", loginname);
					j.put("password", password);
					j.put("videoList", ja.toString());
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setContent(j.toString());
				}
				else
				{
					bd.setCode("1");
					bd.setMessage("无视频列表");
				}
			}
			else
			{
				bd.setCode("1");
				bd.setMessage("无法获取密码！");
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
