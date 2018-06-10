package com.jshx.qyfjfl.service;

import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyfjfl.entity.EnterpriseGrade;

public interface EnterpriseGradeService extends BaseService
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
	public EnterpriseGrade getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EnterpriseGrade model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EnterpriseGrade model);

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
