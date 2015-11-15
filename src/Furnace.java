//Encapsulates a furnace.
//For the purpose of this program, it acts as a head node to a minimum spanning tree.
//Does most of the work when searching through the system

public class Furnace
{
	private Gate firstGate; //Our furnace has one output
	private boolean active;
	//insert other variables here
	
	public Furnace ()
	{
		firstGate = null;
		active = false;
	}
	
	public Gate getGate () //standard getter - might not need
	{
		return firstGate;
	}

	public boolean getStatus ()
	{
		return active;
	}

	public void toggle () //turn on if off and vice versa
	{
		if (active)
			active = false;
		else
			active = true;
	}
	
	public void addJunction(int parentID, int newID) //setting up a recursive function
	{
		if (parentID == 0) //0 refers to the furnace node
			firstGate = new Gate(newID);
		else
			addJunctionRec(parentID, newID, firstGate); //firstGate is basically the head node in a tree
	}

	public void addJunctionRec(int parentID, int newID, Gate g) //oh shit, a recursive function
	{
		if (g.getRoom() != null) //if this gate leads to a room, stop checking this branch
			return;
		else
		{
			Junction temp = g.getJunction();
			
			if (temp.getId() == parentID)
				temp.addGate(newID); //if this is the correct node, add a new path to the junction, then stop checking the branch
			else
				for (int i = 0; i < temp.getNumOfGates(); i++)
					addJunctionRec(parentID, newID, temp.getGate(i));
		}

		//the biggest flaw with this function is that it does not stop checking the system once it finds a match.
		//obviously, this is not a performance issue since the number of rooms will always be small.
		//however, it's important to make sure that each junction has a unique ID and that each room has a unique name.
	}

	public void addRoom(int parentID, String name)  //exact same thing as above, but with a string name.
							//if we wanted extra points, we could mess around with template functions.
	{
		if (parentID == 0)
			firstGate = new Gate(name); // this will happen very rarely - this means the furnace is only heating one room.
		else
			addRoomRec(parentID, name, firstGate);
	}

	public void addRoomRec(int parentID, String name, Gate g) 
	{
		if (g.getRoom() != null)
			return;
		else
		{
			Junction temp = g.getJunction();
			
			if (temp.getId() == parentID)
				temp.addGate(name);
			else
				for (int i = 0; i < temp.getNumOfGates(); i++)
					addRoomRec(parentID, name, temp.getGate(i));
		}
	}

	public void printRooms() //a quick test to make sure the tree is being built properly.
	{
		printRoomRec(firstGate);
	}

	public void printRoomRec(Gate g)
	{
		if (g.getRoom() != null) //if there's a room, give us its info.
			System.out.println(g.getRoom().getName());
		else
		{
			Junction temp = g.getJunction(); //otherwise, check the system for other rooms.
			
			for (int i = 0; i < temp.getNumOfGates(); i++)
					printRoomRec(temp.getGate(i));
		}
	}
	
};