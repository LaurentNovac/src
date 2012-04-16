
package ch.supermafia.processing.dentelle;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class DentelleCamille extends PApplet
	{
	
	private float stepA = 7;
	private float angle = PI / stepA;
	private float n = 2 * PI / angle;
	private List<Float> dentellePosX = new ArrayList<Float>();
	private List<Float> dentellePosY = new ArrayList<Float>();
	
	public void setup()
		{
		stroke(255);
		strokeWeight(1);
		strokeCap(ROUND);
		size(1680, 1050);
		smooth();
		background(0);
		}
	
	public void draw()
		{
		background(0);
		translate(width / 2, height / 2);
		scale(2);
		drawMotifCirculaire();
		}
	
	public void mouseClicked()
		{
		float x = mouseX;
		float y = mouseY;
		pushMatrix();
		strokeWeight(1);
		translate(x, y);
		drawMotifCirculaire();
		dentellePosX.add(x);
		dentellePosY.add(y);
		popMatrix();
		strokeWeight(10);
		}
	
	/**
		 A pattern is the basic shape that will be repeated in the 2D space to draw some "Dentelle"
	 */
	
	public void drawPattern()
		{
		int p1x, p1y, p2x, p2y;
		int stepW = 12;
		int stepH = 8;
		p1x = -width / stepW;
		p1y = height / stepH;
		p2x = width / stepW;
		p2y = height / stepH;
		line(0, 0, p1x, p1y);
		line(0, 0, p2x, p2y);
		line(p1x, p1y, p2x, p2y);
		}
	
	//un autre essai de pattern
	public void drawPattern2()
		{
		int stepW = 12;
		int stepH = 8;
		int p1x, p1y;
		p1x = -width / stepW;
		p1y = height / stepH;
		ellipse(p1x, p1y, 1, 1);
		}
	
	//un autre essai de pattern
	public void drawPattern3()
		{
		int stepW = 12;
		int stepH = 8;
		int p1x, p1y;
		p1x = -width / stepW;
		p1y = height / stepH;
		line(p1x, p1y, p1x, p1y);
		}
	
	//un autre essai de pattern
	public void drawPattern4()
		{
		float step = 1;
		float angle = 2 * PI / step;
		
		float radius = 100;
		float secondRadius = 30;
		float points = 10;
		for(int i = 0; i < points; i++)
			{
			float actualRadius;
			if (i % 2 == 0 && secondRadius != 0)
				{
				actualRadius = secondRadius;
				}
			else
				{
				actualRadius = radius;
				}
			float px = actualRadius * cos(angle);
			float py = actualRadius * sin(angle);
			
			line(0, 0, px, py);
			angle += points;
			}
		}
	
	//============================================//
	
	public void drawMotif()
		{
		int step = 30;
		for(int i = 0; i < n; i++)
			{
			pushMatrix();
			translate(0, height / step);
			drawPattern();
			popMatrix();
			rotate(angle);
			}
		}
	
	//============================================//
	
	public void drawMotifCirculaire()
		{
		for(int i = 0; i < n; i++)
			{
			int step = 12;
			pushMatrix();
			translate(0, height / step);
			drawMotif();
			popMatrix();
			rotate(angle);
			}
		}
	
	public void drawLineAnim(float p1x, float p1y, float p2x, float p2y, int t)
		{
		float alpha = atan((p2y - p1y) / (p2x - p1x));
		float r = t;
		float x = r * cos(alpha);
		float y = r * sin(alpha);
		point(x, y);
		}
	}
