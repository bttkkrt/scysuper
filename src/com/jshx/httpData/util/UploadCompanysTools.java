package com.jshx.httpData.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jshx.httpData.USRCoreService;
import com.jshx.httpData.USRCoreServiceSoap;

public class UploadCompanysTools {
	private static String commonStr1 = "<?xml version='1.0' encoding='GB2312'?>"+
			"<DataBody>"+
			"<UserArea>"+
			"<Token>1bfafe0a-5e5f-4e6d-89f5-4fcb0e3cc4a3</Token>"+
			"<MethodID>AJDataPost.DataExchange.DataPost</MethodID>"+
			"<Params>"+
			"<Param name='xml'><![CDATA[<?xml version='1.0' encoding='utf-8'?>";
	private static String commonStr2 = "<Range>"+
			"<Item name='DeptID' type='S'>"+
			"<Dictionary>"+
			"<Item key='01'>张家港市</Item>"+
			"<Item key='02'>常熟市</Item>"+
			"<Item key='03'>太仓市</Item>"+
			"<Item key='04'>昆山市</Item>"+
			"<Item key='05'>吴江区</Item>"+
			"<Item key='06'>吴中区</Item>"+
			"<Item key='07'>相城区</Item>"+
			"<Item key='08'>姑苏区</Item>"+
			"<Item key='09'>工业园区</Item>"+
			"<Item key='10'>高新区</Item>"+
			"</Dictionary>"+
			"</Item>"+
			"</Range>";
	
	private static String commonStr3 = "</DataTable>]]></Param></Params></UserArea></DataBody>";
	
	/**
	 * 企业安全生产管理机构信息
	 * @param list
	 * @return
	 */
	public static String parseQygljgxxToStr(List<Map> list){
		String content = commonStr1 +
				"<DataTable>"+
				"<Name>qygljgxx</Name>"+
				"<RowCount>1</RowCount>"+
				"<ColumnDesc>"+
				"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
				"<ZYFZRXM Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='主要负责人：姓名'></ZYFZRXM>"+
				"<ZYFZRDH Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='主要负责人：办公电话'></ZYFZRDH>"+
				"<ZYFZRSJ Range='' Default='' Required='1' DataFormat='A2000' DataType='String' Text='主要负责人：手机号码'></ZYFZRSJ>"+
				"</ColumnDesc>"+
				commonStr2;
		if(list!=null&&!list.isEmpty()){
			for(Map m:list){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<ZYFZRXM>"+convertObject(m.get("ZYFZRXM"))+"</ZYFZRXM>"+
				"<ZYFZRSJ>"+convertObject(m.get("ZYFZRSJ"))+"</ZYFZRSJ>"+
				"<ZYFZRDH>"+convertObject(m.get("ZYFZRDH"))+"</ZYFZRDH>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
				
	}
	
	/**
	 * 非危化品企业审核信息 
	 * @param list
	 * @return
	 */
	public static String parseFwhqyshToStr(List<Map> list){
		String content = commonStr1 +
				"<DataTable>"+
				"<Name>fwhqysh</Name>"+ 
				"<RowCount>1</RowCount>"+
				"<ColumnDesc>"+
				"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
				"<Qyid Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='企业ID'></Qyid>"+
				"<HYLB Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='行业类别'></HYLB>"+
				"<Ssqy Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='所属区域'></Ssqy>"+
				"<Jghy Range='' Default='' Required='1' DataFormat='A4' DataType='String' Text='监管行业'></Jghy>"+
				"</ColumnDesc>"+
				commonStr2;
		if(list!=null&&!list.isEmpty()){
			for(Map m:list){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<Qyid>"+convertObject(m.get("QYID"))+"</Qyid>"+
				"<HYLB>"+convertObject(m.get("HYLB"))+"</HYLB>"+
				"<Ssqy>"+convertObject(m.get("SSQY"))+"</Ssqy>"+
				"<Jghy>12</Jghy>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
				
	}
	
	/**
	 * 街镇安监办人员基本信息 
	 * @param list
	 * @return
	 */
	public static String parseJzajbryxxToStr(List<Map> list){
		String content = commonStr1 +
				"<DataTable>"+
				"<Name>jzajbryxx</Name>"+ 
				"<RowCount>1</RowCount>"+
				"<ColumnDesc>"+
				"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
				"<ryId Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='人员ID'></ryId>"+
				"<bmbh Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='部门编号'></bmbh>"+
				"<xm Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='姓名'></xm>"+
				"<xb Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='性别'></xb>"+
				"<zw Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='职务'></zw>"+
				"</ColumnDesc>"+
				commonStr2;
		if(list!=null&&!list.isEmpty()){
			for(Map m:list){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<ryId>"+convertObject(m.get("RYID"))+"</ryId>"+
				"<bmbh>"+convertObject(m.get("BMBH"))+"</bmbh>"+
				"<xm>"+convertObject(m.get("XM"))+"</xm>"+
				"<xb>男</xb>"+
				"<zw>"+convertObject(m.get("zw"))+"</zw>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
				
	}
	
	/**
	 * 街镇安监办部门信息
	 * @param list
	 * @return
	 */
	public static String parseJzajbbmxxToStr(List<Map> list){
		String content = commonStr1 +
				"<DataTable>"+
				"<Name>jzajbbmxx</Name>"+ 
				"<RowCount>1</RowCount>"+
				"<ColumnDesc>"+
				"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
				"<bmId Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='部门ID'></bmId>"+
				"<bmbh Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='部门编号'></bmbh>"+
				"<bmmc Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='部门名称'></bmmc>"+
				"</ColumnDesc>"+
				commonStr2;
		if(list!=null&&!list.isEmpty()){
			for(Map m:list){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<bmId>"+convertObject(m.get("BMID"))+"</bmId>"+
				"<bmbh>"+convertObject(m.get("BMBH"))+"</bmbh>"+
				"<bmmc>"+convertObject(m.get("BMMC"))+"</bmmc>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
				
	}
	
	/**
	 * 网格信息
	 * @param list
	 * @return
	 */
	public static String parseWgxxToStr(List<Map> list){
		String content = commonStr1 +
				"<DataTable>"+
				"<Name>wgxx</Name>"+
				"<RowCount>1</RowCount>"+
				"<ColumnDesc>"+
				"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
				"<WGMC Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='网格名称'></WGMC>"+
				"<WGBM Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='网格编码'></WGBM>"+
				"<WGBJZB Range='' Default='' Required='1' DataFormat='A2000' DataType='String' Text='网格边界的坐标'></WGBJZB>"+
				"<SZJZMC Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='所在街镇名称'></SZJZMC>"+
				"<SZJZQHBM Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='所在街镇区划编码'></SZJZQHBM>"+
				"<SZSQMC Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='所在市区名称'></SZSQMC>"+
				"<SZSQBH Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='所在市区区划编码'></SZSQBH>"+
				"</ColumnDesc>"+
				commonStr2;
		if(list!=null&&!list.isEmpty()){
			for(Map m:list){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<WGMC>"+convertObject(m.get("WGMC"))+"</WGMC>"+
				"<WGBM>"+convertObject(m.get("WGBM"))+"</WGBM>"+
				"<WGBJZB>"+convertObject(m.get("WGBJZB"))+"</WGBJZB>"+
				"<SZJZMC>"+convertObject(m.get("SZJZMC"))+"</SZJZMC>"+
				"<SZJZQHBM>"+convertObject(m.get("SZJZQHBM"))+"</SZJZQHBM>"+
				"<SZSQMC>太仓市</SZSQMC>"+
				"<SZSQBH>320585</SZSQBH>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
				
	}
	
	public static String parseQyzhxxToStr(List<Map> list){
		String content = commonStr1+
			"<DataTable>"+
			"<Name>qyzhxx</Name>"+
			"<RowCount>1</RowCount>"+
			"<ColumnDesc>"+
			"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
			"<Qyid Range='' Default='' Required='1' DataFormat='A32' DataType='String' Text='企业ID'></Qyid>"+
			"<Qyzh Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='企业登录市区系统的用户名'></Qyzh>"+
			"<Qyzhmm Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='企业登录市区系统的加密后的密码'></Qyzhmm>"+
			"</ColumnDesc>"+
			commonStr2;
		if(list!=null&&!list.isEmpty()){
			for(Map m:list){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<Qyid>"+convertObject(m.get("QYID"))+"</Qyid>"+
				"<Qyzh>"+convertObject(m.get("QYZH"))+"</Qyzh>"+
				"<Qyzhmm>"+convertObject(m.get("QYZHMM"))+"</Qyzhmm>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
	}
	
	public static String parseMapToStr(List<Map> coms){
		String content = commonStr1+
		"<DataTable>"+
		"<Name>qyjbxx</Name>"+
		"<RowCount>1</RowCount>"+
		"<ColumnDesc>"+
		"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
		"<Qyid Range='' Default='' Required='1' DataFormat='A32' DataType='String' Text='企业ID'></Qyid>"+
		"<qymc Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='企业名称'></qymc>"+
		"<GSZCH Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='工商注册号'></GSZCH>"+
		"<Swdjh Range='' Default='' Required='0' DataFormat='A50' DataType='String' Text='税务登记号'></Swdjh>"+
		"<Shxydm Range='' Default='' Required='0' DataFormat='A50' DataType='String' Text='统一社会信用代码'></Shxydm>"+
		"<ZZJGDM Range='' Default='' Required='1' DataFormat='A9' DataType='String' Text='组织机构代码'></ZZJGDM>"+
		"<CLRQ Range='' Default='' Required='0' DataFormat='CCYYMMDD' DataType='date' Text='成立日期'></CLRQ>"+
		"<FDDBR Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='法定代表人'></FDDBR>"+
		"<LXR Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='联系人'></LXR>"+
		"<LXDH Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='联系电话'></LXDH>"+
		"<DZYX Range='' Default='' Required='0' DataFormat='A100' DataType='String' Text='电子邮箱'></DZYX>"+
		"<DWCZ Range='' Default='' Required='0' DataFormat='A100' DataType='String' Text='单位传真'></DWCZ>"+
		"<ZCDZ Range='' Default='' Required='1' DataFormat='A300' DataType='String' Text='注册地址'></ZCDZ>"+
		"<SCJYDZ Range='' Default='' Required='1' DataFormat='A300' DataType='String' Text='生产经营地址'></SCJYDZ>"+
		"<YZBM Range='' Default='' Required='0' DataFormat='A50' DataType='String' Text='邮政编码'></YZBM>"+
		"<JJLX Range='' Default='' Required='1' DataFormat='A2' DataType='String' Text='经济类型'></JJLX>"+
		"<HYLB Range='' Default='' Required='1' DataFormat='A500' DataType='String' Text='行业类别'></HYLB>"+
		"<JYFW Range='' Default='' Required='0' DataFormat='A2000' DataType='String' Text='经营范围'></JYFW>"+
		"<QYJYZT Range='QYJYZT' Default='' Required='1' DataFormat='A2' DataType='String' Text='企业经营状态'></QYJYZT>"+
		"<ZCZJ Range='' Default='' Required='0' DataFormat='N14,2' DataType='Float' Text='注册资金(万元)'></ZCZJ>"+
		"<ZCZJDW Range='' Default='' Required='0' DataFormat='A30' DataType='String' Text='注册资金单位'></ZCZJDW>"+
		"<NXSSR Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='年销售收入(万元)'></NXSSR>"+
		"<NXSSRDW Range='' Default='' Required='0' DataFormat='A30' DataType='String' Text='年销售收入单位'></NXSSRDW>"+
		"<NLR Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='年利润(万元)'></NLR>"+
		"<NLRDW Range='' Default='' Required='0'  DataFormat='A30' DataType='String'  Text='年利润单位'></NLRDW>"+
		"<ZCZE Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='资产总额（万元）'></ZCZE>"+
		"<ZCZEDW Range='' Default='' Required='0' DataFormat='A30' DataType='String' Text='资产总额单位'></ZCZEDW>"+
		"<ZDMJ Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='占地面积(㎡)'></ZDMJ>"+
		"<CYRYSL Range='' Default='' Required='1' DataFormat='N14' DataType='int' Text='从业人员数量'></CYRYSL>"+
		"<GMQK Range='GMQK' Default='' Required='1' DataFormat='A1' DataType='String' Text='规模情况'></GMQK>"+
		"<QYGM Range='QYGM' Default='' Required='1' DataFormat='A2' DataType='String' Text='企业规模'></QYGM>"+
		"<QYPMT Range='' Default='' Required='0' DataFormat='..ul' DataType='binary' Text='企业平面图'></QYPMT>"+
		"<JD Range='' Default='' Required='0' DataFormat='N9,6' DataType='float' Text='经度（度）'></JD>"+
		"<WD Range='' Default='' Required='0' DataFormat='N9,6' DataType='float' Text='纬度（度）'></WD>"+
		"</ColumnDesc>"+
		"<Range>"+
		"<Item name='DeptID' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>张家港市</Item>"+
		"<Item key='02'>常熟市</Item>"+
		"<Item key='03'>太仓市</Item>"+
		"<Item key='04'>昆山市</Item>"+
		"<Item key='05'>吴江区</Item>"+
		"<Item key='06'>吴中区</Item>"+
		"<Item key='07'>相城区</Item>"+
		"<Item key='08'>姑苏区</Item>"+
		"<Item key='09'>工业园区</Item>"+
		"<Item key='10'>高新区</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"<Item name='QYJYZT' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>营业</Item>"+
		"<Item key='02'>停业（歇业）</Item>"+
		"<Item key='03'>筹建</Item>"+
		"<Item key='04'>关闭</Item>"+
		"<Item key='99'>其他</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"<Item name='GMQK' type='S'>"+
		"<Dictionary>"+
		"<Item key='1'>规模以上</Item>"+
		"<Item key='0'>规模以下</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"<Item name='QYGM' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>微型</Item>"+
		"<Item key='02'>小型</Item>"+
		"<Item key='03'>中型</Item>"+
		"<Item key='04'>大型</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"</Range>";
		if(coms!=null&&!coms.isEmpty()){
			for(Map m:coms){
				String com = "<RowContent>"+
				"<DeptID>03</DeptID>"+
				"<Qyid>"+convertObject(m.get("QYID"))+"</Qyid>"+
				"<qymc>"+convertObject(m.get("QYMC"))+"</qymc>"+
				"<GSZCH>"+convertObject(m.get("GSZCH"))+"</GSZCH>"+
				"<Swdjh>"+convertObject(m.get("SWDJH"))+"</Swdjh>"+
				"<Shxydm>"+convertObject(m.get("SHXYDM"))+"</Shxydm>"+
				"<ZZJGDM>"+convertObject(m.get("ZZJGDM"))+"</ZZJGDM>"+
				"<CLRQ>"+convertObject(m.get("CLRQ"))+"</CLRQ>"+
				"<FDDBR>"+convertObject(m.get("FDDBR"))+"</FDDBR>"+
				"<LXR>"+convertObject(m.get("LXR"))+"</LXR>"+
				"<LXDH>"+convertObject(m.get("LXDH"))+"</LXDH>"+
				"<DZYX>"+convertObject(m.get("DZYX"))+"</DZYX>"+
				"<DWCZ>"+convertObject(m.get("DWCZ"))+"</DWCZ>"+
				"<ZCDZ>"+convertObject(m.get("ZCDZ"))+"</ZCDZ>"+
				"<SCJYDZ>"+convertObject(m.get("SCJYDZ"))+"</SCJYDZ>"+
				"<YZBM>"+convertObject(m.get("YZBM"))+"</YZBM>"+
				"<JJLX>"+convertObject(m.get("JJLX"))+"</JJLX>"+
				"<HYLB>"+convertObject(m.get("HYLB"))+"</HYLB>"+
				"<JYFW>"+convertObject(m.get("JYFW"))+"</JYFW>"+
				"<QYJYZT>"+convertObject(m.get("QYJYZT"))+"</QYJYZT>"+
				"<ZCZJ>"+convertObjectFloat(m.get("ZCZJ"))+"</ZCZJ>"+
				"<ZCZJDW>"+convertObject(m.get("ZCZJDW"))+"</ZCZJDW>"+
				"<NXSSR>"+convertObjectFloat(m.get("NXSSR"))+"</NXSSR>"+
				"<NXSSRDW>"+convertObject(m.get("NXSSRDW"))+"</NXSSRDW>"+
				"<NLR>"+convertObjectFloat(m.get("NLR"))+"</NLR>"+
				"<NLRDW>"+convertObject(m.get("NLRDW"))+"</NLRDW>"+
				"<ZCZE>"+convertObjectFloat(m.get("ZCZE"))+"</ZCZE>"+
				"<ZCZEDW>"+convertObject(m.get("ZCZEDW"))+"</ZCZEDW>"+
				"<ZDMJ>"+convertObjectFloat(m.get("ZDMJ"))+"</ZDMJ>"+
				"<CYRYSL>"+convertObjectFloat(m.get("CYRYSL"))+"</CYRYSL>"+
				"<GMQK></GMQK>"+
				"<QYGM>"+convertObject(m.get("QYGM"))+"</QYGM>"+
				"<QYPMT></QYPMT>"+
				"<JD>"+convertObjectFloat(m.get("JD"))+"</JD>"+
				"<WD>"+convertObjectFloat(m.get("WD"))+"</WD>"+
				"</RowContent>";
				content += com;
			}
			content += commonStr3;
		}
		return content;
	}
	private static String convertObject(Object o){
		
		if(o==null||"null".equals(o)){
			return "";
		}
		return String.valueOf(o).trim();
	}
	private static float convertObjectFloat(Object o){
		
		if(o==null||"null".equals(o)){
			return 0.0f;
		}
		return Float.parseFloat(o+"");
	}
	public static boolean uploadCompanys(List<Map> coms, String type){
		boolean flag = false;
		try {
			String content = "";
			if("qyjbxx".equals(type)){
				content = parseMapToStr(coms);
			}else if("qyzhxx".equals(type)){
				content = parseQyzhxxToStr(coms);
			}else if("wgxx".equals(type)){
				content = parseWgxxToStr(coms);
			}else if("jzajbbmxx".equals(type)){
				content = parseJzajbbmxxToStr(coms);
			}else if("jzajbryxx".equals(type)){
				content = parseJzajbryxxToStr(coms);
			}else if("fwhqysh".equals(type)){
				content = parseFwhqyshToStr(coms);
			}else if("qygljgxx".equals(type)){
				content = parseQygljgxxToStr(coms);
			}else if("qygljgxx".equals(type)){
				content = parseQygljgxxToStr(coms);
			}
			
			System.out.println(content);
			USRCoreService service = new USRCoreService();
			USRCoreServiceSoap syn = service.getUSRCoreServiceSoap();
			String result = syn.callDestService(content);
			if(result.contains("<Status>true</Status><Description>OK</Description>")){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
