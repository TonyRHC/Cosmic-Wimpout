// Ri Hang Tony Chen (110410513)

import java.util.ArrayList;
import java.util.Scanner;

public class DiceSet 
{
	private static ArrayList<Cube> dice = new ArrayList<Cube>();
	private static ArrayList<Cube> usedDice = new ArrayList<Cube>();
	private int score = 0;
	
	public void reset()
	{
		dice.add(new Cube());
		dice.add(new Cube());
		dice.add(new Cube());
		dice.add(new Cube());
		dice.add(new SunCube());
	}
	
	public void roll()
	{

		for(int i = 0; i < 5; i++)
		{
			Cube temp = dice.get(i);
			temp.roll();
			dice.remove(i);
			dice.add(i, temp);
		}
		
	}
	
	public int diceLeft()
	{
		return dice.size();
	}
	
	public String toString()
	{
		String s = "Dices: ";
		for (int i = 0; i < 5; i++)
		{
			Cube temp = dice.get(i);
			s += temp.value() + " ";
		}	
		return s;
	}
	
	public int getScore()
	{
		return score;
	}
	

	
	public int evaluate() throws Supernova, Autowin
	{
		boolean mustRollAgain = true;
		boolean freightTrain = false;
		boolean hasWild = false;
		int tempScore = 0;

		while (mustRollAgain)
		{
			mustRollAgain = false;
			freightTrain = false;
			hasWild = false;
			
			int [ ] count = new int [6];
			for (int i = 0; i < 5; i++)
			{
				Cube temp = dice.get(i);
				if (temp.value() == 0)
				{
					hasWild = true;
				}
				else if (temp.value() == 10)
				{
					count[0] += 1;
				}
				else
				{
					count[temp.value()-1] += 1;
				}
			}
			

			if(true)
			{

				try
				{
					for (int i = 0; i < 6; i++)
					{
						if (count[i] == 5 || (count[i] == 4 && hasWild))
						{
							freightTrain = true;
						}
					}
					if (freightTrain)
					{
						if (count[0] == 5 || (count[0] == 4 && hasWild))
						{
							throw new Supernova();
						}
						else if (count[5] == 5 || (count[5] == 4 && hasWild))
						{
							throw new Autowin();
						}
						else
						{
							for (int i = 1; i < 5; i++)
							{
								if (count[i] == 5 || (count[i] == 4 && hasWild))
								{
									tempScore += (i+1) * 100;
								}
							}
						}
					}
				}
				catch (Supernova e)
				{
					System.out.println("You automatically lose from too many points");
				}
				catch (Autowin e)
				{
					System.out.println("You win with a freight train of 6's");
				}
				finally
				{
					
					if (freightTrain)
					{
						mustRollAgain = true;
						reset();
					}
					else
					{

						boolean flash = false;
						int indexNumber = 0;
						boolean not5or10 = false;
						boolean is5 = false;
						boolean is10 = false;
						
						
						
						
						
						for(int i = 0; i < 6; i++)
						{
							if (count[i] >= 3 || (count[i] == 2 && hasWild))
							{
								flash = true;
								
								if (i == 0)
								{
									is10 = true;
									tempScore += 100;
									break;
								}
								else if (i == 4)
								{
									is5 = true;
									tempScore += 50;
									break;
								}
								
								else if (i == 1 || i == 2 || i == 3 || i == 5)
								{
									indexNumber = i;
									not5or10 = true;
									tempScore += (indexNumber+1)*10;
									break;
								}

							}
						}
						
						
						
						if (flash)
						{
							System.out.println("Flash!");
							int j = 1;
							int loops = 0;
							while (j <= dice.size())
							{
								if (loops == 3)
								{
									break;
								}
								Cube temp = dice.get(j-1);
								if(is5)
								{
									if (temp.value() == 5 || temp.value() == 0)
									{
										usedDice.add(dice.get(j-1));
										dice.remove(j-1);
										j--;
										loops++;
									}

								}
								else if (is10)
								{
									if (temp.value() == 10 || temp.value() == 0)
									{
										usedDice.add(dice.get(j-1));
										dice.remove(j-1);
										j--;
										loops++;
									}

								}
								else if (not5or10)
								{
									if (temp.value() == indexNumber+1 || temp.value() == 0)
									{
										usedDice.add(dice.get(j-1));
										dice.remove(j-1);
										j --;
										loops++;
									}

								}
								
								j++;
							}
							

							
							
							
							int k = 1;
							while (k <= dice.size())
							{
								Cube temp = dice.get(k-1);
								if(temp.value() == 5)
								{
									tempScore += 5;
									usedDice.add(dice.get(k-1));
									dice.remove(k-1);
									k--;
								}
								else if (temp.value() == 10 || temp.value() == 0)
								{
									tempScore += 10;
									usedDice.add(dice.get(k-1));
									dice.remove(k-1);
									k--;
								}
								
								k++;

							}
							
							System.out.println("Futless rule");
							if (true)
							{
								for(int i = 0; i < dice.size(); i++)
								{
									Cube temp = dice.get(i);
									temp.roll();
									

									if (not5or10)
									{
										while(temp.value() == indexNumber)
										{
											temp.roll();
										}
									}
									else if (is5)
									{
										while(temp.value() == 5)
										{
											temp.roll();
										}
									}
									else if (is10)
									{
										while(temp.value() == 10)
										{
											temp.roll();
										}
									}
								}
								
								//Check rolls
								String rolls = "Dices after another roll: ";
								for (int h = 0; h < dice.size(); h++)
								{
									Cube temporary = dice.get(h);
									rolls += temporary.value() + " ";
								}	
								System.out.println(rolls);
								
								
								int m = 1;
								
								while(m <= dice.size())
								{
									Cube temp = dice.get(m-1);
									if(temp.value() == 5)
									{
										tempScore += 5;
										usedDice.add(dice.get(m-1));
										dice.remove(m-1);
									}
									else if (temp.value() == 10 || temp.value() == 0)
									{
										tempScore += 10;
										usedDice.add(dice.get(m-1));
										dice.remove(m-1);
									}
									
									m++;
								}
								System.out.println("Current score: " + tempScore);

							}
							

							
							if(dice.size() == 0)
							{
								System.out.println("Rerolling: You May Not Want To But You Must");
								mustRollAgain = true;
								reset();
								roll();
							}
							else if (dice.size() > 0)
							{
								
								boolean keepGoing = true;
								while(keepGoing)
								{
									Scanner sc = new Scanner(System.in);
									System.out.print("Continue? (Yes = 1/ No = 0): ");
									int input = sc.nextInt();
									
									if(input == 1)
									{
	
										for(int i = 0; i < dice.size(); i++)
										{
											Cube temp = dice.get(i);
											temp.roll();
		
										}
										
										//Check rolls
										String rolls = "Dices after another roll: ";
										for (int i = 0; i < dice.size(); i++)
										{
											Cube temp = dice.get(i);
											rolls += temp.value() + " ";
										}	
										System.out.println(rolls);
								
										int checkForWimp = tempScore;
										
										int m = 1;
										while(m <= dice.size())
										{
											Cube temp = dice.get(m-1);
											if(temp.value() == 5)
											{
												tempScore += 5;
												usedDice.add(dice.get(m-1));
												dice.remove(m-1);
											}
											else if (temp.value() == 10 || temp.value() == 0)
											{
												tempScore += 10;
												usedDice.add(dice.get(m-1));
												dice.remove(m-1);
											}
											m++;
										}
										
										if (checkForWimp == tempScore)
										{
											System.out.println("You wimped out!");
											tempScore = 0;
											break;
										}
										else if (dice.size() == 0)
										{
											keepGoing = false;
										}
									}
									
									else
									{
										break;
									}
 
								}
								
								

								
							}
							
							score += tempScore;
							
							if(dice.size() == 0)
							{
								System.out.println("Rerolling: You May Not Want To But You Must");
								mustRollAgain = true;
								reset();
								roll();
							}
							
							
						}
					
				
						
						
						
						// Without flash
						else
						{
							
							int k = 1;
							while (k <= dice.size())
							{
								Cube temp = dice.get(k-1);
								if(temp.value() == 5)
								{
									tempScore += 5;
									usedDice.add(dice.get(k-1));
									dice.remove(k-1);
									k--;
								}
								else if (temp.value() == 10 || temp.value() == 0)
								{
									tempScore += 10;
									usedDice.add(dice.get(k-1));
									dice.remove(k-1);
									k--;
								}
								
								k++;
	
							}
							
							if (usedDice.size() == 5)
							{
								System.out.println("You wimped out!");
							}
							
							else if (usedDice.size() <= 4)
							{
								boolean keepGoing = true;
								while(keepGoing)
								{
									Scanner sc = new Scanner(System.in);
									System.out.println("Current score: " + tempScore);
									System.out.print("Continue? (Yes = 1/ No = 0): ");
									int input = sc.nextInt();
									
									if(input == 1)
									{
	
										for(int i = 0; i < dice.size(); i++)
										{
											Cube temp = dice.get(i);
											temp.roll();
		
										}
										
										//Check rolls
										String rolls = "Dices after another roll: ";
										for (int i = 0; i < dice.size(); i++)
										{
											Cube temp = dice.get(i);
											rolls += temp.value() + " ";
										}	
										System.out.println(rolls);
								
										int checkForWimp = tempScore;
										
										int m = 1;
										while(m <= dice.size())
										{
											Cube temp = dice.get(m-1);
											if(temp.value() == 5)
											{
												tempScore += 5;
												usedDice.add(dice.get(m-1));
												dice.remove(m-1);
											}
											else if (temp.value() == 10 || temp.value() == 0)
											{
												tempScore += 10;
												usedDice.add(dice.get(m-1));
												dice.remove(m-1);
											}
											m++;
										}
										
										if (checkForWimp == tempScore)
										{
											System.out.println("You wimped out!");
											tempScore = 0;
											break;
										}
										else if (dice.size() == 0)
										{
											keepGoing = false;
										}
									}
									
									else
									{
										break;
									}
 
								}
								
								

								
							}
							
							score += tempScore;
							
							if(dice.size() == 0)
							{
								System.out.println("Rerolling: You May Not Want To But You Must");
								mustRollAgain = true;
								reset();
								roll();
							}
							
	
							
						}
														
							
						
						
						
			
						
						
						
					}
				}
				
				
				
			}
			
		}
		if(score >= 35)
		{
			System.out.println("You have entered the game!");
		}
		
		return score;
	}
	

}
