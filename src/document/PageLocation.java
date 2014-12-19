package document;

/**
 * This class is used for searching.  This class can define an exact location in the document.
 * That way you can search forwards (or backwards) from the current position.
 * 
 * @author jeffrus
 * @since 2013-10-04
 *
 */
public class PageLocation {

	private Page curPage;
	private int	blockPos;
	private PageObject pageBlock;
	private String search;

	private boolean foundOnPage;
	
	public PageLocation() {
		curPage = null;
		blockPos = -1;
		pageBlock = null;
		foundOnPage = false;
		search = null;
	}
	
	/**
	 * The page the text was found on.
	 * @return
	 */
	public Page getCurPage() {
		return curPage;
	}

	/**
	 * The page the text was found on.
	 * @param curPage
	 */
	public void setCurPage(Page curPage) {
		this.curPage = curPage;
	}

	/**
	 * get the position in the block of the search text.
	 * @return
	 */
	public int getBlockPos() {
		return blockPos;
	}

	/**
	 * Set the position of the specified search text in the 
	 * text block.  If the text block is null, but the pos
	 * is set (not -1), then it is found in the text of a textdoc.
	 * @param blockPos
	 */
	public void setBlockPos(int blockPos) {
		this.blockPos = blockPos;
	}

	/**
	 * Get the Page Object the text was found in.
	 * @return
	 */
	public PageObject getPageBlock() {
		return pageBlock;
	}

	/**
	 * Set the PageObject that the search string was found in.
	 * @param pageBlock
	 */
	public void setPageBlock(PageObject pageBlock) {
		this.pageBlock = pageBlock;
	}

	/**
	 * Returns if the search string was found on this page.
	 * @return
	 */
	public boolean isFoundOnPage() {
		return foundOnPage;
	}


	/**
	 * Set flag if the search string was found on the page
	 * @param foundOnPage
	 */
	public void setFoundOnPage(boolean foundOnPage) {
		this.foundOnPage = foundOnPage;
	}


	/**
	 * Set the search string
	 * @param search
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Return the search string
	 * @return
	 */
	public String getSearch() {
		return search;
	}


	/**
	 * This will copy the contents of the src object to the current
	 * object.
	 * @param src
	 */
	public void copy(PageLocation src) {
		this.blockPos = src.blockPos;
		this.curPage = src.curPage;
		this.foundOnPage = src.foundOnPage;
		this.pageBlock = src.pageBlock;
		this.search = src.search;
	}
	
}
