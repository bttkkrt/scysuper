package com.jshx.vedioname.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.company.entity.Company;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.vedioname.dao.VedioNameDao;
import com.jshx.vedioname.entity.VedioName;

@Component("vedioNameDao")
public class VedioNameDaoImpl extends BaseDaoImpl implements VedioNameDao {
	
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
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
		return this.findPageByHqlId("findVedioNameByMap", paraMap, page);
	}

	/**
	 * 查询所有记录
	 * 
	 * @param page
	 *            分页信息
	 * @param paraMap
	 *            查询条件信息
	 * @return 分页信息
	 */
	public List findVedioName(Map<String, Object> paraMap) {
		return this.findListByHqlId("findVedioNameByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * 
	 * @param id
	 *            主键ID
	 * @return 主键ID对应的信息
	 */
	public VedioName getById(String id) {
		return (VedioName) this.getObjectById(VedioName.class, id);
	}

	/**
	 * 保存信息
	 * 
	 * @param model
	 *            信息
	 */
	public void save(VedioName vedioName) {
		vedioName.setId(null);
		// 此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(vedioName);
	}

	/**
	 * 修改信息
	 * 
	 * @param model
	 *            信息
	 */
	public void update(VedioName vedioName) {
		this.saveOrUpdateObject(vedioName);
	}

	/**
	 * 物理删除信息
	 * 
	 * @param ids
	 *            主键ID
	 */
	public void delete(String id) {
		this.removeObjectById(VedioName.class, id);
	}

	/**
	 * 逻辑删除信息
	 * 
	 * @param ids
	 *            主键ID
	 */
	public void deleteWithFlag(String id) {
		VedioName vedioName = (VedioName) this.getObjectById(VedioName.class, id);
		vedioName.setDelFlag(1);
		this.saveObject(vedioName);
	}
	
	/**
	 * 查询设备列表中的企业数量
	 */
	@Override
	public int getCompanyCount(Map<String, Object> condition) {
		int count = (Integer) sqlMapClientTemplate.queryForObject("getCompanyCount", condition);
		return count;
	}
	
	/**
	 * 分页查询设备列表中的企业
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyByPage(Map<String, Object> condition) {
		List<Company> companys = (List<Company>) 
				sqlMapClientTemplate.queryForList("getCompanyByPage", condition);
		return companys;
	}
	
}
