"""
  Searching for a bus stop by full name or by the first few characters in the name, 
  using a ternary search tree (TST), returning the full stop information for each stop matching the search criteria (which can be zero, one or more stops) 
  In order for this to provide meaningful search functionality please move keywords flagstop, wb, nb, sb, eb from start of the names to the end of the names
  of the stops when reading the file into a TST (eg “WB HASTINGS ST FS HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)
"""
    
/// this class creates the file with keywords moved to end of stopname. 
/// we can use this now to implement a TST for searching for a bus stop.
import java.util.*;

import java.io.*;
import java.text.*;

public class deleteRecord2 {

	static final String keyword1 = "FLAGSTOP";
	static final String keyword2 = "WB";
	static final String keyword3 = "NB";
	static final String keyword4 = "SB";
	static final String keyword5 = "EB";

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub


		String tempFile = "temp.txt";
		String filepath = "stops.txt";

		File oldFile = new File(filepath);
		File newFile = new File(tempFile);

		String stopID = "";
		String stopCode = "";
		String stopName = "";
		String stopDesc = "";
		String stopLat = "";
		String stopLon = "";
		String zoneID = "";
		String stopURL = "";
		String locationType = "";
		String parentStation = "";

		try
		{

			FileWriter x = new FileWriter(tempFile, true);
			BufferedWriter y = new BufferedWriter(x);
			PrintWriter z = new PrintWriter(y);
			Scanner scanner = new Scanner(new File(filepath));
			scanner.useDelimiter("[,\n]");
			scanner.nextLine();
			while(scanner.hasNext())
			{

				stopID = scanner.next();
				stopCode = scanner.next();
				stopName = scanner.next();
				stopDesc = scanner.next();
				stopLat = scanner.next();
				stopLon = scanner.next();
				zoneID = scanner.next();
				stopURL = scanner.next();
				locationType = scanner.next();
				parentStation = scanner.next();

				String words[] = stopName.split(" ");
				String restOfSentence = stopName.substring(stopName.indexOf(" "),stopName.length()).trim();
				String restOfSentence2 = stopName.substring(11, stopName.length()).trim();

				String firstword = words[0];
				String secondword = words[1]; 



				if((stopName.contains(keyword2) || stopName.contains(keyword3) || 
						stopName.contains(keyword4) || stopName.contains(keyword5)) && !stopName.contains(keyword1))			  
				{
					stopName = (restOfSentence + " " + firstword);

					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							"," + parentStation );

				}
				else if(stopName.contains(keyword1) && !stopName.contains(keyword2) && !stopName.contains(keyword3) && 
						!stopName.contains(keyword4) && !stopName.contains(keyword5))			  
				{
					stopName = (restOfSentence + " " + firstword);

					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							"," + parentStation );
				}

				else if(stopName.contains(keyword1) && stopName.contains(keyword2))	
				{
					stopName = (restOfSentence2 + " " + firstword + " " + secondword);
					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							" " + parentStation );
				}
				else if(stopName.contains(keyword1) && stopName.contains(keyword3))	
				{
					stopName = (restOfSentence2 + " " + firstword + " " + secondword);
					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							" " + parentStation );
				}
				else if(stopName.contains(keyword1) && stopName.contains(keyword4))	
				{
					stopName = (restOfSentence2 + " " + firstword + " " + secondword);
					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							" " + parentStation );
				}
				else if(stopName.contains(keyword1) && stopName.contains(keyword5))	
				{
					stopName = (restOfSentence2 + " " + firstword + " " + secondword);
					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							" " + parentStation );
				}


				else
				{
					z.println(stopID + "," + stopCode + "," + stopName + "," + 
							stopDesc + "," + stopLat + "," + stopLon + "," + zoneID + "," + stopURL + "," + locationType +
							"," + parentStation );
				}

			}




			scanner.close();
			z.flush();
			z.close();
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
		}


		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}



	}

}



