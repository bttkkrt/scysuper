package com.jshx.http.command;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.KeyBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

public class VersionCommand implements Command {
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@Override
	public BaseResponse execute(JSONObject obj) {
		// TODO Auto-generated method stub
		SummaryBean br = new SummaryBean();
		Map map = new HashMap();
		map.put("platform", obj.getString("platform"));
		KeyBean dataBean =httpService.getVersionNumberByForm(map);
		if(dataBean!=null&&!"".equals(dataBean)){
				br.setCode("0");
			   JSONArray ja = new JSONArray();
				JSONObject o = new JSONObject();
				o.put("versionNumber", dataBean.getVersionNumber());
				o.put("versionDownload",dataBean.getVersionDownload());
				ja.add(o);
				br.setMessage("查询成功");
				br.setContent(ja.toString());
		}else{
			br.setCode("1");
			br.setMessage("查询无结果");
		}
		return br;
	}

}
