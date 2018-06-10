package com.jshx.safeInspectDistribute.taskEngine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.service.DistributeItemService;
import com.jshx.module.admin.service.UserService;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class DistributeTask {
	private static Log logger = LogFactory.getLog(DistributeTask.class);
	//private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");

	@Autowired 
	DistributeItemService distributeItemService;
	@Autowired
	private SafeInspectDistributeService safeInspectDistributeService;
	@Autowired
	private UserService userService;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	private int num = 1;
	/*
	 * 根据safe_inspect_distribute中数据
	 * 	生成一条检查任务（有任务日期和用户id做唯一区分）
	 *		每条检查任务下生成关联的检查项
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void safeDistTask() {	
		//每天初始化参数
		Date today = new Date();
		num = 1;
		//获取原安全检查任务
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("taskStatus", "0");//获取已启用任务源
		List<SafeInspectDistribute> list = safeInspectDistributeService.findSafeInspectDistribute(paraMap);
		
		for (SafeInspectDistribute sid : list) {
			//当天在开始时间之前或结束时间之后，则不分配任务
			if((null != sid.getStartDate() && today.before(sid.getStartDate())) || 
			   (null != sid.getEndDate() && today.after(sid.getEndDate()))){
				continue;
			}
			String[] userIdArray = sid.getPersonnel().split("\\,");
			String cycleFlag = sid.getCycleFlag();//获取检查周期标识  1：周，2：月
			int inspectNum = Integer.parseInt(sid.getInspectNum());//每天检查次数
			
			Map<String, Object> distParaMap = new HashMap<String, Object>();
			distParaMap.put("distributeId", sid.getId());
			distParaMap.put("isinspect", "0");
			List<DistributeItem> distList = distributeItemService.findDistributeItem(distParaMap);
			if("any".equals(sid.getCycleValue().trim())){//如果为周期内任意时间完成
				if("1".equals(cycleFlag) && "2".equals(getDayOfWeek())){//每周一分配任务
					distUserTask(userIdArray, inspectNum, sid, "any", distList);
				}else if("2".equals(cycleFlag) && "01".equals(getDayOfMonth())){//每月一号分配任务
					distUserTask(userIdArray, inspectNum, sid, "any", distList);
				}
			}else if("1".equals(cycleFlag)){//按周分配任务
				if(sid.getCycleValue().contains(getDayOfWeek())){
					distUserTask(userIdArray, inspectNum, sid, getDayOfWeek(), distList);
				}
			}else if("2".equals(cycleFlag)){//按月分配任务
				if(sid.getCycleValue().contains(getDayOfMonth())){
					distUserTask(userIdArray, inspectNum, sid, getDayOfMonth(), distList);
				}
			}
		}
	}
	
	private void distUserTask(String[] userIdArray,int inspectNum,SafeInspectDistribute sid,String cycleValue,List<DistributeItem> distList){
		for(int i=0; i<userIdArray.length; i++){//循环检查人员
			String personnelDeptCode="";
			personnelDeptCode = userService.findUserById(userIdArray[i].trim()).getDeptCode();
			String serialNum = this.getNum();
			for(int k=1; k<=inspectNum; k++){//循环检查次数
				SafeInspectDistribute tempSid = new SafeInspectDistribute();
				tempSid.setTaskStatus("1");			//0=初始化任务，1=某天某人未检查任务
				tempSid.setCreateTime(new Date());
				tempSid.setDeptId(sid.getDeptId());
				tempSid.setDelFlag(0);
				tempSid.setInspectType(sid.getInspectType());
				tempSid.setTitle(sid.getTitle());
				tempSid.setQuarters(sid.getQuarters());
				tempSid.setCycleFlag(sid.getCycleFlag());
				tempSid.setCycleValue(cycleValue);
				tempSid.setPersonnel(userIdArray[i].trim());
				tempSid.setCompanyFlag(sid.getCompanyFlag());
				tempSid.setRootId(sid.getId());
				tempSid.setTaskTime(df.format(new Date()));
				tempSid.setCount("0");
				tempSid.setInspectNum(k+"");//检查次数
				tempSid.setPersonnelDeptCode(personnelDeptCode);
				tempSid.setSerialNum(serialNum);
				
				safeInspectDistributeService.save(tempSid);
			
				for (DistributeItem di : distList) {
					DistributeItem tempDi = new DistributeItem();
					tempDi.setDelFlag(0);
					tempDi.setCreateTime(new Date());
					tempDi.setDistributeId(tempSid.getId());//某人某天检查任务所对应的ID
					tempDi.setItem(di.getItem());
					tempDi.setRequirement(di.getRequirement());
					tempDi.setIsinspect("1");//检查状态标识:0：初始化 1：未检查   2符合  3：不符合
					tempDi.setImage(di.getImage());
					tempDi.setCount(di.getCount());
					tempDi.setRemark(di.getRemark());
					tempDi.setUserId(userIdArray[i].trim());
					tempDi.setTaskTime(df.format(new Date()));
					tempDi.setIndexNum(di.getIndexNum());
					distributeItemService.save(tempDi);
				}
			}
		}
	}
	
	private String getDayOfWeek(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek+"";
	}
	private String getDayOfMonth(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String ds = df.format(new Date());
		String dayOfMonth = ds.substring(ds.length()-2,ds.length());
		return dayOfMonth;
	}
	private String getNum(){
		String ds = df.format(new Date());
		String str = ds.replace("-","");
		String numStr = "" + num++;
		for(int i=0; i<9-numStr.length(); i++){
			str+="0";
		}
		str +=numStr;
		return str;
	}
	public static void main(String[] args) {
		int num = 1;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
		
		String ds = df.format(new Date());
		ds = ds.substring(ds.length()-2,ds.length());
		String str = ds.replace("-","");
		
		System.out.println(str);
		String numStr = num+"";
		for(int i=0; i<9-numStr.length(); i++){
			str+="0";
		}
		str +=numStr;
		System.out.println(str);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		System.out.println(dayOfWeek);// new Date()为获取当前系统时间
	}
}
