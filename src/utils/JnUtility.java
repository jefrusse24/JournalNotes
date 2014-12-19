package utils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class JnUtility {

	private static String m_iconPath = "";
//	private static String m_iconJar = "";
	
	
	private static void initIconPath () {
		if (m_iconPath.equals("")) {
			String path = JnUtility.class.getResource("/journalNotes.class").getPath();
			path = path.replace("/journalNotes.class", "/../images/");

			File fPath = new File(path);
			if (fPath.exists() && fPath.isDirectory())
				m_iconPath = "file:"+path;

			else {

				URL jarurl = JnUtility.class.getProtectionDomain().getCodeSource().getLocation();
				//Example: jar:file:/c:/almanac/my.jar!/com/mycompany/MyClass.class            
				m_iconPath = "jar:"+jarurl+"!/images/";
			}

			System.out.println("Icon Path is set to :"+m_iconPath);


		}

	}
	
	public static String getIconPath() {

		initIconPath();
		URL imageUrl = null;
		try {
			imageUrl = new URL(m_iconPath);
		} catch (MalformedURLException e) {
			System.err.println("Can not determine file.  iconPath: "+m_iconPath);
			return null;
		}

		return imageUrl.getPath();
	}
	
	public static URL getIconPath(String icon) {
		initIconPath();

		URL imageUrl = null;
		try {
			imageUrl = new URL(m_iconPath+icon);
		} catch (MalformedURLException e) {
			System.err.println("Can not determine file.  iconPath "+m_iconPath+" icon: "+icon);
		}
		return imageUrl;
		
	}
}
