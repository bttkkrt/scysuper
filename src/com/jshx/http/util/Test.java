package com.jshx.http.util;
//需要引入的组件包
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
public class Test {
	public static void main(String[] args) {
		// 实例化验证类对象
//		Authenticator au = new Authenticator();
//		try {
//			/**
//			 * Identity id为验证通过后返回的用户实例对象，其中包含用户相关信息。
//			 * 如果验证失败的话则会抛出异常,通过异常对象能得到验证失败的原因
//			 */
//
//			Identity id = au.authenticate("sipac", "szf", "12345@abcde");
//
//			 User user = id.getUser();//获取用户信息对象
//			 user.getGuid();//获取唯一编号
//			 user.getProperty("sn");//获取属性名称为sn的属性值
//			 
//			 System.out.print(user.getGuid());
//						
//			 Department department = id.getDepartment();//获取用户直属部门对象
//			 department.getParentDept();//获得该部门的父部门，如果不存在则返回null
//			 department.getDeptName();//获得该不明名称
//			 department.getGuid();//获得该部门唯一编码
//			 department.getProperty("sn");//获得该部门属性名称为sn的属性的值
//						
//			 //获得该用户的别名对象所在部门的集合，如果不存在别名对象，则返回null或zise=0
//			 id.getAliasDpartmentsList();
//
//		} catch (AuthenticateException e) {
//			e.getMessage();// 获取异常消息
//			System.out.println(e.getErrorCode());
//		}
//		
		 try {
			URL restURL = new URL("http://58.210.9.131/scms/sipsd/service/DBService/208f36d6-d19f-4b82-a5ac-2acdba315e44?token=M9YXgn9o7FOvu9uiGzJufUm6rIGlnRfF%2fFn03xGFbd7f5cQXbATfbFfk5xXAe%2f%2f5e2gZ6iFJGP3aQ8y15Ev4xBdL7FIT3zmZnHo0o3lQtUHNK4a%2bOIaB3Q%3d%3d&ACTIONTYPE=2&KEY=293b7bfe-a2ec-4efc-9f2a-e20f5f5ef208&MODNAME=&COMNAE=&ADDRESS=&REGION=&SHAPE=&f=list"); 
			 HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
			 conn.setRequestMethod("GET"); 
			 conn.setDoOutput(true); 
			 conn.setAllowUserInteraction(false); 
			    BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8")); 
			    String line,resultStr=""; 
			    while(null != (line=bReader.readLine())) 
			    { 
			        resultStr +=line; 
			    } 
			    bReader.close(); 
			    System.out.println(resultStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		String jsonStr = "<?xml version='1.0' encoding='UTF-8'?>"
//			+ "<ns0:dataQuery1Table xmlns:ns0='http://www.apusic.com/esb/DataQuery/xsd' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"
//			+ "<ns0:组织机构代码>737064826</ns0:组织机构代码>"
//			+ "<ns0:企业名称>苏州武藏涂料有限公司</ns0:企业名称>"
//			+ "<ns0:企业英文名称> </ns0:企业英文名称>"
//			+ "<ns0:法定代表人>MASAYUKI YANAGIDA</ns0:法定代表人>"
//			+ "<ns0:企业注册号>320594400003939</ns0:企业注册号>"
//			+ "<ns0:注册地址>苏州工业园区港田路150号</ns0:注册地址>"
//			+ "<ns0:工商注册日期>2002-04-09T00:00:00.0+08:00</ns0:工商注册日期>"
//			+ "<ns0:行业类型代码>C2641</ns0:行业类型代码>"
//			+ "<ns0:行业类型>涂料制造</ns0:行业类型>"
//			+ "<ns0:注册类型代码>330</ns0:注册类型代码>"
//			+ "<ns0:注册类型>外资企业</ns0:注册类型>"
//			+ "<ns0:企业类型编码>5</ns0:企业类型编码>"
//			+ "<ns0:企业类型>外资企业</ns0:企业类型>"
//			+ "<ns0:注册资本>1300000</ns0:注册资本>"
//			+ "<ns0:货币种类代码>840</ns0:货币种类代码>"
//			+ "<ns0:货币种类>美元</ns0:货币种类>"
//			+ "<ns0:经营范围>许可经营项目：生产丙烯酸清漆、丙烯酸清烘漆、丙烯酸漆稀释剂***；批发危险化学品；第3类第3项高闪点液体：铝粉浆***（不得储存）。一般经营项目：生产非危险化学品类混配涂料及稀释剂；销售本公司所生产的产品，提供相关售后服务；从事本公司生产产品的同类商品的批发、进出口、佣金代理及相关业务（危险品除外）。</ns0:经营范围>"
//			+ "<ns0:行政区划代码>1</ns0:行政区划代码>"
//			+ "<ns0:行政区划>中新合作区</ns0:行政区划>"
//			+ "<ns0:企业状态代码>35</ns0:企业状态代码>"
//			+ "<ns0:企业状态>正常</ns0:企业状态>"
//			+ "<ns0:是否园区工商注册> </ns0:是否园区工商注册>"
//			+ "<ns0:投资国家> </ns0:投资国家>"
//			+ "<ns0:实际投资国别> </ns0:实际投资国别>"
//			+ "<ns0:联系电话>051262560350</ns0:联系电话>"
//			+ "<ns0:纳税人标识号>321700737064826</ns0:纳税人标识号>"
//			+ "</ns0:dataQuery1Table>";
//			if(jsonStr != null && !"".equals(jsonStr))
//			{
//	            try {
//	            	StringReader read = new StringReader(jsonStr);
//			        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
//			        InputSource source = new InputSource(read);
//			        //创建一个新的SAXBuilder
//			        SAXBuilder sb = new SAXBuilder();
//			        //通过输入源构造一个Document
//			        Document doc = sb.build(source);
//		            
//					XPath xpath1 =XPath.newInstance("/ns0:dataQuery1Table/ns0:企业名称");
//				    xpath1.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e1 = (Element)xpath1.selectSingleNode(doc);
//				    System.out.println(e1.getText());//企业名称 
//					XPath xpath2 =XPath.newInstance("/ns0:dataQuery1Table/ns0:企业注册号");
//				    xpath2.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e2 = (Element)xpath2.selectSingleNode(doc);
//				    System.out.println(e2.getText());//工商注册号
//					XPath xpath3 =XPath.newInstance("/ns0:dataQuery1Table/ns0:注册地址");
//				    xpath3.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e3 = (Element)xpath3.selectSingleNode(doc);
//				    System.out.println(e3.getText());//地址
//					XPath xpath4 =XPath.newInstance("/ns0:dataQuery1Table/ns0:行政区划代码");
//				    xpath4.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e4 = (Element)xpath4.selectSingleNode(doc);
//				    System.out.println(e4.getText());//行政区划代码
//					XPath xpath5 =XPath.newInstance("/ns0:dataQuery1Table/ns0:企业类型编码");
//				    xpath5.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e5 = (Element)xpath5.selectSingleNode(doc);
//				    System.out.println(e5.getText());//企业性质
//					XPath xpath6 =XPath.newInstance("/ns0:dataQuery1Table/ns0:行业类型代码");
//				    xpath6.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e6 = (Element)xpath6.selectSingleNode(doc);
//				    System.out.println(e6.getText());//行业类别
//					XPath xpath7 =XPath.newInstance("/ns0:dataQuery1Table/ns0:法定代表人");
//				    xpath7.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e7 = (Element)xpath7.selectSingleNode(doc);
//				    System.out.println(e7.getText());//法人姓名
//					XPath xpath8 =XPath.newInstance("/ns0:dataQuery1Table/ns0:注册资本");
//				    xpath8.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e8 = (Element)xpath8.selectSingleNode(doc);
//				    System.out.println(e8.getText());//注册资本
//					XPath xpath9 =XPath.newInstance("/ns0:dataQuery1Table/ns0:经营范围");
//				    xpath9.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
//				    Element e9 = (Element)xpath9.selectSingleNode(doc);
//				    System.out.println(e9.getText());//注册资本
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		
//		String add = "/jsp/ggl/publicBoardList.action?type=1";
//		String ss = add;
//		ss = ss.substring(ss.lastIndexOf("/")+1,ss.lastIndexOf("List.action"));
//		System.out.println(ss);
//		add += "?"+ss + ".companyName="+"有姐姐";
//		System.out.println(add);
//		String xmlDoc = "<?xml version='1.0' encoding='GB2312'?><Response><ResultMsg>001</ResultMsg><RequestIdentifier>897f656b-43d9-4a97-9347-299389b0ff81</RequestIdentifier></Response>";
//		
//		//创建一个新的字符串
//        StringReader read = new StringReader(xmlDoc);
//        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
//        InputSource source = new InputSource(read);
//        //创建一个新的SAXBuilder
//        SAXBuilder sb = new SAXBuilder();
//        try {
//            //通过输入源构造一个Document
//            Document doc = sb.build(source);
//            //取的根元素
//            Element root = doc.getRootElement();
//            //得到根元素所有子元素的集合
//            List jiedian = root.getChildren();
//            //获得XML中的命名空间（XML中未定义可不写）
//            Element et = (Element)jiedian.get(0);
//               
//            System.out.println(et.getText());
//           
//        } catch (JDOMException e) {
//            // TODO 自动生成 catch 块
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO 自动生成 catch 块
//            e.printStackTrace();
//        }

		
	}
}
