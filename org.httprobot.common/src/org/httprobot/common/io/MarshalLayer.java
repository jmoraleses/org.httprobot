/**
 * 
 */
package org.httprobot.common.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.MarshallerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.interfaces.IMarshallerListener;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.w3c.dom.events.Event;

/**
 * Marshal object class. Inherits {@link CLI}. 
 * <br>
 * Inherits{@link IMarshallerListener}.
 * <br>
 * @author Joan
 * 
 */
@XmlTransient
public abstract class MarshalLayer 
		extends CLI 
		implements IMarshallerListener {
	
	/**
	 * The name space for input and output commands.
	 */
	private static final CliNamespace cliNamespace = CliNamespace.RML;
	/**
	 * 5148359226674211796L;
	 */
	private static final long serialVersionUID = 5148359226674211796L;
	
	/**
	 * JAXB context
	 */
	private JAXBContext jaxbContext = null;
	/**
	 * The marshaller
	 */
	private Marshaller jaxbMarshaller = null;
	/**
	 * The unmarshaller
	 */
	private Unmarshaller jaxbUnmarshaller = null;	
	/**
	 * RmlControlInit Listeners
	 */
	private Vector<IMarshallerListener> marshaller_event_listeners = null;
	
	/**
	 * Marshal object default constructor.
	 */
	public MarshalLayer()
	{
		this.marshaller_event_listeners = new Vector<IMarshallerListener>();
		addMarshallerObjectListener(this);
		//addOutputCommandListener(this);
	}
	/**
	 * Adds {@link IMarshallerListener} Command event listener.
	 * @param listener {@link IMarshallerListener} the listener
	 */
	public final synchronized void addMarshallerObjectListener(IMarshallerListener listener)
	{
		marshaller_event_listeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDebugNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}


	/* (non-Javadoc)
     * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
     */
    @Override
	public void handleEvent(Event arg0) 
    {
		
	}
	/**
	 * @param outputStrem
	 * @throws JAXBException
	 * @throws InconsistenMessageException 
	 */
	public final void marshal(OutputStream outputStrem) throws JAXBException, InconsistenMessageException
	{
		if (getDestinationPath() != null) 
		{
			try 
			{
				jaxbContext = JAXBContext.newInstance(this.getClass());
				jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
				jaxbMarshaller.marshal(this, outputStrem);
				MarshallEvent(new MarshallerEventArgs(this, MarshallerEventType.OBJECT_MARSHALLED));
			}
			catch (JAXBException e) 
			{
				throw new JAXBException(e.getMessage(), e.getCause());
			}
		}
		else
		{
			throw new JAXBException(this.getClass().toString() + ".marshal(OutputStream): Destination path is null");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnControlCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) throws InconsistenMessageException 
	{
		CliCommandInputEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) throws InconsistenMessageException 
	{
		switch (e.getCommand()) 
		{
			case SET_UUID:
				this.setUuid(((IListener)e.getSource()).getUuid());
				break;
			case SET_DESTINATION_PATH:
				this.setDestinationPath(((IListener)e.getSource()).getDestinationPath());
				break;
			case SET_OPTIONS:
				this.setRuntimeOptions(((IListener)e.getSource()).getRuntimeOptions());
				break;
			case MESSAGE:
				System.out.println("\nCLI output message received: " + e.getSource().toString() + "\n");
			default:
				break;
		}
	}
	//	public abstract void OnCommandInput(Object sender, CliEventArgs e);
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IMarshallerListener#OnObjectMarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public abstract void OnObjectMarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException;
	
/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IMarshallerListener#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public abstract void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Removes {@link IMarshallerListener} Command event listener.
	 * @param listener {@link IMarshallerListener} the listener
	 */
	public final synchronized void removeMarshallerObjectListener(IMarshallerListener listener)
	{
		marshaller_event_listeners.remove(listener);
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		try 
		{
			jaxbContext = JAXBContext.newInstance(this.getClass());
			jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(this, sw);	
			String strXml = sw.toString();
			
			return strXml;
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
			return null;
		}
	}	
	/**
	 * @param inputStream
	 * @throws JAXBException
	 */
	public final void unmarshal(InputStream inputStream) throws JAXBException
	{
		if(this.getDestinationPath() != null)
		{
			try 
			{
				jaxbContext = JAXBContext.newInstance(this.getClass());
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				RML obj = (RML)jaxbUnmarshaller.unmarshal(inputStream);
				MarshallEvent(new MarshallerEventArgs(obj, MarshallerEventType.OBJECT_UNMARSHALLED));
			} 
			catch (Exception e) 
			{
				throw new JAXBException(e.getMessage(), e.getCause());
			}
		}
		else
		{
			throw new JAXBException(this.getClass().toString() + ".unmarshal(ObjectInputStream): Destination path is null");
		}
	}	
	/**
	 * Fires Control Command event method to parent.
	 * @param e {@link ControlEventArgs}  the arguments
     * @throws InconsistenMessageException 
	 */
	protected final void MarshallEvent(MarshallerEventArgs e) throws InconsistenMessageException 
	{
		for (IMarshallerListener listener : marshaller_event_listeners) 
		{
			try 
			{
				switch (e.getMarshallerEventType()) 
				{
					case OBJECT_MARSHALLED:
						listener.OnObjectMarshalled(this, e);
						break;
					case OBJECT_UNMARSHALLED:
						listener.OnObjectUnmarshalled(this, e);
						break;
					default:
						break;
					}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
		}
	}

	
}
