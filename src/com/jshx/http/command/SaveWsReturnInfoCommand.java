package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;

/**
 * 文书回执上报接口
 * @author 陆婷 2015-11-5
 *
 */
public class SaveWsReturnInfoCommand implements Command{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private UserService userService= (UserService) SpringContextHolder.getBean("userService");
	private CaseInfoService caseInfoService= (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService"); 
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//文书编号
		String address = obj.getString("address");//送达地点
		String returnWay = obj.getString("returnWay");//送达方式
		String time = obj.getString("time");//送达时间
		
		
		JSONObject json = new JSONObject();
		try {
			User user = userService.findUserById(userId);
			InstrumentsInfo inn = instrumentsInfoService.getById(id);
			inn.setAddress(address);
			inn.setReturnWay(returnWay);
			inn.setReturnTime(sdf.parse(time));
			inn.setReturnPerson(user.getDisplayName());
			inn.setIfReturn("1");
			String linkId = inn.getLinkId();
			if(linkId == null || "".equals(linkId))
			{
				linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				inn.setLinkId(linkId);
			}
			instrumentsInfoService.update(inn);
			
			String root = this.getClass().getResource("/").getPath();
			root = root.replaceAll("\\\\", "/");
			CaseInfo ca = caseInfoService.getById(inn.getCaseId());
			
			Map m = new HashMap();
			m.put("caseId", inn.getCaseId());
			m.put("instrumentType", "102");
			List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
			InstrumentsInfo ins = new InstrumentsInfo();
			if(list.size() != 0)
			{
				ins = list.get(0);
			}
			else
			{
				ins.setCaseId(inn.getCaseId());
				ins.setCaseName(inn.getCaseName());
				ins.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
				ins.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
				ins.setInstrumentType("102");
				ins.setTime(new Date());
				String fileName = "文书送达回执";
				
				SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String destName = sdf1.format(ins.getTime());
				SimpleDateFormat sdf11 =  new SimpleDateFormat("yyyyMMdd");
				String instrumentsName = fileName + sdf11.format(ins.getTime());
				ins.setInstrumentName(instrumentsName);
				ins.setIfCheck("0");
				ins.setIfPrint("1");
				ins.setDeptId(user.getDept().getId());
				ins.setFileName(fileName+destName+".docx");
				//获取文书号 luting 2015-10-25
				if(ca.getFineType().equals("0"))
				{
					ins.setAjbz("苏园安监违管回字");
				}
				else
				{
					ins.setAjbz("苏园安监管回字");
				}
				ins.setAjh(ca.getGlh());
				ins.setAjhNum(ca.getGlhNum());
				ins.setCompanyName(ca.getCompanyName());
				String wsh = ins.getAjbz() +  "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
				ins.setWsh(wsh);
				ins.setDelFlag(0);
				String linkIds = java.util.UUID.randomUUID().toString().replace("-", "");
				ins.setLinkId(linkIds);
				instrumentsInfoService.save(ins);
			}
			
			Map<String, Object> map1=new HashMap<String, Object>();
			map1.put("ajbz", NullToString(ins.getAjbz()));
			map1.put("ghh", NullToString(ins.getAjh()));
			map1.put("ghhNum", NullToString(ins.getAjhNum()));
			map1.put("caseName", NullToString(ca.getCaseName()));
			if(ca.getPersonType().equals("1"))
			{
				map1.put("person", NullToString(ca.getPerson()));
			}
			else
			{
				map1.put("person", NullToString(ca.getCompanyName()));
			}
			
			Map mm = new HashMap();
			mm.put("caseId", ca.getId());
			List<InstrumentsInfo> wslist = instrumentsInfoService.findInstrumentsInfos(mm);
			
			List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
			for(int i=0;i<wslist.size();i++)
			{
				InstrumentsInfo in = wslist.get(i);
				Map<String, Object> mm1 = new HashMap<String, Object>();
				m.put("codeName", "文书类型");
				m.put("itemValue", in.getInstrumentType());
				String fileName = codeService.findCodeValueByMap(m).getItemText();
				if(in.getIfReturn() != null && in.getIfReturn().equals("1"))
				{
					if(in.getWsh() != null && !"".equals(in.getWsh()))
					{
						mm1.put("wsmc",fileName+ "(" + in.getWsh() + ")" );
					}
					else
					{
						mm1.put("wsmc",fileName);
					}
					mm1.put("sddz", NullToString(in.getAddress()));
					mm1.put("sdrq", changeTimeToZw(in.getReturnTime()));
					mm1.put("sdfs", NullToString(in.getReturnWay()));
					mm1.put("sdr", NullToString(in.getReturnPerson()));
					newList1.add(mm1);
				}
			}
			map1.put("wshzList", newList1);
			FileDocUtil fileDocUtil = new FileDocUtil();	
			String[] s = fileDocUtil.createDocFile(root+"../../文书送达回执.docx",ins.getFileName(),root+"../../../../virtualdir/file/"+ca.getCaseName(), map1).split(",");
			ins.setFileSize(s[0]);
			ins.setPageSize(s[1]);
			instrumentsInfoService.update(ins);
			
			json.put("linkId", inn.getLinkId());
			br.setCode("0");
			br.setMessage("成功");
			br.setContent(json.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
	
	/**
	 * 将null值转换为空字符串
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 */
	public String NullToString(String object)
	{
		String s = "";
		if(object != null)
		{
			s = object;
		}
		return s;
	}
	
	public String changeTimeToZw(Date d)
	{
		String time = "";
		if(d != null)
		{
			String day[] = sdf.format(d).split("-");
			time = day[0] + "年" + day[1] + "月" + day[2] + "日";
		}
		return time;
	}
}
