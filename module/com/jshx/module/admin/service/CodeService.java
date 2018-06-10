/**
 * Copyright 2011 hongxin
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li          create
 * 2011-1-22         Chenjian         添加根据一维代码查找代码值
 * 2011-1-24         Chenjian         添加根据一维代码以及代码值查找代码实体
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.CodeValue;

/**
 * @author   Huairu.Li
 * @version 创建时间：2011-1-19 下午02:51:57
 * 类说明
 */
public interface CodeService {
	
	/**
	 * 根据上层一维代码编号生成一维代码编号<br>
	 * 例如上层一维代码编号为001，下层已有001001，则新加子层编号为001002
	 * 
	 * @param parentItemCode
	 * @return String   
	 */
	public String createItemCode(String parentItemCode);
	
	/**
	 * 根据ID查找Code
	 * 
	 * @param id
	 * @return Code  
	 */
	public Code findCodeById(String id);
	
	/**
	 * 根据一维代码ID查找下层一维代码
	 * 
	 * @param parentItemId
	 * @return List<CodeValue>  
	 */
	public List<CodeValue> findCodeByParent(String parentItemId);
	
	/**
	 * 根据一维代码编码查找下层一维代码
	 * 
	 * @param parentItemCode
	 * @param codeId
	 * @return List<CodeValue>  
	 */
	public List<CodeValue> findCodeByParentCode(String parentItemCode, String codeId);

	/**
	 * 保存一维代码项
	 * @param codeValue
	 */
	public CodeValue saveCodeValue(CodeValue codeValue);

	/**
	 * 修改一维代码项
	 * @param codeValue
	 */
	public CodeValue modifyCodeValue(CodeValue codeValue);

	/**
	 * 删除一维代码项
	 * @param id
	 */
	public void deleteCodeValue(String id);

	/**
	 * 根据条件分页查找一维代码项
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination findCodeValueByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 保存一维代码
	 * @param codeValue
	 */
	public Code saveCode(Code code);

	/**
	 * 修改一维代码
	 * @param codeValue
	 */
	public Code modifyCode(Code code);

	/**
	 * 删除一维代码
	 * @param id
	 */
	public void deleteCode(String id);

	/**
	 * 根据条件分页查找一维代码
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination findCodeByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 根据一维代码查找代码实体
	 *
	 * @param codeId
	 * @return List<CodeValue>
	 */
	public List<CodeValue> findCodeValueByCode(String codeId);

	/**
	 * 根据一维代码以及代码值查找代码实体
	 *
	 * @param codeId
	 * @param itemValue
	 * @return CodeValue
	 */
	public CodeValue findValueByCodeAndItem(String codeId, String itemValue);

	/**
	 * 根据ID查找一维代码
	 *  
	 * @param id
	 * @return CodeValue 
	 */
	public CodeValue findCodeValueById(String id);

	/**
	 * 得到所有的代码实体
	 */
	public List<Code> getAllCode();

	/**
     * 根据SQL查询数据，并把结果包装成CodeValue
     * @param sql
     * @return
     */
    public List<CodeValue> getCodeValueBySql(String sql);


	/**
	 * 根据CodeName获取CodeValue的列表
	 * @param codeName
	 * @return
	 */
	public List<CodeValue> getCodeValuesByCodeName(String codeName);

	/**
	 * 根据CodeName和ItemValue获取CodeValue
	 * @param codeName
	 * @param itemValue
	 * @return
	 */
	public CodeValue getCodeValueByCodeNameAndItemValue(String codeName , String itemValue);
	
	/**
	 * 根据一维代码编号查找
	 * 
	 * @param codeId
	 * @param itemCode
	 * @return CodeValue  
	 */
	public CodeValue getCodeValueByItemCode(String codeId, String itemCode);
	
	/**
	 * 判断某个一维代码下面的代码值或代码显示值有没有注册过
	 * 
	 * @param codeId
	 * @param itemText
	 * @param itemValue
	 * @return Boolean   
	 */
	public Boolean checkCodeValue(String codeId,String itemText, String itemValue);
	/** 更新一维代码表缓存 */
	public void updateCodeMap();
	
	/**
	 * 根据条件查询一维代码表信息
	 * @author luting
	 */
	public CodeValue findCodeValueByMap(Map map);
}
