package model;

import java.util.Comparator;

public class CompareByProductIdDesc implements Comparator<String> {

	@Override
	public int compare(String str1, String str2) {
		
		return str2.compareToIgnoreCase(str1);
	}

}
