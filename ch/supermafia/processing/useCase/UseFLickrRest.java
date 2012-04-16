
package ch.supermafia.processing.useCase;

import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.supermafia.REST.FlickrRestTool;
import processing.core.PApplet;
import processing.core.PImage;

public class UseFLickrRest extends PApplet
	{
	
	public static List<String> imageList;
	public static URL url;
	public static PImage img;
	public static double angleDelta;
	public static double angle;
	public static double radius;
	
	/*------------------------------------------------------------------*\
	|*						 Setup            							*|
	\*------------------------------------------------------------------*/
	public void setup()
		{
		size(800, 800);
		background(90, 90, 90);
		stroke(214,0,0);
		strokeWeight(1);
		smooth();
		radius = width / 3;
		angle = 0;
		
		try
			{
			Logger.getLogger("Flickr").log(Level.INFO, "start loading images");
			url = new URL("http://api.flickr.com/services/rest/?&api_key=7994c5c30db6718d956db21a28f1a6ec&method=flickr.photos.getRecent");
			imageList = FlickrRestTool.getFlickrPhotoAdressList(url);
			Logger.getLogger("Flick").log(Level.INFO, "end loading images");
			}
		catch (Exception e)
			{
			Logger.getLogger("Flickr").log(Level.SEVERE, e.getMessage());
			}
		noLoop();
		}
	
	/*------------------------------------------------------------------*\
	|*							 Draw          							*|
	\*------------------------------------------------------------------*/
	
	public void draw()
	{
	translate(width/2,height/2);
	angleDelta=radians(360/imageList.size());
	Logger.getLogger("Draw").log(Level.INFO, "image size = " + imageList.size());
	int i=0;
	for(String img:imageList)
		{
		double x=radius*Math.cos(angle);
		double y=radius*Math.sin(angle);
		PImage im=loadImage(img);
		Logger.getLogger("Draw").log(Level.INFO, "load images"+i);
		im.resize(im.width/30,im.height/30);
		image(im,(int)x,(int)y);
		line(0,0,(int)x,(int)y);
		i++;
		angle+=angleDelta;
		}
	Logger.getLogger("Draw end").log(Level.INFO, "Finished loading images");

	}
	
	/*------------------------------------------------------------------*\
	|*							 Main									*|
	\*------------------------------------------------------------------*/
	
	public static void main(String[] args)
		{
		main();
		}
	
	public static void main()
		{
		PApplet.main(new String[] { "--present", "ch.supermafia.processing.useCase.UseFLickrRest" });
		}
	}
