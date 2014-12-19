package summary;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SummaryEventHandler implements ListSelectionListener {

	public void valueChanged(ListSelectionEvent e) {
		SummarySettings.getInstance().rowSelected();
		
	}

}
