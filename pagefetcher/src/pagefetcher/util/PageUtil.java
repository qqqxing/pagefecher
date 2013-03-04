package pagefetcher.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPResponse;

public class PageUtil {

	private static final String CONTENT_TYPE_ER_PATTERN = "charset=(.+)";
	private static final int CONTENT_TYPE_ER_INDEX = 1;

	private static final String DEFAULT_CONTENT_ENCODE_CHARSET = "utf-8";

	public static String getPageContentType(HTTPResponse resp) {

		List<HTTPHeader> headers = resp.getHeaders();
		for (HTTPHeader header : headers) {
			if (header.getName().contains("Content-Type")) {
				return ERUtil.extractFromString(CONTENT_TYPE_ER_PATTERN,
						header.getValue(), CONTENT_TYPE_ER_INDEX);
			}
		}
		return null;
	}

	public static String pageToString(HTTPResponse resp)
			throws UnsupportedEncodingException {

		String charset = getPageContentType(resp);
		if (charset == null) {
			charset = DEFAULT_CONTENT_ENCODE_CHARSET;
		}

		byte[] data = resp.getContent();
		if (data == null) {
			return null;
		}

		return new String(data, charset);
	}

}
