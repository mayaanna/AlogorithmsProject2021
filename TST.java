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
/// I keep getting a null pointer exception in main line
/// stop information is not being printed for the user
/// need to add an option to search by first few characters of stop name only 
public class TST<Value> 
{
	private tstNode<Value> root;
	private int N; 


	private static class tstNode<Value> 
	{
		private char character;                        	   
		private tstNode<Value> left; 			   
		private tstNode<Value> mid;			   
		private tstNode<Value> right;			   
		private Value val;                         
	}


	public TST() {
		
    }
	
	public int size() {
        return N;
    }
	public boolean contains(String key) 
	{
		
			if (key == null)
				return false;
		
		return get(key) != null;

	}

	public Value get(String key) 
	{

		if (key == null) 
		{
			return null;
		}

		if (key.length() == 0) 
		{
			return null;
		}

		tstNode<Value> x = get(root, key, 0);

		if (x == null) 
			return null;

		return x.val;


	}



	private tstNode<Value> get(tstNode<Value> y, String key, int d) 
	{
		if (y == null)
		{
			return null;
		}

		char c = key.charAt(d);

		if (c < y.character)              
			return get(y.left,  key, d);

		else if (c > y.character)
			return get(y.right, key, d);

		else if (d < key.length() - 1)
			return get(y.mid,   key, d+1);

		else
			return y;
	}

	private tstNode<Value> put(tstNode<Value> x, String key, Value val, int d) 
	{
		char c = key.charAt(d);
		if (x == null) 
		{
			x = new tstNode<Value>();
			x.character = c;
		}

		if (c < x.character)              
			x.left  = put(x.left,  key, val, d);

		else if (c > x.character)               
			x.right = put(x.right, key, val, d);

		else if (d < key.length() - 1)  
			x.mid   = put(x.mid,   key, val, d+1);

		else                            
			x.val   = val;

		return x;
	}
	public void put(String key, Value val) 
	{
		try {
			if (!contains(key))
				N++;

			root = put(root, key, val, 0);
		}
		catch(NullPointerException e)
		{
			System.out.println("NullPointerException thrown!");
		}
	}


	public static void main(String[] args) {

		Scanner scanner = new Scanner (System.in);

		TST<String> trie = new TST<String>(); 
		String line = "";
		File filepath = new File("stops.txt");

		try
		{
			
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			while((line = br.readLine()) != null)
			{
				String[] stops = line.split(",");
				String stopName = stops[2];

				String stopInformation = "// Stop id:" + stops[0] + "// Stop Code: " + stops[1] + "// Stop Desc: " + stops[3] + "//Stop Lat: " + stops[4] + "//Stop Lon: " + stops[5] + "// Zone id: " + stops[6];

				trie.put(stopName, stopInformation);

			}
			br.close();

		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		
		System.out.println("Please enter the bus stop name you wish to search for");

		String userInput  = scanner.nextLine();

		if(trie.contains(userInput))
		{
			System.out.println(userInput + trie.get(userInput));

		}
		else
		{	
			System.out.println( "Bus stop entered does not exist \n" + 
					"Please enter a valid Bus Stop");

		}

		
		scanner.close();
		

	}



}








