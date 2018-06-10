package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.activiti.service.ActivityManageService;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.utils.DateUtils;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;

/**
 * 上报现场检查隐患清单
 *  zhangzhouhua 2016-08-02
 */

public class SaveYhqdCommand implements Command
{

	private YhqdService  yhqdService= (YhqdService) SpringContextHolder.getBean("yhqdService");

	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	private ActivityManageService activityManageService = (ActivityManageService) SpringContextHolder.getBean("activityManageService");
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String userId = json.getString("userId"); 
		String basicId = json.getString("checkbasicid");// 检查基本表ID
		String checktime = json.getString("checktime");//检测时间
		String yhContent = json.getString("yhContent");//隐患内容
		String jgzrrNames = json.getString("jgzrrNames");//监管部门跟踪责任人名称
		String jgzrrIds = json.getString("jgzrrIds");//监管部门跟踪责任人ids
		String qyzrr = json.getString("qyzrr");//企业责任人
		String qyzrrlxdh = json.getString("qyzrrlxdh");//企业责任人联系电话
		String zgqx = json.getString("zgqx");//整改期限
		String sxzj = json.getString("sxzj");//所需资金
		String zgfa = json.getString("zgfa");//整改方案
		String ffcs = json.getString("ffcs");//防范措施
//		String zgysr = json.getString("zgysr");//整改验收人
//		String yssj = json.getString("yssj");//验收时间
//		String resultContent = json.getString("resultContent");//结论
//		String checkFlag = json.getString("checkFlag");//检查标记 
		try
		{
			yhContent = new String(yhContent.getBytes("ISO-8859-1"), "utf-8");
			jgzrrNames = new String(jgzrrNames.getBytes("ISO-8859-1"), "utf-8");
			qyzrr = new String(qyzrr.getBytes("ISO-8859-1"), "utf-8");
			sxzj = new String(sxzj.getBytes("ISO-8859-1"), "utf-8");
			zgfa = new String(zgfa.getBytes("ISO-8859-1"), "utf-8");
			ffcs = new String(ffcs.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			//保存检查情况表数据
			// 根据基本表ID查询
			CheckBasic checkBasic = checkBasicService.getById(basicId);
			Yhqd yhqd = new Yhqd();
			yhqd.setBasic(checkBasic);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			yhqd.setChecktime(DateUtils.StringToDate(checktime,sdf));
			yhqd.setFfcs(ffcs);
			yhqd.setJgzrrIds(jgzrrIds);
			yhqd.setJgzrrNames(jgzrrNames);
			yhqd.setQyzrr(qyzrr);
			yhqd.setQyzrrlxdh(qyzrrlxdh);
			yhqd.setSxzj(sxzj);
			yhqd.setZgfa(zgfa);
			yhqd.setCreateUserID(userId);
			yhqd.setCreateTime(new Date());
			yhqd.setYhContent(yhContent);
			yhqd.setZgqx(DateUtils.StringToDate(zgqx,sdf));
			yhqd.setDelFlag(0);
			yhqdService.save(yhqd);
			//开启隐患整改流程
	 		Map<String,Object> variables = new HashMap<String,Object> ();
			//String deptCode = this.getLoginUserDepartment().getDeptCode();
			//deptCode = deptCode.substring(0,deptCode.length()-3);
			variables.put("deptCode", "");
			variables.put("moveStatus",  "zhenggaitask");
			variables.put("flag", "zhenggai");
			variables.put("workFlowModelId",  yhqd.getId());
			try {
				String defId = activityManageService.StartProcessInstance(yhqd.getId(), "checkTrouble", userId, variables) ;
				Yhqd tyhqd  = yhqdService.getById( yhqd.getId());
				tyhqd.setDefId(defId);
				tyhqd.setEnded(0);
				yhqdService.update(tyhqd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", yhqd.getId());
			jn.put("linkType", "check");//返回附件类型 check
			bd.setContent(jn.toString());
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		return bd;
	}

}
