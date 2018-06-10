/**
 * Copyright 2011 hongxin
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li          create
 * 2011-1-22         Chenjian         添加根据一维代码查找代码值
 * 2011-1-24         Chenjian         添加根据一维代码以及代码值查找代码实体
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.dao.CodeDao;
import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.extend.ICodeValueExtendInfo;
import com.jshx.module.admin.extend.ICodeValueExtendInfoDao;
import com.jshx.module.admin.service.CodeService;

/**
 * 一维代码服务实现类
 * 
 * @author Huairu.Li
 * @version 创建时间：2011-1-19 下午02:51:57 
 */
@Service("codeService")
public class CodeServiceImpl extends BaseServiceImpl implements CodeService {

	@Autowired() 
	@Qualifier("codeDao")
	private CodeDao codeDao;

	@Transactional(propagation = Propagation.NESTED)
	public void deleteCodeValue(String id) {
		CodeValue codeValue = (CodeValue) codeDao.getObjectById(
				CodeValue.class, id);
		codeValue.setDelFlag(1);
		codeDao.updateObject(codeValue);
		CodeValue parentItem = codeValue.getParentItem();
		if (parentItem != null) {
			parentItem.setChildNum(parentItem.getChildNum() - 1);
			codeDao.updateBaseModelObject(parentItem);
		}
	}

	@Transactional(readOnly = true)
	public Pagination findCodeValueByPage(Pagination page,
			Map<String, Object> paraMap) {
		return codeDao.findCodeValueByPage(page, paraMap);
	}

	@Transactional(propagation = Propagation.NESTED)
	public CodeValue modifyCodeValue(CodeValue codeValue) {
		CodeValue codeValue1 = this.findCodeValueById(codeValue.getId());
		codeValue1.setItemText(codeValue.getItemText());
		codeValue1.setItemValue(codeValue.getItemValue());
		codeValue1.setSortSQ(codeValue.getSortSQ());
		codeDao.updateObject(codeValue1);
		ICodeValueExtendInfoDao extendInfoDao = getExtendDao();
		if(extendInfoDao!=null){
			ICodeValueExtendInfo extendInfo = codeValue.getExtendInfo();
			if(extendInfo!=null){
				extendInfo.setCodeValueId(codeValue.getId());
				if(extendInfo.getId()!=null)
					extendInfoDao.updateCodeValueExtendInfo(extendInfo);
				else
					extendInfoDao.saveCodeValueExtendInfo(extendInfo);
				codeValue1.setExtendInfo(extendInfo);
			}
		}
		return codeValue1;
	}

	@Transactional(propagation = Propagation.NESTED)
	public CodeValue saveCodeValue(CodeValue codeValue) {
		CodeValue parentItem = codeValue.getParentItem();
		if (parentItem != null && parentItem.getId() != null) {
			parentItem = this.findCodeValueById(parentItem.getId());
		}
		String itemCode = null;
		if (parentItem == null)
			itemCode = createItemCode("0");
		else
			itemCode = createItemCode(parentItem.getItemCode());
		codeValue.setItemCode(itemCode);
		codeValue.setChildNum(0);
		codeValue.setDelFlag(0);
		codeValue.setParentItem(parentItem);
		codeDao.saveObject(codeValue);

		if (parentItem != null) {
			if (parentItem.getChildNum() != null)
				parentItem.setChildNum(parentItem.getChildNum() + 1);
			else
				parentItem.setChildNum(1);
			codeDao.updateBaseModelObject(parentItem);
		}
		
		ICodeValueExtendInfoDao extendInfoDao = getExtendDao();
		if(extendInfoDao!=null){
			ICodeValueExtendInfo extendInfo = codeValue.getExtendInfo();
			
			if(extendInfo!=null){
				extendInfo.setCodeValueId(codeValue.getId());
				if(extendInfo.getId()!=null)
					extendInfoDao.updateCodeValueExtendInfo(extendInfo);
				else
					extendInfoDao.saveCodeValueExtendInfo(extendInfo);
				codeValue.setExtendInfo(extendInfo);
			}
		}
		return codeValue;
	}

	@Transactional(propagation = Propagation.NESTED)
	public void deleteCode(String id) {
		Code code = (Code) codeDao.getObjectById(Code.class, id);
		code.setDelFlag(1);
		codeDao.updateObject(code);
	}

	@Transactional(readOnly = true)
	public Pagination findCodeByPage(Pagination page,
			Map<String, Object> paraMap) {
		return codeDao.findCodeByPage(page, paraMap);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Code> getAllCode() {
		return (List<Code>) codeDao
				.findListByHql("from Code where 1=1 and delFlag=0");
	}

	@Transactional(propagation = Propagation.NESTED)
	public Code modifyCode(Code code) {
		codeDao.merge(code);
		return code;
	}

	@Transactional(propagation = Propagation.NESTED)
	public Code saveCode(Code code) {
		codeDao.saveObject(code);
		return code;
	}

	public CodeDao getCodeDao() {
		return codeDao;
	}

	public void setCodeDao(CodeDao codeDao) {
		this.codeDao = codeDao;
	}

    /* (non-Javadoc)
     * @see com.jshx.module.admin.service.CodeService#findValueByCodeAndItem(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public CodeValue findValueByCodeAndItem(String codeId , String itemValue)
    {
    	
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("codeId", codeId);
        paraMap.put("itemValue", itemValue);
        List<CodeValue> valueList = codeDao.findListByHqlId("findCodeValueByItemValue", paraMap);
        if (valueList != null && valueList.size() > 0){
            CodeValue codeValue = valueList.get(0);
            ICodeValueExtendInfoDao extendInfoDao = getExtendDao();
    		if(extendInfoDao!=null){
    			ICodeValueExtendInfo extendInfo = extendInfoDao.getByCodeValueId(codeValue.getId());
    			codeValue.setExtendInfo(extendInfo);
    		}
            return codeValue;
        }else
            return null;
    }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#findCodeValueByCode(java.lang.String)
	 *      只查出一维代码结构
	 */
    @Transactional(readOnly = true)
	public List<CodeValue> findCodeValueByCode(String codeId) {
		return codeDao.findCodeValueByCodeId(codeId);
	}


	/**
	 * 根据CodeName获取CodeValue的列表
	 * 
	 * @param codeName
	 * @return
	 */
    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CodeValue> getCodeValuesByCodeName(String codeName) {
    	Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("delFlag", 0);
        paraMap.put("codeName", codeName);
    	Code code = (Code) codeDao.findObjectByFieldsMap(Code.class, paraMap);
    	if (null != code) {
			List<CodeValue> arrayList = (List<CodeValue>) codeDao.findCodeValueByCodeId(code.getId());
			Collections.sort(arrayList, new SortBySQ());
			return arrayList;
		} else {
			return null;
		}
	}

	/**
	 * 根据CodeName和ItemValue获取CodeValue
	 * 
	 * @param codeName
	 * @param itemValue
	 * @return
	 */
    @Transactional(readOnly = true)
	public CodeValue getCodeValueByCodeNameAndItemValue(String codeName,
			String itemValue) {
		Code code = (Code) codeDao.getObjectByProperty(Code.class, "codeName",
				codeName);
		if (null != code) {
			return findValueByCodeAndItem(code.getId(), itemValue);
		} else {
			return null;
		}
	}

	/**
	 * 根据SQL查询数据，并把结果包装成CodeValue
	 * 
	 * @param sql
	 * @return
	 */
    @Transactional(readOnly = true)
	public List<CodeValue> getCodeValueBySql(String sql) {
		return codeDao.getCodeValueBySql(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#createItemCode(java.lang.String)
	 */
    @Transactional(readOnly = true)
	public String createItemCode(String parentItemCode) {
		BigDecimal maxID = codeDao.getMaxItemCodeByParent(parentItemCode);

		if (maxID == null || maxID.intValue() == 0) {
			if (parentItemCode == null || parentItemCode.equals("0"))
				return "001";
			else
				return parentItemCode + "001";
		} else {
			if (parentItemCode == null || parentItemCode.equals("0")) {
				if (new Integer(maxID.intValue() + 1) < 10)
					return "00" + new Integer(maxID.intValue() + 1);
				else if (new Integer(maxID.intValue() + 1) < 100
						&& new Integer(maxID.intValue() + 1) >= 10)
					return "0" + new Integer(maxID.intValue() + 1);
				else
					return "" + new Integer(maxID.intValue() + 1);
			} else {
				if (new Integer(maxID.intValue() + 1) < 10)
					return parentItemCode + "00"
							+ new Integer(maxID.intValue() + 1);
				else if (new Integer(maxID.intValue() + 1) < 100
						&& new Integer(maxID.intValue() + 1) >= 10)
					return parentItemCode + "0"
							+ new Integer(maxID.intValue() + 1);
				else
					return parentItemCode + new Integer(maxID.intValue() + 1);
			}
		}
	}
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#findCodeByParent(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CodeValue> findCodeByParent(String parentItemId) {
		CodeValue parentItem = (CodeValue) codeDao.getObjectById(
				CodeValue.class, parentItemId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentItem", parentItem);
		return codeDao.findListByHqlId("findCodeValue", paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#findCodeByParentCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CodeValue> findCodeByParentCode(String parentItemCode,
			String codeId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (parentItemCode != null){
			CodeValue parent = new CodeValue();
			parent.setId(parentItemCode);
			paraMap.put("parentItem", parent);
		}
		paraMap.put("codeId", codeId);
		return codeDao.findListByHqlId("findCodeValue", paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#findCodeValueById(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public CodeValue findCodeValueById(String id) {
		CodeValue codeValue = (CodeValue) codeDao.getObjectById(CodeValue.class, id);
		ICodeValueExtendInfoDao extendInfoDao = getExtendDao();
		if(extendInfoDao!=null){
			ICodeValueExtendInfo extendInfo = extendInfoDao.getByCodeValueId(codeValue.getId());
			codeValue.setExtendInfo(extendInfo);
		}
		return codeValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#findCodeById(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public Code findCodeById(String id) {
		return (Code) codeDao.getObjectById(Code.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#getCodeValueByItemCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CodeValue getCodeValueByItemCode(String codeId, String itemCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("codeId", codeId);
		paraMap.put("itemCode", itemCode);
		List<CodeValue> list = codeDao.findListByHqlId("findCodeValueByItem",
				paraMap);
		if (list != null && list.size() > 0){
			CodeValue codeValue = list.get(0);
			ICodeValueExtendInfoDao extendInfoDao = getExtendDao();
    		if(extendInfoDao!=null){
    			ICodeValueExtendInfo extendInfo = extendInfoDao.getByCodeValueId(codeValue.getId());
    			codeValue.setExtendInfo(extendInfo);
    		}
			return codeValue;
		}else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.CodeService#checkCodeValue(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public Boolean checkCodeValue(String codeId, String itemText,
			String itemValue) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("codeId", codeId);
		paraMap.put("itemText", itemText);
		paraMap.put("itemValue", itemValue);
		List<?> list = this.codeDao.findListByHqlId("checkCodeValue", paraMap);
		Long cnt = Long.valueOf(list.get(0).toString());
		if (cnt > 0)
			return true;
		else
			return false;
	}

	@Transactional(readOnly = true)
	public void updateCodeMap() {
		Constants.CODE_MAP.clear();
		List<CodeValue> valueList = null;
		List<Code> codeList = getAllCode();
		for(Code code : codeList){
			Map<String, String> valueMap = Constants.CODE_MAP.get(code.getId());
			if(valueMap==null)
				valueMap = new HashMap<String, String>();
			valueList = findCodeValueByCode(code.getId());
			for(CodeValue value : valueList){
				valueMap.put(value.getItemValue(), value.getItemText());
			}
			Constants.CODE_MAP.put(code.getId(), valueMap);
		}		
		valueList = null;
		codeList = null;
	}
	
	private ICodeValueExtendInfoDao getExtendDao(){
		try{
			ICodeValueExtendInfoDao extendDao = (ICodeValueExtendInfoDao)SpringContextHolder.getBean("codeValueExtendDao");
			return extendDao;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public CodeValue findCodeValueByMap(Map map) {
		// TODO Auto-generated method stub
		return codeDao.findCodeValueByMap(map);
	}
}
