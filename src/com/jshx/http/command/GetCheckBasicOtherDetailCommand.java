package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkLawEnforce.entity.CheckLawEnforce;
import com.jshx.checkSituation.entity.CheckSituation;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.rectifyOpinion.entity.RectifyOpinion;
import com.jshx.utils.DateUtils;

/**
 * 查看安全检查现场检查整改复查等详情
 * GY-UPDATE 2015-02-11
 */
public class GetCheckBasicOtherDetailCommand implements Command {

	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new  SummaryBean();
		String checkbasicid = json.getString("checkbasicid");//检查人id
		
		CheckBasic basic = checkBasicService.getById(checkbasicid);
		//封装实体类
		JSONObject obj = new JSONObject();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
		obj.put("checkPlace", basic.getCheckPlace());//检查场所
		obj.put("checkSta", DateUtils.DateToString(basic.getCheckSta(),sdf1));//检查时间起
		obj.put("checkEnd", DateUtils.DateToString(basic.getCheckEnd(),sdf1));//检查时间止
		obj.put("checkOrganization", basic.getCheckOrganization() != null ? basic.getCheckOrganization() : "");//检查机构
		obj.put("rectifyTerm", basic.getRectifyTerm() != null ? basic.getRectifyTerm() : "");//整改项
		obj.put("rectifyDate", DateUtils.DateToString(basic.getRectifyDate(),sdf2));//整改日期
		obj.put("rectifyState", basic.getRectifyState() != null ? basic.getRectifyState() : "");//整改问题
		obj.put("rectifyNum", basic.getRectifyNum() != null ? basic.getRectifyNum() : "");//整改号
		obj.put("rectifyFlag", basic.getRectifyFlag() != null ? basic.getRectifyFlag() : "");//整改标记位
		obj.put("reviewContent", basic.getReviewContent() != null ? basic.getReviewContent() : "");//复查内容
		obj.put("rectifyBeginTime", DateUtils.DateToString(basic.getRectifyBeginTime(),sdf2));//整改提出日期
		//封装检查情况
		JSONArray situationArr = new JSONArray();
		List<CheckSituation> situationList = basic.getSituationList();
		for (CheckSituation situation : situationList)
		{
			JSONObject situationJson = new JSONObject();
			situationJson.put("discreption", situation.getDiscreption() != null ? situation.getDiscreption() : "");//描述
			Map map = new HashMap();
			map.put("linkId", situation.getLinkid());
			map.put("type", "check");
			DataBean bean = httpService.getPhotoListByType(map);
			situationJson.put("linkPath", StringTools.NullToStr(bean.getRname(),""));//图片路径
			situationArr.add(situationJson);
		}
		obj.put("situationList", situationArr);//检查情况集合
		
		//封装责令限期整改问题
		JSONArray rectifyCheckArr = new JSONArray();
		List<CheckSituation> rectifyList = basic.getRectifyList();
		for (CheckSituation situation : rectifyList)
		{
			JSONObject rectifyJson = new JSONObject();
			rectifyJson.put("discreption", situation.getDiscreption() != null ? situation.getDiscreption() : "");//描述
			Map map = new HashMap();
			map.put("linkId", situation.getLinkid());
			map.put("type", "check");
			DataBean bean = httpService.getPhotoListByType(map);
			rectifyJson.put("linkPath", StringTools.NullToStr(bean.getRname(),""));//图片路径
			rectifyCheckArr.add(rectifyJson);
		}
		obj.put("rectifyList", rectifyCheckArr);
		
		//封装整改意见
		JSONArray rectifyArr = new JSONArray();
		List<RectifyOpinion> rectifyOpinionList = basic.getRectifyOpinionList();
		for (RectifyOpinion opinion : rectifyOpinionList)
		{
			JSONObject opinionJson = new JSONObject();
			opinionJson.put("opinion", opinion.getOpinion() != null ? opinion.getOpinion() : "");// 意见
			Map map = new HashMap();
			map.put("linkId", opinion.getLinkid());
			map.put("type", "rectify");
			DataBean bean = httpService.getPhotoListByType(map);
			opinionJson.put("linkPath", StringTools.NullToStr(bean.getRname(),""));//图片路径
			rectifyArr.add(opinionJson);
		}
		obj.put("rectifyOpinionList", rectifyArr);// 整改意见集合
		
		//处理执法人员
		String lawEnforceName = "";//执法人员名称
		String idNum = "";//证件号码
		List<CheckLawEnforce> enforceList = basic.getEnforceList();
		for (CheckLawEnforce enforce : enforceList)
		{
			User checker = enforce.getCheckUser();
			lawEnforceName = lawEnforceName + checker.getDisplayName() + "、"; 
			idNum = idNum + (checker.getIdNum() != null ? checker.getIdNum() : "")  + "、";
		}
		if (lawEnforceName.length() > 0)
		{
			lawEnforceName = lawEnforceName.substring(0, lawEnforceName.length()-1);
		}
		if (idNum.length() > 0)
		{
			idNum = idNum.substring(0, idNum.length()-1);
		}
		obj.put("lawEnforceName", lawEnforceName);
		obj.put("idNum", idNum);
		
 		bd.setContent(obj.toString());
 		bd.setCode("0");
		bd.setMessage("查询成功");
		return bd; 
	}

}
