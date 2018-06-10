package com.jshx.wsgl.service.impl;

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
import com.jshx.module.infomation.entity.Dept;
import com.jshx.wsgl.dao.InstrumentsInfoDao;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.entity.LockUser;
import com.jshx.wsgl.service.InstrumentsInfoService;

@Service("instrumentsInfoService")
public class InstrumentsInfoServiceImpl extends BaseServiceImpl implements InstrumentsInfoService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("instrumentsInfoDao")
	private InstrumentsInfoDao instrumentsInfoDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return instrumentsInfoDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InstrumentsInfo getById(String id)
	{
		return instrumentsInfoDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(InstrumentsInfo instrumentsInfo)
	{
		instrumentsInfoDao.save(instrumentsInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(InstrumentsInfo instrumentsInfo)
	{
		instrumentsInfoDao.update(instrumentsInfo);
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
		List objects=instrumentsInfoDao.findInstrumentsInfo(paraMap);
		
		instrumentsInfoDao.removeAll(objects);
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
				    instrumentsInfoDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<InstrumentsInfo> findInstrumentsInfo(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.findInstrumentsInfo(paraMap);
	}

	@Transactional
	public void deleteInstrumentsInfoByMap(Map map) {
		// TODO Auto-generated method stub
		instrumentsInfoDao.deleteInstrumentsInfoByMap(map);
	}

	@Override
	public List<InstrumentsInfo> findInstrumentsInfos(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.findInstrumentsInfos(paraMap);
	}
	
	@Override
	public List<InstrumentsInfo> findInstrumentsInfoss(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.findInstrumentsInfoss(paraMap);
	}

	@Override
	public List<InstrumentsInfo> getInstrumentsInfoListByUserAndType(Map map,
			int start, int limit) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.getInstrumentsInfoListByUserAndType(map, start, limit);
	}

	@Override
	public int getInstrumentsInfoListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.getInstrumentsInfoListSizeByUserAndType(map);
	}

	@Override
	public int getMaxAjhNumByMap(Map map) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.getMaxAjhNumByMap(map);
	}

	@Transactional
	public void updateAllWsInfoByMap(Map map) {
		// TODO Auto-generated method stub
		instrumentsInfoDao.updateAllWsInfoByMap(map);
	}

	@Override
	public List<Dept> getAllDepartByMap(Map map) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.getAllDepartByMap(map);
	}

	@Override
	public List<User> getAllUsersByMap(Map map) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.getAllUsersByMap(map);
	}

	@Transactional
	public void deleteLockUser(Map map) {
		// TODO Auto-generated method stub
		instrumentsInfoDao.deleteLockUser(map);
	}

	@Transactional
	public void saveLockUser(LockUser lockUser) {
		// TODO Auto-generated method stub
		instrumentsInfoDao.saveLockUser(lockUser);
	}

	@Override
	public List<String> queryXwblUser(Map map) {
		// TODO Auto-generated method stub
		return instrumentsInfoDao.queryXwblUser(map);
	}
}
