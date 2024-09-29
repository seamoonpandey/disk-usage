/**
 * Program Name:	NumberPower.java
 * Purpose:			To write a loop to find the sum of quotients
 * Coder:			Janice Manning, Student Number
 * Date:				October, 2018
 */

import java.util.Scanner;

public class QuotientsSum
{
	public static void main(String[] args)
	{
		//Declare Scanner object
		Scanner input = new Scanner(System.in);
		
		//Declare variables
		int limit = 0;
		int count = 1;
		double quotient = 0;
		double sum = 0;
		
		//Get limit from user
		System.out.print("How many times do you want to sum 1.0/n (at least once)?  ");
		limit = input.nextInt();
		
		//Calculate the sum of quotients
		while (count <= limit)
		{
			quotient = 1.0 / count;
			sum += quotient;
			count++;
		}
		
		//Output the sum
		System.out.println("Sum of quotients is " + sum);		
		
		//Close Scanner object
		input.close();
	}//End of main method
}//End of class
