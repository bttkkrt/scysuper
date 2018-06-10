package com.jshx.zywsqk.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywsqk.entity.Zywsqk;
import com.jshx.zywsqk.entity.ZywsqkAll;

public interface ZywsqkService extends BaseService
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
	public Zywsqk getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsqk model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsqk model);

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
	
	public void deleteZywhglbByMap(Map map);
	
	/**
	 * 获取职业卫生统计数据
	 * 2013-12-24
	 * 陆婷
	 * @param map
	 */
	public List<ZywsqkAll> getZywsqkAllListByMap(Map map);
	
	/**
	 * 获取职业卫生统计合计数据
	 * 2013-12-24
	 * 陆婷
	 * @param map
	 */
	public ZywsqkAll getZywsqkAllByMap(Map map);
}
