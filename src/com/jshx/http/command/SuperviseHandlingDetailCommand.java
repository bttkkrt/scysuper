package com.jshx.http.command;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.gpdb.entity.SuperviseHandlingDetail;
import com.jshx.gpdb.service.GpdbService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;

/**
 * 获取挂牌督办的详情
 * @author lht 2013-09-25
 *
 */

public class SuperviseHandlingDetailCommand implements Command
{
	@Autowired
	private GpdbService gpdbService= (GpdbService) SpringContextHolder.getBean("gpdbService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//获取id
		Gpdb item = gpdbService.getById(id);
		String linkId = item.getLinkid();//获取关联id
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SuperviseHandlingDetail detailItemmBean = new SuperviseHandlingDetail();
			detailItemmBean.setId(item.getId());
			detailItemmBean.setSzzname(StringTools.NullToStr(item.getSzzname(),""));
			detailItemmBean.setQymc(StringTools.NullToStr(item.getQymc(),""));
			detailItemmBean.setYhmc(StringTools.NullToStr(item.getYhmc(),""));
			detailItemmBean.setGpsj(null!=item.getGpsj()?StringTools.NullToStr(sdf.format(item.getGpsj()),""):"");
			
			//隐患类别
			detailItemmBean.setYhlb(StringTools.NullToStr(item.getYhlb(),""));
			
			
			detailItemmBean.setYhs(StringTools.NullToStr(item.getYhs(),""));
			detailItemmBean.setZrr(StringTools.NullToStr(item.getZrr(),""));

			//状态获取
			if (item.getState().equals("0")) {
				detailItemmBean.setState("待整改");
			}else if (item.getState().equals("1")) {
				detailItemmBean.setState("已整改待审核");
			}else if (item.getState().equals("2")) {
				detailItemmBean.setState("审核不通过");
			}else {
				detailItemmBean.setState("审核通过");
			}
			
			detailItemmBean.setRemark(StringTools.NullToStr(item.getRemark(),""));

			Map map = new HashMap();
			map.put("type", "gpdb2");
			map.put("linkId", linkId);
			DataBean bean01 = httpService.getPhotoListByType(map);
			detailItemmBean.setZgqtp(StringTools.NullToStr(bean01.getRname(),""));//此处获取整改前图片
			
			map.put("type", "gpdb1");
			DataBean bean02 = httpService.getPhotoListByType(map);
			detailItemmBean.setJcws(StringTools.NullToStr(bean02.getRname(),""));//此处获检查文书
			
			
			detailItemmBean.setZgwcsj(null!=item.getZgwcsj()?StringTools.NullToStr(sdf.format(item.getZgwcsj()),""):"");
			detailItemmBean.setYssj(null!=item.getYssj()?StringTools.NullToStr(sdf.format(item.getYssj()),""):"");
			detailItemmBean.setYhzgs(StringTools.NullToStr(item.getYhzgs(),"")); 
			
			map.put("type", "gpdb3");
			DataBean bean03 = httpService.getPhotoListByType(map);
			detailItemmBean.setFcws(StringTools.NullToStr(bean03.getRname(),""));//此处获复查文书
			
			map.put("type", "gpdb4");
			DataBean bean04 = httpService.getPhotoListByType(map);
			detailItemmBean.setZgfa(StringTools.NullToStr(bean04.getRname(),""));//此处获整改方案
			
			map.put("type", "gpdb5");
			DataBean bean05 = httpService.getPhotoListByType(map);
			detailItemmBean.setJkcs(StringTools.NullToStr(bean05.getRname(),""));//此处获监控措施
			
			map.put("type", "gpdb6");
			DataBean bean06 = httpService.getPhotoListByType(map);
			detailItemmBean.setZghtp(StringTools.NullToStr(bean06.getRname(),""));//此处获整改后图片
			JSONObject jon = JSONObject.fromObject(detailItemmBean);
			bd.setCode("0");
			bd.setMessage("查询成功");
			bd.setContent(jon.toString());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
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
