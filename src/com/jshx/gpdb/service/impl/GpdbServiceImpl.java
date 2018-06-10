package com.jshx.gpdb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.areacodeRelation.entity.AreacodeRelation;
import com.jshx.company.dao.CompanyDao;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gpdb.dao.GpdbDao;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.gpdb.service.GpdbService;
import com.jshx.heflRelation.entity.HyflRelation;
import com.jshx.httpData.dao.HttpDataDao;
import com.jshx.httpData.service.HttpDataService;
//import com.jshx.httpData.util.ServiceTest;
import com.jshx.httpData.util.ServiceTest;

@Service("gpdbService")
public class GpdbServiceImpl extends BaseServiceImpl implements GpdbService
{
	/**
	 * Dao类
	 */
	@Resource
	private GpdbDao gpdbDao;
	@Resource
	private HttpDataService httpDataService;
	@Resource
	private HttpDataDao httpDataDao;
	@Resource
	private CompanyDao companyDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return gpdbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gpdb getById(String id)
	{
		return gpdbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Gpdb gpdb)
	{
		gpdbDao.save(gpdb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Gpdb gpdb)
	{
		gpdbDao.update(gpdb);
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
		List objects=gpdbDao.findGpdb(paraMap);
		
		gpdbDao.removeAll(objects);
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
				    gpdbDao.deleteWithFlag(id);
			}
		}
	}

	/**
	 * 企业信息数据上报
	 */
	@Override
	public List<CompanyBackUp> tranData(List<Map> coms, List<AreacodeRelation> list, List<CompanyBackUp> companys, List<HyflRelation> hyflRelations) {
		for (CompanyBackUp companyBackUp : companys) {
			for (Map map : coms) {
				String rowId = ServiceTest.convertObject(map.get("ID"));
				if(rowId.equals(companyBackUp.getId())){
					map = ServiceTest.getNewAreacode(map, list, hyflRelations);
					String param = ServiceTest.parseMapToStr(map);
					try {
						String guid = ServiceTest.doPostSoap1_1(param, "");
						if(!StringUtils.isEmpty(guid)){
							companyBackUp.setId(rowId);
							companyBackUp.setGuid(guid);
//					companyDao.updateCompanyBackUp(companyBackUp);
						}
						System.out.println("success:"+ ServiceTest.convertObject(map.get("QYMC")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return companys;
	}
	
	
}
