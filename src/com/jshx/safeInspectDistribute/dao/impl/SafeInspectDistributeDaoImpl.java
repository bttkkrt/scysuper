package com.jshx.safeInspectDistribute.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safeInspectDistribute.dao.SafeInspectDistributeDao;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.entity.SafeInspectTjBean;

@Component("safeInspectDistributeDao")
public class SafeInspectDistributeDaoImpl extends BaseDaoImpl implements SafeInspectDistributeDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
//		List<SafeInspectDistribute> list1 = sqlMapClientTemplate.queryForPaginatedList("findSafeInspectDistributeByMap", paraMap, page.getPageSize());
//		List<SafeInspectDistribute> list = sqlMapClientTemplate.queryForList("findSafeInspectDistributeByMap",paraMap,Integer.valueOf(page.getPageNumber()), Integer.valueOf(page.getPageSize()));
//		page.setList(list);
//		return page;
		return this.findPageByHqlId("findSafeInspectDistributeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafeInspectDistribute(Map<String, Object> paraMap){
		//return this.findListBySqlId("findSafeInspectDistributeByMap", paraMap);
		return this.findListByHqlId("findSafeInspectDistributeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafeInspectDistribute getById(String id)
	{
		return (SafeInspectDistribute)this.getObjectById(SafeInspectDistribute.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SafeInspectDistribute safeInspectDistribute)
	{
		safeInspectDistribute.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(safeInspectDistribute);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SafeInspectDistribute safeInspectDistribute)
	{
		this.saveOrUpdateObject(safeInspectDistribute);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SafeInspectDistribute.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SafeInspectDistribute safeInspectDistribute = (SafeInspectDistribute)this.getObjectById(SafeInspectDistribute.class, id);
		safeInspectDistribute.setDelFlag(1);
		this.saveObject(safeInspectDistribute);
	}
	
	@Override
	public String findMaxSerialNum() {
		return (String)sqlMapClientTemplate.queryForObject("findMaxSerialNum");
	}

	@Override
	public List<SafeInspectTjBean> getTongJiSafeInspect(Map map) {
		return sqlMapClientTemplate.queryForList("query_safeInspect_data",map);
	}
}
