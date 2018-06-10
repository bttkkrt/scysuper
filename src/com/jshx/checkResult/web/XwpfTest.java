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
package com.jshx.checkResult.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class XwpfTest
{

	/**
	 * 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
	 * @throws Exception
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("checktime", "2014-02-28");
		params.put("companyname", "测试");
		params.put("address", "测试地址123");
		params.put("fzr", "高大上");
		params.put("phone", "18900001111");
		String filePath = "D:\\check.docx";
		InputStream is = new FileInputStream(filePath);
		XWPFDocument doc = new XWPFDocument(is);
		// 替换段落里面的变量
		replaceInPara(doc, params);
		// 替换表格里面的变量
		replaceInTable(doc, params);
		// createTable(doc, params);
		OutputStream os = new FileOutputStream("D:\\write.docx");
		doc.write(os);
		close(os);
		close(is);
	}

	/**
	 * 替换段落里面的变量
	 * @param doc 要替换的文档
	 * @param params 参数
	 */
	private static void replaceInPara(XWPFDocument doc, Map<String, Object> params)
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

	/**
	 * 替换段落里面的变量
	 * @param para 要替换的段落
	 * @param params 参数
	 */
	private static void replaceInPara(XWPFParagraph para, Map<String, Object> params)
	{
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
					// newRun.setBold(true);
					// newRun.setFontSize(14);
					// newRun.setFontFamily("宋体");
					// newRun.setUnderline(UnderlinePatterns.THICK);
					newRun.setText(runText);
				}
			}
		}
	}

	/**
	 * 替换表格里面的变量
	 * @param doc 要替换的文档
	 * @param params 参数
	 */
	private static void replaceInTable(XWPFDocument doc, Map<String, Object> params)
	{
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		List<XWPFParagraph> paras;
		while (iterator.hasNext())
		{
			table = iterator.next();
			rows = table.getRows();
			for (XWPFTableRow row : rows)
			{
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells)
				{
					paras = cell.getParagraphs();
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

	private static void createTable(XWPFDocument doc, Map<String, Object> params)
	{
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		while (iterator.hasNext())
		{
			XWPFTable table = iterator.next();
			XWPFTableRow newrow = table.getRow(2);
			table.addRow(newrow);
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
	 * 关闭输入流
	 * @param is
	 */
	private static void close(InputStream is)
	{
		if (is != null)
		{
			try
			{
				is.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭输出流
	 * @param os
	 */
	private static void close(OutputStream os)
	{
		if (os != null)
		{
			try
			{
				os.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}