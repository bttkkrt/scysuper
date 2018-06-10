package com.jshx.gridManager.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gridManager.entity.BaseBean;
import com.jshx.gridManager.entity.CompanyMapBean;
import com.jshx.gridManager.entity.GridManager;
import com.jshx.gridManager.entity.GridmanagerBean;


public interface GridManagerDao extends BaseDao
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
	public List findGridManager(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public GridManager getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(GridManager model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(GridManager model);

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
	 * 根据乡镇code查询乡镇人员的列表 
	 * @param deptCode
	 * @return
	 */

	public List<BaseBean> queryUsersByCode(Map map);
	/**
	 * 删除绑定着关联的信息
	 */
	public void deleteBindUser(Map map);
	/**
	 * 查询有证照企业地图显示信息 lj 2014-07-21
	 */
	public List<CompanyMapBean> getCompamyMapDotsByMap(Map map);
	/**
	 * 查询无证照企业地图显示信息 lj 2014-07-22
	 */
	public List<CompanyMapBean> getNoCompamyMapDotsByMap(Map map);
	
	/**
	 * 查询所有镇
	 */
	public List<BaseBean> getDeptListByMap(Map map);
	
	/**
	 * 查询某个镇对应分管领导
	 */
	public List<BaseBean> getFgldListByMap(Map map);
	
	/**
	 * 查询某个镇对应镇安监办主任
	 */
	public List<BaseBean> getZzrListByMap(Map map);
	
	/**
	 * 查询某个镇对应镇安监办主任绑定镇队长
	 */
	public List<BaseBean> getZddzListByMap(Map map);
	
	
	/**
	 * 查询某个镇安监办主任绑定镇安全责任人
	 */
	public List<BaseBean> getZaqzzrListByMap(Map map);
	
	/**
	 * 查询某个镇安全责任人绑定村安全责任人及有证无证企业数
	 */
	public List<GridmanagerBean> getCaqzzrListByMap(Map map);
	
	public Pagination findYzCompanyByPage(Pagination page, Map<String, Object> paraMap);
	
	public Pagination findWzCompanyByPage(Pagination page, Map<String, Object> paraMap);
	
}
