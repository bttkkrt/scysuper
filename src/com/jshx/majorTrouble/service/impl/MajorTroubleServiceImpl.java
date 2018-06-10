package com.jshx.majorTrouble.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.majorTrouble.dao.MajorTroubleDao;
import com.jshx.majorTrouble.entity.MajorTrouble;
import com.jshx.majorTrouble.entity.QyData;
import com.jshx.majorTrouble.entity.TongJiData;
import com.jshx.majorTrouble.service.MajorTroubleService;

@Service("majorTroubleService")
public class MajorTroubleServiceImpl extends BaseServiceImpl implements MajorTroubleService
{
	/**
	 * Dao类
	 */
	@Resource
	private MajorTroubleDao majorTroubleDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return majorTroubleDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MajorTrouble getById(String id)
	{
		return majorTroubleDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(MajorTrouble majorTrouble)
	{
		majorTroubleDao.save(majorTrouble);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(MajorTrouble majorTrouble)
	{
		majorTroubleDao.update(majorTrouble);
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
		List objects=majorTroubleDao.findMajorTrouble(paraMap);
		
		majorTroubleDao.removeAll(objects);
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
				    majorTroubleDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<String> getDeptListByMap(Map m) {
		// TODO Auto-generated method stub
		return majorTroubleDao.getDeptListByMap(m);
	}

	@Override
	public List<String> getRidsByType(Map linkMap) {
		// TODO Auto-generated method stub
		return majorTroubleDao.getRidsByType(linkMap);
	}

	@Override
	public TongJiData getTongJiDataBean(Map map) {
		return majorTroubleDao.getTongJiDataBean(map);
	}

	@Override
	public List<TongJiData> getTongJiDataList(Map map) {
		return majorTroubleDao.getTongJiDataList(map);
	}

	@Override
	public TongJiData getTongJiDataBeanByQylx(Map map) {
		return majorTroubleDao.getTongJiDataBeanByQylx(map);
	}

	@Override
	public List<TongJiData> getTongJiDataListByQylx(Map map) {
		return majorTroubleDao.getTongJiDataListByQylx(map);
	}

	@Override
	public List<QyData> getMajorTroubleQyListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		return majorTroubleDao.getMajorTroubleQyListByMap(map, start, limit);
	}

	@Override
	public int getMajorTroubleQyListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return majorTroubleDao.getMajorTroubleQyListSizeByMap(map);
	}

	@Override
	public List<QyData> getTongJiDataListByQy(Map map) {
		// TODO Auto-generated method stub
		return majorTroubleDao.getTongJiDataListByQy(map);
	}

	@Override
	public List<TongJiData> getTongJiDataListByKeShi(Map map) {
		return majorTroubleDao.getTongJiDataListByKeShi(map);
	}
	
	@Override
	public List findTjmaj(Map<String, Object> map) {
		return majorTroubleDao.findTjmaj(map);
	}
	@Override
	public List findList(Map<String, Object> paraMap) {
		return majorTroubleDao.findListByHqlId("findMajorTroubleByMap", paraMap);
	}
	
}
