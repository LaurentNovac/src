package ch.supermafia.REST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class FlickrRestTool
{
/*------------------------------------------------------------------*\
|*							Methodes Public							*|
\*------------------------------------------------------------------*/

public static Map<String,String> getFlickrPhotoLinkMap(HttpURLConnection connection) throws IOException
{
Document domDocument = XmlTool.convertToDomDocument(connection.getInputStream());
Element root = domDocument.getDocumentElement();
NodeList photoList = root.getElementsByTagName("photo");
Map<String, String> mapPhotosAndOwner = new HashMap<String,String>();
for(int i = 1; i <= photoList.getLength(); i++)
	{
	Element element = (Element)photoList.item(i-1);;	
	mapPhotosAndOwner.put(element.getAttribute("id"), element.getAttribute("owner"));
	}
return mapPhotosAndOwner;
}

public static Map<String,String> getFlickrPhotoMap(HttpURLConnection connection) throws IOException
{
Document domDocument = XmlTool.convertToDomDocument(connection.getInputStream());
Element root = domDocument.getDocumentElement();
NodeList photoList = root.getElementsByTagName("photo");
Map<String, String> mapPhotosAndOwner = new HashMap<String,String>();
for(int i = 1; i <= photoList.getLength(); i++)
	{
	Element element = (Element)photoList.item(i-1);
	String farm_server=element.getAttribute("farm")+":"+element.getAttribute("server");
	String id_secret_b = element.getAttribute("id")+"_"+element.getAttribute("secret")+"_b";
	mapPhotosAndOwner.put(farm_server,id_secret_b);
	}
return mapPhotosAndOwner;
}

public static List<String> getFlickrPhotoAdressList(URL url) throws IOException, ProtocolException
{
HttpURLConnection connection = HttpTool.connect(url,"GET");
Map<String, String> photoMap = FlickrRestTool.getFlickrPhotoMap(connection);
Set<Entry<String, String>> entries = photoMap.entrySet();
List<String> imageList = new ArrayList<String>();
for(Entry<String, String> entry:entries)
	{
	StringTokenizer keyTokenizer = new StringTokenizer(entry.getKey(), ":");
	imageList.add("http://farm"+keyTokenizer.nextToken()+".static.flickr.com/"+keyTokenizer.nextToken()+"/"+entry.getValue()+".jpg");
	}
return imageList;
}
}
