/**
 * 
 */
package org.httprobot.core.managers.operators;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.Split;
import org.httprobot.core.controls.operators.SplitControl;
import org.httprobot.core.controls.operators.interfaces.IControlSplitListener;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.managers.operators.interfaces.IManagerSplitListener;

/**
 * {@link Split} message manager class. Inherits {@link AbstractOperatorManager}.
 * <br>
 * @author joan
 *
 */
public class SplitManager 
	extends AbstractOperatorManager<Split, SplitControl, IManagerSplitListener> 
	implements IControlSplitListener, IDataMappingImpl<String, String[]> {
	
	/**
	 * 6743341490238529655L
	 */
	private static final long serialVersionUID = 6743341490238529655L;
	/**
	 * {@link Split} message manager default class constructor.
	 */
	public SplitManager() 
	{
		super();
	}
	/**
	 * {@link Split} message manager class constructor.
	 * @param parent {@link IManagerSplitListener} the parent
	 * @param message {@link Split} the message
	 */
	public SplitManager(IManagerSplitListener parent, Split message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<String, String[]>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public String[] get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			//Call abstract method to manage operator's messages
			super.OnCommandInput(sender, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public String[] put(String key, String[] value) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ? extends String[]> m) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public String[] remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#start()
	 */
	@Override
	public void start() {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#stop()
	 */
	@Override
	public void stop() {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<String[]> values() {
		// TODO Auto-generated method stub
		return null;
	}
}
