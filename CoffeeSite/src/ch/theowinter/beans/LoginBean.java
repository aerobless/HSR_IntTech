package ch.theowinter.beans;
import java.util.Map;
import ch.theowinter.data.Member;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * This is the LoginBean of CoffeeSite, a rough representation of
 * the IntTe exam of FS2014.
 */

@ManagedBean
@SessionScoped
public class LoginBean {
	private String username;
	private String password;
	
	private Map<String, Member> members;
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String login() {
		if(!members.containsKey(username)){
			return null;
		}
		
		if(!members.get(username).getPassword().equals(password)){
			return null;
		}
		
		return "/NextSite.xhtml";
	}
}
