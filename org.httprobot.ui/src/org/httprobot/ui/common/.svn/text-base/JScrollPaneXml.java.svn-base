package org.httprobot.ui.common;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;


public class JScrollPaneXml extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private XmlViewer xv = null;	
	private JTreeXml treeXml;

	public JTreeXml getTreeXml() {
		return treeXml;
	}
	public void setTreeXml(JTreeXml treeXml) {
		this.treeXml = treeXml;
	}
	/**
	 * This is the default constructor
	 */
	public JScrollPaneXml(String message) {
		super();
		this.xv = new XmlViewer(message);
		this.treeXml = new JTreeXml(this.xv.getTreeModel());
		initialize();
	}
	/**
	 * This is the default constructor
	 */
	public JScrollPaneXml(Component component, String message) {
		super(component);
		this.xv = new XmlViewer(message);
		this.treeXml = this.xv.getXmlViewerTree();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() 
	{
		ScrollPaneLayout spl = new ScrollPaneLayout();
		this.setLayout(spl);
		JViewport jvp = new JViewport();
		jvp.add(this.treeXml);
		this.setViewport(jvp);
		//jvp.setBounds(new Rectangle(0, 0, 1020, 760));
		jvp.setScrollMode(VERTICAL_SCROLLBAR_ALWAYS);
		spl.addLayoutComponent(ScrollPaneConstants.VIEWPORT , jvp);
		//this.revalidate();
	}
	


}
