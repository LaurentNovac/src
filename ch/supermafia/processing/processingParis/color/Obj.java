
package ch.supermafia.processing.processingParis.color;

import unlekker.util.UColorTool;

public class Obj
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Obj(ColorSketch parent)
		{
		this.parent = parent;
		x = parent.random(parent.width);
		y = parent.random(parent.height);
		sz = parent.random(40, 100);
		colors = new UColorTool();
		col = colors.getRandomColor();
		col=UColorTool.setAlpha(col, 70);
		initColors();

		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void draw()
		{
		parent.fill(col);
		parent.ellipse(x, y, sz, sz);
		}
	
	public void initColors()
		{

		//		colors.add(255, 0, 0);
		//		colors.add(255, 100, 0);
		//		colors.add(255, 200, 0);
		colors.addGradient(3, 10, "00FFFF", "FFFFFF");
		colors.addGradient(3, 10, parent.color(255, 0, 0), parent.color(255, 200, 0));
		colors.addGradient(3, 10, parent.color(0,100,0), parent.color(100,255,0));
		colors.generateColors(5,10);
		colors.drawColors(parent, 0, 0);
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private float x;
	private float y;
	private float sz;
	private int col;
	
	private ColorSketch parent;
	private UColorTool colors;
	
	}
