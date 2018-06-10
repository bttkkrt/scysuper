package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;

/**
 * 获取文书列表接口
 * @author 陆婷 2015-11-6
 *
 */
public class GetWsInfoListCommand implements Command{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String caseId = obj.getString("caseId");//获取案件编号
		String type = obj.getString("type");//0表示案件列表，1表示领导审核列表 
		
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		
		Map map = new HashMap();
		if(type.equals("0"))
		{
			map.put("caseId", caseId);
		}
		else
		{
			User user = userService.findUserById(userId);
			List<UserRight> list = (List<UserRight>) user.getUserRoles();
			String flag = "0";
			for(UserRight ur:list)
			{
				//登录人为安监局领导
				if(ur.getRole().getRoleCode().equals("A02")) 
				{
					map.put("ifCheck", "2");
					map.put("needCheckUser", ","+userId+",");
					flag = "1";
					break;
				}
				//登录人为监察大队队长
				if(ur.getRole().getRoleCode().equals("A09")) 
				{
					map.put("ifCheck", "1");
					map.put("needCheckUser", ","+userId+",");
					flag = "2";
					break;
				}
				//登录人为法务
				if(ur.getRole().getRoleCode().equals("A30")) 
				{
					map.put("ifCheck", "8");
					map.put("needCheckUser", ","+userId+",");
					flag = "4";
					break;
				}
			}
			if(user.getDeptCode().startsWith("002001"))
			{
				if(!"1".equals(flag)&&!"2".equals(flag)&&!"4".equals(flag))
				{
					map.put("needCheckUser", ","+userId+",");
				}
			}
		}
		List<InstrumentsInfo> list  = instrumentsInfoService.getInstrumentsInfoListByUserAndType(map, s, l);
		int total=instrumentsInfoService.getInstrumentsInfoListSizeByUserAndType(map);
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(InstrumentsInfo bean:list){
						   JSONObject jo = new JSONObject();
						   jo.put("id", StringTools.NullToStr(bean.getId(),""));//主键
						   jo.put("caseName", StringTools.NullToStr(bean.getCaseName(),""));//案件名称
						   if(bean.getInstrumentType() != null && !"".equals(bean.getInstrumentType()))
						   {
							   Map m = new HashMap();
							   m.put("codeName", "文书类型");
							   m.put("itemValue", bean.getInstrumentType());
							   jo.put("instrumentType", codeService.findCodeValueByMap(m).getItemText());
						   }
						   else
						   {
							   jo.put("instrumentType", "");
						   }
						   jo.put("time", changeTimeToZw(bean.getTime()));//文书时间
						   jo.put("ifPrint", StringTools.NullToStr(bean.getIfPrint(),""));//1表示可下载打印
						   String ifReturn = bean.getIfReturn();
						   if(ifReturn != null && "1".equals(ifReturn))
						   {
							   ifReturn = "0";
						   }
						   else
						   {
							   if( bean.getIfPrint() != null && "1".equals(bean.getIfPrint()))
							   {
								   ifReturn = "1";
							   }
							   else
							   {
								   ifReturn = "0";
							   }
						   }
						   jo.put("ifReturn", ifReturn);//1表示可回执
						   String filePath = bean.getHttpurl()+"/file/"+URLEncoder.encode(bean.getCaseName(), "utf-8")+"/"+URLEncoder.encode(bean.getFileName(), "utf-8");
						   JSONObject jj = new JSONObject();
						   jj.put("fileName",StringTools.NullToStr( bean.getFileName(),""));
						   jj.put("fileSize",StringTools.NullToStr( bean.getFileSize(),"0"));
						   jj.put("fileUrl", StringTools.NullToStr(filePath,""));
						   jo.put("filePath", jj.toString());//下载地址
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
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
	
	public String changeTimeToZw(Date d)
	{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		String time = "";
		if(d != null)
		{
			String day[] = sdf.format(d).split("-");
			time = day[0] + "年" + day[1] + "月" + day[2] + "日";
		}
		return time;
	}
}
