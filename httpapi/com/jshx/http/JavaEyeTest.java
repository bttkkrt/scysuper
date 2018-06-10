package com.jshx.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jshx.core.utils.DateUtil;
public class JavaEyeTest {

	private static List<String> allTopicList = new ArrayList<String>();
	private static String url = "http://www.impk.net/Forum-135.html";
	private static int i = 0;
	/**
	 * @param args
	 * @throws NetServiceException 
	 */
	public static void main(String[] args) throws NetServiceException {
		
		//创建一个httpclient实例
		DefaultHttpClient httpclient = HttpclientUtil.getDefaultHttpClient(null);
		
		//访问  http://www.iteye.com/login 获得验证用的Token
		String s = HttpclientUtil.get("http://www.iteye.com/login",null,null,httpclient);
		
		//用正则表达式分析出 页面中的 token
		List<String> patternString = HTMLUtil.getPatternString(s, "authenticity_token.*</div>");
		
		//分离出token的值
		String ps = patternString.get(0);
		String ps2 = ps.substring(41,ps.length()-10);
		
		
		//构建参数
		Map<String, String> params = new 	HashMap<String, String>();
		
		params.put("authenticity_token", ps2);
		params.put("name", "jshx153");
		params.put("password", "123456qwe");
		params.put("remember_me", "1");
		
		//登录
		String s2 = HttpclientUtil.post("http://www.iteye.com/login", params,null,httpclient);
		
		//抓取blog首页
		s2 = HttpclientUtil.get("http://www.iteye.com/blogs", null,null,httpclient);
		
		//使用Jsoup解析该页面
		Document doc = Jsoup.parse(s2);
		//提取所有的 class = content的div
		Elements masthead = doc.select("div.content");
		for (Element element : masthead) {
			
			//每个这样的div里面第二个 a 标签即为 blog文章
			Element first = element.select("a[href]").get(1);
			
			//读取超链接的 文字
			System.out.println(first.text());
			//读取超链接的 href 属性
			System.out.println(first.attr("href"));
			
		}
		
		
	}
	
	private static void LoginImpk(DefaultHttpClient httpclient)
	{
		try {
			
			
		String s = HttpclientUtil.get("http://www.impk.net/login");
		
		List<String> patternString = HTMLUtil.getPatternString(s, "http://www.impk.net/SafeLogin-\\d* met");
		String ps = patternString.get(0);
		
		System.out.println(ps);
		
		Map<String, String> params = new 	HashMap<String, String>();
		params.put("name", "tiztm");
		Scanner input = new Scanner(System.in);
		String Capt = input.next();
		params.put("id",Capt);
		
		String pString = ps.substring(0,ps.length()-4);
		String s2 = HttpclientUtil.post(pString, params);
		

		patternString = HTMLUtil.getPatternString(s2, "Login-\\d* method=post");
		ps = patternString.get(0);
		System.out.println(ps);
		ps = ps.substring(0,ps.length()-12);
		params = new 	HashMap<String, String>();
		params.put("name", "tiztm");
		params.put("passwd", "6516332ztm");
		params.put("expire", "1");
		
		
		Scanner input1 = new Scanner(System.in);
		String Capt1 = input.next();
		params.put("id",Capt1);
		
		
		String s3 = HttpclientUtil.post("http://www.impk.net/"+ps, params,null,httpclient);
		
		
		} catch (NetServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
