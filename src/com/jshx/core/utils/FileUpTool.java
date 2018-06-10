package com.jshx.core.utils;
import java.io.*;

import org.apache.struts2.*;


/**
 * 上传文件工具类
 * 
 * @author Zhangtianming
 *
 */
public class FileUpTool {

	/** 文件大小 */
	private float filesize;
	
	/** 文件类型 */
	private String filetype;
	
	/** 保存在硬盘上的文件名 */
	private String fileurl;
	
	/** 获取文件大小 */
	public float getFilesize() {
		return filesize;
	}
	/** 设置文件大小 */
	public void setFilesize(float filesize) {
		this.filesize = filesize;
	}
	/** 获取文件类型 */
	public String getFiletype() {
		return filetype;
	}
	/** 设置文件类型 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	/** 获取保存在硬盘上的文件名 */
	public String getFileurl() {
		return fileurl;
	}
	/** 设置保存在硬盘上的文件名 */
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	/**
	 * 将单个文件保存到system.properties中配置的uploadPath目录
	 * 
	 * @param file 文件对象
	 * @param preFilename 原文件名
	 * @return fileurl 保存后文件url
	 */
	public String saveFile(File file,String preFilename){
		if(file==null)
			return "";
		int fileUpOkCounts = 0;
		this.filetype=preFilename.substring(preFilename.lastIndexOf("."));
		this.fileurl=DateUtil.getDateToFilename()+this.filetype;
		
		try {
			InputStream is = new FileInputStream(file);
			String uploadPath = SysPropertiesUtil.getProperty("uploadPath");
			if (uploadPath == null) {
				return "请在system.properties文件中配置上传文件的路径!";
			}
			String root = ServletActionContext.getRequest().getRealPath(uploadPath);
			File destDir = new File(root);
			if(!destDir.exists()){
				destDir.mkdir();
			}
			File destFile = new File(root, this.fileurl);
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[4000];//缓存大小
			int length = 0;
			this.filesize=0;
			while ((length = is.read(buffer)) > 0) {
				// 写入文件
				os.write(buffer, 0, length);
				this.filesize += length;
			}
			this.filesize = this.filesize / 1024;// 转换成KB
			
			is.close();
			os.close();
			fileUpOkCounts=1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return this.fileurl;
	}
	
	/**
	 * 将单个文件保存到指定的目录
	 * 
	 * @param file 文件对象
	 * @param preFilename 原文件名
	 * @param catalog 指定的目录
	 * @return fileUpOkCounts，0表示上传失败，1表示上传成功
	 */
	public int saveFile(File file,String preFilename,String catalog){
		if(file==null)
			return 0;
		
		int fileUpOkCounts = 0;
		this.filetype=preFilename.substring(preFilename.lastIndexOf("."));
		this.fileurl=DateUtil.getDateToFilename()+this.filetype;
		
		try {
			InputStream is = new FileInputStream(file);
			String root = ServletActionContext.getRequest().getRealPath(catalog);
			
			File destFile = new File(root, this.fileurl);
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[4000];//缓存大小
			int length = 0;
			this.filesize=0;
			while ((length = is.read(buffer)) > 0) {
				// 写入文件
				os.write(buffer, 0, length);
				this.filesize += length;
			}
			this.filesize = this.filesize / 1024;// 转换成KB
			
			is.close();
			os.close();
			fileUpOkCounts=1;
		} catch (Exception ex) {

		}
		return fileUpOkCounts;
	}
	
	/**
	 * 删除system.properties中配置的uploadPath目录下的文件
	 * 
	 * @param filename
	 * @return 1表示删除成功，0表示删除失败
	 */
	public int deleteFile(String filename){
		int fileDeletedCounts=0;
		String uploadPath = SysPropertiesUtil.getProperty("uploadPath");
		this.fileurl= ServletActionContext.getRequest().getRealPath(uploadPath);
		try{
			File file=new File(this.fileurl,filename);
			file.delete();
			fileDeletedCounts=1;
		}catch(Exception e){}
		return fileDeletedCounts;
		
	}
}
