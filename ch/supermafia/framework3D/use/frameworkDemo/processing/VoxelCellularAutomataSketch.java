
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import ch.supermafia.framework3D.geometry.mesh.VoxelSpace;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;

@SuppressWarnings("serial")
public class VoxelCellularAutomataSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		gfx = new ProcessingGfx(this);
		res = 45;
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		strokeWeight(4.0f);
		edgeSize = 10;
		initialState();
		smooth();
		}
	
	public void draw()
		{
		background(0);
		nav.doTransforms();
		gfx.meshPoints(voxelSpaceDst, edgeSize);
		int t = millis() / 100;
		//if (t % 10 == 0) stepRule2();
		}
	
	public void keyPressed()
		{
		if (key == '+')
			{
			edgeSize++;
			}
		if (key == '-')
			{
			edgeSize--;
			}
		if (edgeSize <= 0)
			{
			edgeSize = 0;
			}
		if (key == 's')
			{
			stepRule1();
			}
		if (key == 'r')
			{
			initialState();
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void initialState()
		{
		voxelSpaceSrc = new VoxelSpace(res, res, res, new Vec3D(0, 0, 0));
		voxelSpaceDst = new VoxelSpace(res, res, res, new Vec3D(0, 0, 0));
		voxelSpaceSrc.setColor(voxelSpaceSrc.getResX() / 2, voxelSpaceSrc.getResY() / 2, voxelSpaceSrc.getResY() / 2, 255, 255, 255);
		voxelSpaceDst.setColor(voxelSpaceSrc.getResX() / 2, voxelSpaceSrc.getResY() / 2, voxelSpaceSrc.getResY() / 2, 255, 255, 255);
		}
	
	private void stepRule1()
		{
		for(int i = 1; i < voxelSpaceSrc.getResX() - 1; i++)
			{
			for(int j = 1; j < voxelSpaceSrc.getResY() - 1; j++)
				{
				for(int k = 1; k < voxelSpaceSrc.getResZ() - 1; k++)
					{
					if (voxelSpaceSrc.getColor(i - 1, j, k).x() == 255 && voxelSpaceSrc.getColor(i - 1, j, k).y() == 255 && voxelSpaceSrc.getColor(i - 1, j, k).z() == 255 || voxelSpaceSrc.getColor(i + 1, j, k).x() == 255 && voxelSpaceSrc.getColor(i + 1, j, k).y() == 255
							&& voxelSpaceSrc.getColor(i + 1, j, k).z() == 255 || voxelSpaceSrc.getColor(i, j - 1, k).x() == 255 && voxelSpaceSrc.getColor(i, j - 1, k).y() == 255 && voxelSpaceSrc.getColor(i, j - 1, k).z() == 255 || voxelSpaceSrc.getColor(i, j + 1, k).x() == 255
							&& voxelSpaceSrc.getColor(i, j + 1, k).y() == 255 && voxelSpaceSrc.getColor(i, j + 1, k).z() == 255 || voxelSpaceSrc.getColor(i, j, k - 1).x() == 255 && voxelSpaceSrc.getColor(i, j, k - 1).y() == 255 && voxelSpaceSrc.getColor(i, j, k - 1).z() == 255
							|| voxelSpaceSrc.getColor(i, j, k + 1).x() == 255 && voxelSpaceSrc.getColor(i, j, k + 1).y() == 255 && voxelSpaceSrc.getColor(i, j, k + 1).z() == 255)
						{
						voxelSpaceDst.setColor(i, j, k, 255, 255, 255);
						}
					}
				}
			}
		voxelSpaceSrc = new VoxelSpace(voxelSpaceDst.getResX(), voxelSpaceDst.getResY(), voxelSpaceDst.getResZ(), voxelSpaceDst.getVertices());
		}
	
	private void stepRule2()
		{
		for(int i = 1; i < voxelSpaceSrc.getResX() - 1; i++)
			{
			for(int j = 1; j < voxelSpaceSrc.getResY() - 1; j++)
				{
				for(int k = 1; k < voxelSpaceSrc.getResZ() - 1; k++)
					{
					int count = 0;
					if (voxelSpaceSrc.getColor(i - 1, j, k).x() == 255 && voxelSpaceSrc.getColor(i - 1, j, k).y() == 255 && voxelSpaceSrc.getColor(i - 1, j, k).z() == 255)
						{
						count++;
						}
					if (voxelSpaceSrc.getColor(i + 1, j, k).x() == 255 && voxelSpaceSrc.getColor(i + 1, j, k).y() == 255 && voxelSpaceSrc.getColor(i + 1, j, k).z() == 255)
						{
						count++;
						}
					if (voxelSpaceSrc.getColor(i, j - 1, k).x() == 255 && voxelSpaceSrc.getColor(i, j - 1, k).y() == 255 && voxelSpaceSrc.getColor(i, j - 1, k).z() == 255)
						{
						count++;
						}
					if (voxelSpaceSrc.getColor(i, j + 1, k).x() == 255 && voxelSpaceSrc.getColor(i, j + 1, k).y() == 255 && voxelSpaceSrc.getColor(i, j + 1, k).z() == 255)
						{
						count++;
						}
					if (voxelSpaceSrc.getColor(i, j, k - 1).x() == 255 && voxelSpaceSrc.getColor(i, j, k - 1).y() == 255 && voxelSpaceSrc.getColor(i, j, k - 1).z() == 255)
						{
						count++;
						}
					if (voxelSpaceSrc.getColor(i, j, k + 1).x() == 255 && voxelSpaceSrc.getColor(i, j, k + 1).y() == 255 && voxelSpaceSrc.getColor(i, j, k + 1).z() == 255)
						{
						count++;
						}
					if (count == 1)
						{
						voxelSpaceDst.setColor(i, j, k, 255, 255, 255);
						}
					}
				}
			}
		voxelSpaceSrc = new VoxelSpace(voxelSpaceDst.getResX(), voxelSpaceDst.getResY(), voxelSpaceDst.getResZ(), voxelSpaceDst.getVertices());
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ProcessingGfx gfx;
	private UNav3D nav;
	private float edgeSize;
	private VoxelSpace voxelSpaceSrc;
	private VoxelSpace voxelSpaceDst;
	private int res;
	}
