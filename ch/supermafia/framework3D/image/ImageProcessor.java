
package ch.supermafia.framework3D.image;
/**
 * 
 * using the chain of responsibility pattern
 *
 */
abstract public class ImageProcessor
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public ImageProcessor()
		{
		}
	
	/*------------------------------------------------------------------*\
	|*						Methodes Protected							*|
	\*------------------------------------------------------------------*/
	
	protected void process(Image_I image)
		{
		processor(image);
		if (next != null)
			{
			next.process(image);
			}
		}
	
	abstract protected void processor(Image_I image);
	
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private ImageProcessor next;
	
	}
