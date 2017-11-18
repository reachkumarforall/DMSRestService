package com.hl.is.dms.rest.util;

public class JavaUtils {
	
	public static int nullSafeStringComparator(String one, String two){
		
		if(one == null && two == null)
			return 0;
		
		if(one == null || two == null)
			return (one == null) ? -1 : 1;
		
		return one.compareToIgnoreCase(two);
	}
}
