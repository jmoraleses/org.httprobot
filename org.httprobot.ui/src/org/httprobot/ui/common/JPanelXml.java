package org.httprobot.ui.common;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Color;

public class JPanelXml extends JPanel {

	private static final long serialVersionUID = 1L;
	//private XmlViewer xv = null;
	private JScrollPaneXml scrollPane = null;
	/**
	 * This is the default constructor
	 */
	public JPanelXml(String message) 
	{
		super();
		this.scrollPane = new JScrollPaneXml(message);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() 
	{
		//this.setPreferredSize(new Dimension(2000, 2000));
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		this.setLayout(null);		
		//gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;
		this.setBackground(new Color(28, 131, 164));
		gridBagConstraints.fill = GridBagConstraints.BOTH;	
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;		
		
		gbl.setConstraints(this.scrollPane, gridBagConstraints);
		this.add(this.scrollPane);
		this.revalidate();
	}
}
