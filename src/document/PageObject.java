package document;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public abstract class PageObject implements Transferable {

	public static final int TITLE_HEIGHT=8;
	
	Rectangle m_boundingRect;
	Color m_color = Color.BLACK;
	boolean m_isHilighted;
	int	m_lineWidth = 1;

	public Rectangle getBoundingRect() {
		return m_boundingRect;
	}
	
	public Color getColor() {
		return m_color;
	}

	public void setColor(Color color) {
		m_color = color;
	}
	
	public boolean isHilighted() {
		return m_isHilighted;
	}

	public void isHilighted(boolean isHilighted) {
		m_isHilighted = isHilighted;
	}
	
	public boolean getObjectAtPoint(Point pt) {
		 return m_boundingRect.contains(pt);
	}

	/**
	 * Gets the width of the line for this object.
	 * @return int - Width of the line for this object.
	 */
	public int getLineWidth() {
		return m_lineWidth;
	}
	/**
	 * Sets the width of the line for this object.  The maximum
	 * line width is 15.  It takes the passed in value, and looks
	 * at only the first byte to set the width of the line.
	 * @param width int - Width of the line
	 */
	public void setLineWidth(int width) {
		m_lineWidth = (width & 0x0F); // Max of 15 width.
	}
	//
	//
	// Abstract Methods
	// Specific methods for objects of this class
	//

	/**
	 * Return the upper left point of ths object.
	 * @return
	 */
	public abstract Point getPt();

	public abstract void move(Point pt);
	
	abstract void moveTo(Point point);
	
	abstract void calculateBoundingRect();
	
	public abstract void paint(Graphics g);

	public abstract void paint(Graphics g, int yOffset);
	
	abstract void delete();

	@Override
	public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
		return this;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
