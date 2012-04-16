
package ch.supermafia.processing.processingParis.day2;

import processing.core.PApplet;
import processing.core.PConstants;
import unlekker.modelbuilder.UVec3;
import unlekker.modelbuilder.UVertexList;

public class Particle
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Particle(SketchRib parent)
		{
		this.parent = parent;
		path = new UVertexList();
		pos = new UVec3();
		path.add(pos);
		speed = parent.random(4, 8);
		rot = parent.random(-3, 3);
		dir = new UVec3(speed, 0);
		dir.rotate(PApplet.radians(rot));
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void draw()
		{
		rot += parent.random(-0.5f, 0.5f);
		dir.rotate(PApplet.radians(rot));
		pos.add(dir);
		
		UVec3 delta = new UVec3(pos).sub(path.v[path.n - 1]);
		float angle = delta.angle2D();
		UVec3 nrm = new UVec3(10, 0, 0);
		nrm.rotate(PConstants.HALF_PI + angle);
		parent.stroke(0, 255, 0);
		parent.line(pos.x, pos.y, pos.x + nrm.x, pos.y + nrm.y);
		path.add(pos);
		
		parent.stroke(255, 0, 0);
		parent.line(pos.x, pos.y, pos.x + dir.x, pos.y + dir.y);
		parent.stroke(255);
		path.drawQuadStrip(parent);
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
	private UVertexList path;
	private UVec3 pos;
	private UVec3 dir;
	private float speed;
	private float rot;
	private SketchRib parent;
	}
