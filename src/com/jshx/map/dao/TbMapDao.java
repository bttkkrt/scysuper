package com.jshx.map.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.map.entity.TbMap;


public interface TbMapDao extends BaseDao
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
	public List findTbMap(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TbMap getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TbMap model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TbMap model);

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
	 * 查询map list数据
	 * lj 
	 * 2015-10-31
	 */
	public List<Map> queryMapListByMap(String sqlID,Map map);
	/**
	 * 获取Map 类型的详情数据
	 * lj 
	 * 2015-10-31
	 */
	public Map getMapDetailByMap(String sqlID,Map map);
}
