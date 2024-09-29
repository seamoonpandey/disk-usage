/**
 * Program Name:	RectangleArea.java
 * Purpose:			To calculate the area of a rectangle
 * Coder:			Janice Manning
 * Date:				September, 2018
 */
 
 import java.text.*;
import java.util.*;
 
 public class Rectangle
 {
	public static void main (String [] args)
	{
		//Declare a Scanner object to read in console input
		Scanner input = new Scanner(System.in);
		
		//Declare variables
		double width = 0.0;
		double length = 0.0;
		double area = 0.0;
		double perimeter = 0.0;
		
		//Get the width of a rectangle
		System.out.print("Enter width:\t");
		width = input.nextDouble();		
		
		//Get the length of a rectangle
		System.out.print("Enter length:\t");
		length = input.nextDouble();		
		
		//Calculate the dimensions of a rectangle
		area = width * length;
		perimeter = 2 * width + 2 * length;
		
		//Print dimensions
		System.out.printf("\nArea of the rectangle:  %.1f \n", area);
		System.out.printf("Perimeter of the rectangle:  %.1f \n", perimeter);
	}//End of main method
}//End of class
