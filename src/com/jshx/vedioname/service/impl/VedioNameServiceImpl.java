package com.jshx.vedioname.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.company.entity.Company;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.vedioname.dao.VedioNameDao;
import com.jshx.vedioname.entity.VedioName;
import com.jshx.vedioname.service.VedioNameService;

@Service("vedioNameService")
public class VedioNameServiceImpl extends BaseServiceImpl implements
		VedioNameService {
	
	/**
	 * Dao类
	 */
	@Resource
	private VedioNameDao vedioNameDao;

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            分页信息
	 * @param paraMap
	 *            查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap) {
		return vedioNameDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * 
	 * @param id
	 *            主键ID
	 * @return 主键ID对应的信息
	 */
	public VedioName getById(String id) {
		return vedioNameDao.getById(id);
	}

	/**
	 * 保存信息
	 * 
	 * @param model
	 *            信息
	 */
	@Transactional
	public void save(VedioName vedioName) {
		vedioNameDao.save(vedioName);
	}

	/**
	 * 修改信息
	 * 
	 * @param model
	 *            信息
	 */
	@Transactional
	public void update(VedioName vedioName) {
		vedioNameDao.update(vedioName);
	}

	/**
	 * 物理删除信息
	 * 
	 * @param ids
	 *            主键ID列表
	 */
	@Transactional
	public void delete(String[] ids) {
		List list = Arrays.asList(ids);

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects = vedioNameDao.findVedioName(paraMap);

		vedioNameDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * 
	 * @param ids
	 *            主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids) {
		String[] idArray = ids.split("\\|");
		if (null != idArray) {
			for (String id : idArray) {
				if (id != null && !id.trim().equals(""))
					vedioNameDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 分页查询设备列表中的企业
	 */
	@Override
	public Map<String, Object> getCompanyByPage(Map<String, Object> condition) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = vedioNameDao.getCompanyCount(condition);
		List<Company> companys = vedioNameDao.getCompanyByPage(condition);
		result.put("count", count);
		result.put("companys", companys);
		return result;
	}
}
