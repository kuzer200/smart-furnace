//Encapsulates a furnace.
//For the purpose of this program, it acts as a head node to a minimum spanning tree.

public class Furnace
{
	private Gate firstGate;
	boolean active;
	//insert other variables here
	
	public Furnace ()
	{
		firstGate = null;
		active = false;
	}

	public void setFirstGate(Gate g)
	{
		firstGate = g;
	}

	public Gate getFirstGate()
	{
		return firstGate;
	}

	public void on()
	{
		active = true;
	}

	public void off()
	{
		active = false;
	}

	public boolean getStatus()
	{
		return active;
	}	
};
