package guestbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class UserManager {
	protected Hashtable<String, User> users = new Hashtable<String, User>();

	protected String userFilename = "/Users/theowinter/test/users.ser"; // auf Windows "c:\\temp\\users.ser"

	protected File userFile;

	@SuppressWarnings("unchecked")
	public UserManager() {
		userFile = new File(userFilename);
		if (userFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(userFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				users = (Hashtable<String, User>) objectIn.readObject();
				objectIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean register(User user) throws UserException {
		if (users.get(user.getLogin()) != null) { // User existiert bereits
			throw new UserException();
		}
		
		if (user.checkEntries()) { // wirft UserException bei Fehler
			users.put(user.getLogin(), user);
			save();
			return true;
		}
		return false;
	}

	public User login(String login, String password) throws UserException {
		if ((login == null && password == null)
				|| (login == "" && password == "")) {
			return null;
		}
		User user = users.get(login);
		if (user != null && user.checkPassword(password)) {
			return user;
		}
		throw new UserException();
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(userFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(users);
			objectOut.flush();
			objectOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
