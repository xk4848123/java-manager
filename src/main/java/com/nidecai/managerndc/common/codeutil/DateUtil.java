package com.nidecai.managerndc.common.codeutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateUtil {
	public static int getTimeBeforeDay(int days) {
		int currentTime = (int) (System.currentTimeMillis() / 1000);
		int seconds = days * 24 * 3600;
		return currentTime - seconds;
		
	}

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		System.out.println(set);
		set.removeAll(list);
		System.out.println(set);
	}
}
