package com.jshx.ajxx.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {
	/**
	* @Desc：生成word文件
	* @Author：张轮
	* @Date：2014-1-22下午05:33:42
	* @param dataMap word中需要展示的动态数据，用map集合来保存
	* @param templateName word模板名称，例如：test.ftl
	* @param filePath 文件生成的目标路径，例如：D:/wordFile/
	* @param fileName 生成的文件名称，例如：test.doc
	*/
	    @SuppressWarnings("unchecked")
	public static long createWord(String templateName,String fileName,String filePath,Map dataMap){
	        try {
	        //创建配置实例 
	        Configuration configuration = new Configuration();
	        
	        //设置编码
	            configuration.setDefaultEncoding("UTF-8");
	            
	            //ftl模板文件统一放至 com.lun.template 包下面
	            configuration.setClassForTemplateLoading(WordUtil.class,"/com/jshx/ajxx/template/");
	            
	            //获取模板 
	            Template template = configuration.getTemplate(templateName);
	            
	            //输出文件
	            File outFile = new File(filePath+File.separator+fileName);
	            
	            //如果输出目标文件夹不存在，则创建
	            if (!outFile.getParentFile().exists()){
	                outFile.getParentFile().mkdirs();
	            }
	            
	            //将模板和数据模型合并生成文件 
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

	            //生成文件
	            template.process(dataMap, out);
	            
	            //关闭流
	            out.flush();
	            out.close();
	            
	           return outFile.length();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }
	    
}
