package com.jshx.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

public class FileUtils {
	public static String fileRealName = "";// 保存上传附件的真实名称

	public static String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	public static String uploadFile(MultiPartRequestWrapper request,
			Enumeration enu, String path, String title) {
		String filename = "";
		try {

			String controlName = (String) enu.nextElement();
			String[] fileNames = request.getFileNames(controlName);
			fileRealName = fileNames[0];
			File[] uploadFiles = request.getFiles(controlName);
			for (int i = 0; i < uploadFiles.length; i++) {
				// 如果文件夹不存在，创建文件夹,将文件保存到目录
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File uploadFile = uploadFiles[i];
				if (!uploadFile.exists())
					continue;

				if (!dir.exists()) {
					dir.mkdirs();
				}
				String ext = fileNames[i].substring(fileNames[i].indexOf("."),
						fileNames[i].length());// 获取文件扩展名
				filename += title + ext;
				File file = new File(path + filename);
				byte[] data = new byte[10 * 1024];
				int byteRead = -1;
				FileInputStream in = new FileInputStream(uploadFile);
				FileOutputStream out = new FileOutputStream(file);
				while ((byteRead = in.read(data)) != -1) {
					out.write(data, 0, byteRead);
					out.flush();
				}
				out.close();
				in.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}

	public static String uploadFile(MultiPartRequestWrapper request,
			Enumeration enu, String path) {
		String filename = "";
		fileRealName = "";
		try {
			String controlName = (String) enu.nextElement();
			String[] fileNames = request.getFileNames(controlName);
			File[] uploadFiles = request.getFiles(controlName);
			for (int i = 0; i < uploadFiles.length; i++) {
				// 如果文件夹不存在，创建文件夹,将文件保存到目录
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File uploadFile = uploadFiles[i];
				if (!uploadFile.exists())
					continue;

				if (!dir.exists()) {
					dir.mkdirs();
				}
				String ext = fileNames[i].substring(fileNames[i].indexOf("."),
						fileNames[i].length());// 获取文件扩展名
				String str = UUID.randomUUID().toString().replaceAll("-", "")
						+ ext;
				filename += str + ";";
				fileRealName += fileNames[i] + ";";
				File file = new File(path + str);
				byte[] data = new byte[10 * 1024];
				int byteRead = -1;
				FileInputStream in = new FileInputStream(uploadFile);
				FileOutputStream out = new FileOutputStream(file);
				while ((byteRead = in.read(data)) != -1) {
					out.write(data, 0, byteRead);
					out.flush();
				}
				out.close();
				in.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}

	/**
	 * 根据日期和1-10000000之间的随机数产生产生jpg格式的图片
	 * 
	 */
	public static String getDatedFName() {
		StringBuffer result = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		int ronNum = (int) (Math.random() * 10000000);
		String fileName = df.format(new Date()) + "_" + ronNum;
		result.append(fileName);
		result.append(".jpg");
		return result.toString();
	}

	/**
	 * 计算图片尺寸大小等信息：w宽、h高、s大小。异常时返回null。
	 * 
	 * @param imgpath
	 *            图片路径
	 * @return 图片信息map
	 */
	public static Map<String, Long> getImgInfo(String imgpath) {
		Map<String, Long> map = new HashMap<String, Long>();
		File imgfile = new File(imgpath);
		try {
			FileInputStream fis = new FileInputStream(imgfile);
			BufferedImage buff = ImageIO.read(imgfile);
			map.put("w", buff.getWidth() * 1L);
			map.put("h", buff.getHeight() * 1L);
			fis.close();
		} catch (FileNotFoundException e) {
			System.err.println("所给的图片文件" + imgfile.getPath()
					+ "不存在！计算图片尺寸大小信息失败！");
			map = null;
		} catch (IOException e) {
			System.err.println("计算图片" + imgfile.getPath() + "尺寸大小信息失败！");
			map = null;
		}
		return map;
	}
}
