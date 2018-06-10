package com.jshx.echarts.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.echarts.entity.Echarts;
import com.jshx.yhb.entity.TjYhBean;

public interface EchartsService extends BaseService
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
	public Echarts getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Echarts model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Echarts model);

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
	 * 根据镇查询 企业隐患上报信息 
	 */
	public List<TjYhBean> getTjYhListFromQy(String sqlId,Map map);
}
