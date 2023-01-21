package org.httprobot.ui.common;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;

public class JTreeXml extends JTree {

	private static final long serialVersionUID = 1L;
	//private XmlViewer xv = null;
	/**
	 * This is the default constructor
	 */
	public JTreeXml(TreeModel treeModel) {
		super(treeModel);
		//this.xv = new XmlViewer(Set_Test_Message());
		initialize();
	}
	/**
	 * This is the default constructor
	 */
	public JTreeXml() {
		super();
		//this.xv = new XmlViewer(Set_Test_Message());
		//this.(xv.getTreeModel());
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(200, 200);
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		this.setLayout(gbl);	
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(this, gridBagConstraints);
		//this.add(arg0)
		this.revalidate();
	}
}
