package page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import document.Page;

public class PageInfoDialog extends JDialog implements ActionListener,ListSelectionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTable table;	
    private DefaultTableModel model;
    private JComboBox m_filter;



//	/*
//	 * This class is needed for the center and left alignment of the various columns.
//	 */
//    private class CenterRenderer extends DefaultTableCellRenderer {
//		private static final long	serialVersionUID	= 1L;
//
//		public CenterRenderer() {
//            setHorizontalAlignment( CENTER );
//        }
// 
//        public Component getTableCellRendererComponent(JTable table, Object value,
//        		boolean isSelected, boolean hasFocus, int row, int column) {
//            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            return this;
//        }
//    }

	public PageInfoDialog(Page p) {
		super((JFrame)null,true);
		
        buildPageInfo(p);
		
	}

	
    
	private void buildPageInfo(Page p) {
		JPanel mainP = new JPanel();
		mainP.setLayout(new BoxLayout(mainP,BoxLayout.PAGE_AXIS));
		
		JPanel headerP = new JPanel();
		headerP.setLayout(new BoxLayout(headerP,BoxLayout.LINE_AXIS));
		JLabel banner = new JLabel("Detailed Page Information");
		headerP.add(banner);
		mainP.add(headerP);
		
		JLabel info;
		
		info = new JLabel("Page Number : "+p.getPageNumber());
		mainP.add(info);
		info = new JLabel("Page Title : "+p.getPageTitle());
		mainP.add(info);
		info = new JLabel("Creation Date : "+p.getCreationDate().getTime().toString());
		mainP.add(info);
		info = new JLabel("Modified Date : "+p.getModifiedDate().getTime().toString());
		mainP.add(info);
		info = new JLabel("Sync Date : "+p.getSyncDate().getTime().toString());
		mainP.add(info);
		
		//
		// Categories
		//
		JPanel categories = new JPanel();
		categories.setLayout(new BoxLayout(categories,BoxLayout.PAGE_AXIS));
        model = new DefaultTableModel()  {
			private static final long	serialVersionUID	= 1L;
			public boolean isCellEditable(int rowIndex, int mColIndex) {
        		return false; 
        	}
        	// Needed for sorting and alphanumeric date
        	@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
        		Class returnValue = null;
        		if ((column >= 0) && (column < getColumnCount())) {
                  returnValue = getValueAt(0, column).getClass();
                } else {
                  returnValue = Object.class;
                }
                return returnValue;
        	}
        };

        model.addColumn("Tags");
        table = new JTable(model);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.setGridColor(new Color(224, 224, 224));  // VERY LIGHT GRAY
        table.setIntercellSpacing(new Dimension(2,0));
        table.getSelectionModel().addListSelectionListener(this);
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(400);	//
        JScrollPane qTableScroll = new JScrollPane(table);
        qTableScroll.setPreferredSize(new Dimension(500,200));
        qTableScroll.setVisible(true);

        model.setRowCount(0);
        if ((p.getCategories() == null)  || (p.getCategories().size() == 0)) {
    		Object[] rowData = new Object[1];
    		rowData[0] = "- NONE - ";
    		model.addRow(rowData);
        } else {
        	for (String tag : p.getCategories()){
        		Object[] rowData = new Object[1];
        		rowData[0] = tag;
        		model.addRow(rowData);
        	}
        }
        mainP.add(qTableScroll);


		
		JPanel pClose = new JPanel();
		JButton button = new JButton("Close");	
		button.addActionListener(this);
		pClose.add(button);
//		pClose.setMaximumSize(new Dimension(300,10));
		
		mainP.add(pClose);

		add(mainP);
        
		pack();

		setLocationRelativeTo(null);
		setVisible(true);

		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if ("Close".equals(cmd)) {
			
			setVisible(false);
			return;
		}
		
	}



	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
