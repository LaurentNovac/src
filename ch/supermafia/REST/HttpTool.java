
package ch.supermafia.REST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpTool
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public static HttpURLConnection connect(URL url) throws IOException, ProtocolException
		{
		return connect(url, "GET");
		}
	
	public static HttpURLConnection connect(URL url, String method) throws IOException, ProtocolException
		{
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod(method);
		connection.setAllowUserInteraction(false);
		connection.setDoOutput(true);
		return connection;
		}
	
	public static void logHttpResponseHeader(HttpURLConnection connection)
		{
		//general header
		Logger.getLogger("http header").log(Level.INFO, "Date: " + connection.getHeaderField("Date"));
		Logger.getLogger("http header").log(Level.INFO, "Pragma: " + connection.getHeaderField("Pragma"));
		//request header
		Logger.getLogger("http header").log(Level.INFO, "Authorization: " + connection.getHeaderField("Authorization"));
		Logger.getLogger("http header").log(Level.INFO, "From: " + connection.getHeaderField("From"));
		Logger.getLogger("http header").log(Level.INFO, "If-Modified-Since: " + connection.getHeaderField("If-Modified-Since"));
		Logger.getLogger("http header").log(Level.INFO, "Referer: " + connection.getHeaderField("Referer"));
		Logger.getLogger("http header").log(Level.INFO, "User-Agent: " + connection.getHeaderField("User-Agent"));
		Logger.getLogger("http header").log(Level.INFO, "Type MIME: " + connection.getHeaderField(connection.getContentType()));
		}
	
	public static void logHttpResponseStream(HttpURLConnection connection) throws IOException
		{
		InputStream stream = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line;
		while((line = br.readLine()) != null)
			{
			Logger.getLogger("http header").log(Level.INFO, line);
			}
		}
	}
