package document.db;

import java.sql.Connection;
import java.sql.DriverManager;

import menubar.Preferences;
import document.JournalDocument;

public class DbReader {
	
	
	private JournalDocument theDoc;
	private static Connection dbConnection;
	
	public DbReader(String username, String password, String filename) {
		
		theDoc = JournalDocument.getTemporaryDocument();
		
		dbConnection = getDbConnection();
		if (dbConnection == null) {
			System.out.println("Could Not log into db");
			return;
		}
		
		boolean accessToNotebook = testNotebookAccess(username,password,filename);
		
	}

	/**
	 * This will attempt to see if we have sufficient access to read the specified notebook.
	 * @param username
	 * @param password
	 * @param filename
	 * @return
	 */
	private boolean testNotebookAccess(String username, String password, String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	private Connection getDbConnection() {
			if (dbConnection == null) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String dbInfo = "jdbc:mysql://" + Preferences.getDbUrl() + ":3306/SimpleMatrix";
					dbConnection = DriverManager
							.getConnection(dbInfo, Preferences.getDbUsername(), Preferences.getDbPassword());
					dbConnection.setAutoCommit(true);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			return dbConnection;
		}

	public JournalDocument getDocument() {
		return theDoc;
	}

}
