/**
 * Program Name: Project_Methods.java
 * Purpose: Methods class for Craps Project
 * Coder: Bill Pulling for section 01
 * Date: MONDAY MARCH 6 VERSION 2 UPDATES
 *  */
import java.util.*;
public class Project_Methods
{
	/**
	 * Method Name:createPlayerArray()
	 * Purpose: prompts user to enter number of players used to create the array
	 * Returns: an array of type String
	 * Coder: BP
	 * Date: March 3, 2023
	*/
	public static String[] createPlayerArray()
	{
		String[]playerArray;
		int numPlayers;
		Scanner input = new Scanner(System.in);
		boolean badData = true;
		//prompt user to enter number of players and validate this...
		do
		{
			System.out.print("Enter the number of players for this game (minimum of 2 to maximum of 6):");
			numPlayers = input.nextInt();
			if(numPlayers <2 || numPlayers >6)
			{
				System.out.println("Number of players must be from 2 to 6, please re-enter:");
				badData = true;
			}
			else
			{
				badData = false;
			}
		//validate that input is from 
		}while(badData);
		//do a buffer flush because we are using nextLine() to read in the names
		input.nextLine();
		//create array and return it.
		playerArray = new String[numPlayers];
		
		//Populate player array with names
		for(int i = 0; i < playerArray.length; i++)
		{
			System.out.print("Enter first name of player# " + (i+1) + " and press ENTER: " );
			playerArray[i] = input.nextLine();
		}//end for
		
		return playerArray;
	}//end method
	
	/**
	 * Method Name: createBankRollArray()
	 * Purpose: creates array of type int to hold each player's bank roll and puts 100 into each element.
	 * Accepts:an int value that is the length of the playerArray
	 * Returns:an array of type int with each element holding an amount of 100 virtual dollars
	 * Coder: BP
	 * Date: March 3, 2023
	*/
	public static int[] createBankRollArray(int n)
	{
		int[] bankRollArray = new int[n];
		for(int i = 0; i < bankRollArray.length; i++)
		{
			bankRollArray[i] = 100;
		}
		return bankRollArray;
	}
	/**
	 * Method Name:showRules()
	 * Purpose: just prints the basic rules of the game of craps to the screen.
	 * Accepts: NOTHING
	 * Returns: NOTHING, just prints the rules to the console window. 
	 * Coder: BP
	 * Date: March 3, 2023
	*/
	public static void showRules()
	{
		System.out.println("\nHere are the basic rules of the game:\n");
		System.out.println("One round of play with one person shooting the dice is known as 'a pass'." );
;		System.out.println("\n1)The person with the dice is 'the shooter'. The shooter decides how much they want to bet, which is called 'the action'.");
		System.out.println("\n2)The shooter has to make a minimum bet of 10 dollars, and the bet amount must be a multiple of $10, ");
		System.out.println("but they can bet up to the maximum amount in their bank roll.");
		System.out.println("\n3)The other players are invited to 'take a piece of the action', which means they can bet an amount up to but not greater ");
		System.out.println("than the amount of the action. For example, if the 'action amount' bet was $50, then one player could 'take $30 of the action, ");
		System.out.println("a second player could 'take $10 of the action, and a third player could take the final $10 of the action.");
		System.out.println("The action of $50 would then be 'covered' and no other players in the game would be able to lay a bet on this round.");
		System.out.println("\nWhen all bets are placed, the shooter rolls the dice on the 'come out' roll. Here are the possible outcomes:");
		System.out.println("1)IF the shooter rolls a seven or eleven, then they have rolled 'a natural' and they win their bet. The bets of the other ");
		System.out.println(" players will be given to the shooter. ");
		System.out.println("\n2)IF the shooter rolls a two ('snake-eyes), a three ('ace-deuce'), or a twelve ('box-cars') then the shooter 'craps out' (loses) ");
		System.out.println(" and the amount of his action is split up and given to the other players according to the amount they bet.");
		System.out.println("\n3)IF the shooter rolls any other number besides 2,3,7,11, or 12, then that number becomes the shooter's point'. The shooter now  ");
		System.out.println(" rolls the dice again until they either roll their point again, in which case they have 'come' and won the pass, or they roll a seven, ");
		System.out.println("in which case they 'don't come' (lose) because they have 'sevened out'.");
		System.out.println("\n4)After a pass is completed, the shooter may choose to continue to roll the dice ('make another pass') or ");
		System.out.println("they can pass the dice to the next player ('pass on the bones').");
		System.out.println("\n5)If a player loses all of their money, they are out of the game ('cleaned out').");
		System.out.println("The game ends when one player has won all of the money in the game from the other players ('cleaned house'). \n");
		
	}//end method

	/**
	 * Method Name:validateShooterBet()
	 * Purpose: validates the bet amount placed by the shooter
	 * Accepts: the shooterID value which is the index of the player in the playerArray, an int that is the bet amount,  and the bankRollArray
	 * Returns:a boolean true if the bet is a valid amount, false otherwise.
	 * Coder:BP
	 * Date:March 3, 2023
	 * //REVISION: March 5...added a condition to make sure bet is a multiple of 10
	*/
	public static boolean validateShooterBet(int shooterID, int betAmount, int[] bankRollArray, int minBet)
	{
		//check bet amount is at least equal to minBet and is not larger than amount in bankRollArray
		//REVISION: March 5...added a condition to make sure bet is a multiple of 10
		if(betAmount >= minBet && betAmount <= bankRollArray[shooterID] && betAmount % 10 == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}//end method
	
	/**
	 * Method Name: validateOpponentBet()
	 * Purpose: validates the bets placed by the shooter's opponents. The amount bet by the opponent cannot be less than the minimum bet
	 *          and it cannot be greater than the amount in their bank roll, or greater than the amount of the remaining actionAmount. 
	 * Accepts: an int that is the opponentID, an int that is their bet amount, the bankRollArray, the minBet amount, and the remaining actionAmount.
	 * Returns: a boolean true if the bet amount is valid, a false if not.
	 * Coder:BP
	 * Date:March 3, 2023
	 * //REVISION: March 5...added a condition to make sure bet is a multiple of 10
	*/
	public static boolean validateOpponentBet(int opponentID, int betAmount, int[]bankRollArray,int minBet, int actionAmount)
	{
		if(betAmount >= minBet && betAmount <= bankRollArray[opponentID] && betAmount <= actionAmount && betAmount % 10 ==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}//end method
	
	/**
	 * Method Name:rollDice()
	 * Purpose: generates a random value from 2 to 12
	 * Accepts: NOTHING.
	 * Returns: an int in the range of 2 to 12 inclusive
	 * Coder:BP
	 * Date: March 3, 2023
	*/
	public static int rollDice()
	{
		//generate a random value from 1 to 6 inclusive
	  int dieOne = (int)(Math.random() * 6 + 1);
	  int dieTwo = (int)(Math.random() * 6 + 1);
	  return dieOne + dieTwo;
	}//end method
	
	/**
	 * Method Name:adjustBankBalances()
	 * Purpose: this method adjusts the bank balances of each player after a pass has been completed. If the shooter wins, then the opponents'
	 *          bet amounts are added to the shooter's bank balance. 
	 *          However, if the shooter loses, then his bank balance is reduced BY THE AMOUNT OF THE SHOOTER BET THAT WAS COVERED BY THE
	 *          OPPONENTS. For example, if the shooter bet $60 and his opponents only covered $40 of the $60, then if the shooter loses he
	 *          would have his bankRoll reduced by just $40, not the entire $60 bet. 
	 * Accepts: array of type int holding the bet amounts, the array of type int holding the bankRoll balances, an int representing the actionAmount, which
	 *          is the difference between the shooter's bet and the total of the opponent's bet, an int representing the shooter's shooterID number, and
	 *          a boolean value called shooterWinOrLose indicating if the shooter won (true) or lost(false) on the pass.
	 * Returns: an array of type int representing the player's bankRoll amounts. 
	 * Coder:BP
	 * Date:March 4, 2023
	*/
	public static int[] adjustBankBalances(int[]betAmountArray, int[] bankRollArray, int actionAmount, int shooterID, boolean shooterWinOrLose)
	{
		//SHOOTER HAS WON
		if(shooterWinOrLose)
		{
			for(int i = 0; i < bankRollArray.length; i++)
			{
				//if the loop counter is same as shooter id, just do a continue to skip the shooter and then ADD the other players bets to the shooter's account
				if(i == shooterID)
				{
					continue;
				}
				else
				{
					//add all the bet amounts to the shooters bank roll
					//System.out.println("*****STUB: 195: adding each bet amount to shooter's balance...");
					bankRollArray[shooterID]+=betAmountArray[i];
					
					//THEN WE NEED TO SUBTRACT each players bet amount from their account
					//System.out.println("*****STUB: 199: subtracting each bet amount from opponent's balances...");
					bankRollArray[i] -= betAmountArray[i];
				}
			}//end for			
		}//end if
		
		//SHOOTER HAS LOST
		else
		{
			//first, reduce the shooter's bank roll by the difference between the shooter's bet and the actionAmount, which represents the amount
			// of the shooter's bet that was left uncovered by the total of of the opponents' bets. For example, if the shooter bet $100, and two opponents
			// each bet $30, for a total of $60 against the shooter, then the actionAmount left uncovered would have been (100 - 60 = $40). So,
			// if the shooter loses, then the shooter's bank balance is reduced by (100 - 40) = $60.
			//STUB: System.out.println("STUB: 211 reducing shooter's bank roll by " +  (betAmountArray[shooterID] - actionAmount) );
			bankRollArray[shooterID]-= (betAmountArray[shooterID] - actionAmount);
			//STUB:  System.out.println("STUB 213: Bank balance for shooter is now " + bankRollArray[shooterID]);
			//now loop through the bankArray and adjust each opponent's balance by the amount in the betAmountArray
			//then loop through the opponents
			for(int i = 0; i < betAmountArray.length; i++)
			{
				//if the loop counter is same as shooter id, just do a continue to skip the shooter and then ask the other players for their bets
				if(i == shooterID)
				{
					continue;
				}
				else
				{
					//System.out.println("*****STUB 225: adding opponent bet amounts to each opponent's balance, LOOPcounter is at: " + i);
					bankRollArray[i]+=betAmountArray[i];
				}
			}//end for			
		}//end else			
		
		return bankRollArray;
	}//end method
	
	/**
	 * Method Name: getNextShooterID()
	 * Purpose: this method returns the index number of the next player who is eligible to be a shooter. To be eligible
	 *          the bank balance in the bankRollArray at that index must have a positive balance
	 * Accepts: an int that is the shooterID of the current shooter who wants to pass the dice to the next shooter, and the
	 *          int array holding the bank balances of all the players. 
	 * Returns: the next index number of the next eligible player based on the bank balance being greater than zero.
	 * NOTE: this method could be infinite if all players have a balance of zero, but that should never happen. At least one
	 *       player will always have a positive balance. IF that is not the case then you have a serious logic error elsewhere
	 *       in your code that adjusts the bank balances. 
	 * Coder:BP
	 * Date:March 6, 2023
	*/
	public static int getNextShooterID(int currentShooterID, int[] bankRollArray)
	{
		int counter = 1;
		//increment the currentShooterID by one and then test it
		currentShooterID++;
		//need a do while loop to check each element until we find a valid shooterID
		do
		{
			//System.out.println("STUB: insided getNextShooterID() method, counter value is " + counter);
			if(currentShooterID > bankRollArray.length -1)//then we're at end of array, so re-set the currentShooter to 0
			{
			  currentShooterID = 0;
			}
			else if(bankRollArray[currentShooterID] > 0)
			{
				return currentShooterID;
			}
			else
			{
				currentShooterID++;
			}
			//
		}while(true);
		
	}//end method
	
	/**
	 * Method Name:printPlayerBankBalances()
	 * Purpose: prints out the names of the players and their current bank balances after a pass has been decided.
	 * Accepts: the String[]playerArray and the int[] bankRollArray.
	 * Returns:NOTHING! Just prints the names and balances to the screen
	 * Coder: BP
	 * Date: March 4, 2023
	*/
	public static void printPlayerBankBalances(String[]playerArray, int[] bankRollArray)
	{
		for(int i = 0; i < playerArray.length; i++) 
		{
			System.out.println(playerArray[i] + " has $" + bankRollArray[i] + ".");
		}//end for
	}//end method
	
	/**
	 * Method Name:checkForWinner()
	 * Purpose: checks the bankRolLArray balances to see if anyone has won all the money in the game
	 * Accepts: the bankRollArray, and an int that is the total amount of money in the game.
	 * Returns: a boolean true if a player has won all the money, a false if no player has won all the money
	 * Coder:BP
	 * Date:Mar 4, 2023
	*/
	public static boolean checkForWinner(int[]bankRollArray, int moneyInGame)
	{
		for(int i = 0; i < bankRollArray.length;i++)
		{
			if(bankRollArray[i] == moneyInGame)
			{
				//we have a winner...return a true
				return true;
			}
		}//end for
		return false;
	}//end method
	
	/**
	 * Method Name:identifyWinner()
	 * Purpose: returns the name of the player who has won the game.
	 * Accepts: the String array of player names, and the int array with the bank balances, and the max money amount in the game. The
	 *          method traverses the loop to find the name of the winning player with the max money amount in their bank.
	 * Returns: a String with the name of the player who has won the game. If there is no winner this method returns a null string.
	 * Coder:BP
	 * Date:March 4, 2023
	*/
	public static String identifyWinner(String[] playerArray, int[]bankRollArray, int moneyInGame)
	{
		for(int i = 0; i < playerArray.length; i++)
		{
			if(bankRollArray[i] == moneyInGame)
			{
				//System.out.println("STUB in method identifyWInner: winner name is " + playerArray[i]);
				return playerArray[i];
			}
		}//end for
		return null;
	}//end method
	
}//end class

/**
 * Method Name:
 * Purpose:
 * Accepts: 
 * Returns: 
 * Coder:BP
 * Date:March 4, 2023
*/
