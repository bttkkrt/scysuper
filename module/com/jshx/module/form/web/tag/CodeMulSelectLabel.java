
package com.jshx.module.form.web.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;

public class CodeMulSelectLabel extends SimpleTagSupport
{
    /**
     * Codes表中的CodeName
     */
    private String codeName;

    /**
     * 取值SQL语句
     */
    private String codeSql;

    /**
     * Item的值，如果是多选，则值以逗号隔开，对应CodeValue表中的ItemValue
     */
    private String itemValue;

    private static CodeService codeService;

    public String getCodeName()
    {
        return codeName;
    }

    public void setCodeName(String codeName)
    {
        this.codeName = codeName;
    }

    public String getCodeSql()
    {
        return codeSql;
    }

    public void setCodeSql(String codeSql)
    {
        this.codeSql = codeSql;
    }

    public String getItemValue()
    {
        return itemValue;
    }

    public void setItemValue(String itemValue)
    {
        this.itemValue = itemValue;
    }

    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = this.getJspContext().getOut();
        if (codeService == null)
        {
            ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            codeService = (CodeService) wac.getBean("codeService");
        }
        if ((null != this.itemValue) && (0 < this.itemValue.trim().length()))
        {
            if ((null != this.codeName) && (false == "".equals(this.codeName.trim())))
            {
                List<CodeValue> codeValueList = codeService.getCodeValuesByCodeName(codeName);
                if ((null != codeValueList) && (0 < codeValueList.size()))
                {
                    Map<String, String> map = new HashMap<String, String>();
                    for (CodeValue codeValue : codeValueList)
                    {
                        map.put(codeValue.getItemValue(), codeValue.getItemText());
                    }
                    String[] tempValue = this.itemValue.split(",");
                    String value = "";
                    for (int i = 0; i < tempValue.length; i++)
                    {
                        if (map.containsKey(tempValue[i].trim()))
                        {
                            if ("".equals(value))
                            {
                                value = map.get(tempValue[i].trim());
                            }
                            else
                            {
                                value = value + "," + map.get(tempValue[i].trim());
                            }
                        }
                    }
                    if ("".equals(value))
                    {
                        out.print("(无法获取数据源[" + this.codeName + "]值[" + this.itemValue + "]对应的文本描述)");
                    }
                    else
                    {
                        out.print(value);
                    }
                }
                else
                {
                    out.print("(无法获取数据源[" + this.codeName + "]对应的数据)");
                }
            }
            else if ((null != this.codeSql) && (false == "".equals(this.codeSql.trim())))
            {
                String tempSql = this.codeSql;
                if ((tempSql.indexOf(Constants.CODE_SQL_PATTERN_DEPAR_ID) >= 0)
                        || (tempSql.indexOf(Constants.CODE_SQL_PATTERN_USER_ID) >= 0))
                {
                    HttpSession session = Struts2Util.getSession();
                    User user = (User) session.getAttribute(Constants.CURR_USER);
                    if (tempSql.indexOf(Constants.CODE_SQL_PATTERN_DEPAR_ID) >= 0)
                    {
                        tempSql = tempSql.replace(Constants.CODE_SQL_PATTERN_DEPAR_ID, "'"
                                + ((Department) user.getDept()).getId() + "'");
                    }
                    if (tempSql.indexOf(Constants.CODE_SQL_PATTERN_USER_ID) >= 0)
                    {
                        tempSql = tempSql.replace(Constants.CODE_SQL_PATTERN_USER_ID, "'" + user.getId() + "'");
                    }
                }
                List<CodeValue> codeValueList = null;
                codeValueList = codeService.getCodeValueBySql(tempSql);
                Map<String, String> valueMap = new HashMap<String, String>();
                if ((null != codeValueList) && (0 < codeValueList.size()))
                {
                    for (CodeValue tempCodeValue : codeValueList)
                    {
                        valueMap.put(tempCodeValue.getItemValue(), tempCodeValue.getItemText());
                    }
                }
                if ((null != valueMap) && (0 < valueMap.size()))
                {
                    String[] tempValue = this.itemValue.split(",");
                    String value = "";
                    for (int i = 0; i < tempValue.length; i++)
                    {
                        if (valueMap.containsKey(tempValue[i].trim()))
                        {
                            if ("".equals(value))
                            {
                                value = valueMap.get(tempValue[i].trim());
                            }
                            else
                            {
                                value = value + "," + valueMap.get(tempValue[i].trim());
                            }
                        }
                    }
                    if ("".equals(value))
                    {
                        out.print("(无法获取取值SQL[" + this.codeSql + "]值[" + this.itemValue + "]对应的文本描述)");
                        codeValueList.clear();
                    }
                    else
                    {
                        out.print(value);
                        codeValueList.clear();
                    }
                }
                else
                {
                    out.print("(无法获取取值SQL[" + this.codeSql + "]对应的数据)");
                }
            }
            else
            {
                out.print("");
            }
        }
        else
        {
            out.print("");
        }
    }
}
