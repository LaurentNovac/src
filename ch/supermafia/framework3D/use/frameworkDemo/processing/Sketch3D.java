
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import java.util.List;

import controlP5.ControlP5;
import controlP5.ControlWindow;
import controlP5.ControllerInterface;
import ch.supermafia.framework3D.geometry.matrix.Matrix4x4Rotation;
import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3DUnlekker;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.mathematics.Function.Cresent;
import ch.supermafia.framework3D.mathematics.Function.HeightMap;
import ch.supermafia.framework3D.mathematics.Function.KleinCycloid;
import ch.supermafia.framework3D.mathematics.Function.SinDistSquared;
import ch.supermafia.framework3D.mathematics.Function.SinDistSquaredToTranguloid;
import ch.supermafia.framework3D.mathematics.Function.Steiner;
import ch.supermafia.framework3D.mathematics.Function.TranguloidToTriaxial;
import ch.supermafia.framework3D.mathematics.Function.TranguloidTrefoil;
import ch.supermafia.framework3D.mathematics.Function.Triaxial;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;
import unlekker.modelbuilder.UVec3;
import unlekker.util.UColorTool;
import unlekker.util.USimpleGUI;
import processing.opengl.*;

@SuppressWarnings("serial")
public class Sketch3D extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, OPENGL);
		gfx = new ProcessingGfx(this);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		smooth();
		try
			{
			mesh = new ParametricMesh3DUnlekker(150, 150, new TranguloidTrefoil(), this);
			uMin = mesh.getuMin();
			uMax = mesh.getuMax();
			vMin = mesh.getvMin();
			vMax = mesh.getvMax();
			lastuMin = uMin;
			lastvMin = vMin;
			lastuMax = uMax;
			lastvMax = vMax;
			distortionFactor = 1.0f;
			lerpParam = 0.0f;
			lastLerpParam = lerpParam;
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		scl = 40.0f;
		initColor();
		isColReverse = false;
		t = 0;
		isPrint = false;
		isQuad = false;
		updateGui();
		controlWindow = new ControlWindow(gui, this);
		rotY = 0.0f;
		strokeWeight(1.0f);
		}
	
	public void draw()
		{
		hint(ENABLE_DEPTH_TEST);
		pushMatrix();
		updateMesh();
		nav.doTransforms();
		background(255);
		lights();
		scale(scl);
		updateColor();
		if (distortionFactor > 1.0f)
			{
			distortMesh();
			}
		if (isQuad)
			{
			gfx.mesh(mesh);
			}
		else
			{
			gfx.meshPoints(mesh);
			}
		popMatrix();
		hint(DISABLE_DEPTH_TEST);
		
		noLights();
		
		if (isPrint)
			{
			toSTL();
			isPrint = false;
			}
		
		gui.draw();
		colorTool.drawColors(this, 0, height - 100);
		text(frameRate + "", width - 100, height - 10);
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case '1':
				mesh.setFunc(new SinDistSquared());
				reinit();
				break;
			case '2':
				mesh.setFunc(new TranguloidTrefoil());
				reinit();
				break;
			case '3':
				mesh.setFunc(new Steiner());
				reinit();
				break;
			case '4':
				mesh.setFunc(new Cresent());
				reinit();
				break;
			case '5':
				mesh.setFunc(new KleinCycloid(3, 3, 3));
				reinit();
				break;
			case '6':
				mesh.setFunc(new Triaxial());
				reinit();
				break;
			case '7':
				mesh.setFunc(new TranguloidToTriaxial());
				reinit();
				break;
			case '8':
				mesh.setFunc(new SinDistSquaredToTranguloid());
				reinit();
				break;
			case '9':
				mesh.setFunc(new HeightMap("heightmapCapa.jpg"));
				scl = 1;
				reinit();
				break;
			case '+':
				lerpParam += 0.1f;
				if (lerpParam >= 1.0f)
					{
					lerpParam = 1.0f;
					}
				break;
			case '-':
				lerpParam -= 0.1f;
				if (lerpParam <= 0.0f)
					{
					lerpParam = 0.0f;
					}
				break;
			case 'p':
				isPrint = true;
				break;
			case 's':
				mesh.scale(2.0f);
				break;
			case 't':
				mesh.translate(new Vec3D(1.0f, 0.0f, 0.0f));
				break;
			case 'x':
				mesh.rotateX(radians(90));
				break;
			case 'y':
				mesh.rotateY(radians(90));
				break;
			case 'z':
				mesh.rotateZ(radians(90));
				break;
			case 'i':
				mesh.applyIdentity();
				break;
			default:
				break;
			}
		if (key >= 48 && key <= 57)
			{
			updateGui();
			}
		}
	
	public void reinit()
		{
		mesh.computeTable();
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
	
	private void updateGui()
		{
		uMin = mesh.getuMin();
		uMax = mesh.getuMax();
		vMin = mesh.getvMin();
		vMax = mesh.getvMax();
		mesh.setuMin(uMin);
		mesh.setuMax(uMax);
		mesh.setvMin(vMin);
		mesh.setvMax(vMax);
		gui = new ControlP5(this);
		gui.addButton("reinit");
		gui.addSlider("uMin", mesh.getuMin(), mesh.getuMax());
		gui.addSlider("uMax", mesh.getuMin(), mesh.getuMax());
		gui.addSlider("vMin", mesh.getvMin(), mesh.getvMax());
		gui.addSlider("vMax", mesh.getvMin(), mesh.getvMax());
		gui.addSlider("scl", 10, width);
		gui.addSlider("lerpParam", 0.0f, 1.0f);
		gui.addButton("quad");
		gui.addButton("points");
		gui.addButton("toSTL");
		}
	
	private void updateMesh()
		{
		if (lerpParam != lastLerpParam)
			{
			mesh.getFunction().setLerpParam(lerpParam);
			lastLerpParam = lerpParam;
			reinit();
			}
		if (uMin != lastuMin)
			{
			mesh.setuMin(uMin);
			lastuMin = uMin;
			reinit();
			}
		if (uMax != lastuMax)
			{
			mesh.setuMax(uMax);
			lastuMax = uMax;
			reinit();
			}
		if (vMin != lastvMin)
			{
			mesh.setvMin(vMin);
			lastvMin = vMin;
			reinit();
			}
		if (vMax != lastvMax)
			{
			mesh.setvMax(vMax);
			lastvMax = vMax;
			reinit();
			}
		}
	
	@SuppressWarnings("unused")
	private void toSTL()
		{
		mesh.toSTL("Shape.stl");
		}
	
	private void distortMesh()
		{
		mesh.distort(distortionFactor);
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
			stroke(colorTool.colors[t]);
			t--;
			}
		else
			{
			fill(colorTool.colors[t]);
			stroke(colorTool.colors[t]);
			t++;
			}
		}
	
	private boolean isOverGui()
		{
		return gui.window(this).isMouseOver();
		}
	
	@SuppressWarnings("unused")
	private void quad()
		{
		isQuad = true;
		}
	
	@SuppressWarnings("unused")
	private void points()
		{
		isQuad = false;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ParametricMesh3DUnlekker mesh;
	private UNav3D nav;
	private ControlP5 gui;
	public float uMin;
	public float uMax;
	private float vMin;
	private float vMax;
	private float lastuMin;
	public float lastuMax;
	public float lastvMin;
	public float lastvMax;
	
	private float distortionFactor;
	private float scl;
	private UColorTool colorTool;
	private boolean isColReverse;
	private int t;
	private boolean isPrint;
	private float lerpParam;
	private float lastLerpParam;
	private boolean isQuad;
	private ProcessingGfx gfx;
	private float rotY;
	private ControlWindow controlWindow;
	}
