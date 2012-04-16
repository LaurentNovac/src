
package ch.supermafia.csv;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csvreader.CsvReader;

public class OrangeBillCSVParser
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public OrangeBillCSVParser(String filePath)
		{
		inputFile = filePath;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	Map<String, Integer> getMapPhonenumberNbSMS()
		{
		Map<String, Integer> mapPhoneSMS = new HashMap<String, Integer>();
		try
			{
			CsvReader csvFile = new CsvReader(inputFile);
			csvFile.setDelimiter(';');
			String phoneNumber;
			String quantite;
			int newValue;
			
			//first 3 lines are headers, we don't care about
			csvFile.skipLine();
			csvFile.skipLine();
			csvFile.skipLine();
			//Line reading loop
			while(csvFile.readRecord()) //While another record is readable
				{
				quantite = csvFile.get(3);
				phoneNumber = csvFile.get(4);
				//We just want SMS records
				if (quantite.equals("1 SMS"))
					{
					newValue = 1;
					if (mapPhoneSMS.containsKey(phoneNumber))
						{
						//If the phonenumber is already in the map, we just increment its value by 1
						newValue = mapPhoneSMS.get(phoneNumber) + 1;
						}
					//Else, key phonenumber with value 1 is put into the map
					mapPhoneSMS.put(phoneNumber, newValue);
					}
				}
			
			csvFile.close();
			
			}
		catch (Exception e)
			{
			Logger.getLogger("error log").log(Level.SEVERE, e.getMessage());
			}
		return mapPhoneSMS;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	String inputFile;
	}
