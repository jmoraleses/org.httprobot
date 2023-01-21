package org.httprobot.common.tools;

import java.io.Console;
import java.util.EnumSet;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.exceptions.CellAlreadyExistsException;
import org.httprobot.common.exceptions.DataException;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.RmlException;
import org.httprobot.common.exceptions.UserException;
import org.httprobot.common.exceptions.ViewTableException;
import org.httprobot.common.interfaces.IListener;

public class CommandLineInterface 
{
	//private final static Logger LOGGER = Logger.getLogger(MyClass.class .getName()); 
	
	public static final EnumSet<RuntimeOptions> DefualtSettings = RuntimeOptions.DEFAULT_DEBUG;

	/**
	 * Prints a line on {@link Console}.
	 * @param parent {@link IListener} who calls for print a line.
	 * @param message the message
	 * @param saveLog if message is going to be saved on log.
	 */
	public static final void printLine(IListener parent, String message, Boolean saveLog)
	{
		EnumSet<RuntimeOptions> options  = parent.getRuntimeOptions();
		CliNamespace namespace = parent.getCliNamespace();	
		
		switch (namespace) 
		{
			case INET:
				if(options.contains(RuntimeOptions.INET_CHECK))
				{
					System.out.println("\nINET message output: \n" + message + "\n End\n");
					
					if(saveLog)
					{
						//Input INET Log file stream
					}
				}
				break;
			case RML:
				if(options.contains(RuntimeOptions.RML_CHECK))
				{
					System.out.println("\nRML message output: \n" + message + "\n End\n");
					
					if(saveLog)
					{
						//Input RML Log file stream
					}
				}
				break;
			case DATA:
				if(options.contains(RuntimeOptions.DATA_CHECK))
				{
					System.out.println("\nDATA message output: \n" + message + "\n End\n");
					
					if(saveLog)
					{
						//Input DATA Log file stream
					}
				}
				break;
			case UI:
				if(options.contains(RuntimeOptions.UI_CHECK))
				{
					System.out.println("\nUI message output: \n" + message + "\n End\n");
					
					if(saveLog)
					{
						//Input UI Log file stream
					}
				}
				break;
			case CLIENT:
				if(options.contains(RuntimeOptions.CLIENT_CHECK))
				{
					System.out.println("\nCLIENT message output: \n" + message + "\n End\n");
					
					if(saveLog)
					{
						//Input CLIENT Log file stream
					}
				}
				break;
			case SERVER:
				if(options.contains(RuntimeOptions.SERVER_CHECK))
				{
					System.out.println("\nSERVER message output: \n" + message + "\n End\n");
					
					if(saveLog)
					{
						//Input SERVER Log file stream
					}
				}
				break;
			default:
				break;
		}
		
		
	}
	/**
	 * Application's {@link UserException} throws.
	 * @param listener is who calls to throw the {@link UserException}
	 * @param ex the {@link UserException} type
	 * @throws UserException the {@link Exception}
	 */
	public static final void ThrowException(IListener listener, String message, Class<? extends UserException> ex) throws UserException, NotImplementedException, ViewTableException, CellAlreadyExistsException, DataException
	{
		EnumSet<RuntimeOptions> currentOptions = listener.getRuntimeOptions();
		
		//Check if throw user exceptions feature is activated
		if(currentOptions.contains(RuntimeOptions.THROW_EXCEPTIONS))
		{
			//Check if it's a user DataException
			if(ex.getClass().equals(DataException.class))
			{
				if(ex.getClass().equals(CellAlreadyExistsException.class))
				{
					throw new CellAlreadyExistsException(listener, message);
				}
				else if(ex.getClass().equals(ViewTableException.class))
				{
					throw new ViewTableException(listener, message);
				}
				else
				{
					throw new DataException(listener, message);	
				}
			}
			//Check if it's a user RmlException
			else if(ex.getClass().equals(RmlException.class))
			{
				if(ex.getClass().equals(NotImplementedException.class))
				{
					throw new NotImplementedException(listener, message);
				}
			}
			//Unknown Exception
			else
			{
				throw new UserException(listener, message);
			}
		}
	}
	public static final void ThrowNotImplementedException(IListener listener, String message) throws NotImplementedException
	{
		if(listener != null)
		{
			EnumSet<RuntimeOptions> currentOptions = listener.getRuntimeOptions();
			
			//Check if throw user exceptions feature is activated
			if(currentOptions.contains(RuntimeOptions.THROW_EXCEPTIONS))
			{
				throw new NotImplementedException(listener, message);
			}
		}
	}
	
	public static final void ThrowInconsistentMessageException(IListener listener, String message) throws InconsistenMessageException
	{
		EnumSet<RuntimeOptions> currentOptions = listener.getRuntimeOptions();
		
		//Check if throw user exceptions feature is activated
		if(currentOptions.contains(RuntimeOptions.THROW_EXCEPTIONS))
		{
			throw new InconsistenMessageException(listener, message);
		}
	}
	
	//public 
}
