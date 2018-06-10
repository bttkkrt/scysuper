package com.jshx.http.command;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

/**
 * 保存处理职能部门
 * @author fq 2015-12-7
 *
 */
public class SaveDealDeptCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private TroManService troManService= (TroManService) SpringContextHolder.getBean("troManService");
	private DeptService deptService= (DeptService) SpringContextHolder.getBean("deptService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id	= obj.getString("id");//隐患id
		String dealDeptCode	= obj.getString("dealDeptCode");//处理职能部门code
		try {
			if(httpService.judgeRoleCode(userId, "A17")){//判断是否是安委会办公室角色
				TroMan troMan=troManService.getById(id);
				if(dealDeptCode.equals("002001")){
					troMan.setIfNeedAj("1");
					if("1".equals(troMan.getTroubleLevel())){//一般隐患 直接整改 不需审核
						troMan.setRectificationState("6");
					}else{
						troMan.setRectificationState("2");//不上报安委会 需中队长审核
					}
					troMan.setDealDeptId(dealDeptCode);
					String deptName=deptService.findDeptByDeptCode(dealDeptCode).getDeptName();
					troMan.setDealDeptName(deptName);
					troManService.update(troMan);
				}else{
					troMan.setIfNeedAj("0");
					troMan.setDealDeptId(dealDeptCode);
					String deptName=deptService.findDeptByDeptCode(dealDeptCode).getDeptName();
					troMan.setDealDeptName(deptName);
					troMan.setRectificationState("4");
					troManService.update(troMan);
				}
				br.setCode("0");
				br.setMessage("保存成功");
			}else{
				br.setCode("1");
				br.setMessage("无此权限");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		} 
		
		
		return br;
	}
}
