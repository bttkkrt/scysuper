package com.jshx.zzbw.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zzbw.entity.JshxZzbw;


public interface JshxZzbwDao extends BaseDao
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
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public List<JshxZzbw> findJshxZzbw(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public JshxZzbw getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void save(JshxZzbw model);

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void update(JshxZzbw model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-20
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-20
	 */
	public void deleteWithFlag(String id);
}
