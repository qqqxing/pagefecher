package pagefetcher.fetch;

import java.io.IOException;

import com.google.appengine.api.urlfetch.HTTPResponse;

public interface Fetcher {

	public HTTPResponse fetch(String urlString) throws IOException;
}
