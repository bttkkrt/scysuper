
package com.jshx.module.form.service.generate.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;

import org.hibernate.LobHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;
import com.jshx.module.form.service.FormFieldService;
import com.jshx.module.form.service.generate.CodeGenerate;

@Service("codeGenerate")
@SuppressWarnings("unused")
public class SshCodeGenerate implements CodeGenerate
{
    private static Logger log = LoggerFactory.getLogger(SshCodeGenerate.class);

    private static final String FIELD_TYPE_STRING = String.class.getSimpleName();

    private static final String FIELD_TYPE_LONG = Long.class.getSimpleName();

    private static final String FIELD_TYPE_DOUBLE = Double.class.getSimpleName();

    private static final String FIELD_TYPE_DATE = Date.class.getSimpleName();

    private static final String FIELD_TYPE_LIST = List.class.getSimpleName();

    private static final String FIELD_TYPE_FILE = File.class.getSimpleName();

    private static final String FIELD_TYPE_BLOB = Blob.class.getSimpleName();

    private static final String TEMPLATE_CLASS_PATH = "/com/jshx/module/form/service/generate/template/";

    private static final String CHAR_SET = "UTF-8";

    private static final String KEY_PACKAGE = "\\$\\{package\\}";
    
    private static final String KEY_PROPERTIES = "\\$\\{propeties\\}";

    private static final String KEY_CLASSNAME = "\\$\\{classname\\}";
    
    private static final String KEY_CONSTRUCTOR = "\\$\\{constructor\\}";

    private static final String KEY_LOWER_CLASSNAME = "\\$\\{lowerclassname\\}";

    private static final String KEY_TABLENAME = "\\$\\{tablename\\}";

    private static final String KEY_IMPORT = "\\$\\{customimport\\}";
    
    private static final String KEY_CODEMAP = "\\$\\{codeMap\\}";

    private static final String KEY_FIELD = "\\$\\{customfield\\}";

    private static final String KEY_GETSET = "\\$\\{get_set_customfield\\}";

    private static final String KEY_HQL_CODE = "\\$\\{wherehqlcode\\}";
    
    private static final String KEY_QUERY_PARAMS = "\\$\\{queryParams\\}";
    
    private static final String KEY_COLUMNS = "\\$\\{column\\}";

    //    private static final String KEY_PREXNAME = "\\$\\{prexname\\}";

    private static final String KEY_PROJECT = "\\$\\{project\\}";

    private static final String KEY_QUERY_JSP_CODE = "\\$\\{queryjspcode\\}";

    private static final String KEY_LIST_JSP_CODE = "\\$\\{resultlistjspcode\\}";

    private static final String KEY_DETAIL_JSP_CODE = "\\$\\{detailjspcode\\}";

    private static final String KEY_FAKEDITOR_CODE = "\\$\\{fakeditorscript\\}";

    private static final String KEY_FILELIST_CODE = "\\$\\{setfilelist\\}";

    private static final String KEY_ACTION_CONFIG = "\\$\\{viewimageaction\\}";

    private static final String KEY_BLOB_CODE = "\\$\\{blobconvert\\}";

    private static final String KEY_VIEW_CODE = "\\$\\{viewimage\\}";

    private static final String KEY_CHECK_CODE = "\\$\\{radiocheckbox\\}";

    private static final String KEY_SETDEFAULT_CODE = "\\$\\{setdefaultvalue\\}";

    private static final String KEY_TABLE_WIDTH = "\\$\\{tableWidth\\}";
    
    /**
     * 生成代码的根路径
     */
    private String basePath;

    /**
     * JSP页面文件绝对路径
     */
    private String jspPath;
    
    /**
     * html页面文件绝对路径
     */
    private String htmlPath;

    /**
     * Action文件绝对路径
     */
    private String webPath;

    /**
     * Service层文件绝对路径
     */
    private String servicePath;

    private String daoPath;

    /**
     * 实体类文件绝对路径
     */
    private String entityPath;

    private String confPath;

    private FormTable table;

    private String tableName;

    private String packageName;
    
    private String project;

    @Autowired
    private FormFieldService formFieldService;

    @Autowired
    private CodeService codeService;

    public void genCode(FormTable table,String randomFolderName) throws BasalException
    {
        this.table = table;
        if(null != this.table){
        	this.project = table.getProjectName();
        	checkParam();
        	String generatePath = randomFolderName+File.separator;
        	if ((null != this.table.getProjectName()) && (0 < this.table.getProjectName().trim().length()))
        	{
        		generatePath = generatePath + File.separator + this.table.getProjectName().trim();
        	}
        	//String baseClassName = this.tableName.substring(0, 1).toUpperCase()
        	//        + this.tableName.substring(1, this.tableName.length()).toLowerCase();
        	String baseClassName = JspCodeUtil.convertMothodWord(this.tableName);
        	this.basePath = generatePath;
        	createFolder(generatePath, baseClassName);
        	Map<String, Code> codeMap = getCodeMap(this.table.getId());
        	List<FormField> fieldList = this.table.getFieldList();
        	genEntityFile(baseClassName, fieldList);
        	genDaoImplFile(baseClassName, fieldList);
        	genServiceImplFile(baseClassName);
        	genDaoFile(baseClassName);
        	genServiceFile(baseClassName);
        	genWebFile(baseClassName, fieldList, codeMap);
        	genStrutsXmlFile(baseClassName, fieldList);
        	genSqlmapXmlFile(baseClassName, fieldList);
        	genJspListFile(baseClassName, codeMap);
        	genJspEditFile(baseClassName, codeMap);
        	genJspDetailFile(baseClassName, codeMap);
        	genReadmeFile();
        }

//        genJqmAddHtml(table);
//        genJqmAdd2Html(table);
//        genJqmListHtml(table);
//        genJqmList2Html(table);
//        genJqmDetailHtml(table);
//        genJqmEditHtml(table);
//        genJqmLocalHtml(table);
//        genJqmAction(table);
//        genJqmStruts(table);

    }

    /**
     * 生成实体类文件
     * @param baseClassName
     * @param fieldList
     * @throws Exception
     */
    private void genEntityFile(String baseClassName , List<FormField> fieldList) 
    {
        //读取模板内容
        String src = readTemplate("Java_Entity.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName + ".");
        }
        //替换表名
        src = src.replaceAll(KEY_TABLENAME, this.tableName);
        //增加Import，暂时不需要
        src = src.replaceAll(KEY_IMPORT, "");
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String[] tempSrc = genEntityFieldSrc(fieldList);
        //替换自定义属性
        src = src.replaceAll(KEY_FIELD, tempSrc[0].toString());
        //替换自定义属性get/set方法
        src = src.replaceAll(KEY_GETSET, tempSrc[1].toString());
        //TODO 根据显示字段创建构造器
        StringBuffer constructor = new StringBuffer("public ");
        constructor.append(baseClassName).append("(String id, ");
        StringBuffer content = new StringBuffer("this.id = id;\r\n");
        for(FormField field : fieldList){
        	if(field.getDispInGrid()==1){
        		String property = JspCodeUtil.convertPropertyWord(field.getFieldName());
        		String propType = null;
                if ("int".equalsIgnoreCase(field.getFieldType()))
                {
                    propType = FIELD_TYPE_LONG;
                }
                else if (("decimal".equals(field.getFieldType())) || ("number".equals(field.getFieldType())))
                {
                    propType = FIELD_TYPE_DOUBLE;
                }
                else if (("datetime".equals(field.getFieldType())) || ("date".equals(field.getFieldType())))
                {
                    propType = FIELD_TYPE_DATE;
                }
                else if (("image".equals(field.getFieldType())) || ("blob".equals(field.getFieldType())))
                {
                    propType = FIELD_TYPE_BLOB;
                }
                else
                {
                    propType = FIELD_TYPE_STRING;
                }
        		constructor.append(propType).append(" ");
        		constructor.append(property).append(", ");
        		content.append("\r\nthis.").append(property).append(" = ").append(property).append(";\r\n");
        	}
        }
        String s = constructor.substring(0, constructor.length()-2);
        constructor = new StringBuffer(s);
        constructor.append("){\r\n");
        constructor.append(content);
        constructor.append("}\r\n");
        src = src.replaceAll(KEY_CONSTRUCTOR, constructor.toString());
        //创建类文件
        String fileName = this.entityPath + File.separator + baseClassName + ".java";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        constructor = null;
        s = null;
        content = null;
        if (log.isDebugEnabled())
        {
            log.debug("生成实体类成功");
        }
    }

    /**
     * 生成Dao类文件
     * @param baseClassName
     * @param fieldList
     * @throws BusinessException
     */
    private void genDaoImplFile(String baseClassName , List<FormField> fieldList)
    {
        //读取模板内容
        String src = readTemplate("Java_DaoImpl.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName + ".");
        }
        //增加Import，暂时不需要
        src = src.replaceAll(KEY_IMPORT, "");
        //设置Blob代码
        String temp = genDaoBlobCode(fieldList);
        src = src.replaceAll(KEY_BLOB_CODE, Matcher.quoteReplacement(temp));
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        //创建类文件
        String fileName = this.daoPath + File.separator + "impl" +File.separator + baseClassName + "DaoImpl.java";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Dao类成功");
        }
    }

    /**
     * 生成Service类文件
     * @param baseClassName
     * @throws Exception
     */
    private void genServiceImplFile(String baseClassName) 
    {
        //读取模板内容
        String src = readTemplate("Java_ServiceImpl.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName + ".");
        }
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        //创建类文件
        String fileName = this.servicePath + File.separator + "impl" + File.separator + baseClassName + "ServiceImpl.java";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Service类成功");
        }
    }
    
    /**
     * 生成Dao接口文件
     * @param baseClassName
     * @param fieldList
     * @throws BusinessException
     */
    private void genDaoFile(String baseClassName) 
    {
        //读取模板内容
        String src = readTemplate("Java_Dao.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName + ".");
        }
        //增加Import，暂时不需要
        src = src.replaceAll(KEY_IMPORT, "");
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        
        //创建类文件
        String fileName = this.daoPath + File.separator + baseClassName + "Dao.java";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Dao接口成功");
        }
    }

    /**
     * 生成Service接口文件
     * @param baseClassName
     * @throws Exception
     */
    private void genServiceFile(String baseClassName) 
    {
        //读取模板内容
        String src = readTemplate("Java_Service.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName + ".");
        }
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        //创建类文件
        String fileName = this.servicePath + File.separator + baseClassName + "Service.java";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Service接口成功");
        }
    }

    /**
     * 生成Action类文件
     * @param baseClassName
     * @param fieldList
     * @throws Exception
     */
    private void genWebFile(String baseClassName , List<FormField> fieldList, Map<String, Code> codeMap) 
    {
        //读取模板内容
        String src = readTemplate("Java_Action.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName + ".");
        }
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        //替换查询日期的属性
        String[] tempSrc = genWebFieldSrc(fieldList);
        src = src.replaceAll(KEY_FIELD, Matcher.quoteReplacement(tempSrc[0].toString()));
        //替换查询日期属性get/set方法
        src = src.replaceAll(KEY_GETSET, Matcher.quoteReplacement(tempSrc[1].toString()));
        //替换附件List的属性、查询等代码
        src = src.replaceAll(KEY_FILELIST_CODE, Matcher.quoteReplacement(tempSrc[2].toString()));
        //生成浏览图片代码
        String viewCode = genViewImage(lowerClassName, fieldList);
        src = src.replaceAll(KEY_VIEW_CODE, Matcher.quoteReplacement(viewCode));
        //替换HQL查询条件——待修改
        String hqlCode = genWhereHqlCode();
        src = src.replaceAll(KEY_HQL_CODE,  Matcher.quoteReplacement(hqlCode));
        //替换Blob转换代码
        String blobCode = genBlobConvertCode(fieldList);
        src = src.replaceAll(KEY_BLOB_CODE,  Matcher.quoteReplacement(blobCode));
        //生成并替换默认值代码
        String defualtValueCode = genSetDefaultValueCode(fieldList);
        src = src.replaceAll(KEY_SETDEFAULT_CODE,  Matcher.quoteReplacement(defualtValueCode));
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);//替换类名
        //创建一维代码转换映射
        StringBuffer column = new StringBuffer("id|");
        StringBuffer codes = new StringBuffer();
        for(FormField field : fieldList){
        	if(field.getDispInGrid()==1){
        		String property = JspCodeUtil.convertPropertyWord(field.getFieldName());
        		column.append(property).append("|");
        		if(field.getFieldDisplayType().equals("DropdownList")){
        			if ("Codes".equalsIgnoreCase(field.getDataSource())){
                		Code code = codeMap.get(field.getDataSourceType());
                		codes.append("codeMap.put(\"").append(property).append("\",\"").append(code.getId()).append("\");\r\n");
                	}else{
                		String sql = field.getDefaultValue();
                		codes.append("codeMap.put(\"").append(property).append("\",\"").append(sql).append("\");\r\n");
                	}
        		}
        	}
        }
        
        
        src = src.replaceAll(KEY_CODEMAP, codes.toString());
        src = src.replaceAll(KEY_COLUMNS, column.toString());
        //创建类文件
        String fileName = this.webPath + File.separator + baseClassName + "Action.java";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Action类成功");
        }
    }

    /**
     * 生成Struts XML配置文件
     * @param baseClassName
     * @param fieldList
     * @throws Exception
     */
    private void genStrutsXmlFile(String baseClassName , List<FormField> fieldList) 
    {
        //读取模板内容
        String src = readTemplate("XML_Struts.template");
        //替换包名
        String tempPackageName = this.packageName;
        if (false == "".equals(this.packageName))
        {
            tempPackageName = this.packageName + ".";
        }
        src = src.replaceAll(KEY_PACKAGE, tempPackageName);
        //替换表名
        src = src.replaceAll(KEY_TABLENAME, this.tableName);
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        String projectName = this.table.getProjectName();
        if ((null == projectName) || (0 == projectName.trim().length()))
        {
            //如果项目名称为空，则以类名为项目名
            projectName = baseClassName.toLowerCase();
        }
        src = src.replaceAll(KEY_PROJECT, projectName);
        //替换读取图片的Action配置
        String configSrc = genViewImageActionConf(fieldList, tempPackageName, baseClassName);
        src = src.replaceAll(KEY_ACTION_CONFIG, configSrc);
        //创建配置文件
        String fileName = this.confPath + File.separator + "struts-" + projectName + ".xml";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Struts配置文件成功");
        }
    }

    /**
     * 生成SqlMap配置文件
     * @param baseClassName
     * @param fieldList
     * @throws BusinessException
     */
    private void genSqlmapXmlFile(String baseClassName , List<FormField> fieldList) 
    {
        //读取模板内容
        String src = readTemplate("XML_SqlMap.template");
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String projectName = this.table.getProjectName();
        if ((null == projectName) || (0 == projectName.trim().length()))
        {
            //如果项目名称为空，则以类名为项目名
            projectName = baseClassName.toLowerCase();
        }
        src = src.replaceAll(KEY_PROJECT, projectName);
        //替换HQL查询条件——待修改
        String hqlCode = genMapWhereHqlCode();
        src = src.replaceAll(KEY_HQL_CODE, Matcher.quoteReplacement(hqlCode));
        //替换类名
        src = src.replaceAll(KEY_CLASSNAME, baseClassName);
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        
        //TODO 根据构造器查询
        src = src.replaceAll(KEY_PACKAGE, table.getSrcPackage());
        StringBuffer propeties = new StringBuffer("t.id, ");
        for(FormField field : fieldList){
        	if(field.getDispInGrid()==1){
        		propeties.append("t.").append(JspCodeUtil.convertPropertyWord(field.getFieldName())).append(", ");
        	}
        }
        String s = propeties.substring(0, propeties.length()-2);
        src = src.replaceAll(KEY_PROPERTIES, s);
        //创建配置文件
        String fileName = this.confPath + File.separator + "sql-map-" + projectName + ".xml";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        propeties = null;
        s = null;
        if (log.isDebugEnabled())
        {
            log.debug("生成SqlMap配置文件成功");
        }
    }

    /**
     * 生成列表JSP文件
     * @param baseClassName
     * @param codeMap
     * @throws Exception
     */
    private void genJspListFile(String baseClassName , Map<String, Code> codeMap) 
    {
        //读取模板内容
        String src = readTemplate("JSP_List.template");
        //替换中文表名
        src = src.replaceAll(KEY_TABLENAME, this.table.getTableName());
        
        src = src.replaceAll(KEY_PROJECT, this.project);

        //替换查询代码
        List<FormField> queryList = formFieldService.getQueryField(this.table.getId());
        String queryCode = JspCodeUtil.genQueryJspCode(queryList, codeMap);
        src = src.replaceAll(KEY_QUERY_JSP_CODE, Matcher.quoteReplacement(queryCode));
        //替换List代码
        List<FormField> displayList = formFieldService.getDisplayField(this.table.getId());
        String listCode = JspCodeUtil.genListJspCode(displayList, codeMap);
        src = src.replaceAll(KEY_LIST_JSP_CODE, Matcher.quoteReplacement(listCode));
        
        //替换列表控件
        String queryParam = JspCodeUtil.genQueryParamsCode(queryList);
        
        if(queryParam.lastIndexOf(",")==queryParam.length()-2){
        	queryParam = queryParam.substring(0, queryParam.lastIndexOf(","));
        }
        
        src = src.replaceAll(KEY_QUERY_PARAMS, Matcher.quoteReplacement(queryParam));
        String column = JspCodeUtil.genListColumn(displayList, codeMap);
        src = src.replaceAll(KEY_COLUMNS, Matcher.quoteReplacement(column));
                
        //替换类名
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        
        //创建JSP文件
        String fileName = this.jspPath + File.separator + baseClassName + "_List.jsp";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成JSP List文件成功");
        }
    }

    /**
     * 生成编辑JSP文件
     * @param baseClassName
     * @param codeMap
     * @throws Exception
     */
    private void genJspEditFile(String baseClassName , Map<String, Code> codeMap) 
    {
        //读取模板内容
        String src = readTemplate("JSP_Edit.template");

        List<FormField> columnList = formFieldService.getAllField(this.table.getId());
        //判断替换校验Radio和CheckBox的JavaScript代码
        String checkCode = JspCodeUtil.genCheckScriptCode(columnList);
        src = src.replaceAll(KEY_CHECK_CODE, Matcher.quoteReplacement(checkCode));
        //判断替换FckEditor的JavaScript代码
        String scriptCode = JspCodeUtil.genFakeditorScriptCode(columnList);
        src = src.replaceAll(KEY_FAKEDITOR_CODE, Matcher.quoteReplacement(scriptCode));
        //替换Detail代码
        String detailCode = JspCodeUtil.genEditJspCode(table, columnList, codeMap);
        src = src.replaceAll(KEY_DETAIL_JSP_CODE, Matcher.quoteReplacement(detailCode));
        
        //替换类名
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        
        if(table.getTableWidth()!=null)
        	src = src.replaceAll(KEY_TABLE_WIDTH, table.getTableWidth());
        else
        	src = src.replaceAll(KEY_TABLE_WIDTH, "100%");
        
        //创建JSP文件
        String fileName = this.jspPath + File.separator + baseClassName + "_Edit.jsp";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成JSP Edit文件成功");
        }
    }

    /**
     * 生成详细信息JSP文件
     * @param baseClassName
     * @param codeMap
     * @throws Exception
     */
    private void genJspDetailFile(String baseClassName , Map<String, Code> codeMap) 
    {
        //读取模板内容
        String src = readTemplate("JSP_Detail.template");
        //替换Detail代码
        List<FormField> columnList = formFieldService.getAllField(this.table.getId());
        String detailCode = JspCodeUtil.genDetailJspCode(table, columnList, codeMap);
        src = src.replaceAll(KEY_DETAIL_JSP_CODE, Matcher.quoteReplacement(detailCode));
        
        //替换类名
        String lowerClassName = baseClassName.substring(0, 1).toLowerCase() + baseClassName.substring(1);
        src = src.replaceAll(KEY_LOWER_CLASSNAME, lowerClassName);
        
        if(table.getTableWidth()!=null)
        	src = src.replaceAll(KEY_TABLE_WIDTH, table.getTableWidth());
        //创建JSP文件
        String fileName = this.jspPath + File.separator + baseClassName + "_Detail.jsp";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成JSP Detail文件成功");
        }
    }

    /**
     * 生成说明文件
     * @throws Exception
     */
    private void genReadmeFile() 
    {
        //读取模板内容
        String src = readTemplate("Txt_Readme.template");
        //替换包名
        if ("".equals(this.packageName))
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName);
        }
        else
        {
            src = src.replaceAll(KEY_PACKAGE, this.packageName.replaceAll("\\.", "/"));
        }
        //替换项目名
        src = src.replaceAll(KEY_PROJECT, this.table.getProjectName());
        //创建文件
        String fileName = this.basePath + File.separator + "Readme.txt";
        createFile(fileName);
        //写入文件
        writeFile(fileName, src);
        if (log.isDebugEnabled())
        {
            log.debug("生成Readme.txt文件成功");
        }
    }

    /**
     * 校验参数
     * @throws Exception
     */
    private void checkParam() throws BasalException
    {
        if ((null == this.table.getPhysicalName()) || (0 == this.table.getPhysicalName().trim().length()))
        {
        	BasalException ex = new BasalException(BasalException.NO, "表名为空");
            throw ex;
        }
        this.tableName = this.table.getPhysicalName().trim();
        if (null == this.table.getSrcPackage())
        {
            this.packageName = "";
        }
        else
        {
            this.packageName = this.table.getSrcPackage().trim();
        }
    }

    /**
     * 创建文件夹
     * @param generatePath
     * @return
     */
    private void createFolder(String generatePath , String baseClassName)  {
        File file = new File(generatePath);
        if (file.exists())
        {
            //如果存在该路径，则删除原先文件
            JspCodeUtil.delAllFile(generatePath);
        }
        //如果父路径不存在，则一并创建
        file.mkdirs();

        String projectName = this.table.getProjectName();
        if ((null == projectName) || (0 == projectName.trim().length()))
        {
            //如果项目名称为空，则以类名为项目名
            projectName = baseClassName.toLowerCase();
        }
        this.jspPath = generatePath + File.separator + "pages" + File.separator + projectName;
        file = new File(this.jspPath);
        if (!file.exists())
        {
            //如果父路径不存在，则一并创建
            file.mkdirs();
        }
        this.htmlPath = generatePath + File.separator + "mobile" + File.separator + "pages" + File.separator + projectName;
        file = new File(this.htmlPath);
        if (!file.exists())
        {
            //如果父路径不存在，则一并创建
            file.mkdirs();
        }
        //创建src路径
        StringBuffer sb = new StringBuffer(generatePath + File.separator + "src");
        file = new File(sb.toString());
        if (!file.exists())
        {
            file.mkdir();
        }
        String packName = table.getSrcPackage();
        if (null == packName)
        {
            packName = "";
        }
        packName = packName.trim();
        if (false == "".equals(packName))
        {
            //创建包路径
            String[] packages = table.getSrcPackage().split("\\.");
            for (String folder : packages)
            {
                sb.append(File.separator).append(folder);
            }
            file = new File(sb.toString());
            if (!file.exists())
            {
                file.mkdirs();
            }
        }

        //创建web路径
        this.webPath = sb.toString() + File.separator + "web";
        file = new File(this.webPath);
        if (!file.exists())
        {
            file.mkdir();
        }
        //创建service接口路径
        this.servicePath = sb.toString() + File.separator + "service";
        file = new File(this.servicePath);
        if (!file.exists())
        {
            file.mkdir();
        }
        
        //创建service实现类路径
        this.servicePath = sb.toString() + File.separator + "service" + File.separator + "impl";
        file = new File(this.servicePath);
        if (!file.exists())
        {
            file.mkdir();
        }
        
        this.servicePath = sb.toString() + File.separator + "service";

        //创建Dao接口路径
        this.daoPath = sb.toString() + File.separator + "dao";
        file = new File(this.daoPath);
        if (!file.exists())
        {
            file.mkdir();
        }
        
        //创建Dao实现类路径
        this.daoPath = sb.toString() + File.separator + "dao" + File.separator + "impl";
        file = new File(this.daoPath);
        if (!file.exists())
        {
            file.mkdir();
        }
        
        this.daoPath = sb.toString() + File.separator + "dao";

        //创建Entity路径
        this.entityPath = sb.toString() + File.separator + "entity";
        file = new File(this.entityPath);
        if (!file.exists())
        {
            file.mkdir();
        }

        //创建config路径
        this.confPath = sb.toString() + File.separator + "conf";
        file = new File(this.confPath);
        if (!file.exists())
        {
            file.mkdir();
        }
    }

    /**
     * 读取模板
     * @param templageName
     * @return
     * @throws Exception
     */
    private String readTemplate(String templageName) throws BasalException
    {
        //读取模板
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(
                    TEMPLATE_CLASS_PATH + templageName), CHAR_SET));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                content.append(tempString).append("\r\n");
            }
        }
        catch (Exception ex)
        {
        	BasalException ex1 = new BasalException(BasalException.ERROR, "读取模板失败", ex);
            
            throw ex1;
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (Exception ex)
                {
                    if (log.isWarnEnabled())
                    {
                        log.warn("关闭实体类模板流异常");
                    }
                }
            }
        }
        return content.toString();
    }

    /**
     * 创建文件
     * @param fileName
     * @return
     * @throws Exception
     */
    private boolean createFile(String fileName) throws BasalException
    {
    	System.out.println(fileName);
        File file = new File(fileName);
        if (file.exists())
        {
            file.delete();
        }
        try
        {
            return file.createNewFile();
        }
        catch (Exception ex)
        {
        	BasalException ex1 = new BasalException(BasalException.ERROR, "创建文件异常", ex);
            
            throw ex1;
        }
    }

    /**
     * 写入文件
     * @param fileName
     * @param source
     * @throws Exception
     */
    private void writeFile(String fileName , String source) throws BasalException
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(new File(fileName));
            byte[] bytes = source.getBytes(CHAR_SET);
            fos.write(bytes, 0, bytes.length);
        }
        catch (Exception ex)
        {
            BasalException ex1 = new BasalException(BasalException.ERROR, "写入文件异常", ex);
            throw ex1;
        }
        finally
        {
            if (null != fos)
            {
                try
                {
                    fos.close();
                }
                catch (Exception ex)
                {
                    if (log.isWarnEnabled())
                    {
                        log.warn("关闭输出流异常：", ex);
                    }
                }
            }
        }
    }

    /**
     * 生成实体类文件自定义属性
     * @param fieldList
     * @return
     */
    private String[] genEntityFieldSrc(List<FormField> fieldList) 
    {
        StringBuffer fieldStr = new StringBuffer();
        StringBuffer getsetSrc = new StringBuffer();
        if ((null != fieldList) && (0 < fieldList.size()))
        {
            for (FormField field : fieldList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                //转换首字母
                String temp1 = JspCodeUtil.convertPropertyWord(field.getFieldName());
                String temp2 = JspCodeUtil.convertMothodWord(field.getFieldName());
                String propType = "";
                if ("int".equalsIgnoreCase(field.getFieldType()))
                {
                    propType = FIELD_TYPE_LONG;
                }
                else if (("decimal".equals(field.getFieldType())) || ("number".equals(field.getFieldType())))
                {
                    propType = FIELD_TYPE_DOUBLE;
                }
                else if (("datetime".equals(field.getFieldType())) || ("date".equals(field.getFieldType())))
                {
                    propType = FIELD_TYPE_DATE;
                }
                else if (("image".equals(field.getFieldType())) || ("blob".equals(field.getFieldType())))
                {
                    propType = FIELD_TYPE_BLOB;
                }
                else
                {
                    propType = FIELD_TYPE_STRING;
                }
                fieldStr.append("\n\t").append("/**");
                fieldStr.append("\n\t").append(" * ").append(field.getFieldDisplayName());
                fieldStr.append("\n\t").append(" */");
                fieldStr.append("\n\tprivate ").append(propType).append(" ").append(temp1).append(";\r\n");
                getsetSrc.append(genGetSrc(propType, field.getFieldName(), temp1, temp2));
                getsetSrc.append(genSetSrc(propType, temp1, temp2));
                //如果是image，还需增加ContentType和ContentName属性
                if (("image".equals(field.getFieldType())) || ("blob".equals(field.getFieldType())))
                {
                    //ContentType属性代码
                    String temp_name1 = temp1 + "ContentType";
                    String temp_name2 = temp2 + "ContentType";

                    fieldStr.append("\n\t").append("/**");
                    fieldStr.append("\n\t").append(" * ").append(field.getFieldDisplayName()).append("对应的上传文件类型");
                    fieldStr.append("\n\t").append(" */");
                    fieldStr.append("\n\tprivate ").append(FIELD_TYPE_STRING).append(" ").append(temp_name1).append(
                            ";\r\n");
                    getsetSrc.append(genGetSrc(FIELD_TYPE_STRING, field.getFieldName() + "_CONTENTTYPE", temp_name1,
                            temp_name2));
                    getsetSrc.append(genSetSrc(FIELD_TYPE_STRING, temp_name1, temp_name2));
                    //ContentName属性代码
                    temp_name1 = temp1 + "ContentName";
                    temp_name2 = temp2 + "ContentName";
                    fieldStr.append("\n\t").append("/**");
                    fieldStr.append("\n\t").append(" * ").append(field.getFieldDisplayName()).append("对应的上传文件名字");
                    fieldStr.append("\n\t").append(" */");
                    fieldStr.append("\n\tprivate ").append(FIELD_TYPE_STRING).append(" ").append(temp_name1).append(
                            ";\r\n");
                    getsetSrc.append(genGetSrc(FIELD_TYPE_STRING, field.getFieldName() + "_CONTENTNAME", temp_name1,
                            temp_name2));
                    getsetSrc.append(genSetSrc(FIELD_TYPE_STRING, temp_name1, temp_name2));
                }
            }
        }
        return new String[] { fieldStr.toString(), getsetSrc.toString() };
    }

    /**
     * 生成get方法代码块
     * @param type
     * @param lowerFieldName
     * @param upperFieldName
     * @return
     */
    private String genGetSrc(String type , String fieldName , String lowerFieldName , String upperFieldName)
    {
        StringBuffer result = new StringBuffer();
        result.append("\n\t@Column(name=\"").append(fieldName).append("\")\r\n");
        result.append("\tpublic ").append(type).append(" get").append(upperFieldName).append("()");
        result.append("\n\t").append("{");
        result.append("\n\t\treturn this.").append(lowerFieldName).append(";\r\n");
        result.append("\t}\n");
        return result.toString();
    }

    /**
     * 生成set方法代码块
     * @param type
     * @param lowerFieldName
     * @param upperFieldName
     * @return
     */
    private String genSetSrc(String type , String lowerFieldName , String upperFieldName)
    {
        StringBuffer result = new StringBuffer();
        result.append("\n\tpublic void set").append(upperFieldName).append("(").append(type).append(" ").append(
                lowerFieldName).append(")");
        result.append("\n\t").append("{");
        result.append("\n\t\tthis.").append(lowerFieldName).append(" = ").append(lowerFieldName).append(";\r\n");
        result.append("\t}\r\n");
        return result.toString();
    }

    /**
     * 生成WebAction的Date类型的查询属性、多文件附件List属性。
     * 实体类一个Date类型的属性，在WebAction中需要2个Date类型的属性（包括start和end），格式为query_[FieldName]_start/end
     * @param fieldList
     * @return
     */
    private String[] genWebFieldSrc(List<FormField> fieldList) 
    {
        StringBuffer fieldStr = new StringBuffer();
        StringBuffer getsetSrc = new StringBuffer();
        StringBuffer fileListSrc = new StringBuffer();
        if ((null != fieldList) && (0 < fieldList.size()))
        {
            for (FormField field : fieldList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                String tempProperty = JspCodeUtil.convertMothodWord(field.getFieldName());
                if ("DatePick".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //转换首字母
                    String temp1 = "query" + tempProperty + "Start";
                    String temp2 = "query" + tempProperty + "End";
                    String temp3 = "Query" + tempProperty + "Start";
                    String temp4 = "Query" + tempProperty + "End";
                    String propType = FIELD_TYPE_DATE;
                    fieldStr.append("\n\tprivate ").append(propType).append(" ").append(temp1).append(";\r\n");
                    fieldStr.append("\n\tprivate ").append(propType).append(" ").append(temp2).append(";\r\n");
                    getsetSrc.append(genWebGetSrc(propType, temp1, temp3));
                    getsetSrc.append(genWebSetSrc(propType, temp1, temp3));
                    getsetSrc.append(genWebGetSrc(propType, temp2, temp4));
                    getsetSrc.append(genWebSetSrc(propType, temp2, temp4));
                }
                else if ("MultiFiles".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //转换首字母
                    String temp1 = "filelist" + tempProperty;
                    String temp2 = "Filelist" + tempProperty;
                    String propType = FIELD_TYPE_LIST;
                    fieldStr.append("\n\t").append("//").append(field.getFieldName()).append("对应的文件列表\r\n");
                    fieldStr.append("\n\tprivate ").append(propType).append(" ").append(temp1).append(";\r\n");
                    getsetSrc.append(genWebGetSrc(propType, temp1, temp2));
                    getsetSrc.append(genWebSetSrc(propType, temp1, temp2));
                    String temp3 = JspCodeUtil.convertMothodWord(field.getFieldName());
                    fileListSrc.append(genFileListScr(temp1, temp3));
                }
                else if ("ImageUpload".equalsIgnoreCase(field.getFieldDisplayType()))
                {
                    //如果有ImageUpload的字段，则在action中增加一个File属性和对应的文件名、文件属性的属性
                    String temp1 = "file" + tempProperty;
                    String temp2 = "File" + tempProperty;
                    //文件名
                    String temp3 = "file" + tempProperty + "FileName";
                    String temp4 = "File" + tempProperty + "FileName";
                    //属性
                    String temp5 = "file" + tempProperty + "ContentType";
                    String temp6 = "File" + tempProperty + "ContentType";
                    String propType1 = FIELD_TYPE_FILE;
                    String propType2 = FIELD_TYPE_STRING;
                    fieldStr.append("\n\tprivate ").append(propType1).append(" ").append(temp1).append(";\r\n");
                    fieldStr.append("\n\tprivate ").append(propType2).append(" ").append(temp3).append(";\r\n");
                    fieldStr.append("\n\tprivate ").append(propType2).append(" ").append(temp5).append(";\r\n");
                    getsetSrc.append(genWebGetSrc(propType1, temp1, temp2));
                    getsetSrc.append(genWebSetSrc(propType1, temp1, temp2));
                    getsetSrc.append(genWebGetSrc(propType2, temp3, temp4));
                    getsetSrc.append(genWebSetSrc(propType2, temp3, temp4));
                    getsetSrc.append(genWebGetSrc(propType2, temp5, temp6));
                    getsetSrc.append(genWebSetSrc(propType2, temp5, temp6));
                }
            }
        }
        return new String[] { fieldStr.toString(), getsetSrc.toString(), fileListSrc.toString() };
    }

    /**
     * 生成附件文件代码块
     * @param listFieldName
     * @param upperFieldName
     * @return
     */
    private String genFileListScr(String listFieldName , String upperFieldName)
    {
        StringBuffer result = new StringBuffer();
        result.append("\n\t\t\t").append("if ((null != ${lowerclassname}.get").append(upperFieldName).append(
                "()) && (0 < ${lowerclassname}.get").append(upperFieldName).append("().trim().length()))");
        result.append("\n\t\t\t").append("{");
        result.append("\n\t\t\t\t").append("this.").append(listFieldName).append(
                " = attachfileManager.getAttFilesByGuID(${lowerclassname}.get").append(upperFieldName).append("().trim());");
        result.append("\n\t\t\t").append("}");
        return result.toString();
    }

    /**
     * 生成WebAction的get方法代码
     * @param type
     * @param lowerFieldName
     * @param upperFieldName
     * @return
     */
    private String genWebGetSrc(String type , String lowerFieldName , String upperFieldName)
    {
        StringBuffer result = new StringBuffer();
        result.append("\n\tpublic ").append(type).append(" get").append(upperFieldName).append("(){");
        result.append("\n\t\treturn this.").append(lowerFieldName).append(";\r\n");
        result.append("\t}\r\n");
        return result.toString();
    }

    /**
     * 生成WebAction类的Set方法代码
     * @param type
     * @param lowerFieldName
     * @param upperFieldName
     * @return
     */
    private String genWebSetSrc(String type , String lowerFieldName , String upperFieldName)
    {
        StringBuffer result = new StringBuffer();
        result.append("\n\tpublic void set").append(upperFieldName).append("(").append(type).append(" ").append(lowerFieldName).append("){");
        result.append("\n\t\tthis.").append(lowerFieldName).append(" = ").append(lowerFieldName).append(";\r\n");
        result.append("\t}\r\n");
        return result.toString();
    }

    /**
     * 生成File转换成Blob代码
     * @param fieldList
     * @return
     */
    private String genBlobConvertCode(List<FormField> fieldList) 
    {
        StringBuffer result = new StringBuffer();
        for (FormField formField : fieldList)
        {
            if ("ImageUpload".equalsIgnoreCase(formField.getFieldDisplayType()))
            {
                String fieldName = formField.getFieldName();
                String methodName = JspCodeUtil.convertMothodWord(fieldName);
                result.append("\n\t\t\t\t").append("if (null != this.file").append(methodName).append(")");
                result.append("\n\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("in = new FileInputStream(this.file").append(methodName).append(");");
                result.append("\n\t\t\t\t\t").append("LobHelper lobHelper = sessionFactory.getCurrentSession().getLobHelper();");
                result.append("\n\t\t\t\t\t").append("Blob blob = lobHelper.createBlob(in, in.available());");
                result.append("\n\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append("(blob);");
                result.append("\n\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append("ContentName(this.file")
                        .append(methodName).append("FileName);");
                result.append("\n\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append("ContentType(this.file")
                        .append(methodName).append("ContentType);");
                result.append("\n\t\t\t\t").append("}");
            }
        }
        return result.toString();
    }

    /**
     * 生成设置默认值代码
     * @param fieldList
     * @return
     */
    private String genSetDefaultValueCode(List<FormField> fieldList) 
    {
        StringBuffer result = new StringBuffer();
        for (FormField formField : fieldList)
        {
            if (false == "无".equalsIgnoreCase(formField.getValueUrl()))
            {
                if ("varchar".equalsIgnoreCase(formField.getFieldType()))
                {
                    String fieldName = formField.getFieldName();
                    String methodName = JspCodeUtil.convertMothodWord(fieldName);
                    result.append("\n\t\t\t\t").append("if ((null == ${lowerclassname}.get").append(methodName).append(
                            "())||( 0 == ${lowerclassname}.get").append(methodName).append("().trim().length()))");
                    result.append("\n\t\t\t\t").append("{");
                    if ("操作人员姓名".equalsIgnoreCase(formField.getValueUrl()))
                    {
                        result.append("\n\t\t\t\t\t").append("User user = this.getLoginUser();");
                        result.append("\n\t\t\t\t\t").append("if (null != user)");
                        result.append("\n\t\t\t\t\t").append("{");
                        result.append("\n\t\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append(
                                "(user.getDisplayName());");
                        result.append("\n\t\t\t\t\t").append("}");
                    }
                    else if ("操作人员部门".equalsIgnoreCase(formField.getValueUrl()))
                    {
                        result.append("\n\t\t\t\t\t").append("Department department = this.getLoginUserDepartment();");
                        result.append("\n\t\t\t\t\t").append("if (null != department)");
                        result.append("\n\t\t\t\t\t").append("{");
                        result.append("\n\t\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append(
                                "(department.getDeptName());");
                        result.append("\n\t\t\t\t\t").append("}");
                    }
                    else if ("当前日期".equalsIgnoreCase(formField.getValueUrl()))
                    {
                        result.append("\n\t\t\t\t\t").append(
                                "SimpleDateFormat df = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
                        result.append("\n\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append(
                                "(df.format(new Date()));");
                    }
                    result.append("\n\t\t\t\t").append("}");
                }
                else if (("datetime".equals(formField.getFieldType())) || ("date".equals(formField.getFieldType())))
                {
                    if ("当前日期".equalsIgnoreCase(formField.getValueUrl()))
                    {
                        String fieldName = formField.getFieldName();
                        String methodName = JspCodeUtil.convertMothodWord(fieldName);
                        result.append("\n\t\t\t\t").append("if (null == ${lowerclassname}.get").append(methodName).append("())");
                        result.append("\n\t\t\t\t").append("{");
                        result.append("\n\t\t\t\t\t").append("${lowerclassname}.set").append(methodName).append("(new Date());");
                        result.append("\n\t\t\t\t").append("}");
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 生成图片查看代码，由于一个表中可能存在多个blob字段，如果用一个查看图片的方法，无法区分是哪个字段，因此，生成多个方法，每一个方法对应一个blob字段
     * @return
     */
    private String genViewImage(String lowClassName , List<FormField> fieldList) 
    {
        StringBuffer result = new StringBuffer();
        for (FormField formField : fieldList)
        {
            if ("ImageUpload".equalsIgnoreCase(formField.getFieldDisplayType()))
            {
                String fieldName = formField.getFieldName();
                String methodName = JspCodeUtil.convertMothodWord(fieldName);
                result.append("\n\t").append("/**");
                result.append("\n\t").append(" *").append("查看").append(formField.getFieldDisplayName()).append("的方法");
                result.append("\n\t").append(" */");
                result.append("\n\t").append("public String view").append(methodName).append("Image()");
                result.append("\n\t").append("{");
                result.append("\n\t\t").append("${lowerclassname} = service.getById(${lowerclassname}.getId());");
                result.append("\n\t\t").append("if ((null != ${lowerclassname}) && (null != ${lowerclassname}.get").append(methodName).append(
                        "()))");
                result.append("\n\t\t").append("{");
                result.append("\n\t\t\t").append("HttpServletResponse response = Struts2Util.getResponse();");
                result.append("\n\t\t\t").append("response.setContentType(${lowerclassname}.get").append(methodName).append(
                        "ContentType());");
                result.append("\n\t\t\t").append(
                        "response.addHeader(\"Content-Disposition\", \"filename=\" + ${lowerclassname}.get").append(methodName)
                        .append("ContentName());");
                result.append("\n\t\t\t").append("Blob image = ${lowerclassname}.get").append(methodName).append("();");
                result.append("\n\t\t\t").append("InputStream in = null;");
                result.append("\n\t\t\t").append("OutputStream out = null;");
                result.append("\n\t\t\t").append("try");
                result.append("\n\t\t\t").append("{");
                result.append("\n\t\t\t\t").append("in = image.getBinaryStream();");
                result.append("\n\t\t\t\t").append("out = Struts2Util.getResponse().getOutputStream();");
                result.append("\n\t\t\t\t").append("byte[] buf = new byte[1024];");
                result.append("\n\t\t\t\t").append("int len;");
                result.append("\n\t\t\t\t").append("while ((len = in.read(buf)) != -1)");
                result.append("\n\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("out.write(buf, 0, len);");
                result.append("\n\t\t\t\t").append("}");
                result.append("\n\t\t\t").append("}");
                result.append("\n\t\t\t").append("catch (Exception ex)");
                result.append("\n\t\t\t").append("{");
                result.append("\n\t\t\t").append("if (log.isErrorEnabled())");
                result.append("\n\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("log.error(\"浏览图片发生异常：\", ex);");
                result.append("\n\t\t\t\t").append("}");
                result.append("\n\t\t\t").append("}");
                result.append("\n\t\t\t").append("finally");
                result.append("\n\t\t\t").append("{");
                result.append("\n\t\t\t\t").append("if (null != in)");
                result.append("\n\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("try");
                result.append("\n\t\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t\t").append("in.close();");
                result.append("\n\t\t\t\t\t").append("}");
                result.append("\n\t\t\t\t\t").append("catch (Exception ex)");
                result.append("\n\t\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("}");
                result.append("\n\t\t\t\t").append("}");
                result.append("\n\t\t\t\t").append("if (null != out)");
                result.append("\n\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("try");
                result.append("\n\t\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t\t").append("out.close();");
                result.append("\n\t\t\t\t\t").append("}");
                result.append("\n\t\t\t\t\t").append("catch (Exception ex)");
                result.append("\n\t\t\t\t\t").append("{");
                result.append("\n\t\t\t\t\t").append("}");
                result.append("\n\t\t\t\t").append("}");
                result.append("\n\t\t\t").append("}");
                result.append("\n\t\t").append("}");
                result.append("\n\t\t").append("return null;");
                result.append("\n\t").append("}");
            }
        }
        return result.toString();
    }

    /**
     * 生成图片（单文件上传）的Action配置
     * @param fieldList
     * @return
     */
    private String genViewImageActionConf(List<FormField> fieldList , String packageName , String baseClassName)
    {
        StringBuffer result = new StringBuffer();
        for (FormField formField : fieldList)
        {
            if ("ImageUpload".equalsIgnoreCase(formField.getFieldDisplayType()))
            {
                String methodName = JspCodeUtil.convertMothodWord(formField.getFieldName());
                result.append("\r\n\t\t");
                result.append("<action name=\"view").append(methodName).append("Image\"");
                result.append(" class=\"").append(packageName).append("web.").append(baseClassName).append("Action\"");
                result.append(" method=\"view").append(methodName).append("Image\" />");
            }
        }
        return result.toString();
    }

    /**
     * 生成Dao中Blob代码
     * @return
     */
    private String genDaoBlobCode(List<FormField> fieldList) 
    {
        StringBuffer result = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        int index = 0;
        for (FormField formField : fieldList)
        {
            if ("ImageUpload".equalsIgnoreCase(formField.getFieldDisplayType()))
            {
                index++;
                String methodName = JspCodeUtil.convertMothodWord(formField.getFieldName());
                result.append("\r\n\t\t").append("Blob blob").append(index).append(" = ${lowerclassname}.get").append(methodName)
                        .append("();");
                result.append("\r\n\t\t").append("${lowerclassname}.set").append(methodName).append(
                        "(Hibernate.createBlob(new byte[1]));");
                temp.append("\r\n\t\t").append("${lowerclassname}.set").append(methodName).append("(blob").append(index).append(
                        ");");
            }
        }
        if (result.length() > 0)
        {
            result.append("\r\n\t\t").append("this.saveObject(${lowerclassname});");
            result.append("\r\n\t\t").append("this.flush();");
            result.append("\r\n\t\t").append("this.refresh(${lowerclassname}, LockMode.UPGRADE);");
            result.append(temp);
        }
        return "//此处处理Blob对象（Blob对象不能直接insert）" + result.toString();
    }

    /**
     * 生成HQL Where条件代码块
     * @return
     */
    private String genWhereHqlCode()
    {
        StringBuffer result = new StringBuffer();
        List<FormField> queryList = formFieldService.getQueryField(this.table.getId());
        if ((null != queryList) && (0 < queryList.size()))
        {
            for (FormField field : queryList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                //只有文本框，下拉框，单选框，日期选择框等可以被查询，多选框不可以被查询
                if (!(("TextBox".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("Radio".equalsIgnoreCase(field.getFieldDisplayType())) || ("DatePick"
                        .equalsIgnoreCase(field.getFieldDisplayType()))))
                {
                    continue;
                }

                //转换首字母
                String propertyName = JspCodeUtil.convertPropertyWord(field.getFieldName());
                String methodName = JspCodeUtil.convertMothodWord(field.getFieldName());

                if (("nvarchar".equals(field.getFieldType())) || ("ntext".equals(field.getFieldType()))
                        || ("varchar".equals(field.getFieldType())))
                {
                    //如果数据类型是nvarchar或ntext
                    result.append("\n\t\t\t").append("if ((null != ${lowerclassname}.get").append(methodName).append(
                            "()) && (0 < ${lowerclassname}.get").append(methodName).append("().trim().length())){");
                    if (("TextBox".equalsIgnoreCase(field.getFieldDisplayType()))
                            || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType())))
                    {
                        //表单域为TextBox或TextArea类型
                        result.append("\n\t\t\t\t").append("paraMap.put(\"").append(propertyName).append(
                                "\", \"%\" + ${lowerclassname}.get").append(methodName).append("().trim() + \"%\");");
                    }
                    else if (("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                            || ("Radio".equalsIgnoreCase(field.getFieldDisplayType())))
                    {
                        //表单域为DropdownList或Radio类型
                        result.append("\n\t\t\t\t").append("paraMap.put(\"").append(propertyName).append("\", ${lowerclassname}.get")
                                .append(methodName).append("().trim());");
                    }
                    result.append("\n\t\t\t").append("}").append("\r\n");
                }
                else if (("int".equals(field.getFieldType())) || ("decimal".equals(field.getFieldType()))
                        || ("number".equals(field.getFieldType())))
                {
                    //如果数据类型是int或decimal，此时只有文本输入框
                    result.append("\n\t\t\t").append("if (null != ${lowerclassname}.get").append(methodName).append("()){");
                    result.append("\n\t\t\t\t").append("paraMap.put(\"").append(propertyName).append("\", ${lowerclassname}.get")
                            .append(methodName).append("());");
                    result.append("\n\t\t\t").append("}").append("\r\n");
                }
                else if (("datetime".equals(field.getFieldType())) || ("date".equals(field.getFieldType())))
                {
                    //如果数据类型是datetime
                    result.append("\n\t\t\t").append("if (null != query").append(methodName).append("Start){");
                    result.append("\n\t\t\t\t").append("paraMap.put(\"start").append(methodName).append("\", query")
                            .append(methodName).append("Start);");
                    result.append("\n\t\t\t").append("}");
                    result.append("\n");
                    result.append("\n\t\t\t").append("if (null != query").append(methodName).append("End){");
                    result.append("\n\t\t\t\t").append("paraMap.put(\"end").append(methodName).append("\", query")
                            .append(methodName).append("End);");
                    result.append("\n\t\t\t").append("}");
                }
            }
        }
        return result.toString();
    }

    /**
     * 生成SQL Map HQL Where条件代码块
     * @return
     */
    private String genMapWhereHqlCode() 
    {
        StringBuffer result = new StringBuffer();
        List<FormField> queryList = formFieldService.getQueryField(this.table.getId());
        if ((null != queryList) && (0 < queryList.size()))
        {
            for (FormField field : queryList)
            {
                //过滤掉ROW_ID
                if ("ROW_ID".equalsIgnoreCase(field.getFieldName()))
                {
                    continue;
                }
                //只有文本框，下拉框，单选框，日期选择框等可以被查询，多选框不可以被查询
                if (!(("TextBox".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                        || ("Radio".equalsIgnoreCase(field.getFieldDisplayType())) || ("DatePick"
                        .equalsIgnoreCase(field.getFieldDisplayType()))))
                {
                    continue;
                }

                //转换首字母
                String propertyName = JspCodeUtil.convertPropertyWord(field.getFieldName());
                String methodName = JspCodeUtil.convertMothodWord(field.getFieldName());
                result.append("\r\n\t\t");

                if (("nvarchar".equals(field.getFieldType())) || ("ntext".equals(field.getFieldType()))
                        || ("varchar".equals(field.getFieldType())))
                {
                    result.append("<isNotEmpty property=\"").append(propertyName).append("\">");
                    result.append("\r\n\t\t\t");
                    result.append("<![CDATA[and t.").append(propertyName);
                    //如果数据类型是nvarchar或ntext
                    if (("TextBox".equalsIgnoreCase(field.getFieldDisplayType()))
                            || ("TextArea".equalsIgnoreCase(field.getFieldDisplayType())))
                    {
                        //表单域为TextBox或TextArea类型
                        result.append(" like ");
                    }
                    else if (("DropdownList".equalsIgnoreCase(field.getFieldDisplayType()))
                            || ("Radio".equalsIgnoreCase(field.getFieldDisplayType())))
                    {
                        //表单域为DropdownList或Radio类型
                        result.append(" = ");
                    }
                    result.append(":").append(propertyName).append("]]>");

                }
                else if (("int".equals(field.getFieldType())) || ("decimal".equals(field.getFieldType()))
                        || ("number".equals(field.getFieldType())))
                {
                    //如果数据类型是int或decimal，此时只有文本输入框
                    result.append("<isNotEmpty property=\"").append(propertyName).append("\">");
                    result.append("\r\n\t\t\t");
                    result.append("<![CDATA[and t.").append(propertyName).append(" = :").append(propertyName).append(
                            "]]>");
                }
                else if (("datetime".equals(field.getFieldType())) || ("date".equals(field.getFieldType())))
                {
                    //如果数据类型是datetime，则要增加start和end
                    result.append("<isNotEmpty property=\"start").append(methodName).append("\">");
                    result.append("\r\n\t\t\t");
                    result.append("<![CDATA[and t.").append(propertyName).append(" >= :start").append(methodName)
                            .append("]]>");
                    result.append("\r\n\t\t").append("</isNotEmpty>");
                    result.append("\r\n\t\t").append("<isNotEmpty property=\"end").append(methodName).append("\">");
                    result.append("\r\n\t\t\t");
                    result.append("<![CDATA[and t.").append(propertyName).append(" <= :end").append(methodName).append(
                            "]]>");
                }
                result.append("\r\n\t\t").append("</isNotEmpty>");
            }
        }
        return result.toString();
    }

    /**
     * 根据TableId获取Code相应的Map
     * @param tableId
     * @return CodeID对应的Code的Map
     * @throws Exception
     */
    private Map<String, Code> getCodeMap(String tableId) {
        //        String hql = "from Code where id in (select dataSourceType from FormField where dataSource = :dataSource and dataSourceType != -1 and table.id = :tableid)";
        //        Map paraMap = new HashMap();
        //        paraMap.put("dataSource", "Codes");
        //        paraMap.put("tableid", tableId);
        //        List list = codeDao.findListByHql(hql, paraMap);
        //        Map<String, Code> codeMap = new LinkedHashMap<String, Code>();
        //        for (Object obj : list)
        //        {
        //            Code code = (Code) obj;
        //            codeMap.put(code.getId(), code);
        //        }
        //        return codeMap;

        ///////////////////////////////////////
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
    
/**********************************************************************************/
/* 以下方法用于生成基于JQuery Mobile的html页面，action类以及Struts配置文件 author：max_mac*/
/**********************************************************************************/
 
    
	private void genJqmAddHtml(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String addListElementDetail="";
    	List<FormField> disPlayFields=formFieldService.getDisplayField(table.getId());
    	for(int i=0;i<disPlayFields.size();i++){
    		String temp=getCommonTemplate("addListElementDetail");
    		temp=temp.replace("%FieldDisplayName%", disPlayFields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ObjectName%", objectName);
    		temp=temp.replace("%FieldName%", disPlayFields.get(i).getFieldName().toLowerCase());
    		addListElementDetail=addListElementDetail+(i!=0?getTabs(8)+"+":"")+temp+(i!=disPlayFields.size()-1?"\n":"");
    	}
    	
    	String form="";
    	List<FormField> fields=formFieldService.getAllField(table.getId());
    	for(int i=0;i<fields.size();i++){
    		if("ROW_ID".equals(fields.get(i).getFieldName()))
    			continue;
    		String temp=getCommonTemplate("form");
    		temp=temp.replace("%FieldDisplayName%", fields.get(i).getFieldDisplayName());
    		temp=temp.replace("%FieldName%", fields.get(i).getFieldName().toLowerCase());
    		form=form+getTabs(5)+temp+(i!=fields.size()-1?"\n":"");
    	}
    	
    	String src=readTemplate("JQM_Add.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%AddListElementDetail%", addListElementDetail);
    	src=src.replace("%Form%", form);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_add.html";
        createFile(fileName);
        writeFile(fileName, src);
    }
 
    private void genJqmAdd2Html(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String addListElementDetail="";
    	List<FormField> disPlayFields=formFieldService.getDisplayField(table.getId());
    	for(int i=0;i<disPlayFields.size();i++){
    		String temp=getCommonTemplate("addListElementDetail");
    		temp=temp.replace("%FieldDisplayName%", disPlayFields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ObjectName%", objectName);
    		temp=temp.replace("%FieldName%", disPlayFields.get(i).getFieldName().toLowerCase());
    		addListElementDetail=addListElementDetail+(i!=0?getTabs(8)+"+":"")+temp+(i!=disPlayFields.size()-1?"\n":"");
    	}
    	
    	String form="";
    	List<FormField> fields=formFieldService.getAllField(table.getId());
    	for(int i=0;i<fields.size();i++){
    		if("ROW_ID".equals(fields.get(i).getFieldName()))
    			continue;
    		String temp=getCommonTemplate("form");
    		temp=temp.replace("%FieldDisplayName%", fields.get(i).getFieldDisplayName());
    		temp=temp.replace("%FieldName%", fields.get(i).getFieldName().toLowerCase());
    		form=form+getTabs(5)+temp+(i!=fields.size()-1?"\n":"");
    	}
    	
    	String src=readTemplate("JQM_Add2.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%AddListElementDetail%", addListElementDetail);
    	src=src.replace("%Form%", form);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_add2.html";
        createFile(fileName);
        writeFile(fileName, src);
    }
    
    private void genJqmListHtml(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String listElementDetail="";
    	List<FormField> disPlayFields=formFieldService.getDisplayField(table.getId());
    	for(int i=0;i<disPlayFields.size();i++){
    		String temp=getCommonTemplate("listElementDetail");
    		temp=temp.replace("%FieldDisplayName%", disPlayFields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ObjectName%", objectName);
    		temp=temp.replace("%FieldName%", disPlayFields.get(i).getFieldName().toLowerCase());
    		listElementDetail=listElementDetail+(i!=0?getTabs(10)+"+":"")+temp+(i!=disPlayFields.size()-1?"\n":"");
    	}
    	
    	String src=readTemplate("JQM_List.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%ListElementDetail%", listElementDetail);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_list.html";
        createFile(fileName);
        writeFile(fileName, src);
    }
 
    private void genJqmList2Html(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String listElementDetail="";
    	List<FormField> disPlayFields=formFieldService.getDisplayField(table.getId());
    	for(int i=0;i<disPlayFields.size();i++){
    		String temp=getCommonTemplate("listElementDetail");
    		temp=temp.replace("%FieldDisplayName%", disPlayFields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ObjectName%", objectName);
    		temp=temp.replace("%FieldName%", disPlayFields.get(i).getFieldName().toLowerCase());
    		listElementDetail=listElementDetail+(i!=0?getTabs(10)+"+":"")+temp+(i!=disPlayFields.size()-1?"\n":"");
    	}
    	
    	String src=readTemplate("JQM_List2.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%ListElementDetail%", listElementDetail);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_list2.html";
        createFile(fileName);
        writeFile(fileName, src);
    }
    
    private void genJqmDetailHtml(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String objectDetail="";
    	List<FormField> fields=formFieldService.getDisplayDetailField(table.getId());
    	for(int i=0;i<fields.size();i++){
    		String temp=getCommonTemplate("objectDetail");
    		temp=temp.replace("%FieldDisplayName%", fields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ObjectName%", objectName);
    		temp=temp.replace("%FieldName%", fields.get(i).getFieldName().toLowerCase());
    		objectDetail=objectDetail+(i!=0?getTabs(10)+"+":"")+temp+(i!=fields.size()-1?"\n":"");
    	}
    	
    	String src=readTemplate("JQM_Detail.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%ObjectDetail%", objectDetail);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_detail.html";
        createFile(fileName);
        writeFile(fileName, src);
    }
    
    private void genJqmEditHtml(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String form="";
    	List<FormField> fields=formFieldService.getAllField(table.getId());
    	for(int i=0;i<fields.size();i++){
    		if("ROW_ID".equals(fields.get(i).getFieldName()))
    			continue;
    		String temp=getCommonTemplate("form");
    		temp=temp.replace("%FieldDisplayName%", fields.get(i).getFieldDisplayName());
    		temp=temp.replace("%FieldName%", fields.get(i).getFieldName().toLowerCase());
    		form=form+getTabs(5)+temp+(i!=fields.size()-1?"\n":"");
    	}
    	
    	String src=readTemplate("JQM_Edit.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%Form%", form);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_edit.html";
        createFile(fileName);
        writeFile(fileName, src);
    }

    private void genJqmLocalHtml(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String classDisplayName=table.getTableName();
    	
    	String listElementDetail="";
    	List<FormField> disPlayFields=formFieldService.getDisplayField(table.getId());
    	for(int i=0;i<disPlayFields.size();i++){
    		String temp=getCommonTemplate("listElementDetail");
    		temp=temp.replace("%FieldDisplayName%", disPlayFields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ObjectName%", objectName);
    		temp=temp.replace("%FieldName%", disPlayFields.get(i).getFieldName().toLowerCase());
    		listElementDetail=listElementDetail+(i!=0?getTabs(10)+"+":"")+temp+(i!=disPlayFields.size()-1?"\n":"");
    	}
    	
    	String form="";
    	List<FormField> fields=formFieldService.getAllField(table.getId());
    	for(int i=0;i<fields.size();i++){
    		if("ROW_ID".equals(fields.get(i).getFieldName()))
    			continue;
    		String temp=getCommonTemplate("form");
    		temp=temp.replace("%FieldDisplayName%", fields.get(i).getFieldDisplayName());
    		temp=temp.replace("%FieldName%", fields.get(i).getFieldName().toLowerCase());
    		form=form+getTabs(5)+temp+(i!=fields.size()-1?"\n":"");
    	}
    	
    	String localElementDetail="";
    	for(int i=0;i<disPlayFields.size();i++){
    		String temp=getCommonTemplate("localElementDetail");
    		temp=temp.replace("%FieldDisplayName%", disPlayFields.get(i).getFieldDisplayName());
    		temp=temp.replace("%ClassName%", className);
    		temp=temp.replace("%FieldName%", disPlayFields.get(i).getFieldName().toLowerCase());
    		localElementDetail=localElementDetail+(i!=0?getTabs(10)+"+":"")+temp+(i!=disPlayFields.size()-1?"\n":"");
    	}
    	
    	
    	String src=readTemplate("JQM_Local.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%ClassDisplayName%", classDisplayName);
    	src=src.replace("%ListElementDetail%", listElementDetail);
    	src=src.replace("%Form%", form);
    	src=src.replace("%LocalElementDetail%", localElementDetail);
    	
        String fileName = this.htmlPath + File.separator + objectName + "_local.html";
        createFile(fileName);
        writeFile(fileName, src);
    }
    
    private void genJqmAction(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String packageName=table.getSrcPackage();
    	
    	
    	String src=readTemplate("JQM_Action.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%PackageName%", packageName);
    	
        String fileName = this.webPath + File.separator + className + "MobileAction.java";
        createFile(fileName);
        writeFile(fileName, src);
    }
    
    private void genJqmStruts(FormTable table){
    	String className=JspCodeUtil.convertMothodWord(table.getPhysicalName());
    	String objectName=JspCodeUtil.convertPropertyWord(table.getPhysicalName());
    	String packageName=table.getSrcPackage();
    	
    	
    	String src=readTemplate("JQM_Struts.template");
    	src=src.replace("%ClassName%", className);
    	src=src.replace("%ObjectName%", objectName);
    	src=src.replace("%PackageName%", packageName);
    	
        String fileName = this.confPath + File.separator + "struts-" + objectName + "-mobile.xml";
        createFile(fileName);
        writeFile(fileName, src);
    }
    
    private String getTabs(int t){
    	String temp="";
    	for(int i=0;i<t;i++){
    		temp=temp+"\t";
    	}
    	return temp;
    }
    
    private String getCommonTemplate(String key){
		Properties propeties=new Properties();
		try {
			propeties.load(getClass().getResourceAsStream(TEMPLATE_CLASS_PATH + "common.template"));
			return propeties.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static void main(String[] args) {
    	SshCodeGenerate t = new SshCodeGenerate();
    	  //文件名前不加“/”，则表示从当前类所在的包下查找该资源。如下则表示的是从包myspider下查找22.properties文件资源。  
        System.out.println("1："+t.getClass().getResourceAsStream(TEMPLATE_CLASS_PATH+"Java_Entity.template"));//输出java.io.BufferedInputStream@61de33  
  
        //文件名前加了“/”，则表示从类路径下也就是从classes文件夹下查找资源，如下表示从classes文件夹下查找22.properties文件资源。  
        System.out.println("2："+t.getClass().getResourceAsStream("Java_Entity.template"));//输出null  
  
        //文件名前加了“/”，则表示从类路径下也就是从classes文件夹下查找资源，如下表示从classes文件夹下查找11.properties文件资源。  
        System.out.println("3："+t.getClass().getResourceAsStream("com/jshx/module/form/service/generate/template/Java_Entity.template"));//输出java.io.BufferedInputStream@14318bb  
        System.out.println();  
  
        //当前包路径4：file:/E:/myobject/myspider/build/classes/myspider/  
        System.out.println("4："+t.getClass().getResource(""));  
  
        //输出当前类路径5：file:/E:/myobject/myspider/build/classes/  
        System.out.println("5："+t.getClass().getResource("/"));  
	}
}
