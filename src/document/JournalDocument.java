package document;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import page.PagePanel;
import summary.SummaryPanel;

public class JournalDocument implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8196065042240293050L;

	public static final int FILE_VERSION = 131010;	// 2013-10-10
	
	/* -------- Serialized Objects -------------------------------------------------- */
	private String uuidReader;
	private Calendar dateCreated;
	private Calendar lastSync;
	private Calendar lastModified;
	int docVersion;
//	private int numPages;

	/* ----------------------------------------------------------------------*/
	private LinkedList<Page> m_listOfPages;
	private Page m_currentPage;
	private TreeSet<String> categories;

	private static JournalDocument document;

	public static JournalDocument getInstance() {
		if (document == null) {
			document = new JournalDocument();
		}

		return document;
	}

	private JournalDocument() {
		m_listOfPages = new LinkedList<Page>();
		categories = new TreeSet<String>();
		dateCreated = Calendar.getInstance();
		lastSync = Calendar.getInstance();
		lastSync.setTimeInMillis(0);
		lastModified = Calendar.getInstance();
		
		m_currentPage = null;
	}

	public static JournalDocument getTemporaryDocument() {
		return new JournalDocument();
	}
	
	public void setCurrentPage(Page p) {
		m_currentPage = p;
	}

	/**
	 * Set the current page to this page, and make sure it is showing
	 * in the page panel.  This is called when a person selects the page
	 * from the Summary Panel.
	 * @param p
	 */
	public void setCurrentPage(int p) {
		for (Page page : m_listOfPages) {
			if (page.getPageNumber() == p) {
				m_currentPage = page;
				PagePanel.getInstance().showPage(page);
				break;
			}
		}
	}

	public Page getCurrentPage() {
		return m_currentPage;
	}

	public LinkedList<Page> getPages() {
		return m_listOfPages;
	}

	/**
	 * Add a page to the end of the list of pages.  This will add this page
	 * to the list of pages in the summary panel.
	 * @param p
	 */
	public void addPage(Page p) {
		m_listOfPages.add(p);
		m_currentPage = p;

		if (p.getCategories() != null)
			categories.addAll(p.getCategories());
		
		// OPTIMIZE: Could call a method to add a single row to the summary panel.
		SummaryPanel.getInstance().showPages(m_listOfPages);
		
	}

	public String getUuidReader() {
		return uuidReader;
	}

	public void setUuidReader(String uuidReader) {
		this.uuidReader = uuidReader;
	}

	public Calendar getLastSync() {
		return lastSync;
	}

	public void setLastSync(Calendar lastSync) {
		this.lastSync = lastSync;
	}

	public Calendar getLastModified() {
		return lastModified;
	}

	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Returns the number of pages in this document.
	 * @return number of pages
	 */
	public int getNumPages() {
		return m_listOfPages.size();
	}

	/**
	 * This is called when a user reads a document and wants to use this
	 * document as the current document.  This is also called after a 
	 * "Merge" of a local file and a database are created, and the resulting
	 * master result is set as the current document.
	 * @param jd
	 */
	public static void setDocument(JournalDocument jd) {
		document = jd;
		document.setCurrentPage(jd.getPages().getFirst());
		SummaryPanel.getInstance().showPagesAll();
		PagePanel.getInstance().showPage(document.getCurrentPage());
		SummaryPanel.getInstance().selectLastPage();
		
	}

	/**
	 * Create a new page in the document and show this new page.  This will 
	 * add the new page to the SummaryList, and select it in the Summary List.
	 * It will also show this new page in the PagePanel.  This
	 * is called when a person clicks on either the "New Page" menu, or the
	 * "New" Button on the page.
	 */
	public void newPage() {
		Page p = new Page(getNumPages()+1);
		m_listOfPages.add(p);
		
		// Make it the showing page
		SummaryPanel.getInstance().showPagesAll();
		PagePanel.getInstance().showPage(p);
		SummaryPanel.getInstance().selectLastPage();
	}

}
