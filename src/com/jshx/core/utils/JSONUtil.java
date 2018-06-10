package com.jshx.core.utils;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
/**
 * JSON工具类，将json数据转为PDF或Excel文件
 * 
 * @author Chenjian
 *
 */
public class JSONUtil {

	/**
	 * 输出PDF
	 * 
	 * @param jsonData 列表的json字符串
	 * @param cols 导出列名和字段ID
	 * @param os
	 * @param codeMap
	 * @param codeFields 一维代码对应的字段名，多个以“,”隔开，且顺序与codeId一致
	 * @throws Exception
	 */
	public static void changeJSONtoPdf(String jsonData,
			Map<String, String> cols, OutputStream os,
			Map<String, String>[] codeMap, String[] codeFields) throws Exception {

		int colLength = cols.size();
		String[] headers = new String[colLength];
		String[] colNames = new String[colLength];
		Iterator<String> keyIt = cols.keySet().iterator();
		int i = 0;
		while (keyIt.hasNext()) {
			String header = keyIt.next();
			headers[i] = header;
			colNames[i] = cols.get(header);
			i++;
		}

		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		PdfWriter writer = PdfWriter.getInstance(document, os);
		document.open();

		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
		Paragraph context = new Paragraph();
		context.setFont(fontChinese);

		Table t = new Table(colLength);
		t.setPadding(0);
		t.setSpacing(0);
		t.setBorderWidth(1);

		Cell cell;
		// 输出列名字段
		for (i = 0; i < colLength; i++) {
			cell = new Cell(new Phrase(headers[i], fontChinese));
			cell.setHeader(true);
			t.addCell(cell);
		}
		t.endHeaders();

		JsonParser jsonParser = new JsonParser();
		JsonArray array = jsonParser.parse(jsonData).getAsJsonArray();
		int pos = -1;
		boolean flag = false;
		for (JsonElement e : array) {
			JsonObject o = e.getAsJsonObject();
			for (i = 1; i <= colLength; i++) {
				String colName = colNames[i - 1];
				if(colName.indexOf(".")==-1){
					JsonElement e1 = o.get(colNames[i - 1]);
					if (e1 != null) {
						try {
							JsonObject o1 = e1.getAsJsonObject();
							if (o1 != null) {
								if(codeFields!=null)
								for(String codeField : codeFields){
									pos++;
									if(codeField.equals(colNames[i - 1])){
										flag = true;
										break;
									}
								}
								if(pos!=-1 && flag){
									cell = new Cell(new Phrase(codeMap[pos].get(o1.getAsString()),
											fontChinese));
									t.addCell(cell);
								}else{
									cell = new Cell(new Phrase(o1.getAsString(),
											fontChinese));
									t.addCell(cell);
								}
								pos = -1;
								flag = false;
							}
						} catch (Exception ex) {
							if(codeFields!=null)
							for(String codeField : codeFields){
								pos++;
								if(codeField.equals(colName)){
									flag = true;
									break;
								}
							}
							try{
								if(pos!=-1 && flag){
									cell = new Cell(new Phrase(codeMap[pos].get(e1.getAsString()),
											fontChinese));
								}else{
									cell = new Cell(new Phrase(e1.getAsString(),
											fontChinese));
								}
							}catch(Exception exe){
								cell = new Cell();
							}
							t.addCell(cell);
							pos = -1;
							flag = false;
						}
					} else {
						t.addCell("");
					}
				}else{
					cell = new Cell(new Phrase(getChildText(colName,o,codeMap,codeFields),
							fontChinese));
					t.addCell(cell);
				}
			}
		}
		context.add(t);
		document.add(context);
		document.close();
		writer.close();
	}

	/**
	 * 输出Excel
	 * 
	 * @param jsonData 列表的json字符串
	 * @param cols 导出列名和字段ID
	 * @param os
	 * @param codeMap
	 * @param codeFields 一维代码对应的字段名，多个以“,”隔开，且顺序与codeId一致
	 * @throws Exception
	 */
	public static HSSFWorkbook changeJSONtoEXcel(String jsonData,
			Map<String, String> cols, Map<String, String>[] codeMap,
			String[] codeFields) {
		int colLength = cols.size();
		String[] headers = new String[colLength];
		String[] colNames = new String[colLength];
		Iterator<String> keyIt = cols.keySet().iterator();
		int i = 0;
		while (keyIt.hasNext()) {
			String header = keyIt.next();
			headers[i] = header;
			colNames[i] = cols.get(header);
			i++;
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;
		// 输出列名字段
		for (i = 0; i < colLength; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}
		int rows = 1;

		JsonParser jsonParser = new JsonParser();
		JsonArray array = jsonParser.parse(jsonData).getAsJsonArray();
		int pos = -1;
		boolean flag = false;
		for (JsonElement e : array) {
			  if (rows > 50000){
		        sheet = wb.createSheet();
		        rows = 0;
		      }
			row = sheet.createRow(rows);
			JsonObject o = e.getAsJsonObject();
			for (i = 0; i < colLength; i++) {
				cell = row.createCell(i);
				String colName = colNames[i];
				if(colName.indexOf(".")==-1){
					//没有下层
					JsonElement e1 = o.get(colName);
					if (e1 != null) {
						try {
							JsonObject o1 = e1.getAsJsonObject();
							if (o1 != null){
								if(codeFields!=null)
								for(String codeField : codeFields){
									pos++;
									if(codeField.equals(colName)){
										flag = true;
										break;
									}
								}
								
								if(pos!=-1 && flag)
									cell.setCellValue(codeMap[pos].get(o1.getAsString()));
								else
									cell.setCellValue(o1.getAsString());
								pos = -1;
								flag = false;
							}
						} catch (Exception ex) {
							if(codeFields!=null)
							for(String codeField : codeFields){
								pos++;
								if(codeField.equals(colName)){
									flag = true;
									break;
								}
							}
							try{
							if(pos!=-1 && flag)
								cell.setCellValue(codeMap[pos].get(e1.getAsString()));
							else
								cell.setCellValue(e1.getAsString());
							}catch(Exception exe){
								cell.setCellValue("");
							}
							pos = -1;
							flag = false;
						}
					}
				}else{
					cell.setCellValue(getChildText(colName,o,codeMap,codeFields));
				}				
			}
			rows++;
		}

		return wb;
	}
	
	private static String getChildText(String field, JsonObject o,Map<String, String>[] codeMap,
			String[] codeFields){
		JsonElement e = null;
		String[] fields = field.split("\\.");
		for(int i=0;i<fields.length;i++){
			e = o.get(fields[i]);
			try{
				o = e.getAsJsonObject();
			}catch(Exception ex){
				
			}
		}
		if(e!=null){
			int pos = -1;
			boolean flag = false;
			if(codeFields!=null)
			for(String codeField : codeFields){
				pos++;
				if(codeField.equals(field)){
					flag = true;
					break;
				}
			}
			
			try{
				if(pos!=-1 && flag)
					return codeMap[pos].get(e.getAsString());
				else
					return e.getAsString();
			}catch(Exception ex){
				return null;
			}
		}
		else
			return null;
	}

}
