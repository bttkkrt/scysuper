/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-19         Huairu.Li           create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jshx.core.base.dao.JdbcUtil;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.dao.CodeDao;
import com.jshx.module.form.dbddl.IDateBaseDDL;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;
import com.sun.org.apache.bcel.internal.generic.RETURN;


public class FormFieldDao extends BaseDaoImpl {

	
	private IDateBaseDDL testDDL;
	
	String NoUseWord="action,add,aggregate,all,alter,after,and,as,asc,avg,avg_row_length,auto_increment,between,bigint,bit,binary,blob,bool,both,by,cascade,case,char,character,change,check,checksum,column,columns,comment,constraint,create,cross,current_date,current_time,current_timestamp,data,database,databases,date,datetime,day,day_hour,day_minute,day_second,dayofmonth,dayofweek,dayofyear,dec,decimal,default,delayed,delay_key_write,delete,desc,describe,distinct,distinctrow,double,drop,end,else,escape,escaped,enclosed,enum,explain,exists,fields,file,first,float,float4,float8,flush,foreign,from,for,full,function,global,grant,grants,group,having,heap,high_priority,hour,hour_minute,hour_second,hosts,identified,ignore,in,index,infile,inner,insert,insert_id,int,integer,interval,int1,int2,int3,int4,int8,into,if,is,isam,join,key,keys,kill,last_insert_id,leading,left,length,like,lines,limit,load,local,lock,logs,long,longblob,longtext,low_priority,max,max_rows,match,mediumblob,mediumtext,mediumint,middleint,min_rows,minute,minute_second,modify,month,monthname,myisam,natural,numeric,no,not,null,on,optimize,option,optionally,or,order,outer,outfile,pack_keys,partial,password,precision,primary,procedure,process,processlist,privileges,read,real,references,reload,regexp,rename,replace,restrict,returns,revoke,rlike,row,rows,second,select,set,show,shutdown,smallint,soname,sql_big_tables,sql_big_selects,sql_low_priority_updates,sql_log_off,sql_log_update,sql_select_limit,sql_small_result,sql_big_result,sql_warnings,straight_join,starting,status,string,table,tables,temporary,terminated,text,then,time,timestamp,tinyblob,tinytext,tinyint,trailing,to,type,use,using,unique,unlock,unsigned,update,usage,values,varchar,variables,varying,varbinary,with,write,when,where,year,year_month,zerofill";
	
	
	public boolean isExist(String tablephysicalName,String columName) throws Exception {
		
		//List<String> sList = codeValueManager.getAllTextStringByType(SQLKeyword);
		//if(sList.contains(columName.toUpperCase()))
		//	return true;
		
		/*
		 * 字段名的约束：
		 * 1.不能取保留字
		 * 2.长度小于30
		 * 3.不以数字开头
		 * 待续。。。
		 * 
		 */
		if (NoUseWord.contains(columName.toLowerCase())) {
			return true;
		}
		
		if (columName.length()>30||Character.isDigit(columName.charAt(0))) {
			return true;
		}
		String str = columName.toUpperCase();
		for (int i = 0; i < str.length(); i++) {   
            //对每一个字母进行判断   
            if (!Character.isDigit(str.charAt(i))&&!Character.isUpperCase(str.charAt(i))) {     
            	if (str.charAt(i)!='_') {
            		return true;
				}
            
            }   
        }   
		if(tablephysicalName!=null&&columName!=null){
			return testDDL.isColExist(tablephysicalName, columName);
		}
		return false;
		
	}
	
	   /**
     * 获得该表添加字段
     * @param tableId
     * @return List<FormField>
     */
    public List<FormField> getAllField(String tableId){
    	String hql = "from FormField t where t.table='"+tableId+"' order by t.sortSQ";
    	return this.findListByHql(hql);
    	
    }
    
	public Pagination findFieldByPage(Pagination page, Map<String, Object> paraMap) {
		return this.findPageByHqlId("findformfield", paraMap, page);
		
	}
	
	public void addColumn(String tablephysicalName, FormField field) throws Exception
    {   
		try {
			if (!isExist(tablephysicalName, field.getFieldName())) {
				if (testDDL.AddColumn(tablephysicalName, field)) {
					// 保存字段信息到字段信息表中
					if (field.getId()==null||field.getId().length()==0) {
						this.saveObject(field);
					}
				
				}
			}
		} catch (Exception e) {
			BasalException be = new BasalException(BasalException.ERROR, Constants.CREATING_FIELD_ERROR, e);
			throw be;
		}
		
    }
	
	
	
	public void addColumninForce(String tablephysicalName, FormField field) throws Exception
    {   
		try {
			
				testDDL.AddColumn(tablephysicalName, field);
			
		} catch (Exception e) {
			
		}
		
    }
	
	
	 public void deleteColumn(String tableName, String fieldId)
	 {
		 FormField field = (FormField)this.getObjectById(FormField.class, fieldId.toString());
			
	    	String fieldName = field.getFieldName();
			try {
				
			
	    	if (testDDL.DelColumn(tableName, fieldName,field.getFieldType())) {
	    		this.removeObjectById(FormField.class, fieldId.toString());
	    		
			}} 
	    	catch (Exception e) {
	    		BasalException be = new BasalException(BasalException.ERROR, Constants.DELETING_FIELD_ERROR, e);
				throw be;
	    		
	    		
			}
	       
	   }
		@Autowired
		private JdbcUtil jdbcUtil;
		
		 public List<FormField> getFieldByPhyName(String PhyName)
		 {
		    	
				return testDDL.getFieldByPhyName(PhyName);
		    	
		}

		public int getMaxSortSQ(String tableId){
			String hql = "select max(sortSQ) from Form_TableField where tableId='"+tableId+"'";
			Map<String, Object> l  = jdbcUtil.findBySql(hql);
			if(l!=null&&l.size()>0){
				Object number = l.get("MAX(SORTSQ)");
				if (number!=null) {
					return Integer.parseInt(number.toString());
					
				}
			}
			return 0;
		}

		public IDateBaseDDL getTestDDL() {
			return testDDL;
		}

		public void setTestDDL(IDateBaseDDL testDDL) {
			this.testDDL = testDDL;
		}

}
