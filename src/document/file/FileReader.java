package document.file;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;

import menubar.Preferences;
import document.JournalDocument;
import document.Page;
import document.file.FileWriter.LocalPromptData;

public class FileReader {

	private static JournalDocument theDoc;
	
	private static String path;
	private static String filename;
	
	public static JournalDocument fileOpen() {
		
		// Get the file to read from the preferences.
		// This will generate a Document.  It is then up to the Merger to determine what pages make up the
		// final document.
		
		theDoc = JournalDocument.getTemporaryDocument();
		
		filename = "";
		path = "";
		
		LocalPromptData ldp = getPromptLoad(new Frame(), "Load...", ".\\", "*.jrjn");
		
		File f = new File(ldp.path,ldp.file);
		if (! f.exists())
			return null;

		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			int fileVer = ois.readInt();
			if (fileVer != JournalDocument.FILE_VERSION) {
				ois.close();
				System.out.println("Wrong File Version.");
				return null;
			}
			
			theDoc.setUuidReader(Preferences.getUuid());
			Object o = ois.readObject();
			if (o.getClass().equals(String.class)) {
				// Do Nothing.  This is the UUID.
			}
			
			Long timeStamp = ois.readLong();
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(timeStamp);
			theDoc.setLastSync(c);
			
			timeStamp = ois.readLong();
			c.setTimeInMillis(timeStamp);
			theDoc.setLastModified(c);

			int numPages = ois.readInt();

			readPages(numPages, ois);
			
			ois.close();

			// If it is successful, we can set the filename and path
			path = ldp.path;
			filename = ldp.file;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theDoc;
		
		
	}

	/**
	 * This will read the list of pages into the document.
	 * @param ois
	 * @throws Exception
	 */
	private static void readPages(int numPages, ObjectInputStream ois) throws Exception {
		
		System.out.println("Going to read Page Count: "+numPages);
		int curPage = 0;
		boolean done = false;
		try {
		while (! done) {
			Object page = ois.readObject();
			if (!page.getClass().equals(Page.class)) {
				System.err.println("Errors Here");
			} else {
				theDoc.addPage((Page)page);
			}
			curPage++;
			if (curPage >= numPages)
				done = true;
			System.out.println("Read page "+curPage);
		}
		} catch (Exception e) {
			e.printStackTrace();
			Page p = new Page(curPage+1);
			p.setPageTitle("ERROR Reading Page");
			theDoc.addPage(p);
		}
	}
	
	public JournalDocument getDocument() {
		return theDoc;
	}
	
	
	private static LocalPromptData getPromptLoad(Frame f, String title, String defDir, String fileType) {
		FileDialog fd = new FileDialog(f, title, FileDialog.LOAD);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setLocation(50, 50);
		fd.setVisible(true);
		LocalPromptData pd = new LocalPromptData();
		pd.file = fd.getFile();
		pd.path = fd.getDirectory();
		return pd;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		FileReader.path = path;
	}

	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		FileReader.filename = filename;
	}

	
}
