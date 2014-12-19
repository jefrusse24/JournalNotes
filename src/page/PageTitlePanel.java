package page;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import summary.SummarySettings;
import document.JournalDocument;
import document.Page;

public class PageTitlePanel extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int HEIGHT = 28;
	private JLabel m_pageNumLabel;
	private JTextField m_pageTitle;
	private JLabel m_pageDateLabel;
	private Page currentPage;

	public PageTitlePanel() {
		super(true);
		this.setPreferredSize(new Dimension(PagePanel.PREFERRED_WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(PagePanel.PREFERRED_WIDTH, HEIGHT));

		// Title Area
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(new JLabel("  ")); // Spacing

		//
		// Page Number
		this.add(new JLabel(" # "));
		m_pageNumLabel = new JLabel("0  ");
		this.add(m_pageNumLabel);

		// Title
		m_pageTitle = new JTextField(10);
		m_pageTitle.setPreferredSize(new Dimension(700, HEIGHT - 6));
		m_pageTitle.setMinimumSize(new Dimension(200, HEIGHT - 6));
		m_pageTitle.setFont(new Font("helvetica", Font.PLAIN, 14));
		this.add(m_pageTitle);
		m_pageTitle.addKeyListener(this);

		// Date
		this.add(new JLabel("Date: "));
		m_pageDateLabel = new JLabel("NA");
		this.add(m_pageDateLabel);

		// Info Button
		JButton aButton = new JButton("Info");
		this.add(aButton);
		aButton.addActionListener(this);

		// New Button
		aButton = new JButton("New");
		this.add(aButton);
		aButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("New")) {
			JournalDocument.getInstance().newPage();
			System.out.println("New Page");
			
		} else if (cmd.equals("Info")) {
			new PageInfoDialog(currentPage);
		}

	}

	/**
	 * This is called from PagePanel when a new page is to be shown.  This sets the currentPage to this page
	 * and renders the page title information.
	 * @param p
	 */
	public void showPage(Page p) {

		if (p != null) {
			currentPage = p;
			m_pageNumLabel.setText("" + p.getPageNumber() + "  ");
			m_pageTitle.setText(p.getPageTitle());

			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			m_pageDateLabel.setText("" + df.format(new Date(p.getCreationDate().getTimeInMillis()))
					+ " ");
			System.out.println("SP: ");
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!currentPage.isLocked()) {
			// Update the title each time a key is released in this field.
			String title = ((JTextField) e.getComponent()).getText();
			currentPage.setPageTitle(title);
			SummarySettings.getInstance().updateSummaryPanel(currentPage.getPageNumber(), title);
		} else {
			// This is the password for this page.
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String passKey = ((JTextField) e.getComponent()).getText();
				System.out.println("NotesPanel : Password Attempt " + passKey);
				if (currentPage.unlockPage(passKey)) {
					updateUI();
					System.out.println("NotesPanel : Password Attempt Success " + passKey);
				}
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
