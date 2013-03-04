package pagefetcher.util.test;


import junit.framework.Assert;

import org.junit.Test;

import pagefetcher.util.ERUtil;

public class ERUtilTest {

	@Test
	public void testERUtil() {
		String pattern = "charset=(.+)";
		String target = "text/html;charset=utf-8";
		int index = 1;

		String ans = ERUtil.extractFromString(pattern, target, index);

		Assert.assertTrue("utf-8".equalsIgnoreCase(ans));

	}
}
