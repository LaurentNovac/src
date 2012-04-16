/**
 * Based on Spherical Harmonics by Paul Bourke
 * http://paulbourke.net/geometry/sphericalh/
 */

package ch.supermafia.processing.toxiclibs.sketchs;

import ch.supermafia.processing.toxiclibs.surfaceFunction.PisotTriaxial;
import ch.supermafia.processing.toxiclibs.surfaceFunction.TranguloidTrefoil;
import processing.core.PApplet;
import toxi.geom.mesh.SphericalHarmonics;
import toxi.geom.mesh.SurfaceMeshBuilder;
import toxi.processing.ToxiclibsSupport;
public class SurfaceMesh extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, P3D);
		gfx = new ToxiclibsSupport(this);
		m = new float[8];
		for(int i = 0; i < 8; i++)
			{
			m[i] = (int)random(9);
			}
		doSpherical = true;
		lights();
		noCursor();
		}
	
	public void draw()
		{
		int t = millis() / 10;
		if (t % 10 == 0)
			{
			int s = t % 8;
			m[s] = (int)random(9);
			}
		background(0);
		translate(width / 2, height / 2);
		rotateX(mouseY * 0.01f);
		rotateY(mouseX * 0.01f);
		
		if (doSpherical)
			{
			sphericalMesh();
			}
		else
			{
			//tranguloidTrefoil();
			pisotTriaxial();
			}
		}

	public void mousePressed()
		{
		redraw();
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 's':
				for(int i = 0; i < 8; i++)
					{
					m[i] = (int)random(9);
					}
				break;
			case 't':
				doSpherical=!doSpherical;
				break;
			default:
				break;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void sphericalMesh()
		{
		SphericalHarmonics sphericalHarmonics = new SphericalHarmonics(m);
		meshBuilder = new SurfaceMeshBuilder(sphericalHarmonics);
		scale(80);
		gfx.mesh(meshBuilder.createMesh(100));
		}
	
	private void pisotTriaxial()
		{
		PisotTriaxial pisotTriaxial = new PisotTriaxial();
		meshBuilder=new SurfaceMeshBuilder(pisotTriaxial);
		scale(80);
		
		gfx.mesh(meshBuilder.createMesh(100));
		}
	
	private void tranguloidTrefoil()
		{
		TranguloidTrefoil tranguloidTrefoil = new TranguloidTrefoil();
		meshBuilder = new SurfaceMeshBuilder(tranguloidTrefoil);
		scale(80);
		gfx.mesh(meshBuilder.createMesh(100));
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static ToxiclibsSupport gfx;
	private static SurfaceMeshBuilder meshBuilder;
	private static float[] m;
	private static boolean doSpherical;
	}
