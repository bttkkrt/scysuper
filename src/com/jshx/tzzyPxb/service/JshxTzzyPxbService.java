package com.jshx.tzzyPxb.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;

public interface JshxTzzyPxbService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	public List<JshxTzzyPxb> findJshxTzzyPxb(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxTzzyPxb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void save(JshxTzzyPxb model);

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void update(JshxTzzyPxb model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-17
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-17
	 */
	public void deleteWithFlag(String ids);
	
	public List<JshxTzzyPxb> getJshxTzzyPxbListByMap(Map map,int start, int limit);
	
	public int getJshxTzzyPxbListSizeByMap(Map map);
	public void deleteByQyid(Map map);

	public void saveTzzyPxbs(List<JshxTzzyPxb> tzzyPxbs);//保存批量导入的数据
}
