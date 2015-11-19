//quick driver to make sure the Controller object is working.

//TODO: 
//- implement gate opening/closing functionality.
//If we're opening the gate to a room, we have to open up every gate that leads to the room.
//If we're closing the gate to a room, we have to close only the gate leading directly to that room.
//If we close a gate and every gate at that junction is closed, we should also close the gate to that junction.
//
//- implement input from a config file so we don't have to hardcode addJunction and addRoom functions.

import java.util.*;

public class main
{
	public static void main (String[] args)
	{
		Furnace controller = new Furnace(); 	//eventually, we should move the furnace to its own object
							//and replace it with a Controller object that implements an interface.
							//Shouldn't be too hard, just a few name changes.
		controller.addJunction(0, 1);
		controller.addJunction(1, 2);
		controller.addJunction(1, 3);
		controller.addRoom(2, "bedroom1");
		controller.addRoom(2, "bathroom1");
		controller.addJunction(3, 6);
		controller.addRoom(3, "kitchen");
		controller.addRoom(3, "laundryroom");
		controller.addRoom(6, "livingroom");
		controller.addRoom(6, "bathroom2"); //this builds our system

		controller.printRooms(); //this goes through the system and prints out every leaf node it comes across.
					 //Doesn't print them in the order in which they were added, but that really doesn't matter.

		controller.openGate("bedroom1");
		controller.openGate("bathroom1");
		controller.closeGate("bedroom1");
		controller.closeGate("bathroom1");
		controller.openGate("kitchen");
		controller.openGate("livingroom");
		controller.closeGate("livingroom");
	}
}
