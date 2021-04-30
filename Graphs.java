"""
  Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops en route as well as the associated “cost”.
  Stops are listed in stops.txt and connections (edges) between them come from stop_times.txt and transfers.txt files.
  All lines in transfers.txt are edges (directed), while in stop_times.txt an edge should be added only between 2 consecutive stops
  with the same trip_id. eg first 3 entries in stop_times.txt are 9017927, 5:25:00, 5:25:00,646,1,,0,0,
  9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300 & 9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
  This should add a directed edge from 646 to 378, and a directed edge from 378 to 379 (as they’re on the same trip id 9017927). 
  Cost associated with edges should be as follows: 1 if it comes from stop_times.txt,
  2 if it comes from transfers.txt with transfer type 0 (which is immediate transfer possible),
  and for transfer type 2 the cost is the minimum transfer time divided by 100.
"""

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath 

{

	private double adjMatrix[][] = new double[12479][12479];
	private String stop_times_txt; 
	private String transfers_txt;





	public static void main(String[] args) throws FileNotFoundException 
	{

		printShortestPath();

	}



	public static void printShortestPath() throws FileNotFoundException
	{
		ShortestPath diagram = new ShortestPath("stop_times_txt", "transfers_txt");

		try 

		{
			diagram.matrixBuilder();
		}

		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		Scanner inputScanner = new Scanner (System.in);
		System.out.print("Please enter source Bus stop");
		int stopOne = inputScanner.nextInt();

		System.out.print("Please enter the destination Bus stop");
		int stopTwo = inputScanner.nextInt();

		System.out.println("Shortest distance from " + stopOne + " to " + stopTwo + " is " + diagram.shortestRoute(stopOne, stopTwo));

		inputScanner.close();


	}



	private void matrixBuilder() throws FileNotFoundException {

		// 12479 being the max size of matrix - found by trial and error

		for(int i = 0; i < 12479; i++)
		{
			for(int j = 0; j < 12479; j++) 
			{
				if(i != j)
				{

					adjMatrix[i][j] = Math.pow(1.7*10,308);

				}
				else {
					adjMatrix[i][j] = 0;
				}
			}
		}


		int source = 0; 
		int destination = 0; 
		int lastTripId = 0;
		int currentTripId = 0;
		double cost =1;
		double shortestTime;
		int in_direct; 
		String thisLine;


		File stopTimesFile = new File(stop_times_txt);

		Scanner completeFile = new Scanner(stopTimesFile);
		Scanner lineFile = null;

		completeFile.nextLine();


		while(completeFile.hasNextLine()) 
		{

			thisLine = completeFile.nextLine();
			lineFile = new Scanner(thisLine);
			lineFile.useDelimiter(",");

			lastTripId = currentTripId;
			currentTripId = lineFile.nextInt();


			for (int i = 1; i <3;i++)
			{
				lineFile.next();

			}

			source = destination;
			destination = lineFile.nextInt();

			if(lastTripId == currentTripId) 

			{
				adjMatrix[source][destination] = cost;

			}

			lineFile.close();
		}

		completeFile.close();



		File transfersFile = new File(transfers_txt);
		completeFile = new Scanner(transfersFile);


		completeFile.nextLine();


		while(completeFile.hasNextLine()) 
		{

			thisLine = completeFile.nextLine();
			lineFile = new Scanner(thisLine);
			lineFile.useDelimiter(",");

			source = lineFile.nextInt();
			destination = lineFile.nextInt();
			in_direct = lineFile.nextInt();

			if(in_direct == 0) 
			{
				adjMatrix[source][destination] = 2;
			}
			else if(in_direct == 2) 
			{
				shortestTime = lineFile.nextDouble();
				adjMatrix[source][destination] = (shortestTime / 100 );
			}

			lineFile.close();
		}
		completeFile.close();
	}







	public ShortestPath(String stop_times_txt, String transfers_txt) throws FileNotFoundException 

	{

		this.stop_times_txt = stop_times_txt;
		this.transfers_txt = transfers_txt;
		matrixBuilder();
	}




	private void relaxEdge(int source, int destination, double[] distanceTo, int[] edgeTo) 
	{
		if(distanceTo[destination] > distanceTo[source] + adjMatrix[source][destination]) 
		{
			distanceTo[destination] = distanceTo[source] + adjMatrix[source][destination];
			edgeTo[destination] = source;
		}
	}





	public String shortestRoute(int source, int destination)

	{



		int marked[] = new int[12479];
		double distanceTo[] = new double[12479];
		int edgeTo[] = new int[12479];


		marked[source] = 1;
		distanceTo[source] = 0; 
		int position = source;
		int noStops = 0;
		double shortestDist = Math.pow(1.7*10,308);


		double node1 = source;
		double node2 = destination;
		int distLength = distanceTo.length;

		String path = "";

		for(int i = 0; i < distLength; i++) 
		{
			if(i != source)
			{
				distanceTo[i] = Double.POSITIVE_INFINITY;
			}
		}

		if(destination  == source)
		{
			return "" + adjMatrix[source][destination] + " through " + source;
		}




		while(noStops < distLength)

		{


			for(int i = 0; i < distLength; i++) 
			{
				if(marked[i] != 1 && shortestDist > distanceTo[i]) 
				{
					position = i;
					shortestDist = distanceTo[i];
				}
			}
			noStops++;



			for(int i = 0; i < adjMatrix[position].length; i ++) 
			{

				if(marked[i] == 0) 
				{
					relaxEdge(position, i, distanceTo, edgeTo);
				}

			}
			marked[position] = 1;


		}


		if(distanceTo[destination] == Double.POSITIVE_INFINITY) 
		{
			return "There is no possible route.";
		}




		while(node2 != node1) 
		{
			path =  edgeTo[(int) node2] + path;
			node2 = edgeTo[(int) node2];
		}
		path = path + "," +  destination;

		return distanceTo[destination] + " through stops " + path;
	}





}
    
    }
