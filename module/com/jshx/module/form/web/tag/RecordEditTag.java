package com.jshx.module.form.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


import net.fckeditor.FCKeditor;

import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.form.entity.Attachfiles;
import com.jshx.module.form.entity.FormField;

@SuppressWarnings("serial")
public class RecordEditTag extends TagSupport {
	
    private String columnListName;
	
	private String codeMapName;
	
	private String valueMapName;
	
	private Map<String, Object> valueMap;
	
	private HttpServletRequest request;

	private JspWriter out;
	
	private List<FormField> queryList;
	
	private Map<String, Object> codeMap;
	
	private String tablePhyName;
	
	private String tableId;
	
	private String rowId;
	
	private List<Attachfiles> atfList ;
	
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
	
		out = pageContext.getOut();		
		request = (HttpServletRequest) pageContext.getRequest();
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
		queryList = (List<FormField>)map.get(columnListName);
		codeMap = (Map<String,Object>)request.getAttribute(codeMapName);
		valueMap = (Map<String,Object>)request.getAttribute(valueMapName);

		atfList = (List<Attachfiles>)request.getAttribute("atfList");
		return super.doStartTag();
	}
	
	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		if(queryList!=null&&queryList.size()>0){
			int i = 0;
			int filei=0;
			for(FormField field: queryList){
				if(field.getDispInDetail()==0){
					i++;
					continue;
				}
				if(field.getGridMultiRows()==0)
				{
					if (i%2!=0) {
						html.append("</tr><tr>");
						
					}
				}
				html.append("<td Class='tb_01' width='15%' height='30px'>");
				html.append(field.getFieldDisplayName()).append("</td>");
				html.append("<td Class='tb_02' ");
				if(field.getGridMultiRows()==0)
				{
					html.append(" colspan='3' ");
				}
				
				else
					html.append(" width='35%' ");
				if(field.getGridWidth()!=null&&field.getControlWidth()!=null)
					html.append(" hight='").append(field.getGridWidth()*field.getControlWidth()).append("'");
				html.append(">");
				List<CodeValue> codeList = new ArrayList<CodeValue>();
				if(codeMap!=null){
					codeList = (List<CodeValue>)codeMap.get(field.getFieldName()+"_"+tablePhyName);
				}
				
				if(field.getFieldDisplayType().equals("TextBox")){
					// 文本框类型输入方式
					html.append("<input name='").append(field.getFieldName()+"_"+tablePhyName).append("'");
					if(valueMap!=null&&valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(" value='").append(valueMap.get(field.getFieldName()+"_"+tablePhyName)).append("'");
					html.append(" type='text' maxlength='").append(field.getFieldLength()).append("' id='").append(field.getFieldName()+"_"+tablePhyName).append("'");
					html.append("onMouseOver='this.style.borderColor=\"#005896\"' onMouseOut='this.style.borderColor=\"#A1BCA3\"'");
					html.append("style='border: #A1BCA3 1px solid;'");
					
					if(field.getFieldType().equals("int"))
					{
						html.append("dataType='Integer' msg='此项必须填写整数'");
						if(field.getMustFill()!=null&&field.getMustFill()==1)
						{
							html.append(" require='true'");
						}
						else
						{
							html.append(" require='false'");
						}
					}
					else if(field.getFieldType().equals("number"))
					{
						html.append("dataType='Double' msg='此项必须填写浮点数'");
						if(field.getMustFill()!=null&&field.getMustFill()==1)
						{
							html.append(" require='true'");
						}
						else
						{
							html.append(" require='false'");
						}
					}
					else if(field.getMustFill()!=null&&field.getMustFill()==1){
						html.append("dataType='Require' msg='此项为必填'");
					}
					
					
					if(field.getValidCheckFormula()!=null&&!field.getValidCheckFormula().trim().equals("")){
						String vcfString = field.getValidCheckFormula();
						if(html.indexOf("dataType")<1){
							html.append(" dataType='Regexp'");
						}
						html.append(" checkregexp='").append(vcfString).append("' regexpmsg='不符合校验规则:"+vcfString+"'");
						
						
						
					}
					html.append(">");
					if(field.getMustFill()!=null&&field.getMustFill()==1)
						{
						html.append("&nbsp;<font color='red'>*</font>");
						}
					
				}else if(field.getFieldDisplayType().equals("DropdownList")){
					// 下拉菜单模式
					html.append("<select name='").append(field.getFieldName()+"_"+tablePhyName).append("'>");
					html.append("<option></option>");
					String fvalue = String.valueOf(valueMap.get(field.getFieldName()+"_"+tablePhyName));
					if(codeList!=null){
						for(CodeValue value:codeList){
							html.append("<option value='").append(value.getItemValue()).append("'");
							//if(fvalue.equals(value.getItemtext())||fvalue.equals(value.getId().toString()))
							if(fvalue.equals(value.getItemText())||fvalue.equals(value.getItemValue().toString()))
								html.append(" selected");
							html.append(">").append(value.getItemText()).append("</option>");
						}
					}
					html.append("</select>");
				}else if(field.getFieldDisplayType().equals("Radio")){
					// 单选框
					String fvalue = String.valueOf(valueMap.get(field.getFieldName()+"_"+tablePhyName));
					if(codeList!=null){
						for(CodeValue value:codeList){
							html.append("<input type='radio' name='").append(field.getFieldName()+"_"+tablePhyName).append("'");
							html.append(" value='").append(value.getItemValue()).append("' ");
							//if(fvalue.equals(value.getItemtext())||fvalue.equals(value.getId().toString()))
							if(fvalue.equals(value.getItemText())||fvalue.equals(value.getItemValue()))
								html.append(" checked");
							html.append(">").append(value.getItemText());
						}
					}
				}else if(field.getFieldDisplayType().equals("CheckBox")){
	
					String[] scheckList = (String[])valueMap.get(field.getFieldName()+"_"+tablePhyName);
					
					if (codeList != null) {
						for (CodeValue value : codeList) {
							html.append("<input type='CheckBox' name='")
									.append(field.getFieldName()+"_"+tablePhyName).append("'");
							html.append(" value='").append(value.getItemValue())
									.append("' ");
							if (scheckList!=null) {
								for (String tempalue : scheckList) {
									//if(value.getItemtext().equals(tempalue)||value.getId().toString().equals(tempalue))
									if(value.getItemText().equals(tempalue)||value.getItemValue().equals(tempalue))
										html.append(" checked");
								}
							}
							
							html.append(">").append(value.getItemText());
						}
					}
				}else if(field.getFieldDisplayType().equals("DatePick")){
					// 日期型
					html.append("<input name='").append(field.getFieldName()+"_"+tablePhyName).append("'");
					html.append(" style='width:100px' ");
					if(valueMap!=null&&valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(" value='").append(valueMap.get(field.getFieldName()+"_"+tablePhyName)).append("'");
					html.append("  type='text' class='Wdate' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\">");
				}else if(field.getFieldDisplayType().equals("ImageUpload")){
					html.append("<input  name='files' type='file' >");
					
					if(valueMap!=null&&valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null){
						html.append("<input name='").append(field.getFieldName()+"_"+tablePhyName)
						.append("'");
						html.append(" type=hidden value='true'>");
						html.append("&nbsp;&nbsp;<a href='readFile.action?tableId=").append(tableId);
						html.append("&rowId=").append(rowId);
						html.append("&fieldName=").append(field.getFieldName()).append("'>查看</a>");
					}
						
				}else if(field.getFieldDisplayType().equals("TextArea")){
					html.append("<textarea name='").append(field.getFieldName()+"_"+tablePhyName).append("'");
					if(field.getMustFill()!=null&&field.getMustFill()==1){
						html.append("dataType='Require' msg='此项为必填'");
					}
					html.append("class='txtbg' onMouseOver=\"this.className='txtbg_over';\"")
					.append("onMouseOut=\"this.className='txtbg';\"").append("style=\"width:100%;height:120px\">");
					if(valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(valueMap.get(field.getFieldName()+"_"+tablePhyName));
					html.append("</textarea>	");
				}else if(field.getFieldDisplayType().equals("Html")){

					 FCKeditor fck;
			         fck = new FCKeditor(request,  field.getFieldName()+"_"+tablePhyName); //作用域为request,名称remark
			         fck.setHeight("300");//编辑器的高度
			         fck.setBasePath("/webResources/fckeditor/");//你的应用目录
			         Object fckvalue = valueMap.get(field.getFieldName()+"_"+tablePhyName);
			         if (fckvalue!=null) {
			         fck.setValue(String.valueOf(fckvalue));//编辑器输入框的初始值
			         }
			         html.append(fck.createHtml());
							                   
				}else if(field.getFieldDisplayType().equals("MultiFiles")){
					Object MFvalue = valueMap.get(field.getFieldName()+"_"+tablePhyName);
					String strmFValue = "";

					strmFValue = MFvalue != null ? String.valueOf(MFvalue)
							: java.util.UUID.randomUUID().toString();

				

					html.append("<input name='").append(field.getFieldName()+"_"+tablePhyName)
							.append("'");
					html.append(" type=hidden value='").append(strmFValue).append("'");
					html.append(">");
					html.append("<div id='AllFiles'>");
					if (MFvalue != null) {
						for (Attachfiles att : atfList) {
							if (!strmFValue.equals(att.getFormRowGuid())) {
								continue;
							}
							html.append("<div id='big_file_" + filei + "'>");

							html.append("<a href='#' onclick='open_file(\""
									+ att.getRowguid() + "\")'>"
									+ att.getAttachName() + "</a> &nbsp;|");

							html
									.append("<input type='button' onclick=\"del_big_file("
											+ filei
											+ ",'"
											+ att.getId()
											+ "')\" value='删除'></div>");

							filei++;
						}
						
					}
					html.append("</div>");
					html
					.append("<input type='button'  value='选取文件上传'  onclick=javascript:window.open(\""
							+ request.getContextPath()
							+ "/flashupload.action?fileGroupGuid="
							+ strmFValue + "\")>");
					
					
					
				}
				html.append("</td>");
				i++;
				if(i%2==0||field.getGridMultiRows()==0)
					html.append("</tr><tr>");
			}
		}
		try{
			out.write(html.toString());
		}catch(IOException ioe){
			throw new JspException(ioe);
		}
		return super.doEndTag();
	}	

	public String getColumnListName() {
		return columnListName;
	}

	public void setColumnListName(String columnListName) {
		this.columnListName = columnListName;
	}

	public String getCodeMapName() {
		return codeMapName;
	}

	public void setCodeMapName(String codeMapName) {
		this.codeMapName = codeMapName;
	}

	public String getValueMapName() {
		return valueMapName;
	}

	public void setValueMapName(String valueMapName) {
		this.valueMapName = valueMapName;
	}

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

	public List<FormField> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<FormField> queryList) {
		this.queryList = queryList;
	}

	public Map<String, Object> getCodeMap() {
		return codeMap;
	}

	public void setCodeMap(Map<String, Object> codeMap) {
		this.codeMap = codeMap;
	}



	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public List<Attachfiles> getAtfList() {
		return atfList;
	}

	public void setAtfList(List<Attachfiles> atfList) {
		this.atfList = atfList;
	}


	public String getTablePhyName() {
		return tablePhyName;
	}

	public void setTablePhyName(String tablePhyName) {
		this.tablePhyName = tablePhyName;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
}
