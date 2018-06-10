package com.jshx.zzxtPxb.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;

public interface JshxZzxtPxbService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	public List<JshxZzxtPxb> findJshxZzxtPxb(Map<String, Object> paraMap);
	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxZzxtPxb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void save(JshxZzxtPxb model);

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void update(JshxZzxtPxb model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public void deleteWithFlag(String ids);
	
	public List<JshxZzxtPxb> getJshxZzxtPxbListByMap(Map map,int start, int limit);
	
	public int getJshxZzxtPxbListSizeByMap(Map map);
}
