package com.lzx.codeGenerate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUitls {

	static String propertyName="../resource/project.properties";
	
	public static String jdbcUrl="";
	public static String jdbcDriver="";
	public static String jdbcUserName="";
	public static String jdbcPassword="";
	public static String basePath="";
	public static String selectSql="";
	
	static {
		InputStream in=PropertiesUitls.class.getResourceAsStream(propertyName);
		Properties p=new Properties();
		try {
			p.load(in);
			jdbcUrl=p.getProperty("jdbc.url");
			jdbcDriver=p.getProperty("jdbc.driver");
			jdbcUserName=p.getProperty("jdbc.username");
			jdbcPassword=p.getProperty("jdbc.password");
			basePath=p.getProperty("basepath");
			selectSql=p.getProperty("selectSql");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
