package com.jshx.zfyj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zfyj.entity.LawBasis;
import com.jshx.zfyj.dao.LawBasisDao;

@Component("lawBasisDao")
public class LawBasisDaoImpl extends BaseDaoImpl implements LawBasisDao
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
		return this.findPageByHqlId("findLawBasisByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<LawBasis> findLawBasis(Map<String, Object> paraMap){
		return this.findListByHqlId("findLawBasisByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LawBasis getById(String id)
	{
		return (LawBasis)this.getObjectById(LawBasis.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LawBasis lawBasis)
	{
		lawBasis.setId(null);
		this.saveOrUpdateObject(lawBasis);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LawBasis lawBasis)
	{
		this.saveOrUpdateObject(lawBasis);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LawBasis.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LawBasis lawBasis = (LawBasis)this.getObjectById(LawBasis.class, id);
		lawBasis.setDelFlag(1);
		this.saveObject(lawBasis);
	}

	@Override
	public List<LawBasis> getLawBasisListByUserAndType(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getLawBasisListByUserAndType",map,s,l);
	}

	@Override
	public int getLawBasisListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getLawBasisListSizeByUserAndType",map);
	}
}
