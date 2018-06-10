/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-17        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Module;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-17 下午11:31:30  
 * 模块DAO接口
 */
public interface ModuleDao extends BaseDao {
	
	/**
	 * 查找模块，返回列表
	 * 
	 * @Title: findModuleByList
	 * @Description:
	 * @param hqlId
	 * @param paraMap
	 * @return List<Department>
	 */
	public List<Module> findModuleByList(Map<String, Object> paraMap) ;

	/**
	 * 分页查找模块
	 * 
	 * @Title: findModuleByPage
	 * @Description:
	 * @param hqlId
	 * @param page
	 * @param paraMap
	 * @return Pagination
	 */
	public Pagination findModuleByPage(Pagination page,
			Map<String, Object> paraMap) ;
	
	/**
	 * 根据上层模块编码查找下层模块编码的最大值
	 * 
	 * @Title: getMaxModuleCodeByParent 
	 * @Description: 
	 * @param parentModuleCode
	 * @return Integer   
	 */
	public Integer getMaxModuleCodeByParent(String parentModuleCode);
		
	/**
	 * 根据主键查找模块
	 * 
	 * @Title: findById 
	 * @Description: 
	 * @param id
	 * @return Module   
	 */
	public Module findById(String id);

}
