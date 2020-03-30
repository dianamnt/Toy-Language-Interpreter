package model;

public class GenHeapID {
	private static int number = 1;
	public static int getID()
	{
		return number++;
	}
}
