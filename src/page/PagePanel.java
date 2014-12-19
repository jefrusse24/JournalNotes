package page;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menubar.MenuItem;
import document.Page;

public class PagePanel extends JPanel {

	public static int PREFERRED_WIDTH = 820;
	public static int MAX_WIDTH = 1000;
	public static int PREFERRED_HEIGHT = 700;

	private static final long serialVersionUID = 1L;

	private static PagePanel theInstance = null;
	
	private PageDataPanel m_dataPanel;
	private PageTitlePanel m_titlePanel;
	
	private Page m_currentPage;
	
	/**
	 * Singleton.  This will get the instance of this class.  If it does not exist yet,
	 * it will create it.
	 * 
	 * @return
	 */
	public static PagePanel getInstance() {
		if (theInstance == null) 
			theInstance = new PagePanel();
		
		return theInstance;
	}
	
	/**
	 * Private PagePanel Constructor.  This object is only created once for the entire
	 * program.  All pages are rendered in the PagePanel.
	 */
	private PagePanel() {
		super(true);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
      
		// Title Area
		m_titlePanel = new PageTitlePanel();
		
		//  Page Data Panel
		m_dataPanel = new PageDataPanel();

		this.add(m_titlePanel);
		this.add(m_dataPanel);
		
	}

	/**
	 * This will show the current page in the page panel.  This will update the page title panel
	 * as well as the page data panel.
	 * @param page
	 */
	public void showPage(Page page) {
		m_currentPage = page;
		
		if (page == null) {
			// Do nothing.
		} else {
			m_titlePanel.showPage(m_currentPage);
			m_dataPanel.showPage(m_currentPage);
		}
		
	}

	public void setDrawingMode(DrawingMode drawPoints) {
		// TODO Auto-generated method stub
		
	}

	public DrawingMode getDrawingMode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDrawingFlag(DrawingMode fillOn) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets the current page that is being displayed in the PagePanel.
	 * @return
	 */
	public Page getCurrentPage() {
		return m_currentPage;
	}
	
	/**
	 * ???? Is this used?
	 */
//	public void UpdateUI() {
//		m_currentPage = JournalDocument.getInstance().getCurrentPage();
//		m_titlePanel.setCurrentPage(m_currentPage);
//		m_dataPanel.setCurrentPage(m_currentPage);
//	}

	/**
	 * This is called if a Format Menu item is selected.  It is now up to the
	 * PagePanel to handle the MenuItem that was selected.
	 * @param mi
	 */
	public void menuFormatOption(MenuItem mi) {
		// TODO Auto-generated method stub
		
		switch (mi) {
		case FORMAT_REGULAR:
			break;
		case FORMAT_BOLD:
			break;
		case FORMAT_ITALICS:
			break;
		case FORMAT_HEADING_A:
			break;
		case FORMAT_HEADING_B:
			break;
		case FORMAT_HEADING_C:
			break;
		case FORMAT_HEADING_D:
			break;
		case FORMAT_CODE:
			System.out.println("Code Is Selected");
			break;

		case FORMAT_FORWARDS:
			break;
		case FORMAT_BACKWARDS:
			break;
		case FORMAT_TO_FRONT:
			break;
		case FORMAT_TO_BACK:
			break;
			
		default:
			break;
		}
		
	}

	/**
	 * This is called if a Drawing Menu item is selected.  It is now up to the
	 * PagePanel to handle the MenuItem that was selected.
	 * @param mi
	 */
	public void menuDrawingOption(MenuItem mi) {
		// TODO Auto-generated method stub
		switch (mi) {
		case DRAWING_TEXT:
			System.out.println("Text Is Selected");
			break;
		case DRAWING_PENCIL:
			break;
		case DRAWING_SELECT:
			break;
			
		case DRAWING_LINE:
			break;
		case DRAWING_LINE_IENGTH:
			break;
		case DRAWING_LINE_ARROW:
			break;
		case DRAWING_LINE_DOUBLE_ARROW:
			break;
			
		case DRAWING_FILL:
			break;

		case DRAWING_RECT:
			break;
		case DRAWING_ELLIPSE:
			break;
		case DRAWING_ROUND_RECTANGLE:
			break;
		case DRAWING_MULTI_POINT:
			break;

		case DRAWING_WIDTH_1:
			break;
		case DRAWING_WIDTH_2:
			break;
		case DRAWING_WIDTH_5:
			break;

		default:
			break;
		}
		
	}
	
	
//	public void paint(Graphics g) {
//		int scrollValue = m_scrollPane.getVerticalScrollBar().getValue();
//		m_scrollArea.setScrollDepth(scrollValue);
//		
//		super.paint(g);
//		
//		
//		if (m_currentPage != null)
//			m_currentPage.paint(g,scrollValue);
//
//	}
//		super.paint(g);

//		int offset = 0;
//		int LINE_HEIGHT=18;
//		
//		Rectangle r = g.getClipBounds();
//		int height = getHeight();
//		int width = getWidth();
//		g.setColor(Color.white);
//		g.fillRect(r.x, r.y, r.width, r.height);
//		g.setColor(Color.pink);
//		g.drawLine(30, 0, 30,height);
//
//		g.setColor(new Color(200,230,250));
//		for (int i =41-offset ; i < height ; i+=LINE_HEIGHT) {
//			g.drawLine(31, i, width-16, i);
//		}
//		
//		
//	}

}
