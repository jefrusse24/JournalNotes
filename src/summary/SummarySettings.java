package summary;

import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import document.JournalDocument;

public class SummarySettings {
	private SummaryPanel m_panel;
//	private boolean m_useDate = true;
//	private String m_category;
	
	private Calendar m_date = Calendar.getInstance();
	
	protected DefaultTreeModel treeModel;

	private static SummarySettings m_singleton = null;
	public static SummarySettings getInstance(SummaryPanel qp) {
		
		if (m_singleton == null)
			m_singleton = new SummarySettings(qp,true);

		return m_singleton;
	}

	public static SummarySettings getInstance() {
		return m_singleton;
	}

	private SummarySettings(SummaryPanel jp, boolean nada) {
		m_panel = jp;
	}

//	public void updateQuickPanel(Calendar date) {
//
////		Update filter to filter on Date
//		m_useDate = true;
//		m_date.setTime(date.getTime());
////		Vector<Page> pageListSummary = Pages.getInstance().getPagesFor(date);
////		addPagesToList(pageListSummary);
//	}

//	public void updateQuickPanel(String category) {
//
////		Update Filter to filter on Category.
//		m_useDate = false;
//		m_category = category;
////		Vector<Page> pageListSummary = Pages.getInstance().getPagesFor(category);
////		addPagesToList(pageListSummary);
//	}
	

//	 private void addPagesToList(Vector<Page> pages) {
//		 		
//		DefaultTableModel model = m_panel.getSummaryModel();
//		model.setRowCount(0);
//
//		//String date;
//		Calendar cDate;
//		
//		Object[] rowData = new Object[4];
//		
//		for (Page page : pages) {
//			cDate = page.getDate();
//			//date = ""+cDate.get(Calendar.YEAR)+"-"+cDate.get(Calendar.MONTH)+"-"+cDate.get(Calendar.DAY_OF_MONTH);
//			rowData[0] = page.getPageNumber();
//			rowData[1] = new Date(cDate.getTimeInMillis());
//			rowData[2] = page.getPageTitle();
//			rowData[3] = page.getCategories();
//			model.addRow(rowData);
//		}
//		
//	}

	/**
	 * This is called when a page has it's title changed.  You pass in the
	 * page number that is changing along with the new title.
	 * @param pageNumber
	 * @param title
	 */
	public void updateSummaryPanel(int pageNumber, String title) {
		DefaultTableModel model = m_panel.getSummaryModel();
		int rows = model.getRowCount();
		int rNum = 0;
		boolean updated = false;
		while ((! updated) && (rNum < rows)) {
			if (model.getValueAt(rNum, 0).equals(pageNumber)) {
				updated = true;
				model.setValueAt(title, rNum, 2);
			}
			rNum++;
		}
	}

	/**
	 * New Page was added.  Add a new entry for this new page
	 */
//	public void updateQuickPanelNewPage() {
//		DefaultTableModel model = m_panel.getSummaryModel();
//		Calendar cDate;
//		
//		Page page = Pages.getInstance().getCurrentPage();
//		
//		Object[] rowData = new Object[4];
//
//		cDate = page.getDate();
//		rowData[0] = page.getPageNumber();
//		rowData[1] = new Date(cDate.getTimeInMillis());
//		rowData[2] = page.getPageTitle();
//		rowData[3] = page.getCategories();
//		model.addRow(rowData);
//		
//	}

	/**
	 * Update the quick panel and select the current page from the list
	 * and make sure the currently selected entry is visible.  If the
	 * current page is not in the list, then do nothing.
	 */
//	public void updateQuickPanelCurrentPage() {
//		Page page = Pages.getInstance().getCurrentPage();
//		DefaultTableModel model = m_panel.getQuickModel();
//		int rows = model.getRowCount();
//		for (int i = 0 ; i < rows ; i++) {
//			int pnum = Integer.valueOf(String.valueOf(model.getValueAt(i, 0)));
//			if (pnum == page.getPageNumber()) {
//				m_panel.getQuickTable().changeSelection(i, i, false, false);
//				return;
//			}
//		}
//		
//		// If we make it this far, that means we did not find the page in the list.
//		// We should make sure all elements are not selected.
//		m_panel.getQuickTable().clearSelection();
//		
//	}
	
//	public void updateUI() {
//
//		if (m_useDate) {
//			updateQuickPanel(m_date);
//		} else {
//			updateQuickPanel(m_category);
//		}
//		
//	}

	/**
	 * This is called when a row is selected.  This calls setCurrentPage in the Document class
	 * which will make sure the page is shown.
	 * 
	 */
	public void rowSelected() {
		JTable t = m_panel.getSummaryTable();
		int row = t.getSelectedRow();
		if (row == -1)
			return;
		Object o = t.getValueAt(row,0);
		int pageNum = 0;
		try {
			pageNum = (Integer)o;
		} catch (NumberFormatException e) {
			return;
		}
		
		JournalDocument.getInstance().setCurrentPage(pageNum);
		
		System.out.println("Page Changed to "+pageNum);
		
		// Make sure we get focus back after we update all of the things that need updating.
		t.requestFocus();
		
	}

}
