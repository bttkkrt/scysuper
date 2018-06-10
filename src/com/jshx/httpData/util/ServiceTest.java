package com.jshx.httpData.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClientBuilder;  
import org.apache.http.util.EntityUtils; 
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.areacodeRelation.entity.AreacodeRelation;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.core.base.action.BaseAction;
import com.jshx.heflRelation.entity.HyflRelation;
import com.jshx.httpData.service.HttpDataService;
import com.jshx.httpData.service.impl.HttpDataServiceImpl;
/**
 * 通过UrlConnection调用Webservice服务
 *
 */
public class ServiceTest{
	private static String postUrl = "http://218.25.105.86:9080/services/ResourceService";
	private static int socketTimeout = 30000;// 请求超时时间  
	private static int connectTimeout = 30000;// 传输超时时间
	
	public static String deleteDataByGuid(String guid){
		//请求体
        String soapXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:res=\"http://ws.itcast.cn/\" xmlns:xsd=\"http://resource.service.oas.asb.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
                      "<soapenv:Body> <res:deleteDataApp><arg0>delete</arg0>  <arg1>"+ guid+"</arg1> <arg2>F_Company</arg2> </res:deleteDataApp> </soapenv:Body> </soapenv:Envelope>";
        
        String retStr = "";  
        // 创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpPost httpPost = new HttpPost(postUrl);  
                //  设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(socketTimeout)  
                .setConnectTimeout(connectTimeout).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");  
            httpPost.setHeader("SOAPAction", "");  
            StringEntity data = new StringEntity(soapXml,  
                    Charset.forName("UTF-8"));  
            httpPost.setEntity(data);  
            CloseableHttpResponse response = closeableHttpClient  
                    .execute(httpPost);  
            HttpEntity httpEntity = response.getEntity();  
            if (httpEntity != null) {  
                // 打印响应内容  
                retStr = EntityUtils.toString(httpEntity, "UTF-8");  
                retStr = retStr.substring(retStr.indexOf("<return>")+8, retStr.indexOf("</return>"));
            }  
            // 释放资源  
            closeableHttpClient.close();  
        } catch (Exception e) {  
        	e.getStackTrace();
        }  
//        retStr= retStr.replace("&lt;", '<'+"");
//        retStr= retStr.replace("&gt;", '>'+"");
//        retStr= retStr.replace("&quot;", '"'+"");
//        System.out.println(retStr);
        return retStr;
	}
	public static Map getNewAreacode(Map map, List<AreacodeRelation> list, List<HyflRelation> hyflRelations){
		String oldAreacode = convertObject(map.get("XZJDDM"));
		for (AreacodeRelation areacodeRelation : list) {
			if(areacodeRelation.getOldAreacode().equals(oldAreacode)){
				map.put("XZQYDM", areacodeRelation.getNewAreacode());
				map.put("XZQYMC", areacodeRelation.getNewAreaname());
				break;
			}
		}
		String oldHyflcode = convertObject(map.get("HYLYIDS"));
		for (HyflRelation hyflRelation : hyflRelations) {
			if(hyflRelation.getOldCode().equals(oldHyflcode)){
				map.put("HYLYIDS", hyflRelation.getNewCode());
				map.put("HYLYSTRS", hyflRelation.getNewCodename());
				break;
			}
		}
		return map;
	}
	
	public static String parseMapToZCYHSBStr(Map map,List<AreacodeRelation> areacodeRelations){
		String optype = null;
		if(StringUtils.isEmpty((String)map.get("SBGUID"))){
			optype = "create";
		}else{
			optype = "update";
		}
		//直管部门级别
//		String zgbmjb = convertObject(map.get("ZGBMJB"));
//		zgbmjb = StringUtils.isEmpty(zgbmjb)?"1":zgbmjb;
		//复查时间
		String fcsj = convertObject(map.get("FCSJ"));
//		if(StringUtils.isEmpty(fcsj)){
//			fcsj = new Date().toString();
//		}else{
//			fcsj = fcsj + " 12:00:00";
//		}
		//检查时间
		String jcsj = convertObject(map.get("JCSJ"));
//		if(StringUtils.isEmpty(jcsj)){
//			jcsj = new Date().toString();
//		}else{
//			jcsj = jcsj + " 12:00:00";
//		}
		//检查时间
		String jhwcsj = convertObject(map.get("JHWCSJ"));
//		if(StringUtils.isEmpty(jhwcsj)){
//			jhwcsj = new Date().toString();
//		}else{
//			jhwcsj = jhwcsj + " 12:00:00";
//		}
		//检查大类
		String jcdl = convertObject(map.get("JCDL"));
		if("1".equals(jcdl)){
			jcdl = "基础管理";
		}else{
			jcdl = "现场管理";
		}
		//检查中类
//		String jczl = convertObject(map.get("JCZL"));
//		if("2_1".equals(jczl)){
//			jczl = "整改完成";
//		}else{
//			jczl = "待整改";
//		}
		//目前状态
		String mqzt = convertObject(map.get("MQZT"));
		if("0".equals(mqzt)){
			mqzt = "已整改";
		}else{
			mqzt = "待整改";
		}
		String jb = convertObject(map.get("JB"));
		if("0".equals(jb)){
			jb = "一般隐患";
		}else{
			jb = "重大隐患";
		}
		double zgje = 0;
		String zgtrzj = convertObject(map.get("ZGTRZJ"));
		if(!"".equals(zgtrzj)){
			zgje = (Double.valueOf(zgtrzj)*10000);
		}
		int zgje2 = (int)zgje;
		
		//区县
		String oldAreacode = convertObject(map.get("SZZID"));
		for (AreacodeRelation areacodeRelation : areacodeRelations) {
			if(areacodeRelation.getOldAreacode().equals(oldAreacode)){
				map.put("SZZID", areacodeRelation.getNewAreacode());
				break;
			}
		}
		
		String param = "<![CDATA["+
				"<table>"+
				"<entityType>S_SecurityTroubleLogInfo</entityType>"+
				"<optype>"+optype+"</optype>"+
				"<body>"+
					"<S_SecurityTroubleLog>"+
						"<guid>"+convertObject(map.get("SBGUID"))+"</guid>"+
//						"<!--要求为全球唯一ID如没有可按照（辽宁省-xx市-xx区-系统内ID）进行上传，例：LN-SY-YH-0000001-->"+
						"<yhzt>已提交</yhzt>"+
//						"<!--VARCHAR2(32 CHAR)  隐患状态  未提交 已提交-->"+
						"<jcr>"+convertObject(map.get("JCRY"))+"</jcr>"+
//						"<!--VARCHAR2(32 CHAR)  检查人-->"+
						"<jcrGuid>sss</jcrGuid>"+
//						"<!--必填项 VARCHAR2(128 CHAR) 检查人guid-->"+
						"<jcrbm>"+convertObject(map.get("SZZNAME"))+"</jcrbm>"+
//						"<!--必填项 VARCHAR2(32 CHAR) 检查人部门-->"+
//						"<jcrbmGuid>"+sss+"</jcrbmGuid>"+
//						"<!--VARCHAR2(128 CHAR) 检查人部门Guid-->"+
						"<jcqy>"+convertObject(map.get("QYMC"))+"</jcqy>"+
//						"<!--VARCHAR2(64 CHAR) 检查企业-->"+
						"<jcqyGuid>"+convertObject(map.get("GUID"))+"</jcqyGuid>>"+
//						"<!--必填项  VARCHAR2(128 CHAR) 检查企业GUID-->"+
						"<jcsj>"+jcsj+"</jcsj>"+
//						"<!--TIMESTAMP(6) 检查时间-->"+
						"<jcdd>"+convertObject(map.get("QYMC"))+"</jcdd>"+
//						"<!--必填项  VARCHAR2(128 CHAR) 检查地点-->"+
						"<jcxgs>"+convertObject(map.get("YHSL"))+"</jcxgs>"+
//						"<!--NUMBER(10) 检查项个数-->"+
						"<yhgs>"+convertObject(map.get("YHSL"))+"</yhgs>"+
//						"<!--NUMBER(10) 隐患个数-->"+
						"<rwfl>企业</rwfl>"+
//						"<!--VARCHAR2(16 CHAR) 任务分类  企业或个人-->"+
//						"<zgsGuid>"+sss+"</zgsGuid>"+
//						"<!--VARCHAR2(128 CHAR) 总公司Guid-->"+
//						"<sjgsGuid>"+sss+"</sjgsGuid>"+
//						"<!--VARCHAR2(128 CHAR) 上级公司Guid-->"+
						"<jcjg>0</jcjg>"+
//						"<!--VARCHAR2(32 CHAR) 检查结果0：有隐患；1：无隐患 -->"+
						"<zgzt>"+mqzt+"</zgzt>"+
//						"<!--VARCHAR2(32 CHAR) 整改状态:已整改/待整改 -->"+
//						"<fczt>"+convertObject(map.get("GUID"))+"</fczt>"+
//						"<!--VARCHAR2(32 CHAR) 复查状态:已复查/待复查-->"+
//						"<syzt>"+convertObject(map.get("GUID"))+"</syzt>"+
//						"<!--VARCHAR2(16 CHAR) 省局审阅状态:未审阅/已审阅-->"+
						"<synr>"+convertObject(map.get("FCYSQK"))+"</synr>"+
//						"<!--VARCHAR2(512 CHAR) 审阅内容-->"+
//						"<gllbGuid>"+sss+"</gllbGuid>"+
//						"<!--VARCHAR2(512 CHAR) 关联重大危险源Guid-->"+
					"</S_SecurityTroubleLog>"+
					"<S_CheckResult>"+
					"<guid>"+convertObject(map.get("SBGUID"))+"</guid>"+
//						"<!--要求为全球唯一ID如没有可按照（辽宁省-xx市-xx区-系统内ID）进行上传，例：LN-SY-YH-0000001-->"+
//						"<troubleLogGuid>"+sss+"</troubleLogGuid>"+
//							"<!--VARCHAR2(128 CHAR)   troubleLog 的Guid-->"+
//						"<company_guid>"+sss+"</company_guid>"+
//							"<!--企业guid-->"+
							"<checkDate>"+jcsj+"</checkDate>"+
//							"<!--必填项  TIMESTAMP(6) 检查日期 -->"+
							"<checkerName>"+convertObject(map.get("JCRY"))+"</checkerName>"+
//							"<!--必填项  VARCHAR2(64 CHAR) 检查人名称0511+ -->"+
							"<checkItem>"+jcdl+"</checkItem>"+
//							"<!--VARCHAR2(128 CHAR) 检查项-->"+
//							"<checkItemSpecial>"+convertObject(map.get("GUID"))+"</checkItemSpecial>"+
//							"<!--VARCHAR2(128 CHAR) 检查项详细-->"+
//						"<checkContent>1. 库房与制冷机房～变配电所和控制室贴邻布置时，相邻的墙体至少一面为防火墙。屋顶耐火极限不低于1.00h。"+
//						"2. 冷藏间与穿堂之间的隔墙应为防火隔墙。防火墙耐火极限不应低于3.0h，该防火墙上的冷藏门可为非防火门。+"+
//						"</checkContent>"+
//							"<!--VARCHAR2(512 CHAR) 检查要求 -->"+
							"<clfs>限期整改</clfs>"+
//						"<!--必填项    VARCHAR2(64 CHAR)  处理方式分为限期整改、现场整改、停产停业整改-->"+
//							"<checkContentId>"+572395+"</checkContentId>"+
//							"<!--NUMBER(19) 检查项id-->"+
							"<companyStr>"+convertObject(map.get("QYMC"))+"</companyStr>"+
//							"<!--VARCHAR2(128 CHAR) 企业名称 -->"+
//						"<companyCheckItemId>"+sss+"</companyCheckItemId>"+
//							"<!--VARCHAR2(128 CHAR) 企业检查项Id -->"+
							"<dangertype>"+jb+"</dangertype>"+
//							"<!--必填项     VARCHAR2(32 CHAR) 隐患级别 分为  一般隐患、重大隐患-->"+
							"<jcjg>"+1+"</jcjg>"+
//							"<!--NUMBER(19) 检查结果（1合格，0不合格） -->"+
							"<mark>"+convertObject(map.get("WTYH"))+"</mark>"+
//							"<!--VARCHAR2(1024 CHAR) 检查情况-->"+
							"<status>"+mqzt+"</status>"+
//							"<!--VARCHAR2(128 CHAR) 状态 【待整改、已整改、已完成】-->"+
							"<xqzgsj>"+jhwcsj+"</xqzgsj>"+
//							"<!--必填项     TIMESTAMP(6) 限期整改时间-->"+
							"<zgje>"+zgje2+"</zgje>"+
//							"<!--NUMBER(19) 整改金额-->"+
							"<zgyj>"+convertObject(map.get("CSFA"))+"</zgyj>"+
//							"<!--VARCHAR2(256 CHAR) 整改意见-->"+
							"<content>"+convertObject(map.get("WTYH"))+"</content>"+
//							"<!--VARCHAR2(256 CHAR) 整改内容-->"+
							"<zgdate>"+fcsj+"</zgdate>"+
//							"<!--必填项    TIMESTAMP(6) 整改完成时间 -->"+
							"<changeId>"+convertObject(map.get("LINKID"))+"</changeId>"+
//							"<!--VARCHAR2(128 CHAR) 整改附件关联Guid-->"+
//						"<qydm>"+sss+"</qydm>"+
//							"<!--VARCHAR2(256 CHAR) 区域代码 -->"+
							"<jddm>"+convertObject(map.get("SZZID"))+"</jddm>"+
//							"<!--必填项  VARCHAR2(64 CHAR) 街道代码 -->"+
//							"<sqdm>"+convertObject(map.get("GUID"))+"</sqdm>"+
//							"<!--VARCHAR2(64 CHAR) 社区代码 -->"+
						"<yhly>"+3+"</yhly>"+
//						"<!--必填项    NUMBER(10) 隐患来源1：班组检查、2：车间检查、3：企业(厂矿)检查、4：集团检查、5：监督检查、6：投诉举报、7：标准化自评/评审、8：其他-->"+
							"<yhdd>"+convertObject(map.get("QYMC"))+"</yhdd>"+
//							"<!--必填项    VARCHAR2(128 CHAR) 隐患地点 -->"+
							"<yhbw>"+convertObject(map.get("JCBW"))+"</yhbw>"+
//							"<!--必填项    VARCHAR2(128 CHAR) 隐患部位-->"+
//							"<pcr>"+sss+"</pcr>"+
//							"<!--VARCHAR2(64 CHAR) 排查人-->"+
							"<checkMethod>B99</checkMethod>"+
//							"<!--VARCHAR2(32 CHAR) 检查方法： A01：资质证照; A02：安全生产管理机构及人员; A03：安全生产责任制;A04:安全生产规章制度;A05:安全生产教育培训;A06:安全生产管理基础档案;A07:应急管理;A08:职业卫生;A09:安全操作规程"+
//						"A10:安全生产投入;A11:特种设备基础管理;A12:相关方基础管理;A99:其他基础管理;B:现场管理;B01:生产设备设施;B02:特种设备现场管理;B03:消防与应急安全;B04:用电安全;B05:职业卫生现场管理;B06:危险化学品;B07:场所环境+"+
//						"B08:从业人员操作行为;B09:有限空间现场管理;B10:辅助动力系统;B11:相关方现场管理;B99:其他现场管理-->"+
//							"<superviseDepartment>"+convertObject(map.get("GUID"))+"</superviseDepartment>"+
//							"<!--VARCHAR2(32 CHAR) 监管部门 -->"+
							"<fixerName>"+convertObject(map.get("ZGZRR"))+"</fixerName>"+
//							"<!--必填项  VARCHAR2(32 CHAR) 整改负责人-->"+
//							"<superviserName>"+convertObject(map.get("GUID"))+"</superviserName>"+
//							"<!--VARCHAR2(32 CHAR) 监管负责人-->"+
							"<recheckResult>合格</recheckResult>"+
//							"<!--必填项  VARCHAR2(128 CHAR) 复查结果：合格、不合格-->"+
							"<recheckDate>"+fcsj+"</recheckDate>"+
//							"<!--必填项    TIMESTAMP(6) 复查时间 -->"+
//							"<recheckContent>"+123123+"</recheckContent>"+
//							"<!--VARCHAR2(512 CHAR) 复查内容-->"+
//							"<recheckDepartment>"+convertObject(map.get("GUID"))+"</recheckDepartment>"+
//							"<!--VARCHAR2(32 CHAR) 复查部门  -->"+
							"<recheckerName>"+convertObject(map.get("FCR"))+"</recheckerName>"+
//							"<!--必填项    VARCHAR2(32 CHAR) 复查人-->"+
//							"<recheckStatus>"+convertObject(map.get("GUID"))+"</recheckStatus>"+
//							"<!--VARCHAR2(32 CHAR) 复查状态：待复查、已复查-->"+
//							"<sfxysb>"+sss+"</sfxysb>"+
//							"<!--NUMBER(10) 是否需要上报  -->"+
//							"<ffcshya>"+convertObject(map.get("GUID"))+"</ffcshya>"+
//							"<!--VARCHAR2(1024 CHAR) 防范措施和预案  -->"+
//							"<zgsGuid>"+sss+"</zgsGuid>"+
//							"<!--VARCHAR2(128 CHAR) 总公司Guid -->"+
//							"<sjgsGuid>"+sss+"</sjgsGuid>"+
//							"<!--VARCHAR2(128 CHAR) 上级公司Guid-->"+
//							"<tjzt>"+sss+"</tjzt>"+
//							"<!--VARCHAR2(16 CHAR) 提交状态  未提交/已提交 -->"+
//							"<yhxzjcsyy>"+sss+"</yhxzjcsyy>"+
//							"<!--VARCHAR2(1024 CHAR) 隐患现状及产生原因  -->"+
//							"<whcd>"+sss+"</whcd>"+
//							"<!--VARCHAR2(1024 CHAR) 隐患危害程度  -->"+
//							"<nycd>"+sss+"</nycd>"+
//							"<!--VARCHAR2(1024 CHAR) 隐患整改难易程度分析  -->"+
//							"<zlsx>"+sss+"</zlsx>"+
//							"<!--TIMESTAMP(6) 隐患治理时限-->"+
//							"<zlfa>"+sss+"</zlfa>"+
//							"<!--VARCHAR2(1024 CHAR) 隐患治理方案 -->"+
//							"<lscs>"+sss+"</lscs>"+
//							"<!--VARCHAR2(1024 CHAR) 整改前拟采取的保障安全的临时措施  -->"+
//							"<zgjg>"+sss+"</zgjg>"+
//							"<!--VARCHAR2(16 CHAR) 整改结果：已整改、未整改 -->"+
//							"<jcattchs>"+
//								"<attch>"+
//									"<filename></filename>"+
//									"<contentType></contentType>"+
//									"<module>"+"</module>"+
//									"<isimage>"+"</isimage>"+
//									"<url>"+"</url>"+
//								"</attch>"+
//							"</jcattchs>"+
//							"<!--检查附件列表 -->"+
//							"<zgattchs>"+
//								"<attch>"+
//									"<filename>"+"</filename>"+
//									"<contentType>"+"</contentType>"+
//									"<module>"+"</module>"+
//									"<isimage>"+"</isimage>"+
//									"<url>"+"</url>"+
//								"</attch>"+
//							"</zgattchs>"+
//							"<!--整改附件列表 -->"+
"</S_CheckResult>"+

				"</body>"+
				
				"</table>"+
				"		]]>";
		return param;
	}
	
	public static String parseMapToStr(Map map){
		String optype = null;
		if(StringUtils.isEmpty((String)map.get("GUID"))){
			optype = "create";
		}else{
			optype = "update";
		}
		//直管部门级别
		String zgbmjb = convertObject(map.get("ZGBMJB"));
		zgbmjb = StringUtils.isEmpty(zgbmjb)?"1":zgbmjb;
		//成立时间
		String clsj = convertObject(map.get("CLRQ"));
//		if(StringUtils.isEmpty(clsj)){
//			clsj = new Date().toString();
//		}else{
//			clsj = clsj + " 12:00:00";
//		}
		//区县
		
		
		String param = "<![CDATA["+
				"<table>"+
				"<entityType>F_Company</entityType>"+
				"<optype>"+optype+"</optype>"+
				"<body>"+
				
					"	<F_Company>"+
					"<guid>"+convertObject(map.get("GUID"))+"</guid>"+
//					"<!--要求为全球唯一ID如没有可按照（辽宁省-xx市-xx区-系统内ID）进行上传，例：LN-SY-YH-0000001-->"+
					"<aqscfzr>"+convertObject(map.get("AQSCFZR"))+"</aqscfzr>"+
//					"<!--必填项  VARCHAR2(32 CHAR) 安全生产负责人 -->"+
					"<aqscfzryddh>"+convertObject(map.get("AQSCFZRYDDH"))+"</aqscfzryddh>"+
//					"<!--必填项  VARCHAR2(32 CHAR) 安全生产负责人移动电话 -->"+
					"<aqscfzrgddh>"+convertObject(map.get("AQSCFZRGDDH"))+"</aqscfzrgddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全生产负责人固定电话 -->"+
//					"<aqglryyddh>"+map.get("aqglryyddh")+"</aqglryyddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理人员移动电话 -->"+
					"<provincedm>210000000000</provincedm>"+
//					"<!--必填项  NUMBER(19) 省级代码 -->"+
					"<provincemc>辽宁省</provincemc>"+
//					"<!--VARCHAR2(32 CHAR) 省级名称 -->"+
					"<citydm>210400000000</citydm>"+
//					"<!--必填项  NUMBER(19) 市级代码 -->"+
					"<citymc>抚顺市</citymc>"+
//					"<!--VARCHAR2(32 CHAR) 市级名称 -->"+
					"<xzqydm>"+convertObject(map.get("XZQYDM"))+"</xzqydm>"+
//					"<!--NUMBER(19) 区县代码 -->"+
					"<xzqymc>"+convertObject(map.get("XZQYMC"))+"</xzqymc>"+
//					"<!--VARCHAR2(128 CHAR) 区县名称 -->"+
//					"<xzjddm>2101143500</xzjddm>"+
//					"<!--NUMBER(19) 乡镇街道代码 -->"+
//					"<xzjdmc>"+map.get("xzjdmc")+"</xzjdmc>"+
//					"<!--VARCHAR2(128 CHAR) 乡镇街道名称 -->"+
//					"<sssqdm>"+map.get("sssqdm")+"</sssqdm>"+
//					"<!--NUMBER(19) 所属社区代码 -->"+
//					"<sssqmc>"+map.get("sssqmc")+"</sssqmc>"+
//					"<!--VARCHAR2(128 CHAR) 所属社区 -->"+
					"<clrq>"+clsj+"</clrq>"+
//					"<!--TIMESTAMP(6) 成立日期 -->"+
					"<frdbmc>"+convertObject(map.get("FRDBMC"))+"</frdbmc>"+
//					"<!--VARCHAR2(128 CHAR) 法人代表名称 -->"+
//					"<frgddh>"+convertObject(map.get("frgddh"))+"</frgddh>"+
//					"<!--VARCHAR2(32 CHAR) 法人固定电话 -->"+
					"<fryddh>"+convertObject(map.get("FRYDDH"))+"</fryddh>"+
//					"<!--VARCHAR2(32 CHAR) 法人移动电话 -->"+
//					"<hyIds>"+convertObject(map.get("hyIds"))+"</hyIds>"+
//					"<!--VARCHAR2(1024 CHAR) 所有行业Ids -->"+
					"<hylyIds>"+convertObject(map.get("HYLYIDS"))+"</hylyIds>"+
//					"<!--必填项  VARCHAR2(4000 CHAR) 行业领域Id 多个行业的话，中间用英文逗号分隔，如10037,10038-->"+
					"<hylyStrs>"+convertObject(map.get("HYLYSTRS"))+"</hylyStrs>"+
//					"<!--VARCHAR2(4000 CHAR) 行业领域Str -->"+
//					"<jjlxdm>"+convertObject(map.get("jjlxdm"))+"</jjlxdm>"+
//					"<!--VARCHAR2(128 CHAR) 经济类型代码 枚举类型，具体取值参见编码表-->"+
					"<qylxdh>"+convertObject(map.get("QYLXDH"))+"</qylxdh>"+
//					"<!--VARCHAR2(32 CHAR) 企业联系电话 -->"+
					"<qymc>"+convertObject(map.get("QYMC"))+"</qymc>"+
//					"<!--必填项  VARCHAR2(128 CHAR) 企业名称 -->"+
					"<qywzjd>"+convertObject(map.get("QYWZJD"))+"</qywzjd>"+
//					"<!--FLOAT 企业位置经度 -->"+
					"<qywzwd>"+convertObject(map.get("QYWZWD"))+"</qywzwd>"+
//					"<!--FLOAT 企业位置纬度 -->"+
//					"<qyxzlsgx>"+convertObject(map.get("qyxzlsgx"))+"</qyxzlsgx>"+
//					"<!--VARCHAR2(128 CHAR) 企业行政隶属关系 枚举类型：[10:中央级，20:省级，"+
//					"40:市、地区级，50:县级，60:街道、镇、乡级，61:街道，62:镇，63:乡，90:其他]-->"+
					"<registername>"+convertObject(map.get("REGISTERNAME"))+"</registername>"+
//					"<!--VARCHAR2(12 CHAR) 注册联系人(安全管理人员) -->"+
					"<registertel>"+convertObject(map.get("REGISTERTEL"))+"</registertel>"+
//					"<!--VARCHAR2(32 CHAR) 注册联系人联系电话(安全管理人员固定电话) -->"+
					"<scjydz>"+convertObject(map.get("SCJYDZ"))+"</scjydz>"+
//					"<!--VARCHAR2(128 CHAR) 生产经营地址 -->"+
					"<yzbm>"+convertObject(map.get("YZBM"))+"</yzbm>"+
//					"<!--VARCHAR2(32 CHAR) 邮政编码 -->"+
					"<zcdz>"+convertObject(map.get("ZCDZ"))+"</zcdz>"+
//					"<!--VARCHAR2(256 CHAR) 注册地址 -->"+
					"<zzjgdm>"+convertObject(map.get("ZZJGDM"))+"</zzjgdm>"+
//					"<!--VARCHAR2(64 CHAR) 组织机构代码 -->"+
					"<aqjgszqk>0</aqjgszqk>"+
//					"<!--NUMBER(1) 安全机构设置情况 取值[0:否，1:是]-->"+
//					"<aqgljgmc>"+convertObject(map.get("aqgljgmc"))+"</aqgljgmc>"+
//					"<!--VARCHAR2(128 CHAR) 安全管理机构名称 -->"+
//					"<aqgljgfzr>"+convertObject(map.get("aqgljgfzr"))+"</aqgljgfzr>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理机构负责人 -->"+
//					"<aqgljgfzryddh>"+convertObject(map.get("aqgljgfzryddh"))+"</aqgljgfzryddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理机构负责人移动电话 -->"+
//					"<aqgljgfzrgddh>"+convertObject(map.get("aqgljgfzrgddh"))+"</aqgljgfzrgddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理机构负责人固定电话 -->"+
					"<sfxysb>"+convertObject(map.get("SFXYSB"))+"</sfxysb>"+
//					"<!--NUMBER(1) 是否需要上报  取值[0:否，1:是]-->"+
					"<qyrs>"+convertObject(map.get("QYRS"))+"</qyrs>"+
//					"<!--必填项  NUMBER(10) 企业人数 -->"+
//					"<gridGuids>"+convertObject(map.get("gridGuids"))+"</gridGuids>"+
//					"<!--VARCHAR2(512 CHAR) 直管网格的guid  -->"+
					"<sfsjzywh>"+convertObject(map.get("SFSJZYWH"))+"</sfsjzywh>"+
//					"<!--NUMBER(1) 是否涉及职业危害 取值[0:否，1:是] -->"+
					"<yyzzlx>无</yyzzlx>"+
//					"<!--必填项  VARCHAR2(64 CHAR) 营业执照类型 枚举类型，具体取值参见编码表-->"+
//					"<yyzzbh>"+convertObject(map.get("yyzzbh"))+"</yyzzbh>"+
//					"<!--VARCHAR2(128 CHAR) 营业执照编号或统一社会信用代码 -->"+
					"<jymj>"+convertObject(map.get("JYMJ"))+"</jymj>"+
//					"<!--必填项  FLOAT 用地面积(企业占地面积) -->"+
					"<jzmjpfm>"+convertObject(map.get("JZMJPFM"))+"</jzmjpfm>"+
//					"<!--FLOAT 建筑面积平方米 -->"+
//					"<scgrs>"+convertObject(map.get("scgrs"))+"</scgrs>"+
//					"<!--NUMBER(10) 生产工人数 -->"+
//					"<wbjGridGuids>"+convertObject(map.get("wbjGridGuids"))+"</wbjGridGuids>"+
//					"<!--VARCHAR2(512 CHAR) 委办局网格guids  -->"+
//					"<ajjGridGuids>"+convertObject(map.get("ajjGridGuids"))+"</ajjGridGuids>"+
//					"<!--VARCHAR2(512 CHAR) 安监局网格guids  -->"+
					"<zgbmjb>"+zgbmjb+"</zgbmjb>"+
//					"<!--必填项  NUMBER(1) 直管部门级别 枚举类型，取值[1:省级，2:市级，3:(区、县级)，"+
//					"4:(乡、镇、街道)，5:(社区、村)]-->"+
					"</F_Company>"+

					"</body>"+
					
					"</table>"+
					"		]]>";
		return param;
	}
	
	@SuppressWarnings("rawtypes")
	public int parseMapToStr2(List<Map> coms){
		String optype = null;
		int success = 0;
		for (Map map : coms) {
			if(StringUtils.isEmpty((String)map.get("GUID"))){
				optype = "create";
			}else{
				optype = "update";
			}
			String rowId = convertObject(map.get("ID"));
			//直管部门级别
			String zgbmjb = convertObject(map.get("ZGBMJB"));
			zgbmjb = StringUtils.isEmpty(zgbmjb)?"1":zgbmjb;
			String clsj = convertObject(map.get("CLRQ"));
			if(StringUtils.isEmpty(clsj)){
				clsj = new Date().toString();
			}else{
				clsj = clsj + " 12:00:00";
			}
			String param = "<![CDATA["+
					"<table>"+
					"<entityType>F_Company</entityType>"+
					"<optype>"+optype+"</optype>"+
					"<body>"+

					"	<F_Company>"+
					"<guid>"+convertObject(map.get("GUID"))+"</guid>"+
//					"<!--要求为全球唯一ID如没有可按照（辽宁省-xx市-xx区-系统内ID）进行上传，例：LN-SY-YH-0000001-->"+
					"<aqscfzr>"+convertObject(map.get("AQSCFZR"))+"</aqscfzr>"+
//					"<!--必填项  VARCHAR2(32 CHAR) 安全生产负责人 -->"+
					"<aqscfzryddh>"+convertObject(map.get("AQSCFZRYDDH"))+"</aqscfzryddh>"+
//					"<!--必填项  VARCHAR2(32 CHAR) 安全生产负责人移动电话 -->"+
					"<aqscfzrgddh>"+convertObject(map.get("AQSCFZRGDDH"))+"</aqscfzrgddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全生产负责人固定电话 -->"+
//					"<aqglryyddh>"+map.get("aqglryyddh")+"</aqglryyddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理人员移动电话 -->"+
					"<provincedm>210000000000</provincedm>"+
//					"<!--必填项  NUMBER(19) 省级代码 -->"+
					"<provincemc>辽宁省</provincemc>"+
//					"<!--VARCHAR2(32 CHAR) 省级名称 -->"+
					"<citydm>210400000000</citydm>"+
//					"<!--必填项  NUMBER(19) 市级代码 -->"+
					"<citymc>抚顺市</citymc>"+
//					"<!--VARCHAR2(32 CHAR) 市级名称 -->"+
//					"<xzqydm>210114000000</xzqydm>"+
//					"<!--NUMBER(19) 区县代码 -->"+
//					"<xzqymc>于洪区</xzqymc>"+
//					"<!--VARCHAR2(128 CHAR) 区县名称 -->"+
//					"<xzjddm>2101143500</xzjddm>"+
//					"<!--NUMBER(19) 乡镇街道代码 -->"+
//					"<xzjdmc>"+map.get("xzjdmc")+"</xzjdmc>"+
//					"<!--VARCHAR2(128 CHAR) 乡镇街道名称 -->"+
//					"<sssqdm>"+map.get("sssqdm")+"</sssqdm>"+
//					"<!--NUMBER(19) 所属社区代码 -->"+
//					"<sssqmc>"+map.get("sssqmc")+"</sssqmc>"+
//					"<!--VARCHAR2(128 CHAR) 所属社区 -->"+
					"<clrq>"+clsj+"</clrq>"+
//					"<!--TIMESTAMP(6) 成立日期 -->"+
					"<frdbmc>"+convertObject(map.get("FRDBMC"))+"</frdbmc>"+
//					"<!--VARCHAR2(128 CHAR) 法人代表名称 -->"+
//					"<frgddh>"+convertObject(map.get("frgddh"))+"</frgddh>"+
//					"<!--VARCHAR2(32 CHAR) 法人固定电话 -->"+
					"<fryddh>"+convertObject(map.get("FRYDDH"))+"</fryddh>"+
//					"<!--VARCHAR2(32 CHAR) 法人移动电话 -->"+
//					"<hyIds>"+convertObject(map.get("hyIds"))+"</hyIds>"+
//					"<!--VARCHAR2(1024 CHAR) 所有行业Ids -->"+
					"<hylyIds>"+convertObject(map.get("HYLYIDS"))+"</hylyIds>"+
//					"<!--必填项  VARCHAR2(4000 CHAR) 行业领域Id 多个行业的话，中间用英文逗号分隔，如10037,10038-->"+
					"<hylyStrs>"+convertObject(map.get("HYLYSTRS"))+"</hylyStrs>"+
//					"<!--VARCHAR2(4000 CHAR) 行业领域Str -->"+
//					"<jjlxdm>"+convertObject(map.get("jjlxdm"))+"</jjlxdm>"+
//					"<!--VARCHAR2(128 CHAR) 经济类型代码 枚举类型，具体取值参见编码表-->"+
					"<qylxdh>"+convertObject(map.get("QYLXDH"))+"</qylxdh>"+
//					"<!--VARCHAR2(32 CHAR) 企业联系电话 -->"+
					"<qymc>"+convertObject(map.get("QYMC"))+"</qymc>"+
//					"<!--必填项  VARCHAR2(128 CHAR) 企业名称 -->"+
					"<qywzjd>"+convertObject(map.get("QYWZJD"))+"</qywzjd>"+
//					"<!--FLOAT 企业位置经度 -->"+
					"<qywzwd>"+convertObject(map.get("QYWZWD"))+"</qywzwd>"+
//					"<!--FLOAT 企业位置纬度 -->"+
//					"<qyxzlsgx>"+convertObject(map.get("qyxzlsgx"))+"</qyxzlsgx>"+
//					"<!--VARCHAR2(128 CHAR) 企业行政隶属关系 枚举类型：[10:中央级，20:省级，"+
//					"40:市、地区级，50:县级，60:街道、镇、乡级，61:街道，62:镇，63:乡，90:其他]-->"+
					"<registername>"+convertObject(map.get("REGISTERNAME"))+"</registername>"+
//					"<!--VARCHAR2(12 CHAR) 注册联系人(安全管理人员) -->"+
					"<registertel>"+convertObject(map.get("REGISTERTEL"))+"</registertel>"+
//					"<!--VARCHAR2(32 CHAR) 注册联系人联系电话(安全管理人员固定电话) -->"+
					"<scjydz>"+convertObject(map.get("SCJYDZ"))+"</scjydz>"+
//					"<!--VARCHAR2(128 CHAR) 生产经营地址 -->"+
					"<yzbm>"+convertObject(map.get("YZBM"))+"</yzbm>"+
//					"<!--VARCHAR2(32 CHAR) 邮政编码 -->"+
					"<zcdz>"+convertObject(map.get("ZCDZ"))+"</zcdz>"+
//					"<!--VARCHAR2(256 CHAR) 注册地址 -->"+
					"<zzjgdm>"+convertObject(map.get("ZZJGDM"))+"</zzjgdm>"+
//					"<!--VARCHAR2(64 CHAR) 组织机构代码 -->"+
					"<aqjgszqk>0</aqjgszqk>"+
//					"<!--NUMBER(1) 安全机构设置情况 取值[0:否，1:是]-->"+
//					"<aqgljgmc>"+convertObject(map.get("aqgljgmc"))+"</aqgljgmc>"+
//					"<!--VARCHAR2(128 CHAR) 安全管理机构名称 -->"+
//					"<aqgljgfzr>"+convertObject(map.get("aqgljgfzr"))+"</aqgljgfzr>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理机构负责人 -->"+
//					"<aqgljgfzryddh>"+convertObject(map.get("aqgljgfzryddh"))+"</aqgljgfzryddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理机构负责人移动电话 -->"+
//					"<aqgljgfzrgddh>"+convertObject(map.get("aqgljgfzrgddh"))+"</aqgljgfzrgddh>"+
//					"<!--VARCHAR2(32 CHAR) 安全管理机构负责人固定电话 -->"+
					"<sfxysb>"+convertObject(map.get("SFXYSB"))+"</sfxysb>"+
//					"<!--NUMBER(1) 是否需要上报  取值[0:否，1:是]-->"+
					"<qyrs>"+convertObject(map.get("QYRS"))+"</qyrs>"+
//					"<!--必填项  NUMBER(10) 企业人数 -->"+
//					"<gridGuids>"+convertObject(map.get("gridGuids"))+"</gridGuids>"+
//					"<!--VARCHAR2(512 CHAR) 直管网格的guid  -->"+
					"<sfsjzywh>"+convertObject(map.get("SFSJZYWH"))+"</sfsjzywh>"+
//					"<!--NUMBER(1) 是否涉及职业危害 取值[0:否，1:是] -->"+
					"<yyzzlx>无</yyzzlx>"+
//					"<!--必填项  VARCHAR2(64 CHAR) 营业执照类型 枚举类型，具体取值参见编码表-->"+
//					"<yyzzbh>"+convertObject(map.get("yyzzbh"))+"</yyzzbh>"+
//					"<!--VARCHAR2(128 CHAR) 营业执照编号或统一社会信用代码 -->"+
					"<jymj>"+convertObject(map.get("JYMJ"))+"</jymj>"+
//					"<!--必填项  FLOAT 用地面积(企业占地面积) -->"+
					"<jzmjpfm>"+convertObject(map.get("JZMJPFM"))+"</jzmjpfm>"+
//					"<!--FLOAT 建筑面积平方米 -->"+
//					"<scgrs>"+convertObject(map.get("scgrs"))+"</scgrs>"+
//					"<!--NUMBER(10) 生产工人数 -->"+
//					"<wbjGridGuids>"+convertObject(map.get("wbjGridGuids"))+"</wbjGridGuids>"+
//					"<!--VARCHAR2(512 CHAR) 委办局网格guids  -->"+
//					"<ajjGridGuids>"+convertObject(map.get("ajjGridGuids"))+"</ajjGridGuids>"+
//					"<!--VARCHAR2(512 CHAR) 安监局网格guids  -->"+
					"<zgbmjb>"+zgbmjb+"</zgbmjb>"+
//					"<!--必填项  NUMBER(1) 直管部门级别 枚举类型，取值[1:省级，2:市级，3:(区、县级)，"+
//					"4:(乡、镇、街道)，5:(社区、村)]-->"+
				"</F_Company>"+
						
					"</body>"+

					"</table>"+
					"		]]>";
			try {
//				reportDataApp(rowId, param, optype);
				String guid = doPostSoap1_1(param, "");
				if(!StringUtils.isEmpty(guid)){
					CompanyBackUp cb = new CompanyBackUp();
					cb.setId(rowId);
					cb.setGuid(guid);
//					httpDataService.updateGuid(cb);
				}
				success ++;
				System.out.println("success:"+ convertObject(map.get("QYMC")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public static void reportDataApp(String rowId, String param, String optype) throws Exception {
		//服务的地址
        URL wsUrl = new URL("http://218.25.105.86:9080/services/ResourceService");
        
        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
        
      //  HTTPcl
        
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "text/html");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("contentType", "utf-8");
        
        OutputStream os = conn.getOutputStream();
        String a = "<![CDATA[<table><entityType>F_Company</entityType><optype>create</optype><body>	<F_Company><guid></guid>"
        		+ "<aqscfzr>afjei</aqscfzr><aqscfzryddh>18928374838</aqscfzryddh><aqscfzrgddh>024-3487384738</aqscfzrgddh>"
        		+ "<provincedm>210000000000</provincedm><provincemc>辽宁省</provincemc><citydm>210400000000</citydm><citymc>抚顺市</citymc>"
        		+ "<clrq>2015-03-30 12:00:00</clrq><frdbmc>fdjask</frdbmc><fryddh>18928374838</fryddh><hylyIds>H61</hylyIds><hylyStrs>4321</hylyStrs>"
        		+ "<qylxdh>024-3487384738</qylxdh><qymc>企业0330</qymc><qywzjd>123.926633</qywzjd><qywzwd>41.874841</qywzwd><registername></registername>"
        		+ "<registertel></registertel><scjydz>琥珀街</scjydz><yzbm>123422</yzbm><zcdz></zcdz><zzjgdm>432134</zzjgdm><aqjgszqk>0</aqjgszqk>"
        		+ "<sfxysb></sfxysb><qyrs>3</qyrs><sfsjzywh>0</sfsjzywh><yyzzlx>无</yyzzlx><jymj>3</jymj><jzmjpfm>3</jzmjpfm><zgbmjb>1</zgbmjb>"
        		+ "</F_Company></body></table>		]]>";
        
        
        a = URLEncoder.encode(a, "utf-8");
        //请求体
        String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:res=\"http://ws.itcast.cn/\" xmlns:xsd=\"http://resource.service.oas.asb.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
                      "<soapenv:Body> <res:reportDataApp><arg0>aaa</arg0>  <arg1>"+ a+"</arg1>  </res:reportDataApp> </soapenv:Body> </soapenv:Envelope>";
        
        os.write(soap.getBytes());
        InputStream is = null;
        try {
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while((len = is.read(b)) != -1){
            String ss = new String(b,0,len,"UTF-8");
            s += ss;
        }
        
        is.close();
        os.close();
        conn.disconnect();
        s= s.replace("&lt;", '<'+"");
        s= s.replace("&gt;", '>'+"");
        s= s.replace("&quot;", '"'+"");
        String guid = s.substring(s.indexOf("<guid>")+6, s.indexOf("</guid>"));
        System.out.println(s);
        System.out.println(guid);
    }
	
	public static String convertObject(Object o){
		
		if(o==null||"null".equals(o)){
			return "";
		}
		return String.valueOf(o).trim();
	}
	
	/** 
     * 使用SOAP1.1发送消息 
     *  
     * @param postUrl 
     * @param soapXml 
     * @param soapAction 
     * @return 
     */  
    public static String doPostSoap1_1(String param,  
            String soapAction) {  
    	//请求体
        String soapXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:res=\"http://ws.itcast.cn/\" xmlns:xsd=\"http://resource.service.oas.asb.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
                      "<soapenv:Body> <res:reportDataApp><arg0>210400000000</arg0> <arg1>210000000000</arg1>  <arg2>"+ param+"</arg2>  </res:reportDataApp> </soapenv:Body> </soapenv:Envelope>";
        
        String retStr = "";  
        // 创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpPost httpPost = new HttpPost(postUrl);  
                //  设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(socketTimeout)  
                .setConnectTimeout(connectTimeout).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");  
            httpPost.setHeader("SOAPAction", soapAction);  
            StringEntity data = new StringEntity(soapXml,  
                    Charset.forName("UTF-8"));  
            httpPost.setEntity(data);  
            CloseableHttpResponse response = closeableHttpClient  
                    .execute(httpPost);  
            HttpEntity httpEntity = response.getEntity();  
            if (httpEntity != null) {  
                // 打印响应内容  
                retStr = EntityUtils.toString(httpEntity, "UTF-8");  
            }  
            // 释放资源  
            closeableHttpClient.close();  
        } catch (Exception e) {  
        	e.getStackTrace();
        }  
        retStr= retStr.replace("&lt;", '<'+"");
        retStr= retStr.replace("&gt;", '>'+"");
        retStr= retStr.replace("&quot;", '"'+"");
        System.out.println(retStr);
        String guid = retStr.substring(retStr.indexOf("<guid>")+6, retStr.indexOf("</guid>"));
        System.out.println(guid);
        return guid;  
    }  

    public static void main(String[] args) throws Exception {
//    	String guid = "a6c79e7f-b76e-407f-8b48-f13ab34bced1";
//    	String result = deleteDataByGuid(guid);
//    	System.out.println(result);
//    	 String a = "<![CDATA[<table><entityType>F_Company</entityType><optype>create</optype><body>	<F_Company><guid></guid><aqscfzr>afjei</aqscfzr><aqscfzryddh>18928374838</aqscfzryddh><aqscfzrgddh>024-3487384738</aqscfzrgddh><provincedm>210000000000</provincedm><provincemc>辽宁省</provincemc><citydm>210400000000</citydm><citymc>抚顺市</citymc><clrq>2015-03-30 12:00:00</clrq><frdbmc>fdjask</frdbmc><fryddh>18928374838</fryddh><hylyIds>H61</hylyIds><hylyStrs>4321</hylyStrs><qylxdh>024-3487384738</qylxdh><qymc>企业0330</qymc><qywzjd>123.926633</qywzjd><qywzwd>41.874841</qywzwd><registername></registername><registertel></registertel><scjydz>琥珀街</scjydz><yzbm>123422</yzbm><zcdz></zcdz><zzjgdm>432134</zzjgdm><aqjgszqk>0</aqjgszqk><sfxysb></sfxysb><qyrs>3</qyrs><sfsjzywh>0</sfsjzywh><yyzzlx>无</yyzzlx><jymj>3</jymj><jzmjpfm>3</jzmjpfm><zgbmjb>1</zgbmjb></F_Company></body></table>		]]>";
//         
//         //请求体
//         String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:res=\"http://ws.itcast.cn/\" xmlns:xsd=\"http://resource.service.oas.asb.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
//                       "<soapenv:Body> <res:reportDataApp><arg0>aaa</arg0>  <arg1>"+ a+"</arg1>  </res:reportDataApp> </soapenv:Body> </soapenv:Envelope>";
//         doPostSoap1_1(soap, "");
        //服务的地址
        URL wsUrl = new URL("http://218.25.105.86:9080/services/ResourceService");
        
        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
        
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        
        OutputStream os = conn.getOutputStream();
        String a = "<![CDATA[<table><entityType>F_Company</entityType><optype>create</optype><body>	<F_Company><guid></guid><aqscfzr>afjei</aqscfzr><aqscfzryddh>18928374838</aqscfzryddh><aqscfzrgddh>024-3487384738</aqscfzrgddh><provincedm>210000000000</provincedm><provincemc>辽宁省</provincemc><citydm>210400000000</citydm><citymc>抚顺市</citymc><clrq>2015-03-30 12:00:00</clrq><frdbmc>fdjask</frdbmc><fryddh>18928374838</fryddh><hylyIds>H61</hylyIds><hylyStrs>4321</hylyStrs><qylxdh>024-3487384738</qylxdh><qymc>企业0330</qymc><qywzjd>123.926633</qywzjd><qywzwd>41.874841</qywzwd><registername></registername><registertel></registertel><scjydz>琥珀街</scjydz><yzbm>123422</yzbm><zcdz></zcdz><zzjgdm>432134</zzjgdm><aqjgszqk>0</aqjgszqk><sfxysb></sfxysb><qyrs>3</qyrs><sfsjzywh>0</sfsjzywh><yyzzlx>无</yyzzlx><jymj>3</jymj><jzmjpfm>3</jzmjpfm><zgbmjb>1</zgbmjb></F_Company></body></table>		]]>";
        
        //请求体
        String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:res=\"http://ws.itcast.cn/\" xmlns:xsd=\"http://resource.service.oas.asb.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
                      "<soapenv:Body> <res:reportDataApp><arg0>aaa</arg0> <arg1>210000000000</arg1> <arg2>"+ a+"</arg2>  </res:reportDataApp> </soapenv:Body> </soapenv:Envelope>";
        
        os.write(soap.getBytes());
        InputStream is = null;
        try {
			is = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while((len = is.read(b)) != -1){
            String ss = new String(b,0,len,"UTF-8");
            s += ss;
        }
        
        is.close();
        os.close();
        conn.disconnect();
        s= s.replace("&lt;", '<'+"");
        s= s.replace("&gt;", '>'+"");
        s= s.replace("&quot;", '"'+"");
        String guid = s.substring(s.indexOf("<guid>")+6, s.indexOf("</guid>"));
        System.out.println(s);
        System.out.println(guid);
    }
}
