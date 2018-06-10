package com.jshx.fastdfs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.jshx.core.utils.Struts2Util;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.photoPic.entity.PhotoPic;
import com.opensymphony.xwork2.util.logging.LoggerUtils;


/**
 * File Manager used to provide the services to upload / download / delete the files
 * from FastDFS.
 * 
 * <note>In this version, FileManager only support single tracker, will enhance this later...</note>
 * 
 */
public class FileManager implements FileManagerConfig {
  
  private static final long serialVersionUID = 1L;

  private static Logger logger  = Logger.getLogger(FileManager.class);
  
  private static TrackerClient  trackerClient;
  private static TrackerServer  trackerServer;
  private static StorageServer  storageServer;
  private static StorageClient  storageClient;
  private static StorageClient1  storageClient1;
  //文件服务器的请求头
  public static String httpStart = "";


  static { // Initialize Fast DFS Client configurations
    
    try {
      String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
      
      String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
      
      logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
      ClientGlobal.init(fdfsClientConfigFilePath);
      
      trackerClient = new TrackerClient();
      trackerServer = trackerClient.getConnection();
      
      storageClient = new StorageClient(trackerServer, storageServer);
      storageClient1 = new StorageClient1(trackerServer, storageServer);
      
      httpStart = PROTOCOL + TRACKER_IP 
  	        + COLON
  	        + TRACKER_NGNIX_PORT
  	        + SEPARATOR ;
      
      /*httpStart = PROTOCOL + trackerServer.getInetSocketAddress().getHostName() 
    	        + COLON
    	        + TRACKER_NGNIX_PORT
    	        + SEPARATOR ;*/
//      httpStart = SEPARATOR;
      
    } catch (Exception e) {
      
    }
  }
  
  /**
   * 文件上传
   * @param filePath
   * @return
   * @throws Exception
   */
  public static String uploadFile(String filePath) throws Exception{        
	    String fileId = ""; 
	    String fileExtName = ""; 
	    if (filePath.contains(".")) { 
	        fileExtName = filePath.substring(filePath.lastIndexOf(".") + 1); 
	    } else { 
	        logger.warn("Fail to upload file, because the format of filename is illegal."); 
	        return fileId; 
	    } 

	    //上传文件 
	    try { 
	        fileId = storageClient1.upload_file1(filePath, fileExtName, null); 
	    } catch (Exception e) { 
	        logger.warn("Upload file \"" + filePath + "\"fails"); 
	    }finally{
	        trackerServer.close();
	    }        
	    return fileId; 
	}
  
  /**
   * 文件下载
   * @param file
   * @return
   */
  public static String upload(FastDFSFile file) {
    System.out.println("File Name: " + file.getName() + "		File Length: " + file.getContent().length);
    NameValuePair[] meta_list = new NameValuePair[3];
      meta_list[0] = new NameValuePair("width", "120");
      meta_list[1] = new NameValuePair("heigth", "120");
      meta_list[2] = new NameValuePair("author", "Diandi");
    
      long startTime = System.currentTimeMillis();
    String[] uploadResults = null;
    try {
      uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
    } catch (IOException e) {
      logger.error("IO Exception when uploadind the file: " + file.getName(), e);
    } catch (Exception e) {
      logger.error("Non IO Exception when uploadind the file: " + file.getName(), e);
    }
    logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
    
    if (uploadResults == null) {
    	System.out.println("upload file fail, error code: " + storageClient.getErrorCode());
    }
    
    String groupName 		= uploadResults[0];
    String remoteFileName   = uploadResults[1];
    
//    String fileAbsolutePath = PROTOCOL + trackerServer.getInetSocketAddress().getHostName() 
//        + SEPARATOR
//        + TRACKER_NGNIX_PORT
//        + SEPARATOR 
//        + groupName 
//        + SEPARATOR 
//        + remoteFileName;
    String fileAbsolutePath = FileManager.httpStart + groupName + SEPARATOR + remoteFileName;
    
    System.out.println("upload file successfully!!!  " +"group_name: " + groupName + ", remoteFileName:"
        + " " + remoteFileName);
    
    return fileAbsolutePath;
    
  }
  
  /**
   * 文件下载
   * @param fileId
   * @return
   * @throws Exception
   */
  public static byte[] download(String fileId) throws Exception{   
	  byte[] buf = new byte[1024];

	    //下载文件 
	    try { 
	    	buf = storageClient1.download_file1(fileId); 
	    } catch (Exception e) { 
	        logger.warn("Download file \"" + fileId + "\"fails"); 
	    }finally{
	        trackerServer.close();
	    }

	    return buf;
	}
  
  /**
   * 通过浏览器下载文件
   * @param photoPic
   * @param request
   * @param tomcatFilePath
   */
  public static void downByBrowse(PhotoPic photoPic, HttpServletRequest request, String tomcatFilePath){
	    //通过浏览器下载
	    OutputStream out = null;
	    InputStream in = null;
	  	try {
	  	//获取文件服务器上的文件
//			OutputStream out = Struts2Util.getResponse().getOutputStream();
			if(photoPic.getPicName().startsWith("/group")){
				String fileId = photoPic.getPicName().substring(1, photoPic.getPicName().lastIndexOf("?"));
				byte[] fileBuf = FileManager.download(fileId);
				if(null != fileBuf){
					in = new ByteArrayInputStream(fileBuf);
				}
			}else{
				//获取tomcat中上传的文件
				String path = Struts2Util.getServletContext().getRealPath("/");
				File fis = new File(path + tomcatFilePath + photoPic.getPicName());
				if (fis.exists()) {
					in = new FileInputStream(fis);
				}
			}
	  		out = Struts2Util.getResponse().getOutputStream();
			String browName = new String();
			browName = URLEncoder.encode(photoPic.getFileName(), "UTF-8");
			String clientInfo = request.getHeader("User-agent");
			if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
				if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
					browName = new String(photoPic.getFileName().getBytes("GBK"), "ISO-8859-1");
			}
			Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + browName);
				
			int len;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1)
			{
				out.write(buf, 0, len);
			}
			out.flush(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
  }
  
  /**
   * 附件下载
   * @param contentAttach
   * @param request
   * @param tomcatFilePath
   */
  public static void downAttachByBrowse(ContentAttachs contentAttach,
			HttpServletRequest request, String tomcatFilePath) {
	  //通过浏览器下载
	    OutputStream out = null;
	    InputStream in = null;
	  	try {
	  	//获取文件服务器上的文件
//			OutputStream out = Struts2Util.getResponse().getOutputStream();
			if(contentAttach.getDocUrl().startsWith("/group")){
				String fileId = contentAttach.getDocUrl().substring(1, contentAttach.getDocUrl().lastIndexOf("?"));
				byte[] fileBuf = FileManager.download(fileId);
				if(null != fileBuf){
					in = new ByteArrayInputStream(fileBuf);
				}
			}else{
				//获取tomcat中上传的文件
				String path = Struts2Util.getServletContext().getRealPath("/");
				File fis = new File(path + tomcatFilePath + contentAttach.getAttachName());
				if (fis.exists()) {
					in = new FileInputStream(fis);
				}
			}
	  		out = Struts2Util.getResponse().getOutputStream();
			String browName = new String();
			browName = URLEncoder.encode(contentAttach.getDocName(), "UTF-8");
			String clientInfo = request.getHeader("User-agent");
			if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
				if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
					browName = new String(contentAttach.getDocName().getBytes("GBK"), "ISO-8859-1");
			}
			Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + browName);
				
			int len;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf)) != -1)
			{
				out.write(buf, 0, len);
			}
			out.flush(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
  
  /**
   * 获得文件信息
   * @param groupName
   * @param remoteFileName
   * @return
   */
  public static FileInfo getFile(String groupName, String remoteFileName) {
    try {
      return storageClient.get_file_info(groupName, remoteFileName);
    } catch (IOException e) {
      logger.error("IO Exception: Get File from Fast DFS failed", e);
    } catch (Exception e) {
      logger.error("Non IO Exception: Get File from Fast DFS failed", e);
    }
    return null;
  }
  
  public static void deleteFile(String groupName, String remoteFileName) throws Exception {
    storageClient.delete_file(groupName, remoteFileName);
  }
  
  public static StorageServer[] getStoreStorages(String groupName) throws IOException {
    return trackerClient.getStoreStorages(trackerServer, groupName);
  }
  
  public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
    return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
  }
  
  public static void main(String[] args) {
	  String fileStr = "C:\\520.jpg";
//	  File content = new File(fileStr);
	  try {
		  String fileId = uploadFile(fileStr);
		  System.out.println(fileId);
//          download(fileId, "C:\\0.jpg");
	} catch (Exception e) {
		e.printStackTrace();
	}
  }


}
