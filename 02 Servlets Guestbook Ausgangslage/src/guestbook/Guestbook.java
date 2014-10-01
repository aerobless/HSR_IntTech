package guestbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class Guestbook {

	protected Vector<GuestbookEntry> entries = new Vector<GuestbookEntry>();
	protected String guestbookFilename = "/Users/theowinter/test/test.ser"; // auf Windows "c:\\temp\\guestbook.ser"
	protected File guestbookFile;

	@SuppressWarnings("unchecked")
	public Guestbook() {
		guestbookFile = new File(guestbookFilename);
		if (guestbookFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(guestbookFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				entries = (Vector<GuestbookEntry>) objectIn.readObject();
				objectIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addEntry(GuestbookEntry entry) throws IOException, GuestbookException {
		if (entry.checkEntries()) { // wirft GuestbookException bei Fehler
			entries.insertElementAt(entry, 0);
			save();
		}
	}

	public Vector<GuestbookEntry> getEntries() {
		return entries;
	}

	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(guestbookFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(entries);
			objectOut.flush();
			objectOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
