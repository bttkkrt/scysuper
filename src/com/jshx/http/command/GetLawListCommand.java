package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.zfyj.entity.LawBasis;
import com.jshx.zfyj.service.LawBasisService;

/**
 * 获取执法依据列表接口
 * @author 陆婷 2015-11-5
 *
 */
public class GetLawListCommand implements Command{
	private LawBasisService lawBasisService = (LawBasisService) SpringContextHolder.getBean("lawBasisService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String lawName = obj.getString("lawName");//获取法律法规名称
		String lawProvision = obj.getString("lawProvision");//获取法律法规条款项
		String type = obj.getString("type");//0执法依据，1规定
		
		
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		
		Map map = new HashMap();
		if(lawName != null && !"".equals(lawName))
		{
			map.put("lawName", "%" + lawName + "%");
		}
		if(lawProvision != null && !"".equals(lawProvision))
		{
			map.put("lawProvision", "%" + lawProvision + "%");
		}
		List<LawBasis> list = lawBasisService.getLawBasisListByUserAndType(map, s, l);
		int total=lawBasisService.getLawBasisListSizeByUserAndType(map);
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(LawBasis bean:list){
						   JSONObject jo = new JSONObject();
						   jo.put("id", StringTools.NullToStr(bean.getId(),""));//主键
						   jo.put("lawName", StringTools.NullToStr(bean.getLawName(),""));//法律法规名称
						   jo.put("lawProvision", StringTools.NullToStr(bean.getLawProvision(),""));//法律法规条款项
						   ja.add(jo);
					}
					br.setCode("0");
					br.setMessage("成功");
					br.setTotal(total+"");
					br.setPage(page+"");
					br.setContent(ja.toString());
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
}
