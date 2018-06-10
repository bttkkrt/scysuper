/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li           create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jshx.core.base.dao.JdbcUtil;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.dao.CodeDao;
import com.jshx.module.form.dbddl.IDateBaseDDL;
import com.jshx.module.form.entity.FormTable;

/**
 * @author Huairu.Li
 * @version 创建时间：2011-1-19 上午10:11:35 
 * 类说明
 */
public class FormTableDao extends BaseDaoImpl{


	private IDateBaseDDL testDDL;
	
	@Autowired
	private JdbcUtil jdbcUtil;
	
	String NoUseWord="action,add,aggregate,all,alter,after,and,as,asc,avg,avg_row_length,auto_increment,between,bigint,bit,binary,blob,bool,both,by,cascade,case,char,character,change,check,checksum,column,columns,comment,constraint,create,cross,current_date,current_time,current_timestamp,data,database,databases,date,datetime,day,day_hour,day_minute,day_second,dayofmonth,dayofweek,dayofyear,dec,decimal,default,delayed,delay_key_write,delete,desc,describe,distinct,distinctrow,double,drop,end,else,escape,escaped,enclosed,enum,explain,exists,fields,file,first,float,float4,float8,flush,foreign,from,for,full,function,global,grant,grants,group,having,heap,high_priority,hour,hour_minute,hour_second,hosts,identified,ignore,in,index,infile,inner,insert,insert_id,int,integer,interval,int1,int2,int3,int4,int8,into,if,is,isam,join,key,keys,kill,last_insert_id,leading,left,length,like,lines,limit,load,local,lock,logs,long,longblob,longtext,low_priority,max,max_rows,match,mediumblob,mediumtext,mediumint,middleint,min_rows,minute,minute_second,modify,month,monthname,myisam,natural,numeric,no,not,null,on,optimize,option,optionally,or,order,outer,outfile,pack_keys,partial,password,precision,primary,procedure,process,processlist,privileges,read,real,references,reload,regexp,rename,replace,restrict,returns,revoke,rlike,row,rows,second,select,set,show,shutdown,smallint,soname,sql_big_tables,sql_big_selects,sql_low_priority_updates,sql_log_off,sql_log_update,sql_select_limit,sql_small_result,sql_big_result,sql_warnings,straight_join,starting,status,string,table,tables,temporary,terminated,text,then,time,timestamp,tinyblob,tinytext,tinyint,trailing,to,type,use,using,unique,unlock,unsigned,update,usage,values,varchar,variables,varying,varbinary,with,write,when,where,year,year_month,zerofill";
	
	
	protected Logger logger = LoggerFactory.getLogger(JdbcUtil.class);
	
	/**
	 * 判断该数据库用户下是否存在所提供的表名
	 * 
	 * @param physicalName
	 * @return boolean
	 */
	@SuppressWarnings("deprecation")
	public boolean isExist(String physicalName) {
		try {
			
			if (physicalName != null) {
				/*
				 * 数据库物理表名的约束：
				 * 1.不能取保留字 
				 * 2.长度小于26
				 * 3.不以数字开头
				 * 待续。。。
				 * 
				 */
				
				if (NoUseWord.contains(physicalName.toLowerCase())) {
					return true;
				}
				
				if (physicalName.length()>26||Character.isDigit(physicalName.charAt(0))) {
					return true;
				}
				String str = physicalName.toUpperCase();
				for (int i = 0; i < str.length(); i++) {   
		            //对每一个字母进行判断   
					  if (!Character.isDigit(str.charAt(i))&&!Character.isUpperCase(str.charAt(i))) {   
		            	if (str.charAt(i)!='_') {
		            		return true;
						}
		            
		            }   
		        }   
				return testDDL.isTableExist(physicalName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/*
	 * 通过表名查表
	
	public List<FormTable> queryFormTableByName(String tableName){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(tableName!=null)
			paraMap.put("tableName", "%"+tableName+"%");
		return entityDao.findListByDynamicHqlId("queryFormTable", paraMap);
	}
	 */
	
	public void createCopyTable(String tableName, String copyTableName) {

		try {
			if (!isExist(copyTableName)) {
				String sql = testDDL.copyTable(copyTableName, tableName);
				jdbcUtil.executeSql(sql,null);
				String addPrimaryKey = "alter table " + copyTableName
						+ " add PRIMARY KEY (\"ROW_ID\")";
				jdbcUtil.executeSql(addPrimaryKey,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean createTable(String TableName) throws Exception {

		if (!isExist(TableName)) {
			testDDL.CreatTB(TableName);
			
			return true;
		} else

			return false;
	}
	
	
	public boolean createTableinForce(String TableName) throws Exception {

			testDDL.CreatTB(TableName);
			
			return true;
		
	}
	
	
	public Pagination findTableByPage(Pagination page, Map<String, Object> paraMap) {
		return this.findPageByHqlId("findformtable", paraMap, page);
		
	}
	
	public boolean dropTable(String tableName)throws Exception {
		String SQL = "drop table " + tableName;
		
		try {
			jdbcUtil.executeSql(SQL,null);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			logger.error("删除表"+tableName+"时出现异常,可能该表已被删除！");
			//BusinessException bException = new BusinessException();
			//bException.add(Constants.EXCEPTION_CODE_OO1,Constants.EXCEPTION_LEVEL_ERROR,"删除表"+tableName+"时出现异常,可能该表已被删除！");
			//throw bException;
		}
		//需要删除对应的触发器，但不必要，可有可无，不影响数据库
		return true;
	}
	
	public int getMaxSortSQ() {
		String hql = "select max(sortSQ) from Form_TableInfo";
		Map<String, Object> l  = jdbcUtil.findBySql(hql);
		if(l!=null&&l.size()>0){
			Object number = l.get("MAX(SORTSQ)");
			if (number!=null) {
				return Integer.parseInt(number.toString());
				
			}
		}
		return 0;
	}
	
	public Pagination getImportForm(Pagination tablePage,String modelphysicalName)
	{
		return testDDL.getImportForm(tablePage,modelphysicalName);
	}

	public IDateBaseDDL getTestDDL() {
		return testDDL;
	}

	public void setTestDDL(IDateBaseDDL testDDL) {
		this.testDDL = testDDL;
	}
	
	
}
