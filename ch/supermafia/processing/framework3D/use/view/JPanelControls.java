
package ch.supermafia.processing.framework3D.use.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class JPanelControls extends JPanel
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JPanelControls()
		{
		d_createComponent();
		d_addComponent();
		d_addListener();
		d_setProperties();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void d_setProperties()
		{
		this.setBackground(Color.YELLOW);
		}
	
	private void d_addListener()
		{
		// TODO Auto-generated method stub
		JFrameMain jframeMain = (JFrameMain)this.getParent();
		//jframeMain.getSketch3d().getMesh().setuMin(uMin);
		}
	
	private void d_addComponent()
		{
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(4);
		gridLayout.setColumns(2);
		setLayout(gridLayout);
		add(labelUmin);
		add(jSliderUmin);
		
		}
	
	private void d_createComponent()
		{
		jSliderUmin = new JSlider();
		jSliderUmin.setMinimum(-100);
		jSliderUmin.setMaximum(100);
		jSliderUmin.setValue(-10);
		labelUmin=new JLabel("uMin");
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Tools
	private JLabel labelUmin;
	private JSlider jSliderUmin;
	//input
	private Box box;
	}
