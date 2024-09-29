/**
 * Program Name:  	Rectangle.java
 * Purpose:			This class will create Rectangle objects in memory
 * Coder:			Janice Manning, Student Number
 * Date:			Jul 13, 2015			
 */

public class Rectangle
{
	//Declare class fields (instance variables) 
	private double length;
	private double width;
	private String colour;
	
	//No-arg constructor
	public Rectangle()
	{
		length = 1.0;
		width = 1.0;
		colour = "black";
	}//End of no-arg constructor method
	
	//3-arg constructor
	public Rectangle(double length, double width, String colour)
	{
		this.length = length;
		this.width = width;
		this.colour = colour;
	}//End of 3-arg constructor
	
	//1-arg constructor
	public Rectangle(double side)
	{
		length = side;
		width = side;
		colour = "yellow";
	}//End of 1-arg constructor
	
	/**
	 * Method Name:	getLength
	 * Purpose:		Allows access to length
	 * Parameters:	none
	 * Returns:		double
	 */
	public double getLength()
	{
		return length;
	}

	/**
	 * Method Name:	getWidth
	 * Purpose:		Allows access to width
	 * Parameters:	none
	 * Returns:		double
	 */
	public double getWidth()
	{
		return width;
	}

	/**
	 * Method Name:	getColour
	 * Purpose:		Allows access to colour
	 * Parameters:	none
	 * Returns:		String
	 */
	public String getColour()
	{
		return colour;
	}

	/**
	 * Method Name:	setLength
	 * Purpose:		Allows mutation of length
	 * Parameters:	double length
	 * Returns:		void
	 */
	public void setLength(double length)
	{
		this.length = length;
	}

	/**
	 * Method Name:	setWidth
	 * Purpose:		Allows mutation of width
	 * Parameters:	double width
	 * Returns:		void
	 */
	public void setWidth(double width)
	{
		this.width = width;
	}

	/**
	 * Method Name:	setColour
	 * Purpose:		Allows mutation of colour
	 * Parameters:	String colour
	 * Returns:		void
	 */
	public void setColour(String colour)
	{
		this.colour = colour;
	}

	//Utility Methods
	public double calculatePerimeter()
	{
		return 2 * width + 2 * length;
	}//End of calculatePerimeter method
	
	public double calculateArea()
	{
		return length * width;
	}//End of calculateArea method
	
	public Rectangle doubleRectangle()
	{
		Rectangle result = new Rectangle();
		result.length = 2 * length;
		result.width = 2 * width;
		result.colour = colour;
		return result;
	}//End of doubleRectangle method
	
	public boolean isSquare()
	{
		if (length == width)
			return true;
		else
			return false;
	}//End of isSquare method
	
	public boolean equals(Rectangle r)
	{
		if(length == r.length && width == r.width && colour.equals(r.colour))
			return true;
		else
			return false;
	}//End of equals method
	
	public String toString()
	{
		return "Length:\t" + length +
				"\nWidth:\t" + width +
				"\nColour:\t" + colour;
	}//End of toString method	
}//End of class