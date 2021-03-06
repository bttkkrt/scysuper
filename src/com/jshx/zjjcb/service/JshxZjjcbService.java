package com.jshx.zjjcb.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zjjcb.entity.JshxZjjcb;

public interface JshxZjjcbService extends BaseService
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
	public JshxZjjcb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxZjjcb model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxZjjcb model);

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
	 * 根据月份获取专家检查记录
	 * 2013-12-5
	 * 陆婷
	 */
	public JshxZjjcb getJshxZjjcbByMap(Map map);
	
	/**
	 * 获取企业列表
	 * 2013-12-6
	 * 陆婷
	 */
	public List<JshxZjjcb> getCompanyListByMap(Map map);
}
