
package ch.supermafia.processing.toxiclibs.node;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class JFrameNodeLines extends JFrame
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JFrameNodeLines(NodeLines nodeLines)
		{
		this.nodeLines = nodeLines;
		creerComposant();
		addComposant();
		setColor();
		addListener();
		setPropriete();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void addListener()
		{
		jSliderNodeNumber.addChangeListener(new ChangeListener()
			{
				
				@Override
				public void stateChanged(ChangeEvent e)
					{
					nodeLines.setM(JFrameNodeLines.this.jSliderNodeNumber.getValue());
					}
			});
		jSliderRand.addChangeListener(new ChangeListener()
			{
				
				@Override
				public void stateChanged(ChangeEvent arg0)
					{
					nodeLines.setRand(JFrameNodeLines.this.jSliderRand.getValue());
					}
			});
		}
	
	private void setColor()
		{
		
		}
	
	private void creerComposant()
		{
		jSliderNodeNumber = new JSlider(JSlider.HORIZONTAL, 30, 70, 51);
		jSliderRand = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		
		}
	
	private void addComposant()
		{
		setLayout(new FlowLayout());
		add(jSliderNodeNumber);
		add(jSliderRand);
		}
	
	private void setPropriete()
		{
		this.setTitle("node lines");
		this.setLocation(100, 100);
		this.setSize(200, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true); //Par défaut: NON-visible, A mettre en dernier
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private JSlider jSliderNodeNumber;
	private JSlider jSliderRand;
	
	//output
	private NodeLines nodeLines;
	
	}
