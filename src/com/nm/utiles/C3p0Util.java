package com.nm.utiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Util {
	//私有化静态常量
	private static ComboPooledDataSource dataSource=new ComboPooledDataSource();//单例模式
	private static QueryRunner qr=new QueryRunner(dataSource);
	//私有化构造函数
	private C3p0Util(){
		
	}
	//提供一个外部静态的访问方法
	public static ComboPooledDataSource getInstance(){	
		return dataSource;
	}
	
	//获取连接的方法
	public static Connection getConn() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * 通用的查询多条数据的方法
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T>List<T> queryList(String sql,Class<T> t,Object...params)
			throws SQLException{
		List<T> list=qr.query(sql, new BeanListHandler<T>(t), params);
		
		return list;
	}
	/**
	 * 查询一个对象的方法
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T>T queryOne(String sql,Class<T> t,Object...params) 
			throws SQLException{
		T entity=qr.query(sql, new BeanHandler<T>(t), params);
		return entity;
	}
	/**
	 * 查询一个数值型的数据的方法
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T>T queryNumber(String sql,Class<T> t,Object...params) 
			throws SQLException{
		T entity=qr.query(sql, new ScalarHandler<T>(), params);
		return entity;
	}
	/**
	 * 通用的执行更新数据的方法，包括增加、修改、删除
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int update(String sql,Object...params)
			throws SQLException{
		int rows=qr.update(sql, params);
		return rows;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConn());
	}
}
