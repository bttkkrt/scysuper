package com.jshx.core.utils;

import com.jshx.core.utils.XMLUtil;
import org.dom4j.Element;


/**
 * 生成FusionChart调用时的XML字符串参数的工具类
 * 
 * @author		YuWeitao
 * @version		创建时间：2011-5-19 上午9:00:21
 * 		
 */

public class FusionChartXmlUtil {
	//定义颜色数组
	private String[] colorArray = {"FF69B4","00FF7F","800080","0000FF","ADD8E6",
									"FF0000","008000","FF00FF","FFFF00","FF8C00",
									"00FFFF","FFDAB9","A9A9A9","FFA500","B22222",
									"FAFAD2","90EE90","00FFFF","4169E1","FF7F50"};
	
	private int count = 0;
	
	
	/**
	 * 生成FusionChart调用时的XML字符串参数
	 * @param 	data:				一个二维数组，对应Chart中的横坐标和纵坐标
	 * @param	caption：				定义该图表的主标题
	 * @param	subcaption：			定义该图表的副标题
	 * @param	xAxisName:			X坐标显示名
	 * @param	yAxisName：			Y坐标显示名
	 * @param	yAxisMinValue:		Y坐标显示的最小值，当设置的值大于任何一列值时，该属性设置失效
	 * @param	decimalPrecision:	精确位数设置，可选值：[0,10],'0'为精确到整数;'1'为精确到小数点后一位，依此类推
	 * @param	showValues:			设置是否显示每一列的value值，可选值：'0'不显示;'1'显示
	 * @param	showNames:			设置是否显示X坐标没一点的值，可选值：'0'不显示;'1'显示
	 * @param	numberPrefix:		设置图表上数字的前缀，如设置为'$',则图表上数字前都有$前缀
	 * @param	animation:			设置图表动画效果是否开启，可选值：'0'关闭;'1'开启
	 * @param	multiSeriesFlag:	设置是否是多系列数据，可选值：'0'不是(默认值,可不填);'1'是
	 * 
	 * @return	xmlString:			返回一个格式化的XML字符串
	 */
	public String convertToXML(Object[][] data, String caption, String subcaption,
				String xAxisName, String yAxisName, String yAxisMinValue, 
				String decimalPrecision, String showValues, String showNames, 
				String numberPrefix, String animation){
		String xmlString = "";
		try {
			//二维数组行数
			int rows = data.length;
			
			XMLUtil xml = new XMLUtil();
	        Element graph = xml.addRoot("graph"); 
	        
	        xml.addAttribute(graph, "caption", caption);   
	        xml.addAttribute(graph, "subcaption", subcaption);  
	        xml.addAttribute(graph, "xAxisName", xAxisName);
	        xml.addAttribute(graph, "yAxisName", yAxisName);
	        xml.addAttribute(graph, "yAxisMinValue", yAxisMinValue);
	        xml.addAttribute(graph, "decimalPrecision", decimalPrecision);
	        xml.addAttribute(graph, "showValues", showValues);
	        xml.addAttribute(graph, "showNames", showNames);
	        xml.addAttribute(graph, "numberPrefix", numberPrefix);
	        xml.addAttribute(graph, "animation", animation);
	        
	        for(int i=0;i<rows;i++){
	        	Element node = xml.addNode(graph, "set");
	        	if(null != data[i][0]){
	        		node.addAttribute("name", String.valueOf(data[i][0])); 
	        	}else{
	        		node.addAttribute("name", ""); 
	        	}
	        	if(null != data[i][1]){
	        		node.addAttribute("value",String.valueOf(data[i][1]));  
	        	}else{
	        		node.addAttribute("value","0");  
	        	}
	        	
	        	//node.addAttribute("color", Integer.toHexString(   
		        //            (int) (Math.random() * 255 * 255 * 255)).toUpperCase());
	        	if(count<20){
		        	node.addAttribute("color", colorArray[count]);
		        	count++;	
	        	}else{
	        		count = 0;
		        	node.addAttribute("color", colorArray[count]);
		        	count++;
	        	}

	        }
	        xmlString = xml.getXML();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return xmlString;
	}

	
	/**
	 * 生成FusionChart调用时的XML字符串参数，增加String multiSeriesFlag用于multi-series数据图表显示
	 * @return
	 */
	public String convertToXML(Object[][] data, String caption, String subcaption,
							String xAxisName, String yAxisName, String yAxisMinValue, 
							String decimalPrecision, String showValues, String showNames, 
							String numberPrefix, String animation, String multiSeriesFlag){
		String xmlString = "";
		try {
			if("0".equals(multiSeriesFlag)){
				xmlString = this.convertToXML(data,caption,subcaption,xAxisName,yAxisName,yAxisMinValue,
									decimalPrecision,showValues,showNames,numberPrefix,animation);
				
			}else if("1".equals(multiSeriesFlag)){
				int seriesNum = data[0].length - 1;
				Object[] seriesArray = new Object[seriesNum];
				for(int i=1;i<data[0].length;i++){
					seriesArray[i-1] = data[0][i];
				}
				
				int xNum = data.length - 1;
				Object[] xNameArray = new Object[xNum];
				for(int i=1;i<data.length;i++){
					xNameArray[i-1] = data[i][0];
				}
				
				XMLUtil xml = new XMLUtil();
		        Element graph = xml.addRoot("graph"); 
		        
		        xml.addAttribute(graph, "caption", caption);   
		        xml.addAttribute(graph, "subcaption", subcaption);  
		        xml.addAttribute(graph, "xAxisName", xAxisName);
		        xml.addAttribute(graph, "yAxisName", yAxisName);
		        xml.addAttribute(graph, "yAxisMinValue", yAxisMinValue);
		        xml.addAttribute(graph, "decimalPrecision", decimalPrecision);
		        xml.addAttribute(graph, "showValues", showValues);
		        xml.addAttribute(graph, "showNames", showNames);
		        xml.addAttribute(graph, "numberPrefix", numberPrefix);
		        xml.addAttribute(graph, "animation", animation);
		        
	        	Element categoriesNode = xml.addNode(graph, "categories");
	        	categoriesNode.addAttribute("font", "Arial"); 
	        	categoriesNode.addAttribute("fontSize", "11"); 
	        	categoriesNode.addAttribute("fontColor", "000000");   
	        	for(int i=0;i<xNum;i++){
	        		Element node = xml.addNode(categoriesNode, "category");
	        		if(null != xNameArray[i]){
	        			node.addAttribute("name", String.valueOf(xNameArray[i]));	
	        		}else{
	        			node.addAttribute("name", "");
	        		}
	        	}
	        	
	        	for(int i=0;i<seriesNum;i++){
	        		Element datasetNode = xml.addNode(graph, "dataset");
	        		if(null != seriesArray[i]){
	        			datasetNode.addAttribute("seriesname", String.valueOf(seriesArray[i]));
	        		}else{
	        			datasetNode.addAttribute("seriesname", "");
	        		}
	        		//datasetNode.addAttribute("color", Integer.toHexString(   
		            //        (int) (Math.random() * 255 * 255 * 255)).toUpperCase());
		        	if(count<20){
		        		datasetNode.addAttribute("color", colorArray[count]);
			        	count++;	
		        	}else{
		        		count = 0;
		        		datasetNode.addAttribute("color", colorArray[count]);
			        	count++;
		        	}
	        		
	        		for(int j=0;j<xNum;j++){
	        			Element setNode = xml.addNode(datasetNode, "set");
	        			if(null != data[j+1][i+1]){
	        				setNode.addAttribute("value", String.valueOf(data[j+1][i+1]));
	        			}else{
	        				setNode.addAttribute("value", "0");
	        			}
	        		}
	        	}
				
	        	xmlString = xml.getXML();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return xmlString;		
	}
	
//	public static void main(String[] args){
//		String[][] data = {{"0","1"},{"1","3"},{"2","4"},{"5","6"}};
//		
//		Object[][] queryDataForMultiSeries = {	
//												{"xName", "A",   "B"},
//												{"Jan",   "83",  "77"},
//												{"Feb",   "103", "23"},
//												{"Mar",   "47",  "32"},
//												{"Apr",   "67",  "56"},
//												{"May",   "138", "65"},
//												{"Jun",   "158", "88"},
//												{"Jul",   "49",  "22"},
//												{"Aug",   "90",  "74"}
//											};
//		
//		String ret = new FusionChartXmlUtil().
//			convertToXML(data, "标题", "SubTitle", "横坐标", "纵坐标", "0", "0", "1", "1", "", "1","0");
//		
//		System.out.println("ret="+ret);
//	}
	
}