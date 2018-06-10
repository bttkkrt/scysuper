
package com.jshx.module.form.web.tag;

import java.io.IOException;
import java.util.List;

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

public class CodeLabel extends SimpleTagSupport
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
     * Item的值，对应CodeValue表中的ItemValue
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
                CodeValue codeValue = codeService.getCodeValueByCodeNameAndItemValue(codeName, itemValue);
                if (null != codeValue)
                {
                    out.print(codeValue.getItemText());
                }
                else
                {
                    out.print("<font color=\"red\"></font>");
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
                if ((null == codeValueList) || (0 == codeValueList.size()))
                {
                    out.print("(无法获取取值SQL[" + this.codeSql + "]对应的数据列表)");
                }
                else
                {
                    String text = null;
                    for (CodeValue tempValue : codeValueList)
                    {
                        if (tempValue.getItemValue().equals(this.itemValue))
                        {
                            text = tempValue.getItemText();
                            break;
                        }
                    }
                    if (null != text)
                    {
                        out.print(text);
                        codeValueList.clear();
                    }
                    else
                    {
                        out.print("<font color=\"red\"></font>");
                        codeValueList.clear();
                    }
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
