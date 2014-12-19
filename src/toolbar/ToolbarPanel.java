package toolbar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import page.PagePanel;

public class ToolbarPanel extends JPanel {

	public static int HEIGHT=30;
	private static final long serialVersionUID = 1L;

	private static ToolbarPanel theInstance = null;
	
	/**
	 * Singleton.  This will get the instance of this class.  If it does not exist yet,
	 * it will create it.
	 * 
	 * @return
	 */
	public static ToolbarPanel getInstance() {
		if (theInstance == null)
			theInstance = new ToolbarPanel();
		return theInstance;
	}
	
	
	private ToolbarPanel() {
		super(true);

		this.setPreferredSize(new Dimension(PagePanel.PREFERRED_WIDTH,HEIGHT));
		this.setMaximumSize(new Dimension(PagePanel.MAX_WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(50,HEIGHT));
		JPanel tbp = new JPanel(true);
		tbp.setLayout(new BoxLayout(tbp, BoxLayout.LINE_AXIS));
		SearchPanel spanel = new SearchPanel();
		ButtonPanel buttons = new ButtonPanel();
		tbp.add(buttons);
		tbp.add(Box.createHorizontalGlue());
		tbp.add(spanel);
		
		this.setLayout(new GridBagLayout());  // Make the windows grow and shrink.
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;  // Expand any extra width
        c.weighty = 0;  // Expand any extra height
		this.add(tbp,c);
		
	}
}
