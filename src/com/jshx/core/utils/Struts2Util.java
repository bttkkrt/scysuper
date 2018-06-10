package com.jshx.core.utils;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

/** 
 * Struts2工具类。
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 */
public class Struts2Util {

	//header 常量定义
	private static final String ENCODING_PREFIX = "encoding:";
	private static final String NOCACHE_PREFIX = "no-cache:";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	private static Log logger = LogFactory.getLog(Struts2Util.class);

	private Struts2Util() {
	}

	// 取得Request/Response/Session的简化函数 //

	/**
	 * 取得HttpSession的简化方法.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 *取得ServletContext实例的简化方法.
	 */
	public static ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}

	/**
	 * 取得HttpRequest的简化方法.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化方法.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}
	
	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String[] getParameters(String name) {
		return getRequest().getParameterValues(name);
	}

	// 绕过jsp/freemaker直接输出文本的函数 //

	/**
	 * 直接输出内容的简便函数.<br>
	 * <br>eg.
	 * <br>render("text/plain", "hello", "encoding:GBK");
	 * <br>render("text/plain", "hello", "no-cache:false");
	 * <br>render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.                 
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		try {
			//分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}

			HttpServletResponse response = ServletActionContext.getResponse();

			//设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render("text/plain", text, headers);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render("text/html", html, headers);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render("text/xml", xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param string json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String string, final String... headers) {
		render("application/json", string, headers);
	}
}
