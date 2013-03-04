package pagefetcher.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class SetupTask extends HttpServlet {

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
		String counterName = req.getParameter("counter");
		if(counterName == null){
			counterName = "default-counter";
		}
		Queue defaultQueue = QueueFactory.getDefaultQueue();
		TaskOptions to = TaskOptions.Builder.withUrl("/countworker")
				.method(Method.POST).param("countername", counterName);
		defaultQueue.add(to);

		PrintWriter writer = resp.getWriter();
		writer.append("add queue:" + counterName);
		writer.flush();
	}

}
