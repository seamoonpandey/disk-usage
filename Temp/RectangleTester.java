/**
 * Program Name:  	RectangleTester.java
 * Purpose:			This class will test Rectangle objects
 * Coder:			Janice Manning, Student Number
 * Date:			Jul 13, 2015			
 */

public class RectangleTester
{
	public static void main(String[] args)
	{
		//Print a notice to the screen about the purpose of this class
		System.out.println("Welcome to the Rectangle tester!");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("This program will instantiate objects of Rectangle and test all of the methods of the Rectangle class.");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		//Create a Rectangle object using a no-arg constructor
		Rectangle rectangle1 = new Rectangle();
		
		//Output the properties of the rectangle1
		System.out.println("For triangle1 length of base is:\t" + rectangle1.getLength());
		System.out.println("For triangle1 length of side 1 is:\t" + rectangle1.getWidth());
		System.out.println("For triangle1 length of side 2 is:\t" + rectangle1.getColour());
		System.out.println();
		
		//Create a Rectangle object using a 3-arg constructor
		Rectangle rectangle2 = new Rectangle(3.5, 4.9, "red");
		
		//Output the properties of the rectangle2
		System.out.println("Rectangle2:\n" + rectangle2.toString());
		System.out.println();
		
		//Create a Rectangle object using the 1-arg constructor
		Rectangle rectangle3 = new Rectangle(6.8);
		
		//Output the properties of the rectangle3
		System.out.println("Rectangle3:\n" + rectangle3.toString());
		System.out.println();
		
		//Re-set the properties of rectangle1
		rectangle1.setLength(15.5);
		rectangle1.setWidth(15.5);
		rectangle1.setColour("green");
		
		//Output the properties of the rectangle1
		System.out.println("Rectangle1:\n" + rectangle1.toString());
		System.out.println();
		
		//Testing calculatePerimeter method on rectangle2
		System.out.println("For rectangle2 the perimeter is:  " + rectangle2.calculatePerimeter());
		System.out.println();
		
		//Testing calculateArea method on rectangle2
		System.out.println("For rectangle2 the area is:  " + rectangle2.calculateArea());
		System.out.println();
		
		//Testing doubleRectangle method on rectangle2
		System.out.println("A doubled version of rectangle2 is:\n" + rectangle2.doubleRectangle());
		System.out.println();
		
		//Testing isSquare method on rectangle1
		System.out.println("Is rectangle1 a square:  " + rectangle1.isSquare());
		System.out.println();
		
		//Testing equals method on rectangle1 and rectangle3
		System.out.println("Is rectangle1 equal to rectangle3:  " + rectangle1.equals(rectangle3));
		System.out.println();
				
		

	}//End of main method
}//End of class