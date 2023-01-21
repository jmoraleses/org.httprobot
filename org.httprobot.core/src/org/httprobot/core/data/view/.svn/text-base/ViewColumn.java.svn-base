package org.httprobot.core.data.view;

import org.httprobot.common.definitions.Enums.RmlDataType;
import org.httprobot.common.interfaces.IListener;

/**
 * View column class used in {@link ViewTable}.
 * @author Joan
 */
public class ViewColumn extends ViewElement
{
	private String columnName;	
	private RmlDataType dataType;
	private String dbSchema;	
	/**
	 * Gets the type of the column.
	 * @return {@link RmlDataType}
	 */
	public RmlDataType getDataType() {
		return dataType;
	}	
	/**
	 * Gets the column name.
	 * @return {@link String}
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * Gets the name of the database of working schema.
	 * @return {@link String}
	 */
	public String getDbSchema() {
		return dbSchema;
	}	
	/**
	 * ViewColumn class constructor.
	 * @param dbSchema {@link String} name of the database of working schema.
	 * @param columnName {@link String} the name of the column 
	 * @param dataType {@link RmlDataType} the type of the column
	 */
	public ViewColumn(IListener sender, String dbSchema, String columnName, RmlDataType dataType)
	{
		super(sender);
		this.dbSchema  = dbSchema;
		this.columnName = columnName;
		this.dataType = dataType;
	}
}