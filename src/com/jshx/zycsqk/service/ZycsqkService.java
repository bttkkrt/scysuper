package com.jshx.zycsqk.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zycsqk.entity.Zybwhda;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.entity.Zywhys;

public interface ZycsqkService extends BaseService
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
	public Zycsqk getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zycsqk model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zycsqk model);

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
	 * 获取职业卫生因素
	 * author : 陆婷
	 * 2013-11-21
	 */
	public Zywhys getZywhysByMap(Map map);
	
	/**
	 * 获取职业卫生场所总数
	 * author：　陆婷
	 * 2013-11-26
	 */
	public String getTotalCountByMap(Map map);
	
	/**
	 * 获取职业病危害档案列表
	 * author：　陆婷
	 * 2013-11-27
	 */
	public List<Zybwhda> getZybwhdaListByMap(Map map, int start,int limit);
	
	/**
	 * 获取职业病危害档案列表总数
	 * author：　陆婷
	 * 2013-11-27
	 */
	public int getZybwhdaListSizeByMap(Map map);
	
	/**
	 * 获取职业病危害档案导出列表
	 * author：　陆婷
	 * 2013-12-3
	 */
	public List<Zybwhda> getZybwhdaListExportByMap(Map map);
}
