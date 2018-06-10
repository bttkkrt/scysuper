package com.jshx.company.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.company.dao.CompanyDao;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.entity.XfjCompany;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.module.admin.entity.User;

@Component("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl implements CompanyDao
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
		return this.findPageByHqlId("findCompanyByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Company> findCompany(Map<String, Object> paraMap){
		return this.findListByHqlId("findCompanyByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Company getById(String id)
	{
		return (Company)this.getObjectById(Company.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Company company)
	{
		company.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(company);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Company company)
	{
		this.saveOrUpdateObject(company);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Company.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Company company = (Company)this.getObjectById(Company.class, id);
		company.setDelFlag(1);
		this.saveObject(company);
	}

	@Override
	public boolean findCompanyBYLoginname( String username) {
		// TODO Auto-generated method stub
		return this.findListByHql("from Company c where c.delFlag=0 and c.loginname='"+username.trim()+"'").size()>0;
	}

	@Override
	public Company findCompanyByLoginID(String loginId) {
		// TODO Auto-generated method stub
		return (Company) this.findListByHql("from Company c where c.delFlag=0 and c.loginname='"+loginId+"'").get(0);
	}

	@Override
	public List<Company> findCompanyList(Map map) {
		// TODO Auto-generated method stub
//		return this.findListBySizeAndHqlId("findCompanyByMap", map, Integer.valueOf(map.get("start").toString()), Integer.valueOf(map.get("limit").toString()));
		return sqlMapClientTemplate.queryForList("findCompanyByMaps",map,Integer.valueOf(map.get("start").toString()), Integer.valueOf(map.get("limit").toString()));
	}
	
	@Override
	public List<Company> findCompList(Map map) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findCompanyByMap", map);
	}

	@Override
	public Object findCompanyTypeNameByKey(String qyzclx,String codeid) {
		// TODO Auto-generated method stub
		Object o = "";
		List list = this.findListByHql(" select itemText from CodeValue c where c.delFlag=0 and c.codeId like '"+codeid+"' and c.itemValue='"+qyzclx+"'");
		if(list!=null&&list.size()!=0){
			o= list.get(0);
		}
		return o;
	}
	
	@Override
	public Object findCompanyTypeNameByValue(String qyzclx,String codeid) {
		// TODO Auto-generated method stub
		Object o = "";
		List list = this.findListByHql(" select c.itemValue from CodeValue c where c.delFlag=0 and c.codeId='"+codeid+"' and c.itemText='"+qyzclx+"'");
		if(list!=null&&list.size()!=0){
			o= list.get(0);
		}
		return o;
	}

	public void saveCompanyBackUp(CompanyBackUp cbu) {
		// TODO Auto-generated method stub
		cbu.setId(null);
		this.saveOrUpdateObject(cbu);
	}

	@Override
	public CompanyBackUp getCompanyBackupById(Map map) {
		// TODO Auto-generated method stub
		CompanyBackUp companyBackUp = new CompanyBackUp();
		List<CompanyBackUp> list = this.findListByHqlId("getCompanyBackupById", map);
		if(list.size() != 0 )
		{
			companyBackUp = list.get(0);
		}
		return companyBackUp;
	}

	@Override
	public void updateCompanyBackUp(CompanyBackUp comBc) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(comBc);
	}

	@Override
	public List<CompanyBackUp> findCompanyBackUpList(Map map) {
		// TODO Auto-generated method stub
		//return this.findListByHql("from CompanyBackUp t where t.delFlag = 0");
		return this.findListByHqlId("findCompanyBackUpList", map);
	}

	@Override
	public CompanyBackUp getCompanyByLoginId(String loginId) {
		List list =this.findListByHql("from CompanyBackUp t where t.delFlag = 0 and t.loginname='"+loginId+"'");
		if(list.size()>0){
			return (CompanyBackUp) list.get(0);
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(user);
	}

	@Override
	public void deleteCompanyById(String ids) {
		try {
			sqlMapClientTemplate.update("delete_users_lj_byId",ids);//删除用户表 users 删除顺序 2
			sqlMapClientTemplate.delete("delete_companyback_lj_byId",ids);//删除企业审核后信息 companyback 删除顺序 3
			sqlMapClientTemplate.delete("delete_company_lj_byId",ids);//删除企业注册信息 company 删除顺序 4
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Override
	public String findMaxCode() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String num="";
		try {
			num = (String)this.sqlMapClientTemplate.queryForObject("findMaxCode", paraMap);
			//num = total+"";
//			List<BigDecimal> list = sqlMapClientTemplate.queryForObject("findMaxCode")
//					this.find("findMaxCode", paraMap);
			//List list = this.findListBySqlId("findMaxCode", paraMap);
//			if(null != list.get(0)){
//				num = list.get(0).intValue()+"";
//			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	public   int findTotal(Map map){
		int total = (Integer)this.sqlMapClientTemplate.queryForObject("findCompanyByMaps2_count", map);
		return total;
	}
//	@Override
//	public String finditemText() {
//		Map<String, Object> paraMap = new HashMap<String, Object>();
//		String num="";
//		try {
//			List<String> list = this.findListBySqlId("findMaxCode", paraMap);
//			//List list = this.findListBySqlId("findMaxCode", paraMap);
//			if(null != list.get(0) && list.get(0).length() > 0 ){
//				num = list.get(0).toString();
//			}
//		} catch (DataAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return num;
//	}
}
