package com.jshx.aqscxzcf.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscxzcf.entity.Aqscxzcf;

public interface AqscxzcfService extends BaseService
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
	public Aqscxzcf getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscxzcf model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscxzcf model);

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
	 * 删除关联表数据
	 */
	public void deleteAqscxzcfglbByMap(Map map);
	
	/**
	 * 获取安全生产行政处罚编号
	 * 陆婷
	 * 2013-12-19
	 */
	public List<String> getAqscxzcfIdsByMap(Map map);
}
