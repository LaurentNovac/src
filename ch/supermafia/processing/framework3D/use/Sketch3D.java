
package ch.supermafia.processing.framework3D.use;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3DUnlekker;
import ch.supermafia.processing.framework3D.mathematics.Function.Function;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;
import unlekker.modelbuilder.UVec3;
import unlekker.util.UColorTool;
import unlekker.util.USimpleGUI;

@SuppressWarnings("serial")
public class Sketch3D extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, P3D);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		
		initGui();
		try
			{
			mesh = new ParametricMesh3DUnlekker(100, 100, this);
			uMin = mesh.getuMin();
			uMax = mesh.getuMax();
			vMin = mesh.getvMin();
			vMax = mesh.getvMax();
			distortionFactor = 1.0f;
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		scl = 40.0f;
		smooth();
		initColor();
		isColReverse = false;
		initGui();
		t = 0;
		speedRotCam = new UVec3();
		speedRotCam.x = random(0, PI / 20);
		speedRotCam.y = random(0, PI / 20);
		speedRotCam.z = random(0, PI / 20);
		isPrint=false;
		}
	
	public void draw()
		{
		float t = millis() / 500;
		//t=3000*sin(2*PI*(float)(millis()%1000)/100000);
		hint(ENABLE_DEPTH_TEST);
		pushMatrix();
		updateMesh();
		speedRotCam.x = sin(t * 1 / 10000);
		speedRotCam.y = sin(t * 1 / 10000);
		speedRotCam.z = sin(t * 1 / 10000);
		//nav.rot.add(speedRotCam);
		nav.doTransforms();
		background(255);
		lights();
		noStroke();
		scale(scl);
		updateColor();
		if (distortionFactor > 1.0f)
			{
			distortMesh();
			}
		mesh.draw();
		popMatrix();
		hint(DISABLE_DEPTH_TEST);
		
		noLights();
		noStroke();
		
		if (isPrint)
			{
			saveFrame("image-###.png");
			isPrint=false;
			}
		
		gui.draw();
		colorTool.drawColors(this, 0, height - 100);
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case '1':
				mesh.setFunction(Function.SINSQUARED);
				break;
			case '2':
				mesh.setFunction(Function.TRANGULOID);
				break;
			case '3':
				mesh.setFunction(Function.TWISTEDFANO);
				break;
			case '4':
				mesh.setFunction(Function.STEINER);
				break;
			case '5':
				mesh.setFunction(Function.CRESENT);
				break;
			case '6':
				mesh.setFunction(Function.KLEINCYCLOID);
				break;
			case '7':
				mesh.setFunction(Function.TRIAXIAL);
				break;
			case 'p':
				isPrint = true;
				break;
			default:
				break;
			}
		}
	
	public void reinit()
		{
		try
			{
			mesh.computeTable();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public ParametricMesh3DUnlekker getMesh()
		{
		return mesh;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void initGui()
		{
		gui = new USimpleGUI(this);
		gui.addButton("reinit");
		gui.addSlider("uMin", uMin, 4 * (float)(-Math.PI * 2), 4 * (float)(Math.PI * 2));
		gui.addSlider("uMax", uMax, 4 * (float)(-Math.PI * 2), 4 * (float)(Math.PI * 2));
		gui.addSlider("vMin", vMin, 4 * (float)(-Math.PI * 2), 4 * (float)(Math.PI * 2));
		gui.addSlider("vMax", vMax, 4 * (float)(-Math.PI * 2), 4 * (float)(Math.PI * 2));
		gui.addSlider("distortionFactor", distortionFactor, 1.0f, 3.0f);
		gui.addSlider("scl", scl, 10, width);
		gui.addButton("toSTL");
		
		}
	
	private void updateMesh()
		{
		try
			{
			mesh.setuMin(uMin);
			mesh.setuMax(uMax);
			mesh.setvMin(vMin);
			mesh.setvMax(vMax);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	@SuppressWarnings("unused")
	private void toSTL()
		{
		mesh.toSTL("Shape.stl");
		}
	
	private void distortMesh()
		{
		try
			{
			mesh.distort(distortionFactor);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	private void initColor()
		{
		colorTool = new UColorTool();
		colorTool.addGradient(10, 20, color(0, 100, 255), color(0, 0, 255));
		colorTool.generateColors();
		}
	
	private void updateColor()
		{
		if (t >= colorTool.n - 1)
			{
			t = colorTool.n - 1;
			isColReverse = true;
			}
		else if (t <= 0)
			{
			t = 0;
			isColReverse = false;
			}
		if (isColReverse)
			{
			fill(colorTool.colors[t]);
			t--;
			}
		else
			{
			fill(colorTool.colors[t]);
			t++;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ParametricMesh3DUnlekker mesh;
	private UNav3D nav;
	private USimpleGUI gui;
	public float uMin;
	public float uMax;
	private float vMin;
	private float vMax;
	private float distortionFactor;
	private float scl;
	private UColorTool colorTool;
	private boolean isColReverse;
	private int t;
	private UVec3 speedRotCam;
	private boolean isPrint;
	
	}
