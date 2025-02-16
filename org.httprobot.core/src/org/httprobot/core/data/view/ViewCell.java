package org.httprobot.core.data.view;

import java.awt.Image;
import java.io.OutputStream;
import java.util.Date;

import org.httprobot.common.exceptions.ViewTableException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.tools.CliTools;
import com.gargoylesoftware.htmlunit.ObjectInstantiationException;


/**
 * Cell of a {@link ViewTable} cell class. Inherits {@link ViewElement}.
 * @author Joan
 */
public class ViewCell extends ViewElement
{
	/**
	 * 7959041854942100981L
	 */
	private static final long serialVersionUID = 7959041854942100981L;
	private ViewColumn column;
	private Object value;
	/**
	 * Gets the current column.
	 * @return {@link ViewColumn}
	 */
	public ViewColumn getColumn() {
		return column;
	}
	/**
	 * Gets the current cell value.
	 * @return {@link Object}
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * Sets the current cell value.
	 * @param value {@link ObjectInstantiationException}
	 * @throws Exception when both object value and type match
	 */
	public void setValue(Object value) throws Exception 
	{
		switch (column.getDataType())
		{
			case BOOLEAN:
				if(value instanceof Boolean)
				{
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to BOOLEAN", ViewTableException.class);
				}
				return;
			case BASE64:
				if(value instanceof OutputStream)
				{
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to BYTEARRAY", ViewTableException.class);
				}
				return;
			case DATETIME:
				if(value instanceof Date)
				{
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to DATETIME", ViewTableException.class);
				}
				return;
			case IMAGE:
				if (value instanceof Image)
				{
					
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to IMAGE", ViewTableException.class);
				}
				return;
			case LINK:
				if(value instanceof String)
				{					
					/*
					 * Check if current data is a Link.
					 * */
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to LINK", ViewTableException.class);
				}
				return;
			case NUMBER:
				if(value instanceof Integer)
				{
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to NUMBER", ViewTableException.class);
				}
				return;
			case TEXT:
				if(value instanceof Integer)
				{
					this.value = value;
				}
				else
				{
					CliTools.ThrowException(this, "\nViewCell.setValue: Can't cast to STRING", ViewTableException.class);
				}
				return;
			default:
				return;
		}
	}	
	/**
	 * ViewCell class constructor. Empty cell.
	 * @param column {@link ViewColumn} current column
	 */
	public ViewCell(IListener sender, ViewColumn column) 
	{
		super(sender);
		this.column = column;
	}	
	/**
	 * ViewCell class constructor. Filled cell.
	 * @param column {@link ViewColumn} current column
	 * @param value {@link Object} current value
	 */
	public ViewCell(IListener sender, ViewColumn column, Object value) 
	{
		super(sender);
		this.column = column;
		this.value = value;
	}






}
