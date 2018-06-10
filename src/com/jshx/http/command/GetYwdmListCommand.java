package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.service.CodeService;

public class GetYwdmListCommand implements Command{
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BaseResponse execute(JSONObject bean) {
		SummaryBean bd = new  SummaryBean();
		/**
		 * 一维代码名称
		 */
		String codeName = bean.getString("codeName");
		/*try {
			codeName = new String(codeName.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
		Pagination pagination = new Pagination(1,10000);
		
		Map map1 = new HashMap();
		map1.put("codeName", codeName);
		pagination = codeService.findCodeByPage(pagination, map1);
		List ll = pagination.getList();
		Code code = (Code)ll.get(0);
		
		Map pMap = new HashMap();
		pMap.put("codeId", code.getId());
		pagination = this.codeService.findCodeValueByPage(pagination, pMap);
		List codeValueList  = pagination.getList();

		JSONArray jsons = new JSONArray();
		JSONObject json = null;
		try {
			for (int i = 0; i < codeValueList.size(); i++) {
				json = new JSONObject();
				CodeValue codeValue = (CodeValue)codeValueList.get(i);
				json.put("itemText", StringTools.NullToStr(codeValue.getItemText(),""));
				json.put("itemValue", StringTools.NullToStr(codeValue.getItemValue(),""));
			    jsons.add(json);
			}
			bd.setCode("0");
			bd.setMessage("查询成功");
			bd.setContent(jsons.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
