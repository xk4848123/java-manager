package com.nidecai.managerndc.common.codeutil;

public class RandomUtil {
	   public static String generateCode() {
	        Long codeL = System.nanoTime();
	        String codeStr = Long.toString(codeL);
	        String verifyCode = codeStr.substring(codeStr.length() - 6);
	        return verifyCode;
	    }
	}

