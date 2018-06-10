package com.jshx.commonTrouble.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.commonTrouble.entity.DeptDataBean;
import com.jshx.commonTrouble.entity.KsjcBean;
import com.jshx.commonTrouble.entity.QyDataBean;
import com.jshx.commonTrouble.entity.XzzywhBean;
import com.jshx.commonTrouble.entity.ZfwsData;
import com.jshx.commonTrouble.dao.CommoTroubleDao;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.module.admin.entity.Department;

@Component("commoTroubleDao")
public class CommoTroubleDaoImpl extends BaseDaoImpl implements CommoTroubleDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCommoTroubleByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCommoTrouble(Map<String, Object> paraMap){
		return this.findListByHqlId("findCommoTroubleByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CommoTrouble getById(String id)
	{
		return (CommoTrouble)this.getObjectById(CommoTrouble.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CommoTrouble commoTrouble)
	{
		commoTrouble.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(commoTrouble);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CommoTrouble commoTrouble)
	{
		this.saveOrUpdateObject(commoTrouble);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CommoTrouble.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CommoTrouble commoTrouble = (CommoTrouble)this.getObjectById(CommoTrouble.class, id);
		commoTrouble.setDelFlag(1);
		this.saveObject(commoTrouble);
	}

	@Override
	public List<String> getDeptListByMap(Map map) {
		return sqlMapClientTemplate.queryForList("query_deptList_byMap",map);
	}

	@Override
	public List<String> getRidsByType(Map map) {
		return sqlMapClientTemplate.queryForList("query_rids_byType",map);
	}

	@Override
	public List<DeptDataBean> getDeptDataList(Map map) {
		return sqlMapClientTemplate.queryForList("query_comomnTrouble_byQylx",map);
	}

	@Override
	public DeptDataBean getDeptDataBean(Map map) {
		return (DeptDataBean)sqlMapClientTemplate.queryForObject("query_comomnTrouble_bean_byQylx",map);
	}

	@Override
	public ZfwsData getZfwsData(Map map) {
		return (ZfwsData)sqlMapClientTemplate.queryForObject("query_zfws_data",map);
	}

	@Override
	public DeptDataBean getXzDataBean(Map map) {
		return (DeptDataBean)sqlMapClientTemplate.queryForObject("query_xz_data_bean",map);
	}

	@Override
	public XzzywhBean getXzzywhDataBean(Map map) {
		return (XzzywhBean)sqlMapClientTemplate.queryForObject("query_xzzywh_data_bean",map);
	}
	
	
	@Override
	public List<DeptDataBean> getXzDataList(Map map) {
		return sqlMapClientTemplate.queryForList("query_xz_data_list",map);
	}

	@Override
	public List<XzzywhBean> getXzzywhDataList(Map map) {
		return sqlMapClientTemplate.queryForList("query_xzzywh_data_list",map);
	}
	
	
	@Override
	public List<QyDataBean> getCommonTroubleQyListByMap(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getCommonTroubleQyListByMap",map,s,l);
	}

	@Override
	public int getCommonTroubleQyListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getCommonTroubleQyListByMap",map);
		return list.size();
	}

	@Override
	public List<QyDataBean> getQyDataList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getCommonTroubleQyListByMap",map);
	}

	@Override
	public List<Department> getAllDeptListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getAllDeptListByMap",map);
	}

	@Override
	public List<DeptDataBean> getXzDataListByKeshi(Map map) {
		return sqlMapClientTemplate.queryForList("query_xz_data_list_keshi",map);
	}

	@Override
	public KsjcBean getKsjcBean(Map map) {
		// TODO Auto-generated method stub
		return (KsjcBean)sqlMapClientTemplate.queryForObject("getKsjcBean",map);
	}

	@Override
	public List<KsjcBean> getKsjcListList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getKsjcListList",map);
	}

	@Override
	public KsjcBean getXzjcBean(Map map) {
		// TODO Auto-generated method stub
		return (KsjcBean)sqlMapClientTemplate.queryForObject("getXzjcBean",map);
	}

	@Override
	public List<KsjcBean> getXzjcListList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getXzjcListList",map);
	}

	@Override
	public List findTjcomm(Map<String, Object> map) {
		  return sqlMapClientTemplate.queryForList("tjcomm",map); 
	}
}
