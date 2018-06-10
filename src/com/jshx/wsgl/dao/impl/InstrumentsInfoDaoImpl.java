package com.jshx.wsgl.dao.impl;

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
import com.jshx.module.admin.entity.User;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.entity.LockUser;
import com.jshx.wsgl.dao.InstrumentsInfoDao;

@Component("instrumentsInfoDao")
public class InstrumentsInfoDaoImpl extends BaseDaoImpl implements InstrumentsInfoDao
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
		return this.findPageByHqlId("findInstrumentsInfoByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<InstrumentsInfo> findInstrumentsInfo(Map<String, Object> paraMap){
		return this.findListByHqlId("findInstrumentsInfoByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InstrumentsInfo getById(String id)
	{
		return (InstrumentsInfo)this.getObjectById(InstrumentsInfo.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InstrumentsInfo instrumentsInfo)
	{
		instrumentsInfo.setId(null);
		this.saveOrUpdateObject(instrumentsInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InstrumentsInfo instrumentsInfo)
	{
		this.saveOrUpdateObject(instrumentsInfo);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InstrumentsInfo.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InstrumentsInfo instrumentsInfo = (InstrumentsInfo)this.getObjectById(InstrumentsInfo.class, id);
		instrumentsInfo.setDelFlag(1);
		this.saveObject(instrumentsInfo);
	}

	@Override
	public void deleteInstrumentsInfoByMap(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.update("deleteInstrumentsInfoByMap",map);
	}

	@Override
	public List<InstrumentsInfo> findInstrumentsInfos(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findInstrumentsInfoByMaps", paraMap);
	}

	@Override
	public List<InstrumentsInfo> findInstrumentsInfoss(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findInstrumentsInfoByMapss", paraMap);
	}

	@Override
	public List<InstrumentsInfo> getInstrumentsInfoListByUserAndType(Map map,
			int start, int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getInstrumentsInfoListByUserAndType",map,s,l);
	}

	@Override
	public int getInstrumentsInfoListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getInstrumentsInfoListSizeByUserAndType",map);
	}

	@Override
	public int getMaxAjhNumByMap(Map map) {
		// TODO Auto-generated method stub
		String ss = (String)sqlMapClientTemplate.queryForObject("getMaxAjhNumByMap",map);
		if(ss != null && !"".equals(ss))
		{
			return Integer.parseInt(ss);
		}
		else
		{
			return 0;
		}
	}

	@Override
	public void updateAllWsInfoByMap(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.update("updateAllWsInfoByMap",map);
	}

	@Override
	public List<Dept> getAllDepartByMap(Map map) {
		// TODO Auto-generated method stub
		return this.sqlMapClientTemplate.queryForList("getAllAjDepart", map);
	}

	@Override
	public List<User> getAllUsersByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getAllAjUser", map);
	}

	@Override
	public void deleteLockUser(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("deleteLockUser",map);
	}

	@Override
	public void saveLockUser(LockUser lockUser) {
		// TODO Auto-generated method stub
		lockUser.setId(null);
		this.saveOrUpdateObject(lockUser);
	}

	@Override
	public List<String> queryXwblUser(Map map) {
		// TODO Auto-generated method stub
		return this.sqlMapClientTemplate.queryForList("queryXwblUser", map);
	}
}
