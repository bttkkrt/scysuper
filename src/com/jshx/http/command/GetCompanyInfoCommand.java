package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.companyInfoPlan.entity.CompanyInfoPlan;
import com.jshx.companyInfoPlan.service.CompanyInfoPlanService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qywghjdgl.entity.ComGriMan;
import com.jshx.qywghjdgl.service.ComGriManService;

/**
 * 获取企业详情接口
 * @author 陆婷 2015-11-11
 *
 */
public class GetCompanyInfoCommand implements Command{
	private EntBaseInfoService entBaseInfoService= (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ComGriManService comGriManService= (ComGriManService) SpringContextHolder.getBean("comGriManService");
	private CompanyInfoPlanService companyInfoPlanService= (CompanyInfoPlanService) SpringContextHolder.getBean("companyInfoPlanService");
	
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String companyId = obj.getString("companyId");//获取企业编号
		
		JSONObject json = new JSONObject();
		try {
			EntBaseInfo entBaseInfo = entBaseInfoService.getById(companyId);
			CompanyInfoPlan companyInfoPlan = companyInfoPlanService.getById(companyId);
			if(entBaseInfo != null && entBaseInfo.getId() != null)
			{
				json.put("id", StringTools.NullToStr(entBaseInfo.getId(),""));
				json.put("name", StringTools.NullToStr(entBaseInfo.getEnterpriseName(),""));//企业名称
				json.put("code", StringTools.NullToStr(entBaseInfo.getEnterpriseCode(),""));//组织机构代码
				
				json.put("regisNumber", StringTools.NullToStr(entBaseInfo.getRegistrationNumber(),""));//工商注册号;
				json.put("address", StringTools.NullToStr(entBaseInfo.getEnterpriseAddress(),""));//注册地址
				
				json.put("factoryAddress", StringTools.NullToStr(entBaseInfo.getFactoryAddress(),""));//生产经营地址
				
				json.put("possession", StringTools.NullToStr(entBaseInfo.getEnterprisePossessionName(),""));//企业属地
				json.put("zipCode", StringTools.NullToStr(entBaseInfo.getEnterpriseZipcode(),""));//邮政编码
				
				Map map = new HashMap();
				if(entBaseInfo.getEnterpriseNature() != null && !"".equals(entBaseInfo.getEnterpriseNature()))
				{
					map.put("codeName", "企业性质");
					map.put("itemValue", entBaseInfo.getEnterpriseNature());
					json.put("nature", codeService.findCodeValueByMap(map).getItemText());//企业性质
				}
				else
				{
					json.put("nature", "");//企业性质
				}
				
				if(entBaseInfo.getEnterpriseScale() != null && !"".equals(entBaseInfo.getEnterpriseScale()))
				{
					map.put("codeName", "企业规模");
					map.put("itemValue", entBaseInfo.getEnterpriseScale());
					json.put("scale", codeService.findCodeValueByMap(map).getItemText());//企业规模
				}
				else
				{
					json.put("scale", "");//企业规模
				}
				
				String qyfl = "";
				if(entBaseInfo.getEnterpriseType() != null && !"".equals(entBaseInfo.getEnterpriseType()))
				{
					String[] qyfls = entBaseInfo.getEnterpriseType().replaceAll(" ", "").split(",");
					for(String s:qyfls)
					{
						map.put("codeName", "企业分类");
						map.put("itemValue", s);
						qyfl += codeService.findCodeValueByMap(map).getItemText() + ",";
					}
					if(qyfl.length() != 0)
					{
						qyfl = qyfl.substring(0,qyfl.length()-1);
					}
				}
				json.put("type", qyfl);//企业分类
				
				if(entBaseInfo.getEnterpriseCategory() != null && !"".equals(entBaseInfo.getEnterpriseCategory()))
				{
					map.put("codeName", "行业类别");
					map.put("itemValue", entBaseInfo.getEnterpriseCategory());
					json.put("category", codeService.findCodeValueByMap(map).getItemText());//行业类别
				}
				else
				{
					json.put("category", "");//行业类别
				}
				
				json.put("scope", StringTools.NullToStr(entBaseInfo.getEnterpriseScope(),""));//经营范围
				
				json.put("nation", StringTools.NullToStr(entBaseInfo.getEnterpriseNationnality(),""));//投资方国籍
				json.put("legalName", StringTools.NullToStr(entBaseInfo.getEnterpriseLegalName(),""));//法人姓名
				
				if(entBaseInfo.getEnterpriseLegalSex() != null && !"".equals(entBaseInfo.getEnterpriseLegalSex()))
				{
					map.put("codeName", "性别");
					map.put("itemValue", entBaseInfo.getEnterpriseLegalSex());
					json.put("legalSex", codeService.findCodeValueByMap(map).getItemText());//法人性别
				}
				else
				{
					json.put("legalSex", "");//法人性别
				}
				json.put("legalAge", StringTools.NullToStr(entBaseInfo.getEnterpriseLegalAge(),""));//法人年龄
				json.put("legalPhone", StringTools.NullToStr(entBaseInfo.getEnterpriseLegalPhone(),""));//法人联系电话
				json.put("legalCardNum", StringTools.NullToStr(entBaseInfo.getEnterpriseLegalCardnum(),""));//法人证件号码
				json.put("legalEmail", StringTools.NullToStr(entBaseInfo.getEnterpriseLegalEmail(),""));//法人电子邮箱
				json.put("legalZw", StringTools.NullToStr(entBaseInfo.getEnterpriseLegalZw(),""));//法人职务
				
				json.put("foundDate", null != entBaseInfo.getEnterpriseFoundDate()?new SimpleDateFormat("yyyy-MM-dd").format(entBaseInfo.getEnterpriseFoundDate()):"");//企业设立日期
				json.put("productDate", null != entBaseInfo.getEnterpriseProductDate()?new SimpleDateFormat("yyyy-MM-dd").format(entBaseInfo.getEnterpriseProductDate()):"");//企业投产日期
				
				String zcdw = "";
				map.put("codeName", "货币种类");
				if(entBaseInfo.getEnterpriseRegisterMoneyDw() != null && !"".equals(entBaseInfo.getEnterpriseRegisterMoneyDw()))
				{
					map.put("itemValue", entBaseInfo.getEnterpriseRegisterMoneyDw());
					zcdw = codeService.findCodeValueByMap(map).getItemText();
				}
				
				json.put("registerMoney", StringTools.NullToStr(entBaseInfo.getEnterpriseRegisterMoney(),"")+zcdw);//注册资本
				
				if(entBaseInfo.getEnterpriseInvestMoneyDw() != null && !"".equals(entBaseInfo.getEnterpriseInvestMoneyDw()))
				{
					map.put("itemValue", entBaseInfo.getEnterpriseInvestMoneyDw());
					zcdw = codeService.findCodeValueByMap(map).getItemText();
				}
				
				json.put("investMoney", StringTools.NullToStr(entBaseInfo.getEnterpriseInvestMoney(),"")+zcdw);//投资总额
				
				if(entBaseInfo.getEnterpriseFixedassetMoneyDw() != null && !"".equals(entBaseInfo.getEnterpriseFixedassetMoneyDw()))
				{
					map.put("itemValue", entBaseInfo.getEnterpriseFixedassetMoneyDw());
					zcdw = codeService.findCodeValueByMap(map).getItemText();
				}
				json.put("fixMoney", StringTools.NullToStr(entBaseInfo.getEnterpriseFixedassetMoney(),"")+zcdw);//固定资产总额
				
				json.put("floorArea", StringTools.NullToStr(entBaseInfo.getEnterpriseFloorArea(),""));//占地面积
				json.put("buildArea", StringTools.NullToStr(entBaseInfo.getEnterpriseBuildArea(),""));//建筑面积
				json.put("officeArea", StringTools.NullToStr(entBaseInfo.getEnterpriseOfficeArea(),""));//办公楼面积
				json.put("workshopArea", StringTools.NullToStr(entBaseInfo.getEnterpriseWorkshopArea(),""));//车间厂房面积
				json.put("weahouseArea", StringTools.NullToStr(entBaseInfo.getEnterpriseWearhouseArea(),""));//仓库面积
				
				json.put("houseOwner", "");//出租方
				json.put("ownerTel", "");//出租方联系方式
				
				if(entBaseInfo.getEnterprisWorkshopOwn() != null && !"".equals(entBaseInfo.getEnterprisWorkshopOwn()))
				{
					map.put("codeName", "厂房归属");
					map.put("itemValue", entBaseInfo.getEnterprisWorkshopOwn());
					json.put("workshopOwn", codeService.findCodeValueByMap(map).getItemText());//厂房归属
					if(entBaseInfo.getEnterprisWorkshopOwn().equals("2"))
					{
						json.put("houseOwner", StringTools.NullToStr(entBaseInfo.getHouseOwner(),""));//出租方
						json.put("ownerTel", StringTools.NullToStr(entBaseInfo.getOwnerTel(),""));//出租方联系方式
					}
				}
				else
				{
					json.put("workshopOwn", "");//厂房归属
				}
				
				
				json.put("staffCount", StringTools.NullToStr(entBaseInfo.getEnterpriseStaffCount(),""));//员工总数
				json.put("managerCount", StringTools.NullToStr(entBaseInfo.getEnterpriseManagerCount(),""));//管理人员数
				json.put("workCount", StringTools.NullToStr(entBaseInfo.getEnterpriseWorkerCount(),""));//工人数
				json.put("gridTeamName", StringTools.NullToStr(entBaseInfo.getGridManageteamName(),""));//网格管理中队
				
				if(entBaseInfo.getGrid() != null && !"".equals(entBaseInfo.getGrid()))
				{
					ComGriMan comGriMan = comGriManService.getById(entBaseInfo.getGrid());
					json.put("gridManagerName", StringTools.NullToStr(comGriMan.getGridManagePersonName(),""));//网格管理人员
				}
				else
				{
					json.put("gridManagerName", "");//网格管理人员
				}
				
				json.put("gridName", StringTools.NullToStr(entBaseInfo.getGridName(),""));//所属网格
				
				map.put("linkId",entBaseInfo.getLinkId());
				map.put("mkType", "qyxx");
				map.put("picType","cqtp");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("cqtp",null==bean.getRname()?"":bean.getRname());
				
				json.put("flag","0");
				
				br.setCode("0");
				br.setMessage("成功");
				br.setContent(json.toString());
			}
			else if(companyInfoPlan != null)
			{
				Map map = new HashMap();
				map.put("enterpriseName", companyInfoPlan.getCompanyName());
				EntBaseInfo ent = entBaseInfoService.findEntBaseInfoByMap(map);
				if(ent != null && ent.getId() != null)
				{
					json.put("id", StringTools.NullToStr(ent.getId(),""));
					json.put("name", StringTools.NullToStr(ent.getEnterpriseName(),""));//企业名称
					json.put("code", StringTools.NullToStr(ent.getEnterpriseCode(),""));//组织机构代码
					
					json.put("regisNumber", StringTools.NullToStr(ent.getRegistrationNumber(),""));//工商注册号;
					json.put("address", StringTools.NullToStr(ent.getEnterpriseAddress(),""));//注册地址
					
					json.put("factoryAddress", StringTools.NullToStr(ent.getFactoryAddress(),""));//生产经营地址
					
					json.put("possession", StringTools.NullToStr(ent.getEnterprisePossessionName(),""));//企业属地
					json.put("zipCode", StringTools.NullToStr(ent.getEnterpriseZipcode(),""));//邮政编码
					
					if(ent.getEnterpriseNature() != null && !"".equals(ent.getEnterpriseNature()))
					{
						map.put("codeName", "企业性质");
						map.put("itemValue", ent.getEnterpriseNature());
						json.put("nature", codeService.findCodeValueByMap(map).getItemText());//企业性质
					}
					else
					{
						json.put("nature", "");//企业性质
					}
					
					if(ent.getEnterpriseScale() != null && !"".equals(ent.getEnterpriseScale()))
					{
						map.put("codeName", "企业规模");
						map.put("itemValue", ent.getEnterpriseScale());
						json.put("scale", codeService.findCodeValueByMap(map).getItemText());//企业规模
					}
					else
					{
						json.put("scale", "");//企业规模
					}
					
					String qyfl = "";
					if(ent.getEnterpriseType() != null && !"".equals(ent.getEnterpriseType()))
					{
						String[] qyfls = ent.getEnterpriseType().replaceAll(" ", "").split(",");
						for(String s:qyfls)
						{
							map.put("codeName", "企业分类");
							map.put("itemValue", s);
							qyfl += codeService.findCodeValueByMap(map).getItemText() + ",";
						}
						if(qyfl.length() != 0)
						{
							qyfl = qyfl.substring(0,qyfl.length()-1);
						}
					}
					json.put("type", qyfl);//企业分类
					
					if(ent.getEnterpriseCategory() != null && !"".equals(ent.getEnterpriseCategory()))
					{
						map.put("codeName", "行业类别");
						map.put("itemValue", ent.getEnterpriseCategory());
						json.put("category", codeService.findCodeValueByMap(map).getItemText());//行业类别
					}
					else
					{
						json.put("category", "");//行业类别
					}
					
					json.put("scope", StringTools.NullToStr(ent.getEnterpriseScope(),""));//经营范围
					
					json.put("nation", StringTools.NullToStr(ent.getEnterpriseNationnality(),""));//投资方国籍
					json.put("legalName", StringTools.NullToStr(ent.getEnterpriseLegalName(),""));//法人姓名
					
					if(ent.getEnterpriseLegalSex() != null && !"".equals(ent.getEnterpriseLegalSex()))
					{
						map.put("codeName", "性别");
						map.put("itemValue", ent.getEnterpriseLegalSex());
						json.put("legalSex", codeService.findCodeValueByMap(map).getItemText());//法人性别
					}
					else
					{
						json.put("legalSex", "");//法人性别
					}
					json.put("legalAge", StringTools.NullToStr(ent.getEnterpriseLegalAge(),""));//法人年龄
					json.put("legalPhone", StringTools.NullToStr(ent.getEnterpriseLegalPhone(),""));//法人联系电话
					json.put("legalCardNum", StringTools.NullToStr(ent.getEnterpriseLegalCardnum(),""));//法人证件号码
					json.put("legalEmail", StringTools.NullToStr(ent.getEnterpriseLegalEmail(),""));//法人电子邮箱
					json.put("legalZw", StringTools.NullToStr(ent.getEnterpriseLegalZw(),""));//法人职务
					
					json.put("foundDate", null != ent.getEnterpriseFoundDate()?new SimpleDateFormat("yyyy-MM-dd").format(ent.getEnterpriseFoundDate()):"");//企业设立日期
					json.put("productDate", null != ent.getEnterpriseProductDate()?new SimpleDateFormat("yyyy-MM-dd").format(ent.getEnterpriseProductDate()):"");//企业投产日期
					
					String zcdw = "";
					map.put("codeName", "货币种类");
					if(ent.getEnterpriseRegisterMoneyDw() != null && !"".equals(ent.getEnterpriseRegisterMoneyDw()))
					{
						map.put("itemValue", ent.getEnterpriseRegisterMoneyDw());
						zcdw = codeService.findCodeValueByMap(map).getItemText();
					}
					
					json.put("registerMoney", StringTools.NullToStr(ent.getEnterpriseRegisterMoney(),"")+zcdw);//注册资本
					
					if(ent.getEnterpriseInvestMoneyDw() != null && !"".equals(ent.getEnterpriseInvestMoneyDw()))
					{
						map.put("itemValue", ent.getEnterpriseInvestMoneyDw());
						zcdw = codeService.findCodeValueByMap(map).getItemText();
					}
					
					json.put("investMoney", StringTools.NullToStr(ent.getEnterpriseInvestMoney(),"")+zcdw);//投资总额
					
					if(ent.getEnterpriseFixedassetMoneyDw() != null && !"".equals(ent.getEnterpriseFixedassetMoneyDw()))
					{
						map.put("itemValue", ent.getEnterpriseFixedassetMoneyDw());
						zcdw = codeService.findCodeValueByMap(map).getItemText();
					}
					json.put("fixMoney", StringTools.NullToStr(ent.getEnterpriseFixedassetMoney(),"")+zcdw);//固定资产总额
					
					json.put("floorArea", StringTools.NullToStr(ent.getEnterpriseFloorArea(),""));//占地面积
					json.put("buildArea", StringTools.NullToStr(ent.getEnterpriseBuildArea(),""));//建筑面积
					json.put("officeArea", StringTools.NullToStr(ent.getEnterpriseOfficeArea(),""));//办公楼面积
					json.put("workshopArea", StringTools.NullToStr(ent.getEnterpriseWorkshopArea(),""));//车间厂房面积
					json.put("weahouseArea", StringTools.NullToStr(ent.getEnterpriseWearhouseArea(),""));//仓库面积
					
					json.put("houseOwner", "");//出租方
					json.put("ownerTel", "");//出租方联系方式
					
					if(ent.getEnterprisWorkshopOwn() != null && !"".equals(ent.getEnterprisWorkshopOwn()))
					{
						map.put("codeName", "厂房归属");
						map.put("itemValue", ent.getEnterprisWorkshopOwn());
						json.put("workshopOwn", codeService.findCodeValueByMap(map).getItemText());//厂房归属
						if(ent.getEnterprisWorkshopOwn().equals("2"))
						{
							json.put("houseOwner", StringTools.NullToStr(ent.getHouseOwner(),""));//出租方
							json.put("ownerTel", StringTools.NullToStr(ent.getOwnerTel(),""));//出租方联系方式
						}
					}
					else
					{
						json.put("workshopOwn", "");//厂房归属
					}
					
					
					json.put("staffCount", StringTools.NullToStr(ent.getEnterpriseStaffCount(),""));//员工总数
					json.put("managerCount", StringTools.NullToStr(ent.getEnterpriseManagerCount(),""));//管理人员数
					json.put("workCount", StringTools.NullToStr(ent.getEnterpriseWorkerCount(),""));//工人数
					json.put("gridTeamName", StringTools.NullToStr(ent.getGridManageteamName(),""));//网格管理中队
					
					if(ent.getGrid() != null && !"".equals(ent.getGrid()))
					{
						ComGriMan comGriMan = comGriManService.getById(ent.getGrid());
						json.put("gridManagerName", StringTools.NullToStr(comGriMan.getGridManagePersonName(),""));//网格管理人员
					}
					else
					{
						json.put("gridManagerName", "");//网格管理人员
					}
					
					json.put("gridName", StringTools.NullToStr(ent.getGridName(),""));//所属网格
					
					map.put("linkId",ent.getLinkId());
					map.put("mkType", "qyxx");
					map.put("picType","cqtp");
					DataBean bean = httpService.getPhotoListByType(map);
					json.put("cqtp",null==bean.getRname()?"":bean.getRname());
					json.put("flag","0");
				}
				else
				{
					json.put("id", StringTools.NullToStr(companyInfoPlan.getId(),""));
					json.put("name", StringTools.NullToStr(companyInfoPlan.getCompanyName(),""));//企业名称
					
					json.put("factoryAddress", StringTools.NullToStr(companyInfoPlan.getAddress(),""));//生产经营地址
					
					json.put("legalName", StringTools.NullToStr(companyInfoPlan.getContact(),""));//法人姓名
					
					json.put("legalPhone", StringTools.NullToStr(companyInfoPlan.getContactPhone(),""));//法人联系电话
					json.put("legalEmail", StringTools.NullToStr(companyInfoPlan.getEmail(),""));//法人电子邮箱
					json.put("flag","1");
				}
				
				br.setCode("0");
				br.setMessage("成功");
				br.setContent(json.toString());
			}
			else
			{
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
