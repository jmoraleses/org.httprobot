/**
 * 
 */
package org.httprobot.core.contents.xml.fields;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.DataType;

/**
 * @author joan
 *
 */
@XmlRootElement
public class HttpAddress extends FieldRef
{

	/**
	 * -3079876396234998932L
	 */
	private static final long serialVersionUID = -3079876396234998932L;
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "httpAddress";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType() {
		
		return DataType.LINK;
	}
	/**
	 * 
	 */
	public HttpAddress() {

		this.setUuid(UUID.fromString("4b7dc65e-c22f-4f05-8b56-02fc5b8a19ff"));
	}
}