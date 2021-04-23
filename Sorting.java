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
			scanner.nextLine();

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
        the given arrival time inputed by user, ordered by trip_id in ascending order.
"""

public class Sorting {

	static String invalidTime = "24:00:00";
	static String invalidTime2 = "00:00:00";
	static boolean valid = false;

	public static void main(String[] args) throws ParseException {	
			
		sortArrivalTime();	
		
	}
		public static void sortArrivalTime()
		{
		
		String line = "";
		
		List<String> output = new ArrayList<String>();
		String userInput = JOptionPane.showInputDialog("Please enter prefered arrival time in the format'hh:mm:ss'");
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("stop_times.txt"));
			while((line = br.readLine()) != null)
			{
				String [] stops = line.split(" ");
				String arrivalTime = stops[1];
				if(userInput.compareTo(invalidTime) < 0 && userInput.compareTo(invalidTime2) > 0)
				{
					valid = true;

				
				    if(userInput.equals(arrivalTime))
				    {
					output.add(line + "\n");	
				    }
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Time Entered");
					break;
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
			
	if(valid == true)
		{		
		Collections.sort(output);
		JOptionPane.showMessageDialog(null, "Here is a list of all trips arriving at your given time: " + "\n" + Arrays.toString(output.toArray()).replace("[","").replace("]","").replace(", ",""));
	}
		
		System.exit(0);

	}
		
}




