package com.jshx.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
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
public class TestHTTPUtil {

	private static List<String> allTopicList = new ArrayList<String>();
	private static String url = "http://www.impk.net/Forum-135.html";
	private static int i = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//LoginImpk();
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		//httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, CHARSET_GBK : charset);
		
		
		try {
		
		


		
		
		
		
		
			LoginImpk(httpclient);
			
			while(true)
			{
			String s = HttpclientUtil.get("http://www.impk.net/Forum-135.html",null,null,httpclient);
			
			
			Document doc = Jsoup.parse(s); 
			
			
			Elements masthead = doc.select("a[href]");
			
			for (Element element : masthead) {

				String text = element.attr("href");
				if(text.contains("ShowUser")||text.contains("page-"))
				{
					continue;
				}
				
				if(!allTopicList.contains(element.text()))
				{
					i++;
					allTopicList.add(element.text());
					String herf = element.attr("href");
					String title = element.attr("title");
					
					String sout = i+":"+element.text()+" - "+title+"\r\nhttp://www.impk.net/"+herf;
					System.out.println(sout);

					
					
				}
			}
			
			/*
			List<String> patternString = HTMLUtil.getPatternString(s, "<A class=\"Topic\"\\.*</b></font></A></TD>");
			
			for (String string : patternString) {
				
				
				if(!allTopicList.contains(string))
				{
					System.out.println(string);
					allTopicList.add(string);
				}
			}
			
			
					File file = new File("d:\\impk.txt");

					FileWriter fw = new FileWriter(file);
					
					fw.append(sout);
					
					fw.close();
			
			
			*/
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		      //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			Date d = new Date();
			//DateFormat d1 = DateFormat.getDateInstance(); //默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16 20:54:53）
		    String str1 = df.format(d);

			System.out.println("\r\n★★★★★★★★-----"+str1+"-----★★★★★★★★\r\n");
			Thread.sleep(60*1000);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
