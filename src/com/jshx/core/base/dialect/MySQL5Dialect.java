/**
 * $$ID MySQL5Dialect created on 2008-10-27  10:18:51 $$
 * @author 丁全宁 Simon Ding  <simon.ding@dextyrs.com>
 * @version 1.0
 */
/* Copyright (c) Jiangsu Hongxin System Integration Co.Ltd  2008
 *  China Telecom Group System Integrated Co.Ltd. Jiangsu Branch 2008
 *  All rights reserved.
 */
/* 
* ================================================================
* Change Revision
* ---------------------------------------------------------------
*    DATE                BY                   COMMENT
* ---------------------------------------------------------------
* 2008-10-27         Simon Ding                Create class
* 
*
* ================================================================
*/
package com.jshx.core.base.dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;
import org.hibernate.Hibernate;

import java.sql.Types;

/**
 * MySQL数据库方言处理
 * 
 * @author Chenjian
 *
 */
public class MySQL5Dialect extends MySQLDialect {
    public MySQL5Dialect() {
        super();
        registerHibernateType(Types.LONGVARCHAR, StringType.INSTANCE.getName());
        registerHibernateType(Types.LONGVARCHAR, TextType.INSTANCE.getName());
    }

    protected void registerVarcharTypes() {
		registerColumnType( Types.VARCHAR, "longtext" );
		registerColumnType( Types.VARCHAR, 16777215, "mediumtext" );
		registerColumnType( Types.VARCHAR, 65535, "varchar($l)" );
		
	}
}
