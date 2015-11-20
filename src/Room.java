//encapsulates the information of a room.
//All the system cares about is the name of the room we're accessing and its temperature.

public class Room
{
	private String name;
	private Thermometer temp;
	private double desiredTemp;
	private double tolerance;
	
	public Room(String name1)
	{
		name = name1;
		temp = new Thermometer(21.5); //arbitrary starting value
		desiredTemp = 21.5;
		tolerance = 1.5;
	}

	public void setDesiredTemp(double t1)
	{
		desiredTemp = t1;
	}

	public double getDesiredTemp()
	{
		return desiredTemp;
	}	

	public void setTolerance(double t1)
	{
		tolerance = t1;
	}

	public double getTolerance()
	{
		return tolerance;
	}		

	public String getName ()
	{
		return name;
	}

	public double getTemp()
	{
		return temp.getTemp();
	}
	
	public int checkTemp() //calls checkTemp on the thermometer object, which compares the temperature to a desired temp (temp1).
	{
		return temp.checkTemp(desiredTemp, tolerance);
	}

	public void increment(double num)
	{
		temp.increment(num);
	}	

	public void decrement(double num)
	{
		temp.decrement(num);
	}
};
