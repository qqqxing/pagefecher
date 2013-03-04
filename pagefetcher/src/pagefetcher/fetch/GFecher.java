package pagefetcher.fetch;

import java.io.IOException;
import java.net.URL;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class GFecher implements Fetcher {

	@Override
	public HTTPResponse fetch(String urlString) throws IOException {
		URL url = new URL(urlString);

		HTTPResponse r = URLFetchServiceFactory.getURLFetchService().fetch(url);
		return r;
	}

}
