package org.httprobot.ui.common;

import java.io.InputStream;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlViewer extends DefaultHandler 
{
	private JTreeXml xmlViewerTree;
	DefaultTreeModel treeModel;
	int lineCounter;
	DefaultMutableTreeNode base = new DefaultMutableTreeNode("Title");
	
	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}
	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
	}
	
	
	public JTreeXml getXmlViewerTree() {
		return xmlViewerTree;
	}
	public void setXmlViewerTree(JTreeXml xmlViewerTree) {
		this.xmlViewerTree = xmlViewerTree;
	}
	public XmlViewer(String strXmlDoc)
	{
		this.createUI();
		xmlSetUp(strXmlDoc);
	}
	
	@Override
	public void startElement(String uri, String localName, String tagName, Attributes attr) throws SAXException {

		DefaultMutableTreeNode current = new DefaultMutableTreeNode(tagName);

		base.add(current);
		base = current;

		for (int i = 0; i < attr.getLength(); i++) {
			DefaultMutableTreeNode currentAtt = new DefaultMutableTreeNode(attr.getLocalName(i) + " = "
					+ attr.getValue(i));
			base.add(currentAtt);
		}
	}

	public void skippedEntity(String name) throws SAXException 
	{
//		System.out.println("Skipped Entity: '" + name + "'");
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		 base = new DefaultMutableTreeNode("XML Data");
		((DefaultTreeModel) xmlViewerTree.getModel()).setRoot(base);
	}

	public void characters(char[] ch, int start, int length) throws SAXException {

		String s = new String(ch, start, length).trim();
		if (!s.equals("")) {
			DefaultMutableTreeNode current = new DefaultMutableTreeNode("Data : " + s);
			base.add(current);

		}
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

		base = (DefaultMutableTreeNode) base.getParent();
	}

	public static void main(String[] args) 
	{	
	}

	@Override
	public void endDocument() throws SAXException {
		// Refresh JTree
		((DefaultTreeModel) xmlViewerTree.getModel()).reload();
		expandAll(xmlViewerTree);
	}

	public void expandAll(JTree tree) {
		int row = 0;
		while (row < tree.getRowCount()) {
			tree.expandRow(row);
			row++;
		}
	}

	public void xmlSetUp(String xmlString) 
	{
		try 
		{
			SAXParserFactory fact = SAXParserFactory.newInstance();
			SAXParser parser = fact.newSAXParser();

			InputStream is = null;

			try
			{							
				parser.parse(IOUtils.toInputStream(xmlString), this);
			}
			catch(Exception e)
			{
				// if any I/O error occurs
				e.printStackTrace();
			}
			finally
			{
				// releases system resources associated with this stream
				if(is!=null)
					is.close();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void createUI() 
	{
		this.treeModel = new DefaultTreeModel(base);
		this.xmlViewerTree = new JTreeXml(this.treeModel);
	}
}
