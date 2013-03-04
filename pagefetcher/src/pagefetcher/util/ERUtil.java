package pagefetcher.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ERUtil {

	public static String extractFromString(String pattern, String target,
			int groupIndex) {

		if (pattern == null || target == null || groupIndex < 0) {
			return null;
		}

		Pattern p = Pattern.compile(pattern);

		Matcher m = p.matcher(target);
		if (m.find()) {
			return m.group(groupIndex);
		}
		return null;
	}

}
