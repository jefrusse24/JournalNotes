package document;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import menubar.FormatStyle;

public class TextBlock extends PageObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301503813520786552L;
	byte[] m_blockData;
	String m_blockString;
	private FormatEntryList m_formatting;

	// This is the styled Document which is generated from the text in the object and
	// the formatting found in the m_formatting object.  This is what is used for display
	// purposes.
	private StyledDocument m_styledDoc;

	private boolean m_isDirty;
	
	public TextBlock() {
		m_styledDoc = new DefaultStyledDocument();
		addStylesToDocument(m_styledDoc);
		m_formatting = new FormatEntryList();
		m_isDirty = true;
		m_blockString="";
	}
	
	public void setText(String text) {
		m_blockString = text;
		m_isDirty = true;
		
		if (m_styledDoc == null) 
			m_styledDoc = new DefaultStyledDocument();
		
		try {
			m_styledDoc.insertString(0, text, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public String getText() {
		return m_blockString;
		
	}

	public StyledDocument getStyledDoc() {
		return m_styledDoc;
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		
		Object obj = in.readObject();
		if (obj.getClass().equals(String.class)) 
			m_blockString = (String)obj;
		
		obj = in.readObject();
		if (obj.getClass().equals(FormatEntryList.class)) 
			m_formatting = (FormatEntryList)obj;
		
		m_styledDoc = new DefaultStyledDocument();
		addStylesToDocument(m_styledDoc);

		try {
			m_styledDoc.insertString(0,m_blockString , m_styledDoc.getStyle(FormatStyle.REGULAR.getStyleName()));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		m_isDirty = false;
		
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		
		if (m_isDirty) {
			rebuildFormatting();
			prepareTextBlock();
		}		
		out.writeObject(m_blockString);
		out.writeObject(m_formatting);

		m_isDirty = false;
	}
	
	/**
	 * This will rebuild the formatting list (m_formatting) that is needed for
	 * serialization of the m_styledDoc to keep the existing formatting
	 * (Styles). If the styledDoc is empty, then the m_formatting is set to
	 * null.
	 */
	private void rebuildFormatting() {

		if ((m_styledDoc== null) || (m_styledDoc.getLength() == 0)) {
			m_formatting = null;
			return;
		}
		
		m_formatting = new FormatEntryList();
		
		int startFormat = -1;
		String currentFormat = "";
		for (int i = 0 ; i < m_styledDoc.getLength() ; i++) {
			Element e = m_styledDoc.getCharacterElement(i);
			AttributeSet as = e.getAttributes();
			
			Enumeration<?> en = as.getAttributeNames();
			while (en.hasMoreElements()) {
				Object attr = as.getAttribute(en.nextElement());

				if (attr.getClass().equals(String.class)) {
					if (startFormat==-1) {
						currentFormat = (String)attr;
						startFormat = 0;
					} else if ((! currentFormat.equals(attr)) && (currentFormat.startsWith("f_"))) {
//						System.out.println("Dump Range : "+startFormat+" - "+i+" Format: "+currentFormat);
						m_formatting.add(startFormat, i, currentFormat);
						startFormat = i;
						currentFormat = (String)attr;
					}
				}
			}
		}
		// Dump the remaining formatting info
		if ((startFormat != m_styledDoc.getLength())) {
			if ((currentFormat.startsWith("f_"))) {
//				System.out.println("Range : "+startFormat+" - "+m_styledDoc.getLength()+" Format: "+currentFormat);
				m_formatting.add(startFormat, m_styledDoc.getLength(), currentFormat);
			} else {
//				System.out.println("Range : "+startFormat+" - "+m_styledDoc.getLength()+" Format: NONE(f_regular)");
				m_formatting.add(startFormat, m_styledDoc.getLength(), "f_regular");
				
			}
		}
	}

	/**
	 * Take the text from a styled doc and put them into the textBlock so the data can be saved.
	 * This will also prepare all blocks for encryption.
	 */
	private void prepareTextBlock() {
		String text = null;
		try {
			text = m_styledDoc.getText(0, m_styledDoc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		m_blockString = text;
		
	}

    protected void addStylesToDocument(StyledDocument doc) {
		// Initialize some styles.

		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		Style regular = doc.addStyle(FormatStyle.REGULAR.getStyleName(), def);
		StyleConstants.setFontFamily(def, "SansSerif");

		Style s = doc.addStyle(FormatStyle.HEADING1.getStyleName(), regular);
		StyleConstants.setItalic(s, true);
		StyleConstants.setFontSize(s, 18);
		StyleConstants.setForeground(s, Color.BLUE);
//		StyleConstants.setSpaceAbove(s, 4f);
//		StyleConstants.setSpaceBelow(s, 2f);
//		StyleConstants.setAlignment(s, 3);
//		StyleConstants.setLineSpacing(s, 1.5f);
//		StyleConstants.FontConstants.setSpaceAbove(s, 7f);
//		StyleConstants.FontConstants.setLineSpacing(s, 2f);
//		s.addAttribute(StyleConstants.CharacterConstants.SpaceAbove	, 5);
//		s.addAttribute(StyleConstants.FontConstants.SpaceAbove	, 5);
//		s.addAttribute(StyleConstants.SpaceAbove	, 5);
		
		s = doc.addStyle(FormatStyle.HEADING2.getStyleName(), regular);
		StyleConstants.setFontSize(s, 18);

		s = doc.addStyle(FormatStyle.HEADING3.getStyleName(), regular);
		StyleConstants.setFontSize(s, 18);
		StyleConstants.setForeground(s, Color.WHITE);
		StyleConstants.setBackground(s, Color.BLACK);

		s = doc.addStyle(FormatStyle.HEADING4.getStyleName(), regular);
		StyleConstants.setFontSize(s, 18);
		StyleConstants.setForeground(s, Color.RED);

		s = doc.addStyle(FormatStyle.BOLD.getStyleName(), regular);
		StyleConstants.setBold(s, true);

		s = doc.addStyle(FormatStyle.CODE.getStyleName(), regular);
		StyleConstants.setFontFamily(s, "Monaco");

		s = doc.addStyle(FormatStyle.QUOTE.getStyleName(), regular);
		StyleConstants.setItalic(s, true);

	}

	/**
	 * @return the isDirty
	 */
	public boolean isDirty() {
		return m_isDirty;
	}

	/**
	 * @param isDirty the isDirty to set
	 */
	public void setDirty(boolean isDirty) {
		this.m_isDirty = isDirty;
	}

	@Override
	public Point getPt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(Point pt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void moveTo(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void calculateBoundingRect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g, int yOffset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void delete() {
		// TODO Auto-generated method stub
		
	}

}
