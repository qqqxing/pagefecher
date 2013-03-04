package pagefetcher.servlet.task;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class CountWorker extends HttpServlet {

	private final static Logger log = Logger.getLogger(CountWorker.class
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
		String counterName = req.getParameter("countername");
		int second = 15;
		while (second > 0) {
			second--;
			log.info("[ " + counterName + " ]:" + second);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (second == 1) {
				Queue defaultQueue = QueueFactory.getDefaultQueue();
				TaskOptions to = TaskOptions.Builder.withUrl("/countworker")
						.method(Method.POST)
						.param("countername", counterName + counterName);
				defaultQueue.add(to);
				log.info("[ " + counterName + " ]: task added.");
			}
		}
	}

}
