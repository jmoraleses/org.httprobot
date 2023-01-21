package org.httprobot.core.data.view;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.Date;
import java.awt.Image;
import java.io.OutputStream;
import java.lang.Class;

import javax.swing.table.DefaultTableModel;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.CellAlreadyExistsException;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.w3c.dom.events.Event;

/**
 * View table class. Inherits {@link DefaultTableModel}.
 * @author Joan 
 */
public class ViewTable extends DefaultTableModel implements IManagerDataViewListener
{
	private static final CliNamespace cliNamespace = CliNamespace.DATA;
	/**
	 * 1699806303983919058L
	 */
	private static final long serialVersionUID = 1699806303983919058L;
	
	private ArrayList<ViewColumn> columns;
	private String destinationPath;
	private Vector<ViewRow> rows;
	private UUID uuid;
	
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
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
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
			case BASE64:
				return OutputStream.class;
			case IMAGE:
				return Image.class;
			case NUMBER:
				return Integer.class;
			case TEXT:
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
	 * @see org.httprobot.core.interfaces.IManagerImpl#getContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
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
	 * @see org.httprobot.common.interfaces.IListener#getCliOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid() {
		return this.uuid;
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
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
		
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerListener#OnManager_BannedAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManager_BannedAdded(Object sender, ManagerEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerListener#OnParamAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManager_ParamAdded(Object sender, ManagerEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.datatypes.interfaces.IManagerDataViewListener#OnManagerDataView_Error(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnManagerDataView_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.datatypes.interfaces.IManagerDataViewListener#OnManagerDataView_Finished(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnManagerDataView_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IDataListener#OnProgramDataChanged(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnManagerDataView_Started(Object sender, ManagerEventArgs e)
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
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) 
	{
		this.uuid = uuid;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getParameters()
	 */
	@Override
	public Map<String, String> getParameterConstants() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getParametersBanned()
	 */
	@Override
	public Map<String, String> getParameterBannedWords()
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getTemplateLibrary()
	 */
	@Override
	public TemplateLibrary getTemplateLibrary() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getDocumentLibrary()
	 */
	@Override
	public DocumentLibrary getDocumentLibrary() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public IManagerImpl next() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#addChildManager(org.httprobot.core.interfaces.IManagerImpl)
	 */
	@Override
	public void addChildManager(IManagerImpl manager)
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		
	}

}