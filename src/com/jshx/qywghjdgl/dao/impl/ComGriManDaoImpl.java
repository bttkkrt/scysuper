package com.jshx.qywghjdgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qywghjdgl.entity.ComGriMan;
import com.jshx.qywghjdgl.dao.ComGriManDao;

@Component("comGriManDao")
public class ComGriManDaoImpl extends BaseDaoImpl implements ComGriManDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findComGriManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findComGriMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findComGriManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ComGriMan getById(String id)
	{
		return (ComGriMan)this.getObjectById(ComGriMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ComGriMan comGriMan)
	{
		comGriMan.setId(null);
		this.saveOrUpdateObject(comGriMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ComGriMan comGriMan)
	{
		this.saveOrUpdateObject(comGriMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ComGriMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ComGriMan comGriMan = (ComGriMan)this.getObjectById(ComGriMan.class, id);
		comGriMan.setDelFlag(1);
		this.saveObject(comGriMan);
	}

	@Override
	public ComGriMan getComGriManByMap(Map map) {
		// TODO Auto-generated method stub
		List<ComGriMan> list = this.findListByHqlId("findComGriManByMaps", map);
		if(list.size() != 0)
		{
			return list.get(0);
		}
		else
		{
			return new ComGriMan();
		}
	}
}
