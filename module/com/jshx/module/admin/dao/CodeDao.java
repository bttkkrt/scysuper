/**
 * Copyright 2011 hongxin
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li           create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.CodeValue;

/**
 * @author Huairu.Li
 * @version 创建时间：2011-1-19 上午10:11:35
 * 类说明
 */
public interface CodeDao extends BaseDao
{

    /**
     * 根据条件分页查找一维代码项
     * @param page
     * @param paraMap
     * @return
     */
    public Pagination findCodeValueByPage(Pagination page , Map<String, Object> paraMap);

    /**
     * 根据条件分页查找一维代码
     * @param page
     * @param paraMap
     * @return
     */
    public Pagination findCodeByPage(Pagination page , Map<String, Object> paraMap);

    /**
     * 根据SQL查询数据，并把结果包装成CodeValue
     * @param sql
     * @return
     */
    public List<CodeValue> getCodeValueBySql(String sql);
    
    /**
	 * 根据上层一维代码编码查找下层一维代码编码的最大值
	 * 
	 * @Title: getMaxItemCodeByParent 
	 * @Description: 
	 * @param parentItemCode
	 * @return BigDecimal   
	 */
	public BigDecimal getMaxItemCodeByParent(String parentItemCode) ;
	
	
	/**
	 * 根据CodeId查找CodeValue List
	 * 
	 * @Title: getMaxItemCodeByParent 
	 * @Description: 
	 * @param parentItemCode
	 * @return BigDecimal   
	 */
	 public List<CodeValue> findCodeValueByCodeId(String codeId);
	 
	 /**
		 * 根据条件查询一维代码表信息
		 * @author luting
		 */
		public CodeValue findCodeValueByMap(Map map);

}
