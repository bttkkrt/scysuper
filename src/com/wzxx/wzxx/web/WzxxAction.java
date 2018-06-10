package com.wzxx.wzxx.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.http.service.HttpService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.wzxx.bgt.entity.Lighthouse;
import com.wzxx.bgt.service.LighthouseService;
import com.wzxx.bgxz.entity.Bgxz;
import com.wzxx.bgxz.service.BgxzService;
import com.wzxx.gzdt.entity.Gzdt;
import com.wzxx.gzdt.service.GzdtService;
import com.wzxx.sytp.entity.Sytp;
import com.wzxx.sytp.service.SytpService;
import com.wzxx.tzgg.entity.Tzgg;
import com.wzxx.tzgg.service.TzggService;
import com.wzxx.wzxx.entity.ViewWzxx;
import com.wzxx.wzxx.service.WzxxService;
import com.wzxx.xyjc.entity.Xyjc;
import com.wzxx.xyjc.service.XyjcService;

public class WzxxAction extends BaseAction
{
	private int pageNum;
	
	private int totalCount;
	
	private int totalPage;
	
	private String title;
	
	@Autowired
	private WzxxService wzxxService;
	@Autowired
	private TzggService tzggService;//通知公告
	@Autowired
	private PhotoPicService photoPicService;
	
	@Autowired
	private LighthouseService lighthouseService;
	
	private Tzgg tzgg = new Tzgg();//通知公告
	
	private List<Tzgg> tzggList = new ArrayList<Tzgg>();
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	@Autowired
	private GzdtService gzdtService;//通知公告
	@Autowired
	private HttpService httpService;
	private Gzdt gzdt = new Gzdt();//通知公告
	
	private List<Gzdt> gzdtList = new ArrayList<Gzdt>();
	
	@Autowired
	private SytpService sytpService;//通知公告
	private List<Sytp> sytpList = new ArrayList<Sytp>();
	
	@Autowired
	private BgxzService bgxzService;//通知公告
	private List<Bgxz> bgxzList = new ArrayList<Bgxz>();
	private Bgxz bgxz = new Bgxz();//通知公告
	
	private List<Map<String, Object>> awhgzList = new ArrayList<Map<String, Object>>();//安委会工作
	private List<Map<String, Object>> zwxxList = new ArrayList<Map<String, Object>>();//政务信息
	private List<Map<String, Object>> zfjcList = new ArrayList<Map<String, Object>>();//执法监察
	private List<Map<String, Object>> wxhxpList = new ArrayList<Map<String, Object>>();//危险化学品
	private List<Map<String, Object>> zywsList = new ArrayList<Map<String, Object>>();//职业卫生
	private List<Map<String, Object>> xzxkgsList = new ArrayList<Map<String, Object>>();//行证许可公示
	private List<Map<String, Object>> bslcList = new ArrayList<Map<String, Object>>();//办事流程
	
	private List<Lighthouse> bgtList=new ArrayList<Lighthouse>();
	
	
	private List<ViewWzxx> sousuoList=new ArrayList<ViewWzxx>();
	
	private Xyjc xyjc = new Xyjc();
	@Autowired
	private XyjcService xyjcService;
	
	private String flag;
	
	
	public String index()
	{
		Map map = new HashMap();
		tzggList = tzggService.findAllInfo(null, 1, 5);
		for(Tzgg tzgg:tzggList)
		{
			
			String s = tzgg.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			tzgg.setInfoTitle(s);
		}
		gzdtList = gzdtService.findAllInfo(null, 1, 5);
		for(Gzdt gzdt:gzdtList)
		{
		
			String s = gzdt.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			gzdt.setInfoTitle(s);
		}
		sytpList = sytpService.findAllInfos(null);
		
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "getAwhgzListByPage");
		paraMap.put("start", 1);
		paraMap.put("limit", 6);
		paraMap.put("infoType", "1");
		awhgzList=httpService.findListDataByMap(paraMap);
		paraMap.put("infoType", "2");
		zwxxList=httpService.findListDataByMap(paraMap);
		paraMap.put("infoType", "3");
		zfjcList=httpService.findListDataByMap(paraMap);
		paraMap.put("infoType", "4");
		wxhxpList=httpService.findListDataByMap(paraMap);
		paraMap.put("infoType", "5");
		zywsList=httpService.findListDataByMap(paraMap);
		paraMap.put("limit", 6);
		paraMap.put("infoType", "8");
		xzxkgsList=httpService.findListDataByMap(paraMap);
		paraMap.put("limit", 9);
		paraMap.put("infoType", "9");
		bslcList=httpService.findListDataByMap(paraMap);
		
		String ipString = getRequest().getHeader("x-forwarded-for");  
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
            ipString = getRequest().getHeader("Proxy-Client-IP");  
        }  
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
            ipString = getRequest().getHeader("WL-Proxy-Client-IP");  
        }  
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
            ipString = getRequest().getRemoteAddr();  
        }  
      
        // 多个路由时，取第一个非unknown的ip  
        final String[] arr = ipString.split(",");  
        for (final String str : arr) {  
            if (!"unknown".equalsIgnoreCase(str)) {  
                ipString = str;  
                break;  
            }  
        }  
        if(ipString != null && ipString.startsWith("172"))
        {
        	flag = "1";
        }
        else
        {
        	flag = "0";
        }
        	
		return SUCCESS;
	}
	
	public String newsList()
	{
		tzggList = tzggService.findAllInfo(null, pageNum, 10);
		for(Tzgg tzgg:tzggList)
		{
			String ss = tzgg.getInfoTitle();
			if(ss.length()>60)
			{
				ss = ss.substring(0,40) + "...";
			}
			tzgg.setInfoTitle(ss);
		}
		totalCount = tzggService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String newsContent()
	{
		tzggList = tzggService.findAllInfo(null, 1, 7);
		for(Tzgg tzgg:tzggList)
		{
			
			String s = tzgg.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			tzgg.setInfoTitle(s);
		}
		gzdtList = gzdtService.findAllInfo(null, 1, 7);
		for(Gzdt gzdt:gzdtList)
		{
		
			String s = gzdt.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			gzdt.setInfoTitle(s);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "getAwhgzListByPage");
		paraMap.put("start", 1);
		paraMap.put("limit", 7);
		paraMap.put("infoType", "4");
		wxhxpList=httpService.findListDataByMap(paraMap);
		tzgg = tzggService.getById(tzgg.getId());
		
		String content = tzgg.getInfoContent();
		String sb = "";

		String[] s1 = content.split("style");

		for (int i = 0; i < s1.length; i++) {

			if (i==0) {

				sb = sb + s1[0];

				continue;

			}

			int ii = s1[i].indexOf(">", 0);

			if(ii != -1)
			{
				sb = sb + s1[i].substring(ii);
			}
			else
			{
				sb = sb + s1[i];
			}
		}
		
		tzgg.setInfoContent(sb.toString());
		
		Map map = new HashMap();
		map.put("linkId",tzgg.getLinkId());
		map.put("mkType", "wzInfo");
		map.put("picType","wzInfofj");
		picList = photoPicService.findPicPath(map);//获取执法文书材料
		int readNum = tzgg.getReadNum()+1;
		tzgg.setReadNum(readNum);
		tzggService.update(tzgg);
		return SUCCESS;
	}
	
	public String gzdtList()
	{
		gzdtList = gzdtService.findAllInfo(null, pageNum, 10);
		for(Gzdt gzdt:gzdtList)
		{
			String ss = gzdt.getInfoTitle();
			if(ss.length()>60)
			{
				ss = ss.substring(0,40) + "...";
			}
			gzdt.setInfoTitle(ss);
		}
		totalCount = gzdtService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String gzdtContent()
	{
		tzggList = tzggService.findAllInfo(null, 1, 7);
		for(Tzgg tzgg:tzggList)
		{
			
			String s = tzgg.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			tzgg.setInfoTitle(s);
		}
		gzdtList = gzdtService.findAllInfo(null, 1, 7);
		for(Gzdt gzdt:gzdtList)
		{
		
			String s = gzdt.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			gzdt.setInfoTitle(s);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "getAwhgzListByPage");
		paraMap.put("start", 1);
		paraMap.put("limit", 7);
		paraMap.put("infoType", "4");
		wxhxpList=httpService.findListDataByMap(paraMap);
		gzdt = gzdtService.getById(gzdt.getId());
		
		String content = gzdt.getInfoContent();
		String sb = "";

		String[] s1 = content.split("style");

		for (int i = 0; i < s1.length; i++) {

			if (i==0) {

				sb = sb + s1[0];

				continue;

			}

			int ii = s1[i].indexOf(">", 0);

			if(ii != -1)
			{
				sb = sb + s1[i].substring(ii);
			}
			else
			{
				sb = sb + s1[i];
			}
		}
		
		gzdt.setInfoContent(sb.toString());
		
		Map map = new HashMap();
		map.put("linkId",gzdt.getLinkId());
		map.put("mkType", "wzInfo");
		map.put("picType","wzInfofj");
		picList = photoPicService.findPicPath(map);//获取执法文书材料
		int readNum = gzdt.getReadNum()+1;
		gzdt.setReadNum(readNum);
		gzdtService.update(gzdt);
		return SUCCESS;
	}

	public String bgxzList()
	{
		Map map = new HashMap();
		if(bgxz != null)
		{
			if(bgxz.getBgzl() != null && !"".equals(bgxz.getBgzl()))
			{
				map.put("bgzl", bgxz.getBgzl());
			}
		}
		bgxzList = bgxzService.findAllInfo(map, pageNum, 10);
		for(Bgxz bgxz:bgxzList)
		{
			String ss = bgxz.getInfoTitle();
			if(ss.length()>60)
			{
				ss = ss.substring(0,40) + "...";
			}
			bgxz.setInfoTitle(ss);
		}
		totalCount = bgxzService.findAllInfos(map);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String bgxzContent()
	{
		bgxz = bgxzService.getById(bgxz.getId());
		Map map = new HashMap();
		map.put("linkId",bgxz.getLinkId());
		map.put("mkType", "bgxz");
		map.put("picType","bgxzfj");
		picList = photoPicService.findPicPath(map);//获取执法文书材料
		return SUCCESS;
	}
	
	public String xyjcAdd()
	{
		return SUCCESS;
	}
	
	public String xyjcSave() throws Exception
	{
		try{
			xyjc.setDelFlag("0");
			xyjc.setCreateTime(new Date());
			xyjcService.save(xyjc);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	
	
	public String search()  throws Exception{
		
		return SUCCESS;
	}
	
	
	public String sousuoList(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("title", "%"+title+"%");
		sousuoList = wzxxService.findAllInfo(paraMap, pageNum, 10);
		for(ViewWzxx viewWzxx:sousuoList)
		{
			String ss = viewWzxx.getTitle();
			if(ss.length()>60)
			{
				ss = ss.substring(0,40) + "...";
			}
			viewWzxx.setTitle(ss);
		}
		totalCount = wzxxService.findAllInfos(paraMap);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String xsdw()
	{
		return flag;
	}
	
	public Tzgg getTzgg() {
		return tzgg;
	}

	public void setTzgg(Tzgg tzgg) {
		this.tzgg = tzgg;
	}

	public List<Tzgg> getTzggList() {
		return tzggList;
	}

	public void setTzggList(List<Tzgg> tzggList) {
		this.tzggList = tzggList;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Gzdt getGzdt() {
		return gzdt;
	}

	public void setGzdt(Gzdt gzdt) {
		this.gzdt = gzdt;
	}

	public List<Gzdt> getGzdtList() {
		return gzdtList;
	}

	public void setGzdtList(List<Gzdt> gzdtList) {
		this.gzdtList = gzdtList;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public List<Sytp> getSytpList() {
		return sytpList;
	}

	public void setSytpList(List<Sytp> sytpList) {
		this.sytpList = sytpList;
	}
	
	public List<Map<String, Object>> getAwhgzList() {
		return awhgzList;
	}

	public void setAwhgzList(List<Map<String, Object>> awhgzList) {
		this.awhgzList = awhgzList;
	}

	public List<Map<String, Object>> getZwxxList() {
		return zwxxList;
	}

	public void setZwxxList(List<Map<String, Object>> zwxxList) {
		this.zwxxList = zwxxList;
	}

	public List<Map<String, Object>> getZfjcList() {
		return zfjcList;
	}

	public void setZfjcList(List<Map<String, Object>> zfjcList) {
		this.zfjcList = zfjcList;
	}

	public List<Map<String, Object>> getWxhxpList() {
		return wxhxpList;
	}

	public void setWxhxpList(List<Map<String, Object>> wxhxpList) {
		this.wxhxpList = wxhxpList;
	}

	public List<Map<String, Object>> getZywsList() {
		return zywsList;
	}

	public void setZywsList(List<Map<String, Object>> zywsList) {
		this.zywsList = zywsList;
	}

	public List<Map<String, Object>> getXzxkgsList() {
		return xzxkgsList;
	}

	public void setXzxkgsList(List<Map<String, Object>> xzxkgsList) {
		this.xzxkgsList = xzxkgsList;
	}

	public List<Map<String, Object>> getBslcList() {
		return bslcList;
	}

	public void setBslcList(List<Map<String, Object>> bslcList) {
		this.bslcList = bslcList;
	}
	public List<Lighthouse> getBgtList() {
		return bgtList;
	}

	public void setBgtList(List<Lighthouse> bgtList) {
		this.bgtList = bgtList;
	}
	public List<Bgxz> getBgxzList() {
		return bgxzList;
	}

	public void setBgxzList(List<Bgxz> bgxzList) {
		this.bgxzList = bgxzList;
	}

	public Bgxz getBgxz() {
		return bgxz;
	}

	public void setBgxz(Bgxz bgxz) {
		this.bgxz = bgxz;
	}

	public Xyjc getXyjc() {
		return xyjc;
	}

	public void setXyjc(Xyjc xyjc) {
		this.xyjc = xyjc;
	}

	public List<ViewWzxx> getSousuoList() {
		return sousuoList;
	}

	public void setSousuoList(List<ViewWzxx> sousuoList) {
		this.sousuoList = sousuoList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
