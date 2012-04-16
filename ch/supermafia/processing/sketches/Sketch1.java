package ch.supermafia.processing.sketches;

import processing.core.PApplet;

public class Sketch1 extends PApplet
	{
	private float cellWidth;
	private float cellHeight;
	private float strokeValVertical;
	private float strokeValHorizontal;

	public void setup()
		{
		size(640,480);
		stroke(255);
		cellWidth=width/30;
		cellHeight=height/30;
		strokeValHorizontal=(float)(cellWidth*(255.0/width));
		strokeValVertical=(float)(cellHeight*(255.0/height));
		}
	public void draw()
		{
		background (0);
		drawGrid();
		
		}
	public void drawGrid()
		{
		//vertical lines
		for(float i=0,v=255;i<width;i+=cellWidth,v-=strokeValHorizontal)
			{
			stroke(v);
			for(float j=0;j<height;j++)
				{
				point(i,j);
				}
			}
		//horizontal lines
		for(float i = 0, v=255; i < height; i+=cellHeight,v-=strokeValHorizontal)
			{
			stroke(v);
			for(float j = 0; j < width; j++)
				{
				point(j,i);
				}
			}
		}
	 }
