/**
 * 
 */
package org.httprobot.common.managers;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.managers.action.ActionOutputDataRow;

/**
 * @author joan
 *
 */
@XmlRootElement
public class ActionOutputData extends RML {

	/**
	 * 5514719720895252922L
	 */
	private static final long serialVersionUID = 5514719720895252922L;

	ArrayList<ActionOutputDataRow> actionOutputDataRow = null;
	/**
	 * @return the actionOutputDataRow
	 */
	public ArrayList<ActionOutputDataRow> getActionOutputDataRow() 
	{
		if(actionOutputDataRow != null)
		{
			return actionOutputDataRow;
		}
		else
		{
			actionOutputDataRow = new ArrayList<ActionOutputDataRow>();
			return actionOutputDataRow;
		}
	}
	/**
	 * @param actionOutputDataRow the actionOutputDataRow to set
	 */
	public void setActionOutputDataRow(
			ArrayList<ActionOutputDataRow> actionOutputDataRow) {
		this.actionOutputDataRow = actionOutputDataRow;
	}
	/**
	 * 
	 */
	public ActionOutputData() {
		// TODO Auto-generated constructor stub
	}
}
