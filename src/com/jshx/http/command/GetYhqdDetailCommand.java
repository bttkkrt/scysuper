package com.jshx.http.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.company.entity.Company;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.HibernateUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.StringUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.impl.UserServiceImpl;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.entity.ContentInformations;
import com.jshx.module.infomation.entity.NoticeCallback;
import com.jshx.module.infomation.service.ContentAttachsService;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.reportWork.entity.ReportWork;
import com.jshx.reportWork.service.ReportWorkService;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;
/**
 * 获取隐患详情yhqd
 * @author hanxc 20150403
 */
public class GetYhqdDetailCommand implements Command{
	private YhqdService  yhqdService= (YhqdService) SpringContextHolder.getBean("yhqdService");
	private SzwxPhotoService szwxPhotoService = (SzwxPhotoService) SpringContextHolder.getBean("szwxPhotoService");
	@SuppressWarnings("unchecked")
		public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String yhId = obj.getString("yhId").toString(); 
		try {
			Yhqd yhqd = yhqdService.getById(yhId);
			String companyname = yhqd.getBasic().getCompany().getCompanyname();
			CheckBasic basic = new CheckBasic();
			Company company = new  Company();
			company.setCompanyname(companyname);
			basic.setCompany(company);
			yhqd.setBasic(basic);
			Map map = new HashMap();
			map.put("taskProId", yhqd.getId());
			List<PhotoPic> list = szwxPhotoService.findPicPath(map);
			yhqd.setPicList(list);
			map.put("taskProId", "ZG"+yhqd.getId());
			List<PhotoPic> zglist = szwxPhotoService.findPicPath(map);
			yhqd.setZgpicList(zglist);
		    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ipConfig.properties");    
	 		Properties p = new Properties();    
	 		try {    
	 			p.load(inputStream);    
	 		} catch (IOException e1) {   
	 			e1.printStackTrace();    
	 		}      
	 		 JSONObject json = new JSONObject();
			    json.put("delFlag", yhqd.getDelFlag());
			    json.put("checktime", DateUtil.getDate(yhqd.getChecktime(), DateUtil.DATE_FORMAT_YYYYMMDD));
			    json.put("yhContent",yhqd.getYhContent());
			    json.put("jgzrrNames", yhqd.getJgzrrNames());
			    json.put("jgzrrIds", yhqd.getJgzrrIds());
			    json.put("qyzrr", yhqd.getQyzrr());
			    json.put("zgqx", DateUtil.getDate(yhqd.getZgqx(), DateUtil.DATE_FORMAT_YYYYMMDD));
			    json.put("dealFlag",yhqd.getDealFlag()!=null ?  yhqd.getDealFlag():"");
			    json.put("ended", yhqd.getEnded());
			    //json.put("resultContent", yhqd.getResultContent());
			    //json.put("yssj", yhqd.getYssj());
			    //json.put("zgysr", yhqd.getZgysr());
			    json.put("passFlag", yhqd.getPassFlag()!=null ?  yhqd.getPassFlag():"");
			    json.put("qyzrrlxdh", yhqd.getQyzrrlxdh());
			    json.put("ffcs",yhqd.getFfcs());
			    json.put("companyname", yhqd.getBasic().getCompany().getCompanyname());
			    json.put("zgfa", yhqd.getZgfa());
			    json.put("sxzj", yhqd.getSxzj());
			    json.put("zgInfo", yhqd.getZgInfo());
			    String[] zgysrList = StringUtil.deNull(yhqd.getZgysr()).split("‖");
			    String[] yssjList = StringUtil.deNull(yhqd.getYssj()).split("‖");
			    String[] resultContentList = StringUtil.deNull(yhqd.getResultContent()).split("‖");
			    JSONArray historyList = new JSONArray();
			    for (int i = 0; i < zgysrList.length; i++) {
					JSONObject history = new JSONObject();
					history.put("zgysr", zgysrList[i]);
					history.put("yssj", yssjList[i]);
					history.put("resultContent", resultContentList[i]);
					historyList.add(history);
				}
			    json.put("historyList", historyList);
			    
			    JSONArray picList = new JSONArray();
			if(list!=null&& list.size()>0){
				for (PhotoPic photoPic : list) {
					JSONObject objj = new JSONObject();
					objj.put("url", "/upload/photo/"+ photoPic.getPicName());
					objj.put("remark", photoPic.getTaskRemark());
					picList.add(objj);
				}
			}
			  JSONArray zgpicList = new JSONArray();
			if(zglist!=null&& zglist.size()>0){
				for (PhotoPic photoPic : zglist) {
					JSONObject objj = new JSONObject();
					objj.put("url", "/upload/photo/"+ photoPic.getPicName());
					objj.put("remark", photoPic.getTaskRemark());
					zgpicList.add(objj);
				}
			}
			json.put("list", picList);
			json.put("zglist", zgpicList);
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
