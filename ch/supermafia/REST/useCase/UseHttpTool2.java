
package ch.supermafia.REST.useCase;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.supermafia.REST.HttpTool;

public class UseHttpTool2
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
			HttpURLConnection connection = HttpTool.connect(url, "GET");
			HttpTool.logHttpResponseHeader(connection);
			HttpTool.logHttpResponseStream(connection);
			}
		catch (Exception e)
			{
			e.printStackTrace();
			Logger.getLogger("URL connection").log(Level.SEVERE, e.getMessage());
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
