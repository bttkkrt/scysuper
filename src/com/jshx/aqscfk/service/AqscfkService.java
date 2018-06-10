package com.jshx.aqscfk.service;

import java.util.List;
import java.util.Map;

import com.jshx.aqscfk.entity.Aqscfk;
import com.jshx.aqscfk.entity.Aqscxzcf;
import com.jshx.aqscfk.entity.Aqscxzcfglb;
import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;

public interface AqscfkService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	
	
	/**
	 * 信息查询
	 * author：杨鹏
	 * 2014-06-03
	 * @param sqlId SQL名称
	 * @param paraMap 查询条件
	 * @return 分页信息
	 */
	public List<Aqscxzcf> findListByPara(String sqlId, Map<String, Object> paraMap);
	
	public List<Aqscfk> findAqscfk(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscfk getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscfk model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscfk model);

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
	
	public Aqscxzcfglb getAqscxzcfglbByMap(Map map);
}
