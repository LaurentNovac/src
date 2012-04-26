
package ch.supermafia.jogl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class UseJoglTest
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public static void main(String[] args)
		{
		main();
		}
	
	public static void main()
		{
		GLProfile glp = GLProfile.getDefault();
		GLProfile.initSingleton();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		JFrame jFrame = new JFrame("Hello JOGL");
		jFrame.setSize(400, 400);
		jFrame.add(canvas);
		jFrame.setVisible(true);
		
		jFrame.addWindowListener(new WindowAdapter()
			{
				
				@Override
				public void windowClosing(WindowEvent arg0)
					{
					System.exit(0); // 0 normal, -1 anormal
					}
				
			});
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
