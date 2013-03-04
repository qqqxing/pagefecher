package pagefetcher.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.urlfetch.HTTPResponse;

import pagefetcher.fetch.Fetcher;
import pagefetcher.fetch.GFecher;
import pagefetcher.util.PageUtil;

public class TestFetcher extends HttpServlet {

	private static final Logger log = Logger.getLogger(TestFetcher.class
			.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Fetcher f = new GFecher();
		String url = "http://www.163.com";
		HTTPResponse r = f.fetch(url);
		log.info("Fetch " + url + " state : " + r.getResponseCode());

		String content = PageUtil.pageToString(r);
		String charset = PageUtil.getPageContentType(r);
		System.out.println(charset);
		System.out.println(content);
		PrintWriter writer = resp.getWriter();
		writer.append(content);
		writer.flush();

	}

}
