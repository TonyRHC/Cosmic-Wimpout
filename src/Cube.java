//Ri Hang Tony Chen (110410513)

public class Cube 
{
	private int value;
	private int [ ] numbers = {10,2,3,4,5,6};
	
	public int value ()
	{
		return value;
	}
	
	public void roll ()
	{
		value = numbers[(int)(Math.random() * 5)];
	}
	
	public String toString()
	{
		String s = "";
		s += value;
		return s;
	}

}
