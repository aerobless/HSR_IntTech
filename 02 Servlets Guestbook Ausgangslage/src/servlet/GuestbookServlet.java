package servlet;

import guestbook.Guestbook;
import guestbook.GuestbookEntry;
import guestbook.GuestbookException;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestbookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Guestbook guestbook = new Guestbook();

	public void init(ServletConfig aConfig) throws ServletException {
		super.init(aConfig);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html");

		printHeader(res);
		printForm(res, req);
		printEntries(res);
		printFooter(res);
	}

	// TODO
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		
		// hier ergaenzen ******************************************
		res.setContentType("text/html");
		
		System.out.println(req.getParameter("name"));
		System.out.println(req.getParameter("mail"));
		System.out.println(req.getParameter("comment"));
		
		GuestbookEntry newEntry = new GuestbookEntry();
		newEntry.setName(req.getParameter("name"));
		newEntry.setMail(req.getParameter("mail"));
		newEntry.setComment(req.getParameter("comment"));
		
		try {
			guestbook.addEntry(newEntry);
		} catch (GuestbookException anEx) {
			anEx.printStackTrace();
		}
		
		res.setContentType("text/html");
		printHeader(res);
		printForm(res, req);
		printEntries(res);
		printFooter(res);
	}
	
	protected void printHeader(HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");
		out.println("<title>Guestbook</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Guestbook</h1>");
	}

	protected void printFooter(HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<hr />");
		out.println("</body>");
		out.println("</html>");
	}

	protected void printForm(HttpServletResponse res, HttpServletRequest req)
			throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<form method=\"post\" action=\"" + removeQueryString(req.getRequestURI()) + "\">");
		out.println("<table border=\"0\">");
		out.println("<tr><td>Name</td>");
	    out.println("<td><input type=\"text\" name=\"name\" size=\"30\" maxlength=\"60\" /></td></tr>");
	    out.println("<tr><td>Email</td>");
	    out.println("<td><input type=\"text\" name=\"mail\" size=\"30\" maxlength=\"60\" /></td></tr>");
		out.println("<tr><td>Comment</td>");
		out.println("<td><textarea name=\"comment\" rows=\"5\" cols=\"30\"></textarea></td></tr>");
		out.println("<tr><td></td><td><input type=\"submit\" value=\"Add your entry.\" /></td></tr>");
		out.println("</table>");
		out.println("</form>");
	}

	protected void printEntries(HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		
		SimpleDateFormat formatter = new SimpleDateFormat("'On' dd.MM.yyyy 'at' HH:mm");
		for(GuestbookEntry entry : guestbook.getEntries()) {
			out.println("<hr />" + "<p>" + formatter.format(entry.getDate())
					+ " <a href=\"mailto:" + entry.getMail() + "\">"
					+ entry.getName() + "</a> wrote:<br />" + entry.getComment() + "</p>");
		}
	}

	protected static String removeQueryString(String aURI) {
		int i = aURI.indexOf('?');
		if(i != -1) {
			aURI = aURI.substring(0, i);
		}
		return aURI;
	}

	public void destroy() {
		super.destroy();
	}
}