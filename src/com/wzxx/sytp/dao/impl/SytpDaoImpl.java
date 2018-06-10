package com.wzxx.sytp.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.sytp.dao.SytpDao;
import com.wzxx.sytp.entity.Sytp;

@Component("sytpDao")
public class SytpDaoImpl extends BaseDaoImpl implements SytpDao
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
		return this.findPageByHqlId("findSytpByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSytp(Map<String, Object> paraMap){
		return this.findListByHqlId("findSytpByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Sytp getById(String id)
	{
		return (Sytp)this.getObjectById(Sytp.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Sytp tzgg)
	{
		tzgg.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(tzgg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Sytp tzgg)
	{
		this.saveOrUpdateObject(tzgg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Sytp.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Sytp tzgg = (Sytp)this.getObjectById(Sytp.class, id);
		tzgg.setDelFlag("1");
		this.saveObject(tzgg);
	}
	
	/**
	 * 获取未读公告

	 */
	public List<Sytp> findAllInfos(Map<String, Object> paraMap)
  	{
		return this.findListByHqlId("findSytpByMap", paraMap);
  	}
	
}
