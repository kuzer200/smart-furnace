//encapsulates the information of a room.
//All the system cares about is the name of the room we're accessing and its temperature.

public class Room
{
	private String name;
	private Thermometer temp;
	
	public Room(String name1)
	{
		name = name1;
		temp = new Thermometer(21.5); //arbitrary starting value
	}
	
	public String getName ()
	{
		return name;
	}

	public double getTemp()
	{
		return temp.getTemp();
	}
	
	public int checkTemp(double temp1) //calls checkTemp on the thermometer object, which compares the temperature to a desired temp (temp1).
	{
		return temp.checkTemp(temp1);
	}
};
