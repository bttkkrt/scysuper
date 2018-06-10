/**
 *
 */
package com.jshx.module.form.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.entity.JdbcParameter;
import com.jshx.core.base.service.BaseJdbcService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.RWXml;
import com.jshx.core.utils.SysPropertiesUtil;

import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;
import com.jshx.module.form.entity.FormTables;

import com.jshx.module.form.service.FormFieldService;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.form.service.FormTableService;
import com.jshx.module.form.service.generate.CodeGenerate;

import jxl.CellType;
import jxl.DateCell;
import jxl.Workbook;
import jxl.Cell;
import jxl.Sheet;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author caron
 * 
 */
@SuppressWarnings("serial")
public class FormTableAction extends BaseAction {

	private FormTable model;

	private String categoryNum;

	private String moveCategoryNum;

	private Pagination tablePage;

	private String thisTableIdString;

	@Autowired
	private FormTableService formTableService;

	/*
	 * 对JDBC调用采取的Service
	 */
	@Autowired
	private BaseJdbcService baseJdbcService;

	@Autowired
	private FormFieldService formFieldService;

	// @Autowired
	// private FormInCategoryManager formInCategoryManager;

	// @Autowired
	// private FormPageGroupService formPageGroupService;

	@Autowired
	private CodeGenerate codeGenerate;

	private int flag; // 操作标识 1保存成功 2修改成功

	private String tableIds;

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	private Integer[] sortSQs;

	private String modelphysicalName;

	public String getModelphysicalName() {
		return modelphysicalName;
	}

	public void setModelphysicalName(String modelphysicalName) {
		this.modelphysicalName = modelphysicalName;
	}

	private Integer[] pageTypes;

	private Integer[] displayInMenus;

	private String message;

	private File fileUpload;

	private Integer genType;

	private String flowType = "-1";

	private String tableName;

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = new File(fileUpload);
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String importFormInit() {
		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.core.web.AbstractActionSupport#save()
	 */

	String $xmlData$;

	String $layoutName$;

	public String get$xmlData$() {
		return $xmlData$;
	}

	public void set$xmlData$(String data$) {
		$xmlData$ = data$;
	}

	public String get$layoutName$() {
		return $layoutName$;
	}

	public void set$layoutName$(String name$) {
		$layoutName$ = name$;
	}

	public void getXMLData() {
		System.out.print($xmlData$);
	}

	public String save() throws Exception {
		//新建或修改表单信息
		if (null != model) {
			if ( null  != model.getId() && model.getId().length() != 0
					&& !model.getId().equals("-1")) {
				FormTable ft = formTableService.get(model.getId());
				ft.setDisplayInMenu(model.getDisplayInMenu());
				ft.setFileprexName(model.getFileprexName());
				ft.setGeneratePath(model.getGeneratePath());
				ft.setPageType(model.getPageType());
				ft.setProjectName(model.getProjectName());
				ft.setTableName(model.getTableName());
				ft.setTableWidth(model.getTableWidth());
				ft.setLabelWidth(model.getLabelWidth());
				ft.setTextWidth(model.getTextWidth());

				if (model.getSrcPackage() != null
						&& model.getSrcPackage().length() > 0) {
					ft.setSrcPackage("com.jshx." + model.getSrcPackage());
				} else {
					ft.setSrcPackage("com.jshx");
				}

				formTableService.save(ft);
				flag = 2;

				message = "修改成功！";

			} else {
				int maxSortSQ = formTableService.getMaxSortSQ();
				model.setSortSQ(maxSortSQ + 1);

				if (formTableService
						.createTable(model.getPhysicalName().trim())) {
					model.setPhysicalName(model.getPhysicalName());
					if (model.getSrcPackage() != null
							&& model.getSrcPackage().length() > 0) {
						//包名必须以com.jshx开头
						model
								.setSrcPackage("com.jshx."
										+ model.getSrcPackage());
					} else {
						model.setSrcPackage("com.jshx");
					}
					model.setId(null);
					formTableService.save(model);
				} else {
					BasalException ex = new BasalException(BasalException.NO, "创建表失败");
					
					throw ex;
				}
				flag = 1;
				thisTableIdString = model.getId().toString();

				// Insert Record (PK Row_ID) for formFieldInfo
				FormField ff = getRowId(model);
				formFieldService.save(ff);
				model = null;
				message = "新建成功！";
			}
		}
		return SUCCESS;
	}

	public void delete() throws Exception {
		//删除表单信息
		String[] tableIdArray = tableIds.split("\\|");
		for (int i = 0; i < tableIdArray.length; i++) {
			String tableId = tableIdArray[i];
			if (tableId == null || tableId.trim().equals(""))
				continue;
			model = formTableService.get(tableId);
			try {
				List<FormField> ffList = formFieldService.getAllField(tableId);
				for (FormField formField : ffList) {
					formFieldService.delete(formField.getId());
				}
				formTableService.delete(tableId); // 删除表记录
				if(flag==1)
					formTableService.dropTable(model.getPhysicalName()); // 删除实体表
				
			} catch (Exception e) {
				try {
					getResponse().getWriter().println("{\"result\":\"false\"}");
				} catch (Exception ex) {

				}

			}
		}
		try {
			getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {

		}
	}

	public void deleteWithoutDrop(String tableId) throws Exception {
		//不删除物理表只清楚表单信息
		model = formTableService.get(tableId);
		try {
			List<FormField> ffList = formFieldService.getAllField(tableId);
			for (FormField formField : ffList) {
				formFieldService.delete(formField.getId());
			}
			formTableService.delete(tableId); // 删除表记录

		} catch (Exception e) {
			message = "tableDelError";

		}

	}

	/**
	 * 获取表单信息
	 * @return
	 * @throws Exception
	 */
	public String get() throws Exception {
		
		if (model != null && model.getId() != null) {
			if (!model.getId().equals("-1")) {
				if(model.getId().contains(",")){
					String id = model.getId().split(",")[0];
					model.setId(id);
				}
				model = formTableService.get(model.getId());
				
				if (model.getSrcPackage() != null
						&& model.getSrcPackage().length() > 8) {
					model.setSrcPackage(model.getSrcPackage().substring(9));
				} else {
					model.setSrcPackage("");
				}
			} else {
				model = formTableService.get(model.getId());
			}

		}
		return SUCCESS;
	}

	public String initList() {
		return SUCCESS;
	}

	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public void list() throws Exception {
		tablePage = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if ((null != categoryNum) && (false == "00".equals(categoryNum))
				&& (false == "0".equals(categoryNum))
				&& (false == "".equals(categoryNum.trim()))) {
			paraMap.put("categoryNum", categoryNum + "%");
		} else if ("00".equals(categoryNum)) {
			// categoryNum为00表示未分类
			paraMap.put("categoryNum00", "");
		} else {
			// categoryNum为null或0表示全部
			paraMap.put("categoryNumnull", "1");
		}

		if (model != null) {

			if (model.getTableName() != null
					&& !model.getTableName().trim().equals("")) {
				paraMap.put("tableName", "%" + model.getTableName() + "%");
			}
			if (model.getPhysicalName() != null
					&& !model.getPhysicalName().trim().equals("")) {
				paraMap
						.put("physicalName", "%" + model.getPhysicalName()
								+ "%");
			}
		}
		// hql.append(" order by t.sortSQ");

		tablePage = formTableService.findTableByPage(tablePage, paraMap);
		
		outputJsonList(tablePage.getTotalCount(),"physicalName|tableName|sortSQ|id|",tablePage.getListOfObject());
		
		
	}

	/**
	 * 导出XML
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public void exportXML() throws Exception {
		InputStream instream = null;
		try {
			String[] tableIdArray = tableIds.split("\\|");
			FormTables tables = new FormTables();
			for (int i = 0; i < tableIdArray.length; i++) {
				String tableId = tableIdArray[i];
				if (tableId == null || tableId.trim().equals(""))
					continue;
				model = formTableService.get(tableId);
				tables.getTables().add(model);
			}

			String xmlFilePath = RWXml.objectsToXml(tables, FormTables.class,
					FormTable.class, FormField.class);
			String xmlFileName = null;
			File xmlFile = null;

			if (xmlFilePath != null)
				xmlFileName = xmlFilePath.substring(xmlFilePath
						.lastIndexOf('\\') + 1, xmlFilePath.length());

			if (xmlFileName != null)
				xmlFile = new File(xmlFilePath);

			if ((xmlFile != null) && xmlFile.exists() && xmlFile.isFile()) {
				instream = new FileInputStream(xmlFile);

				Struts2Util.getResponse().reset();
				Struts2Util.getResponse().setContentType(
						"application/x-msdownload");
				Struts2Util.getResponse().setContentLength(
						(int) xmlFile.length());
				Struts2Util.getResponse().addHeader("content-disposition",
						"attachment; filename=\"" + xmlFileName + "\"");

				byte[] b = new byte[4096];
				int len;
				while ((len = instream.read(b)) > 0) {
					Struts2Util.getResponse().getOutputStream()
							.write(b, 0, len);
				}
				Struts2Util.getResponse().getOutputStream().flush();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入XML
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public String importXML() throws Exception {

		final int BUFFER_SIZE = 16 * 1024;

		InputStream in = null;
		OutputStream out = null;

		String destFilePath;

		try {
			destFilePath = Struts2Util.getServletContext().getRealPath("/")
					+ "temp\\" + this.fileUpload.getName();
			File destFile = new File(destFilePath);

			in = new BufferedInputStream(new FileInputStream(fileUpload),
					BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(destFile),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != in) {
				try {
					in.close();
				} catch (IOException ee) {
					ee.printStackTrace();
					this.setMessage("文件上传失败！");

					return SUCCESS;
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException ee) {
					ee.printStackTrace();
					this.setMessage("文件上传失败！");

					return SUCCESS;
				}
			}
			this.setMessage("文件上传失败！");

			return SUCCESS;
		}

		FormTables tables;

		try {
			tables = (FormTables) RWXml.xmlToObjects(destFilePath,
					FormTables.class, FormTable.class, FormField.class);
		} catch (JAXBException e1) {
			e1.printStackTrace();
			this.setMessage("解析XML文件失败，请检查文件的内容！");

			return SUCCESS;
		}

		if (tables == null) {
			this.setMessage("导入XML文件失败，请重新导入！");

			return SUCCESS;
		}

		try {
			FormTable table;
			for (int i = 0; i < tables.getTables().size(); i++) {
				table = tables.getTables().get(i);
				FormTable tt = formTableService.get(table.getId());

				if (tt == null) {
					table.setId(null);
				}
				if (table.getFieldList() != null) {
					for (int j = 0; j < table.getFieldList().size(); j++) {
						table.getFieldList().get(j).setTable(table);
						if (tt == null) {
							table.getFieldList().get(j).setId(null);
						}
					}
				}
				this.formTableService.saveOrUpdate(table);
			}

		} catch (Exception e) {
			this.setMessage("导入XML文件失败，请重新导入！");

			return SUCCESS;
		}

		this.setMessage("导入XML文件成功！");

		return SUCCESS;
	}

	/**
	 * 导入Excel到数据库
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */

	public String importExcel() throws Exception {
		InputStream inStream = null;
		OutputStream out = null;
		String excelPath;

		final int BUFFER_SIZE = 16 * 1024;
		File exlFile;
		try {
			excelPath = Struts2Util.getServletContext().getRealPath("/")
					+ "temp\\" + this.fileUpload.getName();
			exlFile = new File(excelPath);
			/*
			 * if(exlFile.exists()) { exlFile.delete();//如果文件已经存在则删除 }
			 */
			inStream = new BufferedInputStream(new FileInputStream(fileUpload),
					BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(exlFile),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = inStream.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != inStream) {
				try {
					inStream.close();
				} catch (IOException ee) {
					ee.printStackTrace();
					this.setMessage("文件上传失败！");
					return SUCCESS;
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException ee) {
					ee.printStackTrace();
					this.setMessage("文件上传失败！");
					return SUCCESS;
				}
			}
			this.setMessage("文件上传失败！");
			return SUCCESS;
		}
		try {
			/*
			 * inStream=new FileInputStream(excelPath); Workbook
			 * wkb=Workbook.getWorkbook(inStream); Sheet
			 * sht=(Sheet)wkb.getSheet(0);
			 */
			boolean isExist = false;
			int row = 2;
			String[] tableIdArray = tableIds.split("\\|");
			for (int n = 0; n < 1; n++) {
				String tableId = tableIdArray[n];
				if (tableId == null || tableId.trim().equals(""))
					continue;
				FormTable ft = new FormTable();
				ft = formTableService.get(tableId);

				Workbook wbk = Workbook.getWorkbook(exlFile);
				Sheet sht = wbk.getSheet(0);
				// int r=sht.getRows();//获取所有行
				int c = sht.getColumns();// 获取所有列

				Cell[] cells;
				boolean isNull = false;// 用于判断是否存在空行
				int i = 0;
				while (isNull != true) {
					i++;
					cells = sht.getRow(i);
					for (int j = 0; j < 3; j++) {
						if (null == cells[j].getContents()
								|| cells[j].getContents().trim().length() == 0) {
							isNull = true;
							break;
						}
					}
					if (isNull == true) {
						break;
					} else {
						if (!isExistField(tableId, cells[0].getContents(),
								cells[1].getContents())) {
							FormField entity = new FormField();
							entity.setTable(ft);
							entity.setFieldDisplayName(cells[0].getContents()
									.trim());
							entity.setFieldName(cells[1].getContents().trim());
							entity.setFieldType(cells[2].getContents().trim());
							// 提供默认值
							entity.setDispInGrid(1);
							entity.setDispInDetail(1);
							entity.setIsQueryCondition(0);
							entity.setIsSortField(0);
							entity.setFieldLength(255);
							entity.setUniqueField(0);
							entity.setSortDirection("asc");
							// 取Excel中实际值
							if (cells[3].getContents().trim().length() != 0) {
								entity.setDispInGrid(Integer.parseInt(cells[3]
										.getContents().trim().substring(0, 1)));
							}
							if (cells[4].getContents().trim().length() != 0) {
								entity.setDispInDetail(Integer
										.parseInt(cells[4].getContents().trim()
												.substring(0, 1)));
							}
							if (cells[5].getContents().trim().length() != 0) {
								entity.setIsQueryCondition(Integer
										.parseInt(cells[5].getContents().trim()
												.substring(0, 1)));
							}
							if (cells[6].getContents().trim().length() != 0) {
								entity.setIsSortField(Integer.parseInt(cells[6]
										.getContents().trim().substring(0, 1)));
							}
							if (cells[7].getContents().trim().length() != 0) {
								entity.setFieldLength(Integer.parseInt(cells[7]
										.getContents().trim()));

							}
							/*
							 * if(cells[8].getContents().trim().length()==0) {
							 * entity
							 * .setFieldLength(Integer.parseInt(cells[8].getContents
							 * ().trim().substring(0, 1))); }
							 * if(cells[9].getContents().trim().length()==0) {
							 * entity
							 * .setSortDirection(cells[9].getContents().trim
							 * ().substring(3)); }
							 */
							entity.setGridMultiRows(1);
							entity.setDecimalLength(0);

							entity.setDataSource("Blank");
							entity.setFieldDefaultValue("-1");

							int maxSortSQ = formFieldService
									.getMaxSortSQ(tableId);
							entity.setSortSQ(maxSortSQ + 1);
							entity.setDataSourceType("-1");

							formFieldService.addColumn(tableId, entity);
						} else {
							isExist = true;
							row = i + 1;
							break;
						}
					}
				}
				wbk.close();
				exlFile.deleteOnExit();
			}

			if (!isExist) {
				this.setMessage("导入Excel文件成功！");
			} else {
				this.setMessage("导入数据错误，第" + row + "行存在同名字段！");
				return SUCCESS;

			}
		} catch (Exception E) {
			E.printStackTrace();
			this.setMessage("导入Excel文件失败，请重新导入！");
			return SUCCESS;
		}

		return SUCCESS;
	}

	/**
	 * 判断表中是否已经存在字段
	 * 
	 * @param tableId
	 * @param fieldDisplayName
	 * @param fieldName
	 * @return
	 */
	public boolean isExistField(String tableId, String fieldDisplayName,
			String fieldName) {
		boolean isExist = false;

		List<FormField> fieldList = formFieldService.getAllField(tableId);
		for (FormField f : fieldList) {
			if (f.getFieldName().trim().equals(fieldName.trim())
					|| f.getFieldDisplayName().trim().equals(
							fieldDisplayName.trim())) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 类似创建
	 * 
	 * @return
	 */
	public String similar() throws Exception {
		FormTable table = formTableService.get(model.getId());
		formTableService.createCopyTable(table.getPhysicalName(), model
				.getPhysicalName());

		model.setPhysicalName(model.getPhysicalName());
		int maxSortSQ = formTableService.getMaxSortSQ();
		model.setSortSQ(maxSortSQ + 1);

		// 类似创建时对应的路径和包名按原表
		model.setProjectName(table.getProjectName());
		model.setSrcPackage(table.getSrcPackage());

		model.setId(null);
		formTableService.save(model);
		List<FormField> fieldList = formFieldService.getAllField(table.getId());
		for (FormField field : fieldList) {
			FormField copyfield = (FormField) copyPropertysWithoutNull(field);
			copyfield.setTable(model);
			copyfield.setId(null);
			formFieldService.save(copyfield);
		}
		message = "类似创建数据表成功！";

		model.setSrcPackage(model.getSrcPackage().substring(9));

		return SUCCESS;
	}

	public Object copyPropertysWithoutNull(Object obj) throws Exception {
		Class classType = obj.getClass();

		// 通过默认构造方法去创建一个新的对象，getConstructor的视其参数决定调用哪个构造方法
		Object objectCopy = classType.getConstructor(new Class[] {})
				.newInstance(new Object[] {});

		// 获得对象的所有属性
		Field[] fields = classType.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			// 获取数组中对应的属性
			Field field = fields[i];
			String fieldName = field.getName();
			if (fieldName.equals("serialVersionUID")) {
				continue;
			}
			String stringLetter = fieldName.substring(0, 1).toUpperCase();

			// 获得相应属性的getXXX和setXXX方法名称
			String getName = "get" + stringLetter + fieldName.substring(1);
			String setName = "set" + stringLetter + fieldName.substring(1);

			// 获取相应的方法
			Method getMethod = classType.getMethod(getName, new Class[] {});
			Method setMethod = classType.getMethod(setName, new Class[] { field
					.getType() });

			// 调用源对象的 getXXX（）方法
			Object value = getMethod.invoke(obj, new Object[] {});
			// System.out.println(fieldName+" :"+value);

			// 调用拷贝对象的setXXX（）方法
			setMethod.invoke(objectCopy, new Object[] { value });

		}

		return objectCopy;
	}

	/**
	 * 准备导入数据库中存在的数据表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getimportForm() throws Exception {
		tablePage = new Pagination(super.getRequest());
		formTableService.getImportForm(tablePage, modelphysicalName);

		
		outputJsonList(tablePage.getTotalCount(), "", tablePage.getListOfObject());
		
		
		
	}

	/**
	 * 导入数据库中存在的数据表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void importForm() throws Exception {
		try {
			if (ids != null) {
				String[] idArray = ids.split("\\|");
				for (String phyName : idArray) {
					if (phyName == null || phyName.trim().equals(""))
						continue;
					// 将该表名对应的表纳入管理
					// 1.查出该表名对应的表信息保存入table_info表

					// 将该表纳入管理
					FormTable ft = new FormTable();
					ft.setPhysicalName(phyName);
					ft.setProjectName("temp");
					ft.setSrcPackage("com.jshx.temp");
					ft.setTableName(phyName);
					ft.setSortSQ(formTableService.getMaxSortSQ() + 1);
					formTableService.save(ft);

					// 2.从user_table_collumn中读取该表的字段信息，保存入field_info表
					List<FormField> ffList = formFieldService
							.getFieldByPhyName(phyName);
					for (FormField formField : ffList) {

						formField.setTable(ft);

					}
					ffList.add(getRowId(ft));
					formFieldService.saveList(ffList);
				}
			}
			try {
				getResponse().getWriter().println("{\"result\":\"true\"}");
			} catch (Exception e) {

			}
		} catch (Exception e) {
			try {
				getResponse().getWriter().println("{\"result\":\"false\"}");
			} catch (Exception ex) {

			}
		}

	}

	/**
	 * 初始化RowID字段
	 * @param ft
	 * @return
	 */
	private FormField getRowId(FormTable ft) {
		FormField ff = new FormField();
		ff.setDataSource("Blank");
		ff.setDataSourceType("-1");
		ff.setDecimalLength(0);
		ff.setDispInGrid(0);
		ff.setFieldDefaultValue("-1");
		ff.setFieldDisplayName("主键");
		ff.setFieldDisplayType("TextBox");
		ff.setFieldLength(50);
		ff.setFieldName("ROW_ID");
		ff.setFieldType("varchar");
		ff.setMustFill(0);
		ff.setGridMultiRows(1);
		ff.setValueUrl("无");
		ff.setDispInDetail(0);
		ff.setTable(ft);
		ff.setSortSQ(0);
		return ff;
	}

	public String getimportExcel() throws Exception {

		return SUCCESS;
	}

	public void getExceltemplate() throws Exception {

		Struts2Util.getResponse().setContentType("application/vnd.ms-excel");
		Struts2Util.getResponse().addHeader("Content-Disposition",
				"attachment;   filename=\"template.xls\"");
		OutputStream os = Struts2Util.getResponse().getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		String tableId = Struts2Util.getRequest().getParameter("tableId");
		FormTable ft = formTableService.get(tableId);
		if (ft == null) {
			// 抛出该表不存在的异常
			BasalException ex = new BasalException(BasalException.NO, "请指定要导出的数据表");
			throw ex;
		}
		// 新建一张表
		WritableSheet wsheet = wwb.createSheet("template", 0);
		Label label = new Label(0, 0, "");

		wsheet.addCell(label);

		int ii = 0;
		for (FormField ff : ft.getFieldList()) {
			if (ff.getFieldName().equalsIgnoreCase("ROW_ID")) {
				continue;
			}
			if (ff.getFieldType().equals("varchar")
					|| ff.getFieldType().equals("int")
					|| ff.getFieldType().equals("number")
					|| ff.getFieldType().equals("date")) {
				label = new Label(ii, 0, ff.getFieldDisplayName());
				wsheet.addCell(label);
				ii++;
			}
		}
		wwb.write();
		wwb.close();
		os.close();
		Struts2Util.getResponse().flushBuffer();

	}

	/**
	 * 通用导出Excel
	 * @return
	 * @throws Exception
	 */
	public String importExcelforCommon() throws Exception {

		FormTable ft = formTableService.get(thisTableIdString);
		if (ft == null) {
			// 抛出该表不存在的异常
			BasalException ex = new BasalException(BasalException.NO, "请指定要导出的数据表");			
			throw ex;
		}

		try {

			Workbook book = Workbook.getWorkbook(fileUpload);
			// 取得第一个sheet
			Sheet sheet = book.getSheet(0);
			// 取得行数
			int rows = sheet.getRows();
			if (rows > 1000) {
				// 抛出不能一次导出1k行的异常
				BasalException ex = new BasalException(BasalException.NO, "不能一次导入超出1000行");
				
				throw ex;
			}
			if (rows < 2) {
				// 抛出不存在数据的异常
				BasalException ex = new BasalException(BasalException.NO, "表中不存在数据");
				
				throw ex;
			}
			Cell[] cellList = sheet.getRow(0);
			int columns = cellList.length;
			HashMap<Integer, String> fieldmap = new HashMap<Integer, String>();
			for (int j = 0; j < cellList.length; j++) {
				String cellString = cellList[j].getContents().trim()
						.toUpperCase();
				boolean isExistField = false;
				for (FormField ff : ft.getFieldList()) {
					if (ff.getFieldDisplayName().trim().equalsIgnoreCase(
							cellString)
							|| ff.getFieldName().equalsIgnoreCase(cellString)) {
						cellString = ff.getFieldName();
						isExistField = true;
						break;
					}
				}
				if (!isExistField) {
					BasalException ex = new BasalException(BasalException.NO, "Excel表中存在与数据表字段无关的列");
					
					throw ex;
				}

				if (fieldmap.containsValue(cellString)) {
					BasalException ex = new BasalException(BasalException.NO, "Excel表中存在重复的列");
					throw ex;
				}
				fieldmap.put(j, cellString);
			}
			if (!fieldmap.isEmpty()) {
				List<JdbcParameter> paramList = new ArrayList<JdbcParameter>();
				boolean isRowIdExisted = false;
				boolean isCREATETIMEExisted = false;
				boolean isCREATEUSERIDExisted = false;
				boolean isRDEPTCODEExisted = false;
				if (fieldmap.containsValue("ROW_ID")) {
					isRowIdExisted = true;
				}
				if (fieldmap.containsValue("CREATETIME")) {
					isCREATETIMEExisted = true;
				}
				if (fieldmap.containsValue("CREATEUSERID")) {
					isCREATEUSERIDExisted = true;
				}
				if (fieldmap.containsValue("DEPTID")) {
					isRDEPTCODEExisted = true;
				}
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 1; i < rows; i++) {
					JdbcParameter jp = new JdbcParameter();
					jp.setTableName(ft.getPhysicalName());
					jp.setBlobMap(null);
					jp.setClobMap(null);
					Map<String, Object> paraMap = new HashMap<String, Object>();
					Cell[] cellListi = sheet.getRow(i);
					for (int j = 0; j < columns; j++) {
						if (cellListi[j].getType().equals(CellType.DATE)) {
							DateCell dc = (DateCell) cellListi[j];
							// 对时间型，日期型等特殊字段做处理
							paraMap.put(fieldmap.get(j),
									new java.sql.Timestamp(convertDate4JXL(
											dc.getDate()).getTime()));
						} else {
							String cellString = cellListi[j].getContents()
									.trim();
							paraMap.put(fieldmap.get(j), cellString);
							// try
							// {
							// double d = Double.valueOf(cellString);
							// //对数字型做特殊处理,不需要
							// paraMap.put(fieldmap.get(j), d);
							// }
							// catch(Exception e)
							// {
							// paraMap.put(fieldmap.get(j), cellString);
							// }
						}
					}
					// 如果不存在ROW_ID等保留字段，手动创建
					if (!isRowIdExisted) {
						paraMap.put("ROW_ID", java.util.UUID.randomUUID()
								.toString());
					}
					if (!isCREATETIMEExisted) {
						paraMap.put("CREATETIME", new java.sql.Timestamp(System
								.currentTimeMillis()));
					}
					if (!isCREATEUSERIDExisted) {
						paraMap.put("CREATEUSERID", getLoginUserId());
					}
					if (!isRDEPTCODEExisted) {
						paraMap.put("DEPTID", getLoginUserDepartmentId());
					}
					jp.setParaMap(paraMap);
					paramList.add(jp);
				}
				baseJdbcService.batchInsert(paramList);
			}

			book.close();
		} catch (Exception e) {
			throw e;
		}

		message = "导入数据成功！";
		return SUCCESS;
	}

	/**
	 * JXL中通过DateCell.getDate()获取单元格中的时间为（实际填写日期+8小时），原因是JXL是按照GMT时区来解析XML。
	 * 本方法用于获取单元格中实际填写的日期！ 例如单元格中日期为“2009-9-10”，getDate得到的日期便是“Thu Sep 10
	 * 08:00:00 CST 2009”； 单元格中日期为“2009-9-10 16:00:00”，getDate得到的日期便是“Fri Sep 11
	 * 00:00:00 CST 2009”
	 * 
	 * @author XHY
	 * @param jxlDate
	 *            通过DateCell.getDate()获取的时间
	 * @return
	 * @throws ParseException
	 */
	public static java.util.Date convertDate4JXL(java.util.Date jxlDate)
			throws ParseException {
		if (jxlDate == null)
			return null;
		TimeZone gmt = TimeZone.getTimeZone("GMT");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		dateFormat.setTimeZone(gmt);
		String str = dateFormat.format(jxlDate);
		TimeZone local = TimeZone.getDefault();
		dateFormat.setTimeZone(local);
		return dateFormat.parse(str);
	}

	/**
	 * 重建表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void reCreateTable() throws Exception {
		try {
			formTableService.reCreateTable(tableIds);
			try {
				getResponse().getWriter().println("{\"result\":\"true\"}");
			} catch (Exception e) {

			}
		} catch (Exception ex) {
			try {
				getResponse().getWriter().println("{\"result\":\"false\"}");
			} catch (Exception e) {

			}
		}

	}

	/**
	 * 修改表的类别
	 * 
	 * @return
	 * @throws Exception
	 */
	public void move() throws Exception {

		String[] tempIds = tableIds.split("\\|");
		try {
			// 表单类别
			formTableService.move(tempIds, moveCategoryNum);
			try {
				getResponse().getWriter().println("{\"result\":\"true\"}");
			} catch (Exception e) {

			}
		} catch (Exception ex) {
			try {
				getResponse().getWriter().println("{\"result\":\"false\"}");
			} catch (Exception e) {

			}
		}

	}
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 生成代码
	 * 
	 * @return
	 * @throws Exception
	 */
	public void genCode() throws Exception {
		String isDirect = SysPropertiesUtil.getProperty("isDirect");
		int i = (int) (Math.random() * 10000);
		
		 String dateFormat = sf.format(DateUtil.getSystemTimestamp());
			
		String randomFolderName = Struts2Util.getServletContext().getRealPath(
				"/")
				+ "temp"
				+ File.separator
				+dateFormat+"-"+ i;
		
		String[] formList = tableIds.split("\\|");
		for (String string : formList) {
			String tableId = string;
			if (tableId != null && tableId.length() > 1) {
				FormTable formTable = formTableService.get(tableId);
				//为每张表创建一个文件夹
				String fName = randomFolderName+File.separator+UUID.randomUUID();
				codeGenerate.genCode(formTable, fName);
				String downPath = fName + File.separator + formTable.getProjectName();

				if ("1".equals(isDirect)) {
					message = formTableService.copyAll2projectPath(downPath);
					if (!message.equals("代码已复制到开发环境目录！"))
						break;

					// "";
				}
			}
		}

		if (!"1".equals(isDirect)) {
			formTableService.downFilePath(randomFolderName);
		}
		
	}

	public String genALLCode() {

		return "success";

	}
	
	public void tableIsExist(){
		boolean flag = true;
		String oldTableName = this.model.getPhysicalName();
		String physicalName = this.getRequestParameter("param");
		String tableId = this.model.getId();
	    
		if(!"".equals(oldTableName) && oldTableName.equals(physicalName) && !"".equals(tableId)){

		}else{
			if(formTableService.isExist(physicalName)){
				flag = false;
			}
		}
		
		try{
			if(flag==false){
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"该存储表名不符合命名规范或者在数据库中已存在！\"}");
			}else{
				getResponse().getWriter().println("{\"status\":\"y\"}");
			}
		}catch(Exception e){
		}		
	}

	public Integer[] getSortSQs() {
		return sortSQs;
	}

	public void setSortSQs(Integer[] sortSQs) {
		this.sortSQs = sortSQs;
	}

	public Integer[] getPageTypes() {
		return pageTypes;
	}

	public void setPageTypes(Integer[] pageTypes) {
		this.pageTypes = pageTypes;
	}

	public Integer[] getDisplayInMenus() {
		return displayInMenus;
	}

	public void setDisplayInMenus(Integer[] displayInMenus) {
		this.displayInMenus = displayInMenus;
	}

	public FormTable getModel() {
		return model;
	}

	public void setModel(FormTable model) {
		this.model = model;
	}

	public String getTableIds() {
		return tableIds;
	}

	public void setTableIds(String tableIds) {
		this.tableIds = tableIds;
	}

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getMoveCategoryNum() {
		return moveCategoryNum;
	}

	public void setMoveCategoryNum(String moveCategoryNum) {
		this.moveCategoryNum = moveCategoryNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getGenType() {
		return genType;
	}

	public void setGenType(Integer genType) {
		this.genType = genType;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getThisTableIdString() {
		return thisTableIdString;
	}

	public void setThisTableIdString(String thisTableIdString) {
		this.thisTableIdString = thisTableIdString;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void searchFormTable() throws Exception {
		// System.out.println(tableName);
		// if(tableName!=null){
		// tableName = new String(tableName.getBytes("ISO8859-1"),"UTF-8");
		// }
		// System.out.println(tableName);
		List<FormTable> tableList = formTableService
				.queryFormTableByName(tableName);
		System.out.println(tableList.size());
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("{jsonStr:[");
		int i = 0;
		for (FormTable table : tableList) {
			jsonStr.append("{tableId:\"");
			jsonStr.append(table.getId());
			jsonStr.append("\",tableName:\"");
			jsonStr.append(table.getTableName());
			jsonStr.append("\"}");
			if (i < tableList.size() - 1)
				jsonStr.append(",");
			i++;
		}
		jsonStr.append("]}");
		System.out.println(jsonStr);
		Struts2Util.getResponse().getWriter().println(jsonStr.toString());

	}

	public Pagination getTablePage() {
		return tablePage;
	}

	public void setTablePage(Pagination tablePage) {
		this.tablePage = tablePage;
	}

}
