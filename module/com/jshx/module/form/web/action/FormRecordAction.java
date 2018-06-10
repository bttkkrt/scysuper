package com.jshx.module.form.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.entity.JdbcParameter;
import com.jshx.core.base.service.BaseJdbcService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.entity.Attachfiles;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.module.form.service.FormFieldService;
import com.jshx.module.form.service.FormTableService;

@Service("formRecordAction")
public class FormRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 对字段表操作用的Service
	 */
	@Autowired
	private FormFieldService formFieldService;

	/**
	 * 对表单表操作用的Service
	 */
	@Autowired
	private FormTableService formTableService;

	/**
	 * 对JDBC调用采取的Service
	 */
	@Autowired
	private BaseJdbcService baseJdbcService;
	
	/**
	 * 附件管理Service
	 */
	@Autowired
	private AttachfileService attachfileManager;

	/**
	 * 一维代码表Service
	 */
	@Autowired
	private CodeService codeService;

	/**
	 *当前操作的表单ID
	 */
	private String tableId;
	
	/**
	 *表单物理名的规则化表示
	 */
	private String tablePhyName;
	
	//排序列名
	private String orderbywhat;
	
	//排序方式
	private String orderAsc;

	//产生的UUID主键
	private String startGuidString;
	
	private FormTable table;
	
	Map<String, Object> result;

	private String queryString = "";

	private String SQLString = "";

	//供表层ACTION传递添加值进入
	private Map<String, Object> forAddMap = new HashMap<String, Object>();

	private Map<String, Object> map = new HashMap<String, Object>(); // 记录列表的表单信息
	private Map<String, Object> codeMap = new LinkedHashMap<String, Object>(); // 记录代码表信息
	private Map<String, Object> valueMap = new HashMap<String, Object>(); // 记录页面返回值的
	private String rowId;// 记录ID
	private String thisrowId;// 记录ID

	private String ids;
	
	JdbcParameter parameter;

	List<Attachfiles> atfList;

	//列表集
	private Pagination resultPage;

	private Integer pageNo;
	
	private Map<String, String> columNameMap;

	// --------------图片相关-----------------
	private String fieldName; // 字段名称
	private File[] files;
	private String[] filesContentType;
	private String[] filesFileName;
	private String fileNames;
	private InputStream inputstream;
	private String fileName;// 下载文件名
	private String message;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {

		this.tableId = tableId;
	}

	/*
	 * 字段校验
	 */
	public String TestField(FormField field, Object value) {
		/*
		 * 检查是否允许为空
		 * 
		 * if (field.getMustFill()!=null&field.getMustFill().equals(1)) { if
		 * (value==null||value.toString().length()==0) { message =""+
		 * field.getFieldDisplayName()+" 不为空，必须填写！"; return "2"; } }
		 * 
		 */
		return "1";

	}

	/**
	 * 给继承formRecordAction的Action使用，通过PhyName和fieldName查找其在ValueMap里面的值
	 */
	public Object getValueMapbyPhyNameAndFieldName(String PhyName, String fieldName) {
		Object tempObject = valueMap.get(fieldName + "_" + PhyName);
		if (tempObject != null) {
			return tempObject;
		}
		return "";
	}

	/**
	 * 保存从页面提交的对象
	 * 现在分为两个部分：
	 * 1.在beforeSave()中产生需插入数据库的parameter
	 * 2.调用baseJdbcService将parameter插入数据库
	 */
	public String save() throws Exception {
		
		if (beforeSave()) {
			baseJdbcService.insert(parameter);
		}
		return SUCCESS;
	}
	
	/**
	 * 对request进行处理，得到用以保存的信息以哈希表（valueMap）保存
	 */
	public boolean beforeSave() throws Exception {
		List<FormField> columnList = formFieldService.getAllField(tableId);
		FormTable table = formTableService.get(tableId);
		
		String[] pNameStrings = table.getPhysicalName().split("_");
		tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> addmap = new HashMap<String, Object>();
		for (FormField field : columnList) {
			if (field.getFieldDisplayType().equals("ImageUpload")) {

				continue;
			}
			if (forAddMap.containsKey(field.getFieldName())) {
				addmap.put(field.getFieldName(), forAddMap.get(field
						.getFieldName()));
				continue;
			}

			Object[] valueArray = request.getParameterValues(field
					.getFieldName()
					+ "_" + tablePhyName);
			Object value = null;
			if (valueArray != null && valueArray.length != 0) {
				if (valueArray.length > 1) {
					String mutivalue = "";
					for (Object valueTemp : valueArray) {
						mutivalue += valueTemp + ",";
					}
					if (!mutivalue.equals("")) {
						mutivalue = mutivalue.substring(0,
								mutivalue.length() - 1);
					}
					value = mutivalue;
				} else {
					value = valueArray[0];
				}

				if (!TestField(field, value).equals("1")) {
					return false;
				}

				if ((field.getFieldType().equals("date") || field
						.getFieldType().equals("datetime"))
						) {
					
					if(!value.equals(""))
					{
					addmap.put(field.getFieldName().trim(), new java.sql.Timestamp(
							getDate(value).getTime()));
					}
					else
					{
						continue;
					}
				} else if ((field.getFieldType().equals("decimal")||field.getFieldType().equals("int")|| field.getFieldType().equals("number"))
						&& value.equals("")) {
					addmap.put(field.getFieldName().trim(), 0);
				} else {
					addmap.put(field.getFieldName(), value);
				}
			}
		}
		Map<String, Object> blobMap = addBLOBvalue(columnList, addmap);
		rowId = getDefaultInfo(table.getPhysicalName(), addmap);
		valueMap.clear();
		parameter = new JdbcParameter();
		parameter.setBlobMap(blobMap);
		parameter.setParaMap(addmap);
		parameter.setTableName(table.getPhysicalName());
		
		
		//map.put("tableName", table.getTableName().trim());
		//map.put("columnList", columnList);
		
		thisrowId = rowId;
		rowId = null;
		
		fillUserDefaultInfo(true);
		message = "新建成功!";
		
		return true;
	}
	
	
	
	

	/**
	 * 设置blob字段信息
	 * 
	 * @param columnList
	 * @param addmap
	 * @return
	 */
	private Map<String, Object> addBLOBvalue(List<FormField> columnList,
			Map<String, Object> addmap) throws Exception{
		List<String> blobList = new ArrayList<String>();
		for (FormField field : columnList) {
			if (field.getFieldType().equals("blob")
					|| field.getFieldType().equals("image")) {
				blobList.add(field.getFieldName());
			}
		}
		int j = 0;
		Map<String, Object> blobMap = new HashMap<String, Object>();
		if (fileNames != null && fileNames.length() > 0) {
			String[] existfile = fileNames.substring(1).split(",");
			for (int i = 0; i < blobList.size(); i++) {
				if (existfile[i].equals("1")) {
					int num = j++;
					
					File file = files[num];
					InputStream is = new FileInputStream(file);
					long length = file.length();
					byte[] bytes = new byte[(int)length];
			        // 读取数据到byte数组中
			        int offset = 0;
			        int numRead = 0;
			        while (offset < bytes.length
			               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			            offset += numRead;
			        }
			        is.close();
					
					blobMap.put(blobList.get(i), bytes);
					//addmap.put(blobList.get(i), null);
					addmap.put(blobList.get(i) + "_ContentName",
							filesFileName[num]);
					addmap.put(blobList.get(i) + "_ContentType",
							filesContentType[num]);
				}
			}
		}
		return blobMap;
	}

	/**
	 * 格式化时间对象
	 */
	private Date getDate(Object date) {
		try {
			if (date != null && !date.equals("")) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sf.parse(String.valueOf(date));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加记录时，初始化一些默认字段
	 * 
	 * @param tableName
	 * @param map
	 */
	public String getDefaultInfo(String tableName, Map<String, Object> map) {

		startGuidString = java.util.UUID.randomUUID().toString();
		//getLoginUserId();
		if (getLoginUserId() != null) {
			map.put("CREATETIME", new java.sql.Timestamp(System
					.currentTimeMillis()));
			map.put("CREATEUSERID", getLoginUserId());
			map.put("DEPTID", getLoginUserDepartmentId());
		}
		map.put("ROW_ID", startGuidString);

		return startGuidString;
	}

	/**
	 * 查看某一条记录
	 */
	@SuppressWarnings("unchecked")
	public String view() {
		List<FormField> columnList = formFieldService
				.getAllField(tableId);// 查看记录时只显示formFieldEdit.jps中配置的详细显示的记录
		List<String> columnNames = new ArrayList<String>();

		try {
			getCodeValueMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (FormField field : columnList) {
			columnNames.add(field.getFieldName());
		}
		FormTable table = formTableService.get(tableId);
		//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
		String[] pNameStrings = table.getPhysicalName().split("_");
		tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			
		}
		
		//构建查询条件
		StringBuffer querySql = new StringBuffer("select ");
		Object[] params = new Object[1];
		params[0] = rowId;
		if(columnNames==null || columnNames.size()==0)
			querySql.append("* ");
		else{
			for(int i=0;i<columnNames.size();i++){
				querySql.append(columnNames.get(i));
				if(i<columnNames.size()-1)
					querySql.append(",");
			}
		}
		querySql.append(" from ").append(table.getPhysicalName()).append(" where row_id=?");
		
		result = baseJdbcService.findBySql(querySql.toString(), params);
		
		atfList = new ArrayList<Attachfiles>();
		if (result != null) {
			//处理得到的结果集
			for (FormField field : columnList) {
				valueMap.remove(field.getFieldName() + "_" + tablePhyName);
				String value = "";
				if ((result.get(field.getFieldName())) != null
						&& !result.get(field.getFieldName()).equals("")) {

					if (field.getFieldDisplayType().equals("MultiFiles")) {
						String fvalue = String.valueOf(result.get(field
								.getFieldName()));
						if (fvalue != null) {
							List<Attachfiles> tempatfList = attachfileManager
									.getAttFilesByGuID(fvalue);
							atfList.addAll(tempatfList);
						
							valueMap.put(field.getFieldName() + "_" + tablePhyName,
									fvalue);
						}

					} else if (!field.getFieldDisplayType().equals("CheckBox")) {
						if (field.getFieldType().equals("date")// 对日期型特殊处理
								|| field.getFieldType().equals("datetime")) {
							value = String.valueOf(
									result.get(field.getFieldName()));
						} else if (field.getFieldType().equals("decimal")|| field.getFieldType().equals("number"))// 对数字型特殊处理
						{
							value = String
									.valueOf(Double.parseDouble(String
											.valueOf(result.get(field
													.getFieldName()))));
						}

						else if (field.getFieldType().equals("blob")
								|| field.getFieldType().equals("image")) {
							//对blob对象特殊处理
							Object o = result.get(field.getFieldName());

							value = "false";
							if (o != null) {
								value = "true";
							}
						} else if (field.getFieldDisplayType().equals(
								"DropdownList")
								|| field.getFieldDisplayType().equals("Radio")) {
							//对下拉框特殊处理，将后台保存的值替换为显示值
							String fvalue = String.valueOf(result.get(field
									.getFieldName()));
							if (fvalue != null) {
								List<CodeValue> cvList = ((List<CodeValue>) codeMap
										.get(field.getFieldName() + "_"
												+  tablePhyName));
								if (cvList != null && cvList.size() > 0) {
									for (CodeValue codeValue : cvList) {
										if (codeValue.getItemValue().equals(
												fvalue)) {
											value = codeValue.getItemText();
											break;
										}
									}
								}
							}

						} else {
							value = String.valueOf(result.get(field
									.getFieldName()));
						}

						valueMap.put(field.getFieldName() + "_" + tablePhyName,
								value);

					} else {
						String checkBoxValue = String.valueOf(result.get(field
								.getFieldName()));
						String[] sigCheckValue = checkBoxValue.split(",");
						String[] sigCheckText = new String[sigCheckValue.length];
						int i = 0;
						
						for (String tempalue : sigCheckValue) {
							CodeValue cValue = null;
							List<CodeValue> cvList = ((List<CodeValue>) codeMap
									.get(field.getFieldName() + "_" + tablePhyName));
							if (cvList != null && cvList.size() > 0) {
								for (CodeValue codeValue : cvList) {
									if (codeValue.getItemValue().equals(
											tempalue)) {
										cValue = codeValue;
										break;
									}
								}

							}
							sigCheckText[i] = cValue == null ? "" : cValue
									.getItemText();

							i++;
						}
						valueMap.put(field.getFieldName() + "_" + tablePhyName,
								sigCheckText);
					}

				}
			}
		}
		
		map.put("tableName", table.getTableName());
		map.put("columnList", columnList);
		return SUCCESS;
	}

	/**
	 * 更新，和添加一样，分为两部分，从beforeUpdate()内得到要保存的信息保存在valuemap里，然后调用Service保存
	 */
	public String update() throws Exception{
	
		if (beforeUpdate()) {
			baseJdbcService.update(parameter);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 从request里取得值进行保存
	 * @return
	 * @throws Exception
	 */
	public boolean beforeUpdate() throws Exception {
		List<FormField> columnList = formFieldService.getAllField(tableId);
		FormTable table = formTableService.get(tableId);
		String[] pNameStrings = table.getPhysicalName().split("_");
		tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> updatemap = new HashMap<String, Object>();
		List<String> codeColumn = new ArrayList<String>(); // 存放代碼錶類型的屬性
		List<String> columnNames = new ArrayList<String>();
		for (FormField field : columnList) {
			if (field.getFieldDisplayType().equals("ImageUpload")) {
				continue;
			}
			Object[] valueArray = request.getParameterValues(field
					.getFieldName()
					+ "_" + tablePhyName);
			Object value = null;
			if (valueArray != null) {
				if (valueArray.length > 1) {
					String mutivalue = "";
					for (Object valueTemp : valueArray) {
						mutivalue += valueTemp + ",";
					}
					if (!mutivalue.equals("")) {
						mutivalue = mutivalue.substring(0,
								mutivalue.length() - 1);
					}
					value = mutivalue;
				} else {
					value = valueArray[0];
				}
				if (!TestField(field, value).equals("1")) {
					return false;
				}

				if ((field.getFieldType().equals("date") || field
						.getFieldType().equals("datetime"))
						) {
					
					if(!value.equals(""))
					{
						updatemap.put(field.getFieldName().trim(),
								new java.sql.Timestamp(getDate(value).getTime()));
					}
					else
					{
						continue;
					}
					
					
					
				} else if (field.getFieldType().equals("varchar")
						&& !value.equals("")) {
					updatemap
							.put(field.getFieldName(), value.toString().trim());
				} else {
					updatemap.put(field.getFieldName().trim(), value);
				}
			}
			
			String codeType = field.getDataSource();
			String codeId = field.getDataSourceType();
			String fieldName = field.getFieldName();
			if ((codeType != null && codeType.equals("Codes"))
					&& (codeId != null && !codeId.equals("-1") && field
							.getFieldDisplayType().equals("CheckBox"))) {
				codeColumn.add(fieldName);
			}
			
			columnNames.add(field.getFieldName().trim());
		}
		Map<String, Object> blobMap = addBLOBvalue(columnList, updatemap);
		updatemap.put("UPDATETIME", new java.sql.Timestamp(System
				.currentTimeMillis()));
		
		updatemap.put("UPDATEUSERID", getLoginUserId());
		
		parameter = new JdbcParameter();
		parameter.setBlobMap(blobMap);
		parameter.setParaMap(updatemap);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("ROW_ID", rowId);
		parameter.setQueryMap(queryMap);
		parameter.setTableName(table.getPhysicalName());
		
		
		message = "修 改 成 功";

		return true;
		
		
	}

	/**
	 * 给页面填入缺省值。
	 * 
	 */
	public String fillUserDefaultInfo(boolean isNew) throws Exception {
		List<FormField> columnList = formFieldService.getAllField(tableId);
		FormTable table = formTableService.get(tableId);
		String[] pNameStrings = table.getPhysicalName().split("_");
		tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
		}
		map.put("tableName", table.getTableName().trim());
		map.put("columnList", columnList);
		HttpServletRequest request = ServletActionContext.getRequest();
		atfList = new ArrayList<Attachfiles>(); 
		getCodeValueMap();
		
		// 自动取值字段储存值
		for (FormField field : columnList) {
			String fieldName = field.getFieldName();
			if (!isNew) {
				Object[] valueArray = request.getParameterValues(field
						.getFieldName()
						+ "_" + tablePhyName);

				if (valueArray != null && valueArray.length > 0) {

					Object value = null;
					if (valueArray != null && valueArray.length != 0) {
						if (valueArray.length > 1
								|| field.getFieldDisplayType().equals("CheckBox")) {
							value = valueArray;
						} else {
							value = valueArray[0];
						}
					}
					valueMap.put(fieldName.trim() + "_" + tablePhyName, value);

					/*
					 * 如果是多文件上传导致的刷新，则从数据库中取其内容
					 */
					if (field.getFieldDisplayType().equals("MultiFiles")) {
						String fvalue = (String) value;
						if (fvalue != null) {
							List<Attachfiles> tempatfList = attachfileManager
									.getAttFilesByGuID(fvalue);
							atfList.addAll(tempatfList);
						}
					}

					continue;

				}
			}
			
			
			if (field.getFieldDisplayType().equals("Radio")// 对多选型特殊处理
					|| field.getFieldDisplayType().equals("CheckBox")
					|| field.getFieldDisplayType().equals("DropdownList"))
			{
				if (field.getFieldDefaultValue()!=null&&!field.getFieldDefaultValue().equals("-1")) {
					String fvalue = field.getFieldDefaultValue();
					if (fvalue != null) {
						List<CodeValue> cvList = ((List<CodeValue>) codeMap
								.get(field.getFieldName() + "_"
										+  tablePhyName));
						if (cvList != null && cvList.size() > 0) {
							for (CodeValue codeValue : cvList) {
								if (codeValue.getId().equals(
										fvalue)) {
									valueMap.put(fieldName.trim() + "_" + tablePhyName, codeValue.getItemText());
									break;
								}
							}
						}
					}
				}
			
			}
			if (field.getFieldType().equals("varchar")&&!field.getValueUrl().equals("无")) {
				if (field.getValueUrl().equals("操作人员姓名")) {
					User user = (User) request.getSession().getAttribute(
							Constants.CURR_USER);
					if (user != null) {
						valueMap.put(fieldName.trim() + "_" + tablePhyName, user
								.getDisplayName().trim());
					}
				} else if (field.getValueUrl().equals("操作人员部门")) {
					User user = (User) request.getSession().getAttribute(
							Constants.CURR_USER);
					if (user != null) {
						valueMap.put(fieldName.trim() + "_" + tablePhyName, getLoginUserDepartment().getDeptName().trim());
					}
				} else if (field.getValueUrl().equals("当前时间")) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					valueMap.put(fieldName.trim() + "_" + tablePhyName, df
							.format(new Date()));
				}
			}
			
			
			if (field.getFieldType().equals("date")&&!field.getValueUrl().equals("无")) {
				if (field.getValueUrl().equals("当前时间")) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					valueMap.put(fieldName.trim() + "_" + tablePhyName, df
							.format(new Date()));
				}
			}
			
			
			
		}

		return SUCCESS;
	}

	/**
	 * 新建页面准备
	 */
	public String formRecordAdd() throws Exception {
		
		return fillUserDefaultInfo(false);
	}
	
	private Map<String, Code> getCodeMap(String tableId) 
    {
        //获取所有的一维代码列表
        List<Code> allCodeList = codeService.getAllCode();
        Map<String, Code> allCodeMap = new LinkedHashMap<String, Code>();
        for (Code code : allCodeList)
        {
            allCodeMap.put(code.getId(), code);
        }
        Map<String, Code> codeMap = new LinkedHashMap<String, Code>();
        List<FormField> columnList = formFieldService.getAllField(tableId);
        for (FormField formField : columnList)
        {
            String type = formField.getDataSource();
            String codeId = formField.getDataSourceType();
            if ((type != null && type.equals("Codes")) && (codeId != null && !codeId.equals("-1")))
            {
                Code code = allCodeMap.get(codeId);
                codeMap.put(codeId, code);
            }
        }
        return codeMap;
    }
	
	public String viewList(){
		table = formTableService.get(tableId);
		List<FormField> displayList = formFieldService.getDisplayField(tableId);
		/**
		columNameMap = new HashMap<String, String>();
		for(FormField field : displayList){
			columNameMap.put(field.getFieldName(), field.getFieldDisplayName());
		}		
		**/

		String[] pNameStrings = table.getPhysicalName().split("_");
		tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
			
		}
		
		try{
			codeMap = getCodeValueMap();
		}catch(Exception e){
			e.printStackTrace();
		}
		List<FormField> queryList = formFieldService.getQueryField(tableId);
		map.put("queryList", queryList);
		int i = 0;
		//生成列表
		StringBuffer columns = new StringBuffer();
		if(displayList!=null && displayList.size()>0){
			for(FormField field : displayList){
				if (1 != field.getDispInGrid())
	            {
	                continue;
	            }
	            //只有文本框，下拉框，单选框，多选框，日期选择框等可以在列表显示
	            if (!(field.getFieldDisplayType().equals("TextBox") 
	            		|| field.getFieldDisplayType().equals("TextArea")
	                    || field.getFieldDisplayType().equals("DropdownList")
	                    || field.getFieldDisplayType().equals("Radio") 
	                    || field.getFieldDisplayType().equals("CheckBox") 
	                    || field.getFieldDisplayType().equals("DatePick")))
	            {
	                continue;
	            }
	            String property = field.getFieldName().toUpperCase();
	            String title = field.getFieldDisplayName();
	            columns.append("{field:'").append(property).append("',title:'").append(title).append("',width:100}");
				if(i<displayList.size()-1)
					columns.append(",");
				i++;
			}
		}
		map.put("column", columns.toString());
		
		//生成查询条件
		StringBuffer params = new StringBuffer();
		if(queryList!=null && queryList.size()>0){
			for(FormField field : queryList){
				//过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                String property = field.getFieldName().toUpperCase()+"_"+tablePhyName;
                if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //日期型
                    params.append(" \"").append(property).append("_start\"");
                    params.append(" :$(\"#").append(property).append("_start\").val(),\n");
                    params.append(" \"").append(property).append("_end\"");
                    params.append(" :$(\"#").append(property).append("_end\").val()\n");
                }
                else{
                	params.append("\"").append(property).append("\"");
                	params.append(": $(\"#").append(property).append("\").val()");
                }
                params.append(",\n");
			}
		}
		params.append("tableId : \"").append(table.getId()).append("\"\n");
		map.put("queryParams", params.toString());
		
		return SUCCESS;
	}

	/**
	 * 查询当前表的List
	 */
	public String viewRecord() {
		try {
			getCodeValueMap();
			resultPage = new Pagination(super.getRequest());
			
			table = formTableService.get(tableId);
			List<FormField> displayList = formFieldService.getDisplayField(tableId);
			List<FormField> queryList = formFieldService.getQueryField(tableId);
			List<FormField> sortList = formFieldService.getOrderField(tableId);
			
			fillPage(resultPage, table.getPhysicalName(), queryList,
					displayList, sortList);
			
			
			StringBuffer name = new StringBuffer();
			for(FormField field : displayList){
				name.append(field.getFieldName()).append("|");
			}
			final String colNames = name.toString()+"ROW_ID|";
			outputJsonList(resultPage.getTotalCount(), colNames, resultPage.getListOfObject());
			
			
			
		} catch (Exception e) {
			BasalException ex = new BasalException(BasalException.NO, "列表界面出错，可能该表在数据库中不存在", e);
			throw ex;
		}
		return null;
	}

	/**
	 * 取得List页面中的结果集
	 * @param page
	 * @param tableName
	 * @param queryList
	 * @param displayList
	 * @param sortList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Pagination fillPage(
			Pagination page, String tableName,
			List<FormField> queryList, List<FormField> displayList,
			List<FormField> sortList) {

		List<FormField> EditColume = new ArrayList<FormField>(); // 存放代碼錶類型的屬性
		String code = null; // 代码表类型属性列取值

		StringBuffer querySql = new StringBuffer("select ");

		if (SQLString.equals("")) {
			if (displayList != null && displayList.size() > 0) {
				querySql.append("ROW_ID,");
				int i = 0;
				int size = displayList.size();
				for (FormField formField : displayList) {
					String codeType = formField.getDataSource();
					String codeId = formField.getDataSourceType();
					String fieldName = formField.getFieldName();
					if ((codeType != null && codeType.equals("Codes"))
							&& (codeId != null && !codeId.equals("-1"))) {
						EditColume.add(formField);
					}
					if ((codeType != null && codeType.equals("Other"))
							&& (codeId != null && !codeId.equals(""))) {
						EditColume.add(formField);
					}
					if (formField.getFieldDisplayType().equals("DatePick")) {
						EditColume.add(formField);
					}
					querySql.append(fieldName);
					if (i < size - 1)
						querySql.append(", ");
					i++;
				}
			} else
				querySql.append("ROW_ID ");
			querySql.append(" from ");
			querySql.append(tableName);
			querySql.append(" where 1=1 ");

		} else {
			querySql.append(" * from ( " + SQLString + " ) where 1=1");
		}

		// 设置查询条件
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Object> params = new ArrayList<Object>();
		if (queryList != null && queryList.size() > 0) {
			for (FormField field : queryList) {
				if (field.getFieldDisplayType().equals("DatePick")) {
					Object start = request.getParameter(field.getFieldName()
							.trim()
							+ "_" + tablePhyName + "_start");
					Object end = request.getParameter(field.getFieldName()
							.trim()
							+ "_" + tablePhyName + "_end");
					valueMap.put(field.getFieldName().trim() + "_" + tablePhyName
							+ "_start", start);// 将查询的值保存下来，以便页面显示
					valueMap.put(field.getFieldName().trim() + "_" + tablePhyName
							+ "_end", end);// 将查询的值保存下来，以便页面显示

					if (start != null && !start.equals("")) {
						start = new java.sql.Timestamp(getDate(start).getTime());
					} else {
						start = null;
					}
					if (end != null && !end.equals("")) {
						end = new java.sql.Timestamp(getDate(end).getTime());
					} else {
						end = null;
					}

					if (start != null && end == null) {
						querySql.append(" and ").append(field.getFieldName())
								.append(">=?");
						params.add(start);
					} else if (start == null && end != null) {
						querySql.append(" and ").append(field.getFieldName())
								.append("<=? ");
						params.add(end);
					} else if (start != null && end != null) {
						querySql.append(" and ").append(field.getFieldName())
								.append(" between ? and ?  ");
						params.add(start);
						params.add(end);
					}

				} else if (field.getFieldDisplayType().equals("CheckBox")) {
					Object[] value = request.getParameterValues(field
							.getFieldName()
							+ "_" + tablePhyName);
					if (value != null && value.length > 0) {
						String mutivalue = "";
						for (Object valueTemp : value) {
							mutivalue += valueTemp + ",";
						}
						if (!mutivalue.equals("")) {
							mutivalue = mutivalue.substring(0, mutivalue
									.length() - 1);
						}
						querySql.append(" and ").append(field.getFieldName())
								.append(" like ? ");
						params.add("%" + mutivalue + "%");
						valueMap.put(field.getFieldName().trim() + "_"
								+ tableId, value);
					}
				}

				else {
					Object value = request.getParameter(field.getFieldName()
							+ "_" + tablePhyName);
					if (field.getFieldType().equals("decimal")
							|| field.getFieldType().equals("number")
							||field.getFieldDisplayType().equals("DropdownList")
							||field.getFieldDisplayType().equals("Radio")
							) {
						if (value != null && !value.equals("")) {
							querySql.append(" and ").append(
									field.getFieldName()).append(" = ? ");
							params.add(value);
							valueMap.put(field.getFieldName().trim() + "_" + tablePhyName,
									String.valueOf(value).trim());
						}
					} else {
						if (value != null && !value.equals("")) {
							querySql.append(" and ").append(
									field.getFieldName()).append(" like ? ");
							params.add("%" + value + "%");
							valueMap.put(field.getFieldName().trim() + "_" + tablePhyName,
									String.valueOf(value).trim());

						}
					}

					

				}
			}
		}
		//设置从Action里特别设下的查询条件 - 
		if (queryString != null && queryString.length() > 0) {
			querySql.append(" and ").append(queryString);
		}
		
	
		//params.add(deptCodes);

		// 设置排序条件
		StringBuffer orderBy = new StringBuffer("");
		
		if (orderbywhat!=null&&orderbywhat.length()>0) {			
			orderBy.append(" order by ").append(orderbywhat).append(" ").append(orderAsc);
		}else{
			if (sortList != null && sortList.size() > 0) {
				orderBy.append(" order by ");
				for (FormField field : sortList) {

					 //nText和Image不能排序
					 
					if (field.getFieldType().equals("clob")
							|| field.getFieldType().equals("blob")
							|| field.getFieldType().equals("ntext")
							|| field.getFieldType().equals("image")) {
						continue;
					}

					orderBy.append(field.getFieldName()).append(" ").append(
							field.getSortDirection() == null ? "DESC" : field
									.getSortDirection()).append(" ,");
				}
				orderBy.deleteCharAt(orderBy.length() - 1);
			}
		}
		querySql.append(orderBy);
		map.put("queryList", queryList);
		resultPage = baseJdbcService.findPageBySql(querySql.toString(), resultPage, params.toArray());
		
		// 将结果集做处理，因为可能该字段的值是对应代码表中的值
		for (Object obj : resultPage.getList()) {
			LinkedHashMap<String, Object> resultMap = (LinkedHashMap<String, Object>)obj;
			for (FormField column : EditColume) {

				if (column.getFieldDisplayType().equals("Radio")// 对下拉框型特殊处理
						|| column.getFieldDisplayType().equals("CheckBox")
						|| column.getFieldDisplayType().equals("DropdownList")) {

					if ((code = (String) resultMap.get(column.getFieldName())) != null
							&& !code.equals("")) {
						String[] codes = code.split(",");
						String value = "";
						for (int j = 0; j < codes.length; j++) {
							String codeVl = null;
							List<CodeValue> cvList = ((List<CodeValue>) codeMap
									.get(column.getFieldName() + "_" + tablePhyName));
							if (cvList != null && cvList.size() > 0) {
								for (CodeValue codeValue : cvList) {
									if (codeValue.getItemValue().equals(
											codes[j])) {
										codeVl = codeValue.getItemText();
										break;
									}
								}
							}
							/*
							 * String codeVl = codeValueManager
							 * .getCodeValueByCodeIDandValue(
							 * Integer.parseInt(column .getDataSourceType()),
							 * codes[j]).getItemtext();
							 */

							if (codeVl != null) {
								value += "," + codeVl;
							}
							
						}
						if (!value.equals("")) {
							resultMap.put(column.getFieldName(), value
									.substring(1));
						}
						else {
							resultMap.put(column.getFieldName(),"");
						}
					}
				}
				if (column.getFieldDisplayType().equals("DatePick")// 对日期型特殊处理
				) {
					Date value = ((Date) resultMap.get(column.getFieldName()));
					if (value != null) {

						resultMap.put(column.getFieldName(), String
								.valueOf(value.toString()));

					}

				}
			}
		}
		return resultPage;
	}

	/**
	 * 删除记录(物理删除)
	 */
	public String deleteRecord() throws Exception {
		try{
			String[] idsArray = ids.split("\\|");
			for (int i = 0; i < idsArray.length; i++) {
				String id = idsArray[i];
				
				if(id!=null && !id.trim().equals(""))
					deleteRecord(tableId, id.toString());
			}
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				
			}
		}catch(Exception ex){
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
				
			}
		}
		return null;
	}

	/**
	 * 删除记录 - 特定记录
	 */
	public void deleteRecord(String tableId, String id) throws Exception {
		FormTable table = formTableService.get(tableId);
		String sql = "delete from " + table.getPhysicalName()
				+ " where row_id=?";
		Object[] param = new Object[1];
		param[0] = id;
		
		baseJdbcService.executeSql(sql, param);

	}

	/**
	 * 从数据库中查询得到字典表，存放在codemap内，供前台下拉框等调用
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getCodeValueMap() throws Exception {
		List<FormField> columnList = formFieldService.getAllField(tableId);
		
		FormTable table = formTableService.get(tableId);
		
		String[] pNameStrings = table.getPhysicalName().split("_");
		tablePhyName = "";//table.getPhysicalName().replace("_", "").toLowerCase();
		for (String pNameString : pNameStrings) {
			tablePhyName+=pNameString.substring(0,1).toUpperCase()+pNameString.substring(1).toLowerCase();
			//基础类名以物理表名为底，将其中的_转化为后接的字母大写。
			
		}
		
		List<CodeValue> codeList=null;
		for (FormField formField : columnList) {
			String fieldName = formField.getFieldName().trim();
			String type = formField.getDataSource();
			String codeId = formField.getDataSourceType();
			if ((type != null && type.equals("Codes"))
					&& (codeId != null && !codeId.equals("-1"))) {
				codeList = codeService.findCodeValueByCode(codeId);
				codeMap.put(fieldName + "_" + tablePhyName, codeList);
			}
			if ((type != null && type.equals("Other"))
					&& (codeId != null && !codeId.equals(""))) {
				String queryString = formField.getDefaultValue();
				if (queryString!=null&&queryString.length()!=0) {
					/*
					 * 由于在SQL语句中无法获取当前用户等信息，所以此处用一些默认值占位，然后再这里做替换
					 * 1.&userdepartment& —— 代码中用当前用户的部门id替换
					 * 2.&currentuserid&  —— 代码中用当前用户id来替换
					 * 3.user			—— 代码中用当前用户的会员信息id替换
					 */
					queryString.replace("&userdepartment&", getLoginUserDepartmentId());
					queryString.replace("&currentuserid&", getLoginUserId());
					//queryString.replace("userdepartment", getLoginUserDepartmentId());
					
					
					
					List<Map<String, Object>> tempList = baseJdbcService.findListBySql(queryString);
					if (tempList!=null) {
						 codeList = new ArrayList<CodeValue>();
							for (Map<String, Object> linkedHashMap : tempList) {
								CodeValue cValue = new CodeValue();
								Object[] valueStrings = linkedHashMap.values().toArray();
								if (valueStrings[0]!=null&&valueStrings[1]!=null) {
									cValue.setItemValue(valueStrings[0].toString());
									cValue.setItemText(valueStrings[1].toString());
									codeList.add(cValue);
								}
								
							
						
					}
					
				}
				

				}
				codeMap.put(fieldName + "_" + tablePhyName, codeList);
			}
		}
		return codeMap;
	}

	/**
	 * 查看图片
	 * @return
	 */
	public String viewImage() {
		FormTable table = formTableService.get(tableId);
		List<String> columnNames = new ArrayList<String>();
		columnNames.add(fieldName);
		columnNames.add(fieldName + "_ContentName");
		columnNames.add(fieldName + "_ContentType");
		
		StringBuffer querySql = new StringBuffer("select ");
		Object[] params = new Object[1];
		params[0] = rowId;
		if(columnNames==null || columnNames.size()==0)
			querySql.append("* ");
		else{
			for(int i=0;i<columnNames.size();i++){
				querySql.append(columnNames.get(i));
				if(i<columnNames.size()-1)
					querySql.append(",");
			}
		}
		querySql.append(" from ").append(table.getPhysicalName()).append(" where row_id=?");
		Map<String, Object> result = baseJdbcService.findBySql(querySql.toString(), params);
		
		if (result != null) {
			Object o = result.get(fieldName);
			try {
				byte[] data = (byte[]) o;
				if (data != null && data.length > 0) {
					Struts2Util.getResponse().setContentType(
							String.valueOf(result.get(fieldName
									+ "_CONTENTTYPE")));
					Struts2Util.getResponse().addHeader(
							"Content-Disposition",
							"filename="
									+ String.valueOf(result.get(fieldName
											+ "_CONTENTNAME")));
					OutputStream out = Struts2Util.getResponse()
							.getOutputStream();

					try {
						out.write(data, 0, data.length);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						out.close();
					}
				} else {
					inputstream = null;
				}
			} catch (Exception e) {
				BasalException ex = new BasalException(BasalException.NO, "查看图片失败", e);
				throw ex;
			}
		}

		return null;
	}
	
	/**
	 * 对要求做唯一处理的字段做检查
	 */
	private boolean checkUniqueFields() {
		//TODO:由于可能是局部唯一，和业务有挂钩，故暂时不处理
		return true;
		
	}
	

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getCodeMap() {
		return codeMap;
	}

	public void setCodeMap(Map<String, Object> codeMap) {
		this.codeMap = codeMap;
	}

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	public Pagination getResultPage() {
		return resultPage;
	}

	public void setResultPage(Pagination resultPage) {
		this.resultPage = resultPage;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public InputStream getInputstream() {
		return inputstream;
	}

	public void setInputstream(InputStream inputstream) {
		this.inputstream = inputstream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Attachfiles> getAtfList() {
		return atfList;
	}

	public void setAtfList(List<Attachfiles> atfList) {
		this.atfList = atfList;
	}

	public String getStartGuidString() {
		return startGuidString;
	}

	public void setStartGuidString(String startGuidString) {
		this.startGuidString = startGuidString;
	}

	public String getThisrowId() {
		return thisrowId;
	}

	public void setThisrowId(String thisrowId) {
		this.thisrowId = thisrowId;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Map<String, Object> getForAddMap() {
		return forAddMap;
	}

	public void setForAddMap(Map<String, Object> forAddMap) {
		this.forAddMap = forAddMap;
	}

	public String getSQLString() {
		return SQLString;
	}

	public void setSQLString(String string) {
		SQLString = string;
	}

	public String getOrderbywhat() {
		return orderbywhat;
	}

	public void setOrderbywhat(String orderbywhat) {
		this.orderbywhat = orderbywhat;
	}

	public String getOrderAsc() {
		return orderAsc;
	}

	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	/**
	 * @return the columNameMap
	 */
	public Map<String, String> getColumNameMap() {
		return columNameMap;
	}

	/**
	 * @param columNameMap the columNameMap to set
	 */
	public void setColumNameMap(Map<String, String> columNameMap) {
		this.columNameMap = columNameMap;
	}

	public String getTablePhyName() {
		return tablePhyName;
	}

	public void setTablePhyName(String tablePhyName) {
		this.tablePhyName = tablePhyName;
	}

	public JdbcParameter getParameter() {
		return parameter;
	}

	public void setParameter(JdbcParameter parameter) {
		this.parameter = parameter;
	}

	public FormTable getTable() {
		return table;
	}

	public void setTable(FormTable table) {
		this.table = table;
	}
}
