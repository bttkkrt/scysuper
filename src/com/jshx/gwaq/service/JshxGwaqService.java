package com.jshx.gwaq.service;

import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gwaq.entity.JshxGwaq;

public interface JshxGwaqService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public JshxGwaq getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	* author：陆婷
	 * 2013-08-20
	 */
	public void save(JshxGwaq model);

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void update(JshxGwaq model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-20
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-20
	 */
	public void deleteWithFlag(String ids);
}
