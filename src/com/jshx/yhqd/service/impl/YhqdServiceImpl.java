package com.jshx.yhqd.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.checkBasic.dao.CheckBasicDao;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.company.dao.CompanyDao;
import com.jshx.company.entity.Company;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.User;
import com.jshx.yhqd.dao.YhqdDao;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;

@Service("yhqdService")
public class YhqdServiceImpl extends BaseServiceImpl implements YhqdService
{
	/**
	 * Dao类
	 */
	@Resource
	private YhqdDao yhqdDao;
	
	@Resource
	private CheckBasicDao checkBasicDao;
	@Resource
	private UserDAO userDao;
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
		return yhqdDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Yhqd getById(String id)
	{
		return yhqdDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Yhqd yhqd)
	{
		yhqdDao.save(yhqd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Yhqd yhqd)
	{
		yhqdDao.update(yhqd);
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
		List objects=yhqdDao.findYhqd(paraMap);
		
		yhqdDao.removeAll(objects);
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
				    yhqdDao.deleteWithFlag(id);
			}
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<String> nextPerson(  String workFlowModelId,
			String deptCode,String flag) {
		Map <String,Object>paraMap = new HashMap<String,Object>();
		List<String> users  = new ArrayList<String>();
		Yhqd ttyhqd = (Yhqd) yhqdDao.getObjectByProperty(Yhqd.class, "id", workFlowModelId);
		if(flag.equalsIgnoreCase("zhenggai")){
			CheckBasic checkBasic = checkBasicDao.getById(ttyhqd.getBasic().getId());
			Company company = companyDao.getById(checkBasic.getCompany().getId());
			User user = (User)this.userDao.getObjectByProperty(User.class, "loginId", company.getLoginname());
			users.add(user.getId());
		}else if(flag.equalsIgnoreCase("yanshou")){
			users=Arrays.asList(ttyhqd.getJgzrrIds().split("\\|"));
		}
		return users;
	}
	 
}
