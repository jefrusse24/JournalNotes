package summary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import page.PagePanel;
import document.JournalDocument;
import document.Page;

public class SummaryPanel extends JPanel {
	
	public static int HEIGHT=100;
	
	private static final long serialVersionUID = 1L;
	private JTable m_table;	
    private DefaultTableModel m_model;

    public static Color PanelColor = new Color(0xDBF0DB);
    public static Color PanelTitleColor = new Color(0xC8DCC8);

    private static SummaryPanel theInstance = null;

	/**
	 * Singleton.  This will get the instance of this class.  If it does not exist yet,
	 * it will create it.
	 * 
	 * @return
	 */
    public static SummaryPanel getInstance() {
    	if (theInstance == null) {
    		theInstance = new SummaryPanel();
    	}
    	
    	return theInstance;
    }
    
    @SuppressWarnings("serial")
	private SummaryPanel() {
		super(true);
		
		// Instantiate the QuickSettings class.
		SummarySettings.getInstance(this);
		SummaryEventHandler quickEH = new SummaryEventHandler();

		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(PagePanel.PREFERRED_WIDTH,HEIGHT));
        String[] columnNames = {"Page","Create Date", "Title","Tags"};

        // Create a new table model with un-editable cells.
        m_model = new DefaultTableModel()  {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
        		return false; 
        	}
        	// Needed for sorting and alphanumeric date
        	@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
        		Class returnValue;
        		if ((column >= 0) && (column < getColumnCount())) {
                  returnValue = getValueAt(0, column).getClass();
                } else {
                  returnValue = Object.class;
                }
                return returnValue;
        	}
        };

        for (String name : columnNames)
        	m_model.addColumn(name);
        
        m_table = new JTable(m_model);
        m_table.setRowSelectionAllowed(true);
        m_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_table.setAutoCreateRowSorter(true);

        // Add listener when a row is clicked.
        m_table.getSelectionModel().addListSelectionListener(quickEH);
        
        MyTableCellRenderer renderer = new MyTableCellRenderer();
        m_table.setDefaultRenderer(Object.class, renderer);
        m_table.getTableHeader().setBackground(PanelTitleColor);
        m_table.getTableHeader().setFont( new Font( "Arial" , Font.PLAIN, 15 ));
        // Add listener for drag/drop capabilities
        // This has been removed because we have no need to drag/drop from this table anymore.
        m_table.setDragEnabled(true);
//        QuickTransferHandler quickTH = new QuickTransferHandler(m_model);
//        m_table.setTransferHandler(quickTH);
//        m_table.setDropMode(DropMode.INSERT_ROWS);

        // Format the table cells and spacing
        m_table.setIntercellSpacing(new Dimension(2,0));
        m_table.setGridColor(new Color(224, 224, 224));  // VERY LIGHT GRAY

//        TableCellRenderer centerRenderer  = new CenterRenderer();
        
        JScrollPane qTableScroll = new JScrollPane(m_table);

        // Page
        m_table.getColumnModel().getColumn(0).setMaxWidth(60);
        m_table.getColumnModel().getColumn(0).setPreferredWidth(40);
        m_table.getColumnModel().getColumn(0).setCellRenderer(renderer);

        //Date
        m_table.getColumnModel().getColumn(1).setMaxWidth(140);
        m_table.getColumnModel().getColumn(1).setPreferredWidth(120);
        m_table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        
        //Title
        m_table.getColumnModel().getColumn(2).setPreferredWidth(300);	// Title
        m_table.getColumnModel().getColumn(3).setPreferredWidth(150);	// Tags
        this.add(qTableScroll);
		
        // Make sure we update this quickpanel with the selected item in the tree... which is
        // root, so show them all.
 //       CategorySettings.getInstance().treeSelected();	// Show all pages.
		showPagesAll();
	}

	public JTable getSummaryTable() {
		return m_table;
	}
	
	public DefaultTableModel getSummaryModel() {
		return m_model;
	}

	/**
	 * This will show the page summaries for the list of pages that is passed in.
	 * If the passed in value is null or empty, it will clear the list of pages.
	 * 
	 * @param pages
	 */
	public void showPages(LinkedList<Page> pages) {
		if (pages == null) {
			m_model.setRowCount(0);
			return;
		}
		
		m_model.setRowCount(0);

		//String date;
		Calendar cDate;
		
		Object[] rowData = new Object[4];

		
		for (Page page: pages) {
			cDate = page.getCreationDate();
			//date = ""+cDate.get(Calendar.YEAR)+"-"+cDate.get(Calendar.MONTH)+"-"+cDate.get(Calendar.DAY_OF_MONTH);
			rowData[0] = page.getPageNumber();
			rowData[1] = new Date(cDate.getTimeInMillis());
			rowData[2] = page.getPageTitle();
			rowData[3] = page.getCategories();
			m_model.addRow(rowData);
		
		}
	}

	/**
	 * This will show all of the page summaries for all of the
	 * pages in the current document.
	 */
	public void showPagesAll() {
		showPages(JournalDocument.getInstance().getPages());
		
	}

	/**
	 * This will select the last page in the table.
	 */
	public void selectLastPage() {
		selectPage(m_table.getRowCount()-1);
	}

	/**
	 * This will select a specified row in the table.
	 * The rows start at 0.  If you specifiy a value
	 * greater than the number of rows, then it will
	 * nothing.
	 * @param row
	 */
	public void selectPage(int row) {
		int maxRow = m_table.getRowCount()-1;
		if (row > maxRow)
			return;
		
		m_table.changeSelection(row, row, false, false);
	}
}
