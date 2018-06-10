package com.wzxx.bgxz.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.bgxz.dao.BgxzDao;
import com.wzxx.bgxz.entity.Bgxz;
import com.wzxx.gzdt.entity.Gzdt;

@Component("bgxzDao")
public class BgxzDaoImpl extends BaseDaoImpl implements BgxzDao
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
		return this.findPageByHqlId("findBgxzByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findBgxz(Map<String, Object> paraMap){
		return this.findListByHqlId("findBgxzByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Bgxz getById(String id)
	{
		return (Bgxz)this.getObjectById(Bgxz.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Bgxz bgxz)
	{
		bgxz.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(bgxz);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Bgxz bgxz)
	{
		this.saveOrUpdateObject(bgxz);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Bgxz.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Bgxz bgxz = (Bgxz)this.getObjectById(Bgxz.class, id);
		bgxz.setDelFlag("1");
		this.saveObject(bgxz);
	}
	
	/**
	 * 获取未读公告

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findBgxzListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取未读公告列表分页
	 */
	public List<Bgxz> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Bgxz> list =sqlMapClientTemplate.queryForList("findBgxzList",paraMap,s,l);
		return list;
	}
}
