
package ch.supermafia.processing.framework3D.mathematics.Function;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.supermafia.processing.framework3D.geometry.Vec3D;

public class HeightMap implements FunctionR2R3_I
	{
	
	public HeightMap(String filename)
		{
		try
			{
			hMap = loadHeightMap(filename, 0, 255);//FIXME too slow
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		}
	
	public HeightMap()
		{
		this("heightmap.png");
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		float z_ = hMap[index((int)x, (int)y)];
		Vec3D v = new Vec3D(x, y, z_);
		return v;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	public static BufferedImage convertToGrayscale(BufferedImage source)
		{
		BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
		}
	
	private float[] loadHeightMap(String img, float minHeight, float maxHeight) throws IOException
		{
		image = ImageIO.read(new File(img));
		image.getSubimage(0, 0, 10, 10);
		final float[] mapping = new float[image.getWidth() * image.getHeight()];
		for(int x = 0; x < image.getWidth(); x++)
			{
			for(int y = 0; y < image.getHeight(); y++)
				{
				Color col = new Color(image.getRGB(x, y));
				mapping[index(x, y)] = minHeight + ((col.getRed() + col.getBlue() + col.getGreen()) / 765f) * (maxHeight - minHeight);
				}
			}
		return mapping;
		}
	
	private int index(int x, int y)
		{
		return y * image.getWidth() + x;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	@Override
	public float getUmin()
		{
		return 0;
		}
	
	@Override
	public float getUMax()
		{
		return image.getWidth() - 1;
		}
	
	@Override
	public float getVmin()
		{
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		return image.getHeight() - 1;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private BufferedImage image;
	private float[] hMap;
	
	}
