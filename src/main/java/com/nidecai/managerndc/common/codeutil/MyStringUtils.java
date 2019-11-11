package com.nidecai.managerndc.common.codeutil;

import java.util.List;

/**
 * @author tianyu
 * @desc list<String> => string,string,string
 */
public class MyStringUtils {

	public static String listToString (List<Integer> list) {
		StringBuilder stringBuilder = new StringBuilder();
		Boolean tag = true;
		for (Integer singleString : list) {
			if (tag) {
				stringBuilder.append(singleString);
				tag =false;
			}else {
				stringBuilder.append(",").append(singleString);
			}
			
		}
		return stringBuilder.toString();
	}
	
	public static String StringlistToString (List<String> list) {
		StringBuilder stringBuilder = new StringBuilder();
		Boolean tag = true;
		for (String singleString : list) {
			if (tag) {
				stringBuilder.append(singleString);
				tag =false;
			}else {
				stringBuilder.append(",").append(singleString);
			}
			
		}
		return stringBuilder.toString();
	}
	
	
	public static void pushObject(Object[] array,Object object) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = object;
				break;
			}
		}
	}
}
