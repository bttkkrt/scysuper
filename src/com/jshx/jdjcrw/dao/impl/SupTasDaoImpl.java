package com.jshx.jdjcrw.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdjcrw.entity.SupTas;
import com.jshx.jdjcrw.dao.SupTasDao;

@Component("supTasDao")
public class SupTasDaoImpl extends BaseDaoImpl implements SupTasDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSupTasByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSupTas(Map<String, Object> paraMap){
		return this.findListByHqlId("findSupTasByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupTas getById(String id)
	{
		return (SupTas)this.getObjectById(SupTas.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SupTas supTas)
	{
		supTas.setId(null);
		this.saveOrUpdateObject(supTas);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SupTas supTas)
	{
		this.saveOrUpdateObject(supTas);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SupTas.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SupTas supTas = (SupTas)this.getObjectById(SupTas.class, id);
		supTas.setDelFlag(1);
		this.saveObject(supTas);
	}
	public List<Map<String,Object>> findCheckListByMap(Map<String, Object> paraMap){//获取检查机构的用户id和name
		String sqlId=(String) paraMap.get("sqlId");
		return this.sqlMapClientTemplate.queryForList(sqlId,paraMap);
		
	}
	
	public List<Map> getcheckList(Map map) {
		return this.sqlMapClientTemplate.queryForList("checkList",map);
	}
}
