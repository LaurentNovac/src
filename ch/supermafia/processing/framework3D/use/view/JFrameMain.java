
package ch.supermafia.processing.framework3D.use.view;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;

import ch.supermafia.processing.framework3D.use.Sketch3D;

public class JFrameMain extends JFrame
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JFrameMain()
		{
		d_createComponent();
		d_addComponent();
		d_addListener();
		d_setProperties();
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public Sketch3D getSketch3d()
		{
		return sketch3d;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void d_setProperties()
		{
		this.setTitle("_vac_");
		this.setLocation(0, 0);
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		}
	
	private void d_addListener()
		{
		// TODO Auto-generated method stub
		}
	
	private void d_addComponent()
		{
		setLayout(new BorderLayout());
		this.add(boxEast, BorderLayout.EAST);
		this.add(sketch3d,BorderLayout.CENTER);
		}
	
	private void d_createComponent()
		{
		
		jPanelControls = new JPanelControls();
		sketch3d=new Sketch3D();
		sketch3d.init();
		boxEast = Box.createVerticalBox();
		
		boxEast.add(jPanelControls);
		}
	

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private JPanelControls jPanelControls;//EAST
	private Sketch3D sketch3d;
	private Box boxEast;
	}
