package com.jshx.httpData.util;

import java.util.List;
import java.util.Map;

import com.jshx.httpData.USRCoreService;
import com.jshx.httpData.USRCoreServiceSoap;

public class UploadQygljgxxTools {
	public static String parseMapToStr(List<Map> coms){
		String content = "<?xml version='1.0' encoding='GB2312'?>"+
		"<DataBody>"+
		"<UserArea>"+
		"<Token>6ba5f4e1-e131-4372-8eb7-27e788810b7d</Token>"+
		"<MethodID>AJDataPost.DataExchange.DataPost</MethodID>"+
		"<Params>"+
		"<Param name='xml'><![CDATA[<?xml version='1.0' encoding='utf-8'?>"+
		"<DataTable>"+
		"<Name>qygljgxx</Name>"+
		"<RowCount>1</RowCount>"+
		"<ColumnDesc>"+
		"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
		"<Qyid Range='' Default='' Required='1' DataFormat='A32' DataType='String' Text='企业ID'></Qyid>"+
		"<Aqzjxm Range='' Default='' Required='' DataFormat='A50' DataType='String' Text='安全总监：姓名'></Aqzjxm>"+
		"<Aqzjzw Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全总监：职务'></Aqzjzw>"+
		"<Aqzjxb Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全总监：性别'></Aqzjxb>"+
		"<Aqzjdh Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全总监：办公电话'></Aqzjdh>"+
		"<aqzjyj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全总监：电子邮件'></aqzjyj>"+
		"<aqzjsj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全总监：手机号码'></aqzjsj>"+
		"<Zyfzrxm Range='' Default='' Required='1' DataFormat='A50' DataType='String' Text='主要负责人：姓名'></Zyfzrxm>"+
		"<Zyfzrzw Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='主要负责人：职务'></Zyfzrzw>"+
		"<Zyfzrxb Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='主要负责人：性别'></Zyfzrxb>"+
		"<Zyfzrdh Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='主要负责人：办公电话'></Zyfzrdh>"+
		"<Zyfzryj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='主要负责人：电子邮件'></Zyfzryj>"+
		"<Zyfzrsj Range='' Default='' Required='1' DataFormat='A100' DataType='String' Text='主要负责人：手机号码'></Zyfzrsj>"+
		"<Fgfzrxm Range='' Default='' Required='' DataFormat='A50' DataType='String' Text='分管负责人：姓名'></Fgfzrxm>"+
		"<Fgfzrzw Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='分管负责人：职务'></Fgfzrzw>"+
		"<Fgfzrxb Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='分管负责人：性别'></Fgfzrxb>"+
		"<Fgfzrdh Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='分管负责人：办公电话'></Fgfzrdh>"+
		"<Fgfzryj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='分管负责人：电子邮件'></Fgfzryj>"+
		"<Fgfzrsj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='分管负责人：手机号码'></Fgfzrsj>"+
		"<Jgfzrxm Range='' Default='' Required='' DataFormat='A50' DataType='String' Text='安全管理机构负责人：姓名'></Jgfzrxm>"+
		"<Jgfzrzw Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全管理机构负责人：职务'></Jgfzrzw>"+
		"<Jgfzrxb Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全管理机构负责人：性别'></Jgfzrxb>"+
		"<Jgfzrdh Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全管理机构负责人：办公电话'></Jgfzrdh>"+
		"<Jgfzryj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全管理机构负责人：电子邮件'></Jgfzryj>"+
		"<jgfzrsj Range='' Default='' Required='' DataFormat='A100' DataType='String' Text='安全管理机构负责人：手机号码'></jgfzrsj>"+
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
		"<Item name='Aqzjxb' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>男</Item>"+
		"<Item key='02'>女</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"<Item name='Zyfzrxb' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>男</Item>"+
		"<Item key='02'>女</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"<Item name='Fgfzrxb' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>男</Item>"+
		"<Item key='02'>女</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"<Item name='Jgfzrxb' type='S'>"+
		"<Dictionary>"+
		"<Item key='01'>男</Item>"+
		"<Item key='02'>女</Item>"+
		"</Dictionary>"+
		"</Item>"+
		"</Range>";
		if(coms!=null&&!coms.isEmpty()){
			for(Map m:coms){
				String com = "<RowContent>"+
				"<DeptID>09</DeptID>"+
				"<Qyid>"+convertObject(m.get("QYID"))+"</Qyid>"+
				"<Aqzjxm>"+convertObject(m.get("AJXM"))+"</Aqzjxm>"+
				"<Aqzjzw>安全总监</Aqzjzw>"+
				"<Aqzjxb></Aqzjxb>"+
				"<Aqzjdh>"+convertObject(m.get("AJHM"))+"</Aqzjdh>"+
				"<aqzjyj>"+convertObject(m.get("AJYX"))+"</aqzjyj>"+
				"<aqzjsj>"+convertObject(m.get("AJHM"))+"</aqzjsj>"+
				"<Zyfzrxm>"+convertObject(m.get("ZYXM"))+"</Zyfzrxm>"+
				"<Zyfzrzw>主要负责人</Zyfzrzw>"+
				"<Zyfzrxb></Zyfzrxb>"+
				"<Zyfzrdh>"+convertObject(m.get("ZYHM"))+"</Zyfzrdh>"+
				"<Zyfzryj>"+convertObject(m.get("ZYYX"))+"</Zyfzryj>"+
				"<Zyfzrsj>"+convertObject(m.get("ZYHM"))+"</Zyfzrsj>"+
				"<Fgfzrxm>"+convertObject(m.get("FGXM"))+"</Fgfzrxm>"+
				"<Fgfzrzw>分管负责人</Fgfzrzw>"+
				"<Fgfzrxb></Fgfzrxb>"+
				"<Fgfzrdh>"+convertObject(m.get("FGHM"))+"</Fgfzrdh>"+
				"<Fgfzryj>"+convertObject(m.get("FGYX"))+"</Fgfzryj>"+
				"<Fgfzrsj>"+convertObject(m.get("FGHM"))+"</Fgfzrsj>"+
				"<Jgfzrxm>"+convertObject(m.get("JGXM"))+"</Jgfzrxm>"+
				"<Jgfzrzw>安全管理机构负责人</Jgfzrzw>"+
				"<Jgfzrxb></Jgfzrxb>"+
				"<Jgfzrdh>"+convertObject(m.get("JGHM"))+"</Jgfzrdh>"+
				"<Jgfzryj>"+convertObject(m.get("JGYX"))+"</Jgfzryj>"+
				"<jgfzrsj>"+convertObject(m.get("JGHM"))+"</jgfzrsj>"+
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
			System.out.println("机构信息"+content);
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

