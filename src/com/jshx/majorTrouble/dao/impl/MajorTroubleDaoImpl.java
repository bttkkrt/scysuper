package com.jshx.majorTrouble.dao.impl;

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
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.majorTrouble.entity.MajorTrouble;
import com.jshx.majorTrouble.entity.QyData;
import com.jshx.majorTrouble.entity.TongJiData;
import com.jshx.majorTrouble.dao.MajorTroubleDao;

@Component("majorTroubleDao")
public class MajorTroubleDaoImpl extends BaseDaoImpl implements MajorTroubleDao
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
		return this.findPageByHqlId("findMajorTroubleByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMajorTrouble(Map<String, Object> paraMap){
		return this.findListByHqlId("findMajorTroubleByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MajorTrouble getById(String id)
	{
		return (MajorTrouble)this.getObjectById(MajorTrouble.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MajorTrouble majorTrouble)
	{
		majorTrouble.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(majorTrouble);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MajorTrouble majorTrouble)
	{
		this.saveOrUpdateObject(majorTrouble);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MajorTrouble.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MajorTrouble majorTrouble = (MajorTrouble)this.getObjectById(MajorTrouble.class, id);
		majorTrouble.setDelFlag(1);
		this.saveObject(majorTrouble);
	}

	@Override
	public List<String> getDeptListByMap(Map m) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_deptList_byMap_major",m);
	}

	@Override
	public List<String> getRidsByType(Map linkMap) {
		return sqlMapClientTemplate.queryForList("query_rids_byType_major",linkMap);
	}

	@Override
	public TongJiData getTongJiDataBean(Map map) {
		return (TongJiData)sqlMapClientTemplate.queryForObject("query_tongji_xz_bean",map);
	}

	@Override
	public List<TongJiData> getTongJiDataList(Map map) {
		return sqlMapClientTemplate.queryForList("query_tongji_xz_list",map);
	}

	@Override
	public TongJiData getTongJiDataBeanByQylx(Map map) {
		return (TongJiData)sqlMapClientTemplate.queryForObject("query_tongji_qylx_bean",map);
	}

	@Override
	public List<TongJiData> getTongJiDataListByQylx(Map map) {
		return sqlMapClientTemplate.queryForList("query_tongji_qylx_list",map);
	}

	@Override
	public List<QyData> getMajorTroubleQyListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getMajorTroubleQyListByMap",map,s,l);
	}

	@Override
	public int getMajorTroubleQyListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getMajorTroubleQyListByMap",map);
		return list.size();
	}

	@Override
	public List<QyData> getTongJiDataListByQy(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getMajorTroubleQyListByMap",map);
	}

	@Override
	public List<TongJiData> getTongJiDataListByKeShi(Map map) {

		return sqlMapClientTemplate.queryForList("query_tongji_xz_list_keshi",map);
	}

	@Override
	public List findTjmaj(Map<String, Object> map) {
		return   sqlMapClientTemplate.queryForList("tjmaj",map);
	}
}
