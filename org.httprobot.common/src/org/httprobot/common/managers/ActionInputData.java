/**
 * 
 */
package org.httprobot.common.managers;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.common.RML;
import org.httprobot.common.managers.action.ActionInputDataRow;

/**
 * ActionInputData message class. Inherits {@link RML}.
 * @author joan
 *
 */
@XmlRootElement
public class ActionInputData extends RML {

	/**
	 * -1186570281207546541L
	 */
	private static final long serialVersionUID = -1186570281207546541L;

	ArrayList<ActionInputDataRow> actionInputDataRow = null;
	
	/**
	 * 
	 */
	public ActionInputData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the actionInputDataRow
	 */
	public ArrayList<ActionInputDataRow> getActionInputDataRow() 
	{
		if(actionInputDataRow != null)
		{
			return actionInputDataRow;
		}
		else
		{
			actionInputDataRow = new ArrayList<ActionInputDataRow>();
			return actionInputDataRow;
		}
	}

	/**
	 * @param actionInputDataRow the actionInputDataRow to set
	 */
	public void setActionInputDataRow(
			ArrayList<ActionInputDataRow> actionInputDataRow) {
		this.actionInputDataRow = actionInputDataRow;
	}

	
}
