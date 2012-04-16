package ch.supermafia.REST.useCase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ch.supermafia.REST.FlickrRestTool;
import ch.supermafia.REST.HttpTool;


public class UseHttpTool
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public static void main(String[] args)
		{
		main();
		}
	
	public static void main()
		{
		try
			{
			URL url = new URL("http://api.flickr.com/services/rest/?api_key=7994c5c30db6718d956db21a28f1a6ec&method=flickr.photos.getRecent");
			HttpURLConnection connection = HttpTool.connect(url,"GET");//the request returns here
			Map<String, String> photoMap = FlickrRestTool.getFlickrPhotoMap(connection);
			showMap(photoMap);
			connection.disconnect();
			}
		catch (Exception e)
			{
			Logger.getLogger("log").log(Level.SEVERE, e.getMessage());
			}
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	public static void showMap(Map<String, String> photoMap)
		{
		Set<Entry<String, String>> entries = photoMap.entrySet();
		for(Entry<String, String> elem:entries)
			{
			System.out.println(elem.getKey()+" "+elem.getValue());
			}
		}
	}
