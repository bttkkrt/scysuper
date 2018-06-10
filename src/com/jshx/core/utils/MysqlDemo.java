package com.jshx.core.utils;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
 
public class MysqlDemo {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://202.102.101.93:3306/yqzhaj?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
 
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
 
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "select table_name from information_schema.tables where table_schema='yqzhaj' and table_type='base table'";
            ResultSet  result = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            List<String> tables = new ArrayList<String>();
            while (result.next()) {
            	String tableName = result.getString(1).toString().trim();
            	tables.add(tableName);
            	
            }
            for (String tableName : tables) {
            	boolean hasClone = false;
            	String sql1 = "select count(*) from (select row_id from "+tableName+" group by row_id having count(row_id) > 1) as tb";
            	ResultSet  result2 = stmt.executeQuery(sql1);
            	while(result2.next()){
            		if(result2.getInt(1)>0){
            			hasClone = true;
            		}
            	}
            	if(hasClone){
            		String sql2 = "alter table "+tableName+" add column id int(11) PRIMARY KEY AUTO_INCREMENT";
            		String sql3 = "delete from "+tableName+" where id in (select id from (select  max(id) as id, count(row_id) as ucount from "+tableName+" group by row_id having ucount >1 order by ucount desc)as tab )";
            		String sql4 = "ALTER table "+ tableName+" DROP column id";
            		String sql5 = "ALTER table "+ tableName+" add PRIMARY KEY (row_id)";
            		boolean sql2flag = stmt.execute(sql2);
            		boolean sql3flag = stmt.execute(sql3);
            		boolean sql4flag = stmt.execute(sql4);
            		boolean sql5flag = stmt.execute(sql5);
            		System.out.println(sql2flag+"\n"+sql3flag+"\n"+sql4flag+"\n"+sql5flag+"\n");// 入如果返回的是int类型可以用getInt()
            	}
			}
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
 
    }
 
}
