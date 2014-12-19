package menubar;

import java.awt.event.KeyEvent;

public enum FormatStyle {
	REGULAR("Regular","f_regular",KeyEvent.VK_R),
	HEADING1("Heading A","f_headinga",KeyEvent.VK_1),
	HEADING2("Heading B","f_headingb",KeyEvent.VK_2),
	HEADING3("Heading C","f_headingc",KeyEvent.VK_3),
	HEADING4("Heading D","f_headingd",KeyEvent.VK_4),
	CODE("Code","f_code",KeyEvent.VK_M),
	QUOTE("Quote","f_quote",KeyEvent.VK_I),
	BOLD("Bold","f_bold",KeyEvent.VK_B);
	
	private final String menuName, styleName;
	int key;
	private FormatStyle(String n, String f, int k) {
		menuName = n;
		styleName = f;
		key = k;
	}
	public String getMenuName() {
		return menuName;
	}
	public String getStyleName() {
		return styleName;
	}
	public String toString() {
		return menuName;
	}
	public int getKey() {
		return key;
	}
	
}
