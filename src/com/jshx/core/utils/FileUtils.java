package com.jshx.core.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
/**
 * 文件工具类
 * 
 * @author Chenjian
 *
 */
public class FileUtils {
	
	/**
	 * 获得指定路径下的指定扩展名的文件
	 * 
	 * @param imgPath
	 * @param extensions
	 * @return
	 */
	public static List<String> getImg(String imgPath, String...extensions){
		List<String> imgFiles = new ArrayList<String>();
		File file = new File(imgPath);                                                                                                                                                
		if(file.isDirectory()){
			String[] fileNames = file.list();
			for(String fileName:fileNames){
				String extension = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
				if(extensions!=null){
					for(String str:extensions){
						if(str.toUpperCase().equals(extension.toUpperCase())){
							imgFiles.add(fileName);
							break;
						}
					}
				}				
			}
		}
		return imgFiles;
	}

	/**
     * 多级目录创建
     * @param folderPath 准备要在本级目录下创建新目录的目录路径 例如 c:myf
     * @param paths 无限级目录参数，各级目录以单数线区分 例如 a/b/c 或 /a/b/c
     * @return 返回创建文件后的路径 例如 c:myf/a/b/c
     */
	public static String createFolders(String folderPath, String paths){
        String txts = folderPath;
        try{
            String txt;
            txts = folderPath;
            StringTokenizer st = new StringTokenizer(paths,"/");
            for(int i=0; st.hasMoreTokens(); i++){
                    txt = st.nextToken().trim();
                    if(txts.endsWith("/")){ 
                        txts = createFolder(txts+txt);
                    }else{
                        txts = createFolder(txts+"/"+txt);    
                    }
            }
       }catch(Exception e){
       }
        return txts;
    }
	
	/**
	 * 将输入流做为文件保存在指定的路径下
	 * 
	 * @param path
	 * @param in
	 * @throws Exception
	 */
	public static void saveFile(String path,InputStream in)throws Exception
	{
		byte[] bytes = new byte[1024];
		File file = new File(path);
		if(!file.getParentFile().exists())
		{
			file.getParentFile().mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(file);
		while(true)
		{
			int index = in.read(bytes);
			if(index==-1)break;
			fos.write(bytes, 0, index);
		}
		fos.close();
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param folderPath
	 * @return
	 */
	 public static String createFolder(String folderPath) {
	        String txt = folderPath;
	        try {
	            java.io.File myFilePath = new java.io.File(txt);
	            txt = folderPath;
	            if (!myFilePath.exists()) {
	                myFilePath.mkdir();
	            }
	        }
	        catch (Exception e) {
	        }
	        return txt;
	 }
	 
	 /**
	  * 获取目录的大小
	  * 
	  * @param dir
	  * @return
	  */
	 public static long getDirSize(File dir) {   
		    if (dir == null) {   
		        return 0;   
		    }   
		    if (!dir.isDirectory()) {   
		        return 0;   
		    }   
		    long dirSize = 0;   
		    File[] files = dir.listFiles();   
		    for (File file : files) {   
		        if (file.isFile()) {   
		            dirSize += file.length();   
		        } else if (file.isDirectory()) {   
		            dirSize += file.length();   
		            dirSize += getDirSize(file); // 如果遇到目录则通过递归调用继续统计   
		        }   
		    }   
		    return dirSize;   
		}  
	 
	 
	 /**
	  * 获取目录的大小，返回以Mb为单位的
	  * 
	  * @param dir
	  * @return
	  */
	 public static String getDirSizeMb(File dir) {   
		    double size = 0;   
		    long dirSize =  getDirSize(dir); 
		    size = (dirSize + 0.0) / (1024 * 1024);   
		    DecimalFormat df = new DecimalFormat("0.00");// 以Mb为单位保留两位小数   
		    String filesize = df.format(size);   
		    return filesize;   
		} 
}
