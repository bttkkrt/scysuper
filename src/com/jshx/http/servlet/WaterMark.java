package com.jshx.http.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterMark {
	public static final String SBPATH = "/virtualdir/upload/photo";
	/**
	 * 
	 * 给图片添加水印
	 * 
	 * @param filePath
	 *            需要添加水印的图片的路径
	 * 
	 * @param markContent
	 *            水印的文字
	 * 
	 * @param markContentColor
	 *            水印文字的颜色
	 * 
	 * @param qualNum
	 *            图片质量
	 * 
	 * @return
	 * 
	 */

	public boolean createMark(String photoName, String path)

	{
		String nickName="";//设置水印内容
		photoName=photoName.replace("webapps/../", "");
		// String path = "virtualdir/upload/photo/";
		// String filePath = tempdir + path;E:\development\apache-tomcat-6.0.14
		ImageIcon srcFile = new ImageIcon(photoName);
		// ImageIcon srcFile=new ImageIcon("e:\\1.jpg");
		// String name=photoName.replaceAll("/", "\\\\\\\\");
		// ImageIcon srcFile=new ImageIcon(name);
		// ImageIcon watermarkFile=new
		// ImageIcon("d:\\电信logo.png");apache-tomcat-jjh E:\development
		ImageIcon watermarkFile = new ImageIcon(
				"F:\\tomcat06\\virtualdir\\upload\\photo\\01.gif");

		Image theImg = srcFile.getImage();
		Image watermarkImg = watermarkFile.getImage();
		int width = theImg.getWidth(null);

		int height = theImg.getHeight(null);
		if (width > height) {
			int mid = width;
			width = height;
			height = mid;
		}
		BufferedImage bimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Font font = new Font("新宋体", Font.PLAIN, width / 15);

		Graphics2D g = bimage.createGraphics();

		g.setColor(Color.RED);

		g.setFont(font);

		g.setBackground(Color.green);

		g.drawImage(theImg, 0, 0, null);

		g.drawImage(watermarkImg, 10, 10, 870, 280, null); // 添加水印图片
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		g.drawString(nickName, width / 7, height / 20); // 添加水印的文字和设置水印文字出现的内容
		g.drawString(sdf.format(new Date()), width / 7, height / 10);
		g.dispose();

		try {

			FileOutputStream out = new FileOutputStream(photoName);
			// FileOutputStream out=new FileOutputStream("e:\\2.jpg");
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);

			param.setQuality(100f, true);

			encoder.encode(bimage, param);

			out.close();

		} catch (Exception e)

		{
			return false;
		}
		System.out.println("制作成功");
		return true;

	}

	public static void main(String args[]) {
		//	  WaterMark wm=new WaterMark();
		//	  wm.createMark("ddf", "dsfiuhaasdfdsf");
	}

}
