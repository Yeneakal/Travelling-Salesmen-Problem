/*	Nicholas Calleja
 *	10/20/19
 * 	COSC 461: Intro into Artificial Intellegence
 * 
 * 	The Traveling Salesmen Problem
 */ 

import java.util.*;
import java.lang.*;
import java.io.*;

public class TravelingSalesmen {
	
	// Introduction Prompt
	public static void firstPrompt() {
		
		System.out.println("< Welcome to Nicholas Calleja's Traveling Salesmen Solutions >");
		System.out.println("--------------------------------------------------------------");
		System.out.println();
	}
	
	// Error Prompt
	public static void errorPrompt()	{
		
		System.out.println("Error: File Not Found");
		System.out.println("Possible Fixies: ");
		System.out.println("1.) Make sure your file is in the correct place");
		System.out.println("2.) Make sure you typed the correct name of the file");
	}
	
	// Creating Empty Matrix
	public static void createEmpty(SolvingTSP matrix)	{
		
		for(int i = 0; i < matrix.getSize(); i++)	{
			
			for(int j = 0; j < matrix.getSize(); j++)	{
				
				matrix.getGraph()[i][j] = 0;
			}
		}
	}
	
	// Prints Matrix
	public static void printMatrix(int[][] graph)	{
		
		for(int i = 0; i < graph.length; i++)	{
			
			for(int j = 0; j < graph.length; j++)	{
				
				System.out.print(graph[i][j] + " ");
			}
			
			System.out.println();
		}
	}
	
	// Testing How Many Cities
	public static void howManyOnes(int[][] graph)	{
		
		int Ones = 0;
		
		for(int i = 0; i < graph.length; i++)	{
			
			for(int j = 0; j < graph.length; j++)	{
				
				if (graph[i][j] == 1)	{
					
					Ones++;
				}
			}
		}
		
		System.out.println();
		System.out.println("The Amount of Cities on the Map: " + Ones);
		System.out.println();
	}
	
	public static void MainMenu(SolvingTSP graph)	{
		
		System.out.println("< Welcome to the Main Menu >");
		System.out.println("-----------------------------------------------------------");
		System.out.println("How would you like to solve the Traveling Salesmen Problem?");
		System.out.println("(type the number correlating with the options)");
		System.out.println();
		System.out.println("1.) Uniform Cost ");
		System.out.println("2.) Uniform Cost without Crossing Paths");
		System.out.println("3.) Greedy Algorithm");
		
		Scanner keyb = new Scanner(System.in);
		int response = keyb.nextInt();
		
		switch (response)	{
		
		case 1:
			graph.UniformCost();
			break;
		case 2:
			graph.UniCostNoCross();
			break;
		case 3:
			graph.GreedyAlgo();
			break;
		default:
		}
	}
	
	// Throwing FileNotFoundException
	public static void main(String[] args) throws FileNotFoundException {	
		
		// Creating Output File to use later
		PrintStream o = new PrintStream(new File("Output.txt"));			
		PrintStream Console = System.out;
		
		// Calling Intro Prompt Method
		firstPrompt();
		
		// Reading from the File "Data-Set-All.txt"
		File f = new File("Data-Set");
		Scanner inFile;
		
		// Getting the number of cities
		System.out.println("How many Cities do you want to computer?");
		Scanner keyb = new Scanner(System.in);
		int n = keyb.nextInt();
		
		// Trying to Find the File
		try {
			
			inFile = new Scanner(f);
			
			// Creating New Matrix for TSP
			SolvingTSP matrix = new SolvingTSP();
			
			// Creates Empty Matrix
			createEmpty(matrix);
			
			int count = 0;
			
			// Loop to find n amount of cities
			while(count < n)	{
				
				int left = inFile.nextInt();
				int right = inFile.nextInt();
				
				for(int i = 0; i < matrix.getSize(); i++)	{
					
					for(int j = 0; j < matrix.getSize(); j++)	{
						
						// Minus 1 because arrays start at 0
						matrix.getGraph()[left - 1][right - 1] = 1;
					}
				}
				
				count++;
			}
			
			// Prints Matrix to Console
			// printMatrix(matrix.getGraph());
			
			// Testing How Many Cities
			howManyOnes(matrix.getGraph());
			
			// Prompting Main Menu
			MainMenu(matrix);
		}
		
		catch(FileNotFoundException e)	{
			
			errorPrompt();
		}
	}
}