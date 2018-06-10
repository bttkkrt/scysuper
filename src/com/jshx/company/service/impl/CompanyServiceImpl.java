package com.jshx.company.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.company.dao.CompanyDao;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.entity.XfjCompany;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;

@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService
{
	/**
	 * Dao类
	 */
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
		return companyDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Company getById(String id)
	{
		return companyDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public String save(Company company)
	{
		companyDao.save(company);
		return company.getId();
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Company company)
	{
		companyDao.update(company);
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
		List objects=companyDao.findCompany(paraMap);
		
		companyDao.removeAll(objects);
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
				    companyDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public boolean findCompanyBYLoginname(String username) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyBYLoginname( username);
	}

	@Override
	public Company findCompanyByLoginID(String loginId) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyByLoginID( loginId);
	}

	@Override
	public List<Company> findCompanyList(Map map) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyList( map);
	}
	
	@Override
	public List<Company> findCompList(Map map) {
		// TODO Auto-generated method stub
		return companyDao.findCompList( map);
	}

	@Override
	public Object findCompanyTypeNameByKey(String qyzclx, String codeid) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyTypeNameByKey( qyzclx,codeid);
	}

	@Transactional
	public void saveCompanyBackUp(CompanyBackUp cbu) {
		// TODO Auto-generated method stub
		companyDao.saveCompanyBackUp(cbu);
	}

	@Override
	public CompanyBackUp getCompanyBackupById(Map map) {
		// TODO Auto-generated method stub
		return companyDao. getCompanyBackupById(map);
	}

	@Transactional
	public void updateCompanyBackUp(CompanyBackUp comBc) {
		// TODO Auto-generated method stub
		companyDao.updateCompanyBackUp(comBc);
	}

	@Transactional
	public List<CompanyBackUp> findCompanyBackUpList(Map map) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyBackUpList(map);
	}

	@Override
	public CompanyBackUp getCompanyByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return companyDao.getCompanyByLoginId( loginId);
	}

	@Transactional
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		companyDao.updateUser(user);
	}

	@Transactional
	public void deleteCompanyById(String ids) {
		 String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				for(String id : idArray)
				{
				    if(id!=null && !id.trim().equals(""))
				    	companyDao.deleteCompanyById(id);
				}
			}
		
	}

	@Override
	public Object findCompanyTypeNameByValue(String qyzclx, String string) {
		// TODO Auto-generated method stub
		return companyDao.findCompanyTypeNameByValue(qyzclx, string);
	}

	@Override
	public List<Company> findCompany(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return companyDao.findCompany(paraMap);
	}
	
	@Override
	public String findMaxCode(){
		return companyDao.findMaxCode();
	}
	public int findTotal(Map map){
		return companyDao.findTotal(map);
	}
}
