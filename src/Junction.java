//encapsulates a junction in the vent system.
//Junctions are paths that lead to or more junctions/rooms from another junction.

import java.util.*;

public class Junction
{
	private int id; 
	private ArrayList<Gate> gates;  //Important thing about the junctions:
					//if you have only one gate in a junction, then there's no reason to have a junction in the first place
					//since you can cut the middle man and have the preceding gate lead directly to where the single gate leads.
	
	public Junction(int id1) //an ID tells the controller where it should add new nodes
	{
		id = id1;
		gates = new ArrayList<Gate>();
	}
	
	public int getId ()
	{
		return id;
	}

	public int getNumOfGates() 	//will always return the number of gates in a junction - keep in mind that the 
					//highest index of the arraylist is always this number minus one.  Using this number exactly
					//causes unexpected results.
	{
		return gates.size();
	}

	public Gate getGate(int n) //as mentioned before, do not specify a number that's out of range - it won't work.
	{
		return gates.get(n);
	}
	
	public void addGate(int id) //overloaded function for adding nodes of any type
	{
		gates.add(new Gate(id));
	}

	public void addGate(String name)
	{
		gates.add(new Gate(name));
	}
};
