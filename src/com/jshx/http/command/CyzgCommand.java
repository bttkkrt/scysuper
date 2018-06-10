package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.utils.StringTools;
import com.jshx.zazPxb.entity.JshxZazPxb;
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;

/**
 * 从业资格查询
 * @author 陆婷 2014-07-04
 *
 */

public class CyzgCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String sfz = json.getString("sfz");//获取用户的id
		Map map = new HashMap();
		map.put("sfz",sfz);
		
		Object s = json.get("name");
		if(s != null)
		{
			String name = json.getString("name");
			String qymc = json.getString("qymc");
			map.put("name", "%" + name + "%");
			map.put("qymc", "%" + qymc + "%");
		}
		
		
		List<JshxZazPxb> zazpxlist = httpService.getZazpxListByMap(map);//获取主要负责人、安全管理员安全培训和职业卫生培训情况列表
		List<JshxTzzyPxb> tzzypxlist = httpService.getTzzypxListByMap(map);//获取特种作业人员培训情况列表
		List<Zjjtzsbzyry> zjjtzzylist = httpService.getZjjTzsbzyryListByMap(map);//获取质监局特种设备作业人员培训情况列表

		JSONObject jo = new JSONObject();
		try {
			
			JSONArray ja = new JSONArray();
			for(JshxZazPxb jshxZazPxb:zazpxlist)
			{
				JSONObject j1 = new JSONObject();
				//所在镇
				j1.put("szzname", jshxZazPxb.getSzzname());
				//企业名称
				j1.put("qymc", jshxZazPxb.getQymc());
				//姓名
				j1.put("personName", jshxZazPxb.getPersonName());
				//性别
				j1.put("sex", jshxZazPxb.getSex());
				//职务
				j1.put("zw", jshxZazPxb.getZw());
				//文化
				j1.put("xl", jshxZazPxb.getXl());
				//电话
				j1.put("lxfs", jshxZazPxb.getLxfs());
				//地址
				j1.put("address", jshxZazPxb.getAddress());
				//初培时间
				j1.put("cpsj", jshxZazPxb.getCpsj());
				//资格证号
				j1.put("pxzh", jshxZazPxb.getPxzh());
				//成绩
				j1.put("kscj", jshxZazPxb.getKscj());
				//危化品企业类型
				j1.put("whpqylx", jshxZazPxb.getWhpqylx());
				ja.add(j1);
			}
			jo.put("zazpxlist", ja.toString());
			
			JSONArray jb = new JSONArray();
			for(JshxTzzyPxb jshxTzzyPxb:tzzypxlist)
			{
				JSONObject j2 = new JSONObject();
				//所在镇
				j2.put("szzname", jshxTzzyPxb.getSzzname());
				//工作单位
				j2.put("qymc", jshxTzzyPxb.getQymc());
				//姓名
				j2.put("personName", jshxTzzyPxb.getPersonName());
				//性别
				j2.put("sex", jshxTzzyPxb.getSex());
				//学历
				j2.put("xl", jshxTzzyPxb.getXl());
				//理论成绩
				j2.put("llcj", jshxTzzyPxb.getLlcj());
				//实践成绩
				j2.put("sjcj", jshxTzzyPxb.getSjcj());
				//特种证号
				j2.put("tzzh", jshxTzzyPxb.getTzzh());
				//培训时间
				j2.put("sj", jshxTzzyPxb.getSj());
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
				   if(jshxTzzyPxb.getGz() != null && Integer.parseInt(jshxTzzyPxb.getGz()) < a.length)
				   {
					   gz = a[Integer.parseInt(jshxTzzyPxb.getGz())];
					   if(jshxTzzyPxb.getGzxl() != null && Integer.parseInt(jshxTzzyPxb.getGzxl()) < b.length)
					   {
						   gzxl = b[Integer.parseInt(jshxTzzyPxb.getGz())][Integer.parseInt(jshxTzzyPxb.getGzxl())];
					   }
				   }
				//工种大类
				j2.put("gz", gz);
				//工种小类
				j2.put("gzxl", gzxl);
				//领证日期
				j2.put("lzrq", jshxTzzyPxb.getLzrq());
				//有效期
				j2.put("yxsj", jshxTzzyPxb.getYxsj());
				jb.add(j2);
			}
			jo.put("tzzypxlist", jb.toString());
			
			
			JSONArray jc = new JSONArray();
			for(Zjjtzsbzyry zjjtzsbzyry:zjjtzzylist)
			{
				JSONObject j3 = new JSONObject();
				//姓名
				j3.put("mc", zjjtzsbzyry.getMc());
				//性别
				j3.put("sex", zjjtzsbzyry.getSex());
				//乡镇
				j3.put("szzname", zjjtzsbzyry.getSzzname());
				//文化程度
				j3.put("xl", zjjtzsbzyry.getXl());
				//联系电话
				j3.put("lxdh", zjjtzsbzyry.getLxdh());
				//证书类型
				j3.put("xm", StringTools.getXmIndexFromCode(zjjtzsbzyry.getXm()));
				//用人单位
				j3.put("pydw", zjjtzsbzyry.getPydw());
				//单位地址
				j3.put("dwdz", zjjtzsbzyry.getDwdz());
				//单位联系电话
				j3.put("dwlxdh", zjjtzsbzyry.getDwlxdh());
				//首次领证日期
				j3.put("pzrq", zjjtzsbzyry.getPzrq());
				//当前证书有效期
				j3.put("yxrq", zjjtzsbzyry.getYxrq());
				jc.add(j3);
			}
			jo.put("zjjtzzylist", jc.toString());
			
			bd.setCode("0");
			bd.setMessage("查询成功");
			bd.setContent(jo.toString());
			
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
