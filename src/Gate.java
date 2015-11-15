//encapsulates a gate for the smart furnace.
//Each gate can lead to either a junction or a room, and controls the air flow from the furnace to that room.

//encapsulates a gate in the smart furnace.

public class Gate
{
	private Junction nextJunction; //junction leads to either another junction or a room.  Should not lead to both.
	private Room nextRoom;
	private boolean open;
	
	public Gate(int id) 	//constructor for a gate that leads to a junction.  Note how if you call a constructor that initializes one type of node, 
				//the other node is always null.
	{
		nextJunction = new Junction(id);
		nextRoom = null;
	}
	
	public Gate(String name) //constructor for a gate that leads to a room (leaf node)
	{
		nextJunction = null;
		nextRoom = new Room(name);
	}
	
	public void changeState() //toggles state, same as open = !open
	{
		if (open == true)
			open = false;
		else
			open = true;
	}

	public Room getRoom()
	{
		return nextRoom;
	}

	public Junction getJunction()
	{
		return nextJunction;
	}
};
