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

	public int checkTemp (double desiredTemp, double tolerance)
	{
		if (temperature < desiredTemp - tolerance)
			return -1;
		else if (temperature > desiredTemp + tolerance)
			return 1;
		else
			return 0;
	}

	public void increment(double num)
	{
		temperature += num;
	}	

	public void decrement(double num)
	{
		temperature -= num;
	}
};
