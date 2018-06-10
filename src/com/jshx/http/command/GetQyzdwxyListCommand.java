package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;

/**
 * 获取企业重点危险源列表接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetQyzdwxyListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		String type=obj.getString("type");//1:待审核的2:已经审核过的
		int start = Integer.parseInt(pageNum);
		int limit = Integer.parseInt(pageSize);
		int s = (start-1)*limit;
		Map map = new HashMap();
		map.put("pageNum", s);
		map.put("pageSize", limit);
		map.put("type",type);
		map.put("sqlID", "queryZdwxyCountByMap");
		try {
			int total = httpService.getListCountbyMap(map);//获取待审核危险源总数
			map.put("sqlID", "queryZdwxyListByMap");
			List<Map> comDanIde = httpService.getListByMap(map);//获取待审核危险源列表
			if(comDanIde!=null&&!comDanIde.isEmpty()){
				JSONArray ja = new JSONArray();
				for(Map mm:comDanIde){
					JSONObject jo = new JSONObject();
					jo.put("id",mm.get("id").toString());
					jo.put("dangerName", null==mm.get("dangerName")?"":mm.get("dangerName"));//危险源名称
					jo.put("companyName", null==mm.get("companyName")?"":mm.get("companyName"));//企业名称
					jo.put("dangerType", null==mm.get("dangerType")?"":mm.get("dangerType"));//重点危险源类别
					jo.put("dangerLevel", null==mm.get("dangerLevel")?"":mm.get("dangerLevel"));//重点危险源级别
					ja.add(jo);
				}
				bd.setTotal(total+"");
				bd.setContent(ja.toString());
				int page = total%limit==0?total/limit:(total/limit+1);
				bd.setPage(page+"");
				bd.setCode("0");
				bd.setMessage("查询成功!");
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果!");
			}
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("查询失败!");
			e.printStackTrace();
		}
		return bd;
	}
}
