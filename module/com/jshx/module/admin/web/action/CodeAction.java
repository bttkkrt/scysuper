/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.extend.ICodeValueExtendInfo;
import com.jshx.module.admin.service.CodeService;

/**
 * @author Huairu.Li
 * @version 创建时间：2011-1-19 下午02:51:57 类说明
 */
public class CodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired() 
	@Qualifier("codeService")
	private CodeService codeService;

	private Code code;

	private CodeValue codeValue;

	private String[] id;

	private Pagination pagination;

	private String result;

	private Integer refresh;

	private String selNode;
	
	private String callback;
	
	private String codeName;
	
	private String flag;
	/**
	 * 执行查询sql，返回查询一维代码项的json数据:<br>
	 * {"childNum":0,"codeId":"","delFlag":0,"id":"","itemCode":"","itemText":"","itemValue":"","sortSQ":0}
	 */
	public void findCodeValue() {
		String sql = getRequestParameter("querySql");
		if(sql==null || sql.trim().equals("")){
			Map<String, String> valueMap = Constants.CODE_MAP.get(codeValue.getCodeId());
			String itemText = valueMap.get(codeValue.getItemValue());
			codeValue.setItemText(itemText);
		}else{
			if ((sql.indexOf(Constants.CODE_SQL_PATTERN_DEPAR_ID) >= 0)
					|| (sql.indexOf(Constants.CODE_SQL_PATTERN_USER_ID) >= 0)) {
				HttpSession session = Struts2Util.getSession();
				User user = (User) session.getAttribute(Constants.CURR_USER);
				if (sql.indexOf(Constants.CODE_SQL_PATTERN_DEPAR_ID) >= 0) {
					sql = sql.replace(Constants.CODE_SQL_PATTERN_DEPAR_ID, "'"
							+ ((Department) user.getDept()).getId() + "'");
				}
				if (sql.indexOf(Constants.CODE_SQL_PATTERN_USER_ID) >= 0) {
					sql = sql.replace(Constants.CODE_SQL_PATTERN_USER_ID, "'"
							+ user.getId() + "'");
				}
			}
			List<CodeValue> codeValueList = null;
			codeValueList = codeService.getCodeValueBySql(sql);
			for (CodeValue value : codeValueList) {
				if (value.getItemValue().equals(codeValue.getItemValue())) {
					codeValue = value;
					break;
				}
			}
		}
		
		this.outputJson( "id|itemValue|itemText|sortSQ|delFlag|codeId|childNum|itemCode|", codeValue);
		
	}

	/**
	 * 一维代码项初始化修改
	 * 
	 * @return
	 */
	public String editCodeValue(){
		if(codeValue.getId()!=null)
			this.codeValue = codeService.findCodeValueById(codeValue.getId());
		return EDIT;		
	}
	
	/**
	 * 删除一维代码项
	 * 
	 * @return
	 */
	public void deleteCodeValue() {
		if (id != null) {
			for (String item : id)
				codeService.deleteCodeValue(item);
			codeService.updateCodeMap();
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}			
		}
	}

	/**
	 * 保存一维代码项
	 * 
	 * @return
	 */
	public String saveCodeValue() {
		if (codeValue != null) {
			codeValue.setDelFlag(0);
			ICodeValueExtendInfo extendInfo = (ICodeValueExtendInfo)createExtendInfo();
			codeValue.setExtendInfo(extendInfo);
			
			if (codeValue.getSortSQ() == null)
				codeValue.setSortSQ(0);
			if (codeValue.getId() == null)
				codeService.saveCodeValue(codeValue);
			else {
				codeService.modifyCodeValue(codeValue);
			}
			codeService.updateCodeMap();

			return RELOAD;
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.CODE_NULL_EXCEPTION);
			throw ex;
		}
	}
	
	/**
	 * 分页查询一维代码，返回查询一维代码项的json数据:<br>
	 * {"total":1,"rows":[{"codeName":"","id":"","sortSQ":0}]}
	 * @return
	 */
	public void listCode() {
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (code != null) {
			if (code.getCodeName() != null && code.getCodeName().trim() != "")
				paraMap.put("codeName", "%" + code.getCodeName().trim() + "%");
		}
		pagination = codeService.findCodeByPage(pagination, paraMap);
		outputJsonList(pagination.getTotalCount(), "id|codeName|sortSQ|", pagination.getListOfObject());
		
	}

	/**
	 * 删除一维代码
	 * 
	 * @return
	 */
	public void deleteCode() {
		if (id != null) {
			for (String item : id)
				codeService.deleteCode(item);
			codeService.updateCodeMap();
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
			}
		}
	}

	/**
	 * 一维代码初始化修改信息
	 * 
	 * @return
	 */
	public String editCode(){
		if(flag.equals("edit"))
			this.code = codeService.findCodeById(code.getId());
		return EDIT;
	}
	
	/**
	 * 保存一维代码
	 * 
	 * @return
	 */
	public String saveCode() {
		if (code != null) {
			code.setDelFlag(0);
			if (code.getSortSQ() == null)
				code.setSortSQ(0);
			if (code.getId() == null)
				codeService.saveCode(code);
			else {
				codeService.modifyCode(code);
			}
			codeService.updateCodeMap();

			return RELOAD;
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.CODE_VALUE_NULL_EXCEPTION);
			throw ex;
		}
	}
	/**
	 * 一维代码项菜单树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	public void findChildNode() {
		String codeId = codeValue.getCodeId();
		code = codeService.findCodeById(codeId);
		try {
			if (null == selNode) {
				// 初始化部门树
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("id", null);
				root.put("text", code.getCodeName());
				root.put("state", "opened");
				List<CodeValue> codeValueList = codeService
						.findCodeValueByCode(codeId);
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				for (CodeValue v : codeValueList) {
					if (v.getParentItem() != null)
						continue;
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", v.getItemCode()+"|"+v.getId());
					item.put("text", v.getItemText());
					if (codeService.findCodeByParentCode(v.getId(),
							codeId).size() > 0) {
						item.put("state", "closed");
					}
					elements.add(item);
				}
				root.put("children", elements);
				items.add(root);
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().println(json.toString());
			} else {
				List<CodeValue> codeValueList = codeService
						.findCodeByParentCode(selNode.split("\\|")[1], codeId);
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				for (CodeValue v : codeValueList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", v.getItemCode()+"|"+v.getId());
					item.put("text", v.getItemText());
					if (codeService.findCodeByParentCode(v.getId(),
							codeId).size() > 0) {
						item.put("state", "closed");
					}
					items.add(item);
				}
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().println(json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 在左侧一维代码树点击查询子代码项，返回查询结果的json数据：<br>
	 * {"total":1,"rows":[{"id":"","itemCode":"","itemText":"","itemValue":"","sortSQ":0}]}
	 */
	public void listChild() {
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (codeValue != null) {
			paraMap.put("codeId", codeValue.getCodeId());
			if(codeValue.getParentItem()!=null && codeValue.getParentItem().getId()!=null && !codeValue.getParentItem().getId().trim().equals(""))
			    paraMap.put("parentItem", codeValue.getParentItem());
			if (codeValue.getItemValue() != null && !codeValue.getItemValue().trim().equals(""))
				paraMap.put("itemValue", codeValue.getItemValue().trim());

			if (StringUtil.isNotNull(codeValue.getItemText()) && StringUtil.isNotEmpty(codeValue.getItemText()))
				paraMap.put("itemText", "%" + codeValue.getItemText().trim()+ "%");
		}
		pagination = codeService.findCodeValueByPage(pagination, paraMap);
		outputJsonList(pagination.getTotalCount(), "id|itemCode|itemValue|itemText|sortSQ|", pagination.getListOfObject());
		
	}
	
	/**
	 * 根据一维代码类型，返回<itemValue,itemText>，支持jquery ajax跨域 
	 * add by max
	 */
	public void findCodeValueForMobile(){
		if(codeName!=null && !"".equals(codeName)){
			List<CodeValue> results = codeService.getCodeValuesByCodeName(codeName);
			
			final String colNames = new String("itemValue|itemText|");
			JsonConfig config = new JsonConfig();
			config.setJsonPropertyFilter(new PropertyFilter(){
				public boolean apply(Object source, String name, Object value) { 
					if(colNames.indexOf(name+"|")!=-1)
						return false;
					else
						return true;
				}
			});
			JSONArray json = JSONArray.fromObject(results, config);
			try{
				this.getResponse().getWriter().println(callback+"("+json.toString()+")");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public String codeMenu(){
		return SUCCESS;
	}
	
	public String initListChild(){
		return SUCCESS;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public CodeValue getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(CodeValue codeValue) {
		this.codeValue = codeValue;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}
	
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the refresh
	 */
	public Integer getRefresh() {
		return refresh;
	}

	/**
	 * @param refresh
	 *            the refresh to set
	 */
	public void setRefresh(Integer refresh) {
		this.refresh = refresh;
	}

	public String getSelNode() {
		return selNode;
	}

	public void setSelNode(String selNode) {
		this.selNode = selNode;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
