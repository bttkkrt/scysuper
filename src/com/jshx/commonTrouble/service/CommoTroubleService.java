package com.jshx.commonTrouble.service;

import java.util.List;
import java.util.Map;

import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.commonTrouble.entity.DeptDataBean;
import com.jshx.commonTrouble.entity.KsjcBean;
import com.jshx.commonTrouble.entity.QyDataBean;
import com.jshx.commonTrouble.entity.XzzywhBean;
import com.jshx.commonTrouble.entity.ZfwsData;
import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;

public interface CommoTroubleService extends BaseService
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
	public CommoTrouble getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CommoTrouble model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CommoTrouble model);

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
	 * 添加 获取部门id列表
	 * lj 2013-06-26
	 */
	public List<String> getDeptListByMap(Map map);
	/**
	 * 查询企业是危化科或职业健康科的隐患信息 2013-08-05
	 */
	public List<String> getRidsByType(Map map);
	
	/**
	 * 一般安全隐患按企业类型统计 李军 2013-11-13
	 */
	public List<DeptDataBean> getDeptDataList(Map map);

	/**
	 * 一般安全隐患按企业类型统计 李军 2013-11-13
	 */
	public DeptDataBean getDeptDataBean(Map map);
	
	/**
	 * 乡镇检查职业危害企业统计
	 */
	public XzzywhBean getXzzywhDataBean(Map map);
	
	
	/**
	 * 按执法文书统计的数据 李军 2013-11-14
	 */
	public ZfwsData getZfwsData(Map map);
	
	/**
	 * 一般安全隐患按乡镇统计 李军 2013-11-14
	 */
	public List<DeptDataBean> getXzDataList(Map map);

	/**
	 * 一般安全隐患按乡镇统计 李军 2013-11-14
	 */
	public DeptDataBean getXzDataBean(Map map);
	
	/**
	 * 按企业统计分页 陆婷 2013-12-13
	 */
	public List<QyDataBean> getCommonTroubleQyListByMap(Map map, int start,int limit);
	/**
	 * 按企业统计总数 陆婷 2013-12-13
	 */
	public int getCommonTroubleQyListSizeByMap(Map map);
	
	/**
	 * 按企业统计导出 陆婷 2013-12-13
	 */
	public List<QyDataBean> getQyDataList(Map map);
	
	public List<Department> getAllDeptListByMap(Map map);
	/**
	 * 一般安全隐患按科室统计 李军 2013-11-14
	 */
	public List<DeptDataBean> getXzDataListByKeshi(Map map);
	
	/**
	 * 乡镇检查职业危害企业统计
	 */
	public List<XzzywhBean> getXzzywhDataList(Map map);
	
	public List<KsjcBean> getKsjcListList(Map map);
	
	public KsjcBean getKsjcBean(Map map);
	
	public List<KsjcBean> getXzjcListList(Map map);
	
	public KsjcBean getXzjcBean(Map map);

	/**
	 * @param map
	 * @return
	 */
	public List findTjcomm(Map<String, Object> map);
	public List findList(Map<String, Object> map);
}
