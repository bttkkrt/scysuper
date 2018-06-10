package com.jshx.majorTrouble.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.majorTrouble.entity.MajorTrouble;
import com.jshx.majorTrouble.entity.QyData;
import com.jshx.majorTrouble.entity.TongJiData;


public interface MajorTroubleDao extends BaseDao
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
	public List findMajorTrouble(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MajorTrouble getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MajorTrouble model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MajorTrouble model);

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
	 * 获取本部门下的企业隐患信息 2013-08-06
	 * @param m
	 * @return
	 */
	
	public List<String> getDeptListByMap(Map m);
	/**
	 * 查询隶属危化科 职业健康科的企业隐患信息 2013-08-06
	 * @param linkMap
	 * @return
	 */

	public List<String> getRidsByType(Map linkMap);
	/**
	 * 按乡镇统计数据 列表 李军 2013-11-14
	 */
	public List<TongJiData> getTongJiDataList(Map map);
	/**
	 * 按乡镇统计数据 对象 李军 2013-11-14
	 */
	public TongJiData getTongJiDataBean(Map map);
	/**
	 * 按企业类型统计数据 列表 李军 2013-11-15
	 */
	public List<TongJiData> getTongJiDataListByQylx(Map map);
	/**
	 * 按企业类型统计数据 对象 李军 2013-11-15
	 */
	public TongJiData getTongJiDataBeanByQylx(Map map);
	
	/**
	 * 按企业统计分页 陆婷 2013-12-13
	 */
	public List<QyData> getMajorTroubleQyListByMap(Map map, int start,int limit);
	/**
	 * 按企业统计总数 陆婷 2013-12-13
	 */
	public int getMajorTroubleQyListSizeByMap(Map map);
	
	/**
	 * 按企业统计导出 陆婷 2013-12-13
	 */
	public List<QyData> getTongJiDataListByQy(Map map);
	/**
	 * 按科室统计数据 列表 李军  2014-06-03
	 */
	public List<TongJiData> getTongJiDataListByKeShi(Map map);

	/**
	 * @param map
	 * @return
	 */
	public List findTjmaj(Map<String, Object> map);
}
