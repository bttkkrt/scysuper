package com.jshx.http.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.factorypicture.entity.Factorypicture;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.http.bean.CommonTroubleBean;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.KeyBean;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.PhotoBean;
import com.jshx.http.dao.HttpDao;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.qyjbxx.dao.EntBaseInfoDao;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.xkzsb.entity.JshxXkzsb;
import com.jshx.xrcPxb.entity.JshxXrcPxb;
import com.jshx.zazPxb.entity.JshxZazPxb;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;
@Service("httpService")
public class HttpServiceImpl implements HttpService {
	@Resource
	private HttpDao httpDao;
	@Resource
	private UserDAO userDao;
	@Resource
	private EntBaseInfoDao entBaseInfoDao;
	@Override
	public List<MyUserBean> getUserBeanByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getUserBeanByMap(map);
	}
	

	@Override
	public KeyBean getVersionNumberByForm(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getVersionNumberByForm(map);
	}


	@Override
	public DataBean getPhotoListByType(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getPhotoListByType(map);
	}


	@Override
	public List<DataBean> getRoleByUserId(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getRoleByUserId(map);
	}
	
	/**
	 * 查询List<Map<String,Object>>(通用)
	 * 费谦
	 * 2014-11-6
	 */
	public List<Map<String,Object>> findListDataByMap(Map<String, Object> paraMap){
		return httpDao.findListDataByMap(paraMap);
	}

	/**
	 * update(通用)
	 * 费谦
	 * 2014-11-6
	 */
	@Transactional
	public int updateByMap(Map<String, Object> paraMap){
		return httpDao.updateByMap(paraMap);
		
	}
	
		@Override
	public List<Map> getListByMap(Map map) {
		return httpDao.getListByMap(map);
	}

	@Override
	public int getListCountbyMap(Map map) {
		return httpDao.getListCountbyMap(map);
	}

	@Override
	public Map getMapByMap(Map map) {
		return httpDao.getMapByMap(map);
	}

	@Transactional
	public void updateMapByMap(Map map) {
		  httpDao.updateMapByMap(map);
	}
	
	/**
	 * 判断用户userId是否具有角色roleCode
	 * fq 2015-10-24
	 */
	public boolean judgeRoleCode(String userId,String roleCode){
		boolean b=false;
		User user=userDao.findUserById(userId);
		if(user!=null){
		List<UserRight> urs=(List<UserRight>) user.getUserRoles();
		for(UserRight ur:urs){
			if(roleCode.equals(ur.getRole().getRoleCode())){
				b=true;
			}
		}
		}
		return b;
	}
	

	public List<Map<String,String>> findcheckName(Map<String, String> paraMap){
		return httpDao.findcheckName(paraMap);
	}

	/**
	 * 中队队员和中队队长查看监管网格下的企业信息
	 * 费谦
	 * 2016-1-25
	 */
	public Map<String, Object> addParamByRole(Map<String, Object> paraMap,String userId,String deptCode){
		if(this.judgeRoleCode(userId, "A12")){//中队队员
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
			paraMapEnt.put("userId",userId);
			List<Map<String, Object>> ents= this.findListDataByMap(paraMapEnt);
			String companmyIds="";
			for(int i=0;i< ents.size();i++){
				companmyIds+=ents.get(i).get("row_id")+",";
			}
			if("".equals(companmyIds)){
				companmyIds="0";
			}
			paraMap.put("companmyIds", companmyIds);
		}
		if(this.judgeRoleCode(userId, "A11")){//中队长
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
			paraMapEnt.put("deptCode",deptCode);
			List<Map<String, Object>> ents= this.findListDataByMap(paraMapEnt);
			String companmyIds="";
			for(int i=0;i< ents.size();i++){
				companmyIds+=ents.get(i).get("row_id")+",";
			}
			if("".equals(companmyIds)){
				companmyIds="0";
			}
			paraMap.put("companmyIds", companmyIds);
		}
		if(this.judgeRoleCode(userId, "A23")){//企业
			Map map = new HashMap();
			map.put("loginId",userDao.findUserById(userId).getLoginId());
			paraMap.put("companyId", entBaseInfoDao.findEntBaseInfoByMap(map).getId());
		}
		return paraMap;
	}


	@Override
	public List<SecProFeeAcc> getSecProFeeAccListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getSecProFeeAccListByMap(map);
	}
	
	
	
	
	
	
	
	@Transactional
	public void savePhotoInfo(PhotoBean bean) {
		// TODO Auto-generated method stub
		String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
		bean.setId(uuid);
		httpDao.savePhotoInfo(bean);
	}


	@Override
	public List<DataBean> getTownList(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getTownList(map);
	}
	@Override
	public List<DataBean> getCountyList(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getCountyList(map);
	}

	@Override
	public List<DataBean> getWSList(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getWSList(map);
	}

	@Override
	public List<CommonTroubleBean> getCommonTroubleList(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getCommonTroubleList(map);
	}

	@Override
	public int getCommonTroubleListCount(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getCommonTroubleListCount(map);
	}

	@Override
	public List<DataBean> getDeptList(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getDeptList(map);
	}

	@Override
	public List<CommonTroubleBean> getMajorTroubleList(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getMajorTroubleList(map);
	}
	
	@Override
	public List<CommonTroubleBean> getAllMajorTrouble(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getAllMajorTrouble(map);
	}
	
	@Override
	public int getAllMajorTroubleCount(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getAllMajorTroubleCount(map);
	}

	@Override
	public int getMajorTroubleListCount(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getMajorTroubleListCount(map);
	}


	@Override
	public List<String> getAllDeptIdsByDeptCode(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getAllDeptIdsByDeptCode(map);
	}

	@Override
	public DataBean getDeptCodeByUserId(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getDeptCodeByUserId(map);
	}


	@Override
	public List<Gpdb> getSuperviseHandlingListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getSuperviseHandlingListByMap(map);
	}

	@Override
	public int getTotalNumberOfSupreviseHandlingByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getTotalNumberOfSupreviseHandlingByMap(map);
	}

	@Override
	public List<Factorypicture> getCqtpListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getCqtpListByMap(map);
	}

	@Override
	public int getCqtpListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getCqtpListSizeByMap(map);
	}

	@Override
	public List<JshxTzzyPxb> getTzzypxListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getTzzypxListByMap(map);
	}

	@Override
	public int getTzzypxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getTzzypxListSizeByMap(map);
	}

	@Override
	public List<JshxXrcPxb> getXrcpxListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getXrcpxListByMap(map);
	}

	@Override
	public int getXrcpxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getXrcpxListSizeByMap(map);
	}

	@Override
	public List<JshxXkzsb> getXzxkListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getXzxkListByMap(map);
	}

	@Override
	public int getXzxkListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getXzxkListSizeByMap(map);
	}

	@Override
	public List<JshxZazPxb> getZazpxListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZazpxListByMap(map);
	}

	@Override
	public int getZazpxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZazpxListSizeByMap(map);
	}

	@Override
	public List<JshxZcyhsb> getZcyhListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZcyhListByMap(map);
	}

	@Override
	public int getZcyhListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZcyhListSizeByMap(map);
	}

	@Override
	public List<JshxZzbw> getZzbwListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZzbwListByMap(map);
	}

	@Override
	public int getZzbwListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZzbwListSizeByMap(map);
	}

	@Override
	public List<JshxZzxtPxb> getZzxtpxListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZzxtpxListByMap(map);
	}

	@Override
	public int getZzxtpxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZzxtpxListSizeByMap(map);
	}

	@Override
	public List<Zjjtzsb> getZjjTzsbListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZjjTzsbListByMap(map);
	}

	@Override
	public int getZjjTzsbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZjjTzsbListSizeByMap(map);
	}

	@Override
	public List<CommonTroubleBean> getAllCommonTrouble(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getAllCommonTrouble(map);
	}

	@Override
	public int getAllCommonTroubleCount(Map map) {
		return httpDao.getAllCommonTroubleCount(map);
	}

	@Override
	public List<Zjjtzsbzyry> getZjjTzsbzyryListByMap(Map map) {
		// TODO Auto-generated method stub
		return httpDao.getZjjTzsbzyryListByMap(map);
	}
	
	@Override
	public List<Map<String,String>> getInspectTaskList(Map map){
		
		return httpDao.getInspectTaskListByMap(map);
	}
	@Override
	public int getInspectTaskListCountByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)httpDao.getInspectTaskListCountByMap(map);
	}
	
	@Override
	public List<Map<String,String>> GetInspectItemList(Map map){
		
		return httpDao.GetInspectItemListByMap(map);
	}
	@Override
	public int GetInspectItemListCountByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)httpDao.GetInspectItemListCountByMap(map);
	}

	@Override
	public List<Map<String, String>> getNewsphotoList(Map map)
	{
		return httpDao.getNewsphotoList(map);
	}

	@Override
	public List<Map<String, String>> getZcyhNumByMap(Map map)
	{
		return httpDao.getZcyhNumByMap(map);
	}

	@Override
	public List<Map<String, String>> getInspectTaskNumByMonth(Map map)
	{
		return httpDao.getInspectTaskNumByMonth(map);
	}

	@Override
	public List<Map<String, String>> getInspectTaskNumByYear(Map map)
	{
		return httpDao.getInspectTaskNumByYear(map);
	}
}
