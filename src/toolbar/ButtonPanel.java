package toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import page.DrawingMode;
import page.PagePanel;
import utils.JnUtility;

public class ButtonPanel extends JToolBar implements ActionListener {

	private static final long	serialVersionUID	= 1L;

	//
    static final private String SAVE = "Save";
    static final private String BOLD = "Bold";
    static final private String ITALIC = "Italic";
//    static final private String PAPER_TYPE = "Paper Type";
    static final private String DRAW_TEXT = "Text";
    static final private String DRAW_SELECT = "Select";
    static final private String DRAW_PENCIL = "Pencil";
    static final private String DRAW_RECT = "Rectangle";
    static final private String DRAW_ROUNDRECT = "Round Rectangle";
    static final private String DRAW_ELLIPSE = "Ellipse";
    static final private String DRAW_LINE = "Line";
    static final private String DRAW_FILL = "Fill";
    static final private String EDIT_SHOW_TODO = "Show Todo";
    
//    static final private String FONT = "Font";
//    static final private String MULTILINE = "Multi-Line";

//    private ADrawPanel m_dPanel;

    private JToggleButton m_btnSelect;
    private JToggleButton m_btnText;
    private JToggleButton m_btnPencil;
    private JToggleButton m_btnRect;
    private JToggleButton m_btnRoundRect;
    private JToggleButton m_btnEllipse;
    private JToggleButton m_btnLine;
    
    private PagePanel m_sPanel;
    
    private JButton m_btnSave;
    JPopupMenu Pmenu;
    
//	public NotesToolbar(ADrawPanel panel) {
//		super("Notes Toolbar");
//		// Most important is the height (26)
//		this.setPreferredSize(new Dimension(300,26));
//		addButtons(this);
//		setFloatable(false);
//		setRollover(true);
//		m_dPanel = panel;
//	}

	public ButtonPanel() {
		super("Notes Toolbar");
		this.setMaximumSize(new Dimension(600,26));
		// Most important is the height (30)
		this.setPreferredSize(new Dimension(580,26));
		addButtons(this);
		setFloatable(false);
		setRollover(true);
		m_sPanel = PagePanel.getInstance();
	}

	private void addButtons(JToolBar toolBar) {
    	JToggleButton tbutton = null;
    	JButton button = null;
		
        //first button
    	m_btnSave = makeNavigationButton("SaveGray.png", SAVE, "Save document", "Save");
        toolBar.add(m_btnSave);
        
        toolBar.addSeparator();

        m_btnText = makeToggleNavigationButton("text.png", ButtonPanel.DRAW_TEXT, ButtonPanel.DRAW_TEXT, ButtonPanel.DRAW_TEXT);
        m_btnText.setBorderPainted(true);
    	toolBar.add(m_btnText);

    	m_btnSelect = makeToggleNavigationButton("select2.png", ButtonPanel.DRAW_SELECT, ButtonPanel.DRAW_SELECT, ButtonPanel.DRAW_SELECT);
    	toolBar.add(m_btnSelect);

    	m_btnPencil = makeToggleNavigationButton("pencil.png", ButtonPanel.DRAW_PENCIL, ButtonPanel.DRAW_PENCIL, ButtonPanel.DRAW_PENCIL);
    	toolBar.add(m_btnPencil);
 
    	m_btnRect = makeToggleNavigationButton("rectangleNoFill.png", ButtonPanel.DRAW_RECT, ButtonPanel.DRAW_RECT, ButtonPanel.DRAW_RECT);
    	toolBar.add(m_btnRect);

    	m_btnRoundRect = makeToggleNavigationButton("roundedRectangleNoFill.png", ButtonPanel.DRAW_ROUNDRECT, ButtonPanel.DRAW_ROUNDRECT, ButtonPanel.DRAW_ROUNDRECT);
    	toolBar.add(m_btnRoundRect);

    	m_btnEllipse = makeToggleNavigationButton("ellipseNoFill.png", ButtonPanel.DRAW_ELLIPSE, ButtonPanel.DRAW_ELLIPSE, ButtonPanel.DRAW_ELLIPSE);
    	toolBar.add(m_btnEllipse);
    	
    	m_btnLine = makeToggleNavigationButton("line.png", ButtonPanel.DRAW_LINE, ButtonPanel.DRAW_LINE, ButtonPanel.DRAW_LINE);
    	toolBar.add(m_btnLine);
    	
        toolBar.addSeparator();

        // Fill 
    	tbutton = makeToggleNavigationButton("Stop24.gif", ButtonPanel.DRAW_FILL , ButtonPanel.DRAW_FILL, ButtonPanel.DRAW_FILL);
    	toolBar.add(tbutton);

    	toolBar.addSeparator();

    	button = makeNavigationButton("todo.png", ButtonPanel.EDIT_SHOW_TODO, ButtonPanel.EDIT_SHOW_TODO, ButtonPanel.EDIT_SHOW_TODO);
    	toolBar.add(button);

    	button = makeNavigationButton("colorWheel2.png", "Select Color", "Select Color", "Color");
    	toolBar.add(button);

    	toolBar.addSeparator();

    	tbutton = makeToggleNavigationButton("Bold24.gif", BOLD,"Bold", "Bold");
    	toolBar.add(tbutton);

    	tbutton = makeToggleNavigationButton("Italic24.gif", ITALIC,"Italic", "Italic");
    	toolBar.add(tbutton);

    	
    	// Page Type Menu
//    	Pmenu = new JPopupMenu(PAPER_TYPE);	
//    	JCheckBoxMenuItem menuItem;
//    	for (String ptype : Page.PaperKind) {
//    		menuItem = new JCheckBoxMenuItem(ptype);	
//        	menuItem.addActionListener(this);
//        	Pmenu.add(menuItem);
//    	}
//    	button = makeNavigationButton("arrowBlue.png",PAPER_TYPE, PAPER_TYPE, PAPER_TYPE);
//    	button.setComponentPopupMenu(Pmenu);
//    	toolBar.add(button);
    	
   	
	}

	protected JButton makeNavigationButton(String imageName,
			String actionCommand,
			String toolTipText,
			String altText) {
		

		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		button.setIcon(new ImageIcon(JnUtility.getIconPath(imageName), altText));

		return button;
	}

	protected JToggleButton makeToggleNavigationButton(String imageName,
			String actionCommand,
			String toolTipText,
			String altText) {
		
		
		//Create and initialize the button.
		//JButton button = new JButton();
		JToggleButton button = new JToggleButton();
		button.setBorderPainted(false);
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.removeAll();
		button.addActionListener(this);
		button.setIcon(new ImageIcon(JnUtility.getIconPath(imageName), altText));
		
		return button;
	}

	public void actionPerformed(ActionEvent e) {
//		System.out.println("Toolbar Action:"+e.getActionCommand());
		String eCmd = e.getActionCommand();
		

		boolean isSelected=false;
		JToggleButton jtb = null;
		if (e.getSource().getClass().equals(JToggleButton.class)) {
			jtb = (JToggleButton)e.getSource();
			isSelected = jtb.isBorderPainted();
		}

		// When you click on the Paper Type button, update the menu with
		// the correct page type selected.
//		if (PAPER_TYPE.equals(eCmd)) {
//			m_sPanel.finishEditing();
//			int pindex = Pages.getInstance().getCurrentPage().getPaperIntType();
//			for (int i = 0 ; i < Pmenu.getComponentCount(); i++) {
//				if (i == pindex) {
//					((JCheckBoxMenuItem)Pmenu.getComponent(i)).setSelected(true);
//				} else
//					((JCheckBoxMenuItem)Pmenu.getComponent(i)).setSelected(false);
//			}
//			Pmenu.show((Component)e.getSource(), 0, 0);
//			return;
//		}

		// Handle the Paper type option
//		int pIndex = 0;
//		for (String ptype : Page.PaperKind) {
//			if (ptype.equals(eCmd)) {
//				// set the paper type to this.
//				Pages.getInstance().getCurrentPage().setPaperType(pIndex);
//
//				m_sPanel.setPaperType(pIndex);
//				m_sPanel.updateUI();
//				return;
//			}
//			pIndex++;
//		}		
		
		
		// Text, Select, Point buttons
		if (ButtonPanel.DRAW_PENCIL.equals(eCmd)) {
			m_btnPencil.setBorderPainted(true);
			m_sPanel.setDrawingMode(DrawingMode.DRAW_POINTS);
			
			m_btnSelect.setBorderPainted(false);
			m_btnText.setBorderPainted(false);
			m_btnRect.setBorderPainted(false);
			m_btnRoundRect.setBorderPainted(false);
			m_btnEllipse.setBorderPainted(false);
			m_btnLine.setBorderPainted(false);
			return;
		}

		if (ButtonPanel.DRAW_TEXT.equals(eCmd)) {
			m_btnText.setBorderPainted(true);
			m_sPanel.setDrawingMode(DrawingMode.TEXT);
			
			m_btnSelect.setBorderPainted(false);
			m_btnPencil.setBorderPainted(false);
			m_btnRect.setBorderPainted(false);
			m_btnRoundRect.setBorderPainted(false);
			m_btnEllipse.setBorderPainted(false);
			m_btnLine.setBorderPainted(false);
			return;
		}

		if (ButtonPanel.DRAW_SELECT.equals(eCmd)) {
			m_btnSelect.setBorderPainted(true);
			m_sPanel.setDrawingMode(DrawingMode.SELECT);
			
			m_btnPencil.setBorderPainted(false);
			m_btnText.setBorderPainted(false);
			m_btnRect.setBorderPainted(false);
			m_btnRoundRect.setBorderPainted(false);
			m_btnEllipse.setBorderPainted(false);
			m_btnLine.setBorderPainted(false);
			return;
		}

		if (ButtonPanel.DRAW_RECT.equals(eCmd)) {
//			System.out.println("DM: "+m_sPanel.getDrawingMode().toString());
			if (equals(DrawingMode.DRAW_RECT.equals(m_sPanel.getDrawingMode())))
				return;
			if (isSelected) return;
			if (m_btnRect.isBorderPainted() && (m_btnRect.isSelected()))
				
			m_btnRect.setBorderPainted(true);
//			System.out.println("DR");
			m_sPanel.setDrawingMode(DrawingMode.DRAW_RECT);

			m_btnSelect.setBorderPainted(false);
			m_btnPencil.setBorderPainted(false);
			m_btnText.setBorderPainted(false);
			m_btnRoundRect.setBorderPainted(false);
			m_btnEllipse.setBorderPainted(false);
			m_btnLine.setBorderPainted(false);
			return;
		}
		if (ButtonPanel.DRAW_ROUNDRECT.equals(eCmd)) {
			if (isSelected) return;
			m_btnRoundRect.setBorderPainted(true);
			m_sPanel.setDrawingMode(DrawingMode.DRAW_ROUNDRECT);

			m_btnSelect.setBorderPainted(false);
			m_btnPencil.setBorderPainted(false);
			m_btnText.setBorderPainted(false);
			m_btnRect.setBorderPainted(false);
			m_btnEllipse.setBorderPainted(false);
			m_btnLine.setBorderPainted(false);
			return;
		}
		if (ButtonPanel.DRAW_ELLIPSE.equals(eCmd)) {
			m_btnEllipse.setBorderPainted(true);
			m_sPanel.setDrawingMode(DrawingMode.DRAW_ELLIPSE);

			m_btnSelect.setBorderPainted(false);
			m_btnPencil.setBorderPainted(false);
			m_btnText.setBorderPainted(false);
			m_btnRect.setBorderPainted(false);
			m_btnRoundRect.setBorderPainted(false);
			m_btnLine.setBorderPainted(false);
			return;
		}
		if (ButtonPanel.DRAW_LINE.equals(eCmd)) {
			m_btnLine.setBorderPainted(true);
			// Set the drawing mode to the last selected line type from the menu.
			m_sPanel.setDrawingMode(DrawingMode.DRAW_LINE);

			m_btnSelect.setBorderPainted(false);
			m_btnPencil.setBorderPainted(false);
			m_btnText.setBorderPainted(false);
			m_btnRect.setBorderPainted(false);
			m_btnRoundRect.setBorderPainted(false);
			m_btnEllipse.setBorderPainted(false);
			
			// TODO:  Need to update the menubar with this selection.
			return;
		}
		
		if (ButtonPanel.DRAW_FILL.equals(eCmd)) {
	        if (! isSelected) {
				m_sPanel.setDrawingFlag(DrawingMode.FILL_ON);
				jtb.setBorderPainted(true);
				
				m_btnRect.setIcon(new ImageIcon(JnUtility.getIconPath("rectangleFill.png")));
				m_btnRoundRect.setIcon(new ImageIcon(JnUtility.getIconPath("roundedRectangleFill.png")));
				m_btnEllipse.setIcon(new ImageIcon(JnUtility.getIconPath("ellipseFill.png")));
			} else {
				m_sPanel.setDrawingFlag(DrawingMode.FILL_OFF);
				jtb.setBorderPainted(false);

				m_btnRect.setIcon(new ImageIcon(JnUtility.getIconPath("rectangleNoFill.png")));
				m_btnRoundRect.setIcon(new ImageIcon(JnUtility.getIconPath("roundedRectangleNoFill.png")));
				m_btnEllipse.setIcon(new ImageIcon(JnUtility.getIconPath("ellipseNoFill.png")));
			}
			return;
		}

//		if (NotesToolbar.EDIT_SHOW_TODO.equals(eCmd)) {
//			new ShowToDo();
//			return;
//		}
//		
//		if ("Select Color".equals(eCmd)) {
//			m_sPanel.showColorSelector();
//			return;
//		}
//		
//		if (NotesToolbar.SAVE.equals(eCmd)) {
//			m_sPanel.finishEditing();
//			// This will update the saved icon all by itself.
//			if (LocalDataManip.getFileName() == null || LocalDataManip.getFileName().equals(""))
//				LocalDataManip.SaveDataPrompt();
//			else
//				LocalDataManip.SaveData(LocalDataManip.PERMANENT);
//
//			return;
//			
//		}
	}

	void updateSaveIcon(boolean isSaved) {

        if (isSaved) {
			m_btnSave.setIcon(new ImageIcon(JnUtility.getIconPath("SaveGray.png"),"Save"));
		} else {
			m_btnSave.setIcon(new ImageIcon(JnUtility.getIconPath("SaveBlue.png"),"Save"));
		}
	}

	
}
