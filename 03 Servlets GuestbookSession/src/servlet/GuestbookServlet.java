package servlet;

import guestbook.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class GuestbookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig aConfig) throws ServletException {
		super.init(aConfig);
		Guestbook guestbook = (Guestbook) getServletContext().getAttribute("guestbook");
		if (guestbook == null){
			guestbook = new Guestbook();
			getServletContext().setAttribute("guestbook", guestbook);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html");
		printHeader(req, res);
		printForm(res, req);
		printEntries(res);
		printFooter(res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			GuestbookEntry entry = new GuestbookEntry();
			entry.setName(req.getParameter("name"));
			entry.setMail(req.getParameter("mail"));
			entry.setComment(req.getParameter("comment"));
			((Guestbook) getServletContext().getAttribute("guestbook")).addEntry(entry);
			req.getSession(true).setAttribute("message", "Your entry has been added to the guestbook.");
			res.sendRedirect(req.getRequestURI());
		} catch (GuestbookException e) {
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			printHeader(req, res);
			printForm(res, req);
			out.println("<p>At least one field is missing or incorrect.</p>");
			printEntries(res);
			printFooter(res);
		}
	}

	protected void printHeader(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");
		out.println("<title>Guestbook</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Guestbook</h1>");
		HttpSession session = req.getSession(true);
		String message = (String) session.getAttribute("message");
		if (message != null){
			out.println("<p>" + message + "</p>");
			session.removeAttribute("message");
		}
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
		out.println("<form method=\"post\" action=\""
				+ req.getRequestURI() + "\">");
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

		SimpleDateFormat formatter = new SimpleDateFormat(
				"'On' dd.MM.yyyy 'at' HH:mm");
		for (GuestbookEntry entry : ((Guestbook) getServletContext().getAttribute("guestbook")).getEntries()) {
			out.println("<hr />" + "<p>" + formatter.format(entry.getDate())
					+ " <a href=\"mailto:" + entry.getMail() + "\">"
					+ entry.getName() + "</a> wrote:<br />"
					+ entry.getComment() + "</p>");
		}
	}
}