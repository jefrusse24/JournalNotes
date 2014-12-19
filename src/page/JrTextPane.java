package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;

/**
 * This class is used so we can draw the paper lines behind the text.
 * This is when the paper type is of a Doc, and the entire page is a
 * textpane.
 * @author jeffrus
 *
 */
public class JrTextPane extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int scrollDepth;
	private boolean showLines;
	public JrTextPane() {
		super();
		setOpaque(false);

		this.setFont( new Font( "Monaco" , Font.PLAIN, 14 ));
		setBackground(new Color(0,0,0,0));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

		scrollDepth = 2000;
		showLines = true;
	}

	/**
	 * This is needed so we know how far down any of the page lines need to be drawn down to
	 * @param sd - Value of the scrollbar associated with this text pane.
	 */
	public void setScrollDepth(int sd) {
		if (sd < 1000)
			scrollDepth = 1000;
		else 
			scrollDepth = sd;
	}
	
	/**
	 * Should we show page lines for this page.  If so, set show to true.
	 * @param show
	 */
	public void showLines(boolean show) {
		showLines = show;
	}

	/**
	 * What about if the doc is a graph doc?  This only draws if it is a lined doc.
	 */
	@Override
	protected void paintComponent(Graphics g) {

		if (showLines) {
			int spacing = this.getFont().getSize()+5;
			Rectangle r = g.getClipBounds();
			int height = r.height;
			int width=r.width;

			g.setColor(new Color(200,230,250));
			for (int i =spacing+1 ; i < height+scrollDepth+height ; i+=spacing) {
				g.drawLine(1, i, width, i);
			}
		}
		super.paintComponent(g);

	}


}
