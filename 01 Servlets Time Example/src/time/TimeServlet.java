package time;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class TimeServlet
 */
@WebServlet("/TimeServlet")
public class TimeServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		Date date = new Date();
		PrintWriter out = response.getWriter();
		out.println(date);
	}
}
