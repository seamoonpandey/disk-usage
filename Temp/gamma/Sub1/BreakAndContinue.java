/**
 * Program Name:	BreakAndContinue.java
 * Purpose:			Demonstrates how break and continue are used in a loop	
 * Coder:			Janice Manning, Student Number
 * Date:				October, 2018
  */

public class BreakAndContinue
{
	public static void main(String[] args)
	{
		int sum = 0;
		
		//1.  Use break to exit a loop if condition is met
		//loop will repeat for x = 1,2,3
		//total increments 1 + 2
		//when x = 3, if condition is true, and loop is terminated
		//how many times will the loop repeat?
		for(int x = 1; x <= 10; x++)
		{
			if(x == 3)		//if evaluates to be true, break exits the loop
			{
				break;
			}
			
			sum += x;
		}
		System.out.println(sum);
				
		//2.  Use continue to stop current loop iteration
		//but not to exit loop
		//instead return to loop increment
		//how many times will the loop repeat?
		sum = 0;
		for(int x = 1; x <= 10; x++)
		{
			if(x % 3 == 0)		//if evaluates to be true, continue sends control back to for for next x
				continue;
			
			sum += x;
		}
		System.out.println(sum);
		
		//3.  Use of break to exit from a loop early given a condition
		//how many times will the loop repeat?
		int counter = 0;
		while (counter != 5)
		{
			if (counter > 4)
				break;
			else 
				counter += 2;
		}
		
		//4.  Use to break to exit from a loop early
		for(int x = 0; x <= 10; x+=2)
		{
			if(x > 5)
				break;
			
			System.out.println(x);
		}
		
			
	}//End of main method
}//End of class