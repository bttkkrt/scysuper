package com.jshx.http.servlet;

/**
 * Copyright All rights reserved
 * @package: com.jsict.ictmap.common
 * @fileComment: 
 * @author: penghp
 * @time: 2011-6-14 上午10:50:35
 * @version: 1.0.0
 */

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;

@SuppressWarnings("serial")
public class UploadPhotoServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(UploadPhotoServlet.class);

	private PhotoPicService photoPicService = (PhotoPicService) SpringContextHolder.getBean("photoPicService");
	private static final String OBLIQUE_LINE = "/";

	@SuppressWarnings("unused")
	private static final String OPPOSITE_OBLIQUE_LINE = "\\\\";

	private static final String WEBPOSITION = "webapps";

	public static final String SBPATH = "../virtualdir/upload/";

	File outdir = null;

	File outfile = null;

	FileOutputStream fos = null;

	BufferedInputStream bis = null;

	byte[] bs = new byte[1024];

	public UploadPhotoServlet() {
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
		root = root.replaceAll("\\\\", "/");

		StringBuffer destFName = new StringBuffer();
		try {
			destFName.append(getRealDir(root)).append(SBPATH).append("photo/");
			String realSavePath = destFName.toString();
			outdir = new File(destFName.toString());

			request.setCharacterEncoding("UTF-8");

			String uploadName = request.getParameter("fileName");//获取文件名称
			String taskId = request.getParameter("linkId");//获取关联id
			String type = request.getParameter("picType");//获取图片上传类型 
			String mkType = request.getParameter("mkType");//获取图片模块上传类型 
			
			if(mkType.equals("troMan") && taskId.startsWith("troManRect"))
			{
				taskId = taskId.replace("troManRect", "");
				type = "troManRect";
			}
			
			String stream = request.getParameter("stream");//获取图片的二进制流 针对ios
			System.out.println("关联id："+taskId);
			System.out.println("类型:" + type);
			System.out.println("文件名称："+uploadName );
			
			System.out.println("stream："+stream );
			
			
			if(uploadName != null && uploadName.length() > 0 && !"null".equals(uploadName))
			{
				String uploadFName = getDatedFName(uploadName);
				destFName.append(uploadFName);
				outfile = new File(destFName.toString());
				if(stream!=null&&!"".equals(stream)){
					uploadFileIos(stream,destFName.toString());
				}else{
					bis = new BufferedInputStream(request.getInputStream());
					uploadFile();
				}
	            
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
				PhotoPic bean = new PhotoPic();
				bean.setLinkId(taskId);
				bean.setPicName(uploadFName);
				bean.setPicType(type);
				bean.setMkType(mkType);
				bean.setFileName(uploadName);//此处添加获取文件 真实名称 2013-07-27
				bean.setHttpUrl(SysPropertiesUtil.getProperty("httpurl"));
				bean.setNwUrl(SysPropertiesUtil.getProperty("nwurl"));
				bean.setFileSize(outfile.length()+"");
				bean.setDelFlag(0);
				photoPicService.save(bean);
				JSONObject j = new JSONObject();
				j.put("code","0");;
				response.getWriter().write(j.toString());
				response.setHeader("code", "0");
		    }
			else
			{
				JSONObject j = new JSONObject();
				j.put("code","1");;
				response.getWriter().write(j.toString());
				response.setHeader("code", "1");
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				log.debug(e);
			}
			e.printStackTrace();
			JSONObject j = new JSONObject();
			j.put("code","1");;
			response.getWriter().write(j.toString());
			response.setHeader("code", "1");
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e);
			}
			e.printStackTrace();
			JSONObject j = new JSONObject();
			j.put("code","2");;
			response.getWriter().write(j.toString());
			response.setHeader("code", "2");
		} finally {
			if (null != bis)
				bis.close();
			if (null != fos)
				fos.close();
		}
	}
	/**  
	 * @title           根据二进制字符串生成图片  
	 * @param data      生成图片的二进制字符串  
	 * @param fileName  图片名称(完整路径)  
	 * @param type      图片类型  
	 * @return  
	 */  
	private void uploadFileIos(String data,String fileName) {
		BufferedImage image = new BufferedImage(300, 300,BufferedImage.TYPE_BYTE_BINARY);   
	    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();   
	    try {   
	   	 BASE64Decoder decoder = new BASE64Decoder();
         byte[] b = decoder.decodeBuffer(data);
         for(int i=0;i<b.length;++i){
             if(b[i]<0){//调整异常数据
                 b[i]+=256;
             }
         }
	     ImageIO.write(image, ".jpg", byteOutputStream);   
	    // byte[] bytes = hex2byte(data); 
	     System.out.println("ios save path:" + fileName);   
	     RandomAccessFile file = new RandomAccessFile(fileName, "rw");   
	     file.write(b);   
	     file.close();   
	    } catch (IOException e) {
	        e.printStackTrace();   
	    }   
		
	}
	public static byte[] hex2byte(String s) {   
	    byte[] src = s.toLowerCase().getBytes();   
	    byte[] ret = new byte[src.length / 2];   
	    for (int i = 0; i < src.length; i += 2) {   
	        byte hi = src[i];   
	        byte low = src[i + 1];   
	        hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')   
	                : hi - '0');   
	        low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')   
	                : low - '0');   
	        ret[i / 2] = (byte) (hi << 4 | low);   
	    }   
	    return ret;   
	}   
	private String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());
		dateSfx += "_" + UUID.randomUUID().toString();

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}
		return result.toString();
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

	/**
	 * Method getRealDir search webapps position
	 * 
	 * @param despath
	 * 
	 * @return
	 * 
	 */
	private String getRealDir(String newFileNameRoot) throws Exception {
		if (newFileNameRoot == null)
			throw new Exception("get real dir failed !");
		int dp = newFileNameRoot.lastIndexOf(OBLIQUE_LINE);
		if (dp == -1)
			throw new Exception("invalid path !");
		int dpbefore = newFileNameRoot.lastIndexOf(OBLIQUE_LINE, dp - 1);
		if (dpbefore == -1)
			throw new Exception("invalid path !");
		String needSubStr = newFileNameRoot.substring(dpbefore + 1, dp);
		String nextStr = newFileNameRoot.substring(0, dpbefore + 1);
		if (!needSubStr.trim().equals(WEBPOSITION)) {
			return getRealDir(nextStr);

		} else
			return newFileNameRoot;
	}

	private void uploadFile() throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("outdir:" + outdir.getPath());
			log.debug("outfile:" + outfile.getPath());
		}
		if (!outdir.exists())
			outdir.mkdir();
		if (!outfile.exists())
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

	public static String getUrlFName(String fname, HttpServletRequest request) {
		String result = "";
		if (fname == null || fname.length() <= 0)
			return result;

		try {
			if (fname.startsWith("http://")) {
				result = fname;
			} else {
				// HttpServletRequest request =
				// ServletActionContext.getServletContext().getRgetRequest();
				// UserAndOrganAndRole user =
				// (UserAndOrganAndRole)request.getSession().getAttribute("user");

				String ip = request.getServerName();
				int port = request.getServerPort();

				result = fname.substring(fname
						.indexOf(SBPATH));
				StringBuffer tmpBuff = new StringBuffer();
				tmpBuff.append("http://").append(ip).append(":").append(port)
						.append(OBLIQUE_LINE).append(result);
				// Sample:
				// http://localhost:8083/UploadedFiles/IMAGE_067_100222102521.jpg

				result = tmpBuff.toString();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("result is: " + result);
		return result;
	}
}
