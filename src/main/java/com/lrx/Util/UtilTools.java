package com.lrx.Util;

public class UtilTools {
	/**
	 * <p>
	 * [去除SQL注入的的通配符漏洞]
	 * </p>
	 * 
	 * @comment [注释说明]
	 * @author gaishm, 2013-3-23
	 * 
	 * @param args
	 */
	public static String getEscWildCards(String str) {
		return str.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
	}
	
	public static String getNotNull(String str){
		if (str==null){
			return str;
		}
		return str;
		
	}
}
