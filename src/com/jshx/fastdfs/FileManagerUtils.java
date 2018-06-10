package com.jshx.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageServer;
import org.springframework.util.Assert;

import com.jshx.fastdfs.FastDFSFile;
import com.jshx.fastdfs.FileManager;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.photoPic.entity.PhotoPic;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class FileManagerUtils {
  //tomcat图片文件存储地址
  private static String picStorage = "/upload/photo/";
  //tomcat附件文件存储地址
  private static String attachStorage = "/upload/attach/";
  private static String fdfsStorage = "";
  private static Properties p = new Properties();    
  static{
	    InputStream inputStream = FileManagerUtils.class.getClassLoader().getResourceAsStream("ipConfig.properties");    
		try {    
			p.load(inputStream);    
			fdfsStorage = "http://"+p.getProperty("ip")+":"+p.getProperty("port");
		} catch (IOException e1) {   
			e1.printStackTrace();    
		} 
  }

  public static String upload(File content) throws Exception {
	  if(null == content){
		  return "";
	  }
	  int index = content.getName().lastIndexOf(".");
	  if(index == -1){
		  return "";
	  }
	  //获得文件类型
	  String postfix = content.getName().substring(index+1);
	  //获得文件名
	  String prefix = content.getName().substring(0, index);
	  FileInputStream fis = new FileInputStream(content);
      byte[] file_buff = null;
      if (fis != null) {
      	int len = fis.available();
      	file_buff = new byte[len];
      	fis.read(file_buff);
      }
    
      FastDFSFile file = new FastDFSFile(prefix, file_buff, postfix);
    
      String fileAbsolutePath = FileManager.upload(file);
      System.out.println("fileAbsolutePath:"+fileAbsolutePath);
      fis.close();
      return fileAbsolutePath;
  }
  
 /* public static void getFile() throws Exception {
    FileInfo file = FileManager.getFile("group1", "M00/00/00/ymZlXVdVQESAPQk9AAEb8bm15vc365.jpg");
    Assert.notNull(file);
    String sourceIpAddr = file.getSourceIpAddr();
      long size = file.getFileSize();
      System.out.println("ip:" + sourceIpAddr + ",size:" + size);
  }
  
  public void getStorageServer() throws Exception {
    StorageServer[] ss = FileManager.getStoreStorages("group1");
    Assert.notNull(ss);
    
    for (int k = 0; k < ss.length; k++){
      System.err.println(k + 1 + ". " + ss[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + ss[k].getInetSocketAddress().getPort());
      }
  }
  
  public void getFetchStorages() throws Exception {
    ServerInfo[] servers = FileManager.getFetchStorages("group1", "M00/00/00/ymZlXVdVQESAPQk9AAEb8bm15vc365.jpg");
    Assert.notNull(servers);
    
    for (int k = 0; k < servers.length; k++) {
    		System.err.println(k + 1 + ". " + servers[k].getIpAddr() + ":" + servers[k].getPort());
    	}
  }*/
  
  /**
	 * 根据文件名修改文件路径
	 * @param picList
	 * @return
	 */
	public static List<PhotoPic> returnListByPicname(List<PhotoPic> picList){
		if(null != picList){
			for (PhotoPic photoPic : picList) {
				if(!photoPic.getPicName().startsWith("/group")){
					photoPic.setPicName(picStorage + photoPic.getPicName());
				}
			}
		}
		return picList;
	}
	
	/**
	 * 根据附件名修改文件路径
	 * @param attList
	 * @return
	 */
	public static List<ContentAttachs> returnListByAttname(List<ContentAttachs> attList){
		if(null != attList){
			for (ContentAttachs contentAttachs : attList) {
				if(!contentAttachs.getDocUrl().startsWith("/group")){
					contentAttachs.setDocUrl(attachStorage + contentAttachs.getDocUrl());
				}
			}
		}
		return attList;
	}
  
  
  public static void main(String[] args) {
//	  String remotePath = "http://10.1.1.1/8090/M00/00/00/ymZlXVdVQESAPQk9AAEb8bm15vc365.jpg";
//	  int index = remotePath.indexOf("8090");
//	  char temp = remotePath.charAt(index-1);
//	  remotePath = remotePath.replaceFirst(String.valueOf(temp), ":");
//	  System.out.println(remotePath);
	  String fileId ="M00/00/00/ymZlXVdVQESAPQk9AAEb8bm15vc365.jpg";
	  String localFile = "";
	  try {
		System.out.println(FileManager.download(fileId));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	  File content = new File("C:\\520.jpg");
//	  try {
//		System.out.println(upload(content));
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
}
  
}
