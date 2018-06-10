package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.yhb.service.TroManService;

/**
 * 获取企业隐患详情
 * @author lj
 *
 */

public class GetQyyhDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//隐患id
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("id", id);
		map.put("sqlID", "queryTroubleDetailByMap");
		try {
			Map detail = httpService.getMapByMap(map);//获取map 对象

			
			if(detail!=null){
				map.put("linkId", detail.get("linkId"));
				map.put("mkType", "troMan");
				map.put("picType","troManfj");//整改前
				try {
					DataBean bean =  httpService.getPhotoListByType(map);
					if(bean!=null){
						detail.put("zgqPic", bean.getRname());
					}
					map.put("picType","troManRect");//整改后 此处像手机端这哪是最后一次整改的信息 
					//lj 获取最后一次整改的linkId
					//此处进行调整 整改的信息为列表展示
					map.put("yhbId", id);
					 List<HashMap>  rectInfos = troManService.queryRectInfosByMap(map);
					if(rectInfos!=null&&!rectInfos.isEmpty()){
						String linkId = (String)rectInfos.get(rectInfos.size()-1).get("linkId");
						map.put("linkId", linkId);
					}
					DataBean bean1 =  httpService.getPhotoListByType(map);
					if(bean1!=null){
						detail.put("zghPic", bean1.getRname());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//获取隐患的审核记录
				map.put("infoId", detail.get("id"));
				List<CheckRecord>  checkRecords=checkRecordService.findCheckRecord(map);
				JSONObject j = JSONObject.fromObject(detail);
				String recordStr = StringTools.checkRecordToStr(checkRecords);//将列表转换成Str
				j.put("checkrecord",recordStr);
				
				bd.setContent(j.toString());
				bd.setCode("0");
				bd.setMessage("查询成功!");
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败!");
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
