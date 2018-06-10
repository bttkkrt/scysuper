package com.jshx.ajxx.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.ajxx.dao.CaseInfoDao;
import com.jshx.ajxx.entity.CaseCl;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.entity.CaseZj;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;

@Component("caseInfoDao")
public class CaseInfoDaoImpl extends BaseDaoImpl implements CaseInfoDao
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
		return this.findPageByHqlId("findCaseInfoByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCaseInfo(Map<String, Object> paraMap){
		return this.findListByHqlId("findCaseInfoByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CaseInfo getById(String id)
	{
		return (CaseInfo)this.getObjectById(CaseInfo.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CaseInfo caseInfo)
	{
		caseInfo.setId(null);
		this.saveOrUpdateObject(caseInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CaseInfo caseInfo)
	{
		this.saveOrUpdateObject(caseInfo);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CaseInfo.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CaseInfo caseInfo = (CaseInfo)this.getObjectById(CaseInfo.class, id);
		caseInfo.setDelFlag(1);
		this.saveObject(caseInfo);
	}

	@Override
	public String getUserByRole(Map map) {
		// TODO Auto-generated method stub
		return (String)sqlMapClientTemplate.queryForObject("getUserByRole",map);
	}

	@Override
	public List<CaseInfo> getCaseInfoListByUserAndType(Map map,int start,int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getCaseInfoListByUserAndType",map,s,l);
	}

	@Override
	public int getCaseInfoListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getCaseInfoListSizeByUserAndType",map);
	}

	@Override
	public int getMaxGlhNumByMap(Map map) {
		// TODO Auto-generated method stub
		String ss = "";
		ss = (String)sqlMapClientTemplate.queryForObject("getMaxGlhNumByMap",map);
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
	public List<User> queryCaseUserList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("queryCaseUserList",map);
	}

	@Override
	public void deleteCaseCl(String id) {
		// TODO Auto-generated method stub
		this.removeObjectById(CaseCl.class, id);
	}

	@Override
	public void deleteCaseClWithFlag(String id) {
		// TODO Auto-generated method stub
		CaseCl caseInfo = (CaseCl)this.getObjectById(CaseCl.class, id);
		caseInfo.setDelFlag(1);
		this.saveObject(caseInfo);
	}

	@Override
	public void deleteCaseZj(String id) {
		// TODO Auto-generated method stub
		this.removeObjectById(CaseZj.class, id);
	}

	@Override
	public void deleteCaseZjWithFlag(String id) {
		// TODO Auto-generated method stub
		CaseZj caseInfo = (CaseZj)this.getObjectById(CaseZj.class, id);
		caseInfo.setDelFlag(1);
		this.saveObject(caseInfo);
	}

	@Override
	public Pagination findCaseClByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findPageByHqlId("findCaseClByMap", paraMap, page);
	}

	@Override
	public Pagination findCaseZjByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findPageByHqlId("findCaseZjByMap", paraMap, page);
	}

	@Override
	public List<CaseCl> getCaseClList(Map map) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findCaseClByMaps", map);
	}

	@Override
	public List<CaseZj> getCaseZjList(Map map) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findCaseZjByMaps", map);
	}

	@Override
	public void saveCaseCl(CaseCl caseCl) {
		// TODO Auto-generated method stub
		caseCl.setId(null);
		this.saveOrUpdateObject(caseCl);
	}

	@Override
	public void saveCaseZj(CaseZj caseZj) {
		// TODO Auto-generated method stub
		caseZj.setId(null);
		this.saveOrUpdateObject(caseZj);
	}

	@Override
	public void updateCaseCl(CaseCl model) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(model);
	}

	@Override
	public void updateCaseZj(CaseZj model) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(model);
	}

	@Override
	public CaseCl getCaseClById(String id) {
		// TODO Auto-generated method stub
		return (CaseCl)this.getObjectById(CaseCl.class, id);
	}

	@Override
	public CaseZj getCaseZjById(String id) {
		// TODO Auto-generated method stub
		return (CaseZj)this.getObjectById(CaseZj.class, id);
	}

	@Override
	public String getGlhNumListByMap(Map map) {
		// TODO Auto-generated method stub
		String s = "";
		List<String> list = sqlMapClientTemplate.queryForList("getGlhNumListByMap",map);
		for(String ss:list)
		{
			if(ss != null)
			{
				s += ss + ",";
			}
		}
		if(s.length() != 0)
		{
			s = s.substring(0,s.length()-1);
		}
		return s;
	}

}
