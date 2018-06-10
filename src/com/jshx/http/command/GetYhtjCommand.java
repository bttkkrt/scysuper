package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.jshx.yhb.entity.TjYhBean;
import com.jshx.yhb.service.TroManService;

/**
 * #隐患统计
 * @author fq
 * 2016-6-3
 *
 */
public class GetYhtjCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	
	private TroManService troManService= (TroManService) SpringContextHolder.getBean("troManService");
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		try{
		String userId = obj.getString("userId");//获取用户的id
		
		String startTime=obj.getString("startTime");//查询开始时间
		String endTime=obj.getString("endTime");//查询结束时间
		System.out.println(endTime);
		if(null==startTime||"".equals(startTime)){
			startTime="1949-10-01";
		}
		if(null==endTime||"".equals(endTime)){
			endTime="2949-10-01";
		}
		Date start=sdf.parse(startTime);
		Date end=sdf.parse(endTime);
		String type1 = obj.getString("type1");//1:企业自查隐患，2：安监隐患
		
		//(type1=1)1:所属部门，2：所属网格，3：所属企业 ，4：隐患类别     (type1=2)1:所属部门，2：所属网格，3：上报人员 ，4：隐患类别
		String type2 = obj.getString("type2");   
		
		//1:企业自查隐患
		if("1".equals(type1)){
			//所属部门
			if("1".equals(type2)){
				Map map = new HashMap();	
				map.put("queryJcsjStart", start);
				map.put("queryJcsjEnd", end);
				List<TjYhBean> tjyhList = troManService.getTjYhListFromQy("query_tjyh_list_data",map);
				TjYhBean tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data",map);
				
				JSONArray ja=JSONArray.fromObject(tjyhList);
				JSONObject jo=new JSONObject();
				jo.put("list", ja);
				jo.put("heji", tjyhBean);
				br.setCode("0");
				br.setMessage("成功");
//				br.setTotal(total+"");
//				br.setPage(page+"");
				br.setContent(jo.toString());
				
			//所属网格
			}else if("2".equals(type2)){
				Map map = new HashMap();	
				map.put("queryJcsjStart", start);
				map.put("queryJcsjEnd", end);
				List<TjYhBean> tjyhList = troManService.getTjYhListFromQy("query_tjyh_list_data_grid",map);
				TjYhBean tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data_grid",map);
				JSONArray ja=JSONArray.fromObject(tjyhList);
				JSONObject jo=new JSONObject();
				jo.put("list", ja);
				jo.put("heji", tjyhBean);
				br.setCode("0");
				br.setMessage("成功");
//				br.setTotal(total+"");
//				br.setPage(page+"");
				br.setContent(jo.toString());
			//所属企业
			}else if("3".equals(type2)){
				String pageNum = obj.getString("pageNum");//获取分页的起始页
				String pageSize = obj.getString("pageSize");//获取分页的每页条数
				int no=Integer.parseInt(pageNum);
				int size=Integer.parseInt(pageSize);
				Map<String, Object> paraMap = new HashMap<String, Object>();
				
				paraMap.put("queryJcsjStart", start);
				paraMap.put("queryJcsjEnd", end);
				TjYhBean tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data",paraMap);
				int total=tjyhBean.getSbqy();
				int page=total/(size);
				page = total%size==0?page:(page+1);
				paraMap.put("start", (no-1)*size);
				paraMap.put("limit", size);
				paraMap.put("sqlId", "query_tjyh_list_data_company");
				List<Map<String,Object>> a=httpService.findListDataByMap(paraMap);
				List<TjYhBean> l=new ArrayList<TjYhBean>();
				for(Map m:a){
					TjYhBean t=new TjYhBean();
					t.setDwdz(m.get("dwdz").toString());
					t.setYhTotal(Integer.parseInt(m.get("yhTotal").toString()));
					t.setZgwc(Integer.parseInt(m.get("zgwc").toString()));
					t.setZgwwc(Integer.parseInt(m.get("zgwwc").toString()));
					t.setSbcs(Integer.parseInt(m.get("yhTotal").toString()));
					//t.setZgl("1");
					l.add(t);
				}
				JSONArray ja=JSONArray.fromObject(l);
				
				br.setCode("0");
				br.setMessage("成功");
				br.setTotal(total+"");
				br.setPage(page+"");
				br.setContent(ja.toString());
				
				
			//隐患类别
			}else {
				Map map = new HashMap();	
				map.put("queryJcsjStart", start);
				map.put("queryJcsjEnd", end);
				map.put("sqlID", "getZcyhCounts");
				Map counts=httpService.getMapByMap(map);
				int xcCount=Integer.parseInt(counts.get("xcCount").toString());
				int jcCount=Integer.parseInt(counts.get("jcCount").toString());
				JSONObject jo = new JSONObject();
				jo.put("jc",jcCount);
				jo.put("xc",xcCount);
				br.setCode("0");
				br.setMessage("成功");
//				br.setTotal(total+"");
//				br.setPage(page+"");
				br.setContent(jo.toString());
			}
			
		//1:安监隐患
		}else if("2".equals(type1)){
			//所属部门
			if("1".equals(type2)){
				Map map = new HashMap();	
				map.put("queryJcsjStart", start);
				map.put("queryJcsjEnd", end);
				List<TjYhBean> tjyhList = troManService.getTjYhListFromQy("query_ajyh_list_data",map);
				TjYhBean tjyhBean = troManService.getTjYhDataFromQy("query_ajYh_data",map);
				
				JSONArray ja=JSONArray.fromObject(tjyhList);
				JSONObject jo=new JSONObject();
				jo.put("list", ja);
				jo.put("heji", tjyhBean);
				br.setCode("0");
				br.setMessage("成功");
//				br.setTotal(total+"");
//				br.setPage(page+"");
				br.setContent(jo.toString());
				
			//所属网格
			}else if("2".equals(type2)){
				Map map = new HashMap();	
				map.put("queryJcsjStart", start);
				map.put("queryJcsjEnd", end);
				List<TjYhBean> tjyhList = troManService.getTjYhListFromQy("query_ajyh_list_data_grid",map);
				TjYhBean tjyhBean = troManService.getTjYhDataFromQy("query_ajYh_data_grid",map);
				JSONArray ja=JSONArray.fromObject(tjyhList);
				JSONObject jo=new JSONObject();
				jo.put("list", ja);
				jo.put("heji", tjyhBean);
				br.setCode("0");
				br.setMessage("成功");
//				br.setTotal(total+"");
//				br.setPage(page+"");
				br.setContent(jo.toString());
			//上报人员
			}else if("3".equals(type2)){
				String pageNum = obj.getString("pageNum");//获取分页的起始页
				String pageSize = obj.getString("pageSize");//获取分页的每页条数
				int no=Integer.parseInt(pageNum);
				int size=Integer.parseInt(pageSize);
				Map<String, Object> paraMap = new HashMap<String, Object>();
				
				paraMap.put("queryJcsjStart", start);
				paraMap.put("queryJcsjEnd", end);
				
				paraMap.put("start", (no-1)*size);
				paraMap.put("limit", size);
				paraMap.put("sqlId", "query_tjyh_list_data_reportman");
				List<Map<String,Object>> a=httpService.findListDataByMap(paraMap);
				List<TjYhBean> l=new ArrayList<TjYhBean>();
				for(Map m:a){
					TjYhBean t=new TjYhBean();
					t.setSzzid(m.get("szzid").toString());
					t.setDwdz(m.get("dwdz").toString());
					t.setYhTotal(Integer.parseInt(m.get("sbcs").toString()));
					t.setZgwc(Integer.parseInt(m.get("zgwc").toString()));
					t.setZgwwc(Integer.parseInt(m.get("zgwwc").toString()));
					t.setSbcs(Integer.parseInt(m.get("sbcs").toString()));
					//t.setZgl("1");
					l.add(t);
				}
				JSONArray ja=JSONArray.fromObject(l);
				Map<String, Object> paraMap1 = new HashMap<String, Object>();
				paraMap1.put("sqlId", "query_tjyh_list_data_reportman_count");
				int total=Integer.parseInt(httpService.findListDataByMap(paraMap1).get(0).get("num").toString());
				int page=total/(size);
				page = total%size==0?page:(page+1);
				
				br.setCode("0");
				br.setMessage("成功");
				br.setTotal(total+"");
				br.setPage(page+"");
				br.setContent(ja.toString());
				
				
			//隐患类别
			}else {
				Map map = new HashMap();	
				map.put("queryJcsjStart", start);
				map.put("queryJcsjEnd", end);
				map.put("sqlID", "getAjyhCounts");
				Map counts=httpService.getMapByMap(map);
				int xcCount=Integer.parseInt(counts.get("xcCount").toString());
				int jcCount=Integer.parseInt(counts.get("jcCount").toString());
				JSONObject jo = new JSONObject();
				jo.put("jc",jcCount);
				jo.put("xc",xcCount);
				br.setCode("0");
				br.setMessage("成功");
//				br.setTotal(total+"");
//				br.setPage(page+"");
				br.setContent(jo.toString());
			}
			
		}else{}
		
		}catch(Exception e){
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		return br;
	}
}
