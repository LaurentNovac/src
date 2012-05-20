
package ch.supermafia.processing.vale;

import pitaru.sonia_v2_9.LiveInput;
import pitaru.sonia_v2_9.Sonia;
import processing.core.PApplet;
import processing.core.PImage;

public class VScratch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		//   try {
		//     r=new Robot();//robot r  je ne sais pas � quoi ca sert
		//   } catch (Exception e) {}
		colorMode(HSB);
		levels = new int[ceil(height / boxHeight) + 1][ceil(width / boxWidth) + 1];
		txt = new int[ceil(height / boxHeight) + 1][ceil(width / boxWidth) + 1];
		amps = new int[ceil(width / boxWidth) + 1];
		size(800, 600, OPENGL);
		Sonia.start(this, 22025); // Start Sonia engine.
		LiveInput.start(64); // Start LiveInput and return 256 FFT frequency bands.
		
		txtIm[0] = loadImage("0.png");
		println("image width:" + txtIm[0].width);
		txtIm[1] = loadImage("1.png");
		txtIm[2] = loadImage("2.png");
		txtIm[3] = loadImage("3.png");
		fontIm[0] = loadImage("ascii_gray.png");
		fontHeight = fontIm[0].height;
		fontWidth = fontIm[0].width / (lastChar - firstChar + 1);
		lastMousePos = mouseX;
		noCursor();
		}
	
	public void draw()
		{
		LiveInput.getSpectrum();
		
		int amp = round(mouseSpeed * sensAmp);//
		
		if (direction == 0)
			{
			for(int j = 0; j < levels.length; j++)
				{
				for(int i = levels[0].length - 1; i > 0; i--)
					{
					levels[j][i] = levels[j][i - 1];
					}
				levels[j][0] = (int)(sensSpect * LiveInput.spectrum[levels.length - j * freqStep]);
				}
			for(int i = amps.length - 1; i > 0; i--)
				{
				amps[i] = amps[i - 1];
				}
			amps[0] = amp;
			}
		else
			{
			for(int j = 0; j < levels.length; j++)
				{
				for(int i = 0; i < levels[0].length - 1; i++)
					{
					levels[j][i] = levels[j][i + 1];
					}
				levels[j][levels[0].length - 1] = (int)(sensSpect * LiveInput.spectrum[levels.length - j * freqStep]);
				}
			for(int i = 0; i < amps.length - 1; i++)
				{
				amps[i] = amps[i + 1];
				}
			amps[amps.length - 1] = amp;
			}
		
		// update txt if scrolling
		if (scrolling == 1)
			{
			/*      for (int j=0; j<fontHeight; j++) {
			 for (int i=0; i<txt[0].length-1; i++) {
			 txt[j][i]=typedtxt[j][i];
			 }
			 }*/
			if (scrollFramesCounter-- <= 0)
				{
				scrollFramesCounter = fontSpeed;
				for(int j = 0; j < txt.length; j++)
					{
					for(int i = 0; i < txt[0].length - 1; i++)
						{
						txt[j][i] = txt[j][i + 1];
						}
					if (j < fontHeight && typedColumnCounter < typedtxt[0].length)
						{
						txt[j][txt[0].length - 1] = typedtxt[j][typedColumnCounter];
						}
					else
						{
						txt[j][txt[0].length - 1] = fontIm[font].pixels[0] & 255;
						}
					}
				typedColumnCounter++;
				if (typedColumnCounter >= typedtxt[0].length)
					{
					typedColumnCounter = 0;
					}
				}
			}
		
		background(0, 0, 0);
		int l = 0;
		int m = 0;
		for(int y = 0; y < height; y += boxHeight)
			{
			l = 0;
			for(int x = 0; x < width; x += boxWidth)
				{
				strokeWeight(min(50, levels[m][l] * txt[m][l] / 255));
				drawBox(x + mode3d * x / 10 * levels[m][l], y, amps[l]);///////////////////////////////////////////////////////////////////////////////////////////
				l++;
				}
			m++;
			}
		l = 0;
		m = 0;
		if (blackGrid > 0)
			{
			for(int y = 0; y < height; y += boxHeight)
				{
				l = 0;
				for(int x = 0; x < width; x += boxWidth)
					{
					drawBox2(x, y, amps[l]);
					l++;
					}
				m++;
				}
			}
		}
	
	public void keyPressed() 
		{ 
		  if (key == CODED) {
		    println(keyCode);
		    // sensibility//UP et DOWN param�tre le coh�ficien de sensAmp en multipliant(UP) ou divisant(DOWN) sa valeur par 1.05
		    if (keyCode == UP) {
		      sensAmp*=1.05;
		    }
		    if (keyCode == DOWN) {
		      sensAmp/=1.05;
		    }
		    // sensibility//LEFT et RIGHT param�trent le coh�ficien de sensSpect en multipliant(LEFT) ou divisant(RIGHT) sa valeur par 1.05
		    if (keyCode == LEFT) {
		      sensSpect*=1.05;
		    }
		    if (keyCode == RIGHT) {
		      sensSpect/=1.05;
		    }
		    // commands: f1 - f3
		    if (keyCode == 114)//
		    {
		      if (direction == 0) { 
		        direction = 1;
		      } 
		      else { 
		        direction = 0;
		      }
		    }
		    if (keyCode == 113)//F2 d�termine la taille (zoom1 ou zoom2) des boites
		    {
		      if (boxWidth == zoom1) { 
		        boxWidth = zoom2; 
		        boxHeight = zoom2;
		      } 
		      else { 
		        boxWidth = zoom1; 
		        boxHeight = zoom1;
		      }
		    }
		    // commands: 3d
		    if (keyCode == 17)
		    {
		      if (mode3d == 0) { 
		        mode3d = 1;
		      } 
		      else { 
		        mode3d = 0;
		      }
		    }
		    // commands: black grid
		    if (keyCode == 34) // PG DOWN
		    {
		      if (blackGrid == 0) { 
		        blackGrid = 1;
		      } 
		      else { 
		        blackGrid = 0;
		      }
		    }
		    if (keyCode == 112)//change le framerate
		    {
		      if (fr == 35) {
		        fr = 20;
		      }
		      else {
		        fr=35;
		      }
		    }
		    // if alt was pushed, assemble text and go into scroll mode
		    if (keyCode == ALT)
		    { 
		      if (scrolling==0) {//si la valeur de l'int�grale scrolling est �gale a 0 le texte ne d�file pas
		        typedtxt=new int[fontHeight][fontWidth*typed.length()];//pas s�r: entr�e de la longeur 
		        for (int i=0; i<typed.length(); i++) if (typed.charAt(i)>=firstChar && typed.charAt(i)<=lastChar) {
		          for (int x=0; x<fontWidth; x++) {
		            for (int y=0; y<fontHeight; y++) {
		              typedtxt[y][x+i*fontWidth]=fontIm[font].pixels[y*fontIm[font].width+x+fontWidth*(typed.charAt(i)-firstChar)]&255;
		            }
		          }
		        }
		        typed="";
		        typedColumnCounter = 0;
		        scrolling=1;
		      } 
		      else {
		        scrolling=0;
		      }
		    }
		    // images: f5 - f8
		    if (keyCode >= 116 && keyCode < 116+txtIm.length) {
		      for (int j=txt.length-1; j>=0; j--) {
		        for (int i=txt[0].length-1; i>=0; i--) {
		          txt[j][i]=txtIm[keyCode-116].pixels[j*txtIm[keyCode-116].width+i]&255;
		        }
		      }
		      scrolling=0;
		    }
		  } 
		  else {
		    typed+=key;
		  }
		  // saving and loading
		  if (key == CODED && keyCode>=120 && keyCode <=123) {
		    if (saveMode==1) {
		      String strings[]= {
		        "boxWidth:", (new Integer(boxWidth)).toString(), "boxHeight:", (new Integer(boxHeight)).toString(), 
		        "framerate:", (new Integer(fr)).toString(), "sensAmp:", (new Float(sensAmp)).toString(), "sensSpect:", (new Float(sensSpect)).toString()
		      };
		      saveStrings(keyCode+".txt", strings);
		      saveMode=0;
		    } 
		    else {
		      if (keyCode==123) {
		        saveMode=1;
		      } 
		      else {
		        String strings[]=loadStrings(keyCode+".txt");
		        boxWidth=Integer.valueOf(strings[1]);
		        boxHeight=Integer.valueOf(strings[3]);
		        fr=Integer.valueOf(strings[5]);

		        sensAmp=Float.valueOf(strings[7]);
		        sensSpect=Float.valueOf(strings[9]);
		      }
		    }
		  }
		}
	
	public void mouseMoved()
		{
		mouseSpeed = lastMousePos - mouseX;
		lastMousePos = mouseX;
		if (mouseX <= 0 || mouseX >= width)
			{
			//r.mouseMove(width/2,height/2);
			lastMousePos = width / 2;
			}
		}
	
	// Safely close the sound engine upon Browser shutdown.
	public void stop()
		{
		Sonia.stop();
		super.stop();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawBox(int x, int y, int level)
		{
		stroke(255);
		line(x, y + level + boxWidth / 2, x + boxWidth, y - level + boxWidth / 2);
		}
	
	private void drawBox2(int x, int y, int level)
		{
		stroke(mouseSpeed / 100);
		
		// strokeWeight(mouseSpeed);
		// stroke (mouseSpeed);
		line(x, y + mouseSpeed / 10 + boxWidth, x + boxWidth, y - mouseSpeed * 2 + boxWidth);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	int mouseSpeed = 0;//int�grale pour la vitesse de la souris
	int mode3d = 0;//int�grale coh�ficient pour passer en mode 3d
	int blackGrid = 0;//int�grale coh�ficient pour la trame noire ( de superpose a la blanche pour montrer
	int lastMousePos;//int�grale d�termine la position de la souris
	int zoom1 = 32;//int�grale d�terminant la grandeur 1 (zoom1) des boites
	int zoom2 = 800;//int�grale d�terminant la grandeur 2 (zoom2) des boites
	int boxWidth = zoom1;//int�grale d�terminant la largeur des boites
	int boxHeight = zoom1;//int�grale d�terminant la hauteur des boites
	float sensAmp = 0.2f;//int�grale d�terminant l'inclinaison verticale (traduisant la puissance volum�trique) des lignes
	float sensSpect = 0.2f;//int�grale d�terminant l'�paisseur (traduisant la puissance volum�trique) des lignes
	float levelSensitivity = (boxWidth + boxHeight) * 50;//?
	int freqStep = 1;
	int levels[][];
	int txt[][];
	int typedtxt[][];// pas s�r mais �a doit etre l'assemblage des characht�res ascii (tapp�s sur le clavier)
	int amps[];//variable issues de la vitesse de la souri mutipli�e par sensAmp
	int fr;//variable pour changer le framerate
	int direction = 0;//initialise le sens de d�filement de la trame 
	String typed = "";//texte entr� avec le clavier
	char firstChar = ' ';//initialisation des charact�res ascioi enploy� pour le monte texte 
	char lastChar = 'z';//idem
	PImage txtIm[] = new PImage[4];//initialise l'image (ici la 4 est totalement transparente
	PImage fontIm[] = new PImage[1];//??
	int font = 0;//?
	int fontHeight, fontWidth;//chaque charact�re typo ce trouve dans un carr�
	int fontSpeed = 3; // 1 -> fast, >1 -> slow
	int scrolling = 0;//initialise le mode scrollling � 0 = immobile
	int scrollFramesCounter = 0;//?
	int typedColumnCounter = 0;//?
	int saveMode = 0;//initialise le mode de sauvegarde (F12)
	}
