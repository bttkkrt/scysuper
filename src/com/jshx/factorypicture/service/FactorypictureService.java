package com.jshx.factorypicture.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.factorypicture.entity.Factorypicture;

public interface FactorypictureService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	public List<Factorypicture> findFactorypicture(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Factorypicture getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Factorypicture model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Factorypicture model);

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
}
