import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AnmeldungServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = "***";
		HttpSession session = request.getSession(true);
		String thema = request.getParameter("thema");
		Cookie[] cookies = request.getCookies();
		
		//Über alle Cookies iterieren
		if(cookies != null){
			for(int i = 0; i<cookies.length; i++){
				//Ein Cookie finden dass den Benutzernamen enthält
				if(cookies[i].getName().equals("name")){
					//Den Wert des Benutzernamen aus dem Cookie auslesen
					name = cookies[i].getValue();
					break;
				}
			}
		}
		
		//Das Thema wird als Session Attribut gesetzt.
		session.setAttribute("thema", thema);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>Diskussionsforum</title></head>");
		out.println("<body>"
				+"<h1>Anmeldung zum Thema "+thema +"</h1>"
				+"Geben Sie Ihren Namen ein und melden Sie sich an!");
		out.println("<form action=/TinyForum/thema method=GET>"
				+ "Name:&nbsp;<input type=text size=10 name=name value="
				+ name+"><input type=submit value=anmelden></form>");
		out.println("</body></html>");
		out.close();
	}
}
