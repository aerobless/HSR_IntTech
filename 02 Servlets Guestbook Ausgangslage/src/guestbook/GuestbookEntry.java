package guestbook;

import java.io.*;
import java.util.*;

public class GuestbookEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;

	protected String mail;

	protected String comment;

	protected Date date;

	public GuestbookEntry() {
		date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean checkEntries() throws GuestbookException {
		if (name == null && mail == null && comment == null)
			return false;
		if (name == null || name.equals(""))
			throw new GuestbookException();
		if (mail == null || mail.equals(""))
			throw new GuestbookException();
		if (mail.indexOf("@") == -1)
			throw new GuestbookException();
		if (comment == null || comment.equals(""))
			throw new GuestbookException();
		return true;
	}

}
