package com.jshx.httpData.util;

import com.jshx.httpData.USRCoreService;
import com.jshx.httpData.USRCoreServiceSoap;

public class Test {
	public static void main(String[] args) {
		try {
			String content = "<?xml version='1.0' encoding='GB2312'?>"+
			"<DataBody>"+
			"<UserArea>"+
			"<Token>1bfafe0a-5e5f-4e6d-89f5-4fcb0e3cc4a3</Token>"+
			"<MethodID>AJDataPost.DataExchange.DataPost</MethodID>"+
			"<Params>"+
			"<Param name='xml'><![CDATA[<?xml version='1.0' encoding='utf-8'?>"+
			"<DataTable>"+
			"<Name>qyjbxx</Name>"+
			"<RowCount>1</RowCount>"+
			"<ColumnDesc>"+
			"<DeptID Range='DeptID' Default='' Required='1' DataFormat='A2' DataType='String' Text='上报市区编码'></DeptID>"+
			"<Qyid Range='' Default='' Required='1' DataFormat='N32' DataType='Int' Text='企业ID'></Qyid>"+
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
			"<YZBM Range='' Default='' Required='0' DataFormat='A6' DataType='String' Text='邮政编码'></YZBM>"+
			"<JJLX Range='' Default='' Required='1' DataFormat='A2' DataType='String' Text='经济类型'></JJLX>"+
			"<HYLB Range='' Default='' Required='1' DataFormat='A500' DataType='String' Text='行业类别'></HYLB>"+
			"<JYFW Range='' Default='' Required='0' DataFormat='A2000' DataType='String' Text='经营范围'></JYFW>"+
			"<QYJYZT Range='QYJYZT' Default='' Required='1' DataFormat='A2' DataType='String' Text='企业经营状态'></QYJYZT>"+
			"<ZCZJ Range='' Default='' Required='0' DataFormat='N14,2' DataType='Float' Text='注册资金(万元)'></ZCZJ>"+
			"<NXSSR Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='年销售收入(万元)'></NXSSR>"+
			"<NLR Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='年利润(万元)'></NLR>"+
			"<ZCZE Range='' Default='' Required='0' DataFormat='N14,2' DataType='float' Text='资产总额（万元）'></ZCZE>"+
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
			"</Range>"+
			"<RowContent>"+
			"<DeptID>03</DeptID>"+
			"<Qyid>32000000001</Qyid>"+
			"<qymc>太仓测试企业有限公司</qymc>"+
			"<GSZCH>320585400008322</GSZCH>"+
			"<Swdjh>2</Swdjh>"+
			"<Shxydm>2</Shxydm>"+
			"<ZZJGDM>2</ZZJGDM>"+
			"<CLRQ>2</CLRQ>"+
			"<FDDBR>2</FDDBR>"+
			"<LXR>2</LXR>"+
			"<LXDH>2</LXDH>"+
			"<DZYX>2</DZYX>"+
			"<DWCZ>2</DWCZ>"+
			"<ZCDZ>江苏省</ZCDZ>"+
			"<SCJYDZ>江苏省</SCJYDZ>"+
			"<YZBM>320585</YZBM>"+
			"<JJLX>2</JJLX>"+
			"<HYLB>2</HYLB>"+
			"<JYFW>2</JYFW>"+
			"<QYJYZT>2</QYJYZT>"+
			"<ZCZJ>2</ZCZJ>"+
			"<NXSSR>2</NXSSR>"+
			"<NLR>2</NLR>"+
			"<ZCZE>2</ZCZE>"+
			"<ZDMJ>2</ZDMJ>"+
			"<CYRYSL>2</CYRYSL>"+
			"<GMQK>2</GMQK>"+
			"<QYGM>2</QYGM>"+
			"<QYPMT>2</QYPMT>"+
			"<JD>2</JD>"+
			"<WD>2</WD>"+
			"</RowContent>"+
			"</DataTable>"+
			"]]></Param>"+
			"</Params>"+
			"</UserArea>"+
			"</DataBody>";
			
			System.out.println(content);
			
			USRCoreService service = new USRCoreService();
			USRCoreServiceSoap syn = service.getUSRCoreServiceSoap();
			String result = syn.callDestService(content);
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
