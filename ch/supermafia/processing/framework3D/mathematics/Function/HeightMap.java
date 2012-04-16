
package ch.supermafia.processing.framework3D.mathematics.Function;

import java.awt.Image;
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
			img = ImageIO.read(new File(filename));
			image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_BYTE_INDEXED);
			image.setData(img.getRaster());
			System.out.println(image.getWidth());
			System.out.println(image.getHeight());
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
		System.out.println("x: " + x + "y: " + y);
		float z_ = image.getRGB((int)x, (int)y);
		
		z_ /= 255.0f;
		System.out.println("x: " + x + " y: " + y + " z: " + z_);
		return new Vec3D(x, y, z_);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	public static BufferedImage convertToGrayscale(BufferedImage source)
		{
		BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(source, null);
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
	private BufferedImage img;
	}
