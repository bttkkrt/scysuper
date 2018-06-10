package com.jshx.http.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class XyptTest {
	public static void main(String[] args) {
		SynchronizeCompanyInfoService service = new SynchronizeCompanyInfoService();
		service.setHandlerResolver(new HandlerResolver() {
			
			@Override
			public List<Handler> getHandlerChain(PortInfo arg0) {
				List<Handler> handlerList = new ArrayList();
				handlerList.add(new ClientAuthenticationHandler(
						"Zhaj_seivice@APP-00199",
						"zhaj"));
				return handlerList;
			}
		});
		SynchronizeCompanyInfo syn = service.getSynchronizeCompanyInfoPort();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
		"<DeptID><![CDATA[54]]></DeptID>"+
		"<InfoClass><![CDATA[安监-危险化学品生产企业安全生产许可证审批（变更）信息]]></InfoClass>"+
		"<UploadTime><![CDATA[20161130140000]]></UploadTime></Head>"+
		"<Body><FIELDS><![CDATA[发证机关名称,许可证书有效截止日期,许可证书有效起始日期,企业生产地址,企业注册地址,经营范围,经济类型,主要负责人姓名,许可证编号,工商注册号/统一社会信用代码,企业全称（中文）,发证日期,组织机构代码]]></FIELDS>"+
		"<DATA>"+
		"<FZJGMC><![CDATA[发证机关名称]]></FZJGMC>"+
		"<XKZSYXJZRQ><![CDATA[2017-12-30]]></XKZSYXJZRQ>"+
		"<XKZSYXQSRQ><![CDATA[2016-01-01]]></XKZSYXQSRQ>"+
		"<QYSCDZ><![CDATA[苏州工业园区苏虹西路181号]]></QYSCDZ>"+
		"<QYZCDZ><![CDATA[苏州工业园区苏虹西路181号]]></QYZCDZ>"+
		"<JYFW><![CDATA[提供医疗实验仪器的维修、安装及相关的技术服务，生产医疗实验系统产品、销售本企业所生产的产品并提供售后服务。出租本企业所生产的产品并提供与产品配套相关的服务。]]></JYFW>"+
		"<JJLX><![CDATA[化学原料和化学制品制造业]]></JJLX>"+
		"<ZYFZRXM><![CDATA[HO TING LEUNG]]></ZYFZRXM>"+
		"<XKZBH><![CDATA[许可证编号]]></XKZBH>"+
		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
		"<FZRQ><![CDATA[2016-11-20]]></FZRQ>"+
		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
		"</DATA></Body></Request>";
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-非药品类易制毒化学品生产经营备案（变更）信息]]></InfoClass>"+
//		"<UploadTime><![CDATA[20150220037703]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[企业全称（中文）,发证机关名称,许可证书有效截止日期,许可证书有效起始日期,产品流向,注册地址,许可内容/产品流向,主要负责人姓名,许可证编号,生产/经营 范围,组织机构代码,发证日期,工商注册号/统一社会信用代码]]></FIELDS>"+
//		"<DATA>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"<FZJGMC><![CDATA[发证机关名称]]></FZJGMC>"+
//		"<XKZSYXJZRQ><![CDATA[2017-12-30]]></XKZSYXJZRQ>"+
//		"<XKZSYXQSRQ><![CDATA[2016-01-01]]></XKZSYXQSRQ>"+
//		"<CPLX><![CDATA[产品流向]]></CPLX>"+
//		"<ZCDZ><![CDATA[苏州工业园区苏虹西路181号]]></ZCDZ>"+
//		"<XKNR><![CDATA[许可内容]]></XKNR>"+
//		"<ZYFZRXM><![CDATA[HO TING LEUNG]]></ZYFZRXM>"+
//		"<XKZBH><![CDATA[许可证编号]]></XKZBH>"+
//		"<SCJYFW><![CDATA[生产/经营 范围]]></SCJYFW>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<FZRQ><![CDATA[2016-01-01]]></FZRQ>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"</DATA></Body></Request>";
		
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-荣誉表彰信息]]></InfoClass>"+
//		"<UploadTime><![CDATA[20150220037703]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,批准文号,表彰部门,荣誉称号,地区,年度,表彰日期,表彰内容]]></FIELDS>"+
//		"<DATA>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"<PZWH><![CDATA[无]]></PZWH>"+
//		"<BZBM><![CDATA[苏州市危险化学品安全监管协会]]></BZBM>"+
//		"<RYCH><![CDATA[先进单位]]></RYCH>"+
//		"<DQ><![CDATA[苏州]]></DQ>"+
//		"<ND><![CDATA[2015]]></ND>"+
//		"<BZRQ><![CDATA[2016-01-29]]></BZRQ>"+
//		"<BZNR><![CDATA[]]></BZNR>"+
//		"</DATA></Body></Request>";
		
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-失信行为信息]]></InfoClass>"+
//		"<UploadTime><![CDATA[20150220037703]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[组织机构代码,入库日期,工商注册号/统一社会信用代码,处罚名称,处罚决定书文号,被处罚当事人,被处罚当事人证件号码,处罚事由,处罚种类,行政处罚依据,行政处罚结论,没收违法所得,罚款金额,失信等级（严重、较重、一般）,处罚机关全称,行政处罚日期,是否公示,公示起日期,公示止日期,执行完成日期,执行情况,企业全称（中文）]]></FIELDS>"+
//		"<DATA>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<RKRQ><![CDATA[2016-01-01]]></RKRQ>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"<CFMC><![CDATA[处罚名称]]></CFMC>"+
//		"<CFJDSWH><![CDATA[1212]]></CFJDSWH>"+
//		"<BCFDSR><![CDATA[被处罚当事人]]></BCFDSR>"+
//		"<BCFDSRZJHM><![CDATA[212121]]></BCFDSRZJHM>"+
//		"<CFSY><![CDATA[处罚事由]]></CFSY>"+
//		"<CFZL><![CDATA[警告]]></CFZL>"+
//		"<XZCFYJ><![CDATA[行政处罚依据]]></XZCFYJ>"+
//		"<XZCFJL><![CDATA[行政处罚结论]]></XZCFJL>"+
//		"<MSWFSD><![CDATA[1000]]></MSWFSD>"+
//		"<FKJE><![CDATA[0]]></FKJE>"+
//		"<SXDJ><![CDATA[一般失信行为]]></SXDJ>"+
//		"<CFJGQC><![CDATA[处罚机关全称]]></CFJGQC>"+
//		"<XZCFRQ><![CDATA[2016-01-01]]></XZCFRQ>"+
//		"<SFGS><![CDATA[是]]></SFGS>"+
//		"<GSQRQ><![CDATA[2016-11-01]]></GSQRQ>"+
//		"<GSZRQ><![CDATA[2016-12-01]]></GSZRQ>"+
//		"<ZXWCRQ><![CDATA[2016-11-21]]></ZXWCRQ>"+
//		"<ZXQK><![CDATA[处理完毕]]></ZXQK>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"</DATA></Body></Request>";
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-危险化学品经营许可证登记（变更）审批信息]]></InfoClass>"+
//		"<UploadTime><![CDATA[20150220037703]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[发证日期,发证机关名称,许可证书有效截止日期,许可证书有效起始日期,经营场所地址,组织机构代码,经营范围,主要负责人姓名,许可证编号,工商注册号/统一社会信用代码,企业全称（中文）,经济类型]]></FIELDS>"+
//		"<DATA>"+
//		"<FZRQ><![CDATA[2016-01-01]]></FZRQ>"+
//		"<FZJGMC><![CDATA[发证机关名称]]></FZJGMC>"+
//		"<XKZSYXJZRQ><![CDATA[2017-12-30]]></XKZSYXJZRQ>"+
//		"<XKZSYXQSRQ><![CDATA[2016-01-01]]></XKZSYXQSRQ>"+
//		"<JYCSDZ><![CDATA[苏州工业园区苏虹西路181号]]></JYCSDZ>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<JYFW><![CDATA[经营范围]]></JYFW>"+
//		"<ZYFZRXM><![CDATA[HO TING LEUNG]]></ZYFZRXM>"+
//		"<XKZBH><![CDATA[许可证编号]]></XKZBH>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"<JJLX><![CDATA[化学原料和化学制品制造业]]></JJLX>"+
//		"</DATA></Body></Request>";
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-许可证注（撤）销信息]]></InfoClass>"+
//		"<UploadTime><![CDATA[20161130140000]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,许可证编号,批准日期,注（撤、吊）销文号,注（撤、吊）销原因,批准机关名称,许可证名称]]></FIELDS>"+
//		"<DATA>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"<XKZBH><![CDATA[许可证编号]]></XKZBH>"+
//		"<PZRQ><![CDATA[2016-11-30]]></PZRQ>"+
//		"<ZXWH><![CDATA[注（撤、吊）销文号]]></ZXWH>"+
//		"<ZXYY><![CDATA[注（撤、吊）销原因]]></ZXYY>"+
//		"<PZJGMC><![CDATA[批准机关名称]]></PZJGMC>"+
//		"<XKZMC><![CDATA[许可证名称]]></XKZMC>"+
//		"</DATA></Body></Request>";
		
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-烟花爆竹经营许可证登记（变更）审批]]></InfoClass>"+
//		"<UploadTime><![CDATA[20150220037703]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,证书编号,主要负责人姓名,注册地址,变更日期,仓库设施地址,许可经营范围,许可证书有效起始日期,许可证书有效截止日期,发证机关名称,发证日期,经济类型]]></FIELDS>"+
//		"<DATA>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"<ZSBH><![CDATA[证书编号]]></ZSBH>"+
//		"<ZYFZRXM><![CDATA[HO TING LEUNG]]></ZYFZRXM>"+
//		"<ZCDZ><![CDATA[苏州工业园区苏虹西路181号]]></ZCDZ>"+
//		"<BGRQ><![CDATA[2016-01-01]]></BGRQ>"+
//		"<CKSSDZ><![CDATA[苏州工业园区苏虹西路181号]]></CKSSDZ>"+
//		"<XKJYFW><![CDATA[许可经营范围]]></XKJYFW>"+
//		"<XKZSYXQSRQ><![CDATA[2016-01-01]]></XKZSYXQSRQ>"+
//		"<XKZSYXJZRQ><![CDATA[2017-12-30]]></XKZSYXJZRQ>"+
//		"<FZJGMC><![CDATA[发证机关名称]]></FZJGMC>"+
//		"<FZRQ><![CDATA[2016-01-01]]></FZRQ>"+
//		"<JJLX><![CDATA[化学原料和化学制品制造业]]></JJLX>"+
//		"</DATA></Body></Request>";
		
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
//		"<DeptID><![CDATA[54]]></DeptID>"+
//		"<InfoClass><![CDATA[安监-机构资质认定（变更）信息]]></InfoClass>"+
//		"<UploadTime><![CDATA[20150220037703]]></UploadTime></Head>"+
//		"<Body><FIELDS><![CDATA[发证日期,变更日期,组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,资质名称,资质证书编号,法定代表人证件号码,资质级别,业务范围,注册地址,证书有效起始日期,证书有效截止日期,发证机关名称]]></FIELDS>"+
//		"<DATA>"+
//		"<FZRQ><![CDATA[1997-08-14]]></FZRQ>"+
//		"<BGRQ><![CDATA[1997-08-14]]></BGRQ>"+
//		"<ZZJGDM><![CDATA[608207480]]></ZZJGDM>"+
//		"<QYMC><![CDATA[贝克曼库尔特实验系统(苏州)有限公司]]></QYMC>"+
//		"<GSZCH><![CDATA[企独苏总字第020332号]]></GSZCH>"+
//		"<ZZMC><![CDATA[营业执照]]></ZZMC>"+
//		"<ZZZSBH><![CDATA[320594400001058]]></ZZZSBH>"+
//		"<FDDBRZJHM><![CDATA[0000000000]]></FDDBRZJHM>"+
//		"<ZZJB><![CDATA[2]]></ZZJB>"+
//		"<YWFW><![CDATA[经营体外二类诊断试剂；组装及出租生化分析仪器]]></YWFW>"+
//		"<ZCDZ><![CDATA[苏州工业园区苏虹西路181号]]></ZCDZ>"+
//		"<ZSYXQSRQ><![CDATA[2007-10-08]]></ZSYXQSRQ>"+
//		"<ZSYXJZRQ><![CDATA[2047-08-13]]></ZSYXJZRQ>"+
//		"<FZJGMC><![CDATA[苏州工业园区工商行政管理局]]></FZJGMC>"+
//		"</DATA></Body></Request>";
		
		
		System.out.println(syn.uploadCompanyInfo(xml));
	}

}
