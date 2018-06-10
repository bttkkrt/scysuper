package com.jshx.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelTools {

	public static List<List<String>> parseExcel(File file,String type){
		List<List<String>> lists = new ArrayList<List<String>>();
			try {
				Workbook workbook = null;
				workbook = Workbook.getWorkbook(file);//解析文件
				Sheet sheet = workbook.getSheet(0);//获取sheet
				int row = sheet.getRows();//获取行数
				for(int i=1;i<row;i++){
					Cell[] cellsTmp = sheet.getRow(i);//得到某行的单元格数
					List<String> list = new ArrayList<String>();
					for(int j=0;j<cellsTmp.length;j++){//遍历获取单元格内容
						if(("0".equals(type) && (j == 6 || j == 8)) || ("1".equals(type) && (j== 10 || j == 11)) || ("2".equals(type) && (j == 1 || j == 12 || j ==14 || j == 15)))
						{
							if(cellsTmp[j].getType() == CellType.DATE)
							{
								 DateCell c00 = (DateCell)cellsTmp[j]; 
							     TimeZone tz = TimeZone.getTimeZone("GMT"); 
							     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
							     df.setTimeZone(tz) ; 
							     list.add(df.format(c00.getDate()));
							}
							else
							{
								list.add(cellsTmp[j].getContents());
							}
						}
						else{
							list.add(cellsTmp[j].getContents());
						}
					}
					lists.add(list);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lists;
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
