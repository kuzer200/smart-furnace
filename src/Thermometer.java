//encapsulates a thermometer object.  Stores a temperature, gives a temperature.  Easy.

public class Thermometer
{
	private double temperature;
	
	public Thermometer(double temp1)
	{
		temperature = temp1;
	}
	
	public double getTemp ()
	{
		return temperature;
	}
	
	public void setTemp (double temp1)
	{
		temperature = temp1;
	}

	public int checkTemp (double temp1) //3 is the tolerance level - the number 3 is arbitrary, and should probably be defined by the system.  I just chose it to demonstrate how the function should work
	{
		if (temperature < temp1 - 3)
			return -1;
		else if (temperature > temp1 + 3)
			return 1;
		else
			return 0;
	}	
};
