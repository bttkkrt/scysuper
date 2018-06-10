package com.jshx.module.form.web.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.jshx.module.form.entity.Attachfiles;
import com.jshx.module.form.entity.FormField;
//import com.jshx.module.mail.entity.Attachfiles;
//import com.jshx.module.mail.service.AttachfileManager;

@SuppressWarnings("serial")
public class RecordViewTag extends TagSupport {

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
	
	List<Attachfiles> atfList ;
	
	
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
	
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		
		
		if(queryList!=null&&queryList.size()>0){
			int i = 0;
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
					if(i%2==0)
					{
						i++;
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
				{
					html.append(" width='35%'");
				}
					
				if(field.getGridWidth()!=null&&field.getControlWidth()!=null)
					html.append(" hight='").append(field.getGridWidth()*field.getControlWidth()).append("'");
				html.append(">");

				
				if(field.getFieldDisplayType().equals("TextBox")||field.getFieldDisplayType().equals("DropdownList")||field.getFieldDisplayType().equals("Radio")){
					// 文本框类型输入方式以及下拉菜单模式
					if(valueMap!=null&&valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(valueMap.get(field.getFieldName()+"_"+tablePhyName));
					
				}else if(field.getFieldDisplayType().equals("CheckBox")){
					// 多选框
					Object sObject =  valueMap.get(field.getFieldName()+"_"+tablePhyName);
					
					if(sObject!=null)
					{
						String[] scheckList = (String[])(sObject);
					for (String tempalue : scheckList) {
						html.append(tempalue+"，");
					}
					if(scheckList.length>0)
					{
					html.deleteCharAt(html.length()-1);
					}
					}
				}else if(field.getFieldDisplayType().equals("DatePick")){
					// 日期型
					if(valueMap!=null&&valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(valueMap.get(field.getFieldName()+"_"+tablePhyName));
				}else if(field.getFieldDisplayType().equals("ImageUpload")){
					if(valueMap!=null&&valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null){
						html.append("<a href='readFile.action?tableId=").append(tableId);
						html.append("&rowId=").append(rowId);
						html.append("&fieldName=").append(field.getFieldName()).append("'>查看</a>");
					}
						
				}else if(field.getFieldDisplayType().equals("TextArea")){
					html.append("<textarea readOnly name='").append(field.getFieldName()+"_"+tablePhyName).append("'");
					html.append("class='txtbg' onMouseOver=\"this.className='txtbg_over';\"")
					.append("onMouseOut=\"this.className='txtbg';\"").append("style=\"width:100%;height:120px\">");
					if(valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(valueMap.get(field.getFieldName()+"_"+tablePhyName));
					html.append("</textarea>	");
				}
				else if(field.getFieldDisplayType().equals("Html")){
					
					if(valueMap.get(field.getFieldName()+"_"+tablePhyName)!=null)
						html.append(valueMap.get(field.getFieldName()+"_"+tablePhyName));
				}
				
				else if(field.getFieldDisplayType().equals("MultiFiles")){
					Object MFvalue = valueMap.get(field.getFieldName()+"_"+tablePhyName);
						
					if (MFvalue != null) {
						
						String strmFValue = String.valueOf(MFvalue);						
						for (Attachfiles att : atfList) {
							if (!strmFValue.equals(att.getFormRowGuid())) {
								continue;
							}
							html.append("<a href='#' onclick='open_file(\""+att.getRowguid()+"\")'>"+att.getAttachName()+"</a> &nbsp;|");
						}
						
					}
					//<c:forEach  items="${model.attachfiles}" var="attach">
					//<a href='#' onclick='open_file(${attach.attachId})'>${attach.attachName}</a> &nbsp;|
					//</c:forEach>
					
					
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
