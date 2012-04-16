package ch.supermafia.processing.sketches;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import processing.core.PApplet;

public class Sketch2 extends PApplet
	{
	private int nLines=100;
	private List<Float> wCoord = new ArrayList<Float>();
	private List<Float> hCoord = new ArrayList<Float>();
	public void setup()
		{
		size(500,500);
		stroke(255);
		strokeWeight(5);
		smooth();
		generateRandCoordinate();
		}
	public void generateRandCoordinate()
		{
		for(int i = 0; i <nLines; i++)
			{
			float rCoordW=random(width);
			float rCoordH=random(height);
			wCoord.add(rCoordW);
			hCoord.add(rCoordH);
			}
		}
	public void draw()
		{
		background(0);
		ListIterator<Float> it = wCoord.listIterator();
		ListIterator<Float> it2 = hCoord.listIterator();
		beginShape(LINES);
		while(it.hasNext())
			{
			while(it2.hasNext())
				{
				vertex(it.next().floatValue(),it2.next().floatValue());
				}
			}
		endShape();
		}
	 }
