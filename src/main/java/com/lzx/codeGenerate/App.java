package com.lzx.codeGenerate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class App 
{
	private static final String TEMPLATE_PATH = "./src/main/java/com/lzx/resource/template";
    private static final String CLASS_PATH = "genc";
    
    public static void main(String[] args) {
    	List<String> s=new GetPojo().getTable();
    	
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        Writer out2 = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
         // step4 加载模版文件
            Template template = configuration.getTemplate("Controller.ftl");
            
            Template template2 = configuration.getTemplate("Service.ftl");
           
            for(int i=0;i<s.size();i++) {
            	 // step3 创建数据模型
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("Name", getUpperName(s.get(i)));
                dataMap.put("name", getLowName(s.get(i)));
                dataMap.put("prev", getPrevName(s.get(i)));
                dataMap.put("basePath", PropertiesUitls.basePath);
                
                File f=new File(CLASS_PATH+File.separator+getPrevName(s.get(i))+File.separator+"controller");
                if(!f.exists()) {
                	f.mkdirs();
                }
                
               // step5 生成数据
                File docFile = new File(f,getUpperName(s.get(i))+"Controller.java");
                if(!docFile.exists()) {
                	docFile.createNewFile();
                }
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                // step6 输出文件
                template.process(dataMap, out);
                
                File f2=new File(CLASS_PATH + File.separator+getPrevName(s.get(i))+File.separator+"service");
                if(!f2.exists()) {
                	f2.mkdirs();
                }
                
               File docFile2 = new File(f2,getUpperName(s.get(i))+"Service.java");
               out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile2)));
               // step6 输出文件
               template.process(dataMap, out);
            }
            
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private static String getUpperName(String s) {
    	return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    
    private static String getLowName(String s) {
    	return s.substring(0,1).toLowerCase()+s.substring(1);
    }
    
    private static String getPrevName(String s) {
    	if(s.length()>3) {
    		return s.substring(0,3);
    	}
    	return s;
    }

}

