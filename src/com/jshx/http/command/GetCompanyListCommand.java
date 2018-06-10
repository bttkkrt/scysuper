package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

/**
 * 获取企业列表接口
 * @author 陆婷 2015-11-11
 *
 */
public class GetCompanyListCommand implements Command{
	private EntBaseInfoService entBaseInfoService= (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String enterpriseName = obj.getString("enterpriseName");//企业名称：enterpriseName
		String enterprisePossession = obj.getString("enterprisePossession");//乡镇区：enterprisePossession
		String enterpriseType = obj.getString("enterpriseType");//企业类型：enterpriseType
		String enterpriseCategory = obj.getString("enterpriseCategory");//行业分类：enterpriseCategory
		String enterpriseAddress = obj.getString("enterpriseAddress");//企业地址：enterpriseAddress

		
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		
		Map map = new HashMap();
		User user= userService.findUserById(userId);
		if(httpService.judgeRoleCode(userId, "A11"))//安监中队队长
		{
			map.put("gridManageteamCode", user.getDeptCode());
		}
		if(httpService.judgeRoleCode(userId, "A12"))//安监中队队员
		{
			map.put("gridManageId", userId);
		}
		map.put("basePass", "1");
		if(enterpriseName != null && !"".equals(enterpriseName))//企业名称：enterpriseName
		{
			map.put("enterpriseName", "%" + enterpriseName + "%");
		}
		if(enterprisePossession != null && !"".equals(enterprisePossession))//乡镇区：enterprisePossession
		{
			map.put("enterprisePossession",  enterprisePossession );
		}
		if(enterpriseType != null && !"".equals(enterpriseType))//企业类型：enterpriseType
		{
			map.put("enterpriseType",  "%" +enterpriseType + "%");
		}
		if(enterpriseCategory != null && !"".equals(enterpriseCategory))//行业分类：enterpriseCategory
		{
			map.put("enterpriseCategory",  enterpriseCategory );
		}
		if(enterpriseAddress != null && !"".equals(enterpriseAddress))//企业地址：enterpriseAddress
		{
			map.put("enterpriseAddress", "%" + enterpriseAddress + "%");
		}
		
		List<EntBaseInfo> list  = entBaseInfoService.getEntBaseInfoListByUserAndType(map, s, l);
		int total=entBaseInfoService.getEntBaseInfoListSizeByUserAndType(map);
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(EntBaseInfo bean:list){
						   JSONObject jo = new JSONObject();
						   jo.put("id", StringTools.NullToStr(bean.getId(),""));//主键
						   jo.put("name", StringTools.NullToStr(bean.getEnterpriseName(),""));//企业名称
						   jo.put("gridName", StringTools.NullToStr(bean.getGridName(),""));//网格
						   jo.put("possession", StringTools.NullToStr(bean.getEnterprisePossessionName(),""));//企业属地
						   jo.put("possessionId", StringTools.NullToStr(bean.getEnterprisePossession(),""));//企业属地
							if(bean.getEnterpriseNature() != null && !"".equals(bean.getEnterpriseNature()))
							{
								map.put("codeName", "企业性质");
								map.put("itemValue", bean.getEnterpriseNature());
								jo.put("nature", codeService.findCodeValueByMap(map).getItemText());//企业性质
							}
							else
							{
								jo.put("nature", "");//企业性质
							}
						   String qyfl = "";
							if(bean.getEnterpriseType() != null && !"".equals(bean.getEnterpriseType()))
							{
								String[] qyfls = bean.getEnterpriseType().replaceAll(" ", "").split(",");
								for(String ss:qyfls)
								{
									map.put("codeName", "企业分类");
									map.put("itemValue", ss);
									qyfl += codeService.findCodeValueByMap(map).getItemText() + ",";
								}
								if(qyfl.length() != 0)
								{
									qyfl = qyfl.substring(0,qyfl.length()-1);
								}
							}
							jo.put("type", qyfl);//企业分类
							
							if(bean.getEnterpriseCategory() != null && !"".equals(bean.getEnterpriseCategory()))
							{
								map.put("codeName", "行业类别");
								map.put("itemValue", bean.getEnterpriseCategory());
								jo.put("category", codeService.findCodeValueByMap(map).getItemText());//行业类别
							}
							else
							{
								jo.put("category", "");//行业类别
							}
							
							map.put("linkId",bean.getLinkId());
							map.put("mkType", "qyxx");
							map.put("picType","cqtp");
							DataBean da = httpService.getPhotoListByType(map);
							jo.put("cqtp",null==da.getRname()?"":da.getRname());
							
						   ja.add(jo);
					}
					br.setCode("0");
					br.setMessage("成功");
					br.setTotal(total+"");
					br.setPage(page+"");
					br.setContent(ja.toString());
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
}
