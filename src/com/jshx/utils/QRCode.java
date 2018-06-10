/**   
 * 说明：
 * @version： wxet v0.1 
 * @author:Fun  
 * @date:2011-9-5 
 * 
 * @Copyright 2011 版权所有  江苏鸿信  
 */
package com.jshx.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;


/**   
 * 类描述： 二维码生成和解码工具  
 * 
 * 项目名称：wxet   
 * 类名称：QRCode   
 * 创建人：Fun   
 * 创建时间：2011-9-5 下午05:18:04   
 * 修改人：Fun   
 * 修改时间：2011-9-5 下午05:18:04   
 * 修改备注：   
 * @version    
 *    
 */
public class QRCode {

	private static final int DEFAULT_WIDTH = 139;
	
	/**
	  * 
	  * 方法说明：根据字符串信息生成二维码图片
	  *    
	  * @param info：需编码的文字内容  
	  * @param desImgPath:二维码图片生成路径   
	  * @return 返回值类型   
	  * @Exception 异常对象  
	  *
	  * @author:Fun  
	  * @date:2011-9-5
	  */
	 public static void encodeInfoToImage(String info, String desImgPath) throws Exception{
	  
		 try{
			Qrcode qrcode=new Qrcode();
			qrcode.setQrcodeErrorCorrect('M');
			qrcode.setQrcodeEncodeMode('B');
			qrcode.setQrcodeVersion(7);
	 
			String str=info;
	 
			byte[] bstr=str.getBytes("UTF-8");
			BufferedImage bi = new BufferedImage(DEFAULT_WIDTH, DEFAULT_WIDTH, BufferedImage.TYPE_INT_RGB);    
			Graphics2D g = bi.createGraphics();    
	 
	        g.setBackground(Color.WHITE);    
	        g.clearRect(0, 0, DEFAULT_WIDTH, DEFAULT_WIDTH);    
	        g.setColor(Color.BLACK); 
	 
	        if (bstr.length>0 && bstr.length <123){    
	            boolean[][] b = qrcode.calQrcode(bstr);  
	            //int unitWidth = DEFAULT_WIDTH / b.length;
	            
	            for (int i=0;i<b.length;i++){    
	                for (int j=0;j<b.length;j++){    
	                    if (b[j][i]) {    
	                        g.fillRect(j*3+2,i*3+2,3,3); 
	                      //g.fillRect(j*unitWidth, i*unitWidth, unitWidth-1, unitWidth-1);   
	                    }    
	                }    
	 
	            }    
	        }    
	        g.dispose();    
	        bi.flush();    

	        File f = new File(desImgPath);
	        
	        if(!f.exists()){//创建目录
	        	f.mkdirs();
	        }
	        
	        ImageIO.write(bi, "png", f);
	        
	        System.out.println("encoder QRcode success");  
	         
	     }catch (Exception e) {
	    	 System.out.println("Error: "+ e.getMessage());
	         e.printStackTrace();
	     } 
	 }
	 
	 /**
		 * 
		 * 方法说明：根据二维码图片解码成可识别的信息
		 *    
		 * @param imagePath：二维码图片路径     
		 * @return 返回值类型   
		 * @Exception 异常对象  
		 *
		 * @author:Fun  
		 * @date:2011-9-5
		 */
		public static void decodeImageToInfo(String imagePath){
			QRCodeDecoder decoder = new QRCodeDecoder();
			File imageFile = new File(imagePath);
			BufferedImage image = null;
			try {
				image = ImageIO.read(imageFile);
				String decodedData = new String(decoder.decode(new J2SEImage(image)),"utf-8");
				System.out.println("dncoder QRcode success"); 
				System.out.println("解析结果为："+decodedData);
			} catch (IOException e) {
				System.out.println("Error: "+ e.getMessage());
				e.printStackTrace();
			}catch (DecodingFailedException dfe) {
				System.out.println("Error: " + dfe.getMessage());
				dfe.printStackTrace();
			} 
		}
		
		public static void main(String[] args) throws Exception {
			QRCode.encodeInfoToImage("http://202.102.101.35:18004/qa/sign.html","E:\\ycly.png");
			//QRCode.decodeImageToInfo("E:\\ssss.png");
		} 
}

class J2SEImage implements QRCodeImage {
	BufferedImage image;
	public J2SEImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public int getPixel(int x, int y) {
		return image.getRGB(x, y);
	}
}
