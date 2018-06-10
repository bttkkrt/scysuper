package com.jshx.http.servlet;

/**
 * Copyright All rights reserved
 * @package: com.jsict.ictmap.common
 * @fileComment: 
 * @author: penghp
 * @time: 2011-6-14 上午10:50:35
 * @version: 1.0.0
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	
	private static Logger log = Logger.getLogger(UploadServlet.class);
	
	File outdir = null;
	
	File outfile = null;

	FileOutputStream fos = null;

	BufferedInputStream bis = null;
	
	byte[] bs = new byte[1024];
	
	private static final String OBLIQUE_LINE = "/";

	@SuppressWarnings("unused")
	private static final String OPPOSITE_OBLIQUE_LINE = "\\\\";
	
	private static final String WEBPOSITION = "webapps";
	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (log.isDebugEnabled()) {
			log.debug("进入doPost()方法！！！");
		}
		String root = this.getServletContext().getRealPath("/"); // 系统根目录
		root = root.substring(0,root.indexOf("webapps")+8);
		root = root.replaceAll("\\\\", "/");
		StringBuffer destFName = new StringBuffer();
		try {
			request.setCharacterEncoding("UTF-8");
			String filePath = request.getParameter("filePath");
			root = root.replace("webapps",filePath);
			destFName.append(root);
			outdir = new File(destFName.toString());
			String fileName = request.getParameter("fileName");
			destFName.append(fileName);
			outfile = new File(destFName.toString());
			bis = new BufferedInputStream(request.getInputStream());
			uploadFile();
			
			PrintWriter out = response.getWriter();
			out.write(outfile.length()+"");
			out.flush();
		    out.close();
		    
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e);
			}
			e.printStackTrace();
			response.setHeader("code", "2");
		} finally {
			if (null != bis)
				bis.close();
			if (null != fos)
				fos.close();
		}

		response.setHeader("code", "0");
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		if (log.isDebugEnabled()) {
			log.debug("进入init()方法！！！");
		}
	}


	private void uploadFile() throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("outdir:" + outdir.getPath());
			log.debug("outfile:" + outfile.getPath());
		}
		if (!outdir.exists())
		{
			outdir.mkdirs();
		}
		if (outfile.exists())
		{
			outfile.delete();
		}
		outfile.createNewFile();
		fos = new FileOutputStream(outfile);
		int i;
		while ((i = bis.read(bs)) != -1) {
			fos.write(bs, 0, i);
		}
		if (log.isDebugEnabled()) {
			log.debug("uploadFile:uploadFile() ends");
		}
	}

}
