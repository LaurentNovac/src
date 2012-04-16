
package ch.supermafia.processing.toxiclibs.flyingLeaves;

import processing.core.PApplet;
import processing.core.PImage;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;

public class Leaf extends VerletParticle2D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Leaf(Vec2D pos, PImage image, PApplet parent)
		{
		super(pos);
		this.parent=parent;
		this.image = image;
		}
	
	public Leaf(Leaf source)
		{
		this(source, source.image,source.parent);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Leaf [image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
		}
	
	public void drawMe()
		{
		parent.image(image, this.x(), this.y());
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input/output
	private PApplet parent;
	private PImage image;
	
	}
