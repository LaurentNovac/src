package ch.supermafia.REST;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;




public class XmlTool
{
/*------------------------------------------------------------------*\
|*							Methodes Public							*|
\*------------------------------------------------------------------*/
public static Document convertToDomDocument(InputStream stream)
	{
	DocumentBuilderFactory fabric = DocumentBuilderFactory.newInstance();
	
	DocumentBuilder constructor;
	try
		{
		constructor = fabric.newDocumentBuilder();
		return constructor.parse(stream);
		}
	catch (Exception e)
		{
		Logger.getLogger("Dom Conversion").log(Level.SEVERE, e.getMessage());
		}
	return null;
	}
}
