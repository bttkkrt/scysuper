package com.jshx.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * ZIP压缩包处理工具类
 * 
 */
public class ZIPUtil {

	private ZIPUtil() {
	}

	/**
	 * 压缩指定文件或文件夹
	 * 
	 * @param filePath
	 *            文件或者文件夹路径
	 * @param zipFilePath
	 *            生成的压缩包路径
	 */
	public void createZipFile(String filePath, String zipFilePath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFilePath);
			zos = new ZipOutputStream(fos);
			zos.setEncoding("gbk");
			writeZipFile(new File(filePath), zos, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zos != null)
					zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void writeZipFile(File f, ZipOutputStream zos, String hiberarchy) {
		if (f.exists()) {
			if (f.isDirectory()) {
				hiberarchy += f.getName() + "/";
				File[] fif = f.listFiles();
				for (int i = 0; i < fif.length; i++) {
					writeZipFile(fif[i], zos, hiberarchy);
				}

			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(f);
					ZipEntry ze = new ZipEntry(hiberarchy + f.getName());
					zos.putNextEntry(ze);
					int b;
					while ((b = fis.read()) != -1) {
						zos.write(b);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fis != null)
							fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

	private static ZIPUtil zu = null;

	/**
	 * 获取压缩工具实例
	 * 
	 * @return ZIPUtil对象
	 */
	public static ZIPUtil getInstance() {
		if (zu == null)
			zu = new ZIPUtil();
		return zu;

	}

	// public static void main(String[] args) {
	//
	// ZIPUtil.getInstance().CreateZipFile("D://Eclipse插件功能.doc",
	// "D://Eclipse插件功能.zip");
	// }

}