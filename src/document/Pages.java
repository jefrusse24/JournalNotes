//package document;
//
//import java.util.Vector;
//
//import summary.SummaryPanel;
//
//public class Pages {
//
//	private Vector<Page> m_listOfPages;
//	private Page m_currentPage;
//	
//	private static Pages m_pages;
//	
//	public static Pages getInstance() {
//		if (m_pages == null) {
//			m_pages = new Pages();
//		}
//		
//		return m_pages;
//	}
//	
//	private Pages() {
//		m_listOfPages = new Vector<Page>();
//		m_currentPage = null;
//	}
//	
//	public void setCurrentPage(Page p) {
//		m_currentPage = p;
//	}
//	
//	public void setCurrentPage(int p) {
//		for (Page page: m_listOfPages) {
//			if (page.getPageNumber() == p) {
//				m_currentPage = page;
//				break;
//			}
//		}
//	}
//
//	public Page getCurrentPage() {
//		return m_currentPage;
//	}
//	
//	public Vector<Page> getPages() {
//		return m_listOfPages;
//	}
//	
//	public void addPage(Page p) {
//		m_listOfPages.add(p);
//		SummaryPanel.getInstance().showPages(m_listOfPages);
//	}
//	
//}
