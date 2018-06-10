package com.jshx.ajxx.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.ajxx.dao.CaseInfoDao;
import com.jshx.ajxx.entity.CaseCl;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.entity.CaseZj;
import com.jshx.ajxx.service.CaseInfoService;

@Service("caseInfoService")
public class CaseInfoServiceImpl extends BaseServiceImpl implements CaseInfoService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("caseInfoDao")
	private CaseInfoDao caseInfoDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return caseInfoDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CaseInfo getById(String id)
	{
		return caseInfoDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CaseInfo caseInfo)
	{
		caseInfoDao.save(caseInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CaseInfo caseInfo)
	{
		caseInfoDao.update(caseInfo);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=caseInfoDao.findCaseInfo(paraMap);
		
		caseInfoDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    caseInfoDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public String getUserByRole(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.getUserByRole(map);
	}

	@Override
	public List<CaseInfo> getCaseInfoListByUserAndType(Map map,int start,int limit) {
		// TODO Auto-generated method stub
		return caseInfoDao.getCaseInfoListByUserAndType(map,start,limit);
	}

	@Override
	public int getCaseInfoListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.getCaseInfoListSizeByUserAndType(map);
	}

	@Override
	public int getMaxGlhNumByMap(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.getMaxGlhNumByMap(map);
	}

	@Override
	public List<User> queryCaseUserList(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.queryCaseUserList(map);
	}

	@Transactional
	public void deleteCaseCl(String[] ids) {
		// TODO Auto-generated method stub
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=caseInfoDao.getCaseClList(paraMap);
		
		caseInfoDao.removeAll(objects);
	}

	@Transactional
	public void deleteCaseClWithFlag(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    caseInfoDao.deleteCaseClWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteCaseZj(String[] ids) {
		// TODO Auto-generated method stub
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=caseInfoDao.getCaseZjList(paraMap);
		
		caseInfoDao.removeAll(objects);
	}

	@Transactional
	public void deleteCaseZjWithFlag(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    caseInfoDao.deleteCaseZjWithFlag(id);
			}
		}
	}

	@Override
	public Pagination findCaseClByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return caseInfoDao.findCaseClByPage(page, paraMap);
	}

	@Override
	public Pagination findCaseZjByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return caseInfoDao.findCaseZjByPage(page, paraMap);
	}

	@Override
	public List<CaseCl> getCaseClList(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.getCaseClList(map);
	}

	@Override
	public List<CaseZj> getCaseZjList(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.getCaseZjList(map);
	}

	@Transactional
	public void saveCaseCl(CaseCl caseCl) {
		// TODO Auto-generated method stub
		caseInfoDao.saveCaseCl(caseCl);
	}

	@Transactional
	public void saveCaseZj(CaseZj caseZj) {
		// TODO Auto-generated method stub
		caseInfoDao.saveCaseZj(caseZj);
	}

	@Transactional
	public void updateCaseCl(CaseCl model) {
		// TODO Auto-generated method stub
		caseInfoDao.updateCaseCl(model);
	}

	@Transactional
	public void updateCaseZj(CaseZj model) {
		// TODO Auto-generated method stub
		caseInfoDao.updateCaseZj(model);
	}

	@Override
	public CaseCl getCaseClById(String id) {
		// TODO Auto-generated method stub
		return caseInfoDao.getCaseClById(id);
	}

	@Override
	public CaseZj getCaseZjById(String id) {
		// TODO Auto-generated method stub
		return caseInfoDao.getCaseZjById(id);
	}

	@Override
	public String getGlhNumListByMap(Map map) {
		// TODO Auto-generated method stub
		return caseInfoDao.getGlhNumListByMap(map);
	}
}
