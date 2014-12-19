package menubar;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Preferences Dialog.
 * Current List of Preferences:
 * Use Database - 
 * DB URL - Database URL 
 * DB Username
 * DB Password
 * UUID - Universal Unique Identifier
 * Autosave - Autosave the document as you are working on it.
 * Autoload - Autoload the last saved document
 * Default Font - Helvetica 14 pt?
 * Heading A - Arial 20 Red?
 * 
 * @author jeffrus
 *
 */
public class Preferences implements ActionListener {

	private static final String RC_FILE = ".jNotes2rc";

	// These are the programs preferences that can be manipulated
	private static boolean useDatabase;
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;
	private static String uuid;
	private static boolean autoSave;
	private static boolean autoLoad;
	
	// Fields that we need to keep so we can read their values
	private JFrame prefFrame;
	private JCheckBox useDatabaseBox;
	private JTextField urlField;
	private JTextField dbUsernameField;
	private JPasswordField dbPasswordField;
	private JTextField uuidField;
	private JCheckBox autoSaveBox;
	private JCheckBox autoLoadBox;
	
	private static String	fileName = null;
	private static String	filePath = null;

	/**
	 * This will bring up a dialog and then wait for the user to hit OK, or CANCEL button
	 */
	public Preferences() {
		
		buildDialog();
	}
	
	private void buildDialog() {

		prefFrame = new JFrame("Preferences");
		Container contentPane = prefFrame.getContentPane();
//		contentPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		prefFrame.setLocationRelativeTo(null);
		prefFrame.setAlwaysOnTop(true);
		prefFrame.setVisible(false);
		GridBagConstraints c = new GridBagConstraints();

		useDatabaseBox = new JCheckBox("Use Database");
		c.gridwidth=2;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		contentPane.add(useDatabaseBox,c);

		// URL
		JLabel label = new JLabel("URL : ");
		c.gridwidth=1;
		c.gridx = 0;
		c.gridy++;
		contentPane.add(label,c);

		urlField = new JTextField(15);
		c.gridx = 1;
		contentPane.add(urlField,c);

		// DB Username
		label = new JLabel("DB Username : ");
		c.gridwidth=1;
		c.gridx = 0;
		c.gridy++;
		contentPane.add(label,c);

		dbUsernameField = new JTextField(15);
		c.gridx = 1;
		contentPane.add(dbUsernameField,c);

		// DB Password
		label = new JLabel("DB Password : ");
		c.gridwidth=1;
		c.gridx = 0;
		c.gridy++;
		contentPane.add(label,c);
		
		dbPasswordField = new JPasswordField(15);
		c.gridx = 1;
		contentPane.add(dbPasswordField,c);

		// UUID
		label = new JLabel("UUID : ");
		c.gridx = 0;
		c.gridy++;
		contentPane.add(label,c);

		uuidField = new JTextField(15);
		c.gridx = 1;
		contentPane.add(uuidField,c);

		autoSaveBox = new JCheckBox("Autosave");
		c.gridwidth=2;
		c.gridx = 0;
		c.gridy++;
		contentPane.add(autoSaveBox,c);
		
		autoLoadBox = new JCheckBox("Autoload");
		c.gridwidth=2;
		c.gridx = 0;
		c.gridy++;
		contentPane.add(autoLoadBox,c);

		JPanel buttFrame = new JPanel();
		buttFrame.setLayout(new FlowLayout());
		JButton button = new JButton("OK");
		button.addActionListener(this);
		buttFrame.add(button);
		button = new JButton("Cancel");
		button.addActionListener(this);
		buttFrame.add(button);
		c.gridwidth=2;
		c.gridx = 0;
		c.gridy++;
		contentPane.add(buttFrame,c);
	
		prefFrame.pack();
		
	}

	/**
	 * We need to set all of the pereferences to the current values and then show the window.
	 */
	public void showPrefs() {
		urlField.setText(dbUrl);
		uuidField.setText(uuid);
		dbUsernameField.setText(dbUsername);
		dbPasswordField.setText(dbPassword);
		autoSaveBox.setSelected(autoSave);
		autoLoadBox.setSelected(autoLoad);
		
		prefFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("OK".equals(e.getActionCommand())) {
			savePreferences();
		}
		prefFrame.setVisible(false);
	}

	private void savePreferences() {
		dbUrl = urlField.getText();
		uuid = uuidField.getText();
		autoSave = autoSaveBox.isSelected();
		autoLoad = autoLoadBox.isSelected();
		
		updateRcFile();
	}

	private void updateRcFile() {
		try {
			File f = new File(System.getProperty("user.home"), RC_FILE);
			FileOutputStream fos = new FileOutputStream(f);
			DataOutputStream out = new DataOutputStream(fos);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write("useDatabase="+useDatabase+"\n");
			bw.write("dbUrl="+dbUrl+"\n");
			bw.write("dbUsrename="+dbUsername+"\n");
			bw.write("dbPassword=¥¥¥¥¥¥¥¥"+"\n");	// TODO: Fix This. Store in keychain?
			bw.write("uuid="+uuid+"\n");
			bw.write("autoSave="+autoSave+"\n");
			bw.write("autoLoad="+autoLoad+"\n");
			bw.write("lastFile="+fileName+"\n");
			bw.write("lastDir="+filePath+"\n");
			bw.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("[JournalNotes: LocalDataManip] Problem updating RC file");
			e.printStackTrace();
		}


	}

	public static void setUseDatabase(boolean use) {
		useDatabase = use;
	}

	public static boolean isUseDatabase() {
		return useDatabase;
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public static void setDbUrl(String url) {
		dbUrl = url;
	}

	public static String getUuid() {
		if (uuid == null) {
			uuid = "1234-56-7890ABCD";
		}
		return uuid;
	}

	public static void setUuid(String id) {
		uuid = id;
	}

	public static boolean isAutoSave() {
		return autoSave;
	}

	public static void setAutoSave(boolean save) {
		autoSave = save;
	}

	public static boolean isAutoLoad() {
		return autoLoad;
	}

	public static void setAutoLoad(boolean autoLoad) {
		Preferences.autoLoad = autoLoad;
	}

	public static String getDbUsername() {
		return dbUsername;
	}

	public static void setDbUsername(String dbUsername) {
		Preferences.dbUsername = dbUsername;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	public static void setDbPassword(String dbPassword) {
		Preferences.dbPassword = dbPassword;
	}



}
