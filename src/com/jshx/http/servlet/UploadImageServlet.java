package com.jshx.http.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.PhotoBean;
import com.jshx.http.service.HttpService;

public class UploadImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private static final String OBLIQUE_LINE = "/";

	@SuppressWarnings("unused")
	private static final String OPPOSITE_OBLIQUE_LINE = "\\\\";

	private static final String WEBPOSITION = "webapps";

	public static final String SBPATH = "../virtualdir/upload/";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接受参数 
		String  remark = req.getParameter("remark");//获取备注信息
		if(remark!=null)
		remark = java.net.URLDecoder.decode(remark,"utf-8");//进行编码处理
		String taskId = req.getParameter("linkId");//获取关联id
		String type = req.getParameter("type");//获取图片上传类型 
		//图片类型有 执法文书或图片：’zftp’  整改情况：’zgqk’
		//执法文书01：‘zfws01’ ,执法文书02：‘zfws02’ ,整改方案：‘zgfa’
		// 监控措施：‘jkcs’整改前图片：‘zgqtp’整改后图片：‘zghtp’
		//获取工程根目录
		String rootPath = req.getRealPath("/WEB-INF");
		System.out.println("关联id："+taskId);
		System.out.println("存储的路径："+rootPath);
		System.out.println("类型:" + type);
		//临时存储路径
		File tempFile = new File(rootPath + "/Temp");
		if(!tempFile.exists()){
			tempFile.mkdirs();
		}
		//是否是文件上传
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		System.out.println("!!!"+isMultipart);
		if (isMultipart) {
			try {
				// 创建磁盘工厂，利用构造器实现内存数据储存量和临时储存路径
				DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4,
						tempFile);
				// 设置最多只允许在内存中存储的数据,单位:字节
				// factory.setSizeThreshold(4096);
				// 设置文件临时存储路径
				// factory.setRepository(new File("D:\\Temp"));
				// 产生一新的文件上传处理程式
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 设置路径、文件名的字符集
				upload.setHeaderEncoding("UTF-8");
				// 设置允许用户上传文件大小,单位:字节
				upload.setSizeMax(1024 * 1024 * 100);
				// 解析请求，开始读取数据
				// Iterator<FileItem> iter = (Iterator<FileItem>)
				// upload.getItemIterator(request);
				// 得到所有的表单域，它们目前都被当作FileItem
				List<FileItem> fileItems = upload.parseRequest(req);
				
				// 依次处理请求
				Iterator<FileItem> iter = fileItems.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 如果item是正常的表单域
						String name = item.getFieldName();
						String value = item.getString("UTF-8");
						System.out.println("表单域名为:" + name + "表单域值为:" + value);
					} else {
						// 如果item是文件上传表单域
						// 获得文件名及路径
						String fileName = item.getName();
						if (fileName != null) {
							// 如果文件存在则上传
							String pname = item.getName();
							if(!pname.contains(".")){
								pname = pname+".png";
							}
							File fullFile = new File(pname);
							if (!fullFile.exists()) {
								//真实路径
								String realSavePath = makeDir("photo");
								//判断是否存在，不存在，则创建
								if(realSavePath == null){
									System.out.println("文件目录创建失败！");
									return;
								}
								String fipath = realSavePath +"/"+getDatedFName(fullFile.getName());
								File fileOnServer = new File(fipath);
								item.write(fileOnServer);
								System.out.println("文件"
										+ fileOnServer.getName() + "上传成功");
								//保存图片信息
								System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
								PhotoBean bean = new PhotoBean();
								bean.setTaskId(taskId);
								bean.setRemark(remark);
								bean.setPicName(fileOnServer.getName());
								bean.setType(type);
								bean.setFileName(pname);//此处添加获取文件 真实名称 2013-07-27
								httpService.savePhotoInfo(bean);
								resp.setHeader("code", "0");
								WaterMark wm=new WaterMark();
								wm.createMark(fipath,realSavePath);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				resp.setHeader("code", "1");
			}
		}
	}
	/**
	 *  组装文件目录，并创建文件目录
	 *  
	 * @param savePath 存储路径
	 * @return String 文件目录
	 * @throws Exception 
	 */
	private String makeDir(String savePath) throws Exception{
		String root = this.getServletContext().getRealPath("/"); // 系统根目录
		root = root.replaceAll("\\\\", "/");
		StringBuffer destFName = new StringBuffer();
		destFName.append(getRealDir(root)).append(SBPATH).append(savePath);
		System.out.println("图片存储本地路径："+destFName.toString());
		File file = new File(destFName.toString());
		if(!file.exists()){
			if(file.mkdirs()){
				return destFName.toString();
			}else{
				return null;
			}
		}else{
			return destFName.toString();
		}
	}
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
	/**
	 * 组装图片名称
	 * @param fname
	 * @return
	 */
	private String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		return  result.toString();
	}


}
