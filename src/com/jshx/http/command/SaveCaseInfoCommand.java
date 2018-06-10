package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

/**
 * 获取安全生产保险投保情况列表接口
 * @author 陆婷 2015-11-5
 *
 */
public class SaveCaseInfoCommand implements Command{
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String caseName	= obj.getString("caseName");//案件名称
		String caseTime	= obj.getString("caseTime");//案件时间
		
		String areaId	= obj.getString("areaId");//案发区域
		String companyName = obj.getString("companyName");//企业名称
		String companyId = obj.getString("companyId");//企业id
		String caseSource = obj.getString("caseSource");//案件来源
		String personType	= obj.getString("personType");//处罚对象
		String laTime = obj.getString("laTime");//立案时间
		
		if(personType.equals("个人"))
		{
			personType = "1";
		}
		else if(personType.equals("企业"))
		{
			personType = "2";
		}
		String miborNum	= obj.getString("miborNum");//轻伤人数（caseSource=1）
		String injuriesNum	= obj.getString("injuriesNum");//重伤人数（caseSource=1）
		String dieNum = obj.getString("dieNum");//死亡人数（caseSource=1）
		String accidentLevel = obj.getString("accidentLevel");//事故级别（caseSource=1）
		String accidentCategory	= obj.getString("accidentCategory");//事故类别（caseSource=1）
		String person = obj.getString("person");//被处罚人（personType=1）
		String sfzh	= obj.getString("sfzh");//身份证号（personType=1）
		String address = obj.getString("address");//家庭住址（personType=1）
		
		String fddbr = obj.getString("fddbr");//法定代表人（personType=2）
		String xyhm = obj.getString("xyhm");//统一社会信用代码（personType=2）
		
		String age	= obj.getString("age");//年龄
		String sex	= obj.getString("sex");//性别
		String tele	= obj.getString("tele");//电话
		String zw = obj.getString("zw");//职务
		String companyAddress = obj.getString("companyAddress");//单位住址
		String zipCode = obj.getString("zipCode");//邮编
		
		String fineType = obj.getString("fineType");//处罚类型（'0':'违法处罚','1':'事故处罚'）
		
		String undertakerId	= obj.getString("undertakerId");//承办人，一个
		
		String caseCause = obj.getString("caseCause");//案由
		String personCondition = obj.getString("personCondition");//当事人基本情况
		String caseCondition = obj.getString("caseCondition");//案件基本情况
		
		String undertakerComment = obj.getString("undertakerComment");//承办人意见
		

		try {
			CaseInfo ca = new CaseInfo();
			ca.setCaseName(caseName);
			ca.setCaseTime(new SimpleDateFormat("yyyy-MM-dd").parse(caseTime));
			ca.setAreaId(areaId);
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", areaId);
			ca.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			ca.setCompanyId(companyId);
			ca.setCompanyName(companyName);
			ca.setCaseSource(caseSource);
			ca.setPersonType(personType);
			
			if(caseSource != null && caseSource.equals("1"))
			{
				ca.setMiborNum(miborNum);
				ca.setInjuriesNum(injuriesNum);
				ca.setDieNum(dieNum);
				ca.setAccidentLevel(accidentLevel);
				ca.setAccidentCategory(accidentCategory);
			}
			if(personType != null && !"".equals(personType))
			{
				if(personType.equals("1"))
				{
					ca.setPerson(person);
					ca.setSfzh(sfzh);
					ca.setAddress(address);
				}
				else if(personType.equals("2"))
				{
					ca.setFddbr(fddbr);
					ca.setXyhm(xyhm);
				}
			}
			ca.setZw(zw);
			ca.setAge(age);
			ca.setSex(sex);
			ca.setTele(tele);
			ca.setZipCode(zipCode);
			ca.setCompanyAddress(companyAddress);
			ca.setFineType(fineType);
			
			ca.setUndertakerId(undertakerId);
			String undertakerName = "";
			undertakerName += userService.findUserById(userId).getDisplayName() + ",";
			if(ca.getUndertakerId() != null)
			{
				User user = userService.findUserById(ca.getUndertakerId().trim());
				undertakerName += user.getDisplayName();
			}
			ca.setUndertakerName(undertakerName);
			ca.setCaseCause(caseCause);
			ca.setCaseCondition(caseCondition);
			ca.setPersonCondition(personCondition);
			ca.setUndertakerComment(undertakerComment);
			
			User user = userService.findUserById(userId);
			ca.setDeptId(user.getDept().getId());
			ca.setCreateUserID(userId);
			ca.setCreateTime(new Date());
			ca.setDelFlag(0);
			ca.setCaseStatus("8");
			ca.setUndertakerName1(user.getDisplayName());
			ca.setLaTime(new SimpleDateFormat("yyyy-MM-dd").parse(laTime));
			String day[] = new SimpleDateFormat("yyyy-MM-dd").format(ca.getLaTime()).split("-");
			ca.setGlh(day[0]);
			
			caseInfoService.save(ca);
			br.setCode("0");
			br.setMessage("成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		} 
		
		
		return br;
	}
}
