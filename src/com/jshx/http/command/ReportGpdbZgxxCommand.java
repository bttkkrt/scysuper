package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.gpdb.entity.Supervice;
import com.jshx.gpdb.service.SuperviceService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.yhb.service.TroManService;

/**
 * 挂牌督办整改信息信息上报
 * @author 费谦
 *
 */

public class ReportGpdbZgxxCommand implements Command
{
	private SuperviceService superviceService = (SuperviceService) SpringContextHolder.getBean("superviceService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//挂牌督办id
		String rectificationMoney = obj.getString("rectificationMoney");//整改资金
		String recfinishTime = obj.getString("recfinishTime");//整改完成时间
		String acceptTime = obj.getString("acceptTime");//验收时间
		String danrecNum = obj.getString("danrecNum");//隐患整改数
		
		
		
		try {	
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			Supervice supervice = superviceService.getById(id);
			supervice.setAcceptTime(sdf.parse(acceptTime));
			supervice.setRectificationMoney(rectificationMoney);
			supervice.setDanrecNum(danrecNum);
			supervice.setRecfinishTime(sdf.parse(recfinishTime));
			supervice.setRectificationState("已整改待审核");
			supervice.setRectUserId(userId);
			supervice.setNextRoleCode("A03");
			supervice.setNextOperation("check3");
			superviceService.update(supervice);
			bd.setCode("0");
			bd.setMessage("挂牌督办整改信息上报成功!");
			JSONObject json = new JSONObject();
			json.put("linkId", linkId);
			bd.setContent(json.toString());
			Map map = new HashMap();
			//整改资金
			map.put("money", supervice.getRectificationMoney());
			//隐患整改数
			map.put("state",supervice.getDanrecNum());//企业上报整改信息 默认整改完成 lj 2015-12-07
			//整改完成时间
			map.put("rectTime", sdf.format(supervice.getRecfinishTime()));
			//验收时间
			map.put("insertTime", sdf.format(supervice.getAcceptTime()));
			map.put("userId", userId);
			map.put("yhbId", supervice.getId());
			map.put("linkId", linkId);
			
			troManService.saveRectInfo(map);
			
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("挂牌督办整改信息上报失败!");
			e.printStackTrace();
		}
		return bd;
	}
	/**
	 * 判断当前时间是否在有效期内
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private boolean vailTime(String startTime,String endTime){
		boolean flag = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startTime));
			long stime = c.getTimeInMillis();
			c.setTime(new Date());
			long ntime = c.getTimeInMillis();
			c.setTime(sdf.parse(endTime));
			long etime = c.getTimeInMillis();
			
			if(ntime>=stime&&ntime<=etime){
				flag = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
