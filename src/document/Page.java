package document;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.text.StyledDocument;

public class Page implements java.io.Serializable {

	private static final long serialVersionUID = 2529336967763316986L;
	
	/* ---------------------- Seralized Objects -----------------------*/
	private int			m_pageNumber=1;
	private String		m_pageTitle;
	private	int			m_pageType;	// Same as PaperType, but it is an index into the array.
	private	Calendar	m_creationDate;
	private	Calendar	m_modifiedDate;
	private	Calendar	m_syncDate;
	private Color		m_backgroundColor;
	private LinkedList<PageObject> m_pageObjects = new LinkedList<PageObject>();
	private SortedSet<String>		m_categories;
	private boolean		m_isLocked = false;
	private TextBlock	m_textBlock;			// THe page text is kept here (encrypted or not)
	
	// Flag to indicate if the page has changed at all since reading the page.
	// If it is dirty, then before the page is saved, the m_formatting needs to be
	// redone, 
	private boolean		m_isDirty=true;

	// Indicates if the page has been unlocked so we can see the content.  The page is still
	// an encrypted page.
	private boolean 	m_isCurrentlyLocked = false;

	
	/**
	 * Default Constructor.  Creates a page as page 1.
	 */
	public Page() {
		m_pageNumber = 1;
		m_pageTitle = "Untitled";
		m_pageType=1;	// Lined Doc
		m_creationDate = Calendar.getInstance();
		m_modifiedDate = Calendar.getInstance();
		m_syncDate = null;
		m_backgroundColor = Color.WHITE;
		m_pageObjects = new LinkedList<PageObject>();
		m_categories = new TreeSet<String>();
		m_isLocked = false;
		m_textBlock = new TextBlock();
		m_isDirty = true;
		m_isCurrentlyLocked = false;
	}
	
	public Page(int pageNum) {
		m_pageNumber = pageNum;
		m_pageTitle = "Untitled";
		m_pageType=1;	// Lined Doc
		m_creationDate = Calendar.getInstance();
		m_modifiedDate = Calendar.getInstance();
		m_syncDate = null;
		m_backgroundColor = Color.WHITE;
		m_pageObjects = new LinkedList<PageObject>();
		m_categories = new TreeSet<String>();
		m_isLocked = false;
		m_textBlock = new TextBlock();
		m_isDirty = true;
		m_isCurrentlyLocked = false;
	}

	/**
	 * Returns the page number
	 * @return int - The Page number
	 */
	public int getPageNumber() {
		return m_pageNumber;
	}

	/**
	 * Set the page number
	 * @param pageNum
	 */
	public void setPageNumber(int pageNum) {
		m_pageNumber = pageNum;
	}

	/**
	 * Gets the title associated with this page
	 * @return String - The page title
	 */
	public String getPageTitle() {
		return m_pageTitle;
	}

	/**
	 * Sets the title associated with this page
	 * 
	 * @param title
	 */
	public void setPageTitle(String title) {
		m_pageTitle = title;
	}

	/**
	 * Gets the date this page was created.
	 * @return Calendar - The date of creation of this page.
	 */
	public Calendar getCreationDate() {
		return m_creationDate;
	}
	
	/**
	 * Changes the date of this page
	 * 
	 * @param newDate Calendar - The new date of the page.
	 */
	public void setCreationDate(Calendar newDate) {
		m_creationDate.setTime(newDate.getTime());
	}

	/**
	 * Gets the date this page was last synced to the database.
	 * @return Calendar - The date of sync of this page.
	 */
	public Calendar getSyncDate() {
		return m_creationDate;
	}
	
	/**
	 * Changes the sync date of this page
	 * 
	 * @param newDate Calendar - The new sync date of the page.
	 */
	public void setSyncDate(Calendar newDate) {
		m_syncDate.setTime(newDate.getTime());
	}

	/**
	 * Gets the date this page was last modified.
	 * @return Calendar - The date of modified of this page.
	 */
	public Calendar getModifiedDate() {
		return m_modifiedDate;
	}
	
	/**
	 * Changes the modified date of this page
	 * 
	 * @param newDate Calendar - The new modified date of the page.
	 */
	public void setModifiedDate(Calendar newDate) {
		m_modifiedDate.setTime(newDate.getTime());
	}

	/**
	 * Gets the categories that this page is associated with.
	 * 
	 * @return String - String that represents the categories.
	 */
	public SortedSet<String> getCategories() {
		return m_categories;
	}

	/**
	 * Sets the categories this page is associcated with.
	 * 
	 * @param categories String
	 */
	public void setCategories(SortedSet<String> categories) {
		m_categories = categories;
	}

	public void setTextBlock(String text) {
		m_textBlock.setText(text);
	}
	
	public StyledDocument getStyledDoc() {
		return m_textBlock.getStyledDoc();
	}
	
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		m_pageNumber = in.readInt();
		m_pageType = in.readInt();
		
		Object obj = in.readObject();
		if (obj.getClass().equals(String.class))
			m_pageTitle = (String)obj;
		
		Long timeStamp = in.readLong();
		m_creationDate = Calendar.getInstance();
		m_creationDate.setTimeInMillis(timeStamp);
		
		timeStamp = in.readLong();
		m_modifiedDate = Calendar.getInstance();
		m_modifiedDate.setTimeInMillis(timeStamp);

		obj = in.readObject();
		if (obj.getClass().equals(Color.class))
			m_backgroundColor = (Color)obj;

		obj = in.readObject();
		if (obj.getClass().equals(TreeSet.class))
			m_categories = (TreeSet<String>)obj;
		
		m_isLocked = in.readBoolean();
		
		obj = in.readObject();
		if (obj.getClass().equals(TextBlock.class))
			m_textBlock = (TextBlock)obj;

		obj = in.readObject();
		if (obj.getClass().equals(LinkedList.class))
			m_pageObjects = (LinkedList<PageObject>)obj;

		
	}
	
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		
		out.writeInt(m_pageNumber);
		out.writeInt(m_pageType);
		out.writeObject(m_pageTitle);
		out.writeLong(m_creationDate.getTimeInMillis());
		out.writeLong(m_modifiedDate.getTimeInMillis());
		out.writeObject(m_backgroundColor);
		out.writeObject(m_categories);
		out.writeBoolean(m_isLocked);
		out.writeObject(m_textBlock);
		out.writeObject(m_pageObjects);
	}

	public void paint(Graphics g, int offset) {
		
	}

	/**
	 * This performs a search on the page for a matching searchToken.  This will ignore case.
	 * If the page contains a match, this will return a true.
	 * 
	 * @param searchToken
	 * @return
	 */
	public boolean containsString(String searchToken) {
		
		searchToken = searchToken.toUpperCase();
		
		// Look into the categories
		for (String cat: m_categories) 
			if (cat.toUpperCase().equals(searchToken))
				return true;
		
		// Look on the textBlock
		if (m_textBlock.getText().toUpperCase().contains(searchToken))
			return true;
		
		// Look at all of the other textblocks
		if (m_pageObjects == null)
			return false;
		
		for (PageObject po : m_pageObjects) {
			if (po.getClass().equals(TextBlock.class)) {
				if (((TextBlock)po).getText().toUpperCase().contains(searchToken))
					return true;
			}
		}
		return false;
	}

	/**
	 * Add a category to the page.
	 * @param category
	 */
	public void addCategory(String category) {
		m_categories.add(category);
		
	}

	/**
	 * Remove a category from the page.
	 * @param category
	 */
	public void removeCategory(String category) {
		m_categories.remove(category);
		
	}

	/**
	 * Returns true if the current page is locked.
	 * @return
	 */
	public boolean isLocked() {
		return m_isLocked;
	}

	/**
	 * This attempts to unlock the current page with a specified passkey.
	 * If it is successful in unlocking the page, it returns a true.
	 * @param passKey
	 * @return
	 */
	public boolean unlockPage(String passKey) {
		// TODO Auto-generated method stub
		return false;
	}



}
