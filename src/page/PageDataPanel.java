package page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import document.Page;

public class PageDataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane m_scrollPane; 
	JrTextPane m_scrollArea;
	private Page m_currentPage;

	
	public PageDataPanel() {
		super(true);
		this.setPreferredSize(new Dimension(PagePanel.PREFERRED_WIDTH,PagePanel.PREFERRED_HEIGHT));
		
		// Page Area
    	m_scrollArea = new JrTextPane();
    	m_scrollPane = new JScrollPane(m_scrollArea);
		m_scrollPane.setOpaque(false);
//		m_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		m_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		m_scrollPane.setBackground(Color.GRAY);
		this.setLayout(new GridBagLayout());  // Make the windows grow and shrink.
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;  // Expand any extra width
        c.weighty = 1;  // Expand any extra height

		this.add(m_scrollPane,c);

	}

	public void paint(Graphics g) {
		int scrollValue = m_scrollPane.getVerticalScrollBar().getValue();
		m_scrollArea.setScrollDepth(scrollValue);
		
		super.paint(g);
		
		if (m_currentPage != null)
			m_currentPage.paint(g,scrollValue);

	}

	/**
	 * This is called from PagePanel when a new page is to be shown.  This sets the currentPage to this page
	 * and renders the page data information.
	 * @param p
	 */
	public void showPage(Page p) {
		m_currentPage = p;
		m_scrollArea.setStyledDocument(p.getStyledDoc());
	}

}
