package toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import page.PagePanel;
import summary.SummaryPanel;
import document.JournalDocument;
import document.Page;
import document.PageLocation;

public class SearchPanel extends JPanel implements KeyListener,FocusListener,ActionListener {

	public static int SEARCH_WIDTH=400;
	private static String DEFAULT_SEARCH_WORD = "Search";
	private static final long serialVersionUID = 1L;
	JTextField searchField;

	/**
	 * Flag needed to be used to track if the search field is empty.  This is needed
	 * because the search bar may contain the word "Search", and I do not want it confused
	 * if a user actually types the workd "Search" in the search bar.
	 */
	boolean searchCleared = true;

	
	PageLocation m_startSearch = null;

	public SearchPanel() {
		super(true);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMinimumSize(new Dimension(SEARCH_WIDTH,ToolbarPanel.HEIGHT));
		this.setMaximumSize(new Dimension(SEARCH_WIDTH,ToolbarPanel.HEIGHT));

		searchField = new JTextField(15);
		searchField.setMaximumSize(new Dimension(SEARCH_WIDTH,ToolbarPanel.HEIGHT));
		this.add(searchField);
	//	searchField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		searchField.setText(DEFAULT_SEARCH_WORD);
		searchField.setFont(new Font("helvetica", Font.PLAIN, 14));
		searchField.setForeground(Color.GRAY);
		searchField.addKeyListener(this);
		searchField.addFocusListener(this);
//		JButton doSearch = new JButton("Search");
		JButton doSearchBack = new JButton("<-");
		JButton doSearchForw = new JButton("->");
		int BUTTON_SIZE=22;
		
		doSearchBack.setPreferredSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
		doSearchBack.setMaximumSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
		doSearchBack.setMinimumSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
		this.add(doSearchBack);
		doSearchBack.addActionListener(this);

		doSearchForw.setMaximumSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
		doSearchForw.setPreferredSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
		doSearchForw.setMinimumSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
		this.add(doSearchForw);
		doSearchForw.addActionListener(this);
				
	}

	/**
	 * This will perform the search with the specified searchToken.  It will look through
	 * the entire document, and compile a list of pages that match the selected criteria.
	 * This list will be the only items to be listed in the SummmaryPane.  The PagePane
	 * will show the contents of the first page in that list.  We will set a search marker
	 * so we can perform an "Find Next" command.
	 * @param searchToken
	 */
	private void performSearch(String searchToken) {

		LinkedList<Page> matches = findPageMatches(searchToken);
		SummaryPanel.getInstance().showPages(matches);
		
		m_startSearch = new PageLocation();
		m_startSearch.setCurPage(JournalDocument.getInstance().getCurrentPage());
		
		if ((matches == null) || (matches.size() == 0))
			PagePanel.getInstance().showPage(null);
		else
			PagePanel.getInstance().showPage(matches.getFirst());
	}

	/**
	 * This will look through the entire list of pages and find matches for the 
	 * specified criteria.  It will return a list of all the pages that match this
	 * search criteria.
	 * 
	 * @param searchToken
	 * @return
	 */
	private LinkedList<Page> findPageMatches(String searchToken) {
		
		LinkedList<Page> matches = new LinkedList<Page>();
		for (Page p: JournalDocument.getInstance().getPages()) {
			if (p.containsString(searchToken)) 
				matches.add(p);
		
		}
		// TODO Auto-generated method stub
		return matches;
	}

	/**
	 * This will clear the search field, and put the word "Search" in gray text back into the
	 * search box.  It will also remove the focus from this field.
	 */
	private void clearSearchField() {
		searchField.setText(DEFAULT_SEARCH_WORD);
		searchField.setForeground(Color.GRAY);
		searchCleared = true;
		SummaryPanel.getInstance().showPagesAll();
		SummaryPanel.getInstance().requestFocus();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		searchField.setForeground(Color.BLACK);
		searchCleared = false;
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String searchToken = searchField.getText();
			if (searchToken.equals("")) {
				clearSearchField();
			} else {
				performSearch(searchToken);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if ( searchCleared == true) {
			searchField.setText("");
			searchField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		String searchToken = searchField.getText();
		if (searchToken.equals("")) {
			clearSearchField();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String eCmd = e.getActionCommand();

		// Do nothing if the search field is considered cleared.
		if (searchCleared == true)
			return;
		
		if (eCmd.equals("->")) {
			System.out.println("Search Forward");
			String searchToken = searchField.getText();
			performSearch(searchToken);
		} else if (eCmd.equals("<-")) {
			System.out.println("Search Back");
			String searchToken = searchField.getText();
			performSearch(searchToken);
		}
		
	}

}
