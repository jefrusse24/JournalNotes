package menubar;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public enum MenuItem {
	
	FILE_NEW("File","New",MenuType.REGULAR,KeyEvent.VK_N,0),
	FILE_OPEN("File","Open...",MenuType.REGULAR,KeyEvent.VK_O,0),
	FILE_SAVE("File","Save",MenuType.REGULAR,KeyEvent.VK_S,0),
	FILE_SAVE_AS("File","Save as...",MenuType.REGULAR,KeyEvent.VK_S,InputEvent.SHIFT_DOWN_MASK),
	FILE_SEPERATOR_0("File","",MenuType.SEPERATOR,0,0),
	FILE_AUTOSAVE("File","Autosave",MenuType.CHECKBOX_CHECKED,0,0),

	EDIT_CUT("Edit","Cut",MenuType.REGULAR,KeyEvent.VK_X,0),
	EDIT_COPY("Edit","Copy",MenuType.REGULAR,KeyEvent.VK_C,0),
	EDIT_PASTE("Edit","Paste",MenuType.REGULAR,KeyEvent.VK_V,0),
	EDIT_SEPERATOR_0("Edit","",MenuType.SEPERATOR,0,0),
	EDIT_FIND("Edit","Find",MenuType.REGULAR,KeyEvent.VK_F,0),
	EDIT_FIND_NEXT("Edit","Find Next",MenuType.REGULAR,KeyEvent.VK_F,InputEvent.SHIFT_DOWN_MASK),
	EDIT_SEPERATOR_1("Edit","",MenuType.SEPERATOR,0,0),
	
	PAGE_NEW_PAGE("Page","New Page",MenuType.REGULAR,KeyEvent.VK_N,InputEvent.SHIFT_DOWN_MASK),
	PAGE_NEW_PAGE_AFTER("Page","New Page After",MenuType.REGULAR,0,0),
	PAGE_NEW_PAGE_BEFORE("Page","New Page Before",MenuType.REGULAR,0,0),
	PAGE_SEPERATOR_0("Page","",MenuType.SEPERATOR,0,0),
	PAGE_LOCK("Page","Lock Page",MenuType.CHECKBOX_UNCHECKED,0,0),
	PAGE_SEPERATOR_1("Page","",MenuType.SEPERATOR,0,0),
	PAGE_DELETE("Page","Delete Current Page",MenuType.REGULAR,0,0),
	
	FORMAT_REGULAR("Format","Regular",MenuType.REGULAR,KeyEvent.VK_R,0),
	FORMAT_BOLD("Format","Bold",MenuType.REGULAR,KeyEvent.VK_B,0),
	FORMAT_ITALICS("Format","Italics",MenuType.REGULAR,KeyEvent.VK_I,0),
	FORMAT_HEADING_A("Format","Heading A",MenuType.REGULAR,KeyEvent.VK_1,0),
	FORMAT_HEADING_B("Format","Heading B",MenuType.REGULAR,KeyEvent.VK_2,0),
	FORMAT_HEADING_C("Format","Heading C",MenuType.REGULAR,KeyEvent.VK_3,0),
	FORMAT_HEADING_D("Format","Heading D",MenuType.REGULAR,KeyEvent.VK_4,0),
	FORMAT_CODE("Format","Code",MenuType.REGULAR,KeyEvent.VK_M,0),
	FORMAT_SEPERATOR_0("Format","",MenuType.SEPERATOR,0,0),
	FORMAT_FORWARDS("Format","Bring Forwards",MenuType.REGULAR,0,0),
	FORMAT_BACKWARDS("Format","Send Backwards",MenuType.REGULAR,0,0),
	FORMAT_TO_FRONT("Format","Bring to Front",MenuType.REGULAR,0,0),
	FORMAT_TO_BACK("Format","Send to Back",MenuType.REGULAR,0,0),
	
	DRAWING_TEXT("Drawing","Text",MenuType.REGULAR,0,0),
	DRAWING_PENCIL("Drawing","Pencil",MenuType.REGULAR,0,0),
	DRAWING_SELECT("Drawing","Select",MenuType.REGULAR,0,0),
	DRAWING_SEPERATOR_0("Drawing","",MenuType.SEPERATOR,0,0),
	DRAWING_LINE("Drawing","Line",MenuType.RADIO_BUTTON,0,0),
	DRAWING_LINE_IENGTH("Drawing","Line with Length",MenuType.RADIO_BUTTON,0,0),
	DRAWING_LINE_ARROW("Drawing","Line with Arrow",MenuType.RADIO_BUTTON,0,0),
	DRAWING_LINE_DOUBLE_ARROW("Drawing","Line with Double Arrow",MenuType.RADIO_BUTTON,0,0),
	DRAWING_FILL("Drawing","Line with Length",MenuType.RADIO_BUTTON,0,0),
	DRAWING_SEPERATOR_1("Drawing","",MenuType.SEPERATOR,0,0),
	DRAWING_RECT("Drawing","Rectangle",MenuType.REGULAR,0,0),
	DRAWING_ELLIPSE("Drawing","Ellipse",MenuType.REGULAR,0,0),
	DRAWING_ROUND_RECTANGLE("Drawing","Round Rectangle",MenuType.REGULAR,0,0),
	DRAWING_MULTI_POINT("Drawing","Multi Point Line",MenuType.REGULAR,0,0),
	DRAWING_SEPERATOR_2("Drawing","",MenuType.SEPERATOR,0,0),
	DRAWING_WIDTH_1("Drawing","Line Width 1",MenuType.RADIO_BUTTON,0,0),
	DRAWING_WIDTH_2("Drawing","Line Width 2",MenuType.RADIO_BUTTON,0,0),
	DRAWING_WIDTH_5("Drawing","Line Width 5",MenuType.RADIO_BUTTON,0,0),

	;
	
	private final String menu, menuText;
	private int keyEvent,modifier;
	private MenuType menuType;
	private MenuItem(String m,String t, MenuType mt, int k, int mod) {
		menu = m;
		menuText = t;
		menuType = mt;
		keyEvent = k;
		modifier = mod;
	}
	
	public String getMenu() {
		return menu;
	}
	public String getMenuText() {
		return menuText;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public int getKeyEvent() {
		return keyEvent;
	}
	public int getModifier() {
		return modifier;
	}
}
