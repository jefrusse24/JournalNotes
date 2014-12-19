package menubar;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * Menu Items:
 * 
 * @author jeffrus
 *
 */
public class JournalMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public JournalMenu() {
		super();
		
//		MenuOsController macController = new MenuOsController();
//		MRJApplicationUtils.registerAboutHandler(macController);
//		MRJApplicationUtils.registerPrefsHandler(macController);
//		MRJApplicationUtils.registerQuitHandler(macController);

//		PreferencesHandler a;
		init();
	}

	private void init() {
		
		String vers = System.getProperty("os.name").toLowerCase();
		int modifier =InputEvent.CTRL_DOWN_MASK; // Default for windows
		if (vers.indexOf("mac") != -1) {
	    	modifier = InputEvent.META_DOWN_MASK;
	    }

		MenuListener mListener = new MenuListener();
		

		MenuItem[] mis = MenuItem.values();
		String menuName = "";
		JMenu jmenu = null;
		ButtonGroup buttonGroup = null;
		for (MenuItem mi : mis) {

			if (! mi.getMenu().equals(menuName)) {
				
				// Add Prefs (for non-mac)
				if (vers.indexOf("mac") == -1) {
					if ("Edit".equals(menuName)) {
						JMenuItem menuItem = new JMenuItem("Preferences");
						menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA,modifier));
						menuItem.addActionListener(mListener);
						jmenu.add(menuItem);
					}
				}				
				
				menuName = mi.getMenu();
				jmenu = new JMenu(menuName);
				this.add(jmenu);
				buttonGroup = null;
			}
			
			switch (mi.getMenuType()) {
			case REGULAR:
				JMenuItem menuItem = new JMenuItem(mi.getMenuText());
				if (mi.getKeyEvent() != 0) 
					menuItem.setAccelerator(KeyStroke.getKeyStroke(mi.getKeyEvent(),modifier | mi.getModifier()));
				menuItem.addActionListener(mListener);
				jmenu.add(menuItem);
				break;
			case CHECKBOX_CHECKED: {
				JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem(mi.getMenuText());
				if (mi.getKeyEvent() != 0) 
					cbMenuItem.setAccelerator(KeyStroke.getKeyStroke(mi.getKeyEvent(),modifier | mi.getModifier()));
				cbMenuItem.addItemListener(mListener);
				cbMenuItem.setSelected(true);
				jmenu.add(cbMenuItem);
				break;
			}
			case CHECKBOX_UNCHECKED: {
				JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem(mi.getMenuText());
				cbMenuItem.setMnemonic(mi.getModifier());
				cbMenuItem.addItemListener(mListener);
				cbMenuItem.setSelected(false);
				jmenu.add(cbMenuItem);
				break;
			}
			case RADIO_BUTTON :
				JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(mi.getMenuText());
				if (mi.getKeyEvent() != 0) 
					rbMenuItem.setAccelerator(KeyStroke.getKeyStroke(mi.getKeyEvent(),modifier | mi.getModifier()));
				if (buttonGroup != null) {
					buttonGroup.add(rbMenuItem);
					rbMenuItem.setSelected(false);
				} else {
					buttonGroup = new ButtonGroup();
					buttonGroup.add(rbMenuItem);
					rbMenuItem.setSelected(true);
				}
				rbMenuItem.addItemListener(mListener);
				jmenu.add(rbMenuItem);
				break;
			case SEPERATOR:
				buttonGroup = null;
				jmenu.addSeparator();
				break;
			}

		}
		
		
//		
//		JMenu menuFie, menuEdit, menuPage, menuFormat;
//		JMenuItem menuItem;
//		JCheckBoxMenuItem cbMenuItem;
//
//		//Build the first menu.
//		menuFie = new JMenu("File");
//		menuFie.setMnemonic(KeyEvent.VK_F);
//		this.add(menuFie);
//
//		//a group of JMenuItems
//		menuItem = new JMenuItem("New",KeyEvent.VK_N);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, modifier));
//		menuItem.addActionListener(mListener);
//		menuFie.add(menuItem);

	}
	
}
