package com.jshx.company.service;

import java.util.List;
import java.util.Map;

import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.entity.XfjCompany;
import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;

public interface CompanyService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	public List<Company> findCompany(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Company getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public String save(Company model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Company model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteWithFlag(String ids);

	public boolean findCompanyBYLoginname(String username);

	public Company findCompanyByLoginID(String loginId);

	public List<Company> findCompanyList(Map map);
	
	public List<Company> findCompList(Map map);

	public Object findCompanyTypeNameByKey(String qyzclx, String string);
	
	public Object findCompanyTypeNameByValue(String qyzclx, String string);
	
	public void saveCompanyBackUp(CompanyBackUp cbu);

	public CompanyBackUp getCompanyBackupById(Map map);

	public void updateCompanyBackUp(CompanyBackUp comBc);

	public List<CompanyBackUp> findCompanyBackUpList(Map map);

	public CompanyBackUp getCompanyByLoginId(String loginId);
	
	public void updateUser(User user);
	
	/**
	 * 删除企业注册信息 李军 2013-09-09 
	 */
	public void deleteCompanyById(String ids);
	
	public String findMaxCode();
	public int findTotal(Map map);
	
}
