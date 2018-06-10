
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
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;

public class CodeRadio extends SimpleTagSupport implements DynamicAttributes
{
    private static final String ATTR_TEMPLATE = "%s=\"%s\"";

    /**
     * Codes表中的CodeName
     */
    private String codeName;

    /**
     * 取值SQL语句
     */
    private String codeSql;

    private String property;

    /**
     * value属性，用来判断哪个Radio被选中
     */
    private String value;

    /**
     * 是否需要增加一个全选的radio
     */
    private boolean needAddAll;

    private static CodeService codeService;

    /**
     * 动态属性
     */
    private Map<String, Object> tagAttrs = new HashMap<String, Object>();

    public void setDynamicAttribute(String uri , String name , Object value) throws JspException
    {
        this.tagAttrs.put(name, value);
    }

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

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public boolean isNeedAddAll()
    {
        return needAddAll;
    }

    public void setNeedAddAll(boolean needAddAll)
    {
        this.needAddAll = needAddAll;
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
        List<CodeValue> codeValueList = null;
        if ((null != this.codeName) && (false == "".equals(this.codeName.trim())))
        {
            codeValueList = codeService.getCodeValuesByCodeName(codeName);
            if ((null == codeValueList) || (0 == codeValueList.size()))
            {
                out.print("(无法获取数据源[" + this.codeName + "]对应的数据列表)");
            }
            else
            {
                output(out, codeValueList);
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
            codeValueList = codeService.getCodeValueBySql(tempSql);
            if ((null == codeValueList) || (0 == codeValueList.size()))
            {
                out.print("(无法获取取值SQL[" + this.codeSql + "]对应的数据列表)");
                codeValueList.clear();
            }
            else
            {
                output(out, codeValueList);
                codeValueList.clear();
            }
        }
        else
        {
            out.print("<input type=\"radio\" name=\"\" value=\"\">属性codeName和codeSql不能同时为空");
        }
    }

    private void output(JspWriter out , List<CodeValue> codeValueList) throws JspException, IOException
    {
        String dynamicAtts = "";
		 String ss = "";
        for (String attrName : this.tagAttrs.keySet())
        {
			if(attrName.startsWith("on"))
        	{
        		ss += " " + String.format(CodeRadio.ATTR_TEMPLATE, attrName, this.tagAttrs.get(attrName));
        	}
            dynamicAtts += " " + String.format(CodeRadio.ATTR_TEMPLATE, attrName, this.tagAttrs.get(attrName));
        }
        if (needAddAll)
        {
        	String id = property;
        	if(this.property.indexOf(".")!=-1){
        		String[] ids = property.split("\\.");
        		id = ids[ids.length-1];
        	}
            out.print("<input type=\"radio\" name=\"" + this.property + "\" id=\"" + id + "\"");
            out.print(" value=\"\"" + dynamicAtts);
            if (StringUtils.equals("", this.value))
            {
                out.print(" checked");
            }
            out.print(">全选&nbsp;");
        }
        for (CodeValue code : codeValueList)
        {
            out.print("<input type=\"radio\" name=\"" + this.property + "\" id=\"" + this.property + "\"");
            out.print(" value=\"" + code.getItemValue() + "\"" + dynamicAtts);
            dynamicAtts = ss;
            /*
             * 修改，不管是前台还是后台值，符合一个即check
             */
            if (StringUtils.equals(code.getItemValue(), this.value)||StringUtils.equals(code.getItemText(), this.value))
            {
                out.print(" checked");
            }
            out.print(">");
            out.print(code.getItemText());
            out.print("&nbsp;");
        }
    }
}
