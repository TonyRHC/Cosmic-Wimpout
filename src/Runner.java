// Ri Hang Tony Chen (110410513)

import java.util.Scanner;

public class Runner 
{
	

	public static void main (String [ ] args) throws Supernova, Autowin
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("What is the Winning Total? ");
		int winningTotal = sc.nextInt();
		
		System.out.print("Enter player 1's name: ");
		String player1 = sc.next();
		sc.nextLine();
		System.out.print("Enter player 2's name: ");
		String player2 = sc.next();
		
		System.out.println("Rolling to see who goes first:");
		int player1Roll = (int)(Math.random() * 6);
		int player2Roll = (int)(Math.random() * 6);
		if (player1Roll > player2Roll)
		{
			System.out.println(player1 + " rolled " + player1Roll);
			System.out.println(player2 + " rolled " + player2Roll);
			System.out.println(player1 + " goes first!");
		}
		else
		{
			System.out.println(player1 + " rolled " + player1Roll);
			System.out.println(player2 + " rolled " + player2Roll);
			System.out.println(player2 + " goes first!");
			String temp = player1;
			player1 = player2;
			player2 = temp;
		}
		System.out.println();
		
		DiceSet a = new DiceSet();
		DiceSet b = new DiceSet();
		
		
/*		while(a.getScore() < winningTotal && b.getScore() < winningTotal)
		{
*/		
		
			System.out.println(player1 + "'s turn" + " " + "(Total Score: " + a.getScore() + ")");
			a.reset();
			a.roll();
			System.out.println(a);
			System.out.println("Points Scored: " + a.evaluate());
			
			System.out.println();
			System.out.println();

			
			System.out.println(player2 + "'s turn" + " " + "(Total Score: " + b.getScore() + ")");
			b.reset();
			b.roll();
			System.out.println(b);
			System.out.println("Points Scored: " + b.evaluate());
			
//		}
		
			
		if(a.getScore() > b.getScore())
		{
			System.out.println(player1 + " Wins!");
		}
		else
		{
			System.out.println(player2 + " Wins!");
		}
		
		
		
		
		
		
		
		
	}

}
