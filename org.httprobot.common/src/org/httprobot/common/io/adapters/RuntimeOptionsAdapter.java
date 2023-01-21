/**
 * 
 */
package org.httprobot.common.io.adapters;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.common.definitions.Enums.RuntimeOptions;

/**
 * @author Joan
 *
 */
public class RuntimeOptionsAdapter extends XmlAdapter<String, EnumSet<RuntimeOptions>> {

	/**
	 * 
	 */
	public RuntimeOptionsAdapter() {
	}

	@Override
	public String marshal(EnumSet<RuntimeOptions> options_set) throws Exception 
	{			
		if(options_set != null)
		{
			return options_set.toString();
		}
		else
		{
			return null;
		}
	}

	@Override
	public EnumSet<RuntimeOptions> unmarshal(String cliOptions) throws Exception 
	{
		int start_index = cliOptions.indexOf("[") + 1;
		int end_index = cliOptions.indexOf("]");
		String[] options_array = cliOptions.substring(start_index, end_index).trim().split(", ");
		ArrayList<RuntimeOptions> options_list = new ArrayList<RuntimeOptions>();
		
		for (String strOption : options_array) 
		{
			try
			{
				RuntimeOptions option = RuntimeOptions.valueOf(strOption);
				
				if (option != null) 
				{
					options_list.add(option);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
		
		
		EnumSet<RuntimeOptions> options_set = RuntimeOptions.toEnumSet(options_list);		
	
		return options_set;
	}

//	@Override
//	public ArrayList<DebugOptions> marshal(EnumSet<DebugOptions> options_set)
//	{

//		}
//		else
//		{
//			return null;
//		}		
//	}
//
//	@Override
//	public EnumSet<DebugOptions> unmarshal(ArrayList<DebugOptions> options_array)
//	{
//		
//	}
}
