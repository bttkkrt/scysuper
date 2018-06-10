package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.company.entity.Company;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;

/**
 * 
 * @author 陆婷
 * @date 2013-8-5
 * @category 企业列表查询
 */
public class CompListQueryCommand implements Command {
	protected Logger logger = LoggerFactory.getLogger(CompListQueryCommand.class);
	private CompanyService companyService = SpringContextHolder.getBean("companyService");
	private UserService userService = SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject bean) {
		// TODO Auto-generated method stub
		SummaryBean bd = new SummaryBean();
		String userId = bean.getString("userId");
		String title = bean.getString("title");
		User user = userService.findUserById(userId);
		
		String deptCode = user.getDeptCode();
		
		/**
		 * 搜索条件放入MAP中
		 */
		Map map = new HashMap();
		if(user.getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			if(deptCode.length() == 12)
			{
				map.put("dwdz1", deptCode);
			}
			else
			{
				map.put("szc", deptCode);
			}
		}
		else if(deptCode.startsWith("002001"))
		{
			map.put("ifwhpqylx", "1");
		}
		else if(deptCode.startsWith("002002"))
		{
			map.put("ifzywhqylx", "1");
		}
		map.put("passTer", 1);
		if(title != null)
		{
			map.put("companyname", "%" + title + "%");
		}
		JSONArray jsons = new JSONArray();
		JSONObject json = null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			/**
			 * 获取企业列表
			 */
			List<Company> list = companyService.findCompList(map);
			if (list.size() > 0) {
				bd.setCode("0");
				bd.setMessage("");
				for (Company c : list) {
					json = new JSONObject();
					/**
					 * 返回企业id
					 */
					json.put("qymcId", c.getId());
					/**
					 * 返回企业名称
					 */
					json.put("qymc", StringTools.NullToStr(c.getCompanyname(),""));
					/**
					 * 所在镇
					 */
					json.put("szz", StringTools.NullToStr(c.getDwdz1(),""));
					/**
					 * 法定代表人
					 */
					json.put("fddbr", StringTools.NullToStr(c.getFddbr(),""));
					
					
					/**
					 * 企业成立时间
					 */
					
					if(c.getQyclsj() != null)
					{
						json.put("clsj", sdf.format(c.getQyclsj()));
					}
					else
					{
						json.put("clsj", "");
					}
					 /**
			    	 * 详细地址
			    	 */
			    	json.put("dwdz2", StringTools.NullToStr(c.getDwdz2(),""));
			    	
					jsons.add(json);
				}
				bd.setContent(jsons.toString());
			} else {
				bd.setCode("1");
				bd.setMessage("无数据");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("2");
			bd.setMessage("异常");
		}

		return bd;
	}
}
