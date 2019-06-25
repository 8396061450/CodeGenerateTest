package com.lzx.codeGenerate;

import java.io.File;

public class FileUtils {

	public static String[] getFolderFiles(String folder){
		File file=new File(folder);
		
		if(file.isDirectory() && file.exists()) {
			return file.list();
		}
		
		return null;
	}
	
	public static void deleteFiles(String filename) {
		File file=new File(filename);
		if(file.exists() && file.isFile()) {
			file.delete();
		}else if(file.exists() && file.isDirectory()){
			String[] files=getFolderFiles(filename);
			for(String s:files) {
				deleteFiles(filename+File.separator+s);
			}
			file.delete();
		}
	}
	
	public static void main(String[] args) {
		String folder="gene";
		FileUtils.deleteFiles(folder);
	}
}
