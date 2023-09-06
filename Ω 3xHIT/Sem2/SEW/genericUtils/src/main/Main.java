package main;

import java.util.Arrays;
import genericUtils.GenericUtils;

/**
 * Testet die genericUtils
 * @author Christof Zlabinger
 * @version 2023-02-15
 */
public class Main {

	/**
	 * Testet die genericUtils
	 * @param args nicht relevant
	 */
	public static void main(String[] args) {
		String[] s = {"a", "b", "c", "d", "e"};
		s = GenericUtils.umdrehen(s);
		System.out.println(Arrays.toString(s));
	}

}
