package com.jshx.aqscsgccglb.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscsgccglb.entity.Aqscsgccglb;
import com.jshx.aqsczfwsglb.entity.Aqsczfwsglb;


public interface AqscsgccglbDao extends BaseDao
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
	public List<Aqscsgccglb> findAqscsgccglb(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscsgccglb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscsgccglb model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscsgccglb model);

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
	 * 获取生产安全事故处理关联表信息
	 * 2013-11-12 陆婷
	 */
	public Aqscsgccglb getAqscsgccglbByMap(Map map);
}
