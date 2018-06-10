package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.Company;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
/**
 * 
 * @author gq
 * @date 2013-7-25
 * @category 企业列表查询
 */
public class CompanyListQueryCommand implements Command {
	protected Logger logger = LoggerFactory.getLogger(CompanyListQueryCommand.class);
	private CompanyService companyService = SpringContextHolder.getBean("companyService");
	private UserService usesrService = SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject bean) {
		SummaryBean bd = new SummaryBean();
		/**
		 * 登录用户的id
		 */
		String userid = bean.getString("userid");
		/**
		 * 搜索关键词 单位名称
		 */
		String companyName = bean.getString("companyName");
		/**
		 * 搜索关键词 单位地址
		 */
		String companyAddress = bean.getString("companyAddress");
		/**
		 * 搜索关键词  县区
		 */
		String county = bean.getString("county");
		
		/**
		 * 搜索关键词  乡镇等
		 */
		String dwdz1 = bean.getString("dwdz1");
		/**
		 * 搜索关键词 单位类型
		 */
		String qylx = bean.getString("qylx");
		/**
		 * 搜索关键词 行业类型
		 */
		String hyfl = bean.getString("hyfl");
		/**
		 * 搜索关键词 直属等级
		 */
		String zsqytype = bean.getString("zsqytype");
		
		/*try {
			companyName = new String(companyName.getBytes("ISO-8859-1"),"utf-8");
			companyAddress = new String(companyAddress.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
		/**
		 * 每页限定数据条数
		 */
		int limit = Integer.parseInt(bean.getString("limit"));
		/**
		 *每次查询的开始位置
		 */
		int start = (Integer.parseInt(bean.getString("start")) - 1)
				* limit;
		/**
		 * 搜索条件放入MAP中
		 */
		Map map = new HashMap();
		if (StringUtils.isNotEmpty(companyName) && !"-100".equals(companyName))
			map.put("companyname", "%" + companyName + "%");
		if (null != companyAddress && !"-100".equals(companyAddress))
			map.put("companyAddress", "%" + companyAddress + "%");
		if (null != qylx && !"-100".equals(qylx))
			map.put("qylx", qylx);
		if (null != hyfl && !"-100".equals(hyfl))
			map.put("hyfl", hyfl);
		if (null != dwdz1 && (0 < dwdz1.trim().length())){
			map.put("dwdz1", dwdz1);
		}else if((null != county) && (0 < county.trim().length())){
			map.put("dwdz1", county+"%");
		}
		if (null != zsqytype && !"".equals(zsqytype))
			map.put("zsqytype", zsqytype);
		
		map.put("start", start);
		map.put("limit", limit);
		map.put("passTer", 1);
		
		User user=usesrService.findUserById(userid);
		
		String deptCode = user.getDeptCode();
		String deptRole = user.getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//添加部门、部门职责查询条件
			map = Condition.getContiton(map, deptRole, deptCode, companyService,user.getLoginId(), "");
		}
		
		JSONArray jsons = new JSONArray();
		JSONObject json = null;
		try {
			int total = companyService.findTotal(map);//获取企业列表总条数
			int page= total/(limit);
			page = total%limit==0?page:(page+1);
			/**
			 * 分页方式获取企业列表
			 */
			List<Company> list = companyService.findCompanyList(map);
			if (list.size() > 0) {
				bd.setCode("0");
				bd.setMessage("");
				for (Company c : list) {
					json = new JSONObject();
					/**
					 * 返回企业id
					 */
					json.put("companyId", c.getId());
					/**
					 * 返回企业名称
					 */
					json.put("companyName", StringTools.NullToStr(c.getCompanyname(),""));
					/**
					 * 企业类型
					 */
				    json.put("qylx", null!=c.getQylx()?companyService.findCompanyTypeNameByKey(c.getQylx(),"4028e56c3ff0d189013ff0e6b99e000c"):"");
			    	
				    json.put("companyAddress", c.getDwdz() );//地址
				    json.put("companyFzr",c.getFddbr());//主要负责人
				    json.put("companyLxfs", c.getFddbrlxhm());//联系方式
				     
				    jsons.add(json);
				}
				bd.setContent(jsons.toString());
				bd.setTotal(total+"");
				bd.setPage(page+"");
			} else {
				bd.setCode("1");
				bd.setMessage("无数据");
			}

		} catch (Exception e) {
			bd.setCode("2");
			bd.setMessage("异常");
			e.printStackTrace();
		}
		return bd;
	}
}
