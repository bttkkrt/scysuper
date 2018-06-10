/**
 * SelectOneTag.java        Sep 24, 2008 2:51:04 PM
 * Administrator
 * 版权所有 (c) 2007-2008 南京拓构软件有限公司
 * 下拉框控件，传入对应一维代码表中的主键即可
 */

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;

/**
 * <P>Description: The Class SelectOneTag.</p>
 *
 * @author Administrator
 * @version 1.0
 */

public class SelectOneTag extends SimpleTagSupport implements DynamicAttributes
{
    private static final String ATTR_TEMPLATE = "%s=\"%s\"";

    private String defaultText;

    private String codeName;
    
    private String style;

    /**
     * 取值SQL语句
     */
    private String codeSql;

    private Map<String, Object> tagAttrs = new HashMap<String, Object>();

    private static CodeService codeService;

    private String property;

    private String value;

    private String name;

    /**
     * <p>
     * Description: 实现了DynamicAttributes接口中的setDynamicAttribute方法
     * 当控件需要多事件控制时，进行动态事件添加
     * </p>
     * @param uri
     * @param name
     * @param value
     * @throws JspException
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */

    public void setDynamicAttribute(String uri , String name , Object value) throws JspException
    {
        this.tagAttrs.put(name, value);
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param defaultText The defaultText to set.
     */
    public void setDefaultText(String defaultText)
    {
        this.defaultText = defaultText;
    }

    /**
     * Sets the code type.
     *
     * @param codeType the new code type
     */
    public void setCodeName(String codeName)
    {
        this.codeName = codeName;
    }

    /**
     * <p>Description: The doTag</p>
     * @throws JspException
     * @throws IOException
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */

    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = this.getJspContext().getOut();
        //添加字典表数据查询
        if (codeService == null)
        {
            ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            codeService = (CodeService) wac.getBean("codeService");
        }
        List<CodeValue> codeValueList = null;
        if ((null != this.codeName) && (false == "".equals(this.codeName.trim())))
        {
            codeValueList = codeService.getCodeValuesByCodeName(this.codeName);
            this.outputTag(out, codeValueList);
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
            this.outputTag(out, codeValueList);
        }
        else
        {
            out.print("<select></select>");
        }
    }

    private void outputTag(JspWriter out , List<CodeValue> codeValueList) throws JspException, IOException
    {
        if (value == null)
        {
            try
            {
                Object bean = this.getJspContext().findAttribute(name == null ? "" : name);
                if (bean != null)
                {
                    value = BeanUtils.getProperty(bean, property);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        out.print("<select ");
        if(style!=null){
        	out.print(" style='" + style + "' ");
        }
        String id = property;
    	if(this.property.indexOf(".")!=-1){
    		String[] ids = property.split("\\.");
    		id = ids[ids.length-1];
    	}
        out.print(" id=\"" + id + "\" ");
        out.print(" name=\"" + property + "\" ");
        for (String attrName : this.tagAttrs.keySet())
        {
            String attrDefinition = String.format(SelectOneTag.ATTR_TEMPLATE, attrName, this.tagAttrs.get(attrName));
            out.print(attrDefinition + " ");
        }
        out.print(">");
       
        if (defaultText != null)
        {
            out.print("<option value=\"\">" + defaultText + "</option>");
        }
        for (CodeValue tempCodeValue : codeValueList)
        {
        	 /*
             * 修改，不管是前台还是后台值，符合一个即check
             */
            if (StringUtils.equals(tempCodeValue.getItemValue(), value)||StringUtils.equals(tempCodeValue.getItemText(), this.value))
            {
                out.print("<option selected=\"true\" value=" + tempCodeValue.getItemValue() + ">"
                        + tempCodeValue.getItemText() + "</option>");
            }
            else
            {
                out.print("<option value=" + tempCodeValue.getItemValue() + ">" + tempCodeValue.getItemText()
                        + "</option>");
            }
        }
        out.print("</select>");
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param property The property to set.
     */
    public void setProperty(String property)
    {
        this.property = property;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public String getCodeSql()
    {
        return codeSql;
    }

    public void setCodeSql(String codeSql)
    {
        this.codeSql = codeSql;
    }

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}