package com.jshx.zazPxb.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zazPxb.entity.JshxPx;
import com.jshx.zazPxb.entity.JshxZazPxb;

public interface JshxZazPxbService extends BaseService
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
	
	public List<JshxZazPxb> findJshxZazPxb(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxZazPxb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void save(JshxZazPxb model);

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void update(JshxZazPxb model);

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
	
	public List<JshxZazPxb> getJshxZazPxbListByMap(Map map,int start, int limit);
	
	public int getJshxZazPxbListSizeByMap(Map map);
	
	public void deleteByQyid(Map map);
	/**
	 * 保存 批量导入的培训人员信息
	 */
	public void saveZazPxbs(List<JshxZazPxb> pxbs);
	
	public List<JshxPx> getJshxPxListByMap(Map map);
	
	public JshxPx getTotalJshxPxListByMap(Map map);
}
