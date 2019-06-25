package com.lzx.codeGenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPojo {

	public List getTable() {
		Connection con=connect() ;
		try {
			if(con!=null) {
				List result=getAllTables(con);
				return result;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		
		return null;
	}
	
	public Connection connect() {
		try {
			Class.forName(PropertiesUitls.jdbcDriver);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(PropertiesUitls.jdbcUrl, PropertiesUitls.jdbcUserName, PropertiesUitls.jdbcPassword);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getAllTables(Connection connect) throws Exception{
		String sql=PropertiesUitls.selectSql;
	
		PreparedStatement ps=connect.prepareStatement(sql);
		ResultSet set=ps.executeQuery();
		List<String> result=new ArrayList<String>();
		while(set.next()) {
			result.add(set.getString(1));
		}
		return result;
		
	}
	
	public void closeConnection(Connection connect){
		if(connect!=null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		List<String> s=new GetPojo().getTable();
		System.out.println(s.size());
	}
}
