
package ch.supermafia.framework3D.use.frameworkDemo.opengl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GLAutoDrawable;

import ch.supermafia.framework3D.geometry.matrix.Matrix4x4Rotation;
import ch.supermafia.framework3D.geometry.matrix.Matrix4x4Scale;
import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.geometry.vector.Vec4D;
import ch.supermafia.framework3D.mathematics.Function.Cresent;
import ch.supermafia.framework3D.mathematics.Function.FlatFunction;
import ch.supermafia.framework3D.mathematics.Function.HeightMap;
import ch.supermafia.framework3D.mathematics.Function.KleinCycloid;
import ch.supermafia.framework3D.mathematics.Function.SinDistSquared;
import ch.supermafia.framework3D.mathematics.Function.SinDistSquaredToTranguloid;
import ch.supermafia.framework3D.mathematics.Function.Steiner;
import ch.supermafia.framework3D.mathematics.Function.TranguloidToTriaxial;
import ch.supermafia.framework3D.mathematics.Function.TranguloidTrefoil;
import ch.supermafia.framework3D.mathematics.Function.Triaxial;
import ch.supermafia.framework3D.opengl.JoglTemplate;
import ch.supermafia.framework3D.opengl.OpenglGfx;
import ch.supermafia.framework3D.opengl.Shader;

@SuppressWarnings("serial")
public class JoglMesh extends JoglTemplate
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JoglMesh()
		{
		gfx = new OpenglGfx();
		rotY = 0;
		addListener();
		pointSize = 1.0f;
		color = new Vec4D(0.0f, 0.0f, 0.0f, 1.0f);
		rotationMat = new Matrix4x4Rotation(new Vec3D(0.0f, 0.0f, 1.0f), rotY);
		scl = 1.0f;
		sclVec = new Vec3D(scl, scl, scl);
		scaleMat = new Matrix4x4Scale(sclVec);
		lerpParam = 0.0f;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void init(GLAutoDrawable drawable)
		{
		super.init(drawable);
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		try
			{
			mesh = new ParametricMesh3D(200, 200, new FlatFunction());
			gfx.createParametricMeshVBO(mesh, gl);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		
		shader = new Shader();
		shader.setVsrc(shader.loadShader("ambiant.vert"));
		shader.setFsrc(shader.loadShader("ambiant.frag"));
		shader.init(gl);
		}
	
	@Override
	public void display(GLAutoDrawable drawable)
		{
		super.display(drawable);
		//rotY += 0.1f;
		
		gl.glTranslatef(-1.5f, 0.0f, -30.0f); // translate left and into the screen
		rotationMat.setAngle(rotY);
		sclVec.setX(scl);
		sclVec.setY(scl);
		sclVec.setZ(scl);
		scaleMat.setScaleVec(sclVec);
		shader.useShader(gl);
		
		shader.writeUniform(gl, "color", color);
		shader.writeUniform(gl, "scaleMatrix", scaleMat);
		shader.writeUniform(gl, "rotationMatrix", rotationMat);
		
		gfx.meshPoints(mesh, gl, pointSize);
		shader.dontUseShader(gl);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void reinit()
		{
		gfx.deleteParametricMeshVBO(gl);
		mesh.computeTable();
		gfx.createParametricMeshVBO(mesh, gl);
		}
	
	private void addListener()
		{
		addKeyListener(new KeyListener()
			{
				
				@Override
				public void keyTyped(KeyEvent e)
					{
					// TODO Auto-generated method stub
					
					}
				
				@Override
				public void keyReleased(KeyEvent e)
					{
					// TODO Auto-generated method stub
					
					}
				
				@Override
				public void keyPressed(KeyEvent e)
					{
					char key = e.getKeyChar();
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
							mesh.setFunc(new HeightMap());
							reinit();
							break;
						case 's':
							scl *= 2.0f;
							break;
						case 'a':
							scl *= 0.5f;
							break;
						case 't':
							mesh.translate(new Vec3D(3.0f, 0.0f, 0.0f));
							break;
						case 'i':
							mesh.applyIdentity();
							break;
						case 'x':
							mesh.rotateX((float)(Math.PI / 10));
							break;
						case 'y':
							mesh.rotateY((float)(Math.PI / 10));
							break;
						case 'z':
							mesh.rotateZ((float)(Math.PI / 10));
							break;
						case '+':
							pointSize++;
							break;
						case '-':
							pointSize--;
							if (pointSize <= 1.0f)
								{
								pointSize = 1.0f;
								}
							break;
						case 'w':
							lerpParam += 0.1f;
							if (lerpParam >= 1.0f) lerpParam = 1.0f;
							mesh.getFunction().setLerpParam(lerpParam);
							mesh.computeTable();
							break;
						case 'q':
							lerpParam -= 0.1f;
							if (lerpParam <= 0.0f) lerpParam = 0.0f;
							mesh.getFunction().setLerpParam(lerpParam);
							mesh.computeTable();
							break;
						default:
							break;
						}
					int kc = e.getKeyCode();
					switch(kc)
						{
						case KeyEvent.VK_UP:
							mesh.translate(new Vec3D(0.0f, 1.0f, 0.0f));
							break;
						case KeyEvent.VK_DOWN:
							mesh.translate(new Vec3D(0.0f, -1.0f, 0.0f));
							break;
						case KeyEvent.VK_RIGHT:
							mesh.translate(new Vec3D(1.0f, 0.0f, 0.0f));
							break;
						case KeyEvent.VK_LEFT:
							mesh.translate(new Vec3D(-1.0f, 0.0f, 0.0f));
							break;
						default:
							break;
						}
					}
			});
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private OpenglGfx gfx;
	private float rotY;
	private ParametricMesh3D mesh;
	//	private PointCloud pointCloud;//FIXME
	private float pointSize;
	private Shader shader;
	private Vec4D color;
	private Matrix4x4Rotation rotationMat;
	private Matrix4x4Scale scaleMat;
	private Vec3D sclVec;
	private float lerpParam;
	float scl = 1.0f;
	}
