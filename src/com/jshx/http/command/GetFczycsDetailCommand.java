package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.fczycsgl.entity.DusWorMan;
import com.jshx.fczycsgl.service.DusWorManService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.zyyl.entity.MaiMat;
import com.jshx.zyyl.service.MaiMatService;

/**
 * 获取粉尘作业场所管理详情接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetFczycsDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private DusWorManService dusWorManService = (DusWorManService) SpringContextHolder.getBean("dusWorManService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 DusWorMan dusWorMan=dusWorManService.getById(id);
				if(null!=dusWorMan){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(dusWorMan.getId(),""));//主键
					json.put("workName",StringTools.NullToStr(dusWorMan.getWorkplaceName(),""));//作业场所名称
					
					Map m = new HashMap();
					m.put("codeName", "粉尘行业");
					m.put("itemValue", dusWorMan.getAgencyResponsible());
					String agenResponse=codeService.findCodeValueByMap(m).getItemText();
					json.put("agenResponse",agenResponse);//所属行业
					
					Map m1 = new HashMap();
					m1.put("codeName", "粉尘种类");
					m1.put("itemValue", dusWorMan.getIndustryType());
					String indTyoe=codeService.findCodeValueByMap(m1).getItemText();
					json.put("indTyoe",indTyoe);//粉尘种类
					
					Map m2 = new HashMap();
					m2.put("codeName", "除尘器种类");
					m2.put("itemValue", dusWorMan.getDustWiperType());
					String dustWiperType=codeService.findCodeValueByMap(m2).getItemText();
					json.put("dustWiperType",dustWiperType);//除尘器种类
					
					Map m3 = new HashMap();
					m3.put("codeName", "粉尘企业规模");
					m3.put("itemValue", dusWorMan.getEnterpriseScale());
					String enterScale=codeService.findCodeValueByMap(m3).getItemText();
					json.put("enterScale",enterScale);//企业规模
					
					json.put("emoloyNum",StringTools.NullToStr(dusWorMan.getEmployeeNumber(),""));//从业人数
					
					Map m4 = new HashMap();
					m4.put("codeName", "粉尘车间结构");
					m4.put("itemValue", dusWorMan.getWorkshopStructure());
					String workStructure=codeService.findCodeValueByMap(m4).getItemText();
					json.put("workStructure",workStructure);//车间结构
					
					json.put("workLayout",StringTools.NullToStr(dusWorMan.getWorkshopLayout(),""));//车间布局
					
					json.put("recDustDetTime",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(dusWorMan.getRecentlyDustDetectTime()),""));//最近一次粉尘检测的日期
					json.put("testValue",StringTools.NullToStr(dusWorMan.getTestValue(),""));//检测值
					
					Map m5 = new HashMap();
					m5.put("codeName", "是或否");
					m5.put("itemValue", dusWorMan.getIsQualified());
					String ifQualified=codeService.findCodeValueByMap(m5).getItemText();
					json.put("ifQualified",ifQualified);//是否合格
					
					Map m6 = new HashMap();
					m6.put("codeName", "粉尘作业方式");
					m6.put("itemValue", dusWorMan.getOperationMode());
					String opeMode=codeService.findCodeValueByMap(m6).getItemText();
					json.put("opeMode",opeMode);//作业方式
					
					Map m7 = new HashMap();
					m7.put("codeName", "是或否");
					m7.put("itemValue", dusWorMan.getHasDustWiper());
					String hasDustWiper=codeService.findCodeValueByMap(m7).getItemText();
					json.put("hasDustWiper",hasDustWiper);//是否有除尘器
					
					Map m8 = new HashMap();
					m8.put("codeName", "粉尘除尘形式");
					m8.put("itemValue", dusWorMan.getDustRemovalForm());
					String dustRemForm=codeService.findCodeValueByMap(m8).getItemText();
					json.put("dustRemForm",dustRemForm);//除尘形式
				
					json.put("dustWipNum",StringTools.NullToStr(dusWorMan.getDustWiperNumber(),""));//除尘器数量
					json.put("wipInUseTime",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(dusWorMan.getWiperInUseTime()),""));//投入使用时间
					
					Map m9 = new HashMap();
					m9.put("codeName", "是或否");
					m9.put("itemValue", dusWorMan.getIsWiperAccepted());
					String accepted=codeService.findCodeValueByMap(m9).getItemText();
					json.put("accepted",accepted);//除尘器是否经环保部门验收
					
					Map m10 = new HashMap();
					m10.put("codeName", "是或否");
					m10.put("itemValue", dusWorMan.getHasExplosionProof());
					String proof=codeService.findCodeValueByMap(m10).getItemText();
					json.put("proof",proof);//除尘系统是否设置隔爆阀
					
					Map m11 = new HashMap();
					m11.put("codeName", "是或否");
					m11.put("itemValue", dusWorMan.getHasVentPort());
					String port=codeService.findCodeValueByMap(m11).getItemText();
					json.put("port",port);//除尘器是否有泄爆口
					
					json.put("position",StringTools.NullToStr(dusWorMan.getVentPortPosition(),""));//泄爆口位置
					
					Map m12 = new HashMap();
					m12.put("codeName", "是或否");
					m12.put("itemValue", dusWorMan.getIsWorkUnderNegative());
					String pressure=codeService.findCodeValueByMap(m12).getItemText();
					json.put("pressure",pressure);//除尘器是否在负压下工作
			        
					Map m13 = new HashMap();
					m13.put("codeName", "是或否");
					m13.put("itemValue", dusWorMan.getIsInstalledOutdoor());
					String outdoor=codeService.findCodeValueByMap(m13).getItemText();
					json.put("outdoor",outdoor);//除尘器是否安装于室外
				
					Map m14 = new HashMap();
					m14.put("codeName", "是或否");
					m14.put("itemValue", dusWorMan.getHasAutoUnloadLock());
					String lock=codeService.findCodeValueByMap(m14).getItemText();
					json.put("lock",lock);//是否有自动卸灰锁气装置
					
					Map m15 = new HashMap();
					m15.put("codeName", "粉尘除尘器目前状态");
					m15.put("itemValue", dusWorMan.getDustWiperCurrentStatus());
					String wiperStatus=codeService.findCodeValueByMap(m15).getItemText();
					json.put("wiperStatus",wiperStatus);//除尘器目前状态
					
					Map m16 = new HashMap();
					m16.put("codeName", "企业目前状态");
					m16.put("itemValue", dusWorMan.getEnterpriseCurrentStatus());
					String cutStatus=codeService.findCodeValueByMap(m16).getItemText();
					json.put("cutStatus",cutStatus);//企业目前状态
				
					json.put("remark",StringTools.NullToStr(dusWorMan.getRemark(),""));//备注
					
					Map map17 = new HashMap();
					map17.put("linkId",dusWorMan.getLinkId());
					map17.put("mkType", "fczycsgl1");
					map17.put("picType","fczycsglfj1");
					DataBean bean = httpService.getPhotoListByType(map17);
					String filepath = bean.getRname();
					json.put("filePath1",StringTools.NullToStr(filepath,""));//隔爆阀照片
					
					Map map18 = new HashMap();
					map18.put("linkId",dusWorMan.getLinkId());
					map18.put("mkType", "fczycsgl2");
					map18.put("picType","fczycsglfj2");
					DataBean bean2 = httpService.getPhotoListByType(map18);
					String filepath2 = bean2.getRname();
					json.put("filePath2",StringTools.NullToStr(filepath2,""));//泄爆口照片
					
					Map map19 = new HashMap();
					map19.put("linkId",dusWorMan.getLinkId());
					map19.put("mkType", "fczycsgl3");
					map19.put("picType","fczycsglfj3");
					DataBean bean3 = httpService.getPhotoListByType(map19);
					String filepath3 = bean3.getRname();
					json.put("filePath3",StringTools.NullToStr(filepath3,""));//自动卸灰锁气装置图片
					
					Map map20 = new HashMap();
					map20.put("linkId",dusWorMan.getLinkId());
					map20.put("mkType", "fczycsgl4");
					map20.put("picType","fczycsglfj4");
					DataBean bean4 = httpService.getPhotoListByType(map20);
					String filepath4 = bean4.getRname();
					json.put("filePath4",StringTools.NullToStr(filepath4,""));//作业场所图片
					
					Map map21 = new HashMap();
					map21.put("linkId",dusWorMan.getLinkId());
					map21.put("mkType", "fczycsgl5");
					map21.put("picType","fczycsglfj5");
					DataBean bean5 = httpService.getPhotoListByType(map21);
					String filepath5 = bean5.getRname();
					json.put("filePath5",StringTools.NullToStr(filepath5,""));//除尘器全景图片
					
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
