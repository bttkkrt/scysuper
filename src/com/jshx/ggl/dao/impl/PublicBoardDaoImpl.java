package com.jshx.ggl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ggl.entity.PublicBoard;
import com.jshx.ggl.dao.PublicBoardDao;

@Component("publicBoardDao")
public class PublicBoardDaoImpl extends BaseDaoImpl implements PublicBoardDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPublicBoardByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPublicBoard(Map<String, Object> paraMap){
		return this.findListByHqlId("findPublicBoardByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PublicBoard getById(String id)
	{
		return (PublicBoard)this.getObjectById(PublicBoard.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PublicBoard publicBoard)
	{
		publicBoard.setId(null);
		this.saveOrUpdateObject(publicBoard);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PublicBoard publicBoard)
	{
		this.saveOrUpdateObject(publicBoard);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PublicBoard.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PublicBoard publicBoard = (PublicBoard)this.getObjectById(PublicBoard.class, id);
		publicBoard.setDelFlag(1);
		this.saveObject(publicBoard);
	}
}
