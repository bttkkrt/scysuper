package com.jshx.regulationsLevel.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.regulationsLevel.dao.RegulationsLevelDao;
import com.jshx.regulationsLevel.entity.RegulationsLevel;
import com.jshx.regulationsLevel.service.RegulationsLevelService;

@Service("regulationsLevelService")
public class RegulationsLevelServiceImpl extends BaseServiceImpl implements RegulationsLevelService
{
	/**
	 * Dao类
	 */
	@Resource
	private RegulationsLevelDao regulationsLevelDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return regulationsLevelDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RegulationsLevel getById(String id)
	{
		return regulationsLevelDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(RegulationsLevel regulationsLevel)
	{
		regulationsLevelDao.save(regulationsLevel);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(RegulationsLevel regulationsLevel)
	{
		regulationsLevelDao.update(regulationsLevel);
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
		List objects=regulationsLevelDao.findRegulationsLevel("findRegulationsLevelByMap",paraMap);
		
		regulationsLevelDao.removeAll(objects);
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
				    regulationsLevelDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 查询级别
	 */
	public List findRegulationsLevel(String sql,Map<String, Object> paraMap){
		return regulationsLevelDao.findRegulationsLevel(sql,paraMap);
	}
	/**
	 * 根据父节点查询
	 */
	public String findFullPathBySuperId(String parentId){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String str = "";
		paraMap.put("parentId", parentId);
		List list = regulationsLevelDao.findListByHqlId("findFullPathBySuperId", paraMap);
		if(list.size() > 0){
			RegulationsLevel regulationsLevel = (RegulationsLevel)list.get(0);
			str = regulationsLevel.getFullpath();
		}
		return str;
	}
	
	public String findName(String parentId){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String str = "";
		paraMap.put("parentId", parentId);
		List list = regulationsLevelDao.findListByHqlId("findFullPathBySuperId", paraMap);
		if(list.size() > 0){
			RegulationsLevel regulationsLevel = (RegulationsLevel)list.get(0);
			str = regulationsLevel.getLevelName();
		}
		return str;
	}
	
	public List findPageTree(Map<String, Object> paraMap){
		return regulationsLevelDao.findListBySqlId("findPageTree", paraMap);
	}
	//是否存在此种级别
	@Override
	public String checkExist(Map<String, String> paraMap) {			
		List list = null;
		list  = this.regulationsLevelDao.findListByHqlId("getChildRegulationsLevelNodesByParentId", paraMap);
		
		if((null!= list) && (list.size()>0 )){			
			return "1";
		}else{
			return "0";
		}
	}
	//父节点是否变成子节点的子节点
	@Override
	public List checkToBeChild(String parentId) {			
		List list = null;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("parentId", parentId);
		return this.regulationsLevelDao.findListByHqlId("findFullPathBySuperId", paraMap);
	}
	
	//是否存在此种类信息子节点
	@Override
	public String checkHasChild(Map<String, String> paraMap) {			
		List list = null;
		list  = this.regulationsLevelDao.findListByHqlId("getChildRegulationsLevelNodesByParentId", paraMap);
		
		if((null!= list) && (list.size()>0 )){			
			return "1";
		}else{
			return "0";
		}
	}
	@Override
	public int checkOfficesupply(String ids) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("ids", ids);
		List officesupplyList=regulationsLevelDao.findListBySqlId("mfSuppliesTypeDao_checkOfficesupply", params);
		return Integer.parseInt(officesupplyList.get(0).toString());
	}
}
