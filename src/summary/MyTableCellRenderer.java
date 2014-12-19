package summary;

import java.awt.Component;
import java.util.TreeSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(
			JTable table,
			Object value, 
			boolean isSelected,
			boolean hasFocus, 
			int row,
			int col)  {

		if ((value.getClass().equals(TreeSet.class)) && (((TreeSet)value).size()==0))
			value = "";
		
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		if (! isSelected) {
			c.setBackground(SummaryPanel.PanelColor);
		}
		
		// Center align the page number column.
		if (col==0)
            setHorizontalAlignment( CENTER );
		else
            setHorizontalAlignment( LEFT );


		return c;
	}


}
