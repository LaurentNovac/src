
package ch.supermafia.processing.waves.lissajous;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class JFrameLissajous extends JFrame
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JFrameLissajous(LissaJousSketch sketch)
		{
		this.sketch = sketch;
		creerComposant();
		addComposant();
		addListener();
		setPropriete();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void addListener()
		{
		
		phiSlider.addChangeListener(new ChangeListener()
			{
				
				@Override
				public void stateChanged(ChangeEvent arg0)
					{
					sketch.setPhi(JFrameLissajous.this.phiSlider.getValue());
					}
			});
		}
	
	private void creerComposant()
		{
		
		phiLabel = new JLabel("phi");
		phiSlider = new JSlider();
		}
	
	private void addComposant()
		{
		setLayout(new FlowLayout());
		
		add(phiLabel);
		phiSlider.setMinimum(0);
		phiSlider.setMaximum(360);
		phiSlider.setValue((int)sketch.getPhi());
		add(phiSlider);
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
	
	private LissaJousSketch sketch;
	
	private JLabel phiLabel;
	private JSlider phiSlider;
	}
