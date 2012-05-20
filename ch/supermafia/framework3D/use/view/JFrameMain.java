
package ch.supermafia.framework3D.use.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3DUnlekker;
import ch.supermafia.framework3D.use.frameworkDemo.processing.Sketch3D;

@SuppressWarnings("serial")
public class JFrameMain extends JFrame
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JFrameMain(Sketch3D sketch3d)
		{
		this.sketch3d = sketch3d;
		d_createComponent();
		d_addComponent();
		d_addListener();
		d_setProperties();
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
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
		ParametricMesh3DUnlekker m = sketch3d.getMesh();
		setLayout(new FlowLayout());
		
		}
	
	private void d_createComponent()
		{
		uMinSlider = new JSlider();
		uMinLabel = new JLabel("u min");
		
		uMaxSlider = new JSlider();
		uMaxLabel = new JLabel("u max");
		
		vMinSlider = new JSlider();
		vMinLabel = new JLabel("v min");
		
		vMaxSlider = new JSlider();
		vMaxLabel = new JLabel("v max");
		
		lerpSlider = new JSlider();
		lerpLabel = new JLabel("interpolation");
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Sketch3D sketch3d;
	private JSlider uMinSlider;
	private JLabel uMinLabel;
	
	private JSlider uMaxSlider;
	private JLabel uMaxLabel;
	
	private JSlider vMinSlider;
	private JLabel vMinLabel;
	
	private JSlider vMaxSlider;
	private JLabel vMaxLabel;
	
	private JSlider lerpSlider;
	private JLabel lerpLabel;
	/*
	 * 		gui.addSlider("uMin", uMin, mesh.getuMin(), mesh.getuMax());
		gui.addSlider("uMax", uMax, mesh.getuMin(), mesh.getuMax());
		gui.addSlider("vMin", vMin, mesh.getvMin(), mesh.getvMax());
		gui.addSlider("vMax", vMax, mesh.getvMin(), mesh.getvMax());
		gui.addSlider("distortionFactor", distortionFactor, 0.0f, 3.0f);
		gui.addSlider("scl", scl, 10, width);
		gui.addSlider("lerpParam", lerpParam, 0.0f, 1.0f);
		gui.addButton("toSTL");
	 */
	}
