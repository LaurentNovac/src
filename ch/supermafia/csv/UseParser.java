
package ch.supermafia.csv;

import java.util.Map;


public class UseParser
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
		OrangeBillCSVParser myBill = new OrangeBillCSVParser("/Users/vacpics/Documents/dev./processing sketch/eclipseWorkspace/processingSketches/src/ch/supermafia/csv/csvBillLink.csv");

		Map<String, Integer> mapPhoneSMS = myBill.getMapPhonenumberNbSMS();
		System.out.println(mapPhoneSMS);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	
	}
