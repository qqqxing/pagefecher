package pagefetcher.util;

public class STRutil {

	public static boolean isNotEmptyString(String str) {
		if (str == null) {
			return false;
		}
		if (str.length() == 0) {
			return false;
		}

		return true;
	}

}
