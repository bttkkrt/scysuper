package com.jshx.wxyxcjhgl.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wxyxcjhgl.entity.DanPlaMan;


public interface DanPlaManDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDanPlaMan(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DanPlaMan getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DanPlaMan model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DanPlaMan model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id);
	/**
	 * 获取所有计划任务用于定期生成任务
	 * lj
	 * 2015-10-15
	 */
	public List<DanPlaMan> getAllPlans(Map map);
	
	/**
	 * 获取任务用于定期生成任务
	 */
	public List<DanPlaMan> getDanPlans(Map map);
}
