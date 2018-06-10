package com.jshx.module.form.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.form.entity.FormField;

public class RecordQueryTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String queryListName;

	private String codeMapName;

	private String valueMapName;

	private String tablePhyName;
	
	private Map<String, Object> valueMap;

	private HttpServletRequest request;

	private JspWriter out;

	private List<FormField> queryList;

	private Map<String, Object> codeMap;

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {

		out = pageContext.getOut();
		request = (HttpServletRequest) pageContext.getRequest();
		Map<String, Object> map = (Map<String, Object>) request
				.getAttribute("map");
		queryList = (List<FormField>) map.get(queryListName);
		codeMap = (Map<String, Object>) request.getAttribute(codeMapName);
		valueMap = (Map<String, Object>) request.getAttribute(valueMapName);
		return super.doStartTag();
	}

	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		if (queryList != null && queryList.size() > 0) {
			int i = 0;
			for (FormField field : queryList) {
				/*
				 * 只有文本框，下拉框，单选框，多选框，日期选择框等可以被查询
				 */
				if (!(field.getFieldDisplayType().equals("TextBox")||
						field.getFieldDisplayType().equals("TextArea")||
						field.getFieldDisplayType().equals("DropdownList")||
						field.getFieldDisplayType().equals("Radio")||
						field.getFieldDisplayType().equals("CheckBox")||
						field.getFieldDisplayType().equals("DatePick")))
				{
					continue;
				}
				
				
				html.append("<td Class='tb_01' width='15%'>");
				html.append(field.getFieldDisplayName()).append("</td>");
				html.append("<td Class='tb_02' width='35%'>");
				List<CodeValue> codeList = new ArrayList<CodeValue>();
				if (codeMap != null) {
					codeList = (List<CodeValue>) codeMap.get(field
							.getFieldName()+ "_" + tablePhyName);
				}

				if (field.getFieldDisplayType().equals("TextBox")
						|| field.getFieldDisplayType().equals("TextArea")) {
					// 文本框类型输入方式
					html.append("<input name='").append(field.getFieldName()+ "_" + tablePhyName)
							.append("'");
					if (valueMap != null
							&& valueMap.get(field.getFieldName()+ "_" + tablePhyName) != null)
						html.append(" value='").append(
								valueMap.get(field.getFieldName()+ "_" + tablePhyName)).append("'");
					html.append(" type='text' id='").append(
							field.getFieldName()+ "_" + tablePhyName).append("'");
					html
							.append("onMouseOver='this.style.borderColor=\"#005896\"' onMouseOut='this.style.borderColor=\"#A1BCA3\"'");
					html.append("style='border: #A1BCA3 1px solid;'");
					/*屏蔽正则表达式验证，暂时不需要
					if (field.getValidCheckFormula() != null
							&& !field.getValidCheckFormula().trim().equals("")) {
						html.append(" onblur='patrn_check(\"").append(
								field.getValidCheckFormula()).append(
								"\",this,\"");
						html.append(field.getFieldDisplayName()).append("\")'");

					}
					*/
					html.append(">");
				} else if (field.getFieldDisplayType().equals("DropdownList")) {

					// 下拉菜单模式
					html.append("<select name='").append(field.getFieldName()+ "_" + tablePhyName)
					.append("' id='").append(field.getFieldName()+ "_" + tablePhyName).append("'> ");
					html.append("<option></option>");

					if (codeList != null) {
						for (CodeValue value : codeList) {
							html.append("<option value='")
									.append(value.getItemValue()).append("'");
							if (valueMap != null
									&&valueMap.get(field.getFieldName()+ "_" + tablePhyName)!=null&& valueMap.get(field.getFieldName()+ "_" + tablePhyName).equals(value
											.getItemValue().toString()))
								html.append(" selected");
							html.append(">").append(value.getItemText())
									.append("</option>");
						}
					}
					html.append("</select>");
				} else if (field.getFieldDisplayType().equals("Radio")) {
					// 单选框
					if (codeList != null) {
						for (CodeValue value : codeList) {
							html.append("<input type='radio' name='").append(
									field.getFieldName()+ "_" + tablePhyName).append("'");
							html.append(" id='").append(field.getFieldName()+ "_" + tablePhyName).append("' ");
							html.append(" value='").append(value.getItemValue())
									.append("' ");
							if (valueMap != null
								&&	valueMap.get(field.getFieldName()+ "_" + tablePhyName)!=null&& valueMap.get(field.getFieldName()+ "_" + tablePhyName).equals(value
											.getItemValue().toString()))
								html.append(" checked");
							html.append(">").append(value.getItemText());
						}
					}
				} else if (field.getFieldDisplayType().equals("CheckBox")) {
					// 多选框
					String[] scheckList = (String[])valueMap.get(field.getFieldName()+ "_" + tablePhyName);
					if (codeList != null) {
						for (CodeValue value : codeList) {

							html.append("<input type='CheckBox' name='")
									.append(field.getFieldName()+ "_" + tablePhyName).append("'");
							html.append(" id='").append(field.getFieldName()+ "_" + tablePhyName).append("' ");
							html.append(" value='").append(value.getItemValue())
									.append("' ");
							if (scheckList!=null) {
								for (String tempalue : scheckList) {
									if(value.getItemValue().toString().equals(tempalue))
										html.append(" checked");
								}
							}
							html
									.append(">").append(value.getItemText());
						}
					}
				} else if (field.getFieldDisplayType().equals("DatePick")) {
					// 日期型
					html.append("<input name='").append(field.getFieldName()+ "_" + tablePhyName)
							.append("_start'");
					html.append(" id='").append(field.getFieldName()+ "_" + tablePhyName).append("_start' ");
					//html.append(" style='width:100px' ");
					if (valueMap != null
							&& valueMap.get(field.getFieldName()+ "_" + tablePhyName + "_start") != null)
						html.append(" value='").append(
								valueMap.get(field.getFieldName()+ "_" + tablePhyName + "_start"))
								.append("'");
					html.append(" class='Wdate' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\\'").append(field.getFieldName()+ "_" + tablePhyName).append("_end\\')}'})\" type='text'>-");
					html.append("<input name='").append(field.getFieldName()+ "_" + tablePhyName)
							.append("_end'");
					html.append(" id='").append(field.getFieldName()+ "_" + tablePhyName).append("_end' ");
					//html.append(" style='width:100px' ");
					if (valueMap != null
							&& valueMap.get(field.getFieldName() + "_" + tablePhyName+ "_end") != null)
						html.append(" value='").append(
								valueMap.get(field.getFieldName() + "_" + tablePhyName+ "_end"))
								.append("'");
					html.append(" class='Wdate' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\\'").append(field.getFieldName()+ "_" + tablePhyName).append("_start\\')}'})\" type='text'>");
				}
				html.append("</td>");
				i++;
				if (i % 2 == 0)
					html.append("</tr><tr>");
			}
		}
		try {
			out.write(html.toString());
		} catch (IOException ioe) {
			throw new JspException(ioe);
		}

		return super.doEndTag();
	}

	public String getQueryListName() {
		return queryListName;
	}

	public void setQueryListName(String queryListName) {
		this.queryListName = queryListName;
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

	public String getTablePhyName() {
		return tablePhyName;
	}

	public void setTablePhyName(String tablePhyName) {
		this.tablePhyName = tablePhyName;
	}


}
