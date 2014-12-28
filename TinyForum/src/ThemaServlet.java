import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
    	
        HttpSession session = request.getSession();
        
        //Den Namen vom Request abrufen
        String name = request.getParameter("name");
        if (name != null) {
        	
        	//Den Namen als Attribut setzen
            session.setAttribute("name", name);
            
            //Ein Cookie mit dem Namen erstellen
            Cookie namensCookie = new Cookie("name", name);
            namensCookie.setMaxAge(Integer.MAX_VALUE);
            
            //Das Cookie an die Response anhängen
            response.addCookie(namensCookie);
        }
        printKommentarseite(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        
        //Den Namen aus der Session beziehen
        String name = (String) session.getAttribute("name");
        
        //Das Thema aus der Session beziehen
        String thema = (String) session.getAttribute("thema");
        
        //Den eingegeben Kommentar vom Request beziehen
        String eingabeKommentar = request.getParameter("kommentarEingabe");
        
        //Die Kommentare vom ServletContext (global) beziehen
        Vector kommentare = (Vector) getServletContext().getAttribute(thema);
        kommentare.add(name + ": " + eingabeKommentar);
        printKommentarseite(request, response);
    }

    private void printKommentarseite(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        //Das Thema von der Session beziehen
        String thema = (String) session.getAttribute("thema");
        
        //Die Kommentare vom ServletContext (global) beziehen.
        Vector kommentare = (Vector) getServletContext().getAttribute(thema); //request.getParameter("comment"); // TODO de Theo wirds fixe
        
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        
        out.println("<html><head><title>Diskussionsforum</title></head>");
        out.println("<body><h1>Diskussion zum Thema " + thema + ":</h1>");
        for(Object kommentar : kommentare) {
            out.println("<br>" + kommentar);
        }
        out.println("<form action=/TinyForum/thema method=POST>"
                + "Mein Kommentar:&nbsp;<input type=text size=40 name=kommentarEingabe>"
                + "<input type=submit " + "value=\"Fuege Kommentar von "
                
                //Den Benutzernamen von der Session beziehen
                + session.getAttribute("name") +
                " hinzu\"></form>");
        
        out.println("<form action=/TinyForum method=GET>"
                    +"<input type=submit value=\"Verlasse Thema\"></form>");
        out.println("</body></html>");
    }
}
