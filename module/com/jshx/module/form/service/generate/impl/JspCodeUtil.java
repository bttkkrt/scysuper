
package com.jshx.module.form.service.generate.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;

public class JspCodeUtil
{
    /**
     * 将FiledName转化为实体类的属性名，如ab_cd_ef转化为abCdEf，要求第1、2个字母转化为小写（如果有第2个字母）
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static final String convertPropertyWord(String fieldName) throws BasalException
    {
        if ((null != fieldName) && (0 < fieldName.trim().length()))
        {
            String temp = fieldName.trim();
            temp = temp.toLowerCase();
            while (temp.indexOf("_") > -1)
            {
                int index = temp.indexOf("_");
                int length = temp.length();
                if (length > index + 1)
                {
                    temp = temp.substring(0, index + 1) + temp.substring(index + 1, index + 2).toUpperCase()
                            + temp.substring(index + 2);
                    temp = temp.replaceFirst("_", "");
                }
                else
                {
                    temp = temp.replaceFirst("_", "");
                }
            }
            //强制把前二个字母转成小写
            if (temp.length() > 1)
            {
                temp = temp.substring(0, 2).toLowerCase() + temp.substring(2);
            }
            return temp;
        }
        else
        {
        	BasalException ex = new BasalException(BasalException.ERROR, Constants.FIELD_NAME_NULL_ERROR);
            throw ex;
        }

    }

    /**
     * 将FiledName转化为实体类的get/set方法名称（get/set后的字母），要求第1个字母大写，第2个字母转化为小写（如果有第2个字母）
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static final String convertMothodWord(String fieldName) {
        String result = convertPropertyWord(fieldName);
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }
    
    
    
    /**
     * 生成datagrid的查询条件
     * 
     * @param queryList
     * @return
     * @throws BusinessException
     */
    public static String genQueryParamsCode(List<FormField> queryList) {
    	StringBuffer html = new StringBuffer();
        if (queryList != null && queryList.size() > 0)
        {
        	int i = 0;
            for (FormField field : queryList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                String property = convertPropertyWord(field.getFieldName());
                if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //日期型
                    String mothodName = convertMothodWord(field.getFieldName());
                    html.append(" \"query").append(mothodName).append("Start\"");
                    html.append(" :$(\"#query").append(mothodName).append("Start\").val(),\r\n");
                    html.append(" \"query").append(mothodName).append("End\"");
                    html.append(" :$(\"#query").append(mothodName).append("End\").val()");
                }
                else{
                	html.append("\"${lowerclassname}.").append(property).append("\"");
                	html.append(": $(\"#").append(property).append("\").val()");
                }
                if(i<queryList.size()-1)
                	html.append(",\r\n");
                i++;
            }
        }
        return html.toString();
    }

    /**
     * 生成List页面的查询JSP代码
     * @param queryList
     * @param codeMap
     * @return
     * @throws BusinessException
     */
    public static String genQueryJspCode(List<FormField> queryList , Map<String, Code> codeMap) throws BasalException
    {
        StringBuffer html = new StringBuffer();
        if (queryList != null && queryList.size() > 0)
        {
            int i = 0;
            for (FormField field : queryList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                //只有文本框，下拉框，单选框，日期选择框等可以被查询，多选框不可以被查询
                if (!(field.getFieldDisplayType().equals("TextBox") 
                		|| field.getFieldDisplayType().equals("TextArea")
                        || field.getFieldDisplayType().equals("DropdownList")
                        || field.getFieldDisplayType().equals("Radio") 
                        || field.getFieldDisplayType().equals("DatePick")))
                {
                    continue;
                }
                String property = convertPropertyWord(field.getFieldName());
                html.append("\n\t\t\t\t").append("<th width=\"15%\">");
                html.append(field.getFieldDisplayName()).append("</th>");
                html.append("\n\t\t\t\t").append("<td width=\"35%\">");

                if (("TextBox".equalsIgnoreCase(field.getFieldDisplayType())) || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType())))
                {
                    // 文本框类型输入方式
                    html.append("<input name=\"${lowerclassname}.").append(property).append("\"");
                    html.append(" id=\"").append(property).append("\"");
                    html.append(" value=\"").append("${${lowerclassname}.").append(property).append("}\"");
                    html.append(" type=\"text\">");
                }
                else if ("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //下拉菜单模式
                    if (!"Blank".equalsIgnoreCase(field.getDataSource()))
                    {
                        html.append("<cus:SelectOneTag property=\"${lowerclassname}.").append(property).append("\" defaultText='请选择'");
                        if ("Codes".equalsIgnoreCase(field.getDataSource()))
                        {
                            //代码形式
                            Code code = codeMap.get(field.getDataSourceType());
                            if (null == code)
                            {
                            	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                throw ex;
                            }
                            String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                            html.append(" codeName=\"").append(codeName).append("\"");
                        }
                        else
                        {
                            //取值SQL形式
                            html.append("  codeSql=\"").append(field.getDefaultValue()).append("\"");
                        }
                        html.append(" value=\"${${lowerclassname}.").append(property).append("}\" />");
                    }
                    else
                    {
                        //无数据源情况
                        html.append("<select>");
                        html.append("<option value=\"\"></option>");
                        html.append("</select>");
                    }
                }
                else if (field.getFieldDisplayType().equals("Radio"))
                {
                    // 单选框
                    if (!"Blank".equalsIgnoreCase(field.getDataSource()))
                    {
                        html.append("<cus:hxradio property=\"${lowerclassname}.").append(property).append("\"");
                        if ("Codes".equalsIgnoreCase(field.getDataSource()))
                        {
                            //代码形式
                            Code code = codeMap.get(field.getDataSourceType());
                            if (null == code)
                            {
                            	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                throw ex;
                            }
                            String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                            html.append(" codeName=\"").append(codeName).append("\"");
                        }
                        else
                        {
                            //取值SQL形式
                            html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                        }

                        html.append(" value=\"${${lowerclassname}.").append(property).append("}\" needAddAll=\"true\" />");
                    }
                    else
                    {
                        //无数据源情况
                        html.append("<input type=\"radio\" value=\"\">");
                    }
                }
//                else if (field.getFieldDisplayType().equals("CheckBox"))
//                {
//                    // 多选框
//                    Code code = codeMap.get(field.getDataSourceType());
//                    if (null == code)
//                    {
//                        throw new Exception("无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
//                    }
//                    String codeName = codeMap.get(field.getDataSourceType()).getCodename();
//                    html.append("<cus:hxcheckbox property=\"${lowerclassname}.").append(property).append("\"");
//                    html.append(" codeName=\"").append(codeName).append("\"");
//                    html.append(" value=\"${${lowerclassname}.").append(property).append("}\"");
//                    html.append(" />");
//                }
                else if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //日期型
                    String mothodName = convertMothodWord(field.getFieldName());
                    html.append("<input name=\"query").append(mothodName).append("Start\"");
                    html.append(" id=\"query").append(mothodName).append("Start\"");
                    html.append(" value=\"").append("<fmt:formatDate type='both' value='${query").append(mothodName).append("Start}' />\"");
                    html.append(" type=\"text\" class=\"Wdate\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\\'query")
                    	.append(mothodName).append("End\\')}'})\" >");
                    html.append("\n\t\t\t\t\t-");
                    html.append("<input name=\"query").append(mothodName).append("End\"");
                    html.append(" id=\"query").append(mothodName).append("End\"");
                    html.append(" value=\"").append("<fmt:formatDate type='both' value='${query").append(mothodName).append("End}' />\"");
                    html.append(" type=\"text\" class=\"Wdate\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\\'query")
                    	.append(mothodName).append("Start\\')}'})\" >");
                }
                html.append("</td>");
                i++;
                if (i % 2 == 0)
                    html.append("\n\t\t\t").append("</tr>").append("\n\t\t\t").append("<tr>");
            }
        }
        return html.toString();
    }
    
    public static String genListColumn(List<FormField> displayList , Map<String, Code> codeMap) {
    	StringBuffer html = new StringBuffer();
    	int i = 0;
    	int configedWidth = 100;
    	for (FormField field : displayList)
        {
            if (1 != field.getDispInGrid())
            {
                continue;
            }
            try{
            	Integer width = Integer.valueOf(field.getColumnWidth());
            	configedWidth += width;
            }catch(Exception e){
            	
            }
        }
        for (FormField field : displayList)
        {
            if (1 != field.getDispInGrid())
            {
                continue;
            }
            //TODO 读取宽度设置
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
            String property = convertPropertyWord(field.getFieldName());
            String title = field.getFieldDisplayName();
            
            String width = "100";
            if(field.getColumnWidth()!=null && !field.getColumnWidth().trim().equals("")){
            	Float f = Float.valueOf(field.getColumnWidth());
            	if(f<1 ){
            		width = "fixWidth('" + field.getColumnWidth() + "', " + configedWidth + ")";
            	}else
            		width = field.getColumnWidth();
            }
            
            if (("TextBox".equalsIgnoreCase(field.getFieldDisplayType())) || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType())))
            {
                // 文本框类型输入方式
                html.append("{field:'").append(property).append("',title:'").append(title).append("',width:").append(width).append("}");
            }else if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                //日期类型
            	html.append("{field:'").append(property).append("',title:'").append(title).append("',width:").append(width).append("}");
            }
            else {
            	html.append("{field:'").append(property).append("',title:'").append(title).append("',width:").append(width).append("}");
            }
            
            html.append(",\r\n");
            i++;
        }
        // 加入操作列表
        html.append("{field:'op',title:'操作',width:100,formatter:function(value,rec){return \"<a class='btn_01_mini' onclick=view('\"+rec.id+\"') >查看<b></b></a>&nbsp;<a class='btn_01_mini' onclick=edit('\"+rec.id+\"')>修改<b></b></a>\";}}");
        return html.toString();
    }

    /**
     * 生成List页面的列表JSP代码
     * @param displayList
     * @param codeMap
     * @return
     * @throws BusinessException
     */
    public static String genListJspCode(List<FormField> displayList , Map<String, Code> codeMap) throws BasalException
    {
        StringBuffer html = new StringBuffer();
        for (FormField field : displayList)
        {
            //过滤掉ROW_ID
            //if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
            //{
            //    continue;
            //}
            if (1 != field.getDispInGrid())
            {
                continue;
            }
            //只有文本框，下拉框，单选框，多选框，日期选择框等可以被查询
            if (!(field.getFieldDisplayType().equals("TextBox") 
            		|| field.getFieldDisplayType().equals("TextArea")
                    || field.getFieldDisplayType().equals("DropdownList")
                    || field.getFieldDisplayType().equals("Radio") 
                    || field.getFieldDisplayType().equals("CheckBox") 
                    || field.getFieldDisplayType().equals("DatePick")))
            {
                continue;
            }
            String property = convertPropertyWord(field.getFieldName());
            html.append("\n\t\t\t\t\t").append("<display:column title=\"").append(field.getFieldDisplayName())
            	.append("\" style=\"text-align:center\" headerClass=\"w2\">");
            if (("TextBox".equalsIgnoreCase(field.getFieldDisplayType())) || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType())))
            {
                // 文本框类型输入方式
                html.append("\n\t\t\t\t\t\t\t").append("<c:out value=\"${row.").append(property).append("}\" />");

            }
            else if (("DropdownList".equalsIgnoreCase(field.getFieldDisplayType())) || ("Radio".equalsIgnoreCase(field.getFieldDisplayType())))
            {
                // 下拉菜单、单选模式，使用自定义标签
                html.append("\n\t\t\t\t\t\t\t").append("<cus:hxlabel");
                if ("Codes".equalsIgnoreCase(field.getDataSource()))
                {
                    //代码形式
                    Code code = codeMap.get(field.getDataSourceType());
                    if (null == code)
                    {
                    	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        throw ex;
                    }
                    String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                    html.append(" codeName=\"").append(codeName).append("\"");
                }
                else
                {
                    //取值SQL形式
                    html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                }

                html.append(" itemValue=\"${row.").append(property).append("}\" />");
            }
            else if ("CheckBox".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                // 多选模式，使用自定义标签
                html.append("\n\t\t\t\t\t\t\t").append("<cus:hxmulselectlabel");
                if ("Codes".equalsIgnoreCase(field.getDataSource()))
                {
                    //代码形式
                    Code code = codeMap.get(field.getDataSourceType());
                    if (null == code)
                    {
                    	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        //ex.add("EDP001", Constants.EXCEPTION_LEVEL_ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        throw ex;
                    }
                    String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                    html.append(" codeName=\"").append(codeName).append("\"");
                }
                else
                {
                    //取值SQL形式
                    html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                }

                html.append(" itemValue=\"${row.").append(property).append("}\"/>");
            }
            else if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                //日期类型
                html.append("\n\t\t\t\t\t\t\t").append("<fmt:formatDate type=\"both\" value=\"${row.").append(property).append("}\" />");
            }
            html.append("\n\t\t\t\t\t").append("</display:column>");
        }
        return html.toString();
    }

    /**
     * 生成详细信息页面的详细信息JSP代码
     * @param columnList
     * @param codeMap
     * @return
     * @throws BusinessException
     */
    public static String genDetailJspCode(FormTable table, List<FormField> columnList , Map<String, Code> codeMap) throws BasalException
    {
    	String labelWidth = table.getLabelWidth();
    	String textWidth = table.getTextWidth();
    	if(labelWidth==null || labelWidth.trim().equals(""))
    		labelWidth = "15%";
    	if(textWidth==null || textWidth.trim().equals(""))
    		textWidth = "35%";
        StringBuffer html = new StringBuffer();
        int i = 0;
        for (FormField field : columnList)
        {
            //过滤掉不需要在详细信息中显示的
            if (1 != field.getDispInDetail())
            {
                continue;
            }
            String property = convertPropertyWord(field.getFieldName());
            String methodName = convertMothodWord(field.getFieldName());
            if (field.getGridMultiRows() == 0)
            {
                if (i % 2 != 0)
                {
                    html.append("\n\t\t\t\t").append("</tr>").append("\n\t\t\t\t").append("<tr>");

                }
                if (i % 2 == 0)
                {
                    i++;
                }
            }
            
            html.append("\n\t\t\t\t\t").append("<th width=\"").append(labelWidth).append("\">");
            html.append(field.getFieldDisplayName()).append("</th>");
            html.append("\n\t\t\t\t\t").append("<td");

            if (field.getGridMultiRows() == 0)
            {
                html.append(" colspan=\"3\"");
            }
            else
            {
                html.append(" width=\"").append(textWidth).append("\" ");
            }

            if (field.getGridWidth() != null && field.getControlWidth() != null)
                html.append(" height=\"").append(field.getGridWidth() * field.getControlWidth()).append("\"");
            html.append(">");
            if ("TextBox".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                //文本输入框
                html.append("${${lowerclassname}.").append(property).append("}");
            }
            else if ("TextArea".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                //文本区输入
                html.append("<textarea readOnly name=\"${lowerclassname}.").append(property).append("\"");
                html.append(" style=\"width:100%;height:120px\">");
                html.append("${${lowerclassname}.").append(property).append("}");
                html.append("</textarea>");
            }
            else if (("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                    || ("Radio".equalsIgnoreCase(field.getFieldDisplayType())))
            {
                // 下拉菜单、单选模式，使用自定义标签
                html.append("<cus:hxlabel");
                if ("Codes".equalsIgnoreCase(field.getDataSource()))
                {
                    //代码形式
                    Code code = codeMap.get(field.getDataSourceType());
                    if (null == code)
                    {
                    	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        //ex.add("EDP001", Constants.EXCEPTION_LEVEL_ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        throw ex;
                    }
                    String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                    html.append(" codeName=\"").append(codeName).append("\"");
                }
                else
                {
                    //取值SQL形式
                    html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                }
                html.append(" itemValue=\"${${lowerclassname}.").append(property).append("}\"");
                html.append(" />");
            }
            else if ("CheckBox".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                // 多选菜单模式，使用自定义标签
                html.append("<cus:hxmulselectlabel");
                if ("Codes".equalsIgnoreCase(field.getDataSource()))
                {
                    //代码形式
                    Code code = codeMap.get(field.getDataSourceType());
                    if (null == code)
                    {
                    	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        //ex.add("EDP001", Constants.EXCEPTION_LEVEL_ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                        throw ex;
                    }
                    String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                    html.append(" codeName=\"").append(codeName).append("\"");
                }
                else
                {
                    //取值SQL形式
                    html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                }
                html.append(" itemValue=\"${${lowerclassname}.").append(property).append("}\"");
                html.append(" />");
            }
            else if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                //日期型
                html.append("<fmt:formatDate type=\"both\" value=\"${${lowerclassname}.").append(property).append("}\" />");
            }
            else if ("Html".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                //Html
                html.append("<s:property value=\"${lowerclassname}.").append(property).append("\" escape=\"false\"/>");
            }
            else if ("ImageUpload".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                html.append("<s:if test=\"${lowerclassname}.").append(property).append("ContentName!=null\">");
                html.append("<a href=\"view").append(methodName).append("Image.action?${lowerclassname}.id=");
                html.append("${${lowerclassname}.id}\" target=\"_black\">查看</a></s:if>");
            }
            else if ("MultiFiles".equalsIgnoreCase(field.getFieldDisplayType()))
            {
                html.append("\n\t\t\t\t\t\t").append("<s:iterator value=\"filelist").append(methodName).append("\" status=\"st\">");
                html.append("\n\t\t\t\t\t\t\t").append("<a href=\"#\" onclick=\"open_file('<s:property value=\"rowguid\" />')\">")
            	.append("<s:property value=\"attachName\" />").append("</a>");
                html.append("\n\t\t\t\t\t\t").append("</s:iterator>\n\t\t\t\t\t");
            }
            html.append("</td>");
            i++;
            if (i % 2 == 0 || field.getGridMultiRows() == 0)
                html.append("\n\t\t\t\t").append("</tr>").append("\n\t\t\t\t").append("<tr>");
        }
        return html.toString();
    }

    /**
     * 生成新增/修改页面的详细信息JSP代码
     * @param columnList
     * @param codeMap
     * @return
     * @throws BusinessException
     */
    public static String genEditJspCode(FormTable table,List<FormField> columnList , Map<String, Code> codeMap) 
    {
    	String labelWidth = table.getLabelWidth();
    	String textWidth = table.getTextWidth();
    	if(labelWidth==null)
    		labelWidth = "15%";
    	if(textWidth==null)
    		textWidth = "35%";
    	
        StringBuffer html = new StringBuffer();
        if (columnList != null && columnList.size() > 0)
        {
            int i = 0;
            for (FormField field : columnList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                //过滤掉不需要在详细信息中显示的
                if (1 != field.getDispInDetail())
                {
                    continue;
                }
                if (field.getGridMultiRows() == 0)
                {
                    if (i % 2 != 0)
                    {
                        html.append("\n\t\t\t\t").append("</tr>").append("\n\t\t\t\t").append("<tr>");
                    }
                    if (i % 2 == 0)
                    {
                        i++;
                    }
                }
                html.append("\n\t\t\t\t\t").append("<th width=\"").append(labelWidth).append("\">");
                html.append(field.getFieldDisplayName()).append("</th>");
                html.append("\n\t\t\t\t\t").append("<td");
                if (field.getGridMultiRows() == 0)
                {
                    html.append(" colspan=\"3\"");
                }

                else
                    html.append(" width=\"").append(textWidth).append("\"");
                if (field.getGridWidth() != null && field.getControlWidth() != null)
                    html.append(" height=\"").append(field.getGridWidth() * field.getControlWidth()).append("\"");
                html.append(">");
                String property = convertPropertyWord(field.getFieldName());
                String methodName = convertMothodWord(field.getFieldName());
                if (field.getFieldDisplayType().equals("TextBox"))
                {
                    // 文本框类型输入方式
                    html.append("<input name=\"${lowerclassname}.").append(property).append("\"");
                    html.append(" value=\"").append("${${lowerclassname}.").append(property).append("}\"");
                    html.append(" type=\"text\"");
                 
                	if(field.getFieldType().equals("int"))
					{
						html.append(" datatype=\"n1-").append(field.getFieldLength()).append("\" errormsg='此项请填入整数'");
						if(field.getMustFill()!=null&&field.getMustFill()==1)
						{
							//html.append(" require=\"true\"");
						}
						else
						{
							html.append(" ignore=\"ignore\"");
						}
					}
					else if(field.getFieldType().equals("number"))
					{
						//html.append(" dataType=\"Double\" msg=\"此项必须填写浮点数\"");
						// /^(-?\d{1,2})(\.\d{1,3})?$/
						html.append(" datatype='/^(-?\\d{1,").append(field.getFieldLength()).append("})(\\.\\d{1,").append(field.getDecimalLength()).append("})?$/' errormsg='此处请填入浮点数'");
						if(field.getMustFill()!=null&&field.getMustFill()==1)
						{
							//html.append(" require=\"true\"");
						}
						else
						{
							html.append(" ignore=\"ignore\"");
						}
					}
					else if(field.getMustFill()!=null&&field.getMustFill()==1){
						//html.append(" dataType=\"Require\" msg=\"此项为必填\"");
						html.append(" datatype=\"*1-").append(field.getFieldLength()/2).append("\" errormsg='此项为必填'");
					}

                    if (field.getValidCheckFormula() != null && !field.getValidCheckFormula().trim().equals(""))
                    {
                    	/**
                    	if(html.indexOf("datatype")<1)
                        {
                            html.append(" datatype=\"Regexp\"");
                        }
                        */
                        html.append(" datatype=\"/").append(field.getValidCheckFormula().trim()).append("/\"");
                    }
                    /*
                     * 下面这种做法会导致只能检查一个正则表达式
                     */
                    /*
                    else if ("int".equalsIgnoreCase(field.getFieldType()))
                    {
                        //如果没有正则表达式闲置，字段类型是整数，则加上整数正则表达式校验
                        if (field.getMustFill() == null || field.getMustFill() != 1)
                        {
                            html.append(" dataType='Regexp'");
                        }
                        html.append(" checkregexp=\"^-?[1-9]\\d*$\"");
                    }
                    else if (("decimal".equalsIgnoreCase(field.getFieldType()))
                            || ("number".equalsIgnoreCase(field.getFieldType())))
                    {
                        //如果没有正则表达式闲置，字段类型是浮点数，则加上浮点数正则表达式校验
                        if (field.getMustFill() == null || field.getMustFill() != 1)
                        {
                            html.append(" dataType='Regexp'");
                        }
                        html.append(" checkregexp=\"(^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$|^-?[1-9]\\d*$)\"");
                    }
                    */
                    if((null != field.getFieldLength())&&(0 < field.getFieldLength()))
                    {
                        html.append(" maxlength=\"").append(field.getFieldLength()/2 + field.getDecimalLength()).append("\"");
                    }
                    html.append(">");
                    if (field.getMustFill() != null && field.getMustFill() == 1)
                    {
                        html.append("<font style='color:red'>*</font>");
                    }
                }
                else if (field.getFieldDisplayType().equals("TextArea"))
                {
                    html.append("<textarea name=\"${lowerclassname}.").append(property).append("\"");
                    if (field.getMustFill() != null && field.getMustFill() == 1)
                    {
                        //html.append("dataType=\"Require\" msg=\"此项为必填\"");
                    	html.append(" datatype=\"*1-").append(field.getFieldLength()/2).append("\" errormsg='此项为必填'");
                    }
                    if (field.getValidCheckFormula() != null && !field.getValidCheckFormula().trim().equals(""))
                    {
                    	/**
                        if (field.getMustFill() == null || field.getMustFill() != 1)
                        {
                            html.append(" dataType=\"Regexp\"");
                        }
                        html.append(" checkregexp=\"").append(field.getValidCheckFormula()).append("\"");
                        */
                    	html.append(" datatype=\"/").append(field.getValidCheckFormula().trim()).append("/\"");
                    }
                    html.append(" style=\"width:100%;height:120px\">");
                    html.append("${${lowerclassname}.").append(property).append("}");
                    html.append("</textarea>");
                    if (field.getMustFill() != null && field.getMustFill() == 1)
                    {
                        html.append("<font style='color:red'>*</font>");
                    }
                }
                else if ("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //下拉菜单模式
                    if (false == "Blank".equalsIgnoreCase(field.getDataSource()))
                    {
                        html.append("<cus:SelectOneTag property=\"${lowerclassname}.").append(property).append("\" defaultText='请选择'");
                        if ("Codes".equalsIgnoreCase(field.getDataSource()))
                        {
                            //代码形式
                            Code code = codeMap.get(field.getDataSourceType());
                            if (null == code)
                            {
                            	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                //ex.add("EDP001", Constants.EXCEPTION_LEVEL_ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                throw ex;
                            }
                            String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                            html.append(" codeName=\"").append(codeName).append("\"");
                        }
                        else
                        {
                            //取值SQL形式
                            html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                        }
                        html.append(" value=\"${${lowerclassname}.").append(property).append("}\"");
                        if (field.getMustFill() != null && field.getMustFill() == 1)
                        {
                        	html.append(" datatype=\"*1-").append(field.getFieldLength()/2).append("\" errormsg='此项为必填'");
                            //html.append(" dataType=\"Require\" msg=\"此项为必选\"");
                        }
                        html.append(" />");
                        if (field.getMustFill() != null && field.getMustFill() == 1)
                        {
                            html.append("<font style='color:red'>*</font>");
                        }
                    }
                    else
                    {
                        //无数据源情况
                        html.append("<select>");
                        html.append("<option value=\"\"></option>");
                        html.append("</select>");
                    }
                }
                else if ("Radio".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //单选框
                    if (false == "Blank".equalsIgnoreCase(field.getDataSource()))
                    {
                        html.append("<cus:hxradio property=\"${lowerclassname}.").append(property).append("\"");
                        if ("Codes".equalsIgnoreCase(field.getDataSource()))
                        {
                            //代码形式
                            Code code = codeMap.get(field.getDataSourceType());
                            if (null == code)
                            {
                            	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                //ex.add("EDP001", Constants.EXCEPTION_LEVEL_ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                throw ex;
                            }
                            String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                            html.append(" codeName=\"").append(codeName).append("\"");
                        }
                        else
                        {
                            //取值SQL形式
                            html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                        }
                        html.append(" value=\"${${lowerclassname}.").append(property).append("}\"");
                        html.append(" />");
                        if (field.getMustFill() != null && field.getMustFill() == 1)
                        {
                            html.append("<font style='color:red'>*</font>");
                        }
                    }
                    else
                    {
                        //无数据源情况
                        html.append("<input type=\"radio\" value=\"\">");
                    }
                }
                else if ("CheckBox".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //多选框
                    if (false == "Blank".equalsIgnoreCase(field.getDataSource()))
                    {
                        html.append("<cus:hxcheckbox property=\"${lowerclassname}.").append(property).append("\"");
                        if ("Codes".equalsIgnoreCase(field.getDataSource()))
                        {
                            //代码形式
                            Code code = codeMap.get(field.getDataSourceType());
                            if (null == code)
                            {
                            	BasalException ex = new BasalException(BasalException.ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                //ex.add("EDP001", Constants.EXCEPTION_LEVEL_ERROR, "无法获取CodeID为" + field.getDataSourceType() + "对应的Code对象");
                                throw ex;
                            }
                            String codeName = codeMap.get(field.getDataSourceType()).getCodeName();
                            html.append(" codeName=\"").append(codeName).append("\"");
                        }
                        else
                        {
                            //取值SQL形式
                            html.append(" codeSql=\"").append(field.getDefaultValue()).append("\"");
                        }
                        html.append(" value=\"${${lowerclassname}.").append(property).append("}\"");
                        html.append(" />");
                        if (field.getMustFill() != null && field.getMustFill() == 1)
                        {
                            html.append("<font style='color:red'>*</font>");
                        }
                    }
                    else
                    {
                        //无数据源情况
                        html.append("<input type=\"checkbox\" name=\"\">");
                    }
                }
                else if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //日期型
                    html.append("<input name=\"${lowerclassname}.").append(property).append("\"");
                    html.append(" value=\"").append("<fmt:formatDate type='both' value='${${lowerclassname}.").append(property).append("}' />\"");
                    if (field.getMustFill() != null && field.getMustFill() == 1)
                    {
                    	html.append(" datatype=\"*1-30\" errormsg='此项为必填'");
                        //html.append(" dataType=\"Require\" msg=\"此项为必填\"");
                    }
                    html.append(" type=\"text\" class=\"Wdate\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\">");
                    if (field.getMustFill() != null && 1 == field.getMustFill())
                    {
                        html.append("<font style='color:red'>*</font>");
                    }
                }
                else if ("Html".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //HTML
                	html.append("<textarea id=\"${lowerclassname}.").append(property).append("\" name=\"${lowerclassname}.").append(property).append("\" style=\"width:85%;height:300px;visibility:hidden;\">\r\n");
                	html.append("${${lowerclassname}.").append(property).append("}\r\n");
                	html.append("</textarea>");
                	/**
                    html.append("<FCK:editor basePath=\"/webResources/fckeditor\" instanceName=\"${lowerclassname}.").append(property).append("\"");
                    html.append("  height=\"400px\">");
                    html.append("<jsp:attribute name=\"value\">${${lowerclassname}.").append(property).append("}</jsp:attribute>");
                    html.append("</FCK:editor>");
                    */
                }
                else if ("ImageUpload".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    html.append("<input type=\"file\" name=\"file").append(methodName).append("\">");
                    if (field.getMustFill() != null && field.getMustFill() == 1)
                    {
                        html.append("<font style='color:red'>*</font>");
                    }
                    html.append("<s:if test=\"${lowerclassname}.").append(property).append("ContentName!=null\">");
                    html.append("<a href=\"view").append(methodName).append("Image.action?${lowerclassname}.id=");
                    html.append("${{${lowerclassname}.id}\" target=\"_black\">查看</a></s:if>");
                }
                else if ("MultiFiles".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    html.append("\n\t\t\t\t\t\t").append("<s:iterator value=\"filelist").append(methodName).append("\" status=\"st\">");
                    html.append("\n\t\t\t\t\t\t\t").append("<a href=\"#\" onclick=\"open_file('<s:property value=\"rowguid\" />')\">")
                    	.append("<s:property value=\"attachName\" />").append("</a>");
                    html.append("&nbsp;<input type=\"button\" onclick=\"del_big_file(<s:property value=\"#st.index\"/>,'<s:property value=\"rowguid\"/>')\" value=\"删除'\">");
                    html.append("\n\t\t\t\t\t\t").append("</s:iterator>");
                    html.append("\n\t\t\t\t\t\t").append("<s:if test=\"${lowerclassname}.").append(property).append("!=null\">");
                    html.append("\n\t\t\t\t\t\t\t").append("<s:set name=\"").append(field.getFieldName())
                    	.append("uuid\" value=\"${lowerclassname}.").append(property).append("\" scope=\"request\" />");
                    html.append("\n\t\t\t\t\t\t").append("</s:if>");
                    html.append("\n\t\t\t\t\t\t").append("<s:else>");
                    html.append("\n\t\t\t\t\t\t\t").append("<s:set name=\"").append(field.getFieldName())
                    	.append("uuid\" value=\"@java.util.UUID@randomUUID()\" scope=\"request\"/>");
                    html.append("\n\t\t\t\t\t\t").append("</s:else>");
                    html.append("\n\t\t\t\t\t\t").append("<input name=\"${lowerclassname}.").append(property)
                    	.append("\" type=\"hidden\" value=\"${").append(field.getFieldName()).append("uuid}\">");
                    html.append("<input type=\"button\"  value=\"选取文件上传\" onclick=\"javascript:window.open('${ctx}/flashupload.action?fileGroupGuid=${")
                    	.append(field.getFieldName()).append("uuid}')\">");

                }
                html.append("</td>");
                i++;
                if (i % 2 == 0 || field.getGridMultiRows() == 0)
                    html.append("\n\t\t\t\t").append("</tr>").append("\n\t\t\t\t").append("<tr>");
            }
        }
        return html.toString();
    }

    /**
     * 生成新增/修改页面的Fakeitor代码
     * @param columnList
     * @return
     * @throws BusinessException
     */
    public static String genFakeditorScriptCode(List<FormField> columnList) {
    	StringBuffer result = new StringBuffer();
        for (FormField formField : columnList)
        {
            if ("Html".equalsIgnoreCase(formField.getFieldDisplayType()))
            {
            	String property = convertPropertyWord(formField.getFieldName());
            	result.append("<link rel=\"stylesheet\" href=\"${ctx}/webResources/kindeditor/plugins/code/prettify.css\" />\r\n");
            	result.append("<script charset=\"utf-8\" src=\"${ctx}/webResources/kindeditor/kindeditor-all-min.js\"></script>\r\n");
            	result.append("<script charset=\"utf-8\" src=\"${ctx}/webResources/kIndeditor/lang/zh_CN.js\"></script>\r\n");
            	result.append("<script charset=\"utf-8\" src=\"${ctx}/webResources/kindeditor/plugins/code/prettify.js\"></script>\r\n");
            	result.append("<script>\r\n");
            	result.append("KindEditor.ready(function(K) {\r\n");
            	result.append("var editor = K.create('textarea[name=\"${lowerclassname}.").append(property).append("\"]', {\r\n");
            	result.append("cssPath : '${ctx}/webResources/kindeditor/plugins/code/prettify.css',\r\n");
            	result.append("uploadJson : '${ctx}/webResources/kindeditor/upload_json.jsp',\r\n");
            	result.append("fileManagerJson : '${ctx}/webResources/kindeditor/file_manager_json.jsp',\r\n");
            	result.append("allowFileManager : true,\r\n");
            	result.append("afterCreate : function() {\r\n");
            	result.append("var self = this;\r\n");
            	result.append("K.ctrl(document, 13, function() {\r\n");
            	result.append("self.sync();\r\n");
            	result.append("save();\r\n");
            	result.append("});\r\n");
            	result.append("K.ctrl(self.edit.doc, 13, function() {\r\n");
            	result.append("self.sync();\r\n");
            	result.append("save();\r\n");
            	result.append("});\r\n");
            	result.append("},\r\n");
            	result.append("afterBlur: function(){this.sync();}\r\n");
            	result.append("});\r\n");
            	result.append("prettyPrint();\r\n");
            	result.append("});\r\n");
            	result.append("</script>\r\n");
            	/**
            	KindEditor.ready(function(K) {
        			var editor = K.create('textarea[name="appAdvertisement.adsContent"]', {
        				cssPath : '${ctx}/webResources/kindeditor/plugins/code/prettify.css',
        				uploadJson : '${ctx}/webResources/kindeditor/upload_json.jsp',
        				fileManagerJson : '${ctx}/webResources/kindeditor/file_manager_json.jsp',
        				allowFileManager : true,
        				afterCreate : function() {
        					var self = this;
        					K.ctrl(document, 13, function() {
        						self.sync();
        						save();
        					});
        					K.ctrl(self.edit.doc, 13, function() {
        						self.sync();
        						save();
        					});
        				},
        				afterBlur: function(){this.sync();}
        			});
        			prettyPrint();
        		});
        		*/
                break;
            }
        }
        return result.toString();
    }

    /**
     * 生成校验单选框、多选框必选的JS代码
     * @param columnList
     * @return
     * @throws Exception
     */
    public static String genCheckScriptCode(List<FormField> columnList)    {
        StringBuffer result = new StringBuffer();
        for (FormField field : columnList)
        {
            //判断是否必填
            if ((null != field.getMustFill()) && (1 == field.getMustFill()))
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                //判断单选多选框
                if (("Radio".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("CheckBox".equalsIgnoreCase(field.getFieldDisplayType())))
                {
                    String property = convertPropertyWord(field.getFieldName());
                    result.append("\n\t\t\t\t").append("var ches = document.getElementsByName(\"${lowerclassname}.").append(
                            property).append("\");");
                    result.append("\n\t\t\t\t").append("var ischecked = false;");
                    result.append("\n\t\t\t\t").append("for(var i=0;i<ches.length;i++)");
                    result.append("\n\t\t\t\t").append("{");
                    result.append("\n\t\t\t\t\t").append("if(ches[i].checked)");
                    result.append("\n\t\t\t\t\t").append("{");
                    result.append("\n\t\t\t\t\t\t").append("ischecked = true;");
                    result.append("\n\t\t\t\t\t\t").append("break;");
                    result.append("\n\t\t\t\t\t").append("}");
                    result.append("\n\t\t\t\t").append("}");
                    result.append("\n\t\t\t\t").append("if(!ischecked)");
                    result.append("\n\t\t\t\t").append("{");
                    result.append("\n\t\t\t\t\t").append("$.messager.alert('提示',\"").append(field.getFieldDisplayName()).append(
                            "至少选择一项\");");
                    result.append("\n\t\t\t\t\t").append("return false;");
                    result.append("\n\t\t\t\t").append("}");
                }
            }
        }
        return result.toString();
    }

    /**
     * 删除制定路径的所有文件和文件夹，包括子文件及子文件夹
     * @param path
     * @return
     */
    public static boolean delAllFile(String path)
    {
        boolean bea = false;
        File file = new File(path);
        if (!file.exists())
        {
            return bea;
        }
        if (!file.isDirectory())
        {
            return bea;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++)
        {
            if (path.endsWith(File.separator))
            {
                temp = new File(path + tempList[i]);
            }
            else
            {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile())
            {
                temp.delete();
            }
            if (temp.isDirectory())
            {
                delAllFile(path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
                bea = true;
            }
        }
        return bea;
    }

    /**
     * 删除文件夹，包括子文件夹和子文件
     * @param folderPath
     */
    public static void delFolder(String folderPath)
    {
        try
        {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete();
        }
        catch (Exception e)
        {
        }
    }
}
