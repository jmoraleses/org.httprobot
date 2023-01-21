package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.DataType;
import org.httprobot.common.RML;
import org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;


/**
 * {@link DataType} message control listener implementation interface.
 * Implemented when reading {@link RML} {@link DataType} elements.
 * <br>
 * @author Joan
 *
 */
public interface IDataTypeControlListener 
		extends IControlImpl, IControlFieldListener, IControlFieldRootListener, 
			IControlDataSourceListener, IControlDocumentListener, IControlDocumentRootListener { 	
}