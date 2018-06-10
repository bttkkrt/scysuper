package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.tjbhgjl.entity.PhyUnqRec;
import com.jshx.tjbhgjl.service.PhyUnqRecService;
/**
 * 获取体检不合格记录详情接口
 * @author 周云琳 2015-10-14
 *
 */
public class GetTjbhgjlDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private PhyUnqRecService phyUnqRecService = (PhyUnqRecService) SpringContextHolder.getBean("phyUnqRecService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 PhyUnqRec phyUnqRec=phyUnqRecService.getById(id);
				if(null!=phyUnqRec){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(phyUnqRec.getId(),""));//主键
					json.put("name",StringTools.NullToStr(phyUnqRec.getJshxName(),""));//姓名
					json.put("identification",StringTools.NullToStr(phyUnqRec.getIdentification(),""));//身份证
					
					Map m = new HashMap();
					m.put("codeName", "体检类型");
					m.put("itemValue", phyUnqRec.getPhysicalExaminatioType());
					String phyExamType=codeService.findCodeValueByMap(m).getItemText();
					json.put("phyExamType",phyExamType);//体检类型
					json.put("medicalExamDate",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(phyUnqRec.getMedicalExaminationDate()),""));//体检日期
					json.put("medicalInstitute",StringTools.NullToStr(phyUnqRec.getMedicalInstitution(),""));//体检机构
					json.put("phyExamResult",StringTools.NullToStr(phyUnqRec.getPhysicalExaminatioResults(),""));//体检结果
					json.put("occTabPost",StringTools.NullToStr(phyUnqRec.getOccupationalTabooPost(),""));//职业禁忌岗位
					br.setContent(json.toString());
					
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			}catch(Exception e){
				br.setCode("2");
				br.setMessage("异常");
				e.printStackTrace();
			}
		return br;
	}
}
