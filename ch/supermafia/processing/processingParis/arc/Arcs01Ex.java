
package ch.supermafia.processing.processingParis.arc;

import processing.core.PApplet;
import unlekker.util.USimpleGUI;

@SuppressWarnings("serial")
public class Arcs01Ex extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(600, 600);
		smooth();
		gui = new USimpleGUI(this);
		initGui();
		reinit();
		}
	
	public void draw()
		{
		background(255);
		pushMatrix();
		translate(width / 2, height / 2);
		noFill();
		stroke(0);
		for(int i = 0; i < min(n, arcs.length); i++)
			{
			arcs[i].draw();
			}
		popMatrix();
		gui.draw();
		}
	
	public void keyPressed()
		{
		if (key == ' ') reinit();
		}
	
	public void initGui()
		{
		n = (int)random(20, 100);
		minRad = 0.2f;
		radDelta = 0.3f;
		minDeg = 30;
		degDelta = 30;
		radMode=1;
		stepsMode=1;
		gui.addSlider("n", n, 1, 200);
		gui.addSlider("minDeg", minDeg, 1, 180);
		gui.addSlider("degDelta", degDelta, 1, 180);
		gui.addSlider("minRad", minRad, 0, 0.5f);
		gui.addSlider("radDelta", radDelta, 0.01f, 0.5f);
		gui.addSlider("radMode", radMode, 0.01f, 1);
		gui.addSlider("stepsMode", stepsMode, 0.1f, 5);
		gui.addButton("reinit");
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	
	public float getStepsMode()
		{
		return stepsMode;
		}

	public float getMinDeg()
		{
		return minDeg;
		}
	
	public float getDegDelta()
		{
		return degDelta;
		}
	
	public float getMinRad()
		{
		return minRad;
		}
	
	public float getRadDelta()
		{
		return radDelta;
		}
	
	public float getRadMode()
		{
		return radMode;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void reinit()
		{
		arcs = new Arc[n];
		
		for(int i = 0; i < n; i++)
			{
			arcs[i] = new Arc(this);
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Arc[] arcs;
	public int n;
	private USimpleGUI gui;
	private float minDeg;
	private float degDelta;
	private float minRad;
	private float radDelta;
	private float radMode;
	private float stepsMode;

	}
