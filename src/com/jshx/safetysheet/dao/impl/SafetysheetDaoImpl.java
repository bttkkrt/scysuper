package com.jshx.safetysheet.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safetysheet.entity.Safetysheet;
import com.jshx.safetysheet.dao.SafetysheetDao;

@Component("safetysheetDao")
public class SafetysheetDaoImpl extends BaseDaoImpl implements SafetysheetDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSafetysheetByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafetysheet(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafetysheetByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Safetysheet getById(String id)
	{
		return (Safetysheet)this.getObjectById(Safetysheet.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Safetysheet safetysheet)
	{
		safetysheet.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(safetysheet);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Safetysheet safetysheet)
	{
		this.saveOrUpdateObject(safetysheet);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Safetysheet.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Safetysheet safetysheet = (Safetysheet)this.getObjectById(Safetysheet.class, id);
		safetysheet.setDelFlag(1);
		this.saveObject(safetysheet);
	}

	@Override
	public String findUserRoleIdByName(String string) {
		// TODO Auto-generated method stub
		return (String) this.findListByHql("select id from UserRole where delFlag=0 and roleName='"+string+"'").get(0);
	}
}
