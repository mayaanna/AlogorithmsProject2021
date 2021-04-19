"""
  Searching for all trips with a given arrival time,
  returning full details of all trips matching the criteria (zero, one or more),
  sorted by trip id Arrival time should be provided by the user as hh:mm:ss. 
  When reading in stop_times.txt file you will need to remove all invalid times,
  e.g., there are times in the file that start 27/28 hours, so are clearly invalid.
  Maximum time allowed is 23:59:59
"""
    

import java.util.*;
import java.text.ParseException;
import java.io.*;
import java.text.*;

public class sortBusTimes {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub

		String tempFile = "temp.txt";
		String invalidTime = "24:00:00";
		String singleDigitTime = "10:00:00";
		String filepath = "stop_times.txt";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		String tripID = "";
		String arrivalTime = "";
		String departureTime = "";
		String stopID = "";
		String stopSequence = "";
		String stopHeadSign = "";
		String pickupType = "";
		String dropoffType = "";
		String shapeDistTraveled = "";

		try
		{

			FileWriter x = new FileWriter(tempFile, true);
			BufferedWriter y = new BufferedWriter(x);
			PrintWriter z = new PrintWriter(y);
			Scanner scanner = new Scanner(new File(filepath));
			scanner.useDelimiter("[,\n]");

			while(scanner.hasNext())
			{

				tripID = scanner.next();
				arrivalTime = scanner.next();
				departureTime = scanner.next();
				stopID = scanner.next();
				stopSequence = scanner.next();
				stopHeadSign = scanner.next();
				pickupType = scanner.next();
				dropoffType = scanner.next();
				shapeDistTraveled = scanner.next();



				if(arrivalTime.compareTo(invalidTime) < 0) // if arrival time is less than an invalid time
				{
                                    if(arrivalTime.compareTo(singleDigitTime) < 0) 
					{
					z.println(tripID + "" + arrivalTime + " " + departureTime + " " + stopID + " " + 
							stopSequence + " " + stopHeadSign + " " + pickupType + " " + dropoffType + " " 
							+ shapeDistTraveled);	
				        }
					else
					{
						z.println(tripID + " " + arrivalTime + " " + departureTime + " " + stopID + " " + 
								stopSequence + " " + stopHeadSign + " " + pickupType + " " + dropoffType + " " 
								+ shapeDistTraveled);	
					}
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

"""
	Below main line will print out all the stop information matching
        the given arrival time inputed by user.
	Each line gets printed in order it appears in the original "stop_times.txt" 
	so we need to use some sorting algorithm to get the output ordered from i presume
	smallest trip Id to largest.

"""
	
	

public class Sorting {

	
	public static void main(String[] args) throws ParseException {	
		String line = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter prefered arrival time in the format'hh:mm:ss'");

		String userInput = input.nextLine();

		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("stop_times.txt"));
			while((line = br.readLine()) != null)
			{
				String [] stops = line.split(" ");
				String arrivalTime = stops[1];

				if(userInput.equals(arrivalTime))
				{	
					System.out.println(line);
				}
			}
		
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		input.close();
	}
}

	


    
    
  
