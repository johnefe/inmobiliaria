package com.proinsalud.sistemas.core.util.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.proinsalud.sistemas.core.security.model.Option;

/**
 * @author Andres Santacruz
 * @datetime 7/02/2018 - 4:57:23 p. m.
 *
 */
public class OptionComparator {
	
	public static Comparator<Option> byName = (pa, pb) -> pa.getName().compareTo(pb.getName());

//	private static Comparator<Option> COMPARE_BY_NAME = new Comparator<Option>() {
//		public int compare(Option one, Option other) {
//			return one.getName().compareTo(other.getName());
//		}
//	};
	
	public static void organizedOptionsAsc(List<Option> unorder) {
		Collections.sort(unorder, byName);
		for (Option opt : unorder) {
			if (!opt.getOptions().isEmpty()) {
				organizedOptionsAsc(opt.getOptions());
			}
		}
	}
}
