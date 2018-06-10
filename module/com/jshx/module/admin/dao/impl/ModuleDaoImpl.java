/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-17        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.ModuleDao;
import com.jshx.module.admin.entity.Module;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-17 下午11:36:52  
 * 模块DAO实现类
 */
@Component("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl implements ModuleDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleDao#findById(java.lang.String)
	 */
	public Module findById(String id) {
		return (Module)super.getObjectById(Module.class, id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleDao#findModuleByList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	
	public List<Module> findModuleByList(Map<String, Object> paraMap) {
		return super.findListByHqlId("searchModule", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleDao#findModuleByPage(com.jshx.core.base.vo.Pagination, java.util.Map)
	 */
	
	public Pagination findModuleByPage(Pagination page,
			Map<String, Object> paraMap) {
		return super.findPageByHqlId("searchModule", paraMap, page);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleDao#getMaxModuleCodeByParent(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	
	public Integer getMaxModuleCodeByParent(String parentModuleCode) {
		Long length = Long.valueOf(parentModuleCode.length()+1);
		if(parentModuleCode.equals("0"))
			parentModuleCode = "";
		if(length==1)
			length += 1;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(parentModuleCode!=null ){	
			paraMap.put("parentModuleCode", parentModuleCode+"%");
			paraMap.put("length", length);
		}
		List list = this.findListByHqlId("getMaxModuleCodeByParent", paraMap);
		if(list.get(0)!=null){
			return (Integer)list.get(0);
		}else
			return null;
	}
}
