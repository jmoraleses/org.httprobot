package org.httprobot.common.placeholders.operators.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Html;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * Page {@link Html} operator message class. Inherits {@link Html}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class Page extends Html
{
	/**
	 * -2633250624543608352L
	 */
	private static final long serialVersionUID = -2633250624543608352L;

	private Anchor Anchor = null;
	private Element Element = null;
	private Division Division = null;
	private Table Table = null;
	private TableRow tableRow = null;
	private TableCell tableCell = null;
	private Page Page = null;
	
	/**
	 * Page operator message default class constructor.
	 */
	public Page()
	{
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.Html#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setAnchor(((Page)e.getRml()).getAnchor());
		setElement(((Page)e.getRml()).getElement());
		setDivision(((Page)e.getRml()).getDivision());
		setTable(((Page)e.getRml()).getTable());
		setTableRow(((Page)e.getRml()).getTableRow());
		setTableCell(((Page)e.getRml()).getTableCell());
		setPage(((Page)e.getRml()).getPage());
	}
	/**
	 * @param pageAnchor the pageAnchor to set
	 */
	@XmlElement
	public void setAnchor(Anchor pageAnchor) {
		Anchor = pageAnchor;
	}
	/**
	 * @param pageElement the pageElement to set
	 */
	@XmlElement
	public void setElement(Element pageElement) {
		Element = pageElement;
	}
	/**
	 * @return the pageAnchor
	 */
	public Anchor getAnchor() {
		return Anchor;
	}
	/**
	 * @return the pageElement
	 */
	public Element getElement() {
		return Element;
	}
	/**
	 * @return the division
	 */
	public Division getDivision() {
		return Division;
	}
	/**
	 * @param division the division to set
	 */
	@XmlElement
	public void setDivision(Division division) {
		Division = division;
	}
	/**
	 * @return the table
	 */
	public Table getTable() {
		return Table;
	}
	/**
	 * @param table the table to set
	 */
	@XmlElement
	public void setTable(Table table) {
		Table = table;
	}
	/**
	 * @return the tableRow
	 */
	public TableRow getTableRow() {
		return tableRow;
	}
	/**
	 * @param tableRow the tableRow to set
	 */
	@XmlElement
	public void setTableRow(TableRow tableRow) {
		this.tableRow = tableRow;
	}
	/**
	 * @return the tableCell
	 */
	public TableCell getTableCell() {
		return tableCell;
	}
	/**
	 * @param tableCell the tableCell to set
	 */
	@XmlElement
	public void setTableCell(TableCell tableCell) {
		this.tableCell = tableCell;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		Page = page;
	}
	/**
	 * @return the page
	 */
	public Page getPage() {
		return Page;
	}
	
}