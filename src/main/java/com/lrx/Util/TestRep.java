package com.lrx.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRep {
	public static void main(String[] args) {

		String abc = "if((($TEST-YC-TG1-35004-SYDY$==1.5) && "
				+ "     ($TEST-YC-TG1-35004-JYSJ$>=30) &&  "
				+ "     ($TEST-YC-TG1-35004-FDL$<500)) ||  "
				+ "    (($TEST-YC-TG1-35004-SYDY$==1.3) && "
				+ "     ($TEST-YC-TG1-35004-JYSJ$>=30) &&   "
				+ "     (100<300))){    " + " 	ResultValue=true; " + " }else{ "
				+ "	ResultValue=false; " + " } ";

		String rexp = "\\([^(](.*?)\\)";
		String pexp2="\\$(.*?)\\$";
		Pattern p = Pattern.compile(rexp);
		Pattern p2=Pattern.compile(pexp2);
		
		Matcher m = p.matcher(abc);
		boolean result = m.find();
		StringBuffer sb = new StringBuffer(); 
		int i=0;
		 while(result) {   
		        i++; 
		        String matchStr=m.group(0);
		        Matcher m2 = p2.matcher(matchStr);
		        boolean result2 = m2.find();
		        if( result2){
		        m.appendReplacement(sb, "(1=1)");   
		        }
		        //		      将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里  
		        //		      继续查找下一个匹配对象   
		        result = m.find();   
		        }   
		 
		 	m.appendTail(sb); 
	}
}
