/**
 * Copyright 2011 hongxin
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-11        Administrator          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存整个应用中的常量
 * 
 * @author Chenjian
 * @version 创建时间：2011-1-11 上午09:23:55
 */
public class Constants {

	/** JDBC翻页时数据库报错时的提示 */
	public static final String JDBC_PAGE_ERROR = new String("JDBC翻页查询错误");

	/** JDBC查找列表时数据库报错时的提示 */
	public static final String JDBC_LIST_ERROR = new String("JDBC列表查找错误");

	/** JDBC查找单条记录报错时的提示 */
	public static final String JDBC_FIND_ERROR = new String("JDBC查找错误");

	/** SQL运行时候报错的提示 */
	public static final String SQL_ERROR = new String("SQL运行错误");

	/** 打开数据库连接是报错的提示 */
	public static final String GET_CONN_ERROR = new String("打开数据库错误");

	/** 关闭数据库连接是报错的提示 */
	public static final String CLOSE_CONN_ERROR = new String("关闭数据库错误");

	/** 未能在iBatis配置文件中找到SQL时的提示 */
	public static final String IBATIS_SQL_NOT_FIND = new String("SQL语句未配置");

	/** iBatis的sqlMapClient配置错误 */
	public static final String IBATIS_NOT_FIND = new String("iBatis配置错误");


	/** DDL操作运行时候报错的提示文字 */
	public static final String SQLDDL_ERROR = new String("对数据库的DDL操作运行错误");
	
	/**一维代码项为空的提示文字 */
	public static final String CODE_NULL_EXCEPTION = "一维代码项为空";
	/**一维代码值为空的提示文字 */
	public static final String CODE_VALUE_NULL_EXCEPTION = "一维代码值为空";
	/**上传文件过大报错的提示文字*/
	public static final String UPLOAD_FILE_SIZE_OVERFLOW = "一次上传不可超过10M";
	/**信息为空时报错的提示文字*/
	public static final String INFORMATION_NULL_EXCEPIION = "信息为空";
	/**创建表单失败的提示文字*/
	public static final String CREATING_TABLE_ERROR = "创建表单失败";
	/**部门为空的提示文字*/
	public static final String DEPT_NULL_EXCEPTION = "部门为空";
	/**删除字段失败的提示文字*/
	public static final String DELETING_FIELD_ERROR = "删除字段失败";
	/**创建字段失败的提示文字*/
	public static final String CREATING_FIELD_ERROR = "创建字段失败";
	/**读取BLOB字段错误的提示文字*/
	public static final String READ_BLOB_ERROR = "读取BLOB字段错误";
	/**没有找到对应历史版本的提示文字*/
	public static final String REPORT_VERSION_ERROR = "没有找到对应历史版本文件";
	/**模块为空的提示文字*/
	public static final String MODULE_NULL_EXCEPTION = "模块为空";
	/**用户为空的提示文字*/
	public static final String USER_NULL_EXCEPTION = "用户为空";
	/**读取Excel文件出错的提示文字*/
	public static final String EXCEL_FILE_ERROR = "读取Excel文件出错";
	/**保存数据库出错的提示文字*/
	public static final String SAVING_DB_ERROR = "保存数据库出错";
	/**字段名为空的提示文字*/
	public static final String FIELD_NAME_NULL_ERROR = "字段名为空";
	/**表单类别名称为空的提示文字*/
	public static final String FORM_NAME_NULL_ERROR = "表单类别名称不能为空";
	/**表单类别名称为空的提示文字*/
	public static final String FORM_CATE_NAME_NULL_ERROR = "表单类别名称不能为空";
	/**表单类别编号已存在的提示文字*/
	public static final String FORM_CATE_CODE_EXITS = "表单类别编号已存在";
	/**表单类别为空的提示文字*/
	public static final String FORMCATE_NULL_ERROR = "表单类别为空";

	/** 当前登录用户 */
	public static final String CURR_USER = "curr_user";
	/** 当前登录用户 */
	public static final String CURR_USERS = "CURR_USERS";
	/** 当前登录用户的模块 */
	public static final String CURR_USER_MODULE = "CURR_USER_MODULE";
	/**登陆失败提示 */
	public static final String LOGIN_ERROR = "用户名或密码错误！";
	/**注册会员已过期提示 */
	public static final String LOGIN_EXPRIATION = "注册会员已过期！";
	/**权限提示 */
	public static final String ACCESS_DENY = "您无权访问该功能！";
	/**校验码验证失败提示 */
	public static final String CAPTCHA_ERROR = "校验码验证错误！";
	/**登陆信息*/
	public static final String LOGIN_MESSAGE = "LOGIN_MESSAGE";
	/**登陆失败*/
	public static final String LOGIN_FAIL = "LOGIN_FAIL";
	/** 当前登录用户id */
	public static final String LOGIN_USER_ID = "LOGIN_USER_ID";

	/** 是否允许记录访问地址 */
	public static Boolean isLog = null;
	
	/**
	 * CodeSQL里面表示当前用户所在部门ID的占位符
	 */
	public static String CODE_SQL_PATTERN_DEPAR_ID = "&userdepartment&";

	/**
	 * CodeSQL里面表示当前用户ID的占位符
	 */
	public static String CODE_SQL_PATTERN_USER_ID = "&currentuserid&";
	
	/** 未登录用户请求访问的页面 */
	public static String USER_REQUEST_URL = "user_request_url";
	
	/** 当前访问的模块编号 */
	public static final String CURR_MODULE_CODE = "currModuleCode";
	
	/** 打开功能模块时是否需要使用多Tab模式 */
	public static Boolean USER_TAB = false;
	
	/** 用户手机号是否唯一 */
	public static Boolean MOBILE_NO_UNIQUE = false;
	
	/** 第一次访问标识 */
	public static String FIRST_VISITED = "FIRST_VISITED";
	
	/** 资源地址与角色的映射 */
	public static Map<String, String> RESOURCE_AUTH = new HashMap<String, String>();
	
	/** 是否允许一个用户多处登陆 */
	public static Boolean MULTI_LOGIN = false;
	
	
	/** 一维代码表缓存 */
	public static Map<String, Map<String, String>> CODE_MAP = new HashMap<String,Map<String,String>>();
	
	/** session过期后，是否使用ajax登陆 */
	public static Boolean AJAXLOGIN = false;
}
