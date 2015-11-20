//Encapsulates a system controller.
//Does most of the work when searching through the system.

import java.text.DecimalFormat;

public class Controller
{
	private Furnace furnace;
	//insert other variables here
	
	public Controller ()
	{
		furnace = new Furnace();
	}
	
	public Gate getStart ()
	{
		return furnace.getFirstGate();
	}

	public void addJunction(int parentID, int newID) //setting up a recursive function
	{
		if (parentID == 0)
			furnace.setFirstGate(new Gate(newID));
		else
			addJunctionRec(parentID, newID, getStart());
	}

	public void addJunctionRec(int parentID, int newID, Gate g)
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
			furnace.setFirstGate(new Gate(name));
		else
			addRoomRec(parentID, name, getStart());
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

	public void check(int cycles) //a quick test to make sure the tree is being built properly.
	{
		
		for (int i = 0; i < cycles; i++)
		{
			//System.out.println("We are on cycle " + (i+1) + ".");
			checkRec(getStart());
		}
	}

	public void checkRec(Gate g)	
	{
		if (g.getRoom() != null) //if there's a room, give us its info.
		{
			if (g.getRoom().checkTemp() < 0)
			{
				openGate(g.getRoom().getName());	
				//System.out.println(g.getRoom().getName() + " got too cold, opening gate.");
			}
			else if (g.getRoom().checkTemp() > 0)
			{
				closeGate(g.getRoom().getName());	
				//System.out.println(g.getRoom().getName() + " got too hot, closing gate.");	
			}

			if (g.isOpen())
				g.getRoom().increment(0.1);
			else
				g.getRoom().decrement(0.1);
		}
		else
		{
			Junction temp = g.getJunction(); //otherwise, check the system for other rooms.

			for (int i = 0; i < temp.getNumOfGates(); i++)
			{
					checkRec(temp.getGate(i));
			}
		}
	}

	public void openGate(String name) //opens a gate
	{
		openGateRec(name, getStart());
	}

	public boolean openGateRec(String name, Gate g)
	{
		if (g.getRoom() != null && g.getRoom().getName().equals(name)) //if we find the room, open the gate
		{
			g.setOpen();
			return true; //make sure we tell the gate leading to the junction before the room that we found it
		}
		else if (g.getJunction() != null)
		{
			Junction temp = g.getJunction(); //otherwise, check the system for the room.
			
			for (int i = 0; i < temp.getNumOfGates(); i++)
					if (openGateRec(name, temp.getGate(i))) //if we opened a gate, we need to make a path to the furnace
					{
						g.setOpen();
						//System.out.println("We opened the gate to " + temp.getId());
						return true;
					}
		}

		return false;
	}

	public void closeGate(String name) //closes a gate
	{
		closeGateRec(name, getStart());
	}

	public boolean closeGateRec(String name, Gate g)
	{
		if (g.getRoom() != null && g.getRoom().getName().equals(name)) //if we find the room, close the gate
		{
			g.setClose();
			return true; //make sure we tell the gate leading to the junction before the room that we found it
		}
		else if (g.getJunction() != null)
		{
			Junction temp = g.getJunction(); //otherwise, check the system for the room.
			boolean allGatesAreClosed = true;
			
			for (int i = 0; i < temp.getNumOfGates(); i++)
					if (closeGateRec(name, temp.getGate(i))) //if we opened a gate, we need to make a path to the furnace
					{
						for (int j = 0; j < temp.getNumOfGates(); j++)
							if (temp.getGate(j).isOpen())
								allGatesAreClosed = false;

						if (allGatesAreClosed)
						{
							g.setClose();	
							//System.out.println("We closed the gate to " + temp.getId());			
						}
						return true;
					}
		}

		return false;
	}

	public void setDesiredRoomTemp(String name, double newtemp, double tolerance) //closes a gate
	{
		SDRT_rec(name, newtemp, tolerance, getStart());
	}

	public boolean SDRT_rec(String name, double newtemp, double tolerance, Gate g)
	{
		if (g.getRoom() != null && g.getRoom().getName().equals(name)) //if we find the room, close the gate
		{
			g.getRoom().setDesiredTemp(newtemp);
			g.getRoom().setTolerance(tolerance);
			return true; //make sure we tell the gate leading to the junction before the room that we found it
		}
		else if (g.getJunction() != null)
		{
			Junction temp = g.getJunction(); //otherwise, check the system for the room.
			
			for (int i = 0; i < temp.getNumOfGates(); i++)
				if (SDRT_rec(name, newtemp, tolerance, temp.getGate(i)))
				{
					return true;
				}
		}

		return false;
	}

	public double getRoomInfo(String name, String info) //opens a gate
	{
		return  getRoomInfoRec(name, info, getStart());
	}

	public double getRoomInfoRec(String name, String info, Gate g)
	{
		if (g.getRoom() != null && g.getRoom().getName().equals(name)) //if we find the room, get the data
		{
			if (info.equals("temperature"))
				return g.getRoom().getTemp();
			else if (info.equals("desiredtemp"))
				return g.getRoom().getDesiredTemp();
			else if (info.equals("tolerance"))
				return g.getRoom().getTolerance();
			else
				return -274; //just below absolute zero
		}
		else if (g.getJunction() != null)
		{
			Junction temp = g.getJunction();
			
			for (int i = 0; i < temp.getNumOfGates(); i++)
			{
				double tempS = getRoomInfoRec(name, info, temp.getGate(i));

				if (tempS != -274) //if we find a match
				{
						return tempS;
				}
			}
		}

		return -274;
	}

	public void debug_printRooms() //a quick test to make sure the tree is being built properly.
	{
		debug_printRoomsRec(getStart());
		System.out.println("\n");
	}

	public void debug_printRoomsRec(Gate g)
	{
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);

		if (g.getRoom() != null) //if there's a room, give us its info.
			System.out.println(g.getRoom().getName() + " " + df.format(g.getRoom().getTemp()) + " " + g.isOpen());
		else
		{
			Junction temp = g.getJunction(); //otherwise, check the system for other rooms.

			if (g.getRoom() == null)
				System.out.println("Gate to junction " + temp.getId() + ": " + g.isOpen());

			for (int i = 0; i < temp.getNumOfGates(); i++)
			{
					debug_printRoomsRec(temp.getGate(i));
			}
		}
	}
	
};
