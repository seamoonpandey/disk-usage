/**
 *	Program Name:	ArithmeticDrill.java
 * Purpose:			To get two integers and do some arithmetic
 * Coder:			Janice Manning
 * Date:				Jan 22, 2017 
 */

import java.util.Scanner;

public class ArithmeticDrill
{
	public static void main(String[] args)
	{
		//Create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Declare variables
		int num1 = 0;
		int num2 = 0;
		String fullName = "";
		int sum = 0;
		int difference = 0;
		int product = 0;		
		double average = 0.0;	
		int remainder = 0;
		double quotient = 0.0;
				
		//Get two integers from the user
		System.out.print("Enter a number:\t\t");
		num1 = input.nextInt();
		
		System.out.print("Enter another number:\t");
		num2 = input.nextInt();
		
		input.nextLine();
		
		//Get the user's full name
		System.out.print("Enter your full name:\t");
		fullName = input.nextLine();
		
		//Do some arithmetic calculations
		sum = num1 + num2;
		difference = num1 - num2;
		product = num1 * num2;		
		average = (num1 + num2) / 2.0;
		remainder = num1 % num2;
		quotient = num1 / (double)num2;
						
		//Output the calculations
		System.out.println("\nHello, " + fullName + ". Here is your Arithmetic Drill.");
		System.out.printf("%-12s %10d \n", "Sum:", sum);
		System.out.printf("%-12s %10d \n", "Difference:", difference);
		System.out.printf("%-12s %10d \n", "Product:", product);		
		System.out.printf("%-12s %10.1f \n", "Average:", average);
		System.out.printf("%-12s %10d \n", "Remainder:", remainder);	
		System.out.printf("%-12s %10.1f \n", "Quotient:", quotient);
		
		//Close the Scanner object
		input.close();
	}//End of main method
}//End of class