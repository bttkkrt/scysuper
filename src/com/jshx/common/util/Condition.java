package com.jshx.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SysPropertiesUtil;


public class Condition {
	//private static companyService companyService = (companyService) SpringContextHolder.getBean("companyService");
	
	private static int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
	private static int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
	private static int villageDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("villageDeptCodeLength"));
	private static String qiyeCode= SysPropertiesUtil.getProperty("qiyeCode");
	
	public static Map<String, Object> getContiton(Map<String, Object> paraMap, String deptRole, String deptCode, 
												  CompanyService companyService, String userLoginId,String searchCondition){
		
		
		if(qiyeCode.equals(deptRole)){//企业角色
			CompanyBackUp company = companyService.getCompanyByLoginId(userLoginId);
			paraMap.put("qyid", company.getId());
		}else{//安监局角色
			//根据所在部门进行数据量权限过滤
			if(deptCode.length() == countyDeptCodeLength){//市级部门
				String tempDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
				paraMap.put("deptCode",tempDeptCode+ "%");
//				paraMap.put("ifzsqy", "1");
			}else if(deptCode.length() == townDeptCodeLength){//区县级部门
				String tempDeptCode = deptCode.substring(0, townDeptCodeLength-3);
				paraMap.put("deptCode",tempDeptCode+ "%");
				paraMap.put("isNotZsqy", "true");
			}else if(deptCode.length() == villageDeptCodeLength){//村镇级部门
				paraMap.put("deptCode",deptCode);
				paraMap.put("isNotZsqy", "true");
			}
			//根据部门角色进行查询条件过滤
			/*if(null != deptRole){
				String val = "";
				String[] strArr = deptRole.split(",");
				for(int i=0; i<strArr.length;i++){
					String tempStr = strArr[i];
					val += searchCondition + " like '%"+tempStr+"%' or ";
				}
				val = val.substring(searchCondition.length()+5,val.length()-3);
				paraMap.put("companyType",val);
			}*/
			//根据部门判断所辖企业
			if(!"".equals(searchCondition)){
				String[] roleNames = searchCondition.split(",");
				for (int i = 0; i < roleNames.length; i++) {
					if(roleNames[i].contains("一")){
						paraMap.put("iffmksjyqy", "1");
					}
					if(roleNames[i].contains("二")){
						paraMap.put("ifwhpqylx","1");
					}
					if(roleNames[i].contains("三")){
						paraMap.put("iffmgmqylx", "1");
					}
				}
			}
			/*List<String> hyflList = new ArrayList<String>();
			for (int i = 1; i <= 51; i++) {
				String temp = ""+i;
				if(i<10){
					temp = "0"+i;
				}
				hyflList.add(""+temp);
			}
			List<String> yichuList = new ArrayList<String>();
			yichuList.add("06");
			yichuList.add("07");
			yichuList.add("08");
			yichuList.add("09");
			yichuList.add("10");
			yichuList.add("11");
			yichuList.add("12");
			List<String> erchuList = new ArrayList<String>();
			erchuList.add("25");
			erchuList.add("26");
			erchuList.add("28");
			
			if("002010".equals(deptCode)){//监管一处
				paraMap.put("hyflTwoLevel", yichuList);
			}else if("002001".equals(deptCode)){//监管二处
				paraMap.put("hyflTwoLevel", erchuList);
			}else if("002011".equals(deptCode)){//监管三出
				hyflList.removeAll(yichuList);
				hyflList.removeAll(erchuList);
				paraMap.put("hyflTwoLevel", hyflList);
			}*/
		}
		return paraMap;
	}
}
