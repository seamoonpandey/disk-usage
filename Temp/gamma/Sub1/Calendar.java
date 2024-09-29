/**
 * Program Name:	Calendar.java
 * Purpose:			Takes days in a month and first day of the month as input, and generates a calendar	
 * Coder:			Janice Manning, Student Number
 * Date:				October, 2018
  */

import java.util.Scanner;

public class Calendar
{
	public static void main (String [] args)
	{
		//Create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Declare variables
		int days = 0;				//Stores the number of days in the month	
		int count = 0;				//Stores the accumulated number of days printed for one week
		int weekDay = 0;			//Stores the day of the week the month starts
		
		//Prompt the user to enter the number of days in the month
		System.out.print("Enter the number of days in the month: ");
		//Assign the user's answer
		days = input.nextInt();
		System.out.println();
		
		//Prompt the user to enter the day of the week the month starts
		System.out.print("Enter the day of the week the month starts, using an integer from 1 to 7: ");
		//Assign the user's answer
		weekDay = input.nextInt();
		System.out.println();
		
		//Print the days of the week
		System.out.println("Sun\tMon\tTues\tWed\tThurs\tFri\tSat");
		
		//Loop to the day of the week
		for (int x = 1; x < weekDay; x++)
		{
			//Position the cursor to the day of the week
			System.out.print("\t");
			
			//Set count to the day of the week
			count = weekDay;				
		}		
		
		//Print the days of the month across 7 columns 
		for (int j = 1; j <= days; j++)
		{
			//Print the day, then move cursor to next tab
			System.out.print(j + "\t");
			
			if (count == 7)
			{
				//Return cursor to next line
				System.out.println();
				
				//Re-set count
				count = 0;
			}
			
			//Increment count
			count++;
		}
		
		//Print an extra line after the calendar
		System.out.println();	
		
		//Close the Scanner object
		input.close();
	}//End of main method
}//End of class 