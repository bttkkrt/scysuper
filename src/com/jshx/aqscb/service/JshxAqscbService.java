package com.jshx.aqscb.service;

import java.util.List;
import java.util.Map;

import com.jshx.aqscb.entity.JshxAqscb;
import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;

public interface JshxAqscbService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxAqscb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxAqscb model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxAqscb model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteWithFlag(String ids);
	/**
	 *  获取最近一次填报的月份 李军 2013-08-28
	 */
	public String getLaterMonth(Map map);
	
	/**
	 * 获取关联id 
	 * 2013-11-8 陆婷
	 */
	public List<String> getAqscIdsByMap(Map map);
}
