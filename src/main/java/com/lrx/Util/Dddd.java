package com.lrx.Util;

public class Dddd {
	
	public  static void main(String[] args){
		String abcd="${operate == 1}";
		String key="";
		String value="";
		int preInsPos;
		int nextInsPos;
		preInsPos=abcd.indexOf("{");
		if (preInsPos>0){
			nextInsPos=abcd.indexOf("=");
			key=abcd.substring(preInsPos+1, nextInsPos);
			key=key.trim();
			preInsPos=abcd.indexOf("=", -1);
			if (preInsPos>0){
				nextInsPos=abcd.indexOf("}",-1);
				value=abcd.substring(preInsPos+2, nextInsPos);
				value=value.trim();
			}
		}
		
		
		
		
	}
}
