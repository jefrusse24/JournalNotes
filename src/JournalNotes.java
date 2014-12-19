/*
 * =============================================================================
 * Copyright (c) 2009 Jeffrey S Russell. All rights reserved.
 * 
 * All source code and material is the copyright of Jeffrey S. Russell and
 * is protected under copyright laws of the United States. This source code may
 * not be hosted on any site without my express, prior, written
 * permission. Application to host any of the material elsewhere can be made by
 * contacting me at jefrusse@yahoo.com.
 *
 * I have made every effort and taken great care in making sure that the source
 * code and other content included on my web site is technically accurate, but I
 * disclaim any and all responsibility for any loss, damage or destruction of
 * data or any other property which may arise from relying on it. I will in no
 * case be liable for any monetary damages arising from such loss, damage or
 * destruction.
 * 
 * As with any code, ensure to test this code in a development environment 
 * before attempting to run it in production.
 * =============================================================================
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import menubar.JournalMenu;
import menubar.MacPreferencesHandler;
import page.PagePanel;
import summary.SummaryPanel;
import toolbar.ToolbarPanel;
import document.JournalDocument;
import document.Page;


public class JournalNotes  extends JFrame {

		
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
//	NoteMenuBar					menuBar;

	public static Color bgcolor = new Color(0xFFDDE4F2,true);

	public JournalNotes() {

        super();
        super.setTitle("Journal Notes");
        super.setLocationRelativeTo(null);

		Container contentPane = this.getContentPane();
		
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        contentPane.setBackground(bgcolor);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;  // Do Not Expand Height
        c.weightx = 1;  // Do Expand width
        c.ipadx = 0;
        c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,5,0,5);

		
        JPanel toolbarPanel = ToolbarPanel.getInstance();
//        toolbarPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        toolbarPanel.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(toolbarPanel,c);
        JPanel summaryPanel = SummaryPanel.getInstance();
        JPanel pagePanel = PagePanel.getInstance();

        c.fill = GridBagConstraints.BOTH;
//        c.ipadx = 200; 
//        c.ipady = 100;
        c.weightx = 1;  // Expand any extra width
        c.weighty = 1;  // Expand any extra height
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,5,5,5);

		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				summaryPanel, pagePanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(130);

		contentPane.add(splitPane,c);

		this.setJMenuBar(new JournalMenu());
		
//		menuBar = new NoteMenuBar(notes.getScrollPanel());		
//		this.setJMenuBar(menuBar);

		//
		// Initial Load of file
		//
//		if (LocalDataManip.initialLoad()) {
//
//			// Set the title of the document.
//			String title = LocalDataManip.getFileName();
//			NotesSettings.getInstance().setDocumentTitle(title);
//
//			// Update the UI of all the controls.
//			NotesSettings.getInstance().updateUI();
//			CategorySettings.getInstance().updateUI();
//			QuickSettings.getInstance().updateUI();
//			CalendarSettings.getInstance().today();
//
//			// Show the current page in the quick list.
//			QuickSettings.getInstance().updateQuickPanelCurrentPage();
//		}
		
		pack();
	}

	/**
	 * Since we want to control what happens when a user attempts to close out
	 * the frame, we need to override the
	 * javax.swing.JFrame.processWindowEvent() method.
	 * 
	 * @param e
	 *            WindowEvent being passed as a result of user actions at the
	 *            Window level.
	 */
	protected void processWindowEvent(WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {

//			if (menuBar.saveAndQuit())
//				System.exit(0);


		} else {

			super.processWindowEvent(e);
		}
	}

	/**
	 * Sole entry point to the class and application.
	 * 
	 * @param args
	 *            Array of String arguments.
	 * @throws Exception 
	 */
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws  Exception {

		String vers = System.getProperty("os.name").toLowerCase();
		if (vers.indexOf("mac") != -1) {
			try {
				java.lang.System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				        "Journal Notes V3");
			} catch (Exception e) {
				// try the older menu bar property
				java.lang.System.setProperty("com.apple.macos.useScreenMenuBar", "true");
			}
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			com.apple.eawt.Application app = com.apple.eawt.Application.getApplication();
			app.setPreferencesHandler(new MacPreferencesHandler());
		}

		JournalNotes mainFrame = new JournalNotes();

		debugBuildPages();

		mainFrame.setVisible(true);


	}

	
	/**
	 * Temporary debugging method to generate some pages to work with.
	 */
	private static void debugBuildPages() {
		JournalDocument theD = JournalDocument.getInstance();
		
		for (int i = 0 ; i < 40 ; i++ ) {
			Page p = new Page(i+1);
			p.setPageTitle("Page "+(i+1));
			p.setCreationDate(Calendar.getInstance());
			
			String textString = new String("There is some text here.\n");
			for (int j = 0 ; j <= i*i ; j++) {
				textString += "There is some more text here - Line "+(j+1)+"\n";
			}
			for (int j = 0 ; j < i%3; j++) {
				p.addCategory("Cat "+j);
			}
			
			p.setTextBlock(textString);
			theD.addPage(p);
			
		}
		PagePanel.getInstance().showPage(JournalDocument.getInstance().getCurrentPage());
	}
}