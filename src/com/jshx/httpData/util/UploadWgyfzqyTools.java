package com.jshx.httpData.util;

import java.util.List;
import java.util.Map;

import com.jshx.httpData.USRCoreService;
import com.jshx.httpData.USRCoreServiceSoap;

public class UploadWgyfzqyTools {
	public static String parseMapToStr(List<Map> coms){
		String content = "<?xml version='1.0' encoding='GB2312'?>"+
		"<DataBody>"+
		"<UserArea>"+
		"<Token>6ba5f4e1-e131-4372-8eb7-27e788810b7d</Token>"+
		"<MethodID>AJDataPost.DataExchange.DataPost</MethodID>"+
		"<Params>"+
		"<Param name='xml'><![CDATA[<?xml version='1.0' encoding='utf-8'?>"+
		"<DataTable>"+
		"<Name>wgyfzqy</Name>"+
		"<RowCount>1</RowCount>"+
		"<ColumnDesc>"+
		"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
		"<id Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='序号'></id>"+
		"<wgybh Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='网格员编号'></wgybh>"+
		"<qyid Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='企业ID'></qyid>"+
		"<qymc Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='企业名称'></qymc>"+
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
		"</Range>";
		if(coms!=null&&!coms.isEmpty()){
			for(Map m:coms){
				String com = "<RowContent>"+
				"<DeptID>09</DeptID>"+
				"<id>"+convertObject(m.get("ID"))+"</id>"+
				"<wgybh>"+convertObject(m.get("WGYBH"))+"</wgybh>"+
				"<qyid>"+convertObject(m.get("QYID"))+"</qyid>"+
				"<qymc>"+convertObject(m.get("QYMC"))+"</qymc>"+
				"</RowContent>";
				content += com;
			}
			content += "</DataTable>]]></Param></Params></UserArea></DataBody>";
		}
		return content;
	}
	private static String convertObject(Object o){
		
		if(o==null||"null".equals(o)){
			return "";
		}
		return String.valueOf(o).trim();
	}
	private static String convertObjectSpecial(Object o){
		
		if(o==null||"null".equals(o)){
			return "";
		}
		String s = String.valueOf(o).trim();
		if(s.contains("<")){
			s = s.replace("<", " &lt;");
		}
		if(s.contains(">")){
			s= s.replace(">", "&gt;");		
		}
		if(s.contains("&")){
			s=s.replace(">", "&amp;");		
		}
		if(s.contains("\"")){
			s=s.replace("\"", "&quot;");		
		}
		if(s.contains("\'")){
			s=s.replace("\'", "&apos;");		
		}
		return s;
	}
	private static float convertObjectFloat(Object o){
		
		if(o==null||"null".equals(o)){
			return 0.0f;
		}
		return Float.parseFloat(o+"");
	}
	public static boolean uploadCompanys(List<Map> coms){
		boolean flag = false;
		try {
			String content = parseMapToStr(coms);
			USRCoreService service = new USRCoreService();
			USRCoreServiceSoap syn = service.getUSRCoreServiceSoap();
			String result = syn.callDestService(content);
			if(result.contains("<Status>true</Status><Description>OK</Description>")){
				flag = true;
			}else{
				System.out.println("错误的企业");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args) {
		try {
			/*List<Map> coms = new ArrayList<Map>();
			Map m = new HashMap();
			m.put("QYID","10000001");
			coms.add(m);*/
			String s = "26-07-09";
			s.replace("-", "/");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
