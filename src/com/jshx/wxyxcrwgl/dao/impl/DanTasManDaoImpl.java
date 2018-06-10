package com.jshx.wxyxcrwgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wxyxcrwgl.entity.DanTasMan;
import com.jshx.wxyxcrwgl.dao.DanTasManDao;

@Component("danTasManDao")
public class DanTasManDaoImpl extends BaseDaoImpl implements DanTasManDao
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
		return this.findPageByHqlId("findDanTasManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDanTasMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findDanTasManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DanTasMan getById(String id)
	{
		return (DanTasMan)this.getObjectById(DanTasMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DanTasMan danTasMan)
	{
		danTasMan.setId(null);
		this.saveOrUpdateObject(danTasMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DanTasMan danTasMan)
	{
		this.saveOrUpdateObject(danTasMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DanTasMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DanTasMan danTasMan = (DanTasMan)this.getObjectById(DanTasMan.class, id);
		danTasMan.setDelFlag(1);
		this.saveObject(danTasMan);
	}

	@Override
	public List<DanTasMan> getNoDealTasks(Map map) {
		return sqlMapClientTemplate.queryForList("get_noDealTasksByMap",map);
	}

	@Override
	public void updateTaskResult(Map map) {
		sqlMapClientTemplate.update("update_taskResult_byMap",map);
	}

	@Override
	public void saveDanTask(DanTasMan model) {
		// TODO Auto-generated method stub
		try {
			sqlMapClientTemplate.insert("saveDanTask",model);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
