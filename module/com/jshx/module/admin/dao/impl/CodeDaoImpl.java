/**
 * Copyright 2011 hongxin
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li           create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.JdbcUtil;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.CodeDao;
import com.jshx.module.admin.entity.CodeValue;

/**
 * @author Huairu.Li
 * @version 创建时间：2011-1-19 上午10:11:35
 * 类说明
 */
@Component("codeDao")
public class CodeDaoImpl extends BaseDaoImpl implements CodeDao
{

    @Autowired
    private JdbcUtil jdbcUtil;
    @Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;

    public Pagination findCodeValueByPage(Pagination page , Map<String, Object> paraMap)
    {
        return this.findPageByHqlId("findCodeValue", paraMap, page);
    }


    public List<CodeValue> findCodeValueByCodeId(String codeId)
    {
    	Map<String, Object> paraMap = new HashMap<String, Object>();
    	paraMap.put("codeId", codeId);
        return this.findListByHqlId("findCodeValue", paraMap);
    }

    public Pagination findCodeByPage(Pagination page , Map<String, Object> paraMap)
    {
        return this.findPageByHqlId("findCode", paraMap, page);
    }

    public List<CodeValue> getCodeValueBySql(String sql)
    {
        List<CodeValue> list = new ArrayList<CodeValue>();
        List<Map<String, Object>> tempList = jdbcUtil.findListBySql(sql);
        
        for (Map<String, Object> tempMap : tempList)
        {
            CodeValue codeValue = new CodeValue();
            Object[] valueStrings = tempMap.values().toArray();
            if(null != valueStrings[0])
            {
                codeValue.setItemValue(valueStrings[0].toString());
            }
            else
            {
                codeValue.setItemValue("");
            }
            if(null != valueStrings[1])
            {
                codeValue.setItemText(valueStrings[1].toString());
            }
            else
            {
                codeValue.setItemText("");
            }
            list.add(codeValue);
        }
        return list;
    }

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.CodeDao#getMaxItemCodeByParent(java.lang.String)
	 */
	public BigDecimal getMaxItemCodeByParent(String parentItemCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(parentItemCode!=null ){
			Integer length = parentItemCode.length();
			paraMap.put("parentItemCode", parentItemCode.trim());
			paraMap.put("length", length);
		}
		List list = this.findListByHqlId("getMaxItemCodeByParent", paraMap);
		BigDecimal maxID = null;
		if(list.get(0)!=null){
			maxID = new BigDecimal((Integer)list.get(0));
		}
		logger.debug("获得的最大编码是："+maxID);
		return maxID;
	}


	@Override
	public CodeValue findCodeValueByMap(Map map) {
		// TODO Auto-generated method stub
		CodeValue codevalue = new CodeValue();
		if(null!=map.get("itemValue")){
			
			String itemValue = map.get("itemValue").toString();
			if(itemValue != null && !"".equals(itemValue))
			{
				List<CodeValue> list =  sqlMapClientTemplate.queryForList("findCodeValueByMap",map);
				if(list.size() != 0)
				{
					codevalue = list.get(0);
				}
			}
		}
		if(null!=map.get("itemText")){
			String itemText = map.get("itemText").toString();
			if(itemText != null && !"".equals(itemText))
			{
				List<CodeValue> list =  sqlMapClientTemplate.queryForList("findCodeValueByMap",map);
				if(list.size() != 0)
				{
					codevalue = list.get(0);
				}
			}
		}
		return codevalue;
	}
}
