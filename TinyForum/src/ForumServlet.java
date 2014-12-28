import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Vector themen = new Vector();
	
	public void init() throws ServletException {
		String zeile = null;
		Enumeration initParameterNamen = getInitParameterNames();
		String initParameterName = null;
		while(initParameterNamen.hasMoreElements()){
			initParameterName = (String) initParameterNamen.nextElement();
			if(initParameterName.startsWith("Thema")){
				String thema = getInitParameter(initParameterName);
				themen.add(thema);
				Vector kommentare = new Vector();
				getServletContext().setAttribute(thema, kommentare);
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Diskussionsforum</title></head>");
		out.println("<body><h1>Diskussionsforum</h1>" + "Wählen Sie ein Thema zu Mitdiskutieren: <br>");
		
		for(Object thema : themen){
			out.println("<a href="
					+"/TinyForum/anmeldung?thema="+thema
					+">"+thema+"</a><br>");
		}
		out.println("</body></html>");
	}
}
