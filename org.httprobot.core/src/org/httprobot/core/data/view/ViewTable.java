package org.httprobot.core.data.view;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.UUID;
import java.util.Vector;
import java.util.Date;
import java.awt.Image;
import java.io.OutputStream;
import java.lang.Class;

import javax.swing.table.DefaultTableModel;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.CellAlreadyExistsException;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.httprobot.core.interfaces.IDataListener;
import org.w3c.dom.events.Event;

/**
 * View table class. Inherits {@link DefaultTableModel}.
 * @author Joan 
 */
public class ViewTable extends DefaultTableModel implements IDataListener
{
	/**
	 * 1699806303983919058L
	 */
	private static final long serialVersionUID = 1699806303983919058L;
	private static final CliNamespace cliNamespace = CliNamespace.DATA;
	
	private Vector<ViewRow> rows;
	private ArrayList<ViewColumn> columns;
	private String destinationPath;
	/**
	 * ViewTable class constructor. Empty table.
	 * @param columns {@link ArrayList} of {@link ViewColumn}
	 */ 
	public ViewTable(ArrayList<ViewColumn> columns)
	{
		super();
		this.columns = columns;
	}
	/**
	 * ViewTable class constructor. Full table.
	 * @param columns {@link ArrayList} of {@link ViewColumn}
	 * @param data {@link Vector} of {@link ViewRow}
	 */
	public ViewTable(ArrayList<ViewColumn> columns, Vector<ViewRow> data)
	{
		super(data, data.size());
		this.columns = columns;
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#addColumn(java.lang.Object)
	 */
	@Override
	public void addColumn(Object arg0) 
	{
		try
		{
			ViewColumn vc = ViewColumn.class.cast(arg0);
			
			if(vc != null)
			{
				this.columns.add(vc);
				fireTableDataChanged();
			}
		}
		catch(ClassCastException ex1)
		{
			ex1.printStackTrace();
		}
	}	
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#addRow(java.lang.Object[])
	 */
	@Override
	public void addRow(Object[] arg0) 
	{
		ViewRow vr = new ViewRow(this, this.columns);
		
		for(int i = 0; i < arg0.length; i++)
		{
			ViewCell vc = ViewCell.class.cast(arg0[i]);
			
			if(vc != null)
			{
				try 
				{
					vr.addCell(vc);
				} 
				catch (CellAlreadyExistsException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#findColumn(java.lang.String)
	 */
	@Override
	public int findColumn(String columnName) 
	{		
		for(int i = 0; i < this.columns.size(); i++)
		{
			ViewColumn vc = this.columns.get(i);
			
			if(columnName.equals(vc.getColumnName()))
			{
				return i; 
			}
		}
		
		return super.findColumn(columnName);
	}	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) 
	{
		ViewColumn vc = this.columns.get(columnIndex);
		
		switch(vc.getDataType())
		{
			case BOOLEAN:
				return Boolean.class;
			case DATETIME:
				return Date.class;
			case BYTEARRAY:
				return OutputStream.class;
			case IMAGE:
				return Image.class;
			case NUMBER:
				return Integer.class;
			case STRING:
				return String.class;
			case LINK:
				return String.class;
			default:
				return null;
		}
	}	
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() 
	{
		return this.columns.size();
	}	
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int arg0) 
	{
		return this.columns.get(arg0).getColumnName();
	}	
	/**
	 * Gets the columns.
	 * @return {@link ArrayList} of {@link ViewColumn}
	 */
	public ArrayList<ViewColumn> getColumns() 
	{
		return columns;
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#getRowCount()
	 */
	@Override
	public int getRowCount() 
	{
		return this.rows.size();
	}
	/**
	 * Gets the rows. 
	 * @return {@link Vector} of {@link ViewRow}
	 */
	public Vector<ViewRow> getRows() 
	{
		return rows;
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		return this.rows.get(arg0).getCell(arg1);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IDataListener#OnProgramDataChanged(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnProgramDataChanged(Object sender, ProgramDataEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() 
	{
		return this.destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

}