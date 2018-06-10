package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.service.DistributeItemService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class SaveInspectItemReviewCommand  implements Command{
	private DistributeItemService distributeItemService = (DistributeItemService) SpringContextHolder.getBean("distributeItemService");
	private SafeInspectDistributeService safeInspectDistributeService = (SafeInspectDistributeService) SpringContextHolder.getBean("safeInspectDistributeService");

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		try {
			String id = json.getString("id");//获取安全检查项的id
			String reviewResult = json.getString("reviewResult");//获取复查结果是否符合 2:符合  3:不符合
			String reviewRemark = json.getString("reviewRemark");//复查备注
			String reviewUserId = json.getString("reviewUserId");//复查用户ID
			String reviewLongitude = json.getString("reviewLongitude");//复查经度
			String reviewLatitude = json.getString("reviewLatitude");//复查纬度
			reviewRemark=new String(reviewRemark.getBytes("ISO-8859-1"),"utf-8");  
			
			DistributeItem item = new DistributeItem();
			item = distributeItemService.getById(id);
			item.setReviewResult(reviewResult);
			item.setReviewRemark(reviewRemark);
			item.setReviewUserId(reviewUserId);
			item.setReviewTime(df.format(new Date()));
			item.setReviewLongitude(reviewLongitude);
			item.setReviewLatitude(reviewLatitude);
			distributeItemService.update(item);
			
			SafeInspectDistribute safeInspectDistribute = safeInspectDistributeService.getById(item.getDistributeId());
			//0=源任务，1=某天某人安全检查初始化任务  2:安全检查项全部符合  3：安全检查项初查存在不符合项 4：任务复查不合格标识
			if("3".equals(reviewResult)){
				safeInspectDistribute.setTaskStatus("4");//该任务复查不合格标识
				int count = Integer.parseInt(safeInspectDistribute.getCount());
				count += 1;
				safeInspectDistribute.setCount("" + count);//设置不合格数
			}
			safeInspectDistributeService.update(safeInspectDistribute);
			
			
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", id+reviewUserId);//图片linkId为复查人ID
			bd.setContent(jn.toString());
				
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return bd;
	}
}
