package com.jshx.http.dao.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.factorypicture.entity.Factorypicture;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.http.bean.CommonTroubleBean;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.KeyBean;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.PhotoBean;
import com.jshx.http.dao.HttpDao;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.xkzsb.entity.JshxXkzsb;
import com.jshx.xrcPxb.entity.JshxXrcPxb;
import com.jshx.zazPxb.entity.JshxZazPxb;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;
@Component("httpDao")
public class HttpDaoImpl implements HttpDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	@Override
	public List<MyUserBean> getUserBeanByMap(Map map) {
		return  sqlMapClientTemplate.queryForList("query_user_byMap",map);
	}
	@SuppressWarnings("unchecked")
	@Override
	public KeyBean getVersionNumberByForm(Map map) {
		// TODO Auto-generated method stub
		return (KeyBean)sqlMapClientTemplate.queryForObject("query_versionNumByPlatForm",map);
	}
	
	@Override
	public DataBean getPhotoListByType(Map map) {
		// TODO Auto-generated method stub
		DataBean bean = new DataBean();
		List<PhotoPic>  list = sqlMapClientTemplate.queryForList("query_photoList_byMap",map);
		JSONArray ja = new JSONArray();
		for(PhotoPic d:list)
		{
			JSONObject jo = new JSONObject();
			jo.put("fileName", d.getFileName());
			jo.put("fileSize", d.getFileSize());
			jo.put("fileUrl", d.getHttpUrl()+"/upload/photo/"+d.getPicName());
			ja.add(jo);
		}
		bean.setRname(ja.toString());
		return bean;
	}
	@Override
	public List<DataBean> getRoleByUserId(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_role_byUserId",map);
	}
	
	/**
	 * 查询List<Map<String,Object>>(通用)
	 * 费谦
	 * 2015-10-12
	 */
	public List<Map<String,Object>> findListDataByMap(Map<String, Object> paraMap){
		String sqlId=(String) paraMap.get("sqlId");
		if(null==paraMap.get("start")){
			return this.sqlMapClientTemplate.queryForList(sqlId,paraMap);
		}else{
			Integer start = Integer.parseInt(paraMap.get("start").toString()) ;
			Integer limit = Integer.parseInt(paraMap.get("limit").toString()) ;
			return this.sqlMapClientTemplate.queryForList(sqlId,paraMap,(start-1)*limit,limit);
		}
		
	}

	/**
	 * update(通用)
	 * 费谦
	 * 2014-11-6
	 */
	public int updateByMap(Map<String, Object> paraMap){
		String sqlId=(String) paraMap.get("sqlId");
		return this.sqlMapClientTemplate.update(sqlId,paraMap);
		
	}
	
		@Override
	public List<Map> getListByMap(Map map) {
		String sqlID = (String) map.get("sqlID");
		int pageNum = (Integer)map.get("pageNum");
		int pageSize =  (Integer)map.get("pageSize");
		return this.sqlMapClientTemplate.queryForList(sqlID,map,pageNum,pageSize);
		
	}
	@Override
	public int getListCountbyMap(Map map) {
		String sqlID = (String) map.get("sqlID");
		return (Integer)this.sqlMapClientTemplate.queryForObject(sqlID,map);
	}
	@Override
	public Map getMapByMap(Map map) {
		String sqlID = (String)map.get("sqlID");
		return (Map)this.sqlMapClientTemplate.queryForObject(sqlID,map);
	}
	@Override
	public void updateMapByMap(Map map) {
		String sqlID = (String)map.get("sqlID");
		this.sqlMapClientTemplate.update(sqlID,map);
	}
	
	/**
	 * 查询查询监督检查任务列表
	 */
	public List<Map<String,Object>> findJdjcrwByMap(Map<String, Object> paraMap){
		String sqlId=(String) paraMap.get("sqlId");
		if(null==paraMap.get("start")){
			return this.sqlMapClientTemplate.queryForList(sqlId,paraMap);
		}else{
			Integer start = Integer.parseInt(paraMap.get("start").toString()) ;
			Integer limit = Integer.parseInt(paraMap.get("limit").toString()) ;
			return this.sqlMapClientTemplate.queryForList(sqlId,paraMap,(start-1)*limit,limit);
		}
		
	}
	/**
	 * 查询查询监督检查任务列表数量
	 */
	public List<Map<String,Object>> findJdjcrwCountByMap(Map<String, Object> paraMap){
		String sqlId=(String) paraMap.get("sqlId");
		if(null==paraMap.get("start")){
			return this.sqlMapClientTemplate.queryForList(sqlId,paraMap);
		}else{
			Integer start = Integer.parseInt(paraMap.get("start").toString()) ;
			Integer limit = Integer.parseInt(paraMap.get("limit").toString()) ;
			return this.sqlMapClientTemplate.queryForList(sqlId,paraMap,(start-1)*limit,limit);
		}
		
	}
	
	public List<Map<String,String>> findcheckName(Map<String, String> paraMap){
		
		return sqlMapClientTemplate.queryForList("find_checkName",paraMap);
		
	}
	@Override
	public List<SecProFeeAcc> getSecProFeeAccListByMap(Map map) {
		// TODO Auto-generated method stub
		if(null==map.get("start")){
			return this.sqlMapClientTemplate.queryForList("findSecProFeeAccListByMap",map);
		}else{
			Integer start = Integer.parseInt(map.get("start").toString()) ;
			Integer limit = Integer.parseInt(map.get("limit").toString()) ;
			return this.sqlMapClientTemplate.queryForList("findSecProFeeAccListByMap",map,(start-1)*limit,limit);
		}
	}
	
	
	
	
	
	@Override
	public void savePhotoInfo(PhotoBean bean) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.insert("insert_photoInfo",bean);
	}
	@Override
	public List<DataBean> getTownList(Map map) {
		// TODO Auto-generated method stub
		//return sqlMapClientTemplate.queryForList("query_townlist_byMap",map);
		return sqlMapClientTemplate.queryForList("query_townlist_byMap",map);
	}
	@Override
	public List<DataBean> getCountyList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_countylist_byMap",map);
	}
	
	@Override
	public List<DataBean> getWSList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_wslist_byMap",map);
	}
	
	@Override
	public List<CommonTroubleBean> getAllCommonTrouble(Map map) {
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("query_allCommonTrouble_byMap",map,s,limit);
	
	}
	@Override
	public int getAllCommonTroubleCount(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("query_allCommonTroubleCount_byMap",map);
	}
	
	
	@Override
	public List<CommonTroubleBean> getCommonTroubleList(Map map) {
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("query_commonTroubleList_byMap",map,s,limit);
	}
	@Override
	public int getCommonTroubleListCount(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("query_commonTroubleListCount_byMap",map);
	}
	@Override
	public List<DataBean> getDeptList(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_deptlist_byMap",map);
	}
	@Override
	public List<CommonTroubleBean> getMajorTroubleList(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("query_majorTroubleList_byMap",map,s,limit);
	}
	
	@Override
	public List<CommonTroubleBean> getAllMajorTrouble(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("query_allMajorTrouble_byMap",map,s,limit);
	}
	@Override
	public int getAllMajorTroubleCount(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("query_allMajorTroubleCount_byMap",map);
	}
	@Override
	public int getMajorTroubleListCount(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("query_majorTroubleListCount_byMap",map);
	}
	@Override
	public List<String> getAllDeptIdsByDeptCode(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_allDeptIds_byDeptCode",map);
	}
	@Override
	public DataBean getDeptCodeByUserId(Map map) {
		// TODO Auto-generated method stub
		return (DataBean)sqlMapClientTemplate.queryForObject("query_deptCode_byUserId",map);
	}
	@Override
	public List<Gpdb> getSuperviseHandlingListByMap(Map map) {
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("query_commonSuperviseHandlingList_byMap",map,s,limit);
	}
	@Override
	public int getTotalNumberOfSupreviseHandlingByMap(Map map) {
		return (Integer)sqlMapClientTemplate.queryForObject("query_commonSuperviseHandlingListCount_byMap",map);
	}
	@Override
	public List<Factorypicture> getCqtpListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getCqtpListByMap",map,s,limit);
	}
	@Override
	public int getCqtpListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getCqtpListSizeByMap",map);
	}
	@Override
	public List<JshxTzzyPxb> getTzzypxListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getTzzypxListByMap",map);
	}
	@Override
	public int getTzzypxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getTzzypxListSizeByMap",map);
	}
	@Override
	public List<JshxXrcPxb> getXrcpxListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getXrcpxListByMap",map,s,limit);
	}
	@Override
	public int getXrcpxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getXrcpxListSizeByMap",map);
	}
	@Override
	public List<JshxXkzsb> getXzxkListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getXzxkListByMap",map,s,limit);
	}
	@Override
	public int getXzxkListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getXzxkListSizeByMap",map);
	}
	@Override
	public List<JshxZazPxb> getZazpxListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZazpxListByMap",map);
	}
	@Override
	public int getZazpxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getZazpxListSizeByMap",map);
	}
	@Override
	public List<JshxZcyhsb> getZcyhListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getZcyhListByMap",map,s,limit);
	}
	@Override
	public int getZcyhListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getZcyhListSizeByMap",map);
	}
	@Override
	public List<JshxZzbw> getZzbwListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getZzbwListByMap",map,s,limit);
	}
	@Override
	public int getZzbwListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getZzbwListSizeByMap",map);
	}
	@Override
	public List<JshxZzxtPxb> getZzxtpxListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getZzxtpxListByMap",map,s,limit);
	}
	@Override
	public int getZzxtpxListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getZzxtpxListSizeByMap",map);
	}
	@Override
	public List<Zjjtzsb> getZjjTzsbListByMap(Map map) {
		// TODO Auto-generated method stub
		int start = (Integer)map.get("start");
		int limit = (Integer)map.get("limit");
		int s = (start-1)*limit;
		return sqlMapClientTemplate.queryForList("getZjjTzsbListByMap",map,s,limit);
	}
	@Override
	public int getZjjTzsbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getZjjTzsbListSizeByMap",map);
	}
	@Override
	public List<Zjjtzsbzyry> getZjjTzsbzyryListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZjjTzsbzyryListByMap",map);
	}
	@Override
	public List<Map<String,String>> getInspectTaskListByMap(Map map) {
		
		try {
			int start = (Integer)map.get("start");
			int limit = (Integer)map.get("limit");
			int s = (start-1)*limit;
			return sqlMapClientTemplate.queryForList("getInspectTaskListByMap",map,s,limit);
		} catch (Exception e) {
			return sqlMapClientTemplate.queryForList("getInspectTaskListByMap",map);
		}
	}
	@Override
	public int getInspectTaskListCountByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getInspectTaskListCountByMap",map);
	}
	
	@Override
	public List<Map<String,String>> GetInspectItemListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("GetInspectItemListByMap",map);
	}
	@Override
	public int GetInspectItemListCountByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("GetInspectItemListCountByMap",map);
	}
	@Override
	public List<Map<String, String>> getNewsphotoList(Map map)
	{
		return sqlMapClientTemplate.queryForList("getNewsphotoListByMap",map);
	}
	@Override
	public List<Map<String, String>> getZcyhNumByMap(Map map)
	{
		return sqlMapClientTemplate.queryForList("getZcyhNumByMap",map);
	}
	@Override
	public List<Map<String, String>> getInspectTaskNumByMonth(Map map)
	{
		return sqlMapClientTemplate.queryForList("getInspectTaskNumByMonth",map);
	}
	@Override
	public List<Map<String, String>> getInspectTaskNumByYear(Map map)
	{
		return sqlMapClientTemplate.queryForList("getInspectTaskNumByYear",map);
	}
	
}
