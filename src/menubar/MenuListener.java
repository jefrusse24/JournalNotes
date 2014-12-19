package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JMenuItem;

import page.PagePanel;
import document.JournalDocument;
import document.file.FileReader;
import document.file.FileWriter;

public class MenuListener implements ActionListener, ItemListener {

	public MenuListener() {
		super();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String menuText = ((JMenuItem)(e.getSource())).getText();
		System.out.println("MenuSelected: "+menuText);
		//
		// File Menu Item
		//
		if (MenuItem.FILE_OPEN.getMenuText().equals(menuText)) {
			JournalDocument jd = FileReader.fileOpen();
			JournalDocument.setDocument(jd);
			return;
		}
		
		if (MenuItem.FILE_NEW.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.FILE_SAVE_AS.getMenuText().equals(menuText)) {
			FileWriter.fileSaveAs();
			return;
		}
		
		if (MenuItem.FILE_SAVE.getMenuText().equals(menuText)) {
			FileWriter.fileSave();
			return;
		}
		
		//
		// Edit Menu Item
		//
		if (MenuItem.EDIT_CUT.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.EDIT_COPY.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.EDIT_PASTE.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.EDIT_FIND.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.EDIT_FIND_NEXT.getMenuText().equals(menuText)) {
			return;
		}
		
		// Special non-mac preferences
		if ("Preferences".equals(menuText)) {
			Preferences prefs = new Preferences();
			prefs.showPrefs();
		}

		//
		// Page Menu Item
		//
		if (MenuItem.PAGE_NEW_PAGE.getMenuText().equals(menuText)) {
			JournalDocument.getInstance().newPage();
			return;
		}
		
		if (MenuItem.PAGE_NEW_PAGE_AFTER.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.PAGE_NEW_PAGE_BEFORE.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.PAGE_DELETE.getMenuText().equals(menuText)) {
			return;
		}
		
		if (MenuItem.PAGE_LOCK.getMenuText().equals(menuText)) {
			return;
		}
		

		//
		// Format Menu Item
		//
		for (MenuItem mi: MenuItem.values()) {
			if ((mi.getMenu().equals(MenuItem.FORMAT_REGULAR.getMenu())) &&
					(mi.getMenuText().equals(menuText)))
				PagePanel.getInstance().menuFormatOption(mi);
		}
		
		//
		// Menu Drawing Item
		//
		for (MenuItem mi: MenuItem.values()) {
			if ((mi.getMenu().equals(MenuItem.DRAWING_TEXT.getMenu())) &&
					(mi.getMenuText().equals(menuText)))
				PagePanel.getInstance().menuDrawingOption(mi);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("MenuSelected ISC: ");
		// TODO Auto-generated method stub
		
	}

}
