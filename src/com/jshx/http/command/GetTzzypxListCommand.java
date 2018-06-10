package com.jshx.http.command;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;

/**
 * 获取企业自查隐患列表
 * @author 陆婷 2013-09-25
 *
 */

public class GetTzzypxListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String companyId = json.getString("companyId");//获取用户的id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map mm = new HashMap();
		mm.put("companyId", companyId);
		CompanyBackUp comb = companyService.getCompanyBackupById(mm);
		Map map = new HashMap();
		map.put("start", s);
		map.put("limit", l);
		map.put("qyid", comb.getId());
		map.put("companyid", comb.getCompanyId());
		
		List<JshxTzzyPxb> commons = httpService.getTzzypxListByMap(map);//获取特种作业人员培训情况列表

		
		int total = httpService.getTzzypxListSizeByMap(map);//获取特种作业人员培训情况列表总条数
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray ja = new JSONArray();
		try {
				if(commons!=null&&!commons.isEmpty()){
					for(JshxTzzyPxb bean:commons){
						   JSONObject jo = new JSONObject();
						   jo.put("personName", StringTools.NullToStr(bean.getPersonName(),""));//姓名
						   jo.put("sex", null!=bean.getSex()?companyService.findCompanyTypeNameByKey(bean.getSex(),"4ae6e5a340b8711b0140b89fe9840062"):"");//性别
						   jo.put("csny","");//出生年月
						   jo.put("sfz", StringTools.NullToStr(bean.getSfz(),""));//身份证
						   jo.put("lxfs", "");//联系方式
						   jo.put("xl", null!=bean.getXl()?companyService.findCompanyTypeNameByKey(bean.getXl(),"4028804840b9689c0140c45e4fa50341"):"");//学历
						   
						   String[] a = {"","电工作业","焊接与热切割作业","高处作业","制冷与空调作业","冶金（有色）生产安全作业","危险化学品安全作业","烟花爆竹安全作业"};
						   String[][] b = { 
								   			{""},
								   			{"","高压电工作业","低压电工作业","防爆电气作业"},
								   			{"","熔化焊接与热切割作业","压力焊作业","钎焊作业"},
								   			{"","登高架设作业","高处安装、维护、拆除作业"},
								   			{"","制冷与空调设备运行操作作业","制冷与空调设备安装修理作业"},
								   			{"","煤气作业"},
								   			{"","光气及光气化工艺作业","氯碱电解工艺作业","氯化工艺作业","硝化工艺作业","合成氨工艺作业",
								   				"裂解（裂化）工艺作业","氟化工艺作业","加氢工艺作业","重氮化工艺作业","氧化工艺作业","过氧化工艺作业",
								   				"胺基化工艺作业","磺化工艺作业","聚合工艺作业","烷基化工艺作业","化工自动化控制仪表作业"
								   			},
								   			{"","烟火药制造作业","黑火药制造作业","引火线制造作业","烟花爆竹产品涉药作业","烟花爆竹储存作业"}
								   		 };
						   String gz = "";
						   String gzxl = "";
						   if(bean.getGz() != null && Integer.parseInt(bean.getGz()) < a.length)
						   {
							   gz = a[Integer.parseInt(bean.getGz())];
							   if(bean.getGzxl() != null && Integer.parseInt(bean.getGzxl()) < b.length)
							   {
								   gzxl = b[Integer.parseInt(bean.getGz())][Integer.parseInt(bean.getGzxl())];
							   }
						   }
						   jo.put("gz", gz);//工种大类
						   jo.put("gzxl",gzxl);//工种小类
						   jo.put("pxsj", "");//培训时间
						   jo.put("pxjg", "");//培训机构
						   jo.put("kscj", "");//考试成绩
						   
						   jo.put("pxzh","");//培训证号
						   jo.put("fzjg", "");//发证机关
						   jo.put("yxq", "");//有效期
						   jo.put("xcpxsj", "");//下次培训时间
						   ja.add(jo);
					}
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(ja.toString());
				}else{
					bd.setCode("1");
					bd.setMessage("无查询结果");
				}
			
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
