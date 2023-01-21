package org.httprobot.core.data.view;

import java.util.ArrayList;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.CellAlreadyExistsException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.w3c.dom.events.Event;

/**
 * Row a {@link ViewTable}. Inherits {@link ViewElement}.
 * @author Joan
 */
public class ViewRow extends ViewElement
{
	private ArrayList<ViewCell> cells;
	private ArrayList<ViewColumn> columns;	
	/**
	 * Gets the cell for the current index.
	 * @param arg0
	 * @return
	 */
	public ViewCell getCell(int arg0)
	{
		return this.cells.get(arg0);
	}
	/**
	 * Gets all the cells.
	 * @return {@link ArrayList} of {@link ViewCell}
	 */
	public ArrayList<ViewCell> getCells() {
		return cells;
	}	
	/**
	 * Gets the columns.
	 * @return {@link ArrayList} of {@link ViewColumn}
	 */
	public ArrayList<ViewColumn> getColumns() {
		return columns;
	}	
	/**
	 * ViewRow class constructor. Empty row.
	 * @param columns {@link ArrayList} of {@link ViewColumn}
	 */
	public ViewRow(IListener sender, ArrayList<ViewColumn> columns)
	{
		super(sender);
		this.columns = columns;
		this.cells = new ArrayList<ViewCell>();
	}	
	/**
	 * ViewRow class constructor. Full row.
	 * @param columns {@link ArrayList} of {@link ViewColumn}
	 * @param cells {@link ArrayList} of {@link ViewCell}
	 */
	public ViewRow(IListener sender, ArrayList<ViewColumn> columns, ArrayList<ViewCell> cells)
	{
		super(sender);
		this.columns = columns;
		this.cells = cells;
	}
	/**
	 * Adds a cell to current row.
	 * @param cell {@link ViewCell} the cell
	 * @throws CellAlreadyExistsException when current cell already exists inside current row.
	 */
	public void addCell(ViewCell cell) throws CellAlreadyExistsException
	{
		for(ViewCell vc : this.cells)
		{
			Boolean trobat = false;
			
			if(cell.getColumn().equals(vc.getColumn()))
			{
				//If cell belongs to row
				for(ViewCell vc2 : this.cells)
				{
					if(cell.equals(vc2))
					{
						trobat = true;
					}
				}
				if(trobat == false)
				{
					this.cells.add(cell);
				}
				else
				{
					throw new CellAlreadyExistsException(this, 
							"Cell "  + vc.getValue().toString() + 
							"not found on Cells row: " + cell.getColumn().getColumnName());
				}
			}
			else
			{
				continue;
			}
		}
	}


	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	@Override
	public void handleEvent(Event arg0)
	{
		
	}
	@Override
	public void OnProgramDataChanged(Object sender, ProgramDataEventArgs e) 
	{
		
	}

	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		
	}
}