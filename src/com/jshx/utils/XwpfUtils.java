/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2012-2013 Jiangsu Hongxin System Integration Co., Ltd.   */
/*                                                                        */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the 　　　　　　 */
/* subject matter of this material.  All manufacturing, reproduction, use,*/
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */
/* the terms of the license.                                              */
/* 本软件文档资料是江苏鸿信公司的资产,任何人士阅读和使用本资料必须获得    */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                       */
/*                                                                        */
/**************************************************************************/
package com.jshx.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import com.jshx.core.utils.Struts2Util;

/**
 * word 处理方法类 GY-UPDATE 2015-01-24
 */
public class XwpfUtils
{
	/**
	 * 替换段落里面的变量
	 * @param doc 要替换的文档
	 * @param params 参数
	 */
	public static void replaceInPara(XWPFDocument doc, Map<String, Object> params)
	{
		try
		{
			Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
			XWPFParagraph para;
			while (iterator.hasNext())
			{
				para = iterator.next();
				List<XWPFRun> runs;
				Matcher matcher;
				if (matcher(para.getParagraphText()).find())
				{
					runs = para.getRuns();
					for (int i = 0; i < runs.size(); i++)
					{
						XWPFRun run = runs.get(i);
						String runText = run.toString();
						matcher = matcher(runText);
						if (matcher.find())
						{
							while ((matcher = matcher(runText)).find())
							{
								runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
							}
							// 直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
							// 所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
							para.removeRun(i);
							XWPFRun newRun = para.insertNewRun(i);
							newRun.setText(runText);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * 替换表格里面的变量
	 * @param doc 要替换的文档
	 * @param params 参数
	 */
	public static void replaceInTable(XWPFDocument doc, Map<String, Object> params)
	{
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		while (iterator.hasNext())
		{
			table = iterator.next();
			rows = table.getRows();
			for (XWPFTableRow row : rows)
			{
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells)
				{
					String text = cell.getText();
					Matcher matcher;
					if (matcher(text).find())
					{
						matcher = matcher(text);
						if (matcher.find())
						{
							while ((matcher = matcher(text)).find())
							{
								text = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
							}
							cell.removeParagraph(0);
							cell.setText(text);
						}
					}
				}
			}
		}
	}

	/**
	 * 正则匹配字符串
	 * @param str
	 * @return
	 */
	private static Matcher matcher(String str)
	{
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}

	/**
	 * 输出文件方法
	 */
	public static void downLoad(ByteArrayOutputStream ostream, String title, HttpServletRequest request)
	{
		try
		{
			String browName = new String();
			String clientInfo = request.getHeader("User-agent");
			if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0))
			{
				if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
					browName = new String(title.getBytes("GBK"), "ISO-8859-1");
				else
				{
					browName = URLEncoder.encode(title, "UTF-8");
				}
			}
			browName = URLEncoder.encode(title, "UTF-8");
			Struts2Util.getResponse().setContentType("application/msword");
			Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + browName);
			OutputStream out = Struts2Util.getResponse().getOutputStream();
			try
			{
				out.write(ostream.toByteArray());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				ostream.close();
				out.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 跨列合并
	 */
	public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell)
	{
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++)
		{
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCell)
			{
				// The first merged cell is set with RESTART merge
				// value
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			}
			else
			{
				// Cells which join (merge) the first one, are set
				// with CONTINUE
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * 跨行合并
	 */
	public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow)
	{
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++)
		{
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow)
			{
				// The first merged cell is set with RESTART merge
				// value
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
			}
			else
			{
				// Cells which join (merge) the first one, are set
				// with CONTINUE
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}
	
	/**
	 * 单元格赋值并设置相应属性
	 */
	public static void setCellText(XWPFTableCell cell,String text, String bgcolor, int width) {  
        try
		{
        	CTTc cttc = cell.getCTTc(); 
            CTTcPr ctPr = cttc.addNewTcPr(); 
            if(width != 0)
            {
            	ctPr.addNewTcW().setW(BigInteger.valueOf(width));  //设置宽度
            }
            if (null != bgcolor)
            {
            	cell.setColor(bgcolor);
            }
            ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);//居中
            cell.setText(text);
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
    }  
}
