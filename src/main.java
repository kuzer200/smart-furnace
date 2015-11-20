//quick driver to make sure the Controller object is working.

//TODO: 
// -implement the UI

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class main
{
	public static void main (String[] args)
	{
		Controller controller = new Controller(); 

		Scanner input;

		try
		{
			input = new Scanner(new File("vent.txt"));
    		}

    		catch (Exception e)
		{
        		System.out.println("ERROR: File not found.");
			return;
    		}

		int p = 0;
		int c = 0;
		String room = "";
		
		while (input.hasNext())
		{
			p = input.nextInt();

			if (input.hasNextInt())
			{     
				c = input.nextInt();
				controller.addJunction(p, c);

		        }
			else
			{
				room = input.next();
				controller.addRoom(p, room);

			}

		}

		controller.debug_printRooms(); //this goes through the system and debug_prints out every leaf node it comes across.
					 //Doesn't debug_print them in the order in which they were added, but that really doesn't matter.

		controller.setDesiredRoomTemp("bedroom1", 21, 1);
		controller.setDesiredRoomTemp("kitchen", 21.5, 0.4);
		controller.setDesiredRoomTemp("laundryroom", 20, 2);
		controller.setDesiredRoomTemp("bathroom2", 21, 1);

		controller.check(22);
		controller.debug_printRooms();

		System.out.println("bedroom1: " + controller.getRoomInfo("bedroom1", "temperature") + " " + controller.getRoomInfo("bedroom1", "desiredtemp") + " " + controller.getRoomInfo("bedroom1", "tolerance") + " " + controller.getRoomInfo("bedroom1", "invalid"));


	}
}
