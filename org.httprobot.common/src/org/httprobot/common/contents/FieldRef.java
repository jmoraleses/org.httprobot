package org.httprobot.common.contents;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Content;
import org.httprobot.common.definitions.Enums.DataType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * FieldRef message class. Inherits {@link Content}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class FieldRef extends Content
{
	/**
	 * 5672797536641842105L
	 */
	private static final long serialVersionUID = 5672797536641842105L;
	
	private Boolean compressed;
	private Integer compressThreshold;
	private DataType dataType;
	private Boolean indexed;
	private Boolean multiValued;
	private String name;
	private Boolean omitNorm;
	private Boolean omitPositions;
	private Boolean omitTermFreqAndPositions;
	private Boolean stored;
	private Boolean termVectors;
	private String type;
	/**
	 * @return the compressed
	 */
	public Boolean getCompressed() {
		return compressed;
	}
	/**
	 * @return the compressThreshold
	 */
	public Integer getCompressThreshold() {
		return compressThreshold;
	}
	/**
	 * @return the dataType
	 */
	public DataType getDataType() {
		return dataType;
	}
	/**
	 * @return the indexed
	 */
	public Boolean getIndexed() {
		return indexed;
	}
	/**
	 * @return the multiValued
	 */
	public Boolean getMultiValued() {
		return multiValued;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the omitNorm
	 */
	public Boolean getOmitNorm() {
		return omitNorm;
	}
	/**
	 * @return the omitPositions
	 */
	public Boolean getOmitPositions() {
		return omitPositions;
	}
	/**
	 * @return the omitTermFreqAndPositions
	 */
	public Boolean getOmitTermFreqAndPositions() {
		return omitTermFreqAndPositions;
	}
	/**
	 * @return the stored
	 */
	public Boolean getStored() {
		return stored;
	}
	/**
	 * @return the termVectors
	 */
	public Boolean getTermVectors() {
		return termVectors;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		this.setName(((FieldRef)e.getRml()).getName());
		this.setType(((FieldRef)e.getRml()).getType());
		this.setIndexed(((FieldRef)e.getRml()).getIndexed());
		this.setStored(((FieldRef)e.getRml()).getStored());
		this.setCompressed(((FieldRef)e.getRml()).getCompressed());
		this.setCompressThreshold(((FieldRef)e.getRml()).getCompressThreshold());
		this.setMultiValued(((FieldRef)e.getRml()).getMultiValued());
		this.setOmitNorm(((FieldRef)e.getRml()).getOmitNorm());
		this.setTermVectors(((FieldRef)e.getRml()).getTermVectors());
		this.setOmitTermFreqAndPositions(((FieldRef)e.getRml()).getOmitTermFreqAndPositions());
		this.setOmitPositions(((FieldRef)e.getRml()).getOmitPositions());
		this.setDataType(((FieldRef)e.getRml()).getDataType());
	}
	/**
	 * @param compressed the compressed to set
	 */
	@XmlAttribute
	public void setCompressed(Boolean compressed) {
		this.compressed = compressed;
	}
	/**
	 * @param compressThreshold the compressThreshold to set
	 */
	@XmlAttribute
	public void setCompressThreshold(Integer compressThreshold) {
		this.compressThreshold = compressThreshold;
	}
	/**
	 * @param dataType the dataType to set
	 */
	@XmlAttribute
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	/**
	 * @param indexed the indexed to set
	 */
	@XmlAttribute
	public void setIndexed(Boolean indexed) {
		this.indexed = indexed;
	}
	/**
	 * @param multiValued the multiValued to set
	 */
	@XmlAttribute
	public void setMultiValued(Boolean multiValued) {
		this.multiValued = multiValued;
	}
	/**
	 * @param name the name to set
	 */
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param omitNorm the omitNorm to set
	 */
	@XmlAttribute
	public void setOmitNorm(Boolean omitNorm) {
		this.omitNorm = omitNorm;
	}
	/**
	 * @param omitPositions the omitPositions to set
	 */
	@XmlAttribute
	public void setOmitPositions(Boolean omitPositions) {
		this.omitPositions = omitPositions;
	}
	/**
	 * @param omitTermFreqAndPositions the omitTermFreqAndPositions to set
	 */
	@XmlAttribute
	public void setOmitTermFreqAndPositions(Boolean omitTermFreqAndPositions) {
		this.omitTermFreqAndPositions = omitTermFreqAndPositions;
	}
	/**
	 * @param stored the stored to set
	 */
	@XmlAttribute
	public void setStored(Boolean stored) {
		this.stored = stored;
	}
	/**
	 * @param termVectors the termVectors to set
	 */
	@XmlAttribute
	public void setTermVectors(Boolean termVectors) {
		this.termVectors = termVectors;
	}
	/**
	 * @param type the type to set
	 */
	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
}