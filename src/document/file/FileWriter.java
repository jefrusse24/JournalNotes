package document.file;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import menubar.Preferences;
import document.JournalDocument;
import document.Page;

/**
 * This will handle the Menu calls of "Save" and "Save As"
 * 
 * @author jeffrus
 * 
 */
public class FileWriter {

	static class LocalPromptData {
		String file;
		String path;
	}

	/**
	 * This will save the current open file.
	 * 
	 * @return
	 */
	public static boolean fileSave() {
		if ((FileReader.getFilename() == null) || (FileReader.getFilename().equals(""))) {
			return fileSaveAs();
		}
		
		LocalPromptData pd = new LocalPromptData();
		pd.file = FileReader.getFilename();
		pd.path = FileReader.getPath();
		saveIt(pd);

		return true;
	}

	public static boolean fileSaveAs() {

		LocalPromptData pd = getPromptSave(new Frame(), "Save ...", ".\\", "*.jrjn");
		saveIt(pd);
		return true;
	}

	public static void fileSaveTemporary() {

	}

	private static boolean saveIt(LocalPromptData pd) {

		File f = new File(pd.path, pd.file);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			JournalDocument theDoc = JournalDocument.getInstance();

			// Document Version
			oos.writeInt(JournalDocument.FILE_VERSION);

			oos.writeObject(Preferences.getUuid());

			oos.writeLong(theDoc.getLastSync().getTimeInMillis());

			oos.writeLong(theDoc.getLastModified().getTimeInMillis());

			oos.writeInt(theDoc.getNumPages());

			for (Page p : theDoc.getPages()) {
				oos.writeObject(p);
			}

			oos.flush();
			oos.close();
			
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private static LocalPromptData getPromptSave(Frame f, String title, String defDir,
			String fileType) {
		FileDialog fd = new FileDialog(f, title, FileDialog.SAVE);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setLocation(50, 50);
		fd.setVisible(true);
		LocalPromptData pd = new LocalPromptData();
		pd.file = fd.getFile();
		pd.path = fd.getDirectory();
		return pd;
	}

}
