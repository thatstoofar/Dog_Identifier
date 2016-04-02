package jonathan.dogidentifer.expert;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static void copy(List<String> dest, List<String> src) {
		for (String s:src) {
			dest.add(s);
		}
	}
	public static List<String> and(List<String> l1, List<String> l2) {
		List<String> result = new ArrayList<String>();
		
		for (String s1:l1) {
			for (String s2:l2) {
				if (s1.equals(s2)) {
					result.add(s1);
				}
			}
		}
		
		return result;
	}
}
