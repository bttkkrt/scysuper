package com.jshx.ajxx.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

/**
 * 根据数据生成doc文件
 * @author luting
 * 2015-10-21
 *
 */
public class FileDocUtil {
	public String createDocFile(String srcPath,String fileName,String destPath,Map<String, Object> map) throws IOException
	{
		FileOutputStream fos = null;
		try {
			OPCPackage pack = POIXMLDocument.openPackage(srcPath);
			CustomXWPFDocument doc = new CustomXWPFDocument(pack);
            // 替换文本内容
            for (Entry<String, Object> e : map.entrySet()) {
            	if(e.getKey().equals("cyqyList"))//抽样取样凭证
        		{
            		List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(1);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row=table.createRow();
        				List<XWPFTableCell> cells = row.getTableCells();
        				row.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells.get(0), mm.get("xh").toString());
        				setCellText(tmpCells.get(1), cells.get(1), mm.get("wpmc").toString());
        				setCellText(tmpCells.get(2), cells.get(2), mm.get("gg").toString());
        				setCellText(tmpCells.get(3), cells.get(3), mm.get("sl").toString());
        			}
        			table.removeRow(1);
        		}
        		else if(e.getKey().equals("jdwtList"))//鉴定委托书
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(1);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row=table.createRow();
        				List<XWPFTableCell> cells = row.getTableCells();
        				row.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells.get(0), mm.get("wpmc").toString());
        				setCellText(tmpCells.get(1), cells.get(1), mm.get("gg").toString());
        				setCellText(tmpCells.get(2), cells.get(2), mm.get("sl").toString());
        				setCellText(tmpCells.get(3), cells.get(3), mm.get("bz").toString());
        			}
        			table.removeRow(1);
        		}
        		else if(e.getKey().equals("wshzList"))//文书送达回执
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(2);
            			tmpCells=tmpRow.getTableCells();
            		}
            		int i = 3;
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row=table.insertNewTableRow(i);
        				row.setHeight(tmpRow.getHeight());
        				row.createCell();
        				row.createCell();
        				row.createCell();
        				row.createCell();
        				row.createCell();
        				row.createCell();
        				List<XWPFTableCell> cells = row.getTableCells();
        				setCellText(tmpCells.get(0), cells.get(0), mm.get("wsmc").toString());
        				setCellText(tmpCells.get(2), cells.get(2), mm.get("sddz").toString());
        				setCellText(tmpCells.get(3), cells.get(3), mm.get("sdrq").toString());
        				setCellText(tmpCells.get(4), cells.get(4), mm.get("sdfs").toString());
        				setCellText(tmpCells.get(5), cells.get(5), mm.get("sdr").toString());
        				i++;
        			}
        			table.removeRow(2);
        		}
        		else if(e.getKey().equals("mlList"))//目录
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(1);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row=table.createRow();
        				List<XWPFTableCell> cells = row.getTableCells();
        				row.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells.get(0), mm.get("xh").toString());
        				setCellText(tmpCells.get(1), cells.get(1), mm.get("wjmc").toString());
        				setCellText(tmpCells.get(2), cells.get(2), mm.get("time").toString());
        				setCellText(tmpCells.get(3), cells.get(3), mm.get("ys").toString());
        			}
        			table.removeRow(1);
        		}
        		else if(e.getKey().equals("xxzjList"))//先行登记保存证据通知书
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(1);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row=table.createRow();
        				List<XWPFTableCell> cells = row.getTableCells();
        				row.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells.get(0), mm.get("xh").toString());
        				setCellText(tmpCells.get(1), cells.get(1), mm.get("zjmc").toString());
        				setCellText(tmpCells.get(2), cells.get(2), mm.get("gg").toString());
        				setCellText(tmpCells.get(3), cells.get(3), mm.get("cd").toString());
        				setCellText(tmpCells.get(4), cells.get(4), mm.get("cs").toString());
        				setCellText(tmpCells.get(5), cells.get(5), mm.get("dw").toString());
        				setCellText(tmpCells.get(6), cells.get(6), mm.get("jg").toString());
        				setCellText(tmpCells.get(7), cells.get(7), mm.get("sl").toString());
        				setCellText(tmpCells.get(8), cells.get(8), mm.get("bz").toString());
        			}
        			table.removeRow(1);
        		}
        		else if(e.getKey().equals("zjList"))//证据列表
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(0);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row=table.createRow();
        				List<XWPFTableCell> cells = row.getTableCells();
        				row.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells.get(0), mm.get("wpmc").toString());
        			}
        			table.removeRow(0);
        		}
        		else if(e.getKey().equals("blList"))//询问笔录
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow=rows.get(0);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row1=table.createRow();
        				List<XWPFTableCell> cells1 = row1.getTableCells();
        				row1.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells1.get(0), mm.get("wt").toString());
        				XWPFTableRow row2=table.createRow();
        				List<XWPFTableCell> cells2 = row2.getTableCells();
        				row2.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells2.get(0), mm.get("hd").toString());
        			}
        			table.removeRow(0);
        		}
        		else if(e.getKey().equals("xccssgyhList"))//事故隐患
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
        			List<XWPFTable> tables  = doc.getTables();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		if(tables.size() != 0) {
            			table =  tables.get(0);
            			rows = table.getRows();
            			tmpRow=rows.get(0);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row1=table.createRow();
        				List<XWPFTableCell> cells1 = row1.getTableCells();
        				row1.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells1.get(0), mm.get("sgyh").toString());
        			}
        			table.removeRow(0);
        			table.setInsideHBorder(XWPFBorderType.NONE, 0, 0, "");
        			table.setInsideVBorder(XWPFBorderType.NONE, 0, 0, "");
        		}
        		else if(e.getKey().equals("xccscljdList"))//处理决定
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
        			List<XWPFTable> tables  = doc.getTables();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells=null;//模版列
            		XWPFTableRow tmpRow=null;//匹配用
            		if(tables.size() > 1) {
            			table =  tables.get(1);
            			rows = table.getRows();
            			tmpRow=rows.get(0);
            			tmpCells=tmpRow.getTableCells();
            		}
        			for(Map<String, Object> mm:newList){
        				XWPFTableRow row1=table.createRow();
        				List<XWPFTableCell> cells1 = row1.getTableCells();
        				row1.setHeight(tmpRow.getHeight());
        				setCellText(tmpCells.get(0), cells1.get(0), mm.get("cljd").toString());
        			}
        			table.removeRow(0);
        			table.setInsideHBorder(XWPFBorderType.NONE, 0, 0, "");
        			table.setInsideVBorder(XWPFBorderType.NONE, 0, 0, "");
        		}
        		else if(e.getKey().equals("picList"))//照片
        		{
        			List<Map<String, Object>> newList = (List<Map<String, Object>>) e.getValue();
            		Iterator<XWPFTable> iterator = doc.getTablesIterator();
            		XWPFTable table = null;
            		List<XWPFTableRow> rows=null;
            		List<XWPFTableCell> tmpCells1=null;//模版列
            		List<XWPFTableCell> tmpCells2=null;//模版列
            		XWPFTableRow tmpRow1=null;//匹配用
            		XWPFTableRow tmpRow2=null;//匹配用
            		while (iterator.hasNext()) {
            			table =  iterator.next();
            			rows = table.getRows();
            			tmpRow1=rows.get(0);
            			tmpCells1=tmpRow1.getTableCells();
            			tmpRow2=rows.get(1);
            			tmpCells2=tmpRow2.getTableCells();
            		}
            		for(Map<String, Object> mm:newList){
        				XWPFTableRow row1=table.createRow();
        				List<XWPFTableCell> cells1 = row1.getTableCells();
        				setPic(doc,tmpCells1.get(0), cells1.get(0), mm.get("pic"));
        				setCellText(tmpCells1.get(0),cells1.size() == 1?row1.addNewTableCell():cells1.get(1), "");
        				XWPFTableRow row2=table.createRow();
        				List<XWPFTableCell> cells2 = row2.getTableCells();
        				row2.setHeight(tmpRow1.getHeight());
        				setCellText(tmpCells1.get(0), cells2.get(0), mm.get("picName").toString());
        				setCellText(tmpCells1.get(0), cells2.size() == 1?row2.addNewTableCell():cells2.get(1), "");
        				
        				XWPFTableRow row3=table.createRow();
        				List<XWPFTableCell> cells3 = row3.getTableCells();
        				row3.setHeight(tmpRow1.getHeight());
        				setCellText(tmpCells2.get(0), cells3.get(0), "拍摄时间：");
        				setCellText(tmpCells2.get(1), cells3.size() == 1?row3.addNewTableCell():cells3.get(1), mm.get("picTime").toString());
        				
        				XWPFTableRow row4=table.createRow();
        				List<XWPFTableCell> cells4 = row4.getTableCells();
        				row4.setHeight(tmpRow1.getHeight());
        				setCellText(tmpCells2.get(0), cells4.get(0), "拍摄地点：");
        				setCellText(tmpCells2.get(1), cells4.size() == 1?row4.addNewTableCell():cells4.get(1), mm.get("picAdd").toString());
        				
        				XWPFTableRow row5=table.createRow();
        				List<XWPFTableCell> cells5 = row5.getTableCells();
        				row5.setHeight(tmpRow1.getHeight());
        				setCellText(tmpCells2.get(0), cells5.get(0), "照片内容：");
        				setCellText(tmpCells2.get(1), cells5.size() == 1?row5.addNewTableCell():cells5.get(1), mm.get("picContent").toString());
        				
        				XWPFTableRow row6=table.createRow();
        				List<XWPFTableCell> cells6 = row6.getTableCells();
        				row6.setHeight(tmpRow1.getHeight());
        				setCellText(tmpCells2.get(0), cells6.get(0), "事故单位确认：");
        				setCellText(tmpCells2.get(1), cells6.size() == 1?row6.addNewTableCell():cells6.get(1), "                                                       ");
        				
        			}
            		table.removeRow(1);
        			table.removeRow(0);
        			
        			for(int i=0;i<newList.size();i++)
        			{
        				mergeCellsHorizontal(table,i*6,0,1);
        				mergeCellsHorizontal(table,i*6+1,0,1);
        			}
        		}
        		else
        		{
        			// 读取word文本内容
        			List<XWPFParagraph> paragraphs = doc.getParagraphs();
        			processParagraphs(paragraphs, map, doc);  
        			 //处理表格  
                    Iterator<XWPFTable> it = doc.getTablesIterator();  
                    while (it.hasNext()) {  
                        XWPFTable table = it.next();  
                        List<XWPFTableRow> rows = table.getRows();  
                        for (XWPFTableRow row : rows) {  
                            List<XWPFTableCell> cells = row.getTableCells();  
                            for (XWPFTableCell cell : cells) {  
                                List<XWPFParagraph> paragraphListTable =  cell.getParagraphs();  
                                processParagraphs(paragraphListTable, map, doc);  
                            }  
                        }  
                    }  
                    
                    //处理页脚
                    List<XWPFFooter> yjlist = doc.getFooterList();
                    for(XWPFFooter footer:yjlist)
                    {
                    	List<XWPFParagraph> pp = footer.getParagraphs();
            			processParagraphs(pp, map, doc);  
                    }
                    
        		}
            }
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			doc.write(ostream);
			File outdir = new File(destPath);
			File outfile = new File(destPath+"/"+fileName);
			if(!outdir.exists())
			{
				outdir.mkdirs();
			}
			if(outfile.exists())
			{
				outfile.delete();
			}
			outfile.createNewFile();
			fos = new FileOutputStream(outfile);
			fos.write(ostream.toByteArray());
			
			int pageSize =  doc.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
			return outfile.length()+ "," + pageSize;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (null != fos)
				fos.close();
		}
		return "";
	}
	
	/** 
     * 处理段落 
     * @param paragraphList 
     */  
    public static void processParagraphs(List<XWPFParagraph> paragraphList,Map<String, Object> param,CustomXWPFDocument doc){  
        if(paragraphList != null && paragraphList.size() > 0){  
            for(XWPFParagraph paragraph:paragraphList){  
                List<XWPFRun> runs = paragraph.getRuns();  
                for (XWPFRun run : runs) {  
                    String text = run.getText(0);  
                    if(text != null){  
                        for (Entry<String, Object> entry : param.entrySet()) {  
                            String key = "${" + entry.getKey() + "}";  
                            if(text.indexOf(key) != -1){  
                                Object value = entry.getValue();  
                                if (value instanceof String) {//文本替换  
                                    run.setText(entry.getValue().toString(),0);
                                } else if (value instanceof Map) {//图片替换  
                                	run.setText("",0);
                                    Map pic = (Map)value;  
                                    if(pic.containsKey("content"))
                                    {
                                    	int width = Integer.parseInt(pic.get("width").toString());  
                                        int height = Integer.parseInt(pic.get("height").toString());  
                                        int picType = getPictureType("jpg");  
                                        byte[] byteArray = (byte[]) pic.get("content");  
                                        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);  
                                        try {  
                                        	String ind = doc.addPictureData(byteInputStream,picType);  
                                            CTInline inline = run.getCTR().addNewDrawing().addNewInline();    
                                            doc.createPicture(doc.getAllPictures().size()-1, width , height,inline);  
                                        } catch (Exception e) {  
                                            e.printStackTrace();  
                                        }  
                                    }
                                }  
                            }  
                        }  
                    }  
                }  
            }  
        }  
    }  
    /** 
     * 根据图片类型，取得对应的图片类型代码 
     * @param picType 
     * @return int 
     */  
    private static int getPictureType(String picType){  
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;  
        if(picType != null){  
        	if(picType.equalsIgnoreCase("png")){  
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;  
            }else if(picType.equalsIgnoreCase("dib")){  
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;  
            }else if(picType.equalsIgnoreCase("emf")){  
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;  
            }else if(picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")){  
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;  
            }else if(picType.equalsIgnoreCase("wmf")){  
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;  
            }else if(picType.equalsIgnoreCase("jpeg")){  
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;  
            }      
        }  
        return res;  
    }  
    
    
    /** 
     * 将输入流中的数据写入字节数组 
     * @param in 
     * @return 
     */  
    public static byte[] inputStream2ByteArray(InputStream in,boolean isClose){  
        byte[] byteArray = null;  
        try {  
            int total = in.available();  
            byteArray = new byte[total];  
            in.read(byteArray);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if(isClose){  
                try {  
                    in.close();  
                } catch (Exception e2) {  
                    System.out.println("关闭流失败");  
                }  
            }  
        }  
        return byteArray;  
    }  
	public  void setCellText(XWPFTableCell tmpCell,XWPFTableCell cell,String text) throws Exception{
		CTTc cttc2 = tmpCell.getCTTc();
		CTTcPr ctPr2=cttc2.getTcPr();
		
		CTTc cttc = cell.getCTTc();
		CTTcPr ctPr = cttc.addNewTcPr();
		if(ctPr2.getTcW()!=null){
			ctPr.addNewTcW().setW(ctPr2.getTcW().getW());
		}
		if(ctPr2.getVAlign()!=null){
			ctPr.addNewVAlign().setVal(ctPr2.getVAlign().getVal());
		}
		
		if(ctPr2.getTcBorders()!=null){
			ctPr.setTcBorders(ctPr2.getTcBorders());
		}
		
		XWPFParagraph tmpP=tmpCell.getParagraphs().get(0);
		XWPFParagraph cellP=cell.getParagraphs().get(0);
		XWPFRun tmpR =null;
		if(tmpP.getRuns()!=null&&tmpP.getRuns().size()>0){
			tmpR=tmpP.getRuns().get(0);
		}
		XWPFRun cellR = cellP.createRun();
		cellR.setText(text);
		//复制字体信息
		if(tmpR!=null){
			cellR.setBold(tmpR.isBold());
			cellR.setItalic(tmpR.isItalic());
			cellR.setStrike(tmpR.isStrike());
			cellR.setUnderline(tmpR.getUnderline());
			cellR.setTextPosition(tmpR.getTextPosition());
			if(tmpR.getFontSize()!=-1){
				cellR.setFontSize(tmpR.getFontSize());
			}
			if(tmpR.getFontFamily()!=null){
				cellR.setFontFamily(tmpR.getFontFamily());
			}
			if(tmpR.getCTR()!=null){
				if(tmpR.getCTR().isSetRPr()){
					CTRPr tmpRPr =tmpR.getCTR().getRPr();
					if(tmpRPr.isSetRFonts()){
						CTFonts tmpFonts=tmpRPr.getRFonts();
						CTRPr cellRPr=cellR.getCTR().isSetRPr() ? cellR.getCTR().getRPr() : cellR.getCTR().addNewRPr();
						CTFonts cellFonts = cellRPr.isSetRFonts() ? cellRPr.getRFonts() : cellRPr.addNewRFonts();
						cellFonts.setAscii(tmpFonts.getAscii());
						cellFonts.setAsciiTheme(tmpFonts.getAsciiTheme());
						cellFonts.setCs(tmpFonts.getCs());
						cellFonts.setCstheme(tmpFonts.getCstheme());
						cellFonts.setEastAsia(tmpFonts.getEastAsia());
						cellFonts.setEastAsiaTheme(tmpFonts.getEastAsiaTheme());
						cellFonts.setHAnsi(tmpFonts.getHAnsi());
						cellFonts.setHAnsiTheme(tmpFonts.getHAnsiTheme());
					}
				}
			}
		}
		//复制段落信息
		cellP.setAlignment(tmpP.getAlignment());
		cellP.setVerticalAlignment(tmpP.getVerticalAlignment());
		cellP.setBorderBetween(tmpP.getBorderBetween());
		cellP.setBorderBottom(tmpP.getBorderBottom());
		cellP.setBorderLeft(tmpP.getBorderLeft());
		cellP.setBorderRight(tmpP.getBorderRight());
		cellP.setBorderTop(tmpP.getBorderTop());
		cellP.setPageBreak(tmpP.isPageBreak());
		if(tmpP.getCTP()!=null){
			if(tmpP.getCTP().getPPr()!=null){
				CTPPr tmpPPr = tmpP.getCTP().getPPr();
				CTPPr cellPPr = cellP.getCTP().getPPr() != null ? cellP.getCTP().getPPr() : cellP.getCTP().addNewPPr();
				//复制段落间距信息
				CTSpacing tmpSpacing =tmpPPr.getSpacing();
				if(tmpSpacing!=null){
					CTSpacing cellSpacing= cellPPr.getSpacing()!=null?cellPPr.getSpacing():cellPPr.addNewSpacing();
					if(tmpSpacing.getAfter()!=null){
						cellSpacing.setAfter(tmpSpacing.getAfter());
					}
					if(tmpSpacing.getAfterAutospacing()!=null){
						cellSpacing.setAfterAutospacing(tmpSpacing.getAfterAutospacing());
					}
					if(tmpSpacing.getAfterLines()!=null){
						cellSpacing.setAfterLines(tmpSpacing.getAfterLines());
					}
					if(tmpSpacing.getBefore()!=null){
						cellSpacing.setBefore(tmpSpacing.getBefore());
					}
					if(tmpSpacing.getBeforeAutospacing()!=null){
						cellSpacing.setBeforeAutospacing(tmpSpacing.getBeforeAutospacing());
					}
					if(tmpSpacing.getBeforeLines()!=null){
						cellSpacing.setBeforeLines(tmpSpacing.getBeforeLines());
					}
					if(tmpSpacing.getLine()!=null){
						cellSpacing.setLine(tmpSpacing.getLine());
					}
					if(tmpSpacing.getLineRule()!=null){
						cellSpacing.setLineRule(tmpSpacing.getLineRule());
					}
				}
				//复制段落缩进信息
				CTInd tmpInd=tmpPPr.getInd();
				if(tmpInd!=null){
					CTInd cellInd=cellPPr.getInd()!=null?cellPPr.getInd():cellPPr.addNewInd();
					if(tmpInd.getFirstLine()!=null){
						cellInd.setFirstLine(tmpInd.getFirstLine());
					}
					if(tmpInd.getFirstLineChars()!=null){
						cellInd.setFirstLineChars(tmpInd.getFirstLineChars());
					}
					if(tmpInd.getHanging()!=null){
						cellInd.setHanging(tmpInd.getHanging());
					}
					if(tmpInd.getHangingChars()!=null){
						cellInd.setHangingChars(tmpInd.getHangingChars());
					}
					if(tmpInd.getLeft()!=null){
						cellInd.setLeft(tmpInd.getLeft());
					}
					if(tmpInd.getLeftChars()!=null){
						cellInd.setLeftChars(tmpInd.getLeftChars());
					}
					if(tmpInd.getRight()!=null){
						cellInd.setRight(tmpInd.getRight());
					}
					if(tmpInd.getRightChars()!=null){
						cellInd.setRightChars(tmpInd.getRightChars());
					}
				}
			}
		}
	}
	
	
	public  void setPic(CustomXWPFDocument doc,XWPFTableCell tmpCell,XWPFTableCell cell,Object value) throws Exception{
		CTTc cttc2 = tmpCell.getCTTc();
		CTTcPr ctPr2=cttc2.getTcPr();
		
		CTTc cttc = cell.getCTTc();
		CTTcPr ctPr = cttc.addNewTcPr();
		if(ctPr2.getTcW()!=null){
			ctPr.addNewTcW().setW(ctPr2.getTcW().getW());
		}
		if(ctPr2.getVAlign()!=null){
			ctPr.addNewVAlign().setVal(ctPr2.getVAlign().getVal());
		}
		
		if(ctPr2.getTcBorders()!=null){
			ctPr.setTcBorders(ctPr2.getTcBorders());
		}
		
		XWPFParagraph cellP=cell.getParagraphs().get(0);
		XWPFRun cellR = cellP.createRun();
		cellR.setText("",0);
		Map pic = (Map)value;  
        if(pic.containsKey("content"))
        {
        	int width = 560;  
            int height = 350;  
            int picType = getPictureType(pic.get("type").toString());  
            byte[] byteArray = (byte[]) pic.get("content");  
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);  
            try {  
            	String ind = doc.addPictureData(byteInputStream,picType);  
                CTInline inline = cellR.getCTR().addNewDrawing().addNewInline();    
                doc.createPicture(doc.getAllPictures().size()-1, width , height,inline);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	public  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
            if ( cellIndex == fromCell ) {  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }  
	/***
     * <b>function:</b> 将数字转化为大写
     * @author luting
     * 2015-10-30
     * @param num 数字
     * @return 转换后的大写数字
     */
	public static String numToUpper(int num) {
		 String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		 String ss[] = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		 String s = String.valueOf(num);
		 StringBuffer sb = new StringBuffer();
		 for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
		 }
		 String sss = String.valueOf(sb);
		 int i = 0;
		 for (int j = sss.length(); j > 0; j--) {
			 sb = sb.insert(j, ss[i++]);
		 }
		 return sb.toString();
    }
	public static void main(String[] args) {
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			FileDocUtil ff= new FileDocUtil ();
			List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
			for(int i=0;i<5;i++)
			{
				Map<String, Object> mm = new HashMap<String, Object>();
				
				//图片
				Map<String,Object> header = new HashMap<String, Object>();  
			    header.put("width", 560);  
			    header.put("height", 350);  
			    header.put("type", "png");  
			    
			    URL url = new URL("http://127.0.0.1:8080/upload/photo/20160601130931751.jpg"); 
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
				conn.setRequestMethod("GET"); 
	            conn.setConnectTimeout(6000); 
				InputStream in = conn.getInputStream();
				byte[] buf = new byte[1024];  
				int size = 0;  
				BufferedInputStream bis = new BufferedInputStream(in);
				FileOutputStream out = new FileOutputStream("D:/1.JPG");
				while ((size = bis.read(buf)) != -1) {  
					out.write(buf, 0, size);  
				}  
			    header.put("content", FileDocUtil.inputStream2ByteArray(new FileInputStream("D:/1.JPG"), true));  
			    
			    mm.put("pic",header);  
				
				int index = i+1;
				mm.put("picName", "照片" + numToUpper(index));
				mm.put("picTime", "2016年1月11日");
				mm.put("picAdd", "星港街以西、中新大道西以南进行电力线路迁改施工现场");
				mm.put("picContent", "事发施工现场已经热熔对接加工好待排布的电缆导管，焊环高度、宽度及焊缝高度的状态。");
				newList.add(mm);
			}
			
			map.put("picList", newList);
			
			ff.createDocFile("D:/照片.docx", "照片1.docx", "D:", map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
