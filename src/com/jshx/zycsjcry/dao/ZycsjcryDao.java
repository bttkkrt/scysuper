package com.jshx.zycsjcry.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zycsjcry.entity.Hyfl;
import com.jshx.zycsjcry.entity.Qylb;
import com.jshx.zycsjcry.entity.Whys;
import com.jshx.zycsjcry.entity.Xzqy;
import com.jshx.zycsjcry.entity.Zclx;
import com.jshx.zycsjcry.entity.Zycsjcry;


public interface ZycsjcryDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZycsjcry(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zycsjcry getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zycsjcry model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zycsjcry model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id);
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlags(String id);
	
	/**
	 * 获取职业卫生接触人数总数
	 * author：　陆婷
	 * 2013-11-26
	 */
	public String getTotalCountByMap(Map map);
	
	/**
	 * 获取职业健康监护安监部门查看列表
	 * author：　陆婷
	 * 2013-11-26
	 */
	public List<Zycsjcry> getZycsjcryListByMap(Map map, int start,int limit);
	
	/**
	 * 获取职业健康监护安监部门查看列表总数
	 * author：　陆婷
	 * 2013-11-26
	 */
	public int getZycsjcryListSizeByMap(Map map);
	
	/**
	 * 获取按行业分类列表
	 * author：　陆婷
	 * 2013-11-29
	 */
	public List<Hyfl> getHyflListByMap(Map map);
	
	/**
	 * 获取按注册类型列表
	 * author：　陆婷
	 * 2013-11-29
	 */
	public List<Zclx> getZclxListByMap(Map map);
	
	/**
	 * 获取按行政区域列表
	 * author：　陆婷
	 * 2013-11-29
	 */
	public List<Xzqy> getXzqyListByMap(Map map);
	
	/**
	 * 获取按行政区域合计
	 * author：　陆婷
	 * 2013-11-29
	 */
	public Xzqy getXzqyByMap(Map map);
	
	/**
	 * 获取按危害因素列表
	 * author：　陆婷
	 * 2013-11-29
	 */
	public List<Whys> getWhysListByMap(Map map);
	
	/**
	 * 获取按危害因素合计
	 * author：　陆婷
	 * 2013-11-29
	 */
	public Whys getWhysByMap(Map map);
	
	/**
	 * 获取按企业列表
	 * author：　陆婷
	 * 2013-11-28
	 */
	public List<Qylb> getQylbListByMap(Map map);
}
