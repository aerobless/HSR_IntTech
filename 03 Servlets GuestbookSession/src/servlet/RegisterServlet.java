package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	   RequestDispatcher view = req.getRequestDispatcher("/W");
	   view.forward(req, resp);  
		
	//	printHeader(request, response);
	//	printBody(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void printHeader(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");
		out.println("<title>Guestbook Registration</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Guestbook Registration</h1>");
		HttpSession session = req.getSession(true);
		String message = (String) session.getAttribute("message");
		if (message != null){
			out.println("<p>" + message + "</p>");
			session.removeAttribute("message");
		}
	}
	
	protected void printBody(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<body>");
		out.println("<h:panelGrid border=\"1\" columns=\"2\">");
		out.println("<h:outputText value=\"Name\"></h:outputText> <h:inputText></h:inputText>");
		out.println("<h:outputText value=\"Firstname\"></h:outputText> <h:inputText></h:inputText>");
		out.println("<h:outputText value=\"Email:\"></h:outputText> <h:inputText></h:inputText>");
		out.println("<h:outputText value=\"Username:\"></h:outputText> <h:inputText></h:inputText>");
		out.println("<h:outputText value=\"Password:\"></h:outputText> <h:inputText></h:inputText>");
		out.println("<h:outputText value=\"Verify Password:\"></h:outputText> <h:inputText></h:inputText>");							
		out.println("</h:panelGrid>");
		out.println("<h:form>");
		out.println("	<h:commandButton value=\"register\" action=\"register\"></h:commandButton>");
		out.println("</h:form>");
		out.println("</body>");
	}
}
