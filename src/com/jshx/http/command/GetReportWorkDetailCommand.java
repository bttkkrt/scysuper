package com.jshx.http.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.HibernateUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.impl.UserServiceImpl;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.reportWork.entity.ReportWork;
import com.jshx.reportWork.service.ReportWorkService;
/**
 * 获取工作上报详情
 * @author hanxc 20150403
 */
public class GetReportWorkDetailCommand implements Command{
	private ReportWorkService reportWorkService=(ReportWorkService) SpringContextHolder.getBean("reportWorkService");
	private ContentAttachsService contentAttachsService = (ContentAttachsService)SpringContextHolder.getBean("contentAttachsService");
	 @SuppressWarnings("unchecked")
		public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String id = obj.getString("id").toString(); 
		try {
			ReportWork reportWork = reportWorkService.getById(id);
		    JSONObject json = new JSONObject();
		    json.put("id", reportWork.getId());
		    json.put("workTitle", reportWork.getWorkTitle());
		    json.put("workContent", reportWork.getWorkContent());
		    json.put("userIds", reportWork.getUserIds());
		    json.put("userNames", reportWork.getUserNames());
		    json.put("time", reportWork.getTime());
		    json.put("userName", reportWork.getUserName());
		    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ipConfig.properties");    
	 		Properties p = new Properties();    
	 		try {    
	 			p.load(inputStream);    
	 		} catch (IOException e1) {   
	 			e1.printStackTrace();    
	 		}    
	 		System.out.println("ip:"+p.getProperty("ip")+",port:"+p.getProperty("port"));   
			Map paraMap = new HashMap();
			paraMap.put("infoId", reportWork.getProId());
			List<ContentAttachs> attachList = contentAttachsService.findContentAttachs(paraMap);
			StringBuffer sbName=new StringBuffer();
			StringBuffer sbPath=new StringBuffer();
			String filename = "";
			String path = "";
			for(ContentAttachs contentAttachs:attachList)
			{
				sbName.append(contentAttachs.getDocName()+",");
				sbPath.append("http://"+p.getProperty("ip")+":"+p.getProperty("port")+ "/upload/attach/"+ contentAttachs.getAttachName()+",");
			}
			if(sbName.length() > 1 && sbPath.length() > 1)
			{
				filename = sbName.substring(0,sbName.length()-1);
				path = sbPath.substring(0,sbPath.length() -1);
			}
			json.put("fileName", filename);
		    json.put("path", path);
			bd.setCode("0");
			bd.setMessage("查询成功");
			bd.setContent(json.toString());
	 	} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	 }
}
